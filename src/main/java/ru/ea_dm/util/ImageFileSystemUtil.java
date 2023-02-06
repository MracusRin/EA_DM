package ru.ea_dm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.ea_dm.models.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class ImageFileSystemUtil {

    public void saveToFs(MultipartFile file, Path path) throws IOException {
        File saveDir = path.getParent().toFile();
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        file.transferTo(path);
    }

    public void deleteFromFs(Image image) {
        Path path = Paths.get(image.getDownloadLink());
        try {
            log.info("Image DELETED: {}", image.getName());
            Files.delete(path);
        } catch (IOException e) {
            log.error("Image {} not found. Nothing to delete", image.getName());
        }
    }
}
