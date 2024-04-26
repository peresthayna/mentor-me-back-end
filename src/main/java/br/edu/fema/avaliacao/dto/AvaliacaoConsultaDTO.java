package br.edu.fema.avaliacao.dto;

import br.edu.fema.avaliacao.domain.Avaliacao;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoConsultaDTO {
    private Long id;
    private UsuarioConsultaDTO usuario;
    private RespostaConsultaDTO resposta;
    private int nota;

    public AvaliacaoConsultaDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.usuario = new UsuarioConsultaDTO(avaliacao.getUsuario());
        this.resposta = new RespostaConsultaDTO(avaliacao.getResposta());
        this.nota = avaliacao.getNota();
    }

    public static List<AvaliacaoConsultaDTO> converterParaListDTO(List<Avaliacao> avaliacoes) {
        return avaliacoes.stream().map(AvaliacaoConsultaDTO::new).toList();
    }
}
