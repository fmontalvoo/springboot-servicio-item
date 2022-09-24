package com.fmontalvoo.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmontalvoo.springboot.app.models.Item;
import com.fmontalvoo.springboot.app.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
//	@Qualifier("itemService")
	private ItemService service;

	@GetMapping
	public List<Item> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}/{cantidad}")
	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		return service.findById(id, cantidad);
	}

}
