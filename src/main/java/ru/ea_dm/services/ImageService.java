package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ea_dm.models.Image;
import ru.ea_dm.models.Product;
import ru.ea_dm.repositories.ImageRepository;
import ru.ea_dm.repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Value("${upload.productImage.path}")
    private String uploadPath;
    private final String imageDir = "product";

    @Transactional
    public void save(MultipartFile file, Product product) throws IOException {
        Product saveProduct = productRepository.findByTitle(product.getTitle()).get();

        String fileName = "img_" + imageDir + "_" +
                file.getOriginalFilename().toLowerCase().replaceAll(" ", "-");
        Path savePath = Paths.get(uploadPath + "/" + imageDir + "/" + fileName);

        Image image = Image.builder()
                .title(fileName)
                .downloadLink(savePath.toString())
                .product(product)
                .build();
        saveToFs(file, savePath);
        saveProduct.getImages().add(image);
        imageRepository.save(image);
        log.info("Image {} SAVE to Product: {}", product.getTitle(), fileName);
    }

    @Transactional
    public void delete(Image image) {
        Product product = productRepository.findById(image.getProduct().getProductId()).get();
        product.getImages().remove(image);
        imageRepository.delete(image);
        deleteFromFs(image);
        log.info("Image {} DELETED fro Product: {}", image.getTitle(), image.getProduct());
    }

    private void saveToFs(MultipartFile file, Path path) throws IOException {
        File saveDir = new File(uploadPath + "/" + imageDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        file.transferTo(path);
    }

    private void deleteFromFs(Image image) {
        Path path = Paths.get(image.getDownloadLink());
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

