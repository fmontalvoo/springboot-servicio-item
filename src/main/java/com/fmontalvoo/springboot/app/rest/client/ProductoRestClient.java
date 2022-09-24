package com.fmontalvoo.springboot.app.rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fmontalvoo.springboot.app.models.Producto;

@FeignClient(name = "ms-productos/api/v1/productos")
public interface ProductoRestClient {

	@GetMapping
	public List<Producto> findAll();
	
	@GetMapping("/{id}")
	public Producto findById(@PathVariable Long id);
}
