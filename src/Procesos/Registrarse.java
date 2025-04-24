package Procesos;

import DAO.conexion;
import ds.desktop.notify.DesktopNotify;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javafx.scene.control.PasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Registrarse {

    JFrame formularioregistrarse;

    JLabel idusuario;
    JLabel nombre;
    JLabel apellidopate;
    JLabel apellidomate;
    JLabel usuario;
    JLabel contraseña;
    JLabel confirmarcontraseña;
    JLabel rol;
    JLabel textoregist;

    JTextField txtidusuario;
    JTextField txtnombre;
    JTextField txtapellidopate;
    JTextField txtapellidomate;
    JTextField txtusuario;
    JTextField txtcontraseña;
    JTextField txtconfirmarcontraseña;

    JComboBox<String> rolcomboBox = new JComboBox<>();

    JButton btnregistrase;
    JButton btnregresar;

    JPanel paneltexto;
    JPanel panelidusuario;
    JPanel panelnombre;
    JPanel panelapellidopate;
    JPanel panelapellidomate;
    JPanel panelusuario;
    JPanel panelcontraseña;
    JPanel panelconfirmarcontraseña;
    JPanel panelrol;
    JPanel panelBotones;

    Connection con;
    PreparedStatement stmt;
    
    String sql = "";
    int sw = 0;
    conexion mycon = new conexion();

    Registrarse() {
        con = mycon.conecta();

        formularioregistrarse = new JFrame("Registrate");

        idusuario = new JLabel("ID_Usuario");
        nombre = new JLabel("Nombre");
        apellidopate = new JLabel("Apellido Paterno");
        apellidomate = new JLabel("Apellido Materno");
        rol = new JLabel("Rol que ejerces");
        usuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        confirmarcontraseña = new JLabel("Confirmar contraseña");
        textoregist = new JLabel("REGISTRARSE");

        rolcomboBox.addItem(null);
        rolcomboBox.addItem("administrador");
        rolcomboBox.addItem("bibliotecario");

        txtidusuario = new JTextField(25);
        txtnombre = new JTextField(25);
        txtapellidopate = new JTextField(20);
        txtapellidomate = new JTextField(20);
        txtusuario = new JTextField(25);
        txtcontraseña = new JTextField(23);
        txtconfirmarcontraseña = new JTextField(18);

        btnregistrase = new JButton("REGISTRAR");
        btnregresar = new JButton("REGRESAR");

        paneltexto = new JPanel();
        panelidusuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelnombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelapellidopate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelapellidomate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelusuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelcontraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelconfirmarcontraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelrol = new JPanel();
        panelBotones = new JPanel();

    }

    public void darforma() {
        formularioregistrarse.setLayout(new GridLayout(10, 1));
        paneltexto.add(textoregist);

        panelidusuario.add(idusuario);
        panelidusuario.add(txtidusuario);

        panelnombre.add(nombre);
        panelnombre.add(txtnombre);

        panelapellidopate.add(apellidopate);
        panelapellidopate.add(txtapellidopate);

        panelapellidomate.add(apellidomate);
        panelapellidomate.add(txtapellidomate);

        panelusuario.add(usuario);
        panelusuario.add(txtusuario);

        panelrol.add(rol);
        panelrol.add(rolcomboBox);

        panelcontraseña.add(contraseña);
        panelcontraseña.add(txtcontraseña);

        panelconfirmarcontraseña.add(confirmarcontraseña);
        panelconfirmarcontraseña.add(txtconfirmarcontraseña);

        panelBotones.add(btnregistrase);
        panelBotones.add(btnregresar);

        formularioregistrarse.add(paneltexto);
        formularioregistrarse.add(panelidusuario);
        formularioregistrarse.add(panelnombre);
        formularioregistrarse.add(panelapellidopate);
        formularioregistrarse.add(panelapellidomate);
        formularioregistrarse.add(panelusuario);
        formularioregistrarse.add(panelrol);
        formularioregistrarse.add(panelcontraseña);
        formularioregistrarse.add(panelconfirmarcontraseña);
        formularioregistrarse.add(panelBotones);
        formularioregistrarse.setVisible(true);
        formularioregistrarse.pack();
        formularioregistrarse.setLocationRelativeTo(null);

        txtidusuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtidusuario.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL ID DEL USUARIO ES OBLIGATORIO", 1, 3000);
                } else {
                    txtnombre.requestFocusInWindow();

                }
            }
        });

        txtnombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtnombre.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL NOMBRE DEL USUARIO ES OBLIGATORIO", 1, 3000);
                } else {
                    txtapellidopate.requestFocusInWindow();

                }
            }
        });

        txtapellidopate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtapellidopate.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL APELLIDO PATERNO DEL USUARIO ES OBLIGATORIO", 1, 3000);
                } else {
                    txtapellidomate.requestFocusInWindow();

                }
            }
        });

        txtapellidomate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtapellidomate.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL APELLIDO MATERNO DEL USUARIO ES OBLIGATORIO", 1, 3000);
                } else {
                    txtusuario.requestFocusInWindow();

                }
            }
        });

        txtusuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtusuario.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "USUARIO OBLIGATORIO", 1, 3000);
                } else {
                    rolcomboBox.requestFocusInWindow();

                }
            }
        });

        txtcontraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtcontraseña.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "CONTRASEÑA OBLIGATORIA ES OBLIGATORIO", 1, 3000);
                } else {
                    txtconfirmarcontraseña.requestFocusInWindow();

                }
            }
        });

        txtconfirmarcontraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtconfirmarcontraseña.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "CONFIRMAR CONTRASEÑA ES OBLIGATORIO", 1, 3000);
                } else {
                    btnregistrase.requestFocusInWindow();

                }
            }
        });

        btnregresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioregistrarse.dispose();
                IngresarSistema myingresis = new IngresarSistema();
                myingresis.darforma();

            }
        });

        btnregistrase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();

            }
        });

    }

    public void limpiar() {
        String vacio = "";
        txtidusuario.setText(vacio);
        txtnombre.setText(vacio);
        txtapellidopate.setText(vacio);
        txtapellidomate.setText(vacio);
        txtusuario.setText(vacio);
        txtcontraseña.setText(vacio);
        txtconfirmarcontraseña.setText(vacio);
        rolcomboBox.setSelectedItem(null);
        txtidusuario.requestFocusInWindow();

    }

    public void guardar() {
        if (!txtcontraseña.getText().equals(txtconfirmarcontraseña.getText())) {
            DesktopNotify.showDesktopMessage("ERROR", "LA CONTRASEÑA NO COINCIDE VUELVE A INTENTAR", 3, 3000);

        } else {
            sql = "insert into usuarios values (";
            sql += "\"" + txtidusuario.getText() + "\" , ";
            sql += "\"" + txtnombre.getText() + "\" , ";
            sql += "\"" + txtapellidopate.getText() + "\" , ";
            sql += "\"" + txtapellidomate.getText() + "\" , ";
            sql += "\"" + txtconfirmarcontraseña.getText() + "\" , ";
            sql += "\"" + txtusuario.getText() + "\" , ";
            sql += "\"" + rolcomboBox.getSelectedItem().toString() + "\" ) ";
            System.out.println(sql);
            try {
                stmt = con.prepareStatement(sql);
                sw = stmt.executeUpdate();
                if (sw != 0) {
                    DesktopNotify.showDesktopMessage("ESO", "REGISTRO DADO DEL ALTA CON EXITO", 7, 3000);
                    limpiar();

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Registrarse myregistrase = new Registrarse();
        myregistrase.darforma();
    }

}
