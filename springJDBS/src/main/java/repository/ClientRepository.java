package repository;

import model.Client;

import java.util.Optional;

public interface ClientRepository {
    long save(Client client);

    Optional<Client> findById(long id);

    boolean deleteById(long id);

}