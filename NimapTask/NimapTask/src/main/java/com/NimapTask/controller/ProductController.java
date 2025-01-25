package com.NimapTask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.NimapTask.entity.Product;
import com.NimapTask.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	 @Autowired
	 private ProductService productService;

	 @GetMapping
	 public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     PageRequest pageable = PageRequest.of(page, size);
	     return productService.getAllProducts(pageable);
	 }

	 @PostMapping
	 public Product createProduct(@RequestBody Product product) {
	     return productService.createProduct(product);
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	     Optional<Product> product = productService.getProductById(id);
	     return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	 }

	 @PutMapping("/{id}")
	 public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	     Product updatedProduct = productService.updateProduct(id, productDetails);
	     return ResponseEntity.ok(updatedProduct);
	 }

	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	     productService.deleteProduct(id);
	     return ResponseEntity.noContent().build();
	 }
}
