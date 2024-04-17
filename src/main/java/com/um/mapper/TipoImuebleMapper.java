package com.um.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.um.model.TipoInmueble;

public class TipoImuebleMapper implements RowMapper<TipoInmueble> {
    @Override
    public TipoInmueble mapRow(ResultSet rs, int rowNum) throws SQLException {
        TipoInmueble objeto = new TipoInmueble();

        objeto.setId(rs.getInt("ID"));
        objeto.setTipo_inmueble(rs.getString("TIPO_INMUEBLE"));
        return objeto;
    }
}
