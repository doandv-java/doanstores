package haui.doan.stores.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@Slf4j
public class FileUtils {
    public static String store(MultipartFile file, Path root) {
        Long timeNow = Instant.now().getEpochSecond();
        try {
            String newFileName = timeNow + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(),
                    root.resolve(newFileName));
            return newFileName;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public static void deletePath(String path) {
        if (StringUtils.isEmpty(path)) {
            log.error("{} is not exist or null", path);
            return;
        }
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            log.error("Delete file image :{} fail", path);
        }
    }

    public static boolean checkFileNullOrEmpty(MultipartFile file) {
        return file == null || file.isEmpty() || file.getSize() == 0;
    }
    public static boolean createDirectoryIfNotExist(String directory) {
        try {
            File theDir = new File(directory);
            if (theDir.exists()) {
                log.info("The {} directory had existed", theDir);
                return true;
            }
            theDir.mkdirs();
            log.info("Created {} directory", theDir);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
            return false;
        }
        return true;
    }

}
