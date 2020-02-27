package haui.doan.stores;

import haui.doan.stores.framework.Settings;
import haui.doan.stores.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StoresApplication implements CommandLineRunner {

    @Autowired
    private Settings settings;

    public static void main(String[] args) {
        SpringApplication.run(StoresApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        FileUtils.createDirectoryIfNotExist(settings.getImageRoot().getPath());
    }
}
