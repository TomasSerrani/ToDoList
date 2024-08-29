
package com.mycompany.trabajopracticon3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame{
    private JTextField pantalla;
    private double num1, num2, resultado;
    private String operacion;
    public Calculadora(){
        super("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pantalla = new JTextField(20);
        pantalla.setEditable(false);
        pantalla.setPreferredSize(new Dimension(280, 50));
        add(pantalla, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4));
        String[] botones = {
            "C", "CE", "+/-", "÷",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            ",", "0", "=", ""
        };
        for (String boton : botones){
            JButton btn = new JButton(boton);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String textoBtn = btn.getText();
                    if (textoBtn.equals("C")){
                        String textoActual = pantalla.getText();
                        if (!textoActual.isEmpty()){
                            pantalla.setText(textoActual.substring(0, textoActual.length() - 1));
                        }
                    } else if (textoBtn.equals("CE")){
                        pantalla.setText("");
                    } else if (textoBtn.equals("+/-")){
                        String textoActual = pantalla.getText();
                        if (!textoActual.isEmpty() && !textoActual.equals("")){
                            pantalla.setText("-" + textoActual);
                        } else {
                            pantalla.setText(textoActual.substring(1));
                        }
                    }
                    if(textoBtn.matches("[0-9]")){
                    pantalla.setText(pantalla.getText() + textoBtn);
                } else if (textoBtn.equals(".")){
                    if (!pantalla.getText().contains(".")){
                        pantalla.setText(pantalla.getText() + textoBtn);
                    }
                } else if (textoBtn.matches("[+\\-x÷]")){
                    num1 = Double.parseDouble(pantalla.getText());
                    operacion = textoBtn;
                    pantalla.setText("");
                } else if (textoBtn.equals("=")){
                    try{ 
                    num2 = Double.parseDouble(pantalla.getText());
                    switch(operacion){
                        case "+":
                            resultado = num1 + num2;
                        break;
                        case "-":
                            resultado = num1 - num2;
                        break;
                        case "x":
                            resultado = num1 * num2;
                        break;
                        case "÷":
                            if (num2 != 0){
                                resultado = num1 / num2;
                            } else {
                                throw new ArithmeticException("División por cero");
                                    }
                                    break;
                            }
                            pantalla.setText(Double.toString(resultado));
                        } catch (ArithmeticException ex) {
                            pantalla.setText("");
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (NumberFormatException ex) {
                            pantalla.setText("");
                            JOptionPane.showMessageDialog(null, "Error: Entrada inválida", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
                }
        });
            panelBotones.add(btn);
        }
        add(panelBotones, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculadora().setVisible(true);
            }
        });
    }
}
