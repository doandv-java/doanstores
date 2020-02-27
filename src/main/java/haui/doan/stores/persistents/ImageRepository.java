package haui.doan.stores.persistents;

import haui.doan.stores.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageByNameIs(String name);

}
