package com.um.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.um.model.Agente;

public class AgenteMapper implements RowMapper<Agente> {
    @Override
    public Agente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Agente objeto = new Agente();

        objeto.setId(rs.getInt("ID"));
        objeto.setNombre(rs.getString("NOMBRE"));
        objeto.setApellido(rs.getString("APELLIDO"));
        objeto.setTelefono(rs.getString("TELEFONO"));
        objeto.setCorreo(rs.getString("CORREO"));
        objeto.setAgencia_id(rs.getInt("AGENCIA_ID"));

        return objeto;
    }

}
