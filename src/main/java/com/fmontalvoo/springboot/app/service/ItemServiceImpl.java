package com.fmontalvoo.springboot.app.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fmontalvoo.springboot.app.models.Item;
import com.fmontalvoo.springboot.app.models.Producto;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	private static final String URL_PRODUCTOS = "http://localhost:8001/api/v1/productos";

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject(URL_PRODUCTOS, Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject(URL_PRODUCTOS.concat("/{id}"), Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

}
