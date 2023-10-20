package models;

import dtos.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Usuarios")
@Table(name = "Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 30, nullable = false)
	private String senha;

	@Column(length = 100, nullable = false, unique = true)
	private String email;

	public Usuario(UsuarioDTO usuarioDTO) {
		this.nome = usuarioDTO.nome();
		this.senha = usuarioDTO.senha();
		this.email = usuarioDTO.email();
	}

}
