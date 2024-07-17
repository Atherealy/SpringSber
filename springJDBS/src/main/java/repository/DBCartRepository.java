package repository;

import model.Product;
import model.Client;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.ClientService;
import service.ProductService;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Primary
@Repository
@AllArgsConstructor
public class DBCartRepository implements CartRepository {

    public static final String JDBC = "jdbc:h2:~/testDB;USER=test;PASSWORD=test";

    private final ProductService productService;

    private final ClientService clientService;

    @Override
    @Transactional
    public boolean addToCartById(long userId, long productId) {
        return clientService
                .findById(userId)
                .map(client -> findProductAndAddToCart(client, productId))
                .orElse(false);
    }

    @Override
    @Transactional
    public boolean deleteFromCartById(long userId, long productId) {
        return clientService
                .findById(userId)
                .map(client -> deleteFromCart(client, productId))
                .orElse(false);
    }

    private boolean findProductAndAddToCart(Client client, long productId) {
        return productService
                .findById(productId)
                .map(product -> addProductToCart(client, product))
                .orElse(false);
    }

    private boolean addProductToCart(Client client, Product product) {
        var insertDB = "INSERT INTO products_carts (id_product, amount, id_cart) VALUES (?,?,?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertDB, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setLong(1, product.getId());
            prepareStatement.setInt(2, 1);
            prepareStatement.setLong(3, client.getCart().getId());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (!rs.next()) {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }

            return rs.getLong(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean deleteFromCart(Client client, long productId) {
        var deleteDB = "DELETE FROM products_carts WHERE id_cart = ? AND id_product = ?;";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(deleteDB)) {
            prepareStatement.setLong(1, client.getCart().getId());
            prepareStatement.setLong(2, productId);

            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}