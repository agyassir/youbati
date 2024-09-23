package main.Entity;

public class Client {

    private int id;
    private String FirstName;
    private String LastName;
    private String adresse;
    private String telephone;
    private boolean isProfessionnel;

    public Client(String fnom,String lname, String adresse, String telephone, boolean isProfessionnel) {
        this.FirstName = fnom;
        this.LastName = lname;
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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isProfessionnel=" + isProfessionnel +
                '}';
    }

    public double applyDiscount(double montant) {
        double discount = 0.0;
        if (isProfessionnel) {
            discount = 0.10; // 10% discount for professionals
        }
        return montant * (1 - discount);
    }
}

