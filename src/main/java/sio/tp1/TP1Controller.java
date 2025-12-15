package sio.tp1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import sio.tp1.Entity.Rayon;
import sio.tp1.Entity.Secteur;
import sio.tp1.Services.EmployeService;
import sio.tp1.Services.RayonService;
import sio.tp1.Services.SecteurService;
import sio.tp1.dto.EmployeRayon;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TP1Controller implements Initializable
{

    Alert alert;

    //recuperation
    private SecteurService secteurService;
    private EmployeService employeService;
    private RayonService rayonService;

    @FXML
    private TableColumn tcNomSecteur;
    @FXML
    private TableView<Rayon> tvRayons;
    @FXML
    private TableView<EmployeRayon> tvEmployesRayon;
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
    private TableView<Secteur> tvSecteurs;
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
    public TP1Controller(SecteurService secteurService, EmployeService employeService,RayonService rayonService) {
        this.secteurService = secteurService;
        this.employeService = employeService;
        this.rayonService = rayonService;
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

        tcNumeroEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
        tcNomEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("nomEmploye"));
        tcDateEmployeRayon.setCellValueFactory(new PropertyValueFactory<>("dateRayon"));
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
        List<Rayon> rayonParSecteur = rayonService.getAllRayonsByIdSecteur(tvSecteurs.getSelectionModel().getSelectedItem().getId());
        tvRayons.setItems(FXCollections.observableArrayList(rayonParSecteur));

    }

    @FXML
    public void tvRayonsClicked(Event event)
    {
        int idRayonSelected = tvRayons.getSelectionModel().getSelectedItem().getId();
        List<EmployeRayon> employeRayon = employeService.getAllEmployeesByRayonId(idRayonSelected);
        tvEmployesRayon.setItems(FXCollections.observableArrayList(employeRayon));
    }

    @FXML
    public void btnAjouterAction(ActionEvent actionEvent)
    {

    }
}