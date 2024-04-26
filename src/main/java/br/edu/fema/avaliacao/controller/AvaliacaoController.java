package br.edu.fema.avaliacao.controller;

import br.edu.fema.avaliacao.domain.Avaliacao;
import br.edu.fema.avaliacao.dto.AvaliacaoConsultaDTO;
import br.edu.fema.avaliacao.service.AvaliacaoService;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.resposta.service.RespostaService;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import br.edu.fema.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RespostaService respostaService;

    @GetMapping()
    public List<AvaliacaoConsultaDTO> recuperarAvaliacoes() {
        return AvaliacaoConsultaDTO.converterParaListDTO(this.avaliacaoService.recuperarAvaliacoes());
    }

    @GetMapping("/resposta/{idResposta}")
    public List<AvaliacaoConsultaDTO> recuperarAvaliacoesPorResposta(@PathVariable("idResposta") Long idResposta) {
        RespostaConsultaDTO resposta = new RespostaConsultaDTO(this.respostaService.recuperarPorId(idResposta));
        return AvaliacaoConsultaDTO.converterParaListDTO(this.avaliacaoService.recuperarAvaliacoesPorResposta(resposta));
    }

    @GetMapping("/usuario/{idUsuario}")
    public AvaliacaoConsultaDTO recuperarAvaliacaoPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        UsuarioConsultaDTO usuario =  new UsuarioConsultaDTO((this.usuarioService.findById(idUsuario)));
        return new AvaliacaoConsultaDTO(this.avaliacaoService.recuperarAvaliacoesPorUsuario(usuario));
    }

    @GetMapping("/busca/{id}")
    public AvaliacaoConsultaDTO getAvaliacaoById(@PathVariable("id") Long id) {
        return new AvaliacaoConsultaDTO(this.avaliacaoService.getAvaliacaoById(id));
    }

    @PostMapping
    public void salvarAvaliacao(@RequestBody AvaliacaoConsultaDTO avaliacaoConsultaDTO) {
        this.avaliacaoService.salvarAvaliacao(new Avaliacao(avaliacaoConsultaDTO));
    }

    @PutMapping("atualizar/{id}")
    public void atualizarAvaliacao(@PathVariable("id") Long id, @RequestBody Avaliacao avaliacao) {
        this.avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }

    @DeleteMapping("deletar/{id}")
    public void deletarAtualizacao(@PathVariable("id") Long id) {
        this.avaliacaoService.deletarAvaliacao(id);
    }

}
