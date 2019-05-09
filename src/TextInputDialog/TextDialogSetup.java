package TextInputDialog;


import javafx.scene.control.TextInputDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author slinger
 */
public class TextDialogSetup {
    
    private String text;
    private String myTitle;
    private String myHeaderText;
    private String myContentText;
    private TextInputDialog myDialog;

    public TextDialogSetup(String text, String myTitle, String myHeaderText, String myContentText) {
        this.text = text;
        this.myTitle = myTitle;
        this.myHeaderText = myHeaderText;
        this.myContentText = myContentText;
        
        myDialog = new TextInputDialog(text);
        myDialog.setTitle(myTitle);
        myDialog.setHeaderText(myHeaderText);
        myDialog.setContentText(myContentText);
    }
    
    
    
    public TextInputDialog getTextInputDialog() {
        return myDialog;
    }


    

    
    
    

}
