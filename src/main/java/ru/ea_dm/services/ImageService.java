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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Value("${upload.image.path}")
    private String uploadPath;
    private final String imageDir = "products";

    @Transactional
    public void save(List<MultipartFile> files, Product product) throws IOException {
        Product saveProduct = productRepository.findByTitle(product.getTitle()).get();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = ("img_" + product.getTitle() + "_" + UUID.randomUUID())
                        .toLowerCase().replaceAll(" ", "_");
                Path savePath = Paths.get(uploadPath + "/" + imageDir + "/" + fileName);

                Image image = Image.builder()
                        .name(fileName)
                        .downloadLink(savePath.toString())
                        .product(product)
                        .build();
                saveToFs(file, savePath);
                saveProduct.getImages().add(image);
                imageRepository.save(image);
                log.info("Image {} SAVE to Product: {}", product.getTitle(), fileName);
            }
        }
    }

    @Transactional
    public void delete(Image image) {
        Product product = productRepository.findById(image.getProduct().getProductId()).get();
        product.getImages().remove(image);
        imageRepository.delete(image);
        deleteFromFs(image);
        log.info("Image {} DELETED fro Product: {}", image.getName(), image.getProduct());
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
            log.error("Image {} not found. Nothing to delete", image.getName());
        }
    }
}

