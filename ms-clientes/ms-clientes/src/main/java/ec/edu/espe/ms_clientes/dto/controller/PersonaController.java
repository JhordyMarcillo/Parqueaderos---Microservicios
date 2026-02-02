package ec.edu.espe.ms_clientes.dto.controller;

import ec.edu.espe.ms_clientes.dto.request.PersonaJuridicaRequestDto;
import ec.edu.espe.ms_clientes.dto.request.PersonaNaturalRequestDto;
import ec.edu.espe.ms_clientes.dto.response.PersonaResponseDto;
import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.PersonaJuridica;
import ec.edu.espe.ms_clientes.model.PersonaNatural;
import ec.edu.espe.ms_clientes.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaResponseDto>> findAll() {
        return ResponseEntity.ok(personaService.findAllPersona());
    }

    @GetMapping("/naturales")
    public ResponseEntity<List<PersonaNatural>> findAllNaturales() {
        return ResponseEntity.ok(personaService.findAllNaturalPersona());
    }

    @GetMapping("/juridicas")
    public ResponseEntity<List<PersonaJuridica>> findAllJuridicas() {
        return ResponseEntity.ok(personaService.findAllJuridicaPersona());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Persona>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(personaService.findAllByNameIgnoreCaseContaining(nombre));
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<PersonaResponseDto> findByIdentificacion(@PathVariable
                                                                       String identificacion) {
        return ResponseEntity.ok(personaService.findPersonaById(identificacion));
    }

    @PostMapping("/natural")
    public ResponseEntity<PersonaResponseDto> createPersonaNatural(@RequestBody PersonaNaturalRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.createPersonaNatural(dto));
    }

    @PostMapping("/juridica")
    public ResponseEntity<PersonaResponseDto> createPersonaJuridica(@RequestBody PersonaJuridicaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.createPersonaJuridica(dto));
    }

    @PutMapping("/natural/{id}")
    public ResponseEntity<PersonaResponseDto> updatePersonaNatural(@PathVariable UUID id, @RequestBody PersonaNaturalRequestDto dto) {
        return ResponseEntity.ok(personaService.updatePersonaNatural(dto, id));
    }

    @PutMapping("/juridica/{id}")
    public ResponseEntity<PersonaResponseDto> updatePersonaJuridica(@PathVariable UUID id, @RequestBody PersonaJuridicaRequestDto dto) {
        return ResponseEntity.ok(personaService.updatePersonaJuridica(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable UUID id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}
