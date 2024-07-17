package repository;

import model.Client;
import model.Product;

public interface CartRepository {

    boolean addProductToCart(Client client, Product product);

    boolean deleteProductFromCart(Client client, long productId);

}
