package com.yzl.service.controller;

import com.yzl.service.common.Page;
import com.yzl.service.common.ReturnMessage;
import com.yzl.service.domain.UserInfo;
import com.yzl.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Object> deleteUserInfo(@RequestParam Long userId) {
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

    @GetMapping("/selectSidebarList")
    public ResponseEntity<Object> getSidebarList() {
        return ResponseEntity.status(HttpStatus.OK).body(ReturnMessage.successMessage(userService.selectSidebarList()));
    }
}
