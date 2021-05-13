package com.talesayajins.services.validation;

import com.talesayajins.controllers.exceptions.FieldMessage;
import com.talesayajins.dto.CandidatoDTO;
import com.talesayajins.entities.Candidato;
import com.talesayajins.repositories.CandidatoRepository;
import com.talesayajins.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CandidatoUpdateValidator implements ConstraintValidator<CandidatoUpdate, CandidatoDTO> {

    @Autowired
    private CandidatoRepository repo;

    @Override
    public void initialize(CandidatoUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(CandidatoDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getCpf() != null && !BR.isValidCPF(objDto.getCpf())) {
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
