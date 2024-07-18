package repository;

import model.Client;
import model.Product;

public interface CartRepository {

    boolean addProductToCart(Client client, Product product);

    boolean deleteFromCart(Client client, long productId);

}
