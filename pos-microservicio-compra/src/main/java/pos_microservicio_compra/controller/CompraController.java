package pos_microservicio_compra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos_microservicio_compra.model.Compra;
import pos_microservicio_compra.service.interfaces.ICompraService;

import static pos_microservicio_compra.util.Constants.*;

@RestController
@RequestMapping(API)
public class CompraController {

    @Autowired
    private ICompraService compraService;

    @PostMapping(COMPRA)
    public ResponseEntity<?> save(@RequestBody Compra compra){
        return ResponseEntity.ok(compraService.registerCompra(compra));
    }

    @GetMapping(GET_COMPRA_BY_ID)
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(compraService.getCompraById(id));
    }

    @GetMapping(GET_All_COMPRA_BY_USER_ID)
    public ResponseEntity<?> getAllComprasByUserId(@PathVariable Long id){
        return ResponseEntity.ok(compraService.getAllComprasById(id));
    }

    @GetMapping(GET_ALL_COMPRAS)
    public ResponseEntity<?> findAllCompras(){
        return ResponseEntity.ok(compraService.getAllCompras());
    }
}
