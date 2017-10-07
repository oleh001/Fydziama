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
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "fydziama_db_new")

// Lombok
@EqualsAndHashCode(of = "idUser")
@Getter
@Setter // генерация гетеров-сетеров для всех полей класса

// аннотации Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate  // проверить объект перед обновлением, нужно ли его обновлять
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    @Id
    @Column(name = "id_user")
    private Long idUser;

    private String name;
    private String surname;
    private String email;
    private String phone;

    @Column(name = "phone_add")
    private String phoneAdd;

    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_hous")
    private String addressHous;
    @Column(name = "address_street")
    private String addressStreet;

    private String specification;

    private String login;
    private String password;

    @ManyToOne // ссылка foreign key идет из таблицы Book в таблицу Genre
    // по-умолчанию Hibernate пытается связать по полю genre_id (как в нашей таблице), если имя столбца другое, нужно задавать атрибут name у @JoinColumn
    @JoinColumn(name = "id_role") // для получения готового объекта Role по id
    private Role role;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

    @Override
    public String toString() {
        return name;
    }
}
