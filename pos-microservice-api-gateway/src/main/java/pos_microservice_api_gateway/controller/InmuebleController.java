package pos_microservice_api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos_microservice_api_gateway.request.IInmuebleService;

import static pos_microservice_api_gateway.util.Constants.*;


@RestController
@RequestMapping(GATEWAY)
public class InmuebleController {

    @Autowired
    private IInmuebleService inmuebleService;

    @PostMapping(INMUEBLE)
    public ResponseEntity<?> save(@RequestBody Object inmueble){
        return ResponseEntity.ok(inmuebleService.saveInmueble(inmueble));
    }

    @GetMapping(INMUEBLES)
    public  ResponseEntity<?> getAllInmuebles(){
        return ResponseEntity.ok(inmuebleService.getAllInmuebles());
    }

    @DeleteMapping(GET_INMUEBLE_BY_ID)
    public ResponseEntity<?> deleteInmuebleById(@PathVariable Long inmuebleId){
        inmuebleService.deleteInmuebleById(inmuebleId);
        return ResponseEntity.noContent().build();
    }
}
