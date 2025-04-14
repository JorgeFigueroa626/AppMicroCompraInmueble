package pos_microservicio_inmueble.service.interfaces;

import pos_microservicio_inmueble.model.Inmueble;

import java.util.List;

public interface IInmuebleService {

    Inmueble registerInmueble(Inmueble inmueble);
    Inmueble updateInmuebleById(Long id, Inmueble inmueble);
    Inmueble getInmuebleById(Long id);
    List<Inmueble> getAllInmuebles();
    void deleteInmuebleById(Long id);
}
