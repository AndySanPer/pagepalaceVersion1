package Procesos;

import DAO.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Fechas {

    Connection con; //objeto mysql
    PreparedStatement stmt; //objeto mysql
    ResultSet tabla; //objeto mysql
    int sw = 0;//bandera
    String sql = "";
    //conexion mycon;//objeto del paquete DAO
    conexion mycon = new conexion();
    String fecha = "";

    public void Fechas() {
        con = mycon.conecta();

        fecha = JOptionPane.showInputDialog("Ingresa la fecha");
        sw = 0;
        sql = "SELECT prestamos.id_libro, libros.titulo FROM prestamos JOIN libros ON prestamos.id_libro = libros.id_libro ";
        sql += "WHERE prestamos.FecDevolucion = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, fecha); // Establecer el valor del parámetro fecha
            tabla = stmt.executeQuery();

            String resultado = "";
            while (tabla.next()) {
                sw = 1;
                int idLibro = tabla.getInt("id_libro");
                String tituloLibro = tabla.getString("titulo");

                resultado += "ID_Libro: " + idLibro + "\t" + "Titulo: " + tituloLibro + "\n";

            }
            Menuprincipal mymenu = new Menuprincipal();
            mymenu.darformamenu();

            if (sw == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados para la fecha: " + fecha);
            } else {
                JOptionPane.showMessageDialog(null, resultado);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        Fechas myfecha = new Fechas();
        myfecha.Fechas();
    }

}
