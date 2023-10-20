package services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dtos.UsuarioDTO;
import models.Usuario;
import repositories.UsuarioRepository;
import services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Classe de serviço do usuário.
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Método responsável por buscar todos os usuários no banco de dados.
	 *
	 * @return – Retorna todos os usuários.
	 */
	@Override
	public List<Usuario> encontrarTodosUsuarios() {
		return this.usuarioRepository.findAll();
	}

	/**
	 * Método responsável por buscar um usuário específico no banco de dados.
	 *
	 * @param id - ID do parâmetro.
	 * @return Retorna um usuário específico.
	 * @throws Exception pode ser lançada caso não encontre o usuário.
	 */
	@Override
	public Usuario encontrarUsuarioPorID(Long id) throws Exception {
		Optional<Usuario> objUser = this.usuarioRepository.findById(id);
		return objUser.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
	}

	/**
	 * Método responsável pela inserção de usuários no banco de dados.
	 *
	 * @return - Retorna o usuário inserido.
	 * @throws Exceção
	 */
	@Override
	public Usuario inserirUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			Usuario novoUsuario = new Usuario(usuarioDTO);
			return this.usuarioRepository.save(novoUsuario);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Usuário já cadastrado!");
		}
	}

	/**
	 * Método responsável por excluir um usuário específico do banco de dados.
	 *
	 * @param id - ID do parâmetro.
	 * @throws Exception
	 */
	@Override
	public void deletarUsuario(Long id) throws Exception {
		try {
			this.usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
	        throw new EntityNotFoundException("Usuário não encontrado!");
	    } catch (DataIntegrityViolationException e) {
	        throw new DataIntegrityViolationException("Usuário já cadastrado!");
	    }
	}

	/**
	 * Método responsável por atualizar um usuário específico do banco de dados.
	 *
	 * @return – Retorna usuário atualizado.
	 */
	@Override
	public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuario = encontrarUsuarioPorID(id);
		converterUsuarioParaAtualizar(usuarioDTO, usuario);
		this.usuarioRepository.save(usuario);
		return usuario;
	}

	/**
	 * Método responsável por converter o usuário DTO para usuário e atualiza-ló.
	 *
	 * @return – void
	 */
	private void converterUsuarioParaAtualizar(UsuarioDTO usuarioDTO, Usuario usuario) {
		usuario.setNome(usuarioDTO.nome());
		usuario.setSenha(usuarioDTO.senha());
		usuario.setEmail(usuarioDTO.email());
	}
}
