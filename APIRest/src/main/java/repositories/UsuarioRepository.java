package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Usuario;

/**
 * Interface repositório do usuário.
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
