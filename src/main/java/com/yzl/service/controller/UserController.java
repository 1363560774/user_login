package com.yzl.service.controller;

import com.yzl.service.common.Page;
import com.yzl.service.common.ReturnMessage;
import com.yzl.service.domain.Menu;
import com.yzl.service.domain.TDesignMenu;
import com.yzl.service.domain.UserInfo;
import com.yzl.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户前端交互层
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userListPage")
    public ResponseEntity<Object> userListPage(Page<UserInfo> userInfoPage){
        Page<UserInfo> examItems = userService.userListPage(userInfoPage);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(examItems));
    }

    @GetMapping("/userList")
    public ResponseEntity<Object> userList(String username){
        List<UserInfo> examItems = userService.userListByUsername(username);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(examItems));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserInfo userInfo) {
        Boolean data = userService.addUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(data));
    }

    @PostMapping("/addUserInfo")
    public ResponseEntity<Object> addUserInfo(@RequestBody UserInfo userInfo) {
        Boolean data = userService.addUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(data));
    }

    @PutMapping("/alterUserInfo")
    public ResponseEntity<Object> alterUserInfo(@RequestBody UserInfo userInfo) {
        Boolean data = userService.alterUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(data));
    }

    @GetMapping("/getUserInfoById")
    public ResponseEntity<Object> getUserInfoById(@RequestParam String userId) {
        UserInfo userInfo = userService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(userInfo));
    }

    @DeleteMapping("/deleteUserInfo")
    public ResponseEntity<Object> deleteUserInfo(String userId) {
        Boolean data = userService.deleteUserInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(data));
    }

    @PostMapping("/roleUpdate")
    public ResponseEntity<Object> roleUpdate(String selectItem, String sidebarId) {
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(userService.roleUpdate(selectItem, sidebarId)));
    }

    @PostMapping("/roleDelete")
    public ResponseEntity<Object> roleDelete(Byte sidebarId) {
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(userService.roleDelete(sidebarId)));
    }

    @PostMapping("/roleCreate")
    public ResponseEntity<Object> roleCreate(String author, String selectItem) {
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(userService.roleCreate(author, selectItem)));
    }

    @GetMapping("/loadMenu")
    public ResponseEntity<Object> loadMenu(){
        List<Menu> menus = userService.loadMenu();
        return ResponseEntity.ok().body(ReturnMessage.successMessage(menus));
    }

    @GetMapping("/loadTdMenu")
    public ResponseEntity<Object> loadTdMenu(){
        List<TDesignMenu> tDesignMenus = userService.loadTdMenu();
        Map<String, List<TDesignMenu>> list = new HashMap<>();
        list.put("list", tDesignMenus);
        return ResponseEntity.ok().body(ReturnMessage.amisSuccessMessage(list));
    }

    @GetMapping("/selectMenuList")
    public ResponseEntity<Object> selectMenuList() {
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = userService.loadMenu();
        map.put("menus", menus);
        Map<String, Object> adminInfo = new HashMap<>();
        Map<String, Object> siteConfig = new HashMap<>();
        Map<String, Object> terminal = new HashMap<>();
        terminal.put("installServicePort", "8000");
        terminal.put("npmPackageManager", "pnpm");
        map.put("terminal", terminal);
        Map<String, Object> upload = new HashMap<>();
        upload.put("maxsize", 10485760);
        upload.put("savename", "/storage/{topic}/{year}{mon}{day}/{filename}{filesha1}{.suffix}");
        upload.put("mimetype", "jpg,png,bmp,jpeg,gif,webp,zip,rar,xls,xlsx,doc,docx,wav,mp4,mp3,txt");
        upload.put("mode", "local");
        siteConfig.put("siteName", "BuildAdmin演示站");
        siteConfig.put("version", "v1.0.0");
        siteConfig.put("cdnUrl", "https://demo.buildadmin.com");
        siteConfig.put("apiUrl", "https://buildadmin.com");
        siteConfig.put("upload", upload);
        map.put("siteConfig", siteConfig);
        adminInfo.put("avatar", "/static/images/avatar.png");
        adminInfo.put("id", 1);
        adminInfo.put("last_login_time", "2023-11-22 14:17:59");
        adminInfo.put("nickname", "Admin");
        adminInfo.put("super", true);
        adminInfo.put("username", "admin");
        map.put("adminInfo", adminInfo);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(map));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Object> dashboard() {
        Map<String, Object> map = new HashMap<>();
        map.put("remark", "开源等于互助；开源需要大家一起来支持，支持的方式有很多种，比如使用、推荐、写教程、保护生态、贡献代码、回答问题、分享经验、打赏赞助等；欢迎您加入我们！");
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(map));
    }
}
