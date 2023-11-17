package com.yzl.service.controller;

import com.yzl.service.common.ReturnMessage;
import com.yzl.service.domain.ItemBase;
import com.yzl.service.service.ItemBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai
 * @date 2023/11/16 17:06
 */
@RestController
@RequestMapping("base")
public class ItemBaseController {

    @Autowired
    private ItemBaseService itemBaseService;

    @GetMapping("/loadItemBaseTree")
    public ResponseEntity<Object> loadItemBaseTree(String baseId){
        ItemBase itemBase = itemBaseService.loadItemBaseTree(baseId);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(itemBase));
    }
}
