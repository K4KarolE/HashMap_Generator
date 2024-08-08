package src;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Desktop;


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


    static String keys;
    static String keysRemoveLeft = "";
    static String keysRemoveRight = "";
    static String values;
    static String valuesRemoveLeft = "";
    static String valuesRemoveRight = "";
    static String mapBody = "empty";
    static String mapName = "myMap";
    static String functionName = "myFunction";

    static Path resultFilePath = Paths.get("result.txt");
    static File resultFile = new File(resultFilePath.toString());

    
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
        return "Map<" + dataTypeKey + ", " + dataTypeValue + "> " + mapName + " = new " + mapTypeSelected + "<>();"; 
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
            return "static Map<" + dataTypeKey + ", " + dataTypeValue + "> " + functionName + "() {\n"; 
        }
        else { return "";}
    }


    static String getClousareFunction() {
        if (resultAsSelected == "Function") {
            return "return " + mapName + ";\n}";
        }
        else { return "";} 
    }

    
    static String getMap() {
        String map = getHeaderFunction()
                    + getHeader() + "\n"
                    + getBody() + "\n"
                    + getClousareFunction();
        return map;
    }


    static void writeToFile() {
        try {
            String map = getMap();
            Files.writeString(resultFilePath, map, StandardCharsets.UTF_8);
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


    public static void main(String[] args) {
        
        // TEST TEXT
        // SPLIT BY LINE BREAK
        keys = """
                <a>
                <b>
                <c>
                """; 
        values = """
                #a-
                #b-
                #c-
                """;
        
        // SPLIT BY ","
        keys = "<a>, <b>, <c>";
        values = "#a-, #b-, #c-";

        writeToFile();
        // launcNotepad();
        
    }
 
}
