package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Image;
import ru.ea_dm.models.Product;
import ru.ea_dm.repositories.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void save(Product product) {
        log.info("Product {} SAVE", product.getTitle());
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id).get();
        product.getImages().forEach(this::deleteProductImages);
        productRepository.deleteById(id);
        log.info("Product {} DELETED", product.getTitle());
    }

    private void deleteProductImages(Image image) {
        // TODO: убрать дублирование кода при удалении Image
        Path path = Paths.get(image.getDownloadLink());
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}