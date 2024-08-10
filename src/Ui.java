package src;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

    static void warningMsgKeysValuesDiff() {
        if (fs.getArrayOfKeys().length != fs.getArrayOfValues().length) {
            JOptionPane.showMessageDialog(
                frame,
                "The amount of the Keys and Values are not matching!        \n\n"
                        + "The result can be mismatched, truncated, \n"
                        + "missing values can be replaced with empty string (\"\").",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        
        Font combo_box_font_style = new Font("Times New Roman", Font.PLAIN, 14);
        Font field_font_style = new Font("Consolas", Font.BOLD, 18);
        Font label_font_style = new Font("Times New Roman", Font.PLAIN, 14);
        Font label_title_font_style = new Font("Times New Roman", Font.PLAIN, 18);
        
        JComboBox<String> comboBoxMapType = new JComboBox<>(fs.getRollDownOptions("mapType"));

        comboBoxMapType.setFont(combo_box_font_style);

        JTextArea keysTextArea = new JTextArea();
        keysTextArea.setFont(field_font_style);
        
        comboBoxMapType.setBounds(40, da.getPosY(-1), da.WIDGET_WIDTH, 25);
        
        
        JButton button = new JButton("Go");
        button.setFont(label_font_style);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fs.setTypeSelected(comboBoxMapType.getSelectedItem().toString());
                fs.setResultAsSelected( "Function");
                fs.setSplitBySelected( ",");
                fs.setDataTypeKey( "String");
                fs.setDataTypeValue( "Double");
                fs.setResultActionsSelected( "Both");
                
                fs.setKeys(keysTextArea.getText());
                fs.setKeysRemoveLeft( "");
                fs.setKeysRemoveRight( "");
                
                // fs.setValues( """
                //             a
                //             b
                //             c
                //             """);
                fs.setValues("a,b,c");
                fs.setValuesRemoveLeft( "");
                fs.setValuesRemoveRight( "");
                
                fs.setMapName( "--mapname");
                fs.setFunctionName( "--funname");
                
                warningMsgKeysValuesDiff();
                fs.goButtonAction();
                fs.setMapBodyBackToDefault();
            }
        });
        
        
        
        keysTextArea.setBounds(40, da.getPosY(1), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        button.setBounds(200, da.getPosY(-1), da.WIDGET_WIDTH, 25);
        

        frame.add(keysTextArea);
        frame.add(comboBoxMapType);
        frame.add(button);

        
        createWindow();
        

        
        
    }

}
