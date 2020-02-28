package haui.doan.stores.persistents;

import haui.doan.stores.domain.Provide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvideRepository extends JpaRepository<Provide, Long> {

    Provide findProvideByIdAndStatus(long id, int status);

    Provide findProvideByNameEqualsAndStatus(String name, int status);

    List<Provide> findProvidesByStatus(int status);

}
