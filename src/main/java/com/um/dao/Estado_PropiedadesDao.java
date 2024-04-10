package com.um.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.um.model.Estado_Propiedades;

public class Estado_PropiedadesDao {

    @Autowired
    private JdbcTemplate MySQLTemplate;

    public List<Estado_Propiedades> listEstados() {
        List<Estado_Propiedades> lista = new ArrayList<Estado_Propiedades>();
        String query = "SELECT * FROM EXA.ESTADO ORDER BY ID";
        lista = MySQLTemplate.queryForList(query, Estado_Propiedades.class);

        return lista;
    }

}
