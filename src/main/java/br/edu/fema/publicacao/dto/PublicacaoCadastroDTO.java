package br.edu.fema.publicacao.dto;

import java.util.List;
import java.util.Objects;

import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacaoTag.dto.PublicacaoTagCadastroDTO;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicacaoCadastroDTO {
	private Long id;
    private Long idUsuario;
    private String titulo;
    private String publicacao;
    private Integer visualizacoes;
    private List<PublicacaoTagCadastroDTO> publicacaoTags;
    private List<RespostaConsultaDTO> respostas;

    public PublicacaoCadastroDTO(Publicacao publicacao) {
        this.id = publicacao.getId();
        this.idUsuario = publicacao.getUsuario().getId();;
        this.titulo = publicacao.getTitulo();
        this.publicacao = publicacao.getPublicacao();
        this.visualizacoes = publicacao.getVisualizacoes();
        publicacao.getPublicacaoTags().forEach(publicacaoTag ->
            this.publicacaoTags.add(new PublicacaoTagCadastroDTO(publicacaoTag)));
        publicacao.getRespostas().forEach(resposta ->
            this.respostas.add(new RespostaConsultaDTO(resposta)));
    }
}
