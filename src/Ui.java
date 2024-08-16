package src;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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


    static void addFieldsExplanationTextWidgets() {
        int widgetCommentPosX = da.BASE_X + da.WIDGET_WIDTH + 15;
        Font label_title_font_style = new Font("Verdana", Font.PLAIN, 15);

        HashMap<Integer, String> mapText = new HashMap<>();
        mapText.put(1,"Map type");
        mapText.put(2,"Deliver result as");
        mapText.put(3,"Map title");
        mapText.put(4,"Function title (if appropriate)");
        mapText.put(5,"Action with the result");
        mapText.put(6,"Keys / Values - split text by");

        for (int i = 1; i <= mapText.size(); i++) {
            JLabel text = new JLabel(mapText.get(i));
            text.setFont(label_title_font_style);
            text.setBounds(widgetCommentPosX, da.getPosY(i-1),300, da.WIDGET_HEIGHT);
            frame.add(text);
        }
    }


    static void addImage(String imgType, int posY) {
        try {
            int imgPosX = (int)(da.BASE_X + da.keyAndValueWidgetsWidth + (da.keysAndValuesWidgetPosXdiff - da.WIDGET_HEIGHT)/2);
            Image img = Toolkit.getDefaultToolkit().getImage("./docs/img_" + imgType + ".png");
            JLabel img_label = new JLabel(new ImageIcon(img.getScaledInstance(da.WIDGET_HEIGHT, da.WIDGET_HEIGHT, Image.SCALE_SMOOTH)));
            img_label.setBounds(imgPosX, da.getPosY(posY), da.WIDGET_HEIGHT, da.WIDGET_HEIGHT);
            frame.add(img_label);
        }
        catch (Exception e) {System.out.println("ERROR - loading image");}
    }


    public static void main(String[] args) {
        
        Font combo_box_font_style = new Font("Times New Roman", Font.PLAIN, 16);
        Font field_font_style = new Font("Consolas", Font.BOLD, 16);
        Font label_font_style = new Font("Times New Roman", Font.PLAIN, 16);
        
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


        addFieldsExplanationTextWidgets();

        addImage("remove", 8);
        addImage("add", 9);

        
        JButton buttonCompile = new JButton("COMPILE");
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
        comboBoxMapType.setBounds(da.BASE_X, da.getPosY(0), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        comboBoxResultAs.setBounds(da.BASE_X, da.getPosY(1), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        
        textFieldMapName.setBounds(da.BASE_X, da.getPosY(2), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        textFieldFunctionName.setBounds(da.BASE_X, da.getPosY(3), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        
        comboBoxdResultActions.setBounds(da.BASE_X, da.getPosY(4), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);
        comboBoxSplitBy.setBounds(da.BASE_X, da.getPosY(5), da.WIDGET_WIDTH, da.WIDGET_HEIGHT);


        int trimAndAddFieldDiff = 5;
        int trimAndAddFieldsWidth = (int)(da.keyAndValueWidgetsWidth / 2 - trimAndAddFieldDiff);
        int trimAndAddFielRightPos = da.BASE_X + trimAndAddFieldsWidth + trimAndAddFieldDiff * 2;
        int keysPosBaseX = da.BASE_X + da.keyAndValueWidgetsWidth + da.keysAndValuesWidgetPosXdiff;
        int keysPosRightX = keysPosBaseX + trimAndAddFieldsWidth + trimAndAddFieldDiff * 2;
        
        
        comboBoxdDataTypeKey.setBounds(da.BASE_X, da.getPosY(7), da.keyAndValueWidgetsWidth, da.WIDGET_HEIGHT);
        comboBoxdDataTypeValue.setBounds(keysPosBaseX, da.getPosY(7), da.keyAndValueWidgetsWidth, da.WIDGET_HEIGHT);
        
        textFieldKeysRemoveLeft.setBounds(da.BASE_X, da.getPosY(8), trimAndAddFieldsWidth, da.WIDGET_HEIGHT);
        textFieldKeysRemoveRight.setBounds(trimAndAddFielRightPos, da.getPosY(8), trimAndAddFieldsWidth, da.WIDGET_HEIGHT);


        textFieldValuesRemoveLeft.setBounds(keysPosBaseX, da.getPosY(8), trimAndAddFieldsWidth, da.WIDGET_HEIGHT);
        textFieldValuesRemoveRight.setBounds(keysPosRightX, da.getPosY(8), trimAndAddFieldsWidth, da.WIDGET_HEIGHT);


        textAreaKeys.setBounds(da.BASE_X, da.getPosY(10), da.keyAndValueWidgetsWidth, da.WIDGET_HEIGHT*8);
        textAreaValues.setBounds(keysPosBaseX, da.getPosY(10), da.keyAndValueWidgetsWidth, da.WIDGET_HEIGHT*8);

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        textAreaKeys.setBorder(border);
        textAreaValues.setBorder(border);

   
        int buttonCompileHeight = (int)(da.WIDGET_HEIGHT*1.3);
        int buttonCompileWidth = da.keyAndValueWidgetsWidth * 2 + da.keysAndValuesWidgetPosXdiff;
        buttonCompile.setBounds(da.BASE_X, da.FRAME_HEIGHT - 100, buttonCompileWidth, buttonCompileHeight);
            

 
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
