package haui.doan.stores.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class ProvideRequest extends BaseRequest {
    
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    private int status;
    private String nameOld;
    private MultipartFile image;
    private Long imageId;
    private String imageUrl;
}
