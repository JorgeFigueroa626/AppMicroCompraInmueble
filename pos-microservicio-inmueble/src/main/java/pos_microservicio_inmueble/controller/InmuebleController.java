package pos_microservicio_inmueble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos_microservicio_inmueble.model.Inmueble;
import pos_microservicio_inmueble.service.interfaces.IInmuebleService;

import static pos_microservicio_inmueble.util.Constants.*;

@RestController
@RequestMapping(API)
public class InmuebleController {

    @Autowired
    private IInmuebleService inmuebleService;

    @PostMapping(INMUEBLE)
    public ResponseEntity<?> save(@RequestBody Inmueble inmueble){
        return ResponseEntity.ok(inmuebleService.registerInmueble(inmueble));
    }

    @PutMapping(GET_INMUEBLE_BY_ID)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Inmueble inmueble){
        return ResponseEntity.ok(inmuebleService.updateInmuebleById(id, inmueble));
    }

    @GetMapping(GET_ALL_INMUEBLE)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(inmuebleService.getAllInmuebles());
    }

    @GetMapping(GET_INMUEBLE_BY_ID)
    public ResponseEntity<?> save(@PathVariable Long id){
        return ResponseEntity.ok(inmuebleService.getInmuebleById(id));
    }

    @DeleteMapping(GET_INMUEBLE_BY_ID)
    public ResponseEntity<?> delete(@PathVariable Long id){
        inmuebleService.deleteInmuebleById(id);;
        return ResponseEntity.noContent().build();
    }
}
