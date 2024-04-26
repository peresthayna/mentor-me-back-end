package br.edu.fema.resposta.controller;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.resposta.dto.RespostaCadastroDTO;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.resposta.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    @Autowired
    private RespostaService respostaService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/publicacao/{idPublicacao}")
    public PageResponseDTO<RespostaConsultaDTO> recuperarPorPublicacao(PaginationRequest request, @PathVariable Long idPublicacao) {
        return RespostaConsultaDTO.converterParaPageResponseDTO(this.respostaService.recuperarPorPublicacao(request, idPublicacao));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("atualizar-nota/{idResposta}")
    public void atualizarNota(@PathVariable("idResposta") Long id, @RequestBody int nota) {
        this.respostaService.atualizarNota(id, nota);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public void salvar(@RequestBody RespostaCadastroDTO respostaCadastroDTO) {
        this.respostaService.salvar(respostaCadastroDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    public void atualizar(@PathVariable("id") Long id, @RequestBody RespostaCadastroDTO respostaCadastroDTO) {
        respostaCadastroDTO.setId(id);
        this.respostaService.atualizar(id, respostaCadastroDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public void deletar(@PathVariable("id") Long id) {
        this.respostaService.deletar(id);
    }
}
