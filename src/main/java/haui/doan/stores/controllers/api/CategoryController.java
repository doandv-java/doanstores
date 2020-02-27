package haui.doan.stores.controllers.api;

import haui.doan.stores.business.CategoryService;
import haui.doan.stores.domain.Category;
import haui.doan.stores.dto.request.CategoryRequest;
import haui.doan.stores.dto.response.ErrorResponse;
import haui.doan.stores.framework.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/admin/category")
    public Map<String, Object> saveCategory(@Valid CategoryRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = new ArrayList<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> {
                errors.add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
            });
        }
        if (categoryService.existsCategory(request.getName(), request.getNameOld())) {
            errors.add(new ErrorResponse("name", "Name da ton tai"));
        }
        if (errors.isEmpty()) {
            categoryService.saveCategory(request);
            map.put("status", 200);
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @DeleteMapping("/admin/category/{id}")
    public Map<String, Object> saveCategory(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        CategoryRequest flag =categoryService.findCategoryByIdAndStatus(id, CommonConstant.STATUS.NO_DELETE);
        if(flag==null){
            map.put("status",101);
        }else{
            map.put("status",200);
            categoryService.delete(id);
        }
        return map;
    }

}
