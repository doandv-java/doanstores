package haui.doan.stores.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest extends BaseRequest {

    private Long id;

    @NotBlank
    private String name;

    private MultipartFile image;

    private Long imageId;

    private int status;

    private String nameOld;

    private String imageUrl;
}
