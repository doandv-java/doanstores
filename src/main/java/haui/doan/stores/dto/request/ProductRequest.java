package haui.doan.stores.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest extends BaseRequest {
    private Long id;
    private String code;
    private String name;
    private Long categoryId;
    private Long provideId;
    private int quantity;
    private int imageId;
    private MultipartFile image;
    private double cost;
    private int ram;
    private String cpu;
    private String storage;
    private double power;
    private String os;
    private int weight;
    private String graphic;
    private double screen;
    private String makeTime;
    private int status;
    private String codeOld;
}
