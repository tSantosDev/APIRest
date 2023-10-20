package dtos;

import models.Usuario;

public record UsuarioDTO(String nome, String senha, String email) {

	public UsuarioDTO(Usuario usuario) {
		this(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
	}

}
