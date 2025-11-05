package sio.tp1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import sio.tp1.Repository.SecteurRepository;
import sio.tp1.Services.EmployeService;
import sio.tp1.Services.SecteurService;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class TP1Controller implements Initializable
{

    Alert alert;

    //recuperation
    private SecteurService secteurService;
    private EmployeService employeService;

    @FXML
    private TableColumn tcNomSecteur;
    @FXML
    private TableView tvRayons;
    @FXML
    private TableView tvEmployesRayon;
    @FXML
    private TableColumn tcNumeroEmployeAll;
    @FXML
    private TableView tvEmployesAll;
    @FXML
    private TableColumn tcDateEmployeRayon;
    @FXML
    private TableColumn tcNumeroRayon;
    @FXML
    private TableColumn tcNumeroEmployeRayon;
    @FXML
    private TableColumn tcNomRayon;
    @FXML
    private TableView tvSecteurs;
    @FXML
    private TableColumn tcNomEmployeAll;
    @FXML
    private TableColumn tcNomEmployeRayon;
    @FXML
    private TableColumn tcHeureEmployeRayon;
    @FXML
    private TableColumn tcNumeroSecteur;
    @FXML
    private Button btnAjouter;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField txtNbHeures;
    @FXML
    private TextField txtTotalRayon;
    @FXML
    private TextField txtTotalSecteur;


    //constructeur pour recuperer les fonction dans services
    public TP1Controller(SecteurService secteurService, EmployeService employeService) {
        this.secteurService = secteurService;
        this.employeService = employeService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Choix incorrect");
        alert.setHeaderText(null);

        tcNumeroSecteur.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomSecteur.setCellValueFactory(new PropertyValueFactory<>("nomSecteur"));

        tcNumeroRayon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomRayon.setCellValueFactory(new PropertyValueFactory<>("nomRayon"));

        tcNumeroEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("employeId"));
        tcNomEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcDateEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcHeureEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("temps"));

        tcNumeroEmployeAll.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomEmployeAll.setCellValueFactory(new PropertyValueFactory<>("nomEmploye"));

        //remplissage du table view
        tvSecteurs.setItems(FXCollections.observableArrayList(secteurService.getAllSecteur()));
        tvEmployesAll.setItems(FXCollections.observableArrayList(employeService.getAllEmploye()));
    }

    @FXML
    public void tvSecteursClicked(Event event)
    {

    }

    @FXML
    public void tvRayonsClicked(Event event)
    {

    }

    @FXML
    public void btnAjouterAction(ActionEvent actionEvent)
    {

    }
}