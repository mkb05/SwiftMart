package com.Inventory.Stock.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Inventory.Stock.Entity.Inventory;
import com.Inventory.Stock.Repository.InventoryRepository;

@Service
public class InventoryService {

	private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory createInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    public Inventory updateInventory(Long productId, Integer quantity) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for productId: " + productId));
        inv.setQuantity(quantity);
        return repository.save(inv);
    }

    public void deleteInventory(Long productId) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for productId: " + productId));
        repository.delete(inv);
    }

    public Inventory getInventoryByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for productId: " + productId));
    }

    public List<Inventory> getAllInventory() {
        return repository.findAll();
    }
}
