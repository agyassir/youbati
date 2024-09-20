package main.Entity;

public class Client {

    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean isProfessionnel;

    public Client(String nom, String adresse, String telephone, boolean isProfessionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.isProfessionnel = isProfessionnel;
    }

    public int getId() {
        return id;
    }

    public boolean isProfessionnel() {
        return isProfessionnel;
    }

    public void setProfessionnel(boolean professionnel) {
        isProfessionnel = professionnel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isheProfessionnel() {
        return isProfessionnel;
    }

    public void setisProfessionnel(boolean isProfessionnel) {
        this.isProfessionnel = isProfessionnel;
    }

@Override
    public String toString() {
        return "Nom: " + nom +
        "\n Adresse: " + adresse +
        "\n Téléphone: " + telephone +
        "\n Type de Client: " + (isProfessionnel ? "Professionnel" : "Particulier");
    }


    public double applyDiscount(double montant) {
        double discount = 0.0;
        if (isProfessionnel) {
            discount = 0.10; // 10% discount for professionals
        }
        return montant * (1 - discount);
    }
}

