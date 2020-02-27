package haui.doan.stores.controllers.web;

import haui.doan.stores.framework.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //constant

    //Inject
    @Autowired
    private Settings settings;


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
        mav.setViewName("admin/product");
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
        mav.setViewName("admin/category");
        return mav;
    }

    @GetMapping("/provide")
    public ModelAndView viewProvide() {
        ModelAndView mav = new ModelAndView();
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
