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
@EqualsAndHashCode(of = "idGood")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Good {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_good")
    private Long idGood;

    private String name;
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    private int gram;
    private String anons;
    private String content;

    @Column(name = "visible")
    @Enumerated(EnumType.STRING)
    private Visible visibility;

    @Enumerated(EnumType.STRING)
    private Visible hits;

    @Column(name = "new")
    @Enumerated(EnumType.STRING)
    private Visible news;

    @Enumerated(EnumType.STRING)
    private Visible specials;

    @Enumerated(EnumType.STRING)
    private Visible sale;

    private float price;

    @Column(name = "date")
    private Date goodDate;

    @Column(name = "img_slide")
    private String imgSlide;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name = "total_vote_count")
    private long totalVoteCount;

    @Column(name = "avg_rating")
    private int avgRating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "good")
    private List<OrderDetail> orderDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "good")
    private List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "good")
    private List<Review> review;

    @Override
    public String toString() {
        return name;
    }
}
