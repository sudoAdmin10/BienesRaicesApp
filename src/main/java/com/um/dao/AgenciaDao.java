package com.um.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.um.mapper.AgenciaMapper;
import com.um.model.Agencia;

@Component
public class AgenciaDao {
    @Autowired
    private JdbcTemplate MySQLTemplate;

    public HashMap<Integer, Agencia> mapaAgencias() {
        HashMap<Integer, Agencia> agencias = new HashMap<Integer, Agencia>();
        List<Agencia> lista = new ArrayList<Agencia>();

        String query = "SELECT * FROM AGENCIAS";
        try {
            lista = MySQLTemplate.query(query, new AgenciaMapper());
            for (Agencia agencia : lista) {
                agencias.put(agencia.getId(), agencia);
            }
        } catch (Exception e) {
            System.out.println("ERROR : AgenciaDao | mapaAgencias | " + e);
        }

        return agencias;
    }

}
