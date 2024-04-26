package br.edu.fema.publicacaoTag.dto;


import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacaoTag.domain.PublicacaoTag;
import br.edu.fema.tag.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PublicacaoTagCadastroDTO {
	private Long id;
	private Publicacao publicacao;
	private Tag tag;

	public PublicacaoTagCadastroDTO(PublicacaoTag publicacaoTag) {
		this.id = publicacaoTag.getId();
		this.publicacao = publicacaoTag.getPublicacao();
		this.tag = publicacaoTag.getTag();
	}
}
