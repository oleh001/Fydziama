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
@EqualsAndHashCode(of = "idOrderDetail")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class OrderDetail implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_good")
    private Good good;

    private int quantity;

    @Override
    public String toString() {
        return good + " - " + quantity;
    }

    public OrderDetail() {
    }

    public OrderDetail(Order order, Good good, int quantity) {
        this.order = order;
        this.good = good;
        this.quantity = quantity;
    }
}
