package service;

import repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartServiseImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public boolean addToCartById(long userId, long productId) {
        return cartRepository.addToCartById(userId, productId);
    }

    @Override
    public boolean deleteFromCartById(long userId, long productId) {
        return cartRepository.deleteFromCartById(userId, productId);
    }
}
