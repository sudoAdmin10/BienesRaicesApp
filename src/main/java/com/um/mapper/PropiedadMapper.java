package com.um.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.um.model.Propiedad;

public class PropiedadMapper implements RowMapper<Propiedad> {

    @Override
    public Propiedad mapRow(ResultSet rs, int rowNum) throws SQLException {
        Propiedad objeto = new Propiedad();

        objeto.setId(rs.getInt("ID"));
        objeto.setPrecio(rs.getString("PRECIO"));
        objeto.setTipo(rs.getInt("TIPO"));
        objeto.setDescripcion(rs.getString("DESCRIPCION"));
        objeto.setDimensiones(rs.getString("DIMENSIONES"));
        objeto.setNum_wc(rs.getString("NUM_WC"));
        objeto.setNum_habitaciones(rs.getString("NUM_HABITACIONES"));
        objeto.setDireccion(rs.getString("DIRECCION"));
        objeto.setEstado_Propiedad(rs.getInt("ESTADO_PROPIEDAD"));
        objeto.setPropietario(rs.getInt("PROPIETARIO"));
        objeto.setImagen(rs.getString("IMAGEN"));

        return objeto;
    }
}
