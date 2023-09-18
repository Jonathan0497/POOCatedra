package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Validaciones {
    public void validarSoloLetras(JTextField campo){

        campo.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int k = (int)e.getKeyChar();
                if(Character.isDigit(c)|| k == 64){ //Esto lo puse para que no pusiera "@" para evitar la inyección sql, esto con el método ASCII al momento de aplicar el Alt 64 pone ese signo.
                    e.consume();
                    System.out.println("car: "+c);
                }
            }
        });
    }

    public void validarTextAreas(JTextArea campo){

        campo.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int k = (int)e.getKeyChar();
                if(Character.isDigit(c)|| k == 64){//Esto lo puse para que no pusiera "@" para evitar la inyección sql, esto con el método ASCII al momento de aplicar el Alt 64 pone ese signo.
                    e.consume();
                    System.out.println("car: "+c);
                }
            }
        });
    }

    //Aquí solo validamos para que el textbox solo deje números.
    public void validarSoloNumeros(JTextField campo){

        campo.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    e.consume();//Esto es para que no lo ponga, osea que si la condición dice que es diferente a los digitos numéricos no lo pondrá en el textbox.
                    System.out.println("car: "+c);
                }
            }
        });
    }

    public void LongitudDeCaracteres(final JTextField campo, final int cantidad){

        campo.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int tam = campo.getText().length();
                if(tam >=cantidad){
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Numero máximo de carácteres");
                }
            }
        });
    }
}
