package Procesos;

import javax.swing.*;
import java.sql.*;
import DAO.conexion;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ds.desktop.notify.*;
import javax.swing.JComboBox;

public class Libros {

    JFrame Formulariolibros;

    JLabel ID_Libro;
    JLabel Titulo;
    JLabel ID_Autor;
    JLabel Editorial;
    JLabel Genero;
    JLabel AñoPublicacion;
    JLabel NoEjemplares;
    JLabel Idioma;

    JComboBox<String> IdiomascomboBox = new JComboBox<>();

    JTextField txtID_Libro;
    JTextField txtTitulo;
    JTextField txtID_Autor;
    JTextField txtnombreautor;
    JTextField txtapellidoautor;
    JTextField txtEditorial;
    JTextField txtGenero;
    JTextField txtAñoPublicacion;
    JTextField txtNoEjemplares;

    JPanel panelID_Libro;
    JPanel panelTitulo;
    JPanel panelID_Autor;
    JPanel panelEditorial;
    JPanel panelGenero;
    JPanel panelAñoPublicacion;
    JPanel panelNoEjemplares;
    JPanel panelIdiomascomboBox;
    JPanel panelBotones;

    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0, swnom = 0, swape = 0;
    String sql = "";
    conexion mycon = new conexion();

    Libros() {
        con = mycon.conecta();

        Formulariolibros = new JFrame("Libros");

        ID_Libro = new JLabel("ID_Libro");
        Titulo = new JLabel("Titulo");
        ID_Autor = new JLabel("ID_Autor");
        Editorial = new JLabel("Editorial");
        Genero = new JLabel("Genero");
        AñoPublicacion = new JLabel("Año de Publicación");
        NoEjemplares = new JLabel("No.Ejemplares");
        Idioma = new JLabel("Idioma");

        txtID_Libro = new JTextField(25);
        txtTitulo = new JTextField(25);
        txtID_Autor = new JTextField(8);
        txtnombreautor = new JTextField(8);
        txtapellidoautor = new JTextField(8);
        txtEditorial = new JTextField(25);
        txtGenero = new JTextField(25);
        txtAñoPublicacion = new JTextField(25);
        txtNoEjemplares = new JTextField(25);

        IdiomascomboBox.addItem(null);
        IdiomascomboBox.addItem("Español");
        IdiomascomboBox.addItem("IdiomaOriginal");
        IdiomascomboBox.addItem("Ambos");

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");

        panelID_Libro = new JPanel();
        panelTitulo = new JPanel();
        panelID_Autor = new JPanel();
        panelEditorial = new JPanel();
        panelGenero = new JPanel();
        panelAñoPublicacion = new JPanel();
        panelNoEjemplares = new JPanel();
        panelIdiomascomboBox = new JPanel();
        panelBotones = new JPanel();

    }

    public void darforma() {
        Formulariolibros.setLayout(new GridLayout(9, 1));

        panelID_Libro.add(ID_Libro);
        panelID_Libro.add(txtID_Libro);

        panelTitulo.add(Titulo);
        panelTitulo.add(txtTitulo);

        panelID_Autor.add(ID_Autor);
        panelID_Autor.add(txtID_Autor);
        panelID_Autor.add(txtnombreautor);
        panelID_Autor.add(txtapellidoautor);

        panelEditorial.add(Editorial);
        panelEditorial.add(txtEditorial);

        panelGenero.add(Genero);
        panelGenero.add(txtGenero);

        panelAñoPublicacion.add(AñoPublicacion);
        panelAñoPublicacion.add(txtAñoPublicacion);

        panelNoEjemplares.add(NoEjemplares);
        panelNoEjemplares.add(txtNoEjemplares);

        panelIdiomascomboBox.add(Idioma);
        panelIdiomascomboBox.add(IdiomascomboBox);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnsalir);

        Formulariolibros.add(panelID_Libro);
        Formulariolibros.add(panelTitulo);
        Formulariolibros.add(panelID_Autor);
        Formulariolibros.add(panelEditorial);
        Formulariolibros.add(panelGenero);
        Formulariolibros.add(panelAñoPublicacion);
        Formulariolibros.add(panelNoEjemplares);
        Formulariolibros.add(panelIdiomascomboBox);
        Formulariolibros.add(panelBotones);

        Formulariolibros.setVisible(true);
        Formulariolibros.pack();
        Formulariolibros.setLocationRelativeTo(null);

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Libro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Libro.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO ES REQUERIDO", 1, 3000);
                } else {
                    txtTitulo.requestFocusInWindow();
                }
            }
        });

        txtTitulo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtTitulo.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL TITULO DEL LIBRO ES REQUERIDO", 1, 3000);
                } else {
                    txtID_Autor.requestFocusInWindow();
                }
            }
        });

        txtID_Autor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Autor.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL AUTOR ES REQUERIDO", 1, 3000);
                } else {
                    consultarnombre();
                    consultarapellido();
                    if (swnom == 0) {
                        txtID_Autor.setText("");

                    }
                    if (swape == 0) {
                        txtID_Autor.setText("");
                    } else {
                        txtEditorial.requestFocusInWindow();

                    }

                }
            }
        });

        txtEditorial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtEditorial.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA EDITORIAL ES REQUERIDA", 1, 3000);
                } else {
                    txtGenero.requestFocusInWindow();
                }
            }
        });

        txtGenero.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtGenero.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL GENERO DEL LIBRO ES REQUERIDO", 1, 3000);
                } else {
                    txtAñoPublicacion.requestFocusInWindow();
                }
            }
        });

        txtAñoPublicacion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtAñoPublicacion.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL AÑO DE PUBLICACION ES REQUERIDO", 1, 3000);
                } else {
                    txtNoEjemplares.requestFocusInWindow();
                }
            }
        });

        txtNoEjemplares.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtNoEjemplares.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL TOTAL DE EJEMPLARES ES REQUERIDO", 1, 3000);
                } else {
                    IdiomascomboBox.requestFocusInWindow();
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

        txtID_Libro.setText(vacio);
        txtTitulo.setText(vacio);
        txtID_Autor.setText(vacio);
        txtnombreautor.setText(vacio);
        txtapellidoautor.setText(vacio);
        txtEditorial.setText(vacio);
        txtGenero.setText(vacio);
        txtAñoPublicacion.setText(vacio);
        txtNoEjemplares.setText(vacio);
        IdiomascomboBox.setSelectedItem(null);
        txtID_Libro.requestFocusInWindow();

    }

    public void guadar() {

        sql = "insert into libros values (";
        sql += "\"" + txtID_Libro.getText() + "\" , ";
        sql += "\"" + txtTitulo.getText() + "\" , ";
        sql += "\"" + txtID_Autor.getText() + "\" , ";
        sql += "\"" + txtEditorial.getText() + "\" , ";
        sql += "\"" + txtGenero.getText() + "\" , ";
        sql += "\"" + txtAñoPublicacion.getText() + "\" , ";
        sql += "\"" + txtNoEjemplares.getText() + "\" , ";
        sql += "\"" + IdiomascomboBox.getSelectedItem().toString() + "\" ) ";
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
        sql = "select * from libros ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtTitulo.setText(tabla.getString(2));
                txtID_Autor.setText(tabla.getString(3));
                txtEditorial.setText(tabla.getString(4));
                txtGenero.setText(tabla.getString(5));
                txtAñoPublicacion.setText(tabla.getString(6));
                txtNoEjemplares.setText(tabla.getString(7));
                IdiomascomboBox.setSelectedItem(tabla.getString(8));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombreautor.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtapellidoautor.setText(tabla.getString(3));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
        }

    }

    public void consultarnombre() {

        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swnom = 1;
                txtnombreautor.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void consultarapellido() {
        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swape = 1;
                txtapellidoautor.setText(tabla.getString(3));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void modificar() {
        sql = "update libros set ";
        sql += "Titulo='" + txtTitulo.getText() + "', ";
        sql += "ID_Autor='" + txtID_Autor.getText() + "', ";
        sql += "Editorial='" + txtEditorial.getText() + "', ";
        sql += " Genero='" + txtGenero.getText() + "', ";
        sql += "AñoPublicacion='" + txtAñoPublicacion.getText() + "', ";
        sql += " NoEjemplares='" + txtNoEjemplares.getText() + "', ";
        sql += "Idioma='" + IdiomascomboBox.getSelectedItem().toString() + "' ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";
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
        sql = "delete from libros ";
        sql += " where id_libro='" + txtID_Libro.getText() + "'";
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
                Formulariolibros.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    public static void main(String[] args) {
        Libros mylibro = new Libros();
        mylibro.darforma();
        

    }
}
