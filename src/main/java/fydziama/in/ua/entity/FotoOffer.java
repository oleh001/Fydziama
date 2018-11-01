package fydziama.in.ua.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idOffer")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class FotoOffer {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_offer")
    private Long idOffer;

    private String name;
    private int height;
    private int width;
}
