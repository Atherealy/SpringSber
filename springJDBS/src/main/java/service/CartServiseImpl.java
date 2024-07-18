package service;

import model.Client;
import repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import repository.ProductRepository;

@AllArgsConstructor
@Service
public class CartServiseImpl implements CartService {
    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    @Override
    public boolean addToCartById(long userId, long productId) {
        return clientRepository
                .findById(userId)
                .map(client -> findProductAndAddToCart(client, productId))
                .orElse(false);
    }

    @Override
    public boolean deleteFromCartById(long userId, long productId) {
        return clientRepository
                .findById(userId)
                .map(client -> cartRepository.deleteFromCart(client, productId))
                .orElse(false);
    }

    private boolean findProductAndAddToCart(Client client, long productId) {
        return productRepository
                .findById(productId)
                .map(product -> cartRepository.addProductToCart(client, product))
                .orElse(false);
    }
}
