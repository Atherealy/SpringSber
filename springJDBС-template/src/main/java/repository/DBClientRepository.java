package repository;

import model.Cart;
import model.Client;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Primary
@Repository
@Transactional
@AllArgsConstructor
public class DBClientRepository implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public long save(Client client) {
        var insertCartDB = "INSERT INTO carts (promocode) VALUES (?)";
        var insertClientDB = "INSERT INTO clients (name, username, password, email, cart_id) VALUES (?,?,?,?,?)";
        KeyHolder cartKeyHolder = new GeneratedKeyHolder();
        KeyHolder clientKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator cartInsertCreator = connection -> {
            var cartStatement = connection.prepareStatement(insertCartDB, Statement.RETURN_GENERATED_KEYS);
            cartStatement.setString(1, "");
            return cartStatement;
        };

        jdbcTemplate.update(cartInsertCreator, cartKeyHolder);

        PreparedStatementCreator clientInsertCreator = connection -> {
            var clientStatement = connection.prepareStatement(insertClientDB, Statement.RETURN_GENERATED_KEYS);
            clientStatement.setString(1, client.getClientName());
            clientStatement.setString(2, client.getLogin());
            clientStatement.setString(3, client.getPassword());
            clientStatement.setString(4, client.getEmail());
            clientStatement.setLong(5, (long) Objects.requireNonNull(cartKeyHolder.getKeys()).get("id"));
            return clientStatement;
        };

        jdbcTemplate.update(clientInsertCreator, clientKeyHolder);

        return (long) Objects.requireNonNull(clientKeyHolder.getKeys()).get("id");
    }

    @Override
    public Optional<Client> findById(long id) {
        var insertDB = "INSERT INTO carts (promocode) VALUES (?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(insertDB);
            prepareStatement.setLong(1, id);
            return prepareStatement;
        };

        return jdbcTemplate
                .query(preparedStatementCreator, getClientMapper())
                .stream()
                .findFirst();
    }

    @Override
    public boolean deleteById(long id) {
        var deleteDB = "DELETE FROM clients WHERE id = ?;";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteDB);
            prepareStatement.setLong(1, id);
            return prepareStatement;
        };

        return jdbcTemplate.update(preparedStatementCreator) > 0;
    }

    private static RowMapper<Client> getClientMapper() {
        return (resultSet, rowNum) -> {
            Client client = new Client();
            Cart cart = new Cart();

            cart.setId(resultSet.getLong("cart_id"));
            cart.setPromocode(resultSet.getString("promocode"));
            client.setId(resultSet.getLong("id"));
            client.setClientName(resultSet.getString("name"));
            client.setLogin(resultSet.getString("username"));
            client.setPassword(resultSet.getString("password"));
            client.setEmail(resultSet.getString("email"));
            client.setCart(cart);

            return client;
        };
    }

}