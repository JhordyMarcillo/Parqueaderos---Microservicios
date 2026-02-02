package ec.edu.espe.ms_clientes.dto.controller;

import ec.edu.espe.ms_clientes.dto.request.MotoRequestDto;
import ec.edu.espe.ms_clientes.dto.request.VehiculoRequestDto;
import ec.edu.espe.ms_clientes.dto.response.VehiculoResponseDto;
import ec.edu.espe.ms_clientes.model.Vehiculo;
import ec.edu.espe.ms_clientes.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/")
    public ResponseEntity<List<VehiculoResponseDto>> getVehiculos() {
        return ResponseEntity.ok(vehiculoService.getVehiculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDto> getVehiculoById(@PathVariable UUID id) {
        return ResponseEntity.ok(vehiculoService.getVehiculoById(id));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VehiculoResponseDto> getVehiculoByPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(vehiculoService.getVehiculoByPlaca(placa));
    }

    @GetMapping("/propietario/{identificacion}")
    public ResponseEntity<List<VehiculoResponseDto>> getVehiculosByPersona(@PathVariable String identificacion) {
        return ResponseEntity.ok(vehiculoService.getVehiculosByPersona(identificacion));
    }


    @PostMapping("familiar/")
    public ResponseEntity<VehiculoResponseDto> createVehiculo(@RequestBody VehiculoRequestDto vehiculo){
        return ResponseEntity.ok(vehiculoService.createVehiculoFamiliar(vehiculo));
    }

    @DeleteMapping("familiar/{id}")
    public ResponseEntity<VehiculoResponseDto> deleteVehiculoFamiliar(@PathVariable UUID id){
        vehiculoService.deleteVehiculoFamiliar(id);
        return ResponseEntity.ok((VehiculoResponseDto) Collections.singletonMap("message", "Vehiculo delete"));
    }

    @PutMapping("familiar/{id}")
    public ResponseEntity<VehiculoResponseDto> updateVehiculoFamiliar(@PathVariable UUID id, @RequestBody VehiculoRequestDto vehiculo){
        return ResponseEntity.ok(vehiculoService.updateVehiculoFamiliar(id, vehiculo));
    }

    @PostMapping("/moto")
    public ResponseEntity<VehiculoResponseDto> createVehiculoMoto(@RequestBody MotoRequestDto dto) { // <--- CAMBIO AQUÍ
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.createVehiculoMoto(dto));
    }

    @DeleteMapping("moto/{id}")
    public ResponseEntity<VehiculoResponseDto> deleteVehiculoMoto(@PathVariable UUID id){
        vehiculoService.deleteVehiculoFamiliar(id);
        return ResponseEntity.ok((VehiculoResponseDto) Collections.singletonMap("message", "Vehiculo delete"));
    }

    @PutMapping("moto/{id}")
    public ResponseEntity<VehiculoResponseDto> updateVehiculoMoto(@PathVariable UUID id, @RequestBody MotoRequestDto vehiculo){
        return ResponseEntity.ok(vehiculoService.updateVehiculoMoto(id, vehiculo));
    }

}
