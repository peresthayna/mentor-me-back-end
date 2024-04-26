package br.edu.fema.resposta.repository;

import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.resposta.domain.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long>  {
    Page<Resposta> findALlByPublicacao(Pageable request, Publicacao publicacao);
}
