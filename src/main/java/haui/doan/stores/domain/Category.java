package haui.doan.stores.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "image_id", insertable = false, updatable = false)
    private Long imageId;
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    private Image image;

    @Column(name = "status")
    private int status;

}
