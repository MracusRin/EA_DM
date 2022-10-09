package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ea_dm.models.Product;
import ru.ea_dm.repositories.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> index() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
