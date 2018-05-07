package br.senai.sp.tecagenda.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Contato {

	@Id
	private Long idContato;

	@NotNull
	private String nome;

	@Null
	private String telefone;

	@Null
	private String endereco;

	@Null
	private String email;

	@Null
	private String linkFacebook;

	private byte[] foto;

	@Null
	private int ratingBar;

	public Contato(@NotNull String nome, @Null String telefone, @Null String endereco, @Null String email,
			@Null String linkFacebook, byte[] foto, @Null int ratingBar) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.linkFacebook = linkFacebook;
		this.foto = foto;
		this.ratingBar = ratingBar;
	}

}
