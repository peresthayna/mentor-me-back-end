package br.edu.fema.avaliacao.repository;

import br.edu.fema.avaliacao.domain.Avaliacao;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.usuario.domain.Usuario;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByUsuarioAndResposta(Usuario usuario, Resposta resposta);

}
