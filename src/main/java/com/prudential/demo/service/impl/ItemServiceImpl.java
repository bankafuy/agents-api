package com.prudential.demo.service.impl;

import com.prudential.demo.dto.ItemDTO;
import com.prudential.demo.model.Item;
import com.prudential.demo.repository.ItemRepository;
import com.prudential.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ItemDTO> getDataList() {
        List<ItemDTO> results = new LinkedList<>();
        final Iterable<Item> dataList = repository.findAll();

        dataList.forEach(item -> {
            final ItemDTO result = modelMapper.map(item, ItemDTO.class);
            results.add(result);
        });

        return results;
    }
}
