package service;

import lombok.AllArgsConstructor;
import model.Product;
import repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public long save(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {

        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {

        productRepository.update(product);
    }

    @Override
    public List<Product> findAll(String name) {

        return productRepository.findAll(name);
    }

    @Override
    public boolean deleteById(long id) {

        return productRepository.deleteById(id);
    }

}