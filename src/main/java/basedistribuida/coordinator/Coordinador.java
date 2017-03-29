/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.coordinator;

import basedistribuida.beans.InformacionPersona;
import basedistribuida.connection.Connection;
import basedistribuida.coordinator.Nodo.Maquina;
import basedistribuida.model.Colonia;
import basedistribuida.model.Direccion;
import basedistribuida.model.Estado;
import basedistribuida.model.Estado.Zona;
import basedistribuida.model.EstadoNodoReplica;
import basedistribuida.model.Municipio;
import basedistribuida.model.Persona;
import basedistribuida.model.controller.ColoniaCtrl;
import basedistribuida.model.controller.DireccionCtrl;
import basedistribuida.model.controller.EstadoCtrl;
import basedistribuida.model.controller.EstadoNodoReplicaCtrl;
import basedistribuida.model.controller.MunicipioCtrl;
import basedistribuida.model.controller.PersonaCtrl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author USUARIO
 */
public class Coordinador {

    private List<Nodo> nodos;
    private boolean REPLICA_MAQUINA1;
    private boolean REPLICA_MAQUINA2;
    private boolean REPLICA_MAQUINA3;
    private boolean REPLICA_MAQUINA4;

    public Coordinador() {
        iniciarNodos();
        REPLICA_MAQUINA1 = false;
        REPLICA_MAQUINA2 = false;
        REPLICA_MAQUINA3 = false;
        REPLICA_MAQUINA4 = false;
    }

    private void iniciarNodos() {
        nodos = new ArrayList<>();
        for (Maquina maquina : Nodo.Maquina.values()) {
            nodos.add(new Nodo(maquina));
        }
    }

    //Algoritmo de fragmentacion
    //Maquina 1: Tablas de estados, municipios y colonias en maquina de catalogos //Replica de personas zona sur
    //Maquina 2: Personas, direccion fragmentada de forma horizontal que vivan en zona sur //Replica de personas de zona centro
    //Maquina 3: Personas, direccion fragmentada de forma horizontal que vivan en zona centro //Replica de personas de zona norte
    //Maquina 4: Personas, direccion fragmentada de forma horizontal que vivan en zona norte //Replica de catalogos
    private Nodo getNodo(Maquina maquina) {
        for (Nodo nodo : nodos) {
            if (nodo.getMaquina() == maquina) {
                return nodo;
            }
        }
        return null;
    }

    public List<Estado> obtenerEstados() {
        List<Estado> estados = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                estados = new EstadoCtrl(nodo.getConexionReplica()).obtenerTodos();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                estados = new EstadoCtrl(nodo.getConexion()).obtenerTodos();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                estados = new EstadoCtrl(nodo.getConexionReplica()).obtenerTodos();
                REPLICA_MAQUINA1 = true;
            }
        }

        return estados;
    }

    public Estado obtenerEstadoById(int id) {
        Estado estado = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                estado = new EstadoCtrl(nodo.getConexionReplica()).findEstadoById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                estado = new EstadoCtrl(nodo.getConexion()).findEstadoById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                estado = new EstadoCtrl(nodo.getConexionReplica()).findEstadoById(id);
                REPLICA_MAQUINA1 = true;
            }
        }
        return estado;
    }

    public Municipio obtenerMunicipioById(int id) {
        Municipio municipio = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                municipio = new MunicipioCtrl(nodo.getConexionReplica()).findMunicipioById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                municipio = new MunicipioCtrl(nodo.getConexion()).findMunicipioById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                municipio = new MunicipioCtrl(nodo.getConexionReplica()).findMunicipioById(id);
                REPLICA_MAQUINA1 = true;
            }
        }

        return municipio;
    }

    public List<Municipio> obtenerMunicipiosByEstado(int idEstado) {
        List<Municipio> municipios = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                municipios = new MunicipioCtrl(nodo.getConexionReplica()).obtenerTodosPorEstado(idEstado);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                municipios = new MunicipioCtrl(nodo.getConexion()).obtenerTodosPorEstado(idEstado);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                municipios = new MunicipioCtrl(nodo.getConexionReplica()).obtenerTodosPorEstado(idEstado);
                REPLICA_MAQUINA1 = true;
            }
        }

        return municipios;
    }

    public List<Colonia> obtenerColoniasByMunicipio(int idMunicipio) {
        List<Colonia> colonias = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                colonias = new ColoniaCtrl(nodo.getConexionReplica()).obtenerTodosPorMunicipio(idMunicipio);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                colonias = new ColoniaCtrl(nodo.getConexion()).obtenerTodosPorMunicipio(idMunicipio);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                colonias = new ColoniaCtrl(nodo.getConexionReplica()).obtenerTodosPorMunicipio(idMunicipio);
                REPLICA_MAQUINA1 = true;
            }
        }

        return colonias;
    }

    public Colonia obtenerColoniaById(int id) {
        Colonia colonia = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                colonia = new ColoniaCtrl(nodo.getConexionReplica()).findColoniaById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                colonia = new ColoniaCtrl(nodo.getConexion()).findColoniaById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                colonia = new ColoniaCtrl(nodo.getConexionReplica()).findColoniaById(id);
                REPLICA_MAQUINA1 = true;
            }
        }

        return colonia;
    }

    public Estado insertarEstado(Estado estado) {
        Estado edo = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                edo = new EstadoCtrl(nodo.getConexionReplica()).saveEstado(estado);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                edo = new EstadoCtrl(nodo.getConexion()).saveEstado(estado);
                try {
                    new EstadoCtrl(nodo.getConexionReplica()).saveEstado(estado);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                edo = new EstadoCtrl(nodo.getConexionReplica()).saveEstado(estado);
                REPLICA_MAQUINA1 = true;
            }
        }

        return edo;
    }

    public Municipio insertarMunicipio(Municipio municipio) {
        Municipio m = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                m = new MunicipioCtrl(nodo.getConexionReplica()).saveMunicipio(municipio);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                m = new MunicipioCtrl(nodo.getConexion()).saveMunicipio(municipio);
                try {
                    new MunicipioCtrl(nodo.getConexionReplica()).saveMunicipio(municipio);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                m = new MunicipioCtrl(nodo.getConexionReplica()).saveMunicipio(municipio);
                REPLICA_MAQUINA1 = true;
            }
        }

        return m;
    }

    public Colonia insertarColonia(Colonia colonia) {
        Colonia c = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                c = new ColoniaCtrl(nodo.getConexionReplica()).saveColonia(colonia);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                c = new ColoniaCtrl(nodo.getConexion()).saveColonia(colonia);
                try {
                    new ColoniaCtrl(nodo.getConexionReplica()).saveColonia(colonia);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                c = new ColoniaCtrl(nodo.getConexionReplica()).saveColonia(colonia);
                REPLICA_MAQUINA1 = true;
            }
        }

        return c;
    }

    public boolean updateEstado(Estado estado) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new EstadoCtrl(nodo.getConexionReplica()).updateEstado(estado);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new EstadoCtrl(nodo.getConexion()).updateEstado(estado);
                try {
                    new EstadoCtrl(nodo.getConexionReplica()).updateEstado(estado);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new EstadoCtrl(nodo.getConexionReplica()).updateEstado(estado);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    public boolean updateMunicipio(Municipio municipio) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new MunicipioCtrl(nodo.getConexionReplica()).updateMunicipio(municipio);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new MunicipioCtrl(nodo.getConexion()).updateMunicipio(municipio);
                try {
                    new MunicipioCtrl(nodo.getConexionReplica()).updateMunicipio(municipio);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new MunicipioCtrl(nodo.getConexionReplica()).updateMunicipio(municipio);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    public boolean updateColonia(Colonia colonia) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new ColoniaCtrl(nodo.getConexionReplica()).updateColonia(colonia);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new ColoniaCtrl(nodo.getConexion()).updateColonia(colonia);
                try {
                    new ColoniaCtrl(nodo.getConexionReplica()).updateColonia(colonia);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new ColoniaCtrl(nodo.getConexionReplica()).updateColonia(colonia);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    //Borrar Estados
    public boolean borrarEstado(Estado estado) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new EstadoCtrl(nodo.getConexionReplica()).deleteEstado(estado);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new EstadoCtrl(nodo.getConexion()).deleteEstado(estado);
                try {
                    new EstadoCtrl(nodo.getConexionReplica()).deleteEstado(estado);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new EstadoCtrl(nodo.getConexionReplica()).deleteEstado(estado);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    //Borrar Municipios
    public boolean borrarMunicipio(Municipio municipio) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new MunicipioCtrl(nodo.getConexionReplica()).deleteMunicipio(municipio);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new MunicipioCtrl(nodo.getConexion()).deleteMunicipio(municipio);
                try {
                    new MunicipioCtrl(nodo.getConexionReplica()).deleteMunicipio(municipio);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new MunicipioCtrl(nodo.getConexionReplica()).deleteMunicipio(municipio);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    //Borrar Colonias
    public boolean borrarColonia(Colonia colonia) {
        boolean result = false;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        validarReplicaCatalogos(nodo);

        if (nodo.getConexion() == null || REPLICA_MAQUINA1) {
            try {
                REPLICA_MAQUINA1 = true;
                result = new ColoniaCtrl(nodo.getConexionReplica()).deleteColonia(colonia);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                result = new ColoniaCtrl(nodo.getConexion()).deleteColonia(colonia);
                try {
                    new ColoniaCtrl(nodo.getConexionReplica()).deleteColonia(colonia);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                result = new ColoniaCtrl(nodo.getConexionReplica()).deleteColonia(colonia);
                REPLICA_MAQUINA1 = true;
            }
        }

        return result;
    }

    private void validarReplicaCatalogos(Nodo nodo) {
        if (REPLICA_MAQUINA1 && nodo.getConexionReplica() != null) {
            try {
                EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M1");
                if (estadoNodoReplica.getSincronizada() == 1) {
                    REPLICA_MAQUINA1 = false;
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        }
    }

    private Nodo validarNodo(Estado estado) {
        Nodo nodo;
        switch (estado.getZona()) {
            case Sur:
                nodo = getNodo(Maquina.MAQUINA_2);
                if (REPLICA_MAQUINA2 && nodo.getConexionReplica() != null) {
                    try {
                        EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M2");
                        if (estadoNodoReplica.getSincronizada() == 1) {
                            REPLICA_MAQUINA2 = false;
                        }
                    } catch (JDBCConnectionException ex) {
                        System.out.println("Se perdio la conexion a la replica");
                    }
                }
                break;
            case Centro:
                nodo = getNodo(Maquina.MAQUINA_3);
                if (REPLICA_MAQUINA3 && nodo.getConexionReplica() != null) {
                    try {
                        EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M3");
                        if (estadoNodoReplica.getSincronizada() == 1) {
                            REPLICA_MAQUINA3 = false;
                        }
                    } catch (JDBCConnectionException ex) {
                        System.out.println("Se perdio la conexion a la replica");
                    }
                }
                break;
            default:
                nodo = getNodo(Maquina.MAQUINA_4);
                if (REPLICA_MAQUINA4 && nodo.getConexionReplica() != null) {
                    try {
                        EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M4");
                        if (estadoNodoReplica.getSincronizada() == 1) {
                            REPLICA_MAQUINA4 = false;
                        }
                    } catch (JDBCConnectionException ex) {
                        System.out.println("Se perdio la conexion a la replica");
                    }
                }
                break;
        }
        return nodo;
    }

    public boolean insertarPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo = validarNodo(estado);
        int lastId = obtenerUltimoIdPersona();
        persona.setId(lastId + 1);
        if (nodo.getConexion() == null || REPLICA_MAQUINA2 || REPLICA_MAQUINA3 || REPLICA_MAQUINA4) {
            try {
                Persona p = new PersonaCtrl(nodo.getConexionReplica()).savePersona(persona);
                direccion.setIdPersona(p.getId());
                direccion.setId(p.getId());
                Direccion d = new DireccionCtrl(nodo.getConexionReplica()).saveDireccion(direccion);
                result = p != null && d != null;
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                Persona p = new PersonaCtrl(nodo.getConexion()).savePersona(persona);
                direccion.setIdPersona(p.getId());
                direccion.setId(p.getId());
                Direccion d = new DireccionCtrl(nodo.getConexion()).saveDireccion(direccion);
                try {
                    new PersonaCtrl(nodo.getConexionReplica()).savePersona(persona);
                    new DireccionCtrl(nodo.getConexionReplica()).saveDireccion(direccion);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                Persona p = new PersonaCtrl(nodo.getConexionReplica()).savePersona(persona);
                direccion.setIdPersona(p.getId());
                direccion.setId(p.getId());
                Direccion d = new DireccionCtrl(nodo.getConexionReplica()).saveDireccion(direccion);
                result = p != null && d != null;
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            }
        }

        return result;
    }

    public boolean editarPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo = validarNodo(estado);

        if (nodo.getConexion() == null || REPLICA_MAQUINA2 || REPLICA_MAQUINA3 || REPLICA_MAQUINA4) {
            try {
                new PersonaCtrl(nodo.getConexionReplica()).updatePersona(persona);
                new DireccionCtrl(nodo.getConexionReplica()).updateDireccion(direccion);
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                new PersonaCtrl(nodo.getConexion()).updatePersona(persona);
                new DireccionCtrl(nodo.getConexion()).updateDireccion(direccion);
                try {
                    new PersonaCtrl(nodo.getConexionReplica()).updatePersona(persona);
                    new DireccionCtrl(nodo.getConexionReplica()).updateDireccion(direccion);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                new PersonaCtrl(nodo.getConexionReplica()).updatePersona(persona);
                new DireccionCtrl(nodo.getConexionReplica()).updateDireccion(direccion);
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            }
        }

        return result;
    }

    //ObtenerPersonas
    public List<InformacionPersona> obtenerPersonas() {
        List<InformacionPersona> personas = null;
        Nodo nodo2 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo3 = getNodo(Maquina.MAQUINA_3);
        Nodo nodo4 = getNodo(Maquina.MAQUINA_4);

        personas = new ArrayList<>();
        if (nodo2.getConexion() == null || REPLICA_MAQUINA2) {
            try {
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo3.getConexion() == null || REPLICA_MAQUINA3) {
            try {
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo4.getConexion() == null || REPLICA_MAQUINA4) {
            try {
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo4.getConexion()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerTodos()) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        return personas;
    }
    //ObtenerPersonasPorEstado

    public List<InformacionPersona> obtenerPersonasByEstado(int idEstado) {
        List<InformacionPersona> personas = null;
        Nodo nodo2 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo3 = getNodo(Maquina.MAQUINA_3);
        Nodo nodo4 = getNodo(Maquina.MAQUINA_4);

        personas = new ArrayList<>();
        if (nodo2.getConexion() == null || REPLICA_MAQUINA2) {
            try {
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo3.getConexion() == null || REPLICA_MAQUINA3) {
            try {
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo4.getConexion() == null || REPLICA_MAQUINA4) {
            try {
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo4.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        return personas;
    }

    //ObtenerPersonasPorMunicipio
    public List<InformacionPersona> obtenerPersonasByMunicipio(int idMunicipio) {
        List<InformacionPersona> personas = null;
        Nodo nodo2 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo3 = getNodo(Maquina.MAQUINA_3);
        Nodo nodo4 = getNodo(Maquina.MAQUINA_4);

        personas = new ArrayList<>();
        if (nodo2.getConexion() == null || REPLICA_MAQUINA2) {
            try {
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo3.getConexion() == null || REPLICA_MAQUINA3) {
            try {
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo4.getConexion() == null || REPLICA_MAQUINA4) {
            try {
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo4.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        return personas;
    }

    //ObtenerPersonasPorColonia
    public List<InformacionPersona> obtenerPersonasByColonia(int idColonia) {
        List<InformacionPersona> personas = null;
        Nodo nodo2 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo3 = getNodo(Maquina.MAQUINA_3);
        Nodo nodo4 = getNodo(Maquina.MAQUINA_4);

        personas = new ArrayList<>();
        if (nodo2.getConexion() == null || REPLICA_MAQUINA2) {
            try {
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA2 = true;
                for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo3.getConexion() == null || REPLICA_MAQUINA3) {
            try {
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA3 = true;
                for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        if (nodo4.getConexion() == null || REPLICA_MAQUINA4) {
            try {
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo4.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("El nodo primario fallo");
                REPLICA_MAQUINA4 = true;
                for (Persona persona : new PersonaCtrl(nodo4.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                    Direccion direccion = new DireccionCtrl(nodo4.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            }
        }

        return personas;
    }

    public boolean borrarInformacionPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo = validarNodo(estado);

        if (nodo.getConexion() == null || REPLICA_MAQUINA2 || REPLICA_MAQUINA3 || REPLICA_MAQUINA4) {
            try {
                new PersonaCtrl(nodo.getConexionReplica()).deletePersona(persona);
                new DireccionCtrl(nodo.getConexionReplica()).deleteDireccion(direccion);
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                new PersonaCtrl(nodo.getConexion()).deletePersona(persona);
                new DireccionCtrl(nodo.getConexion()).deleteDireccion(direccion);
                try {
                    new PersonaCtrl(nodo.getConexionReplica()).deletePersona(persona);
                    new DireccionCtrl(nodo.getConexionReplica()).deleteDireccion(direccion);
                } catch (Exception e) {
                }
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                new PersonaCtrl(nodo.getConexionReplica()).deletePersona(persona);
                new DireccionCtrl(nodo.getConexionReplica()).deleteDireccion(direccion);
                switch (nodo.getMaquina()) {
                    case MAQUINA_2:
                        REPLICA_MAQUINA2 = true;
                        break;
                    case MAQUINA_3:
                        REPLICA_MAQUINA3 = true;
                        break;
                    default:
                        REPLICA_MAQUINA4 = true;
                        break;
                }
            }
        }

        return result;
    }

    //Metodos o forma de obtener ultimo id de persona y de direccion, aunque id de direccion y de persona deberia de ser el mismo siempre
    private int obtenerUltimoIdPersona() {
        int lastId = 0;
        Nodo nodo2 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo3 = getNodo(Maquina.MAQUINA_3);
        Nodo nodo4 = getNodo(Maquina.MAQUINA_4);
        
        int maq2Id = 0, maq3Id = 0, maq4Id = 0;
        if (nodo2.getConexion() == null || REPLICA_MAQUINA2) {
            try {
                REPLICA_MAQUINA2 = true;
                maq2Id = new PersonaCtrl(nodo2.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                maq2Id = new PersonaCtrl(nodo2.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a nodo primario");
                REPLICA_MAQUINA2 = true;
                maq2Id = new PersonaCtrl(nodo2.getConexionReplica()).getLastId();
            }
        }
        if (nodo3.getConexion() == null || REPLICA_MAQUINA3) {
            try {
                REPLICA_MAQUINA3 = true;
                maq3Id = new PersonaCtrl(nodo3.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                maq3Id = new PersonaCtrl(nodo3.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a nodo primario");
                REPLICA_MAQUINA3 = true;
                maq3Id = new PersonaCtrl(nodo3.getConexionReplica()).getLastId();
            }
        }
        if (nodo4.getConexion() == null || REPLICA_MAQUINA4) {
            try {
                REPLICA_MAQUINA4 = true;
                maq4Id = new PersonaCtrl(nodo4.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a la replica");
            }
        } else {
            try {
                maq4Id = new PersonaCtrl(nodo4.getConexionReplica()).getLastId();
            } catch (JDBCConnectionException ex) {
                System.out.println("Se perdio la conexion a nodo primario");
                REPLICA_MAQUINA2 = true;
                maq4Id = new PersonaCtrl(nodo4.getConexionReplica()).getLastId();
            }
        }        
        
        lastId = Math.max(maq2Id, Math.max(maq3Id, maq4Id));   
        return lastId;
    }

    public void cerrarConexiones() {
        for (Nodo nodo : nodos) {
            if (nodo.getConexion() != null) {
                Connection.dispose(nodo.getConexion());
            }
            if (nodo.getConexionReplica() != null) {
                Connection.dispose(nodo.getConexionReplica());
            }
        }
    }

}
