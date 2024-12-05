package com.pagani.proposta_app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pagani.proposta_app.entity.Proposta;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long>{
	
	List<Proposta> findAllByIntegradaIsFalse();

}
