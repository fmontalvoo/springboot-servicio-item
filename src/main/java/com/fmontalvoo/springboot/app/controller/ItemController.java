package com.fmontalvoo.springboot.app.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmontalvoo.springboot.app.models.Item;
import com.fmontalvoo.springboot.app.models.Producto;
import com.fmontalvoo.springboot.app.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/items")
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private CircuitBreakerFactory cbf;

	@Autowired
//	@Qualifier("itemService")
	private ItemService service;

	@GetMapping
	public List<Item> findAll() {
		return service.findAll();
	}

//	@GetMapping("/{id}/{cantidad}")
//	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
//		return cbf.create("items").run(() -> service.findById(id, cantidad), e -> metodoAlternativoA(id, cantidad, e));
//	}

//	@GetMapping("/{id}/{cantidad}")
//	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativoA")
//	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
//		return service.findById(id, cantidad);
//	}

	@GetMapping("/{id}/{cantidad}")
	@TimeLimiter(name = "items", fallbackMethod = "metodoAlternativoB")
	public CompletableFuture<Item> findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(() -> service.findById(id, cantidad));
	}

	public Item metodoAlternativoA(Long id, Integer cantidad, Throwable error) {
		log.error(error.getMessage());
		Producto producto = new Producto();
		producto.setId(-1L);
		producto.setNombre("");
		producto.setPrecio(0.0);
		producto.setCreatedAt(new Date());
		return new Item(producto, cantidad);
	}

	public CompletableFuture<Item> metodoAlternativoB(Long id, Integer cantidad, Throwable error) {
		log.error(error.getMessage());
		Producto producto = new Producto();
		producto.setId(-1L);
		producto.setNombre("");
		producto.setPrecio(0.0);
		producto.setCreatedAt(new Date());
		return CompletableFuture.supplyAsync(() -> new Item(producto, cantidad));
	}

}
