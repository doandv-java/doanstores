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
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "provide_id", insertable = false, updatable = false)
    private Long provideId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image_id", insertable = false, updatable = false)
    private Long imageId;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    private Image image;

    @Column(name = "cost")
    private double cost;

    @Column(name = "ram")
    private int ram;

    @Column(name = "cpu")
    private String cpu;
    @Column(name = "storage")
    private String storage;

    @Column(name = "power")
    private double power;

    @Column(name = "os")
    private String os;

    @Column(name = "weight")
    private int weight;

    @Column(name = "graphic")
    private String graphic;

    @Column(name = "screen")
    private double screen;

    @Column(name = "makeTime")
    private String makeTime;

    @Column(name = "status")
    private int status;
}
