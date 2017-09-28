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
@EqualsAndHashCode(of = "idFaq")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Faq {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_faq")
    private Long idFaq;

    private String name;
    private String text;

    private int position;

    @Column(name = "step")
    @Enumerated(EnumType.STRING)
    private GoodVisible step;

    @Override
    public String toString() {
        return name;
    }
}
