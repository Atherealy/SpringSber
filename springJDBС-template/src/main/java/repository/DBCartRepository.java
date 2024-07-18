package repository;

import model.Client;
import model.Product;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Statement;

@Primary
@Repository
@Transactional
@AllArgsConstructor
public class DBCartRepository implements CartRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean addProductToCart(Client client, Product product) {
        var insertDB = "INSERT INTO products_carts (id_product, amount, id_cart) VALUES (?,?,?);";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(insertDB, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setLong(1, product.getId());
            prepareStatement.setInt(2, 1);
            prepareStatement.setLong(3, client.getCart().getId());
            return prepareStatement;
        };

        return jdbcTemplate.update(preparedStatementCreator) > 0;
    }

    @Override
    public boolean deleteProductFromCart(Client client, long productId) {
        var deleteDB = "DELETE FROM products_carts WHERE id_cart = ? AND id_product = ?";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteDB);
            prepareStatement.setLong(1, client.getCart().getId());
            prepareStatement.setLong(2, productId);
            return prepareStatement;
        };

        return jdbcTemplate.update(preparedStatementCreator) > 0;
    }

}
