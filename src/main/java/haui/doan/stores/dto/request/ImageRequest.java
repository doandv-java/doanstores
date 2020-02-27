package haui.doan.stores.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequest {

    private Long id;

    private MultipartFile image;

}
