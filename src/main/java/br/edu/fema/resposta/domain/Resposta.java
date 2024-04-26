package br.edu.fema.resposta.domain;

import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.resposta.dto.RespostaCadastroDTO;
import br.edu.fema.resposta.dto.RespostaConsultaDTO;
import br.edu.fema.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "respostas")
@NoArgsConstructor
public class Resposta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    private Publicacao publicacao;

    @Column(name = "resposta", columnDefinition = "TEXT")
    private String resposta;

    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "media_avaliacoes")
    private Long mediaAvaliacoes;

    @Column(name = "numero_avaliacoes")
    private int numeroAvaliacoes;

    public Resposta(RespostaCadastroDTO resposta) {
        this.id = resposta.getId();
        this.usuario = new Usuario();
        this.usuario.setId(resposta.getIdUsuario());
        this.resposta = resposta.getResposta();
        this.data = resposta.getData();
        this.mediaAvaliacoes = resposta.getMediaAvaliacoes();
        this.publicacao = new Publicacao();
        this.publicacao.setId(resposta.getIdPublicacao());
        this.numeroAvaliacoes = resposta.getNumeroAvaliacoes();
    }

    public Resposta(RespostaConsultaDTO resposta) {
        this.id = resposta.getId();
        this.usuario = new Usuario();
        this.usuario = resposta.getUsuario();
        this.resposta = resposta.getResposta();
        this.data = resposta.getData();
        this.mediaAvaliacoes = resposta.getMediaAvaliacoes();
        this.publicacao = new Publicacao();
        this.publicacao.setId(resposta.getIdPublicacao());
    }
}
