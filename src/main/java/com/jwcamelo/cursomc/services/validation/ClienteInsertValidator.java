package com.jwcamelo.cursomc.services.validation;

import com.jwcamelo.cursomc.domain.enums.TipoCliente;
import com.jwcamelo.cursomc.dto.ClienteNewDTO;
import com.jwcamelo.cursomc.resources.exception.FieldMessage;
import com.jwcamelo.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert ann){}
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();
        //testes
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
            list.add(new FieldMessage("CpfOuCnpj","CPF inválido"));

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
            list.add(new FieldMessage("CpfOuCnpj","CNPJ inválido"));

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}
