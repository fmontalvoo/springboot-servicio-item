package com.fmontalvoo.springboot.app.rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fmontalvoo.springboot.commons.models.Producto;

@FeignClient(name = "ms-productos/api/v1/productos")
public interface ProductoRestClient {

	@PostMapping
	public Producto save(@RequestBody Producto producto);

	@GetMapping("/{id}")
	public Producto findById(@PathVariable Long id);

	@PutMapping("/{id}")
	public Producto update(@PathVariable Long id, @RequestBody Producto producto);

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id);

	@GetMapping
	public List<Producto> findAll();
}
