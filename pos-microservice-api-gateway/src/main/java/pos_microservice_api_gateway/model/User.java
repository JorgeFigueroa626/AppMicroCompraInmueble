package pos_microservice_api_gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;


    @Transient
    private String toke;
}
