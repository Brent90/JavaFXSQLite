package javafxsqlite;

import TextInputDialog.TextDialogSetup;
import Album.Album;
import Database.DAO;
import Database.DatabaseConnector;
import TextInputDialog.CustomDialog;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author slinger
 */
public class MainController implements Initializable {

    private final DAO databaseOperations = new DAO();
    private final DatabaseConnector connection = new DatabaseConnector();
    private final CustomDialog customDialog = new CustomDialog();

    @FXML
    private TableColumn<Album, Integer> albumIdColumn;
    @FXML
    private TableColumn<Album, String> titleColumn;
    @FXML
    private TableColumn<Album, Integer> artistIdColumn;

    ObservableList<Album> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Album> table;
    @FXML
    private TextArea sqliteCommand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    @FXML
    private void loadData() {

        table.getItems().clear();
        executeQuery("select * from album");

        //  display data to user
        albumIdColumn.setCellValueFactory(new PropertyValueFactory<>("AlbumId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        artistIdColumn.setCellValueFactory(new PropertyValueFactory<>("ArtistId"));
        table.setItems(oblist);

    }

    @FXML
    private void searchByArtistId(ActionEvent event) {

        TextDialogSetup setup = new TextDialogSetup("Enter Id", "Artist Id Filter", "Search Artists with Id: ", "ArtistId: ");
        TextInputDialog mydDialog = setup.getTextInputDialog();
        Optional<String> result = mydDialog.showAndWait();

        result.ifPresent(id -> {
            executeQuery("select * from album where artistid=" + Integer.parseInt(id));
        });

    }

    @FXML
    private void searchByAlbumId(ActionEvent event) {

        TextDialogSetup setup = new TextDialogSetup("Enter Id", "Album Id Filter", "Search Album with Id: ", "AlbumId: ");
        TextInputDialog mydDialog = setup.getTextInputDialog();
        Optional<String> result = mydDialog.showAndWait();

        result.ifPresent(id -> {
            executeQuery("select * from album where albumid=" + Integer.parseInt(id));
        });

    }

    @FXML
    private void searchByTitle(ActionEvent event) {
        TextDialogSetup setup = new TextDialogSetup("Enter Title", "Title Filter", "Search Title: ", "Title: ");
        TextInputDialog mydDialog = setup.getTextInputDialog();
        Optional<String> result = mydDialog.showAndWait();

        result.ifPresent(id -> {
            executeQuery("select * from album where title like '%" + id + "%'");
        });

    }

    @FXML
    private void executeCommand(ActionEvent event) {
        String statement = sqliteCommand.getText();
        if (statement.isEmpty()) {
            System.out.println("enter command...");
        } else {
            executeQuery(statement);
        }

    }

    @FXML
    private void clearCommand(ActionEvent event) {
        sqliteCommand.setText("");
    }

    @FXML
    private void InsertData(ActionEvent event) {
        Optional result = customDialog.getDialog().showAndWait();

        try {
            int albumIdValue = customDialog.getAlbumId();
            String titleValue = "'" + customDialog.getTitle() + "'";
            int artistIdValue = customDialog.getArtistId();
            result.ifPresent(id -> {
                String query = "insert into album(albumid,title,artistid) values (" + albumIdValue + "," + titleValue + "," + artistIdValue + ")";
                executeQuery(query);

            });
            loadData();
        } catch (Exception e) {
            System.out.println("not valid command");
        }

    }

    @FXML
    private void deleteRow(ActionEvent event) {
        TextDialogSetup setup = new TextDialogSetup("Id", "Delete Row", "Delete Row By AlbumId: ", "AlbumId: ");
        TextInputDialog mydDialog = setup.getTextInputDialog();
        Optional<String> result = mydDialog.showAndWait();

        result.ifPresent(id -> {
            executeQuery("delete from album where albumid=" + id);
            loadData();
        });

    }

    public void executeQuery(String command) {
        try {
            ResultSet rs = databaseOperations.executeUpdate(connection.getConnection(), command);

            table.getItems().clear();

            while (rs.next()) {
                oblist.add(new Album(rs.getInt("albumId"), rs.getString("title"), rs.getInt("artistId")));

            }

            connection.closeConnection();

        } catch (Exception e) {
            System.out.println("Command not valid");
        }
    }

}
