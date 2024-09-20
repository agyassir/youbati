package main.Entity;

public class Material extends Component {

    private double coutTransport;
    private double coefficientQualite;

    public Material(String nom, double coutUnitaire, double quantite, double tauxTVA, double coutTransport, double coefficientQualite) {
        super(nom, coutUnitaire, quantite, "Matériel", tauxTVA);
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

    @Override
    public double calculateCost() {
        double costWithoutTax = (coutUnitaire * quantite * coefficientQualite) + coutTransport;
        return costWithoutTax + (costWithoutTax * tauxTVA / 100);
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
