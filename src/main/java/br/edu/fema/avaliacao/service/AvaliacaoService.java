package br.edu.fema.avaliacao.service;

import br.edu.fema.avaliacao.domain.Avaliacao;
import br.edu.fema.avaliacao.repository.AvaliacaoRepository;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> recuperarAvaliacoes() {
        return this.avaliacaoRepository.findAll();
    }

    public List<Avaliacao> recuperarAvaliacoesPorResposta(RespostaConsultaDTO resposta) {
        return this.avaliacaoRepository.findAllByResposta(resposta);
    }

    public Avaliacao recuperarAvaliacoesPorUsuario(UsuarioConsultaDTO usuario) {
        return this.avaliacaoRepository.findByUsuario(usuario);
    }
    public Avaliacao getAvaliacaoById(Long id) {
        Avaliacao avaliacao = this.avaliacaoRepository.findById(id).orElse(null);
        if(avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada");
        } else {
            return avaliacao;
        }
    }

    public void salvarAvaliacao(Avaliacao avaliacao) {
        this.avaliacaoRepository.save(avaliacao);
    }

    public void atualizarAvaliacao(Long id, Avaliacao avaliacao) {
        avaliacao.setId(id);
        this.avaliacaoRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Long id) {
        Avaliacao avaliacao = this.avaliacaoRepository.findById(id).orElse(null);
        if(avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada!");
        }
        this.avaliacaoRepository.delete(avaliacao);
    }

}
