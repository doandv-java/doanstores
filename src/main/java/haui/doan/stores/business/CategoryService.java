package haui.doan.stores.business;

import haui.doan.stores.domain.Category;
import haui.doan.stores.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    //check Category
    void saveCategory(CategoryRequest request);

    CategoryRequest findCategoryByIdAndStatus(Long id, int status);

    List<Category> findCategoryByStatus(int status);

    void delete(Long id);

    boolean existsCategory(String name, String nameOld);

    Category getDefaultCategory();
}
