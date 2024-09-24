package main.Entity;

import java.util.Date;
import java.util.Optional;

public class Estimate {

    private int id;
    private double montantEstime;
    private Date dateEmission;
    private Date dateValidite;
    private boolean accepte;
    private Project project;

    public Estimate( Date dateEmission, Date dateValidite, boolean accepte) {
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // Getters and Setters

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public boolean isEstimateValid() {
        Date currentDate = new Date();
        return currentDate.before(this.dateValidite);
    }


    public void displayEstimateDetails() {
        System.out.println("Montant Estimé: " + montantEstime);
        System.out.println("Date d'Émission: " + dateEmission);
        System.out.println("Date de Validité: " + dateValidite);
        System.out.println("Estimation Acceptée: " + (accepte ? "Oui" : "Non"));
    }

    @Override
    public String toString() {
        return "\n================================================================================================\n" +
                "=                                     Quote Details                                           =\n" +
                "================================================================================================\n" +
                "  Estimated Amount  : " + montantEstime + " DH\n" +
                "  Date of Emission  : " + dateEmission + "\n" +
                "  Validity Date     : " + dateValidite + "\n" +
                "  Accepted          : " + (accepte ? "Yes" : "No") + "\n" +
                "================================================================================================\n";
    }

}

