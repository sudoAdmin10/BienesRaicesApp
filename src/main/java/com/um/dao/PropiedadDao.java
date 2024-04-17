package com.um.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.um.mapper.EstadoPropiedadMapper;
import com.um.mapper.PropiedadMapper;
import com.um.model.Estado_Propiedades;
import com.um.model.Propiedad;
import com.um.model.TipoInmueble;
import com.um.mapper.TipoImuebleMapper;

@Component
public class PropiedadDao {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JdbcTemplate MySQLTemplate;

    public Propiedad buscaPropiedadPorId(int id) {
        Propiedad objeto = new Propiedad();
        String query = "SELECT * FROM PROPIEDADES WHERE ID = ?";

        try {
            objeto = MySQLTemplate.queryForObject(query, new PropiedadMapper(), id);
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | buscaPropiedadPorId | " + e);
        }

        return objeto;
    }

    public List<Propiedad> listaPropiedades() {
        List<Propiedad> lista_propiedades = new ArrayList<Propiedad>();
        String query = "SELECT * FROM PROPIEDADES ORDER BY CAST(PRECIO AS DECIMAL) DESC";

        try {
            lista_propiedades = MySQLTemplate.query(query, new PropiedadMapper());
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | listaPropiedades | " + e);
        }
        return lista_propiedades;
    }

    public List<Propiedad> listaPropiedadesDestacadas() {
        List<Propiedad> lista_propiedades_destacadas = new ArrayList<Propiedad>();
        String query = "SELECT * FROM PROPIEDADES WHERE ID BETWEEN 23 AND 30 ORDER BY CAST(PRECIO AS DECIMAL) DESC";

        try {
            lista_propiedades_destacadas = MySQLTemplate.query(query, new PropiedadMapper());
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | listaPropiedades_destacadas | " + e);
        }
        return lista_propiedades_destacadas;
    }

    public boolean grabaPropiedad(Propiedad objeto, int estados, int tipos, int[] porpietarios) {
        System.out.println("Entrando al metodo de Grabar Propiedad");
        boolean ok = false;

        String query = "SELECT ID FROM USUARIO WHERE ID = ?";

        int grabaPropietario = 0;

        if (porpietarios != null) {
            for (int propietarioId : porpietarios) {
                int propietario = MySQLTemplate.queryForObject(query, Integer.class, propietarioId);
                grabaPropietario += Integer.valueOf(propietario);
                System.out.println("PROPIETARIO GRABADO " + grabaPropietario);

            }
        }

        Object[] parametros = new Object[] {
                objeto.getPrecio(), tipos, objeto.getDescripcion(), objeto.getDimensiones(),
                objeto.getNum_wc(), objeto.getNum_habitaciones(), objeto.getDireccion(),
                estados, grabaPropietario, objeto.getImagen()
        };

        query = "INSERT INTO PROPIEDADES(PRECIO, TIPO, DESCRIPCION, DIMENSIONES ,NUM_WC, NUM_HABITACIONES, DIRECCION, ESTADO_PROPIEDAD, PROPIETARIO, IMAGEN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (MySQLTemplate.update(query, parametros) >= 1) {
            query = "SELECT ID FROM PROPIEDADES WHERE PROPIETARIO = ?";
            System.out.println("3");

            parametros = new Object[] {
                    objeto.getPropietario()
            };

            System.out.println("Parametros " + parametros.length);

            ok = true;
        }

        return ok;
    }

    public boolean editarPropiedad(Propiedad objeto) {
        boolean ok = false;

        System.out.println("Entrado en Editar propiedadDao ");

        String query = "UPDATE PROPIEDADES SET PRECIO = ?, TIPO = ?, DESCRIPCION = ?, DIMENSIONES = ?, NUM_WC = ?, NUM_HABITACIONES = ?, DIRECCION = ?, ESTADO_PROPIEDAD = ?, PROPIETARIO = ? WHERE ID = ?";
        int rowsUpdated = MySQLTemplate.update(query, objeto.getPrecio(), objeto.getTipo(),
                objeto.getDescripcion(), objeto.getDimensiones(), objeto.getNum_wc(), objeto.getNum_habitaciones(),
                objeto.getDireccion(), objeto.getEstado_Propiedad(), objeto.getPropietario(),
                objeto.getId());
        if (rowsUpdated >= 1) {
            ok = true;
        }
        ok = true;
        return ok;
    }

    public boolean bajaPropiedad(int propiedadId) {
        boolean ok = false;
        String query = "UPDATE PROPIEDAD SET ESTADO = FALSE WHERE ID = ?";

        try {
            if (MySQLTemplate.update(query, propiedadId) >= 1) {
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | bajaPropiedad | " + e);
        }

        return ok;
    }

    public boolean cambiarEtadoPropiedad(int propiedadId) {
        boolean ok = false;

        String query = "SELECT ESTADO FROM PROPIEDAD WHERE ID = ?";
        String estado = MySQLTemplate.queryForObject(query, String.class, propiedadId);

        if (estado == "1") {
            estado = "2";
        } else {
            estado = "1";
        }

        Object[] parametros = new Object[] {
                estado, propiedadId
        };

        query = "UPDATE PROPIEDAD SET ESTADO = ? WHERE ID = ?";

        try {
            if (MySQLTemplate.update(query, parametros) >= 1) {
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | activarPropiedad | " + e);
        }
        return ok;
    }

    public boolean borrarPropiedadPorId(int id) {
        boolean ok = false;
        String query = "DELETE FROM PROPIEDADES WHERE ID = ?";

        System.out.println("BORRAR DAO " + id);

        try {
            if (MySQLTemplate.update(query, id) >= 1) {
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadDao | borrarPropiedad | " + e);
        }

        return ok;
    }

    public HashMap<Integer, TipoInmueble> mapaTipoInmueble() {
        HashMap<Integer, TipoInmueble> tipoInmueble = new HashMap<Integer, TipoInmueble>();
        List<TipoInmueble> listaInmuebles = new ArrayList<TipoInmueble>();

        String query = "SELECT * FROM TIPO_INMUEBLE";
        try {
            listaInmuebles = MySQLTemplate.query(query, new TipoImuebleMapper());
            for (TipoInmueble tipo : listaInmuebles) {
                tipoInmueble.put(tipo.getId(), tipo);
            }
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadesDao | mapaTipoInmueble | " + e);
        }

        return tipoInmueble;
    }

    public HashMap<Integer, Estado_Propiedades> mapaEstadoInmueble() {
        HashMap<Integer, Estado_Propiedades> estadoInmueble = new HashMap<Integer, Estado_Propiedades>();
        List<Estado_Propiedades> listaEstados = new ArrayList<Estado_Propiedades>();

        String query = "SELECT * FROM ESTADO_PROPIEDADES";
        try {
            listaEstados = MySQLTemplate.query(query, new EstadoPropiedadMapper());
            for (Estado_Propiedades tipo : listaEstados) {
                estadoInmueble.put(tipo.getId(), tipo);
            }
        } catch (Exception e) {
            System.out.println("ERROR : PropiedadesDao | mapaEstadoInmueble | " + e);
        }

        return estadoInmueble;
    }

}
