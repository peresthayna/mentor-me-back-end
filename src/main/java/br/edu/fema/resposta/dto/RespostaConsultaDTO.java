package br.edu.fema.resposta.dto;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.usuario.domain.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class RespostaConsultaDTO {
    private Long id;
    private Usuario usuario;
    private Long idPublicacao;
    private String resposta;
    private Date data;
    private Long mediaAvaliacoes;
    private int numeroAvaliacoes;

    public RespostaConsultaDTO(Resposta resposta) {
        if(Objects.nonNull(resposta)) {
        this.id = resposta.getId();
        this.usuario = resposta.getUsuario();
        this.resposta = resposta.getResposta();
        this.data = resposta.getData();
        this.mediaAvaliacoes = resposta.getMediaAvaliacoes();
        this.idPublicacao = resposta.getPublicacao().getId();
        this.numeroAvaliacoes = resposta.getNumeroAvaliacoes();
        }
    }

    public static List<RespostaConsultaDTO> converterParaListDTO(List<Resposta> respostas) {
        return respostas.stream().map(RespostaConsultaDTO::new).toList();
    }

    public static PageResponseDTO<RespostaConsultaDTO> converterParaPageResponseDTO(Page<Resposta> page) {
        List<RespostaConsultaDTO> dto = converterParaListDTO(page.getContent());
        return new PageResponseDTO<RespostaConsultaDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
