package haui.doan.stores.persistents;

import haui.doan.stores.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByNameEqualsAndStatus(String name, int status);

    Category findCategoryByIdAndStatus(Long id, int status);

    List<Category> findCategoriesByStatus(int status);
}
