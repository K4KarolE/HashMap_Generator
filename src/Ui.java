package src;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;


public class Ui {

    static JFrame frame = new JFrame();
    static Data da = new Data();
    static Functions fs = new Functions();


    static void createWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage("./docs/icon_window.png");
        frame.setIconImage(icon);
        frame.setTitle("Hashmap Generator");
        frame.setLayout(null);
        frame.setBounds(da.FRAME_POS_X, da.FRAME_POS_Y, da.FRAME_WIDTH, da.FRAME_HEIGHT);
        frame.getContentPane().setBackground(new java.awt.Color(243, 243, 243));
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        
        Font combo_box_font_style = new Font("Times New Roman", Font.PLAIN, 14);
        Font field_font_style = new Font("Consolas", Font.BOLD, 18);
        // Font label_font_style = new Font("Times New Roman", Font.PLAIN, 14);
        // Font label_title_font_style = new Font("Times New Roman", Font.PLAIN, 18);
        
        JComboBox<String> comboBoxMapType = new JComboBox<>(fs.getRollDownOptions("mapType"));

        comboBoxMapType.setFont(combo_box_font_style);

        JTextArea keysTextA = new JTextArea();
        keysTextA.setFont(field_font_style);
        
        keysTextA.setBounds(40, da.getPosY(1), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        comboBoxMapType.setBounds(40, da.getPosY(-1), da.WIDGET_WIDTH, 25);
        
        
        frame.add(keysTextA);
        frame.add(comboBoxMapType);
        
        
        createWindow();
        fs.goButtonAction();
        
    }
}