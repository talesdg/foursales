package com.talesayajins.services;

import com.talesayajins.dto.CartaoDTO;
import com.talesayajins.entities.Cartao;
import com.talesayajins.repositories.CartaoRepository;
import com.talesayajins.services.exceptions.DataIntegrityException;
import com.talesayajins.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repo;

    @Autowired
    private CandidatoService candidatoService;

    public Cartao find(Integer id) {
        Optional<Cartao> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cartao.class.getName()));
    }

    public Cartao insert(Cartao obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Cartao update(Cartao obj) {
        Cartao newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas.");
        }
    }

    public List<Cartao> findAll() {

        return repo.findAll();
    }

    private void updateData(Cartao newObj, Cartao obj) {
        newObj.setTipo(obj.getTipo());
        if (obj.getCandidato() == null) {
            newObj.setCandidato(newObj.getCandidato());
        } else {
            newObj.setCandidato(obj.getCandidato());
        }
    }

    public Cartao fromDTO(CartaoDTO objDto) {
        Cartao p = new Cartao(null,
                objDto.getTipo(),
                objDto.getCandidato_id() != null ? candidatoService.find(objDto.getCandidato_id()) : candidatoService.find(0)
        );
        return p;
    }
}
