package pos_microservicio_compra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "inmueble_id", nullable = false)
    private Long inmuebleId;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_compra", nullable = false)
    private Timestamp fecha_compra;
}
