package repository;

import model.Client;
import model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LocalCartRepository implements CartRepository {

    @Override
    public boolean addProductToCart(Client client, Product product) {
        return client.getCart().getProducts().add(product);
    }

    @Override
    public boolean deleteFromCart(Client client, long productId) {
        return client.getCart().getProducts().removeIf(product -> product.getId() == productId);
    }
}

