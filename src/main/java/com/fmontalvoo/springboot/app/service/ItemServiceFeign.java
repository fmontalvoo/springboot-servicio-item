package com.fmontalvoo.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fmontalvoo.springboot.app.models.Item;
import com.fmontalvoo.springboot.app.rest.client.ProductoRestClient;
import com.fmontalvoo.springboot.commons.models.Producto;

@Service
@Primary
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoRestClient client;

	@Override
	public List<Item> findAll() {
		return client.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(client.findById(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return client.save(producto);
	}

	@Override
	public Producto update(Long id, Producto producto) {
		return client.update(id, producto);
	}

	@Override
	public void delete(Long id) {
		client.delete(id);
	}

}
