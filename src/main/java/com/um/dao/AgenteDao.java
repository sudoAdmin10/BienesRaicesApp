package com.um.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.um.mapper.AgenteMapper;
import com.um.model.Agente;

public class AgenteDao {

    @Autowired
    private JdbcTemplate MySQLTemplate;

    public Agente buscaAgentePorAgencia(String agencia) {
        Agente objeto = new Agente();
        String query = "SELECT * FROM AGENTE WHERE AGENCIA = ?";

        try {
            objeto = MySQLTemplate.queryForObject(query, new AgenteMapper(), agencia);
        } catch (Exception e) {
            System.out.println("ERROR : AgenteDao | buscaAgentePorEmail | " + e);
        }

        return objeto;
    }

    public List<Agente> listaAgentes() {
        List<Agente> lista = new ArrayList<Agente>();
        String query = "SELECT * FROM AGENTE ORDER BY NOMBRE";

        try {
            lista = MySQLTemplate.query(query, new AgenteMapper());
        } catch (Exception e) {
            System.out.println("ERROR : AgenteDao | listaAgentes | " + e);
        }
        return lista;
    }
}
