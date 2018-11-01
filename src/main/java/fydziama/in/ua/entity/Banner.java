package fydziama.in.ua.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;

// JPA
@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idBanner")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Banner implements Serializable{

    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_banner")
    private Long idBanner;

    @Enumerated(EnumType.STRING)
    private BannerType type;

    @Column(name = "visible")
    @Enumerated(EnumType.STRING)
    private Visible visibility;

    private String image;
}
