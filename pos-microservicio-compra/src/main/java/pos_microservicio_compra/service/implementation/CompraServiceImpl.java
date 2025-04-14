package pos_microservicio_compra.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos_microservicio_compra.model.Compra;
import pos_microservicio_compra.repository.ICompraRepository;
import pos_microservicio_compra.service.interfaces.ICompraService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CompraServiceImpl implements ICompraService {

    @Autowired
    private ICompraRepository compraRepository;

    @Override
    public Compra registerCompra(Compra compra) {
        compra.setFecha_compra(new Timestamp(System.currentTimeMillis()));
        return compraRepository.save(compra);
    }

    @Override
    public Compra updateCompraById(Long id, Compra compra) {
       /* Compra save = compraRepository.findById(id).get();
        save.setUserId(compra.getUserId());
        save.setInmuebleId(compra.getInmuebleId());
        save.setTitulo(compra.getTitulo());
        save.setPrecio(compra.getPrecio());
        save.setFecha_compra(compra.getFecha_compra());
        return compraRepository.save(save);*/
        return null;
    }

    @Override
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @Override
    public List<Compra> getAllComprasById(Long userId) {
        return compraRepository.findAllByUserId(userId);
    }

    @Override
    public Compra getCompraById(Long id) {
        return compraRepository.findById(id).get();
    }

    @Override
    public void deleteCompraById(Long id) {
        compraRepository.deleteById(id);
    }
}
