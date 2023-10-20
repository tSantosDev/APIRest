package controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import dtos.UsuarioDTO;

/**
 * Interface de controle do usuário.
 *
 */
public interface UsuarioController {
	ResponseEntity<List<UsuarioDTO>> encontrarTodosUsuarios();

	ResponseEntity<UsuarioDTO> encontrarUsuarioPorID(Long id) throws Exception;

	ResponseEntity<UsuarioDTO> inserirUsuario(UsuarioDTO usuarioDTO) throws Exception;

	ResponseEntity<UsuarioDTO> atualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception;
	
	ResponseEntity<String> deletarUsuario(Long id) throws Exception;
}
