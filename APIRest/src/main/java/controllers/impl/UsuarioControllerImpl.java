package controllers.impl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import controllers.UsuarioController;
import dtos.UsuarioDTO;
import models.Usuario;
import services.impl.UsuarioServiceImpl;

/**
 * Classe controladora do usuário.
 *
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerImpl implements UsuarioController {

	/**
	 * Atributo classe de serviço do usuário.
	 */
	@Autowired
	private UsuarioServiceImpl usuarioService;

	/**
	 * Método responsável por buscar todos os usuários no banco de dados.
	 *
	 * @return – Retorna todos os usuários em Lista.
	 */
	@Override
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> encontrarTodosUsuarios() {
		return ResponseEntity.ok().body(this.usuarioService.encontrarTodosUsuarios().stream()
				.map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList()));
	}

	/**
	 * Método responsável por buscar um usuário específico no banco de dados.
	 *
	 * @param id - ID do usuário.
	 * @return – Retorna o usuário específico encontrado.
	 */
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> encontrarUsuarioPorID(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok().body(new UsuarioDTO(this.usuarioService.encontrarUsuarioPorID(id)));
	}

	/**
	 * Método responsável pela inserção de usuários no banco de dados.
	 *
	 * @return - Retorna o usuário inserido.
	 */
	@Override
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserirUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		Usuario novoUsuario = this.usuarioService.inserirUsuario(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(novoUsuario));
	}

	/**
	 * Método responsável por atualizar um usuário específico no banco de dados.
	 *
	 * @return - Retorna o usuário específico atualizado.
	 */
	@Override
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO)
			throws Exception {
		Usuario usuario = this.usuarioService.atualizarUsuario(id, usuarioDTO);
		return ResponseEntity.ok().body(new UsuarioDTO(usuario));
	}

	/**
	 * Método responsável por deletar um usuário específico no banco de dados.
	 *
	 * @param id - ID do usuário.
	 */
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id) throws Exception {
		this.usuarioService.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
