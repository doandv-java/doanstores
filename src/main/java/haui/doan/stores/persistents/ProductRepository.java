package haui.doan.stores.persistents;

import haui.doan.stores.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryIdIs(Long id);

    List<Product> findProductsByProvideId(Long id);

    List<Product> findProductsByStatus(int status);
}
