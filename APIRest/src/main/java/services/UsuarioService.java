package services;

import java.util.List;

import dtos.UsuarioDTO;
import models.Usuario;

/**
 * Interface de serviço do usuário.
 *
 */
public interface UsuarioService {
	List<Usuario> encontrarTodosUsuarios();

	Usuario encontrarUsuarioPorID(Long id) throws Exception;

	Usuario inserirUsuario(UsuarioDTO usuarioDTO) throws Exception;

	Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception;

	void deletarUsuario(Long id) throws Exception;
}
