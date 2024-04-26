package br.edu.fema.avaliacao.domain;

import br.edu.fema.avaliacao.dto.AvaliacaoConsultaDTO;
import br.edu.fema.resposta.domain.Resposta;
import br.edu.fema.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_resposta")
    private Resposta resposta;

    @Column(name = "nota")
    private int nota;

    public Avaliacao(AvaliacaoConsultaDTO avaliacaoConsultaDTO) {
        this.id = avaliacaoConsultaDTO.getId();
        this.usuario = new Usuario(avaliacaoConsultaDTO.getUsuario());
        this.resposta = new Resposta(avaliacaoConsultaDTO.getResposta());
        this.nota = avaliacaoConsultaDTO.getNota();
    }
}
