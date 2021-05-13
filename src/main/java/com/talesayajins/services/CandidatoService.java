package com.talesayajins.services;

import com.talesayajins.dto.CandidatoDTO;
import com.talesayajins.dto.CandidatoNewDTO;
import com.talesayajins.entities.Candidato;
import com.talesayajins.repositories.CandidatoRepository;
import com.talesayajins.services.exceptions.DataIntegrityException;
import com.talesayajins.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository repo;

    public Candidato find(Integer id) {
        Optional<Candidato> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Candidato.class.getName()));
    }

    public Candidato insert(Candidato obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Candidato update(Candidato obj) {
        Candidato newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir porque há cartões relacionados a este candidato.");
        }
    }

    public List<Candidato> findAll() {
        return repo.findAll();
    }

    private void updateData(Candidato newObj, Candidato obj) {
        newObj.setNome(obj.getNome() != null ? obj.getNome() : newObj.getNome());
        newObj.setEmail(obj.getEmail() != null ? obj.getEmail() : newObj.getEmail());
        newObj.setCpf(obj.getCpf() != null ? obj.getCpf() : newObj.getCpf());
    }

    public Candidato fromDTO(CandidatoNewDTO objDto) {
        Candidato p = new Candidato(null,
                objDto.getNome(),
                objDto.getCpf(),
                objDto.getEmail()
        );
        return p;
    }
    public Candidato fromDTO(CandidatoDTO objDto) {
        Candidato p = new Candidato(objDto.getId(),
                objDto.getNome(),
                objDto.getCpf(),
                objDto.getEmail()
        );
        return p;
    }
}
