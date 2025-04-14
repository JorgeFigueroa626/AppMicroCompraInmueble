package pos_microservicio_inmueble.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inmueble")
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "address", length = 150, nullable = false)
    private String address;

    @Column(name = "image", length = 1000, nullable = true)
    private String image;

    @Column(name = "price", nullable = false)
    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
}
