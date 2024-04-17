package com.um.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.um.model.Agencia;

public class AgenciaMapper implements RowMapper<Agencia> {
    @Override
    public Agencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Agencia objeto = new Agencia();

        objeto.setId(rs.getInt("ID"));
        objeto.setNombre(rs.getString("NOMBRE"));
        objeto.setDireccion(rs.getString("DIRECCION"));
        objeto.setTelefono(rs.getString("TELEFONO"));
        return objeto;
    }
}
