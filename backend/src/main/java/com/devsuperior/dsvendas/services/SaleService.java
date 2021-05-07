package com.devsuperior.dsvendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.dto.SaleSuccessDTO;
import com.devsuperior.dsvendas.dto.SaleSumDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;


//metodos para buscas no banco (findAll, 

@Service //identifica como componente do sistema
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;

	@Transactional(readOnly = true) //serve para que toda operacao seja resolvida so no service, metodo soh de busca
	public Page<SaleDTO> findAll(Pageable pageable){
		
		sellerRepository.findAll(); //para evitar de buscar os vendedores toda hora, busca antes os vendedores e isso ja
		//faz eles serem salvos em memoria e sao identificados pela jpa
		
		Page<Sale> result =repository.findAll(pageable);
		
		return result.map(x -> new SaleDTO(x)); //retornar pagina convertida em DTO
		//a funcao map converte a colecao original em outra colecao que pode ser de outro tipo
		//para cada x da lista original, converte em um novo SaletDTO
	}
	
	@Transactional(readOnly = true) 
	public List<SaleSumDTO> amountGroupedBySeller(){
		return repository.amountGroupedBySeller();
		
	}
	
	@Transactional(readOnly = true) 
	public List<SaleSuccessDTO>  successGroupedBySeller(){
		return repository.sucessGroupedBySeller();
		
	}


}
