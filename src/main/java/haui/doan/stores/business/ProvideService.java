package haui.doan.stores.business;

import haui.doan.stores.domain.Provide;
import haui.doan.stores.dto.request.ProvideRequest;

import java.util.List;

public interface ProvideService {

    void saveProvide(ProvideRequest request);

    void deleteProvide(Long id);

    ProvideRequest findProvideByIdAndStatus(Long id, int status);

    List<Provide> findProvidesByStatus(int status);

    boolean existProvide(String name, String nameOld);
}
