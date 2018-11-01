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
@EqualsAndHashCode(of = "idAllData")
@Getter
@Setter

@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class AllData  implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_all_data")
    private Long idAllData;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "url_on_social_network")
    private String urlOnSocialNetwork;
}
