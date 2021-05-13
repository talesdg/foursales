package com.talesayajins.repositories;

import com.talesayajins.entities.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Integer> {
    @Transactional(readOnly=true)
    Candidato findByEmail(String email);

    @Transactional(readOnly=true)
    Candidato findByCpf(String cpf);
}
