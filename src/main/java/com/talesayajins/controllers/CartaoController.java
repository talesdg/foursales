package com.talesayajins.controllers;

import com.talesayajins.dto.CartaoDTO;
import com.talesayajins.entities.Cartao;
import com.talesayajins.services.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value="/cartao")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Cartao> find(@PathVariable Integer id) {
        Cartao obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CartaoDTO>> findAll() {
        List<Cartao> list = service.findAll();
        List<CartaoDTO> listDto = list.stream().map(obj -> new CartaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Cartao> insert(@Valid @RequestBody CartaoDTO objDTO){
        Cartao obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Cartao> update(@Valid @RequestBody CartaoDTO objDTO,@PathVariable Integer id){
        Cartao obj = service.fromDTO(objDTO);
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
