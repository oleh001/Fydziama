package fydziama.in.ua.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

// JPA
@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idBrand")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Brand {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_brand")
    private Long idBrand;

    private String brand;

    @Column(name = "id_parent")
    private Long idParent;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "brand")
    private List<Good> goods;


    @Override
    public String toString() {
        return brand;
    }
}
