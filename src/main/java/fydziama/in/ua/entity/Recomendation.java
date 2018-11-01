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
@EqualsAndHashCode(of = "idRecomendation")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Recomendation  implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_recomendation")
    private Long idRecomendation;

    @OneToOne
    @JoinColumn(name = "id_good")
    private Good good;

    private String name1;
    private String name2;
    private String special;

    @Column(name = "visible")
    @Enumerated(EnumType.STRING)
    private Visible visibility;
}
