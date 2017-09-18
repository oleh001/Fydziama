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
@EqualsAndHashCode(of = "idMessage")
@Getter
@Setter

// аннотации Hibernate
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_message")
    private Long idMessage;

    @JoinColumn(name = "user_name")
    private String userName;

    @JoinColumn(name = "user_email")
    private String userEmail;

    @JoinColumn(name = "user_subject")
    private String userSubject;

    @JoinColumn(name = "user_message")
    private String userMessage;

    @Override
    public String toString() {
        return userName + " - " + userMessage;
    }
}
