package haui.doan.stores.business;

import haui.doan.stores.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findProductByStatus(int status);
}
