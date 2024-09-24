package main.Entity;

public class Material extends Component {

    private double coutTransport;
    private double coefficientQualite;
    private double coutUnitaire;
    private double quantite;

    public Material(String nom, double coutUnitaire, double quantite, double tauxTVA, double coutTransport, double coefficientQualite) {
        super(nom, tauxTVA);
        this.coutUnitaire=coutUnitaire;
        this.quantite=quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
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

    @Override
    public String toString() {
        return "Matériel: " + nom +
                ", Coût Unitaire: " + coutUnitaire +
                ", Quantité: " + quantite +
                ", Coût Transport: " + coutTransport +
                ", Coefficient Qualité: " + coefficientQualite +
                ", Taux TVA: " + tauxTVA;
    }




}
