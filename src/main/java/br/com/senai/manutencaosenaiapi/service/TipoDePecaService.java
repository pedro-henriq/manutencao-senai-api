package br.com.senai.manutencaosenaiapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.repository.TipoDePecaRepository;

@Service
@Validated
public class TipoDePecaService {

	@Autowired
	private TipoDePecaRepository repository;
	
	public TipoDePeca inserir(@Valid @NotNull(message = "O tipo não pode ser nulo") TipoDePeca novoTipo) {
		TipoDePeca tipoSalvo = repository.save(novoTipo);
		return tipoSalvo;
	}

	public TipoDePeca alterar(@Valid @NotNull(message = "O tipo não pode ser nulo") TipoDePeca tipoSalvo) {
		TipoDePeca tipoAtualizado = repository.save(tipoSalvo);
		return tipoAtualizado;
	}

	public List<TipoDePeca> listarPor(
			@NotEmpty(message = "A descrição para busca não pode ser nulo") @NotBlank(message = "A descrição para busca não pode ser em branco") String descricao) {
		return repository.listarPor("%" + descricao + "%");
	}

	public void removerPor(
			@NotNull(message = "O id não pode ser nulo") @Min(value = 100, message = "O id deve maior que 0") Integer id) {
		this.repository.deleteById(id);
	}
	
}
