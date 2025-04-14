package pos_microservice_api_gateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import static pos_microservice_api_gateway.util.Constants.*;
import java.util.List;

@FeignClient(
        value = "pos-compra",
        //url = "${compra.service.base.url}",
        configuration = FeignConfiguration.class
)
public interface ICompraService {

    @PostMapping("/api/compra")
    Object saveCompra(@RequestBody Object compra);

    /*@GetMapping("/api/compra/user/{id}")
    List<Object> getAllComprasById(@PathVariable Long id);*/

    @GetMapping("/api/compra/{id}")
    Object getCompraById(@PathVariable Long id);

    @GetMapping("/api/compra")
    List<Object> getAllCompras();
}
