package com.fmontalvoo.springboot.app.service;

import java.util.List;

import com.fmontalvoo.springboot.app.models.Item;
import com.fmontalvoo.springboot.commons.models.Producto;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);

	public Producto update(Long id, Producto producto);

	public void delete(Long id);


}
