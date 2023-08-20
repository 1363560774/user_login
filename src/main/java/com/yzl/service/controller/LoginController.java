package com.yzl.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzl.service.common.ReturnMessage;
import com.yzl.service.common.utils.RestTemplate;
import com.yzl.service.common.utils.WxTokenUtils;
import com.yzl.service.domain.Login;
import com.yzl.service.domain.UserInfo;
import com.yzl.service.properties.WxProperties;
import com.yzl.service.service.LoginService;
import com.yzl.service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private WxProperties wxProperties;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody Login userLogin,
            HttpServletResponse response, HttpServletRequest request){
        // 登录
        String token;
        try {
            token = loginService.login(userLogin);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ReturnMessage.errorMessageDate(null,"登录失败，请校验用户名密码是否正确"));
        }
        return ResponseEntity.ok().body(ReturnMessage.SuccessMessage(null));
    }

    @GetMapping("/xcxLogin")
    public ResponseEntity<Object> xcxLogin(String js_code, HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        } if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ResponseEntity.ok().body(ReturnMessage.SuccessMessage(loginService.xcxLogin(js_code, ip)));
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout( HttpServletResponse response, HttpServletRequest request){
        try {
            // 获取用户信息
            Login userLogin = new Login();
            //返回用户
            return ResponseEntity.ok(ReturnMessage.SuccessMessage(userLogin));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 校验登录状态
     * @return 结果，成功会刷新token
     */
    @PostMapping("/info")
    public ResponseEntity<Object> info( HttpServletResponse response, HttpServletRequest request){
        try {
            UserInfo userInfo = userService.getById("userId");;
            Map<String, Object> map = new HashMap<>(5);
            return ResponseEntity.ok(ReturnMessage.SuccessMessage(map));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("wxLogin")
    public void wxLogin(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        try (PrintWriter out = response.getWriter()) {
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (WxTokenUtils.checkSignature(wxProperties.getWxToken(), signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * pc点击微信登录，生成登录二维码
     * @author wangsong
     * @date 2019年6月19日 下午5:58:56
     * @param request 请求
     */
    @ResponseBody
    @PostMapping(value = "/wxLoginPage")
    public ResponseEntity<Object> wxLoginPage(HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        // 设置redirect_uri和state=sessionId以及测试号信息，返回授权url
        String uri = this.getAuthorizationUrl("pc", sessionId);
        Map<String, String> data = new HashMap<String, String>();
        data.put("sessionId", sessionId);
        // 用来前端生成二维码
        data.put("uri", uri);
        // 生成二维码清除上一个用户的数据
        return ResponseEntity.ok(ReturnMessage.SuccessMessage(data));
    }

    /**
     * 扫描二维码授权成功，取到code，设置的回调方法
     * @author wangsong
     * @date 2019年6月19日 下午5:58:36
     * @param code 状态码
     * @param state 状态
     * @param request 请求
     * @param response 响应
     */
    @ResponseBody
    @RequestMapping(value = "/pcAuth")
    public String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response,
                             HttpSession session) throws Exception {
        // 根据code获取access_token和openId，不懂看微信文档
        String result = this.getAccessToken(code);
        JSONObject jsonObject = JSONObject.parseObject(result);
        // String refresh_token = jsonObject.getString("refresh_token");
        String access_token = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openId");
        // 授权成功 --> 根据token和openId获取微信用户信息，不懂看我上一篇文章开始分享的链接
        JSONObject infoJson = this.getUserInfo(access_token, openId);
        if (infoJson != null) {
            infoJson.put("openId", openId);
        }
        return "登录成功";
    }

    /**
     * 获取生成的二维码url连接
     * @param state：非必传，重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param type：类型
     * @return url
     */
    public String getAuthorizationUrl(String type, String state) {
        // url
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
        String callbackUrl = "";
        Object urlState = "";
        if ("pc".equals(type)) {
            // pc端回调方法（没处理，我这里回调一致）
            callbackUrl = URLEncoder.encode(wxProperties.getWeCatRedirectUrl() + "/pcAuth", StandardCharsets.UTF_8);
            urlState = state;
        } else if ("mobile".equals(type)) {
            // pc端回调方法
            callbackUrl = URLEncoder.encode(wxProperties.getWeCatRedirectUrl() + "/pcAuth", StandardCharsets.UTF_8);
            urlState = System.currentTimeMillis();
        }
        // 权限 snsapi_userinfo snsapi_base
        String scope = "snsapi_userinfo";
        url = String.format(url, wxProperties.getWeCatAppId(), callbackUrl, scope, urlState);
        return url;
    }

    /**
     * 获取 token, openId（token有效期2小时）
     * @param code 状态码
     * @return 用户信息
     */
    public String getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        url = String.format(url, wxProperties.getWeCatAppId(), wxProperties.getWeCatAppSecret(), code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = RestTemplate.getRestTemplate().getForObject(uri, String.class);
        if (resp != null && resp.contains("openid")) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            String access_token = jsonObject.getString("access_token");
            String openId = jsonObject.getString("openid");
            JSONObject res = new JSONObject();
            res.put("access_token", access_token);
            res.put("openId", openId);
            res.put("refresh_token", jsonObject.getString("refresh_token"));
            return res.toJSONString();
        } else {
            return null;
        }
    }

    /**
     * 获取用户信息
     * @param accessToken token
     * @param openId openId
     * @return 用户信息
     */
    public JSONObject getUserInfo(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
        url = String.format(url, accessToken, openId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        String resp = RestTemplate.getRestTemplate().getForObject(uri, String.class);
        if (resp != null && resp.contains("errcode")) {
            return null;
        } else {
            JSONObject data = JSONObject.parseObject(resp);
            JSONObject result = new JSONObject();
            result.put("id", data.getString("unionid"));
            result.put("sex", data.getString("sex"));
            result.put("nickName", data.getString("nickname"));
            result.put("avatar", data.getString("headimgurl"));
            return result;
        }
    }

    /**
     * 微信的token只有2小时的有效期，过时需要重新获取，所以官方提供了,根据refresh_token
     * 刷新获取token的方法，本项目仅仅是获取用户，信息，并将信息存入库，所以两个小时也已经足够了
     * @param refresh_token token
     * @return access_token
     */
    public String refreshToken(String refresh_token) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        url = String.format(url, wxProperties.getWeCatAppId(), refresh_token);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        ResponseEntity<JSONObject> resp = RestTemplate.getRestTemplate().getForEntity(uri, JSONObject.class);
        JSONObject jsonObject = resp.getBody();
        assert jsonObject != null;
        return jsonObject.getString("access_token");
    }
}
