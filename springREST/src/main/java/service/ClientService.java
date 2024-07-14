package service;

import model.Client;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClientService {

    long save(Client client);

    Optional<Client> findById(long id);

    boolean deleteById(long id);
}
