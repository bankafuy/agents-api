package com.prudential.demo.repository;

import com.prudential.demo.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {

}
