package com.yzl.service.controller;

import com.yzl.service.common.ReturnMessage;
import com.yzl.service.domain.ItemBase;
import com.yzl.service.service.ItemBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        return ResponseEntity.ok().body(ReturnMessage.amisSuccessMessage(itemBase));
    }

    @GetMapping("/loadBaseTree")
    public ResponseEntity<Object> loadBaseTree(String baseId){
        ItemBase itemBase = itemBaseService.loadItemBaseTree(baseId);
        List<ItemBase> itemBases = new ArrayList<>();
        itemBases.add(itemBase);
        return ResponseEntity.ok().body(ReturnMessage.amisSuccessMessage(itemBases));
    }

    @PostMapping("/editItemBase")
    public ResponseEntity<Object> editItemBase(ItemBase itemBase){
        Integer count = itemBaseService.editItemBase(itemBase);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(count > 0));
    }
}
