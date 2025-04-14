package pos_microservicio_inmueble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pos_microservicio_inmueble.model.Inmueble;

@Repository
public interface IInmuebleRepository extends JpaRepository<Inmueble, Long> {
}
