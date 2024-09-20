package main.Entity;

public abstract class Component {

    // Attributes common to all components
    protected int id;
    protected String nom;
    protected double coutUnitaire;
    protected double quantite;
    protected String typeComposant; // Could be "Matériel" or "Main-d'œuvre"
    protected double tauxTVA;

    // Constructor
    public Component(String nom, double coutUnitaire, double quantite, String typeComposant, double tauxTVA) {
        this.nom = nom;
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Abstract method to calculate the cost
    public abstract double calculateCost();

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }
}

