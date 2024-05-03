package br.edu.fema.avaliacao.service;

import br.edu.fema.avaliacao.domain.Avaliacao;
import br.edu.fema.avaliacao.repository.AvaliacaoRepository;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.resposta.repository.RespostaRepository;
import br.edu.fema.resposta.service.RespostaService;
import br.edu.fema.usuario.domain.Usuario;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import br.edu.fema.usuario.repository.UsuarioRepository;
import br.edu.fema.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespostaRepository respostaRepository;

    public List<Avaliacao> recuperarAvaliacoes() {
        return this.avaliacaoRepository.findAll();
    }

    public List<Avaliacao> recuperarAvaliacoesPorUsuario(Long idUsuario, Long idResposta) {
        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Resposta resposta = this.respostaRepository.findById(idResposta).orElse(null);
        if(usuario != null && resposta != null) {
            List<Avaliacao> avaliacoes = this.avaliacaoRepository.findByUsuarioAndResposta(usuario, resposta);
            return avaliacoes;
        }
        throw new IllegalArgumentException("Usuario ou Resposta nulas");
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
