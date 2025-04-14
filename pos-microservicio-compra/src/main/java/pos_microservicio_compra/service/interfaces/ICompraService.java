package pos_microservicio_compra.service.interfaces;

import pos_microservicio_compra.model.Compra;

import java.util.List;

public interface ICompraService {

    Compra registerCompra(Compra compra);
    Compra updateCompraById(Long id, Compra compra);
    List<Compra> getAllCompras();
    List<Compra> getAllComprasById(Long userid);
    Compra getCompraById(Long id);
    void deleteCompraById(Long id);
}
