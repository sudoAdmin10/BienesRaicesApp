package com.um.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.um.model.Estado_Propiedades;

public class EstadoPropiedadMapper implements RowMapper<Estado_Propiedades> {
    @Override
    public Estado_Propiedades mapRow(ResultSet rs, int rowNum) throws SQLException {
        Estado_Propiedades objeto = new Estado_Propiedades();

        objeto.setId(rs.getInt("ID"));
        objeto.setEstado(rs.getString("ESTADO"));

        return objeto;
    }
}
