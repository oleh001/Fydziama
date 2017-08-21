package fydziama.in.ua.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

// JPA
@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idVote")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Vote {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_vote")
    private Long idVote;

    private int value;

    @ManyToOne
    @JoinColumn(name = "id_good")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Override
    public String toString() {
        return user + " - " + good + " - " + value;
    }
}
