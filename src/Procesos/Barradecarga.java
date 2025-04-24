package Procesos;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Barradecarga {

    JFrame barra;
    JProgressBar porcentajedecarga;

    Barradecarga() {
        barra = new JFrame();
        porcentajedecarga = new JProgressBar();
    }

    public void darformabarra() {
        barra.setUndecorated(true);//elimina
        barra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        barra.setSize(400, 20);
        barra.setLocationRelativeTo(null);

        porcentajedecarga.setStringPainted(true);
        porcentajedecarga.setForeground(new Color(0x21e136)); // Cambiar color
        porcentajedecarga.setBorder(BorderFactory.createLineBorder(new Color(0x00000), 2)); // Cambiar color del contorno
        barra.add(porcentajedecarga);
        barra.setVisible(true);

        //proceso para que funcione el progreso de carga
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          
            porcentajedecarga.setValue(i);

        }

        IngresarSistema myingresis = new IngresarSistema();
        myingresis.darforma();
        barra.dispose();

    }

    public static void main(String[] args) {
        Barradecarga mybarrita = new Barradecarga();
        mybarrita.darformabarra();

    }
}
