package com.Inventory.Stock.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Inventory.Stock.Entity.Inventory;
import com.Inventory.Stock.Service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	 private final InventoryService service;

	    public InventoryController(InventoryService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
	        return ResponseEntity.ok(service.createInventory(inventory));
	    }

	    @PutMapping("/{productId}")
	    public ResponseEntity<Inventory> updateInventory(@PathVariable Long productId,
	                                                     @RequestParam Integer quantity) {
	        return ResponseEntity.ok(service.updateInventory(productId, quantity));
	    }

	    @DeleteMapping("/{productId}")
	    public ResponseEntity<Void> deleteInventory(@PathVariable Long productId) {
	        service.deleteInventory(productId);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/{productId}")
	    public ResponseEntity<Inventory> getInventory(@PathVariable Long productId) {
	        return ResponseEntity.ok(service.getInventoryByProductId(productId));
	    }

	    @GetMapping
	    public ResponseEntity<List<Inventory>> getAllInventory() {
	        return ResponseEntity.ok(service.getAllInventory());
	    }
}
