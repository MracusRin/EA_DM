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
import ru.ea_dm.util.ImageFileSystemUtil;

import java.io.IOException;
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
    private final ImageFileSystemUtil imageFileSystemUtil;

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
                imageFileSystemUtil.saveToFs(file, savePath);
                saveProduct.getImages().add(image);
                imageRepository.save(image);
                log.info("Image {} SAVE to Product: {}", product.getTitle(), fileName);
            }
        }
    }

    @Transactional
    public void delete(Image image) {
        Product product = productRepository.findById(image.getProduct().getProductId()).get();
        log.info("Image {} DELETED from Product: {}", image.getName(), image.getProduct());
        product.getImages().remove(image);
        imageRepository.delete(image);
        imageFileSystemUtil.deleteFromFs(image);
    }

}

