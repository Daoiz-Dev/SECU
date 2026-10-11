package DAO;

import Datos.*;
import Persistencia.ConexionBD;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean guardarUsuario(Usuario usuario, String tipoRol) {
        String sqlUsuario = "INSERT INTO \"PROYECTO\".Usuarios "
                + "(correo, ci, nombre, apellido, contrasenia_hash, estado_usuario, id_domicilio, id_nacionalidad) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1, 1) RETURNING id_usuario";

        String sqlRol = "SELECT id_rol FROM \"PROYECTO\".Roles WHERE nombre_rol = ?";

        Connection con = null;
        try {
            con = ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false); // Iniciar transacción explícita

            // 1. Insertar en la tabla Usuarios
            int idGenerado = -1;
            try (PreparedStatement pstUser = con.prepareStatement(sqlUsuario)) {
                pstUser.setString(1, usuario.getCorreo());
                pstUser.setString(2, usuario.getCedula());
                pstUser.setString(3, usuario.getNombre());
                pstUser.setString(4, usuario.getApellido());
                pstUser.setString(5, usuario.getContrasenia());
                pstUser.setBoolean(6, usuario.getEstado() != null ? usuario.getEstado() : true);

                try (ResultSet rs = pstUser.executeQuery()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                        usuario.setIdUsuario(idGenerado);
                    }
                }
            }

            // 2. Obtener el ID del rol
            int idRol = -1;
            try (PreparedStatement pstRol = con.prepareStatement(sqlRol)) {
                pstRol.setString(1, tipoRol.toUpperCase());
                try (ResultSet rsRol = pstRol.executeQuery()) {
                    if (rsRol.next()) {
                        idRol = rsRol.getInt("id_rol");
                    }
                }
            }

            // 3. Insertar en la tabla especifica según el rol
            if (tipoRol.equalsIgnoreCase("ADMINISTRADOR")) {
                String sqlAdmin = "INSERT INTO \"PROYECTO\".Administradores (id_usuario, id_rol) VALUES (?, ?)";
                try (PreparedStatement pstAdmin = con.prepareStatement(sqlAdmin)) {
                    pstAdmin.setInt(1, idGenerado);
                    pstAdmin.setInt(2, idRol);
                    pstAdmin.executeUpdate();
                }
            } else if (tipoRol.equalsIgnoreCase("EVALUADOR_EXTERNO")) {
                String sqlEval = "INSERT INTO \"PROYECTO\".Evaluadores_Externos (id_usuario, id_rol) VALUES (?, ?)";
                try (PreparedStatement pstEval = con.prepareStatement(sqlEval)) {
                    pstEval.setInt(1, idGenerado);
                    pstEval.setInt(2, idRol);
                    pstEval.executeUpdate();
                }
            }

            con.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Deshacer cambios en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error al guardar el usuario: " + e.getMessage());
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Restablecer autoCommit
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Usuario buscarPorCedula(String cedula) {
        String sql = "SELECT u.id_usuario, u.ci, u.nombre, u.apellido, u.correo, u.contrasenia_hash, u.estado_usuario, r.nombre_rol "
                + "FROM \"PROYECTO\".Usuarios u "
                + "LEFT JOIN \"PROYECTO\".Administradores a ON u.id_usuario = a.id_usuario "
                + "LEFT JOIN \"PROYECTO\".Evaluadores_Externos e ON u.id_usuario = e.id_usuario "
                + "LEFT JOIN \"PROYECTO\".Roles r ON r.id_rol = COALESCE(a.id_rol, e.id_rol) "
                + "WHERE u.ci = ?";

        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cedula);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return UsuarioFactory.crearUsuario(
                            rs.getString("nombre_rol"),
                            rs.getInt("id_usuario"),
                            rs.getString("ci"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("contrasenia_hash"),
                            rs.getBoolean("estado_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por cédula: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean cambiarEstadoUsuario(Integer idUsuario, boolean nuevoEstado) {
        String sql = "UPDATE \"PROYECTO\".Usuarios SET estado_usuario = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setBoolean(1, nuevoEstado);
            pst.setInt(2, idUsuario);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al cambiar estado del usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarContrasenia(Integer idUsuario, String nuevaContrasenia) {
        String sql = "UPDATE \"PROYECTO\".Usuarios SET contrasenia_hash = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nuevaContrasenia);
            pst.setInt(2, idUsuario);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar contraseña: " + e.getMessage());
            return false;
        }
    }
}
