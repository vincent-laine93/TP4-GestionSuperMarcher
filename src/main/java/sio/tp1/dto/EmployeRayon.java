package sio.tp1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeRayon
{
    private int idEmploye;
    private String nomEmploye;
    private LocalDate dateRayon;
    private int temps;

    public EmployeRayon(int idEmploye, String nomEmploye, LocalDate dateRayon, int temps) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.dateRayon = dateRayon;
        this.temps = temps;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public LocalDate getDateRayon() {
        return dateRayon;
    }

    public int getTemps() {
        return temps;
    }
}
