package br.edu.fema.resposta.dto;

import br.edu.fema.publicacao.domain.Publicacao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RespostaCadastroDTO {
    private Long id;
    private Long idUsuario;
    private Long idPublicacao;
    private String resposta;
    private Date data;
    private Long mediaAvaliacoes;
    private int numeroAvaliacoes;
}
