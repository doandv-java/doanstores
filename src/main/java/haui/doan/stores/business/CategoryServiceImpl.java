package haui.doan.stores.business;

import haui.doan.stores.domain.Category;
import haui.doan.stores.domain.Image;
import haui.doan.stores.domain.Product;
import haui.doan.stores.dto.request.CategoryRequest;
import haui.doan.stores.dto.request.ImageRequest;
import haui.doan.stores.framework.CommonConstant;
import haui.doan.stores.persistents.CategoryRepository;
import haui.doan.stores.persistents.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ImageService imageService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ImageService imageService, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @Override
    public void saveCategory(CategoryRequest request) {
        Category category = new Category();
        category.setId(request.getId());
        category.setName(request.getName());
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setId(request.getImageId());
        imageRequest.setImage(request.getImage());
        Image image = imageService.saveImage(imageRequest);
        category.setImageId(image.getId());
        category.setImage(image);
        category.setStatus(CommonConstant.STATUS.NO_DELETE);
        categoryRepository.save(category);
    }

    @Override
    public CategoryRequest findCategoryByIdAndStatus(Long id, int status) {
        Category category = categoryRepository.findCategoryByIdAndStatus(id, status);
        CategoryRequest request = new CategoryRequest();
        request.setId(category.getId());
        request.setName(category.getName());
        request.setNameOld(category.getName());
        request.setStatus(category.getStatus());
        request.setImageId(category.getImageId());
        request.setImageUrl(category.getImage().getUrl());
        return request;
    }

    @Override
    public List<Category> findCategoryByStatus(int status) {
        return categoryRepository.findCategoriesByStatus(status);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.getOne(id);
        //Chuyen ve dang default Category
        Category defaultCategory = getDefaultCategory();
        List<Product> products = productRepository.findProductsByCategoryIdIs(id);
        products.forEach(product -> {
            product.setCategory(defaultCategory);
            product.setCategoryId(defaultCategory.getId());
        });
        productRepository.saveAll(products);
        category.setStatus(CommonConstant.STATUS.DELETE);
        categoryRepository.save(category);
    }

    @Override
    public boolean existsCategory(String name, String nameOld) {
        if (!Strings.isEmpty(name)) {
            Category category = categoryRepository.findCategoryByNameEquals(name);
            if (StringUtils.isEmpty(nameOld)) {
                return category != null;
            } else {
                //no exist
                boolean check = name.equals(nameOld) || category == null;
                return !check;
            }
        }
        return true;
    }

    @Override
    public Category getDefaultCategory() {
        Category category = categoryRepository.findCategoryByNameEquals(CommonConstant.DEFAULT_CATEGORY.NAME);
        if (category == null) {
            Image image = imageService.getDefaultImage();
            category.setName(CommonConstant.DEFAULT_CATEGORY.NAME);
            category.setImage(image);
            category.setImageId(image.getId());
            category.setStatus(CommonConstant.STATUS.DELETE);
            return categoryRepository.save(category);
        }
        return category;
    }
}
