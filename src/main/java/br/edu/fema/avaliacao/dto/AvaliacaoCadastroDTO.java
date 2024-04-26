package br.edu.fema.avaliacao.dto;

import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import lombok.Getter;

@Getter
public class AvaliacaoCadastroDTO {
    private Long id;
    private UsuarioConsultaDTO usuario;
    private RespostaConsultaDTO resposta;
    private int nota;
}
