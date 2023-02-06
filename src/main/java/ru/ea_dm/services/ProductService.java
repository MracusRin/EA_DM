package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Product;
import ru.ea_dm.repositories.ProductRepository;
import ru.ea_dm.util.ImageFileSystemUtil;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageFileSystemUtil imageFileSystemUtil;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public void save(Product product) {
        log.info("Product {} SAVE", product.getTitle());
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        product.getImages().forEach(imageFileSystemUtil::deleteFromFs);
        productRepository.deleteById(id);
        log.info("Product {} DELETED", product.getTitle());
    }

}