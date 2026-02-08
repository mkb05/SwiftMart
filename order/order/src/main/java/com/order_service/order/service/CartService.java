package com.order_service.order.service;

import org.springframework.stereotype.Service;

import com.order_service.order.Entity.Cart;
import com.order_service.order.Entity.Cart.CartStatus;
import com.order_service.order.Entity.CartItem;
import com.order_service.order.Repository.CartItemRepository;
import com.order_service.order.Repository.CartRepository;

@Service
public class CartService {

	private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;
	public CartService(CartRepository cartRepo, CartItemRepository itemRepo) {
		super();
		this.cartRepo = cartRepo;
		this.itemRepo = itemRepo;
	}
    
	public Cart addToCart(Long userId, Long productId, int quantity, double price) {

        Cart cart = cartRepo
                .findByUserIdAndStatus(userId, CartStatus.ACTIVE)
                .orElseGet(() -> cartRepo.save(new Cart(userId)));

        CartItem item = itemRepo
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem();
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setCart(cart);
            cart.getItems().add(item);
        }

        return cartRepo.save(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepo
                .findByUserIdAndStatus(userId, CartStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart updateQuantity(Long userId, Long productId, int quantity) {
        Cart cart = getCart(userId);

        CartItem item = itemRepo
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (quantity <= 0) {
            itemRepo.delete(item);
        } else {
            item.setQuantity(quantity);
        }

        return cartRepo.save(cart);
    }
}
