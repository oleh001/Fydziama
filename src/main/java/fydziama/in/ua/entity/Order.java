package fydziama.in.ua.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

// JPA
@Entity
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idOrder")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Order implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_order")
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "id_zone")
    private Zone zone;

    @Column(name = "code_confirmed")
    private String codeConfirmed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(User user, OrderStatus status) {
        this.user = user;
        this.status = status;
        this.orderDate=new Date();
    }

    //    @Override
//    public String toString() {
//        return idOrder + " - " + status;
//    }
}
