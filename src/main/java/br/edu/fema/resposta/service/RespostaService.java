package br.edu.fema.resposta.service;

import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacao.dto.PublicacaoCadastroDTO;
import br.edu.fema.publicacao.service.PublicacaoService;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.resposta.dto.RespostaCadastroDTO;
import br.edu.fema.resposta.repository.RespostaRepository;
import br.edu.fema.usuario.domain.Usuario;
import br.edu.fema.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    private RespostaRepository respostaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PublicacaoService publicacaoService;

    public Page<Resposta> recuperarPorPublicacao(PaginationRequest request, Long idPublicacao) {
        Publicacao publicacao = this.publicacaoService.recuperarPorId(idPublicacao);
        return this.respostaRepository.findALlByPublicacao(request.toPageable(), publicacao);
    }

    public Resposta atualizarNota(Long idResposta, int nota) {
        Resposta resposta = this.respostaRepository.findById(idResposta).orElse(null);
        if(resposta != null) {
            Usuario usuario = resposta.getUsuario();
            usuario.setPontuacao(nota * 10);
            usuarioService.salvar(usuario);
            resposta.setNumeroAvaliacoes(resposta.getNumeroAvaliacoes() + 1);
            resposta.setMediaAvaliacoes((nota + resposta.getMediaAvaliacoes()) / resposta.getNumeroAvaliacoes());
            return this.respostaRepository.save(resposta);
        }
        throw new IllegalArgumentException("Resposta não encontrada");
    }
    public Resposta recuperarPorId(Long id) {
        return this.respostaRepository.findById(id).orElse(null);
    }

    public Resposta salvar(RespostaCadastroDTO resposta) {
        resposta.setIdUsuario(this.usuarioService.getUsuarioDaSession().getId());
        resposta.setMediaAvaliacoes(0L);
        resposta.setNumeroAvaliacoes(0);
        resposta.setData(new Date());
        Resposta respostaSalvar = new Resposta(resposta);
        respostaSalvar.setPublicacao(publicacaoService.recuperarPorId(resposta.getIdPublicacao()));
        return this.respostaRepository.save(respostaSalvar);
    }

    public Resposta atualizar(Long id, RespostaCadastroDTO resposta) {
        resposta.setId(id);
        resposta.setIdUsuario(this.usuarioService.getUsuarioDaSession().getId());
        return this.respostaRepository.save(new Resposta(resposta));
    }

    public void deletar(Long id) {
        Optional<Resposta> resposta = this.respostaRepository.findById(id);
        resposta.ifPresent(value -> this.respostaRepository.delete(value));
    }
}
