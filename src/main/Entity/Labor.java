package main.Entity;

public class Labor extends Component {

    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    public Labor(String nom, double tauxHoraire, double heuresTravail, double productiviteOuvrier, double tauxTVA) {
        super(nom, tauxHoraire, heuresTravail, "Main-d'œuvre", tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public double calculateCost() {
        double costWithoutTax = tauxHoraire * heuresTravail * productiviteOuvrier;
        return costWithoutTax + (costWithoutTax * tauxTVA / 100);
    }

    @Override
    public String toString() {
        return "Labor{" +
                "nom='" + nom + '\'' +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", tauxTVA=" + tauxTVA +
                '}';
    }
}
