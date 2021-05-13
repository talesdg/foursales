package com.talesayajins.repositories;

import com.talesayajins.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Integer> {
}
