package com.talesayajins.services.validation;

import com.talesayajins.controllers.exceptions.FieldMessage;
import com.talesayajins.dto.CandidatoNewDTO;
import com.talesayajins.entities.Candidato;
import com.talesayajins.repositories.CandidatoRepository;
import com.talesayajins.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CandidatoInsertValidator implements ConstraintValidator<CandidatoInsert, CandidatoNewDTO> {

    @Autowired
    private CandidatoRepository repo;

    @Override
    public void initialize(CandidatoInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(CandidatoNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (!BR.isValidCPF(objDto.getCpf())) {
            list.add(new FieldMessage("cpf", "CPF inválido"));
        }

        Candidato aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        Candidato aux2 = repo.findByCpf(objDto.getCpf());
        if (aux2 != null) {
            list.add(new FieldMessage("cpf", "CPF já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
