package haui.doan.stores.framework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.settings")
@Data
public class Settings {

    private ImageRoot imageRoot = new ImageRoot();

    @Data
    public static class ImageRoot {
        private String path;
    }
}
