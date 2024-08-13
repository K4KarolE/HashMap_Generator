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
import java.awt.Component;


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
        
        Font combo_box_font_style = new Font("Times New Roman", Font.PLAIN, 18);
        Font field_font_style = new Font("Consolas", Font.BOLD, 18);
        Font label_font_style = new Font("Times New Roman", Font.PLAIN, 18);
        Font label_title_font_style = new Font("Times New Roman", Font.PLAIN, 18);
        
        JComboBox<String> comboBoxMapType = new JComboBox<>(fs.getRollDownOptions("mapType"));
        comboBoxMapType.setFont(combo_box_font_style);

        JComboBox<String> comboBoxResultAs = new JComboBox<>(fs.getRollDownOptions("resultAs"));
        comboBoxResultAs.setFont(combo_box_font_style);

        JComboBox<String> comboBoxSplitBy = new JComboBox<>(fs.getRollDownOptions("splitBy"));
        comboBoxSplitBy.setFont(combo_box_font_style);

        JComboBox<String> comboBoxdDataTypeKey = new JComboBox<>(fs.getRollDownOptions("dataType"));
        comboBoxdDataTypeKey.setFont(combo_box_font_style);

        JComboBox<String> comboBoxdDataTypeValue = new JComboBox<>(fs.getRollDownOptions("dataType"));
        comboBoxdDataTypeValue.setFont(combo_box_font_style);

        JComboBox<String> comboBoxdResultActions = new JComboBox<>(fs.getRollDownOptions("resultActions"));
        comboBoxdResultActions.setFont(combo_box_font_style);


        JTextField textFieldMapName = new JTextField();
        textFieldMapName.setFont(field_font_style);
        JTextField textFieldFunctionName = new JTextField();
        textFieldFunctionName.setFont(field_font_style);


        JTextField textFieldKeysRemoveLeft = new JTextField();
        textFieldKeysRemoveLeft.setFont(field_font_style);
        JTextField textFieldKeysRemoveRight = new JTextField();
        textFieldKeysRemoveRight.setFont(field_font_style);

        JTextField textFieldValuesRemoveLeft = new JTextField();
        textFieldValuesRemoveLeft.setFont(field_font_style);
        JTextField textFieldValuesRemoveRight = new JTextField();
        textFieldValuesRemoveRight.setFont(field_font_style);


        JTextArea textAreaKeys = new JTextArea();
        textAreaKeys.setFont(field_font_style);

        JTextArea textAreaValues = new JTextArea();
        textAreaValues.setFont(field_font_style);
        
        
        
        JButton buttonCompile = new JButton("Compile");
        buttonCompile.setFont(label_font_style);
        buttonCompile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                fs.setTypeSelected(comboBoxMapType.getSelectedItem().toString());
                fs.setResultAsSelected(comboBoxResultAs.getSelectedItem().toString());
                fs.setSplitBySelected(comboBoxSplitBy.getSelectedItem().toString());
                fs.setDataTypeKey(comboBoxdDataTypeKey.getSelectedItem().toString());
                fs.setDataTypeValue(comboBoxdDataTypeValue.getSelectedItem().toString());
                fs.setResultActionsSelected(comboBoxdResultActions.getSelectedItem().toString());
                
                if (!textFieldMapName.getText().equals("")) {fs.setMapName(textFieldMapName.getText());}
                if (!textFieldFunctionName.getText().equals("")) {fs.setFunctionName(textFieldFunctionName.getText());}

                fs.setKeys(textAreaKeys.getText());
                fs.setKeysRemoveLeft(textFieldKeysRemoveLeft.getText());
                fs.setKeysRemoveRight(textFieldKeysRemoveRight.getText());
             
                fs.setValues(textAreaValues.getText());
                fs.setValuesRemoveLeft(textFieldValuesRemoveLeft.getText());
                fs.setValuesRemoveRight(textFieldValuesRemoveLeft.getText());
                     
                warningMsgKeysValuesDiff();
                fs.goButtonAction();
                fs.setMapBodyBackToDefault();
                }
            });
            
            
        
        // WIDGETS POSITIONING    
        comboBoxMapType.setBounds(40, da.getPosY(-1), da.WIDGET_WIDTH, 25);
        comboBoxResultAs.setBounds(300, da.getPosY(-1), da.WIDGET_WIDTH, 25);
        comboBoxSplitBy.setBounds(300, da.getPosY(0), da.WIDGET_WIDTH, 25);
        comboBoxdDataTypeKey.setBounds(300, da.getPosY(2), da.WIDGET_WIDTH, 25);
        comboBoxdDataTypeValue.setBounds(300, da.getPosY(3), da.WIDGET_WIDTH, 25);

        comboBoxdResultActions.setBounds(300, da.getPosY(5), da.WIDGET_WIDTH + 40, 25);
        
        textFieldMapName.setBounds(40, da.getPosY(1), da.WIDGET_WIDTH, 25);
        textFieldFunctionName.setBounds(40, da.getPosY(2), da.WIDGET_WIDTH, 25);


        textFieldKeysRemoveLeft.setBounds(40, da.getPosY(7), 70, 25);
        textFieldKeysRemoveRight.setBounds(140, da.getPosY(7), 70, 25);
        textFieldValuesRemoveLeft.setBounds(300, da.getPosY(7), 70, 25);
        textFieldValuesRemoveRight.setBounds(400, da.getPosY(7), 70, 25);


        textAreaKeys.setBounds(40, da.getPosY(10), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        textAreaValues.setBounds(300, da.getPosY(10), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);

        buttonCompile.setBounds(40, da.FRAME_HEIGHT - 100, da.WIDGET_WIDTH, 25);
            

 
        // FRAME << WIDGETS
        Component[] widgets_array = {
            comboBoxMapType,
            comboBoxResultAs,
            comboBoxSplitBy,
            comboBoxdDataTypeKey,
            comboBoxdDataTypeValue,

            comboBoxdResultActions,

            textFieldMapName,
            textFieldFunctionName,

            textFieldKeysRemoveLeft,
            textFieldKeysRemoveRight,
            textFieldValuesRemoveLeft,
            textFieldValuesRemoveRight,

            textAreaKeys,
            textAreaValues,

            buttonCompile
        };

        for (Component widget : widgets_array) {
           frame.add(widget);
        }

        
        createWindow();
    }

}
