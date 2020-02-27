package haui.doan.stores.framework;

import lombok.Data;

@Data
public class CommonConstant {

    public interface DEFAULT_IMAGE {
        String NAME = "default.png";
        String URL = "/upload/default.png";
    }

    public interface STATUS {
        int NO_DELETE = 0;
        int DELETE = 1;
    }

    public interface DEFAULT_CATEGORY {
        String NAME = "Lap top";
    }
}
