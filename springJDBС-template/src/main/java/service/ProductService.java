package service;

import model.Product;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public interface ProductService {
    long save(Product product);

    Optional<Product> findById(long id);

    List<Product> findAll(String name);

    void update(Product product);

    boolean deleteById(long id);

}