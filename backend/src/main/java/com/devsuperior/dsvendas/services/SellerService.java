package com.devsuperior.dsvendas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsvendas.dto.SellerDTO;
import com.devsuperior.dsvendas.entities.Seller;
import com.devsuperior.dsvendas.repositories.SellerRepository;


//metodos para buscas no banco
@Service //identifica como componente do sistema
public class SellerService {

	@Autowired
	private SellerRepository repository;
	
	public List<SellerDTO> findAll(){
		List<Seller> result =repository.findAll();
		//retornar lista convertida em DTO
		//a funcao map converte a colecao original em outra colecao que pode ser de outro tipo
		//para cada x da lista original, converte em um novo SelletDTO
		return result.stream().map(x -> new SellerDTO(x)).collect(Collectors.toList());
	}

}
