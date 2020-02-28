package haui.doan.stores.business;

import haui.doan.stores.domain.Product;
import haui.doan.stores.persistents.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ImageService imageService;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ImageService imageService, ProductRepository productRepository) {
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProductByStatus(int status) {
        return productRepository.findProductsByStatus(status);
    }
}
