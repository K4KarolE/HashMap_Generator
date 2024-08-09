package docs.learning;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class copyToClipboard {

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Clipboard clipboard = toolkit.getSystemClipboard();


    static void copyToCb(String text) {
        StringSelection strSel = new StringSelection(text);
        clipboard.setContents(strSel, null); 
    }

    public static void main(String[] args) {
        copyToCb("Random text");
    }
}
