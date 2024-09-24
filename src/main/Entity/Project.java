package main.Entity;

import java.util.List;

public class Project {

    public enum ProjectStatus {
        EN_COURS, TERMINE, ANNULE
    }
    private Integer id;
    private String nom;
    private Double coutTotal;
    private double margeBeneficiaire;
    private  ProjectStatus etatProjet;
    private Client client;
    private List<Component> components;

    public Project(String nom, Double surface,Double marge, ProjectStatus etatProjet) {
        this.nom = nom;
        this.etatProjet = etatProjet;
        this.margeBeneficiaire=marge;
    }
    public Project(){}

    public Integer getId() {
        return id;
    }



    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public ProjectStatus getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(ProjectStatus etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "\n================================================================================================\n" +
                "=                                     Project Details                                            =\n" +
                "================================================================================================\n" +
                "  Project ID        : " + id + "\n" +
                "  Project Name      : '" + nom + "'\n" +
                "  Profit Margin     : " + margeBeneficiaire + "%\n" +
                "  Total Cost        : " + coutTotal + " DH\n" +
                "  Total Cost with Profit Margin: " + coutTotal+(coutTotal*margeBeneficiaire)/100 + " DH\n" +
                "  Status            : " + etatProjet + "\n" +
                "  Client Name       : " + client.getFirstName() + "\n" +
                "================================================================================================\n";

    }
}
