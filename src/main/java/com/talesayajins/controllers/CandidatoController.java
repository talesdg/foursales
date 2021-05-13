package com.talesayajins.controllers;

import com.talesayajins.dto.CandidatoDTO;
import com.talesayajins.dto.CandidatoNewDTO;
import com.talesayajins.entities.Candidato;
import com.talesayajins.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value="/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Candidato> find(@PathVariable Integer id) {
        Candidato obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CandidatoNewDTO>> findAll() {
        List<Candidato> list = service.findAll();
        List<CandidatoNewDTO> listDto = list.stream().map(obj -> new CandidatoNewDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Candidato> insert(@Valid @RequestBody CandidatoNewDTO objDTO){
        Candidato obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Candidato> update(@Valid @RequestBody CandidatoDTO objDTO, @PathVariable Integer id){
        Candidato obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
