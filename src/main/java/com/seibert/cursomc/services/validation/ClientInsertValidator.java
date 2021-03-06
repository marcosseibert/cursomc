package com.seibert.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.seibert.cursomc.domain.Client;
import com.seibert.cursomc.domain.ClientNewDTO;
import com.seibert.cursomc.domain.enums.ClientType;
import com.seibert.cursomc.repositories.ClientRepository;
import com.seibert.cursomc.resources.exection.FieldMessage;
import com.seibert.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repo;
	
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// include the tests here, inserting errors in list
		if (objDTO.getType().equals(ClientType.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
		}
		
		if (objDTO.getType().equals(ClientType.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ((objDTO.getCpfOrCnpj()))) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client aux = repo.findByEmail(objDTO.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","This email already exist"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
