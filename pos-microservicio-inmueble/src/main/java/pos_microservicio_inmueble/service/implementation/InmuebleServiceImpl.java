package pos_microservicio_inmueble.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos_microservicio_inmueble.model.Inmueble;
import pos_microservicio_inmueble.repository.IInmuebleRepository;
import pos_microservicio_inmueble.service.interfaces.IInmuebleService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InmuebleServiceImpl implements IInmuebleService {

    @Autowired
    private IInmuebleRepository inmuebleRepository;

    @Override
    public Inmueble registerInmueble(Inmueble inmueble) {
        inmueble.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public Inmueble updateInmuebleById(Long id, Inmueble inmueble) {
        Inmueble update = inmuebleRepository.findById(id).get();
        update.setName(inmueble.getName());
        update.setAddress(inmueble.getAddress());
        update.setPrice(inmueble.getPrice());
        update.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return inmuebleRepository.save(update);
    }

    @Override
    public Inmueble getInmuebleById(Long id) {
        return inmuebleRepository.findById(id).get();
    }

    @Override
    public List<Inmueble> getAllInmuebles() {
        System.out.print(inmuebleRepository.findAll());
        return inmuebleRepository.findAll();
    }

    @Override
    public void deleteInmuebleById(Long id) {
        inmuebleRepository.deleteById(id);
    }
}
