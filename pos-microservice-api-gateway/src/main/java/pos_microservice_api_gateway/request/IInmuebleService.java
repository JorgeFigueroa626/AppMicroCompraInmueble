package pos_microservice_api_gateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "pos-inmueble",
        //url = "${inmueble.service.base.url}",
        configuration = FeignConfiguration.class
)
public interface IInmuebleService {

    @PostMapping("/api/inmueble")
    Object saveInmueble(@RequestBody Object requestBody);

    @DeleteMapping("/api/inmueble/{inmuebleId}")
    void deleteInmuebleById(@PathVariable Long inmuebleId);

    @GetMapping("/api/inmueble")
    List<Object> getAllInmuebles();
}
