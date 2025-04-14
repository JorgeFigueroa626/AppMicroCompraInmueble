package pos_microservice_api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pos_microservice_api_gateway.request.ICompraService;
import pos_microservice_api_gateway.security.UserPrincipal;
import static pos_microservice_api_gateway.util.Constants.*;


@RestController
@RequestMapping(GATEWAY)
public class CompraController {

    @Autowired
    private ICompraService compraService;

    @PostMapping(COMPRA)
    ResponseEntity<?> save(@RequestBody Object compra){
        return ResponseEntity.ok(compraService.saveCompra(compra));
    }

    /*@GetMapping(GET_ALL_COMPRA)
    ResponseEntity<?> getAllCompras(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(compraService.getAllComprasById(userPrincipal.getId()));
    }*/

    @GetMapping(GET_COMPRA_BY_ID)
    public  ResponseEntity<?> getCompraById(@PathVariable Long compraId){
        return ResponseEntity.ok(compraService.getCompraById(compraId));
    }

    @GetMapping(FIND_ALL_COMPRAS)
    public  ResponseEntity<?> FindAllCompras(){
        return ResponseEntity.ok(compraService.getAllCompras());
    }

}
