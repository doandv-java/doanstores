package haui.doan.stores.business;

import haui.doan.stores.domain.Image;
import haui.doan.stores.domain.Product;
import haui.doan.stores.domain.Provide;
import haui.doan.stores.dto.request.ImageRequest;
import haui.doan.stores.dto.request.ProvideRequest;
import haui.doan.stores.framework.CommonConstant;
import haui.doan.stores.persistents.ProductRepository;
import haui.doan.stores.persistents.ProvideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class ProvideServiceImpl implements ProvideService {

    private final ProvideRepository provideRepository;

    private final ProductRepository productRepository;

    private final ImageService imageService;

    @Autowired
    public ProvideServiceImpl(ProvideRepository provideRepository, ImageService imageService, ProductRepository productRepository) {
        this.provideRepository = provideRepository;
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @Override
    public void saveProvide(ProvideRequest request) {
        Provide provide = new Provide();
        provide.setId(request.getId());
        provide.setName(request.getName());
        provide.setEmail(request.getEmail());
        provide.setPhone(request.getPhone());
        provide.setAddress(request.getAddress());
        provide.setStatus(request.getStatus());

        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setId(request.getImageId());
        imageRequest.setImage(request.getImage());
        Image image = imageService.saveImage(imageRequest);
        provide.setImage(image);
        provide.setImageId(image.getId());
        provideRepository.save(provide);
    }

    @Override
    public ProvideRequest findProvideByIdAndStatus(Long id, int status) {
        ProvideRequest request = new ProvideRequest();
        Provide provide = provideRepository.findProvideByIdAndStatus(id, status);
        request.setId(provide.getId());
        request.setEmail(provide.getEmail());
        request.setName(provide.getName());
        request.setNameOld(provide.getName());
        request.setImageUrl(provide.getImage().getUrl());
        request.setPhone(provide.getPhone());
        request.setAddress(provide.getAddress());
        request.setImageId(provide.getImageId());
        request.setStatus(provide.getStatus());
        return request;
    }

    @Override
    public void deleteProvide(Long id) {
        Provide provide = provideRepository.findProvideByIdAndStatus(id, CommonConstant.STATUS.NO_DELETE);
        List<Product> products = productRepository.findProductsByProvideId(id);
        products.forEach(product -> product.setStatus(CommonConstant.STATUS.DELETE));
        productRepository.saveAll(products);
        provide.setStatus(CommonConstant.STATUS.DELETE);
        provideRepository.save(provide);
    }

    @Override
    public List<Provide> findProvidesByStatus(int status) {
        return provideRepository.findProvidesByStatus(status);
    }

    @Override
    public boolean existProvide(String name, String nameOld) {
        if (!StringUtils.isEmpty(name)) {
            Provide provide = provideRepository.findProvideByNameEqualsAndStatus(name, CommonConstant.STATUS.NO_DELETE);
            if (StringUtils.isEmpty(nameOld)) {
                return provide != null;
            } else {
                boolean check = name.equals(nameOld) || provide == null;
                return !check;
            }
        }
        return false;
    }
}
