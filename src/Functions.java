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
    static String mapTypeSelected = mapType[1];

    static String[] resultAs = {"Map", "Function"};
    static String resultAsSelected = resultAs[1];

    static String[] splitBy = {"Line break", ",", ";" ,"#"};
    static String splitBySelected = splitBy[1];

    static String[] dataType = {"String", "Integer", "Double", "Float", "Long", "Byte", "Short", "Boolean", "Char"};
    static String dataTypeKey = dataType[0];
    static String dataTypeValue = dataType[0];
    
    static String[] resultActions = {"Display it in Notepad", "Copy to clipboard", "Both"};
    static String resultActionsSelected = resultActions[2];

    static String keys = "<a>, <b>, <c>";
    static String keysRemoveLeft = "";
    static String keysRemoveRight = "";
    static String values = "#a-, #b-, #c-";
    static String valuesRemoveLeft = "";
    static String valuesRemoveRight = "";
    static String mapBody = "empty";
    static String mapName = "myMap";
    static String functionName = "myFunction";
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
        return mapName + ".put(\"" + key.strip() + "\", \"" + value.strip() + "\");"; 
    }
    
    
    static String[] getArrayOfKeys() {
        if (splitBySelected == splitBy[0]) {splitBySelected = "\n";}
        return keys.split(splitBySelected);
    }

    
    static String[] getArrayOfValues() {
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

        for  (int i = 0; i < mapLength; i++) {

            String key = getArrayOfKeys()[i];
            key = getTrimmedKey(key);
            String value = getArrayOfValues()[i];
            value = getTrimmedValue(value);

            if (mapBody!="empty")  {
                mapBody = mapBody + addToMap(key, value) + getNewLine(i, mapLength);
            }
            else {
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