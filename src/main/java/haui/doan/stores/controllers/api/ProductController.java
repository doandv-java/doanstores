package haui.doan.stores.controllers.api;

import haui.doan.stores.business.ProductService;
import haui.doan.stores.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/admin/product")
    public List<Product> getListProductByStatus(@RequestParam("status") int status) {
        return productService.findProductByStatus(status);
    }
}
