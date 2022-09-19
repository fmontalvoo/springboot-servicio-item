package com.fmontalvoo.springboot.app.service;

import java.util.List;

import com.fmontalvoo.springboot.app.models.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

}
