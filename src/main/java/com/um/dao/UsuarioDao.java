package com.um.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.um.mapper.UsuarioMapper;
import com.um.model.Usuario;

@Component
public class UsuarioDao {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JdbcTemplate MySQLTemplate;

	public Usuario buscaUsuarioPorEmail(String email) {
		Usuario objeto = new Usuario();
		String query = "SELECT ID, NOMBRE, EMAIL, PASSWORD, ROLES, ACTIVO FROM USUARIO WHERE EMAIL = ?";

		try {
			objeto = MySQLTemplate.queryForObject(query, new UsuarioMapper(), email);
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | buscaUsuarioPorEmail | " + e);
		}

		return objeto;
	}

	public List<Usuario> listaUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		String query = "SELECT * FROM USUARIO ORDER BY NOMBRE";

		try {
			lista = MySQLTemplate.query(query, new UsuarioMapper());
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | listaUsuarios | " + e);
		}
		return lista;
	}

	public boolean grabaUsuario(Usuario objeto, int[] roles) {
		boolean ok = false;

		objeto.setPassword(bCryptPasswordEncoder.encode(objeto.getPassword()));

		String query = "SELECT NOMBRE FROM ROLE WHERE ID = ?";

		String grabaRoles = "-";

		if (roles != null) {
			for (int roleId : roles) {
				String role = MySQLTemplate.queryForObject(query, String.class, roleId);
				grabaRoles += String.valueOf(role) + "-";
			}
		}
		Object[] parametros = new Object[] {
				objeto.getNombre(), objeto.getEmail(), objeto.getPassword(), grabaRoles
		};

		query = "INSERT INTO USUARIO(NOMBRE,EMAIL,PASSWORD,ROLES,ACTIVO, AGENCIA_ID, STATUS ) VALUES(?, ?, ?, ?, TRUE, 0, '-' )";

		if (MySQLTemplate.update(query, parametros) >= 1) {
			query = "SELECT ID FROM USUARIO WHERE NOMBRE = ? AND EMAIL = ?";

			parametros = new Object[] {
					objeto.getNombre(), objeto.getEmail()
			};

			int usuarioId = MySQLTemplate.queryForObject(query, Integer.class, parametros);

			if (roles != null) {
				for (int roleId : roles) {
					query = "INSERT INTO USUARIO_ROLE(ID_USUARIO, ID_ROLE) VALUES( ?, ?)";
					MySQLTemplate.update(query, new Object[] { usuarioId, roleId });
				}
			}
			ok = true;
		}

		return ok;
	}

	public boolean editarUsuario(Usuario objeto, int[] roles) {
		boolean ok = false;
		System.out.println("password " + objeto.getPassword());

		objeto.setPassword(bCryptPasswordEncoder.encode(objeto.getPassword()));

		System.out.println("Entrado en Editar Dao ");

		String query = "UPDATE USUARIO SET NOMBRE = ?, EMAIL = ?,PASSWORD = ?, AGENCIA_ID = ?  WHERE ID = ?";
		int rowsUpdated = MySQLTemplate.update(query, objeto.getNombre(),
				objeto.getEmail(), objeto.getPassword(),
				objeto.getAgencia_id(),
				objeto.getId());
		if (rowsUpdated >= 1) {
			ok = true;
		}
		ok = true;
		return ok;
	}

	public Usuario buscaUsuarioPorId(int id) {
		Usuario objeto = new Usuario();
		String query = "SELECT * FROM USUARIO WHERE ID = ?";

		try {
			objeto = MySQLTemplate.queryForObject(query, new UsuarioMapper(), id);
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | buscaUsuarioPorId | " + e);
		}

		return objeto;
	}

	public boolean existeEmail(String email) {
		boolean ok = false;
		String query = "SELECT COUNT(*) FROM USUARIO WHERE EMAIL = ?";

		try {
			if (MySQLTemplate.queryForObject(query, Integer.class, email) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | existeEmail | " + e);
		}

		return ok;
	}

	public boolean bajaUsuario(int usuarioId) {
		boolean ok = false;
		String query = "UPDATE USUARIO SET ACTIVO = FALSE WHERE ID = ?";

		try {
			if (MySQLTemplate.update(query, usuarioId) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | bajaUsuario | " + e);
		}

		return ok;
	}

	public boolean activarUsuario(int usuarioId) {
		boolean ok = false;
		String query = "UPDATE USUARIO SET ACTIVO = TRUE WHERE ID = ?";

		try {
			if (MySQLTemplate.update(query, usuarioId) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | activarUsuario | " + e);
		}

		return ok;
	}

	public boolean borrarUsuarioPorId(int id) {
		boolean ok = false;
		String query = "DELETE FROM USUARIO WHERE ID = ?";

		try {
			if (MySQLTemplate.update(query, id) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : UsuarioDao | borrarUsuario | " + e);
		}

		return ok;
	}

}
