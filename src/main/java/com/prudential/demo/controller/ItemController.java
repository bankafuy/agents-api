package com.prudential.demo.controller;

import com.prudential.demo.dto.ItemDTO;
import com.prudential.demo.model.Item;
import com.prudential.demo.repository.ItemRepository;
import com.prudential.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService service;
    @Autowired
    private ItemRepository repo;

    @GetMapping("/items")
    public Object getData() {
        Item item = new Item();
        item.setDate(new Date());
        item.setId("id2");
        item.setName("name");
        item.setTotal(19);
        final Item save = repo.save(item);
        return save;
//        final List<ItemDTO> dataList = service.getDataList();
//        dataList.forEach(it -> System.out.println(it.getName()));
//        return dataList;
    }
}
