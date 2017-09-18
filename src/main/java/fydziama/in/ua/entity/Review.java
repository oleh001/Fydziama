package fydziama.in.ua.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// JPA
@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idReview")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_review")
    private Long idReview;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String review;
    private String name;

    @Column(name = "date")
    private Date reviewDate;

    @ManyToOne
    @JoinColumn(name = "id_good")
    private Good good;

    private int rating;

    public Review() {
    }

    public Review(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public Review(int rating) {
        this.rating = rating;
    }

    public Review(User user, String name, int rating) {
        this.user = user;
        this.name = name;
        this.rating = rating;
    }

    public Review(User user, String name, Good good, int rating) {
        this.user = user;
        this.name = name;
        this.good = good;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name;
    }
}
