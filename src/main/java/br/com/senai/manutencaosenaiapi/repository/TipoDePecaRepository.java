package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;

@Repository
public interface TipoDePecaRepository extends JpaRepository<TipoDePeca, Integer> {

	@Query(value="SELECT tp FROM TipoDePeca tp WHERE Upper(tp.descricao) LIKE Upper(:descricao)")
	List<TipoDePeca> listarPor(@Param("descricao")String descricao);
	
}
