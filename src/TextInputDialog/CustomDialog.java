/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextInputDialog;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author slinger
 */
public class CustomDialog {
    
    private final Dialog dialog;
    private final TextField albumId;
    private final TextField title;
    private final TextField artistId;

    public CustomDialog() {
        
           // Create the custom dialog.
        dialog = new Dialog<>();

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        albumId = new TextField();
        albumId.setPromptText("Album Id ");

        title = new TextField();
        title.setPromptText("Title ");

        artistId = new TextField();
        artistId.setPromptText("Artist Id");

        grid.add(new Label("AlbumId:"), 0, 0);
        grid.add(albumId, 1, 0);

        grid.add(new Label("Title:"), 0, 1);
        grid.add(title, 1, 1);

        grid.add(new Label("AristId:"), 0, 2);
        grid.add(artistId, 1, 2);

        dialog.getDialogPane().setContent(grid);

        
    }
    
    
    public Dialog getDialog() {
        return dialog;
    }

    public int getAlbumId() {
        return Integer.parseInt(albumId.getText());
    }

    public String getTitle() {
        return title.getText();
    }

    public int getArtistId() {
        return Integer.parseInt(artistId.getText());
    }
    
    
    
    
}
