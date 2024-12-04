package com.pagani.proposta_app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pagani.proposta_app.DTO.PropostaRequestDTO;
import com.pagani.proposta_app.DTO.PropostaResponseDTO;
import com.pagani.proposta_app.entity.Proposta;

@Mapper
public interface PropostaMapper {
	
	PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

	@Mapping(target = "usuario.nome", source = "nome")
	@Mapping(target = "usuario.sobrenome", source = "sobrenome")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target = "usuario.telefone", source = "telefone")
	@Mapping(target = "usuario.renda", source = "renda")
	@Mapping(target = "id" , ignore = true)
	@Mapping(target = "aprovada" , ignore = true)
	@Mapping(target = "integrada" , ignore = true)
	@Mapping(target = "observacao" , ignore = true)
	Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDto);
	
	
	@Mapping(target = "nome", source = "usuario.nome")
	@Mapping(target = "sobrenome", source = "usuario.sobrenome")
	@Mapping(target = "telefone", source = "usuario.telefone")
	@Mapping(target = "cpf", source = "usuario.cpf")
	@Mapping(target = "renda", source = "usuario.renda")
	PropostaResponseDTO convertEntityToDTO(Proposta proposta);
	
	List<PropostaResponseDTO> convertListEntityToListDto(Iterable<Proposta> propostas);
}
