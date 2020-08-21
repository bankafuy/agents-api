package com.prudential.demo.service;

import com.prudential.demo.dto.ItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

public interface ItemService {

    public List<ItemDTO> getDataList();
}
