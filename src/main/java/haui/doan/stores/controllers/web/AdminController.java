package haui.doan.stores.controllers.web;

import haui.doan.stores.business.CategoryService;
import haui.doan.stores.business.ProductService;
import haui.doan.stores.business.ProvideService;
import haui.doan.stores.domain.Category;
import haui.doan.stores.domain.Product;
import haui.doan.stores.domain.Provide;
import haui.doan.stores.framework.CommonConstant;
import haui.doan.stores.framework.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //constant

    private final Settings settings;

    private final CategoryService categoryService;

    private final ProvideService provideService;

    private final ProductService productService;

    @Autowired
    public AdminController(Settings settings, CategoryService categoryService, ProvideService provideService, ProductService productService) {
        this.settings = settings;
        this.categoryService = categoryService;
        this.provideService = provideService;
        this.productService = productService;
    }


    @GetMapping("/home")
    public ModelAndView viewHome() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/index");
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView viewProfile() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/profile");
        return mav;
    }

    @GetMapping("/product")
    public ModelAndView viewProduct() {
        ModelAndView mav = new ModelAndView();
        List<Product> products = productService.findProductByStatus(CommonConstant.STATUS.NO_DELETE);
        mav.addObject("products", products);
        mav.setViewName("admin/product/product");
        return mav;
    }

    @GetMapping("/product/create")
    public ModelAndView viewProductForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/product/updateProduct");
        return mav;
    }

    @GetMapping("/product/edit")
    public ModelAndView editProduct() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/product/updateProduct");
        return mav;
    }

    @GetMapping("/employee")
    public ModelAndView viewEmployee() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/employee");
        return mav;
    }

    @GetMapping("/customer")
    public ModelAndView viewCustomer() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/customer");
        return mav;
    }

    @GetMapping("/category")
    public ModelAndView viewCategory() {
        ModelAndView mav = new ModelAndView();
        List<Category> categoryList = categoryService.findCategoryByStatus(CommonConstant.STATUS.NO_DELETE);
        mav.addObject("categories", categoryList);
        mav.setViewName("admin/category");
        return mav;
    }

    @GetMapping("/provide")
    public ModelAndView viewProvide() {
        ModelAndView mav = new ModelAndView();
        List<Provide> provideList = provideService.findProvidesByStatus(CommonConstant.STATUS.NO_DELETE);
        mav.addObject("provides", provideList);
        mav.setViewName("admin/provide");
        return mav;
    }

    @GetMapping("/warehouse")
    public ModelAndView viewWarehouse() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/warehouse");
        return mav;
    }

    @GetMapping("/comment")
    public ModelAndView viewComment() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/comment");
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView viewOrder() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/order");
        return mav;
    }

    @GetMapping("/advertise")
    public ModelAndView viewAdvertise() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/advertise");
        return mav;
    }
}
