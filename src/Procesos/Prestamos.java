package Procesos;

import javax.swing.*;
import java.sql.*;
import DAO.conexion;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ds.desktop.notify.*;
import javax.swing.JComboBox;

public class Prestamos {

    JFrame Formularioprestamos;

    JLabel ID_Prestamo;
    JLabel ID_Libro;
    JLabel ID_Lector;
    JLabel fecInicioPrestamo;
    JLabel FecDevolucion;
    JLabel Estado;

    JTextField txtID_Prestamo;

    JTextField txtID_Libro;
    JTextField txtnombrelibro;

    JTextField txtID_Lector;
    JTextField txtnombrelector;

    JTextField txtfecInicioPrestamo;
    JTextField txtFecDevolucion;

    JComboBox<String> EstadocomboBox = new JComboBox<>();

    JPanel panelID_Prestamo;
    JPanel panelID_Libro;
    JPanel panelID_Lector;
    JPanel panelfecInicioPrestamo;
    JPanel panelFecDevolucion;
    JPanel panelEstado;
    JPanel panelBotones;

    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0, swlec = 0, swlib = 0;
    String sql = "";
    conexion mycon = new conexion();

    Prestamos() {
        con = mycon.conecta();

        Formularioprestamos = new JFrame("Prestamos");

        ID_Prestamo = new JLabel("ID_Prestamos");
        ID_Libro = new JLabel("ID_Libro");
        ID_Lector = new JLabel("ID_Lector");
        fecInicioPrestamo = new JLabel("Fecha de inicio");
        FecDevolucion = new JLabel("Fecha de termino");
        Estado = new JLabel("Estado");

        txtID_Prestamo = new JTextField(25);
        txtID_Libro = new JTextField(8);
        txtnombrelibro = new JTextField(25);
        txtID_Lector = new JTextField(8);
        txtnombrelector = new JTextField(25);
        txtfecInicioPrestamo = new JTextField(25);
        txtFecDevolucion = new JTextField(25);

        EstadocomboBox.addItem(null);
        EstadocomboBox.addItem("Prestado");
        EstadocomboBox.addItem("Devuelto");
        EstadocomboBox.addItem("Vencido");

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");

        panelID_Prestamo = new JPanel();
        panelID_Libro = new JPanel();
        panelID_Lector = new JPanel();
        panelfecInicioPrestamo = new JPanel();
        panelFecDevolucion = new JPanel();
        panelEstado = new JPanel();
        panelBotones = new JPanel();

    }

    public void darforma() {
        Formularioprestamos.setLayout(new GridLayout(7, 1));

        panelID_Prestamo.add(ID_Prestamo);
        panelID_Prestamo.add(txtID_Prestamo);

        panelID_Libro.add(ID_Libro);
        panelID_Libro.add(txtID_Libro);
        panelID_Libro.add(txtnombrelibro);

        panelID_Lector.add(ID_Lector);
        panelID_Lector.add(txtID_Lector);
        panelID_Lector.add(txtnombrelector);

        panelfecInicioPrestamo.add(fecInicioPrestamo);
        panelfecInicioPrestamo.add(txtfecInicioPrestamo);

        panelFecDevolucion.add(FecDevolucion);
        panelFecDevolucion.add(txtFecDevolucion);

        panelEstado.add(Estado);
        panelEstado.add(EstadocomboBox);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnsalir);

        Formularioprestamos.add(panelID_Prestamo);
        Formularioprestamos.add(panelID_Libro);
        Formularioprestamos.add(panelID_Lector);
        Formularioprestamos.add(panelfecInicioPrestamo);
        Formularioprestamos.add(panelFecDevolucion);
        Formularioprestamos.add(panelEstado);
        Formularioprestamos.add(panelBotones);

        Formularioprestamos.setVisible(true);
        Formularioprestamos.pack();
        Formularioprestamos.setLocationRelativeTo(null);

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Prestamo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Prestamo.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL PRESTAMO ES REQUERIDO", 1, 3000);
                } else {
                    txtID_Libro.requestFocusInWindow();
                }
            }
        });

        txtID_Libro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Libro.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO ES REQUERIDO", 1, 3000);
                } else {
                    consultarnombrelibro();
                    if (swlib == 0) {
                        txtID_Libro.setText("");
                    } else {
                        txtID_Lector.requestFocusInWindow();

                    }
                }
            }
        });

        txtID_Lector.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Lector.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    consultarnombrelector();
                    if (swlec == 0) {
                        txtID_Lector.setText("");
                    } else {
                        txtfecInicioPrestamo.requestFocusInWindow();

                    }

                }
            }
        });

        txtfecInicioPrestamo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtfecInicioPrestamo.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DEL PRESTAMO ES REQUERIDA", 1, 3000);
                } else {
                    txtFecDevolucion.requestFocusInWindow();
                }
            }
        });

        txtFecDevolucion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtFecDevolucion.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DE DEVOLUCIÓN DEL PRESTAMO ES REQUERIDA", 1, 3000);
                } else {
                    EstadocomboBox.requestFocusInWindow();
                }
            }
        });
        //CAJAS DE ESCUCHA DE LOS BOTONES 
        btnnuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nuevo();
            }
        });

        btnguardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guadar();
            }
        });

        btnmodificar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });

        btnborrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                borrar();
            }
        });

        btnconsultar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });

        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

    }

    public void nuevo() {
        String vacio = "";

        txtID_Prestamo.setText(vacio);
        txtID_Libro.setText(vacio);
        txtnombrelibro.setText(vacio);
        txtID_Lector.setText(vacio);
        txtnombrelector.setText(vacio);
        txtfecInicioPrestamo.setText(vacio);
        txtFecDevolucion.setText(vacio);
        EstadocomboBox.setSelectedItem(null);
        txtID_Prestamo.requestFocusInWindow();

    }

    public void guadar() {

        sql = "insert into prestamos values (";
        sql += "\"" + txtID_Prestamo.getText() + "\" , ";
        sql += "\"" + txtID_Libro.getText() + "\" , ";
        sql += "\"" + txtID_Lector.getText() + "\" , ";
        sql += "\"" + txtfecInicioPrestamo.getText() + "\" , ";
        sql += "\"" + txtFecDevolucion.getText() + "\" , ";
        sql += "\"" + EstadocomboBox.getSelectedItem().toString() + "\" ) ";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);
            sw = stmt.executeUpdate();
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO DADO DEL ALTA CON EXITO", 7, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void consultar() {
        sw = 0;
        sql = "select * from prestamos ";
        sql += "where id_prestamo='" + txtID_Prestamo.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtID_Libro.setText(tabla.getString(2));
                txtID_Lector.setText(tabla.getString(3));
                txtfecInicioPrestamo.setText(tabla.getString(4));
                txtFecDevolucion.setText(tabla.getString(5));
                EstadocomboBox.setSelectedItem(tabla.getString(6));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        sw = 0;
        sql = "select * from libros ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombrelibro.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        sw = 0;
        sql = "select * from lectores ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombrelector.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
        }

    }

    public void consultarnombrelibro() {

        sw = 0;
        sql = "select * from libros ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swlib = 1;
                txtnombrelibro.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void consultarnombrelector() {
        sw = 0;
        sql = "select * from lectores ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swlec = 1;
                txtnombrelector.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void modificar() {
        sql = "update prestamos set ";
        sql += " ID_Libro='" + txtID_Libro.getText() + "', ";
        sql += " ID_Lector='" + txtID_Lector.getText() + "', ";
        sql += " FecInicioPrestamo ='" + txtfecInicioPrestamo.getText() + "', ";
        sql += " FecDevolucion='" + txtFecDevolucion.getText() + "', ";
        sql += " Estado='" + EstadocomboBox.getSelectedItem().toString() + "' ";
        sql += "where id_prestamo='" + txtID_Prestamo.getText() + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO MODIFICADO CON EXITO", 6, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void borrar() {
        sql = "delete from prestamos ";
        sql += " where id_prestamo='" + txtID_Libro.getText() + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO BORRADO CON EXITO", 7, 3000);
            }
            nuevo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void salir() {
        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Formularioprestamos.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    public static void main(String[] args) {
        Prestamos myprestamo = new Prestamos();
        myprestamo.darforma();
    }
}
