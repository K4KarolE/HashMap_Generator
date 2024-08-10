package src;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class Functions {

    static String[] mapType = {"Map", "HashMap", "LinkedHashMap", "TreeMap"};
    static String mapTypeSelected = "";
    public void setTypeSelected(String newValue) {mapTypeSelected = newValue;}

    static String[] resultAs = {"Map", "Function"};
    static String resultAsSelected = "";
    public void setResultAsSelected(String newValue) {resultAsSelected = newValue;}

    static String[] splitBy = {"Line break", ",", ";" ,"#"};
    static String splitBySelected = "";
    public void setSplitBySelected(String newValue) {splitBySelected = newValue;}

    static String[] dataType = {"String", "Integer", "Double", "Float", "Long", "Byte", "Short", "Boolean", "Char"};
    static String dataTypeKey = "";
    public void setDataTypeKey(String newValue) {dataTypeKey = newValue;}
    static String dataTypeValue = "";
    public void setDataTypeValue(String newValue) {dataTypeValue = newValue;}
    
    static String[] resultActions = {"Display it in Notepad", "Copy to clipboard", "Both"};
    static String resultActionsSelected = "";
    public void setResultActionsSelected(String newValue) {resultActionsSelected = newValue;}


    // KEYS
    static String keys = "";
    public void setKeys(String newValue) {keys = newValue;}

    static String keysRemoveLeft = "";
    public void setKeysRemoveLeft(String newValue) {keysRemoveLeft = newValue;}

    static String keysRemoveRight = "";
    public void setKeysRemoveRight(String newValue) {keysRemoveRight = newValue;}


    // VALUES
    static String values = "#a-, #b-, #c-";
    public void setValues(String newValue) {values = newValue;}

    static String valuesRemoveLeft = "";
    public void setValuesRemoveLeft(String newValue) {valuesRemoveLeft = newValue;}

    static String valuesRemoveRight = "";
    public void setValuesRemoveRight(String newValue) {valuesRemoveRight = newValue;}


    static String mapName = "myMap";
    public void setMapName(String newValue) {mapName = newValue;}
    
    static String functionName = "myFunction";
    public void setFunctionName(String newValue) {functionName = newValue;}
    
    
    static String mapBody = "empty";
    public void setMapBodyBackToDefault() {mapBody = "empty";}

    static String mapResult = "";

    // PATH-FILE
    static Path resultFilePath = Paths.get("result.txt");
    static File resultFile = new File(resultFilePath.toString());

    // CLIPBOARD
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Clipboard clipboard = toolkit.getSystemClipboard();


    public String[] getRollDownOptions(String title) {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("mapType", mapType);
        map.put("resultAs", resultAs);
        map.put("splitBy", splitBy);
        map.put("dataType", dataType);
        map.put("resultActions", resultActions);
        return map.get(title);
    }


    
    static String getTrimmedKey(String originalKey) {
        Integer beginIndex = keysRemoveLeft.length();
        Integer endIndex = originalKey.length() - keysRemoveRight.length();
    return originalKey.substring(beginIndex, endIndex);
    }


    static String getTrimmedValue(String originalValue) {
        Integer beginIndex = valuesRemoveLeft.length();
        Integer endIndex = originalValue.length() - valuesRemoveRight.length();
    return originalValue.substring(beginIndex, endIndex);
    }
    
    
    static String addToMap(String key, String value) {
        if (dataTypeKey == "String") {
            key = "\"" + key + "\"";
        }
        if (dataTypeKey == "Char") {
            key = "'" + key + "'";
        }


        if (dataTypeValue == "String") {
            value = "\"" + value + "\"";
        }
        if (dataTypeValue == "Char") {
            value = "'" + value + "'";
        }
        return mapName + ".put(" + key + "," +  value + ");"; 
    }
    
    
    public static String[] getArrayOfKeys() {
        if (splitBySelected == splitBy[0]) {splitBySelected = "\n";}
        return keys.split(splitBySelected);
    }

    
    public static String[] getArrayOfValues() {
        if (splitBySelected == splitBy[0]) {splitBySelected = "\n";}
        return values.split(splitBySelected);
    }
    
    
    static String getHeader() {
        return mapTypeSelected + "<" + dataTypeKey + ", " + dataTypeValue + "> " + mapName + " = new " + mapTypeSelected + "<>();"; 
    }


    static String getNewLine(Integer i, Integer mapLenght) {
        if (i!=mapLenght-1) {
            return "\n";
        }
        else {return "";}
    }
    

    static String getBody() {

        Integer mapLength = getArrayOfKeys().length;
        Integer valuesLength = getArrayOfValues().length;
        String value;

   
            for  (int i = 0; i < mapLength; i++) {

                String key = getArrayOfKeys()[i];
                key = getTrimmedKey(key);

                if (i < valuesLength) {    // Keys > Values
                    value = getArrayOfValues()[i];
                    value = getTrimmedValue(value);
                }
                else {value = "";}

                if (mapBody!="empty")  {
                    mapBody = mapBody + addToMap(key, value) + getNewLine(i, mapLength);
                }
                else {
                    // First line of mapBody
                    mapBody = addToMap(key, value) + getNewLine(i, mapLength); 
                }
            }
            return mapBody;
    }


    static String getHeaderFunction() {
        if (resultAsSelected == "Function") {
            return "static " + mapTypeSelected + "<" + dataTypeKey + ", " + dataTypeValue + "> " + functionName + "() {\n"; 
        }
        else { return "";}
    }


    static String getClousareFunction() {
        if (resultAsSelected == "Function") {
            return "return " + mapName + ";\n}";
        }
        else { return "";} 
    }

    
    static void createMap() {
        mapResult = getHeaderFunction()
                    + getHeader() + "\n"
                    + getBody() + "\n"
                    + getClousareFunction();
    }


    static void writeToFile() {
        try {
            Files.writeString(resultFilePath, mapResult, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.print("Invalid Path");
        }
    }


    static void launcNotepad() {
        try {
            Desktop.getDesktop().open(resultFile);
            }
        catch (IOException e) {System.out.println("Could not open Notepad");}
    }
    
    
    static void copyToClipboard() {
        StringSelection strSel = new StringSelection(mapResult);
        clipboard.setContents(strSel, null); 
    }


    public void goButtonAction() {
        createMap();
        writeToFile();
        if (resultActionsSelected == resultActions[0]) {
            launcNotepad();
        }
        if (resultActionsSelected == resultActions[1]) {
            copyToClipboard();
        }
        else {
            launcNotepad();
            copyToClipboard();
        }
    }
}