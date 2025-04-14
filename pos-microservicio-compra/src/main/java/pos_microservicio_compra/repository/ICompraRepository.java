package pos_microservicio_compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pos_microservicio_compra.model.Compra;

import java.util.List;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findAllByUserId(Long userId);
}
