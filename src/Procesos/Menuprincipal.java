package Procesos;

import DAO.conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class Menuprincipal {

    JFrame formulariomenu;

    JMenu Catalogos;
    JMenu Procesos;
    JMenu Reportes;
    JMenu Consultas;
    JMenu Ayuda;

    JMenuItem cataLibros;
    JMenuItem cataAutores;
    JMenuItem cataLectores;
    JMenuItem cataSalir;

    JMenuItem proPrestamos;
    JMenuItem profechaDevo;

    JMenuItem repoLibros;
    JMenuItem repoAutores;
    JMenuItem repoLectores;
    JMenuItem repoPrestamos;

    JMenuItem consulprestamosactivos;
    JMenuItem consullibrosporgenero;
    JMenuItem consulprestamosvencidos;
    JMenuItem consulautoryobra;
    JMenuItem consullectoresinformacion;
    JMenuItem consullibrosporexistencias;

    JMenuItem ayudaf1;
    JMenuItem acercade;

    JSeparator js1;

    JMenuBar jbar;

    JLabel etiquetaimagen;
    ImageIcon imagen;

    int sw = 0;

    public Menuprincipal() {

        formulariomenu = new JFrame("Menu principal");

        imagen = new ImageIcon("E:\\respaldo andrea pc monche\\BD RICARDO\\pagepalace\\menu.JPG");
        etiquetaimagen = new JLabel();

        jbar = new JMenuBar();

        jbar = new JMenuBar();
        js1 = new JSeparator();

        //opciones
        Catalogos = new JMenu("Catalogos");
        Procesos = new JMenu("Procesos");
        Reportes = new JMenu("Reportes");
        Consultas = new JMenu("Consultas");
        Ayuda = new JMenu("Ayuda");

        //opciones catalogos
        cataLibros = new JMenuItem("Libros");
        cataAutores = new JMenuItem("Autores");
        cataLectores = new JMenuItem("Lectores");
        cataSalir = new JMenuItem("Salir");

        //opciones procesos
        proPrestamos = new JMenuItem("Prestamos");
        profechaDevo = new JMenuItem("Fechas de devolucion");

        //opciones reportes
        repoLibros = new JMenuItem("Libros");
        repoAutores = new JMenuItem("Autores");
        repoLectores = new JMenuItem("Lectores");
        repoPrestamos = new JMenuItem("Prestamos");

        //opciones consultas    
        consulprestamosactivos = new JMenuItem("Prestamos activos");
        consulprestamosvencidos = new JMenuItem("Prestamos vencidos");
        consullectoresinformacion = new JMenuItem("Informacion de los lectores");
        consullibrosporexistencias = new JMenuItem("Libros por idioma");
        consullibrosporgenero = new JMenuItem("Libros por genero");
        consulautoryobra = new JMenuItem("Autor y obra");

        //opciones ayuda
        ayudaf1 = new JMenuItem("Ayuda (F1)");
        acercade = new JMenuItem("Acerca de...");
    }

    public void darformamenu() {

        Catalogos.add(cataLibros);
        Catalogos.add(cataAutores);
        Catalogos.add(cataLectores);
        Catalogos.add(js1);//separador
        Catalogos.add(cataSalir);
        cataSalir.setBorder(BorderFactory.createLineBorder(Color.red));

        Procesos.add(proPrestamos);
        Procesos.add(profechaDevo);

        Reportes.add(repoLibros);
        Reportes.add(repoAutores);
        Reportes.add(repoLectores);
        Reportes.add(repoPrestamos);

        Consultas.add(consulprestamosactivos);
        Consultas.add(consulprestamosvencidos);
        Consultas.add(consullectoresinformacion);
        Consultas.add(consullibrosporexistencias);
        Consultas.add(consullibrosporgenero);
        Consultas.add(consulautoryobra);

        Ayuda.add(ayudaf1);
        Ayuda.add(acercade);

        jbar.add(Catalogos);
        jbar.add(Procesos);
        jbar.add(Reportes);
        jbar.add(Consultas);
        jbar.add(Ayuda);

        etiquetaimagen.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(490, 290, Image.SCALE_SMOOTH)));
        formulariomenu.add(etiquetaimagen);

        formulariomenu.setJMenuBar(jbar);
        formulariomenu.setVisible(true);
        formulariomenu.setSize(500, 300);
        formulariomenu.setLocationRelativeTo(null);

        cataLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Libros mylibro = new Libros();
                mylibro.darforma();
                formulariomenu.dispose();
            }
        });

        cataAutores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Autores myautor = new Autores();
                myautor.darforma();
                formulariomenu.dispose();
            }
        });

        cataLectores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lectores mylector = new Lectores();
                mylector.darforma();
                formulariomenu.dispose();
            }
        });

        cataSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sw = JOptionPane.showConfirmDialog(null, "En verdad deseas salir de tu sesión?");
                if (sw == 0) {
                    IngresarSistema myingresis = new IngresarSistema();
                    myingresis.darforma();
                    formulariomenu.dispose();

                }

            }
        });


    }

   /* public void reporte(String nombreReporte) {
        try {
            conexion myConexion = new conexion();
            Connection tuConexionBD = myConexion.conecta();
            String rutaReporte = "C:\\Users\\never\\OneDrive\\Escritorio\\5to Cuatrimestre\\Base de datos\\pagepalace\\src\\REPORT\\" + nombreReporte;

            // Cargar el archivo del reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(rutaReporte, null, tuConexionBD);

            // Mostrar el reporte en una ventana de visualización
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }*/

    public static void main(String[] args) {
        Menuprincipal mymenu = new Menuprincipal();
        mymenu.darformamenu();
    }
}
