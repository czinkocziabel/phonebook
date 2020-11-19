package phonebook;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ViewController implements Initializable {

    @FXML
    TableView table;
    @FXML
    TextField inputLastName;
    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputEmail;
    @FXML
    Button addNewContactButton;
    @FXML
    StackPane menuPane;
    @FXML
    Pane contactPane;
    @FXML
    Pane exportPane;

    private final ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("Gyula", "Szabó", "gyula@gmail.com"),
            new Person("Jason", "Bourne", "jason@citromail.hu"),
            new Person("Michael", "Scott", "michael@freemail.com")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn lastNameCol = new TableColumn("Vezetéknév");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        
        TableColumn fistNameCol = new TableColumn("Keresztnév");
        fistNameCol.setMinWidth(100);
        fistNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fistNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        
        TableColumn emailCol = new TableColumn("Email cím");
        emailCol.setMinWidth(200);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));
        
        table.getColumns().addAll(lastNameCol,fistNameCol,emailCol);
        table.setItems(data);
    }

}
