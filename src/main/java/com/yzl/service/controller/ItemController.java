package com.yzl.service.controller;

import com.yzl.service.common.Page;
import com.yzl.service.common.ReturnMessage;
import com.yzl.service.domain.Item;
import com.yzl.service.domain.ItemBase;
import com.yzl.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kai
 * @date 2023/11/16 17:06
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/loadItemPage")
    public ResponseEntity<Object> loadItemPage(@ModelAttribute Page<Item> itemPage, Item item){
        Page<Item> page = itemService.loadItemPage(itemPage, item);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(page));
    }

    @PostMapping("/addItem")
    public ResponseEntity<Object> addItem(@RequestBody Item item){
        Boolean addItem = itemService.addItem(item);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(addItem));
    }
}
