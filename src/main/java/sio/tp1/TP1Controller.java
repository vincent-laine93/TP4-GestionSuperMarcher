package sio.tp1;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import sio.tp1.Entity.*;
import sio.tp1.Services.EmployeService;
import sio.tp1.Services.RayonService;
import sio.tp1.Services.SecteurService;
import sio.tp1.Services.TravaillerService;
import sio.tp1.dto.EmployeRayon;


import java.net.URL;
import java.time.LocalDate;
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
    private TravaillerService travaillerService;

    @FXML
    private TableColumn tcNomSecteur;
    @FXML
    private TableView<Rayon> tvRayons;
    @FXML
    private TableView<EmployeRayon> tvEmployesRayon;
    @FXML
    private TableColumn tcNumeroEmployeAll;
    @FXML
    private TableView<Employe> tvEmployesAll;
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
    public TP1Controller(SecteurService secteurService, EmployeService employeService,RayonService rayonService,TravaillerService travaillerService) {
        this.secteurService = secteurService;
        this.employeService = employeService;
        this.rayonService = rayonService;
        this.travaillerService = travaillerService;
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
        Secteur secteurSelected = tvSecteurs.getSelectionModel().getSelectedItem();
        List<Rayon> rayonParSecteur = rayonService.getAllRayonsByIdSecteur(tvSecteurs.getSelectionModel().getSelectedItem().getId());
        tvRayons.setItems(FXCollections.observableArrayList(rayonParSecteur));

        int nbHeureTravaillerParSecteur = travaillerService.getTempsTravaillerParSecteur(secteurSelected);
        txtTotalSecteur.setText(String.valueOf(nbHeureTravaillerParSecteur));

        txtTotalRayon.setText("");
    }

    @FXML
    public void tvRayonsClicked(Event event)
    {
        Rayon rayonSelected = tvRayons.getSelectionModel().getSelectedItem();
        List<EmployeRayon> employeRayon = employeService.getAllEmployeesByRayonId(rayonSelected.getId());
        tvEmployesRayon.setItems(FXCollections.observableArrayList(employeRayon));

        int nbHeuresTravaillerParRayon = travaillerService.getTempsTravaillerParRayon(rayonSelected);
        txtTotalRayon.setText(String.valueOf(nbHeuresTravaillerParRayon));

    }

    @FXML
    public void btnAjouterAction(ActionEvent actionEvent)
    {
        //recuperation des donnee
        Rayon rayonSelected = tvRayons.getSelectionModel().getSelectedItem();
        Employe employeSelected = tvEmployesAll.getSelectionModel().getSelectedItem();
        LocalDate date = dpDate.getValue();
        String nbHeures = txtNbHeures.getText();

        if (rayonSelected == null){
            alert.setContentText("Veuillez selectionner un Rayon");
            alert.showAndWait();
        } else if (employeSelected == null) {
            alert.setContentText("Veuillez selectionner un Employe");
            alert.showAndWait();
        } else if (date == null) {
            alert.setContentText("Veuillez choisir une Date");
            alert.showAndWait();
        } else if (nbHeures == null || !nbHeures.matches("\\d+")) {
            alert.setContentText("Veuillez entrer un nombre heures travailler");
            alert.showAndWait();
        }else {
            List<EmployeRayon> employeRayon = employeService.getAllEmployeesByRayonId(rayonSelected.getId());
            for (EmployeRayon employe : employeRayon) {
                if (employe.getDateRayon().equals(date) && employe.getIdEmploye() == (employeSelected.getId())) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Choix incorrect");
                    alert.setContentText(employe.getNomEmploye() + " travaille déjà a cette date la");
                    alert.showAndWait();
                    return;
                }
            }
            //on envoie en bdd
            travaillerService.ajouterTravailler(employeSelected,rayonSelected,date,nbHeures);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation");
            alert.setContentText("Les information ont bien été enregistrer");
            alert.showAndWait();

            //pour refresh
            refresh();
        }
    }

    public void refresh(){
        Rayon rayonSelected = tvRayons.getSelectionModel().getSelectedItem();
        List<EmployeRayon> employeRayon = employeService.getAllEmployeesByRayonId(rayonSelected.getId());
        tvEmployesRayon.setItems(FXCollections.observableArrayList(employeRayon));

        int nbHeuresTravaillerParRayon = travaillerService.getTempsTravaillerParRayon(rayonSelected);
        txtTotalRayon.setText(String.valueOf(nbHeuresTravaillerParRayon));


        int nbHeureTravaillerParSecteur = travaillerService.getTempsTravaillerParSecteur(tvSecteurs.getSelectionModel().getSelectedItem());
        txtTotalSecteur.setText(String.valueOf(nbHeureTravaillerParSecteur));
    }
}