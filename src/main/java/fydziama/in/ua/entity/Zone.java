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
@EqualsAndHashCode(of = "idZone")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Zone {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_zone")
    private Long idZone;

    private String name;

    private String text;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zone")
    private List<Order> orders;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zone)) return false;

        Zone zone = (Zone) o;

        return idZone.equals(zone.idZone);
    }

    @Override
    public int hashCode() {
        return idZone.hashCode();
    }
}
