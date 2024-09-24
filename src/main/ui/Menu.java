package main.ui;
import main.Entity.*;
import main.Service.ClientServiceInterface;
import main.Service.ComponentServiceInterface;
import main.Service.ProjectServiceInterface;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {

    // Main Menu
    public static int displayMainMenu(Scanner scan) {
        printMainMenuHeader();
        printMainMenuOptions();
        return getMenuSelection(scan);
    }

    // Client Menu
    public static int displayClientMenu(Scanner scan) {
        printClientMenuHeader();
        printClientMenuOptions();
        return getMenuSelection(scan);
    }

    // Common method to get menu selection
    private static int getMenuSelection(Scanner scan) {
        System.out.print("\nVeuillez choisir une option: ");
        return scan.nextInt();
    }

    // Method to display Main Menu Header
    public static void printMainMenuHeader() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║        Bienvenue dans l'application de gestion des projets        ║");
        System.out.println("║               de rénovation de cuisines                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
    }

    // Method to display Main Menu Options
    private static void printMainMenuOptions() {
        System.out.println("╔═════════════════════════╗");
        System.out.println("║      *** Menu ***       ║");
        System.out.println("╚═════════════════════════╝");
        System.out.println("1. ➤ Créer un nouveau projet");
        System.out.println("2. ➤ Afficher les projets existants");
        System.out.println("3. ➤ Calculer le coût d'un projet");
        System.out.println("4. ➤ Quitter");
    }

    // Method to display Client Menu Header
    private static void printClientMenuHeader() {
        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       🔍 Recherche de client                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");
    }

    public static Project   printProjectMenuHeader(Scanner scanner, ComponentServiceInterface<Material> materialService,ComponentServiceInterface<Labor> laborService, double taxRate) {
        List<Material> insertedMaterials = new ArrayList<>();
        List<Labor> insertedLabor = new ArrayList<>();
        List<Component> components = new ArrayList<>();
        int choice;
        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       \uD83D\uDEE0\uFE0F create the project                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");
        System.out.println("1. ➤ nom de projet");
        scanner.nextLine();
        String nom = scanner.nextLine();
        System.out.println("2. ➤ surface du cuisine");
        Double surface = scanner.nextDouble();

        do {
            Material mat = createMaterial(materialService, scanner, taxRate);
            insertedMaterials.add(mat);
            components.add(mat);
            System.out.println("Do you want to add another material? (YES:1 / NO:2): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice == 1);
        displayMaterialDetails(insertedMaterials,materialService);

        int choice2;
        do {
            Labor labor = addLabor(laborService, scanner, taxRate);
            insertedLabor.add(labor);
            components.add(labor);
            System.out.println("Do you want to add another labor? (YES:1 / NO:2): ");
            choice2 = scanner.nextInt();
            scanner.nextLine();
        } while (choice2 == 1);
        displayLaborDetails(insertedLabor,laborService);

        System.out.println("Do you want to add a profit margin ? (YES:1 / NO:2): ");
        Double marge=0.0;
        if (scanner.nextInt()==1){
            System.out.println(" Insert your profit margin:  ");
            marge=scanner.nextDouble();

        }

        Project project = new Project(nom,surface,marge, Project.ProjectStatus.EN_COURS);
        project.setComponents(components);
        return project;
    }

    public static Material createMaterial(ComponentServiceInterface<Material> materialService,Scanner scanner,Double TVA) {

        System.out.println("================================================================================================");
        scanner.nextLine();
        System.out.println("    Enter material name: ");
        String name = scanner.nextLine();
        System.out.println("Enter material Quantity (/m2): ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material unit cost (DH): ");
        double unitCost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material transport cost (DH): ");
        double transportCost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material quality coefficient (5 = standard - 10 best quality): ");
        double qualityCoefficient = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("================================================================================================");
        Material material=new Material(name, unitCost,quantity,TVA,  transportCost, qualityCoefficient );
        return materialService.create(material);
    }

    public static Labor addLabor(ComponentServiceInterface<Labor> laborService,Scanner scanner,Double TVA) {
        System.out.println("================================================================================================");
        System.out.println("Enter labor name: ");
        String name = scanner.nextLine();
        System.out.println("Enter labor hourly rate (? DH per 1h): ");
        double hourlyRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter labor working hours : ");
        double workingHours = scanner.nextDouble();
        System.out.println("Enter labor worker productivity : ");
        double workerProductivity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("================================================================================================");
        Labor labor = new Labor(name, hourlyRate, workingHours, workerProductivity,TVA);
        laborService.create(labor);
        return labor;
    }

    public static int affichageMenu(Scanner scan){
        System.out.println("╔═════════════════════════╗");
        System.out.println("║         Affichage       ║");
        System.out.println("╚═════════════════════════╝");
        System.out.println("1. ➤ Myprojects");
        System.out.println("2. ➤ All projects");
        System.out.println("3. ➤ Quitter");
        return getMenuSelection(scan);

    }

    private static void printClientMenuOptions() {
        System.out.println("Souhaitez-vous :");
        System.out.println("1. 🔎 Chercher un client existant");
        System.out.println("2. ➕ Ajouter un nouveau client");
    }

    public static HashMap<String,String> ClientSearch(Scanner scan){
        printClientMenuHeader();
        HashMap<String,String> name=new HashMap<>();
        System.out.println("Souhaitez-vous :");
        System.out.println("Entrer le prenom");
        scan.nextLine();
        String fname=scan.nextLine();
        System.out.println("Entrer le nom");
        String lname=scan.nextLine();
        name.put("fname",fname);
        name.put("lname",lname);
        return name;
    }

    public static Client viewClient(Client client){
        System.out.println("Détails de l'utilisateur : ");
        System.out.printf("%-15s : %s%n", "ID", client.getId());
        System.out.printf("%-15s : %s%n", "First Name", client.getFirstName());
        System.out.printf("%-15s : %s%n", "Last Name", client.getLastName());
        System.out.printf("%-15s : %s%n", "Adresse", client.getAdresse());
        System.out.printf("%-15s : %s%n", "Téléphone", client.getTelephone());
        System.out.printf("%-15s : %s%n", "Professionnel", client.isProfessionnel() ? "Oui" : "Non");
        return client;
    }

    public static Client viewClients(List<Client> clients,ClientServiceInterface clientService,Scanner scanner) {
        System.out.printf("%-5s %-20s %-20s %-15s %-15s%n", "ID", "FirstName", "LastName", "adresse");
        System.out.println("-------------------------------------------------------------------------------");
        for (Client c : clients) {
            System.out.printf("%-5d %-20s %-20s %-15s ",
                    c.getId(), c.getFirstName(), c.getLastName(), c.getAdresse()
            );
        }
        System.out.println("which one are you looking for (choose id)");

        int choix = getMenuSelection(scanner);
        Optional<Client> clientOpt = clientService.getById(choix);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();  // Get the client if present
            return viewClient(client);

        } else {
            System.out.println("Client with ID " + choix + " not found.");
            viewClients(clients, clientService, scanner);
            return null;
        }

    }

    public static void AjouterClient(Scanner scanner, ClientServiceInterface clientService){
        Client client=null;

        System.out.println("insert firstname(family name):");
        scanner.nextLine();
        String fname=scanner.nextLine();
        System.out.println("insert lastname(personnal name):");
        String lname=scanner.nextLine();
        System.out.println("insert his adresse:");
        String adr=scanner.nextLine();
        System.out.println("insert his number:");
        String number= scanner.nextLine();
        System.out.println("is he a professionnal labor:(1.yes 2.no) ");
        int pro=scanner.nextInt();
        if (pro==1){
         client=new Client(fname,lname,adr,number,true);}else {
            client=new Client(fname,lname,adr,number,false);
        }
        clientService.create(client);

    }

    public static void MyProjects(Scanner scanner, ProjectServiceInterface projectService,ClientServiceInterface clientService){
        List<Project> myProject=new ArrayList<>();
        System.out.println("would you like to use your id(1) or you name(2)");
        int ch=scanner.nextInt();
        if(ch==1){
            System.out.println("please insert you id");
            int id=scanner.nextInt();

            myProject=projectService.MyprojectsbyID(id);

            System.out.printf("%-5s %-20s %-20s %-15s %-15s%n", "ID", "nomproject", "coutotal", "etatproject", "client_id");
            System.out.println("-------------------------------------------------------------------------------");
            for (Project p : myProject) {
                System.out.printf("%-5d %-20s %-20s %-15s %-15s%n",
                        p.getId(), p.getNom(), p.getCoutTotal(), p.getEtatProjet(), id
                );
            }
        }else if (ch==2){
            Client client=null;
            HashMap<String,String> name = Menu.ClientSearch(scanner);
            List<Client> clients=  clientService.findByName(name.get("fname"), name.get("lname"));
            if (clients.size()==0){
                System.out.println("Utilisateur non trouvé !");
            } else if (clients.size()==1) {
                client=Menu.viewClient(clients.get(0));
            }else{
                client= Menu.viewClients(clients,clientService,scanner);
            }

            myProject=projectService.MyprojectsbyID(client.getId());

            System.out.printf("%-5s %-20s %-20s %-15s %-15s%n", "ID", "nomproject", "coutotal", "etatproject", "client_id");
            System.out.println("-------------------------------------------------------------------------------");
            for (Project p : myProject) {
                p.setClient(client);
                System.out.printf("%-5d %-20s %-20s %-15s %-15s%n",
                        p.getId(), p.getNom(), p.getCoutTotal(), p.getEtatProjet(), p.getClient() != null ? p.getClient().getFirstName()+" "+p.getClient().getLastName() : "Aucun utilisateur"
                );
            }

        }


    }
    public static void AllProject(ProjectServiceInterface projectService){
        List<Project> myProject=new ArrayList<>();

        myProject=projectService.getAll();

        System.out.printf("%-5s %-20s %-20s %-15s %-15s%n", "ID", "nomproject", "coutotal", "etatproject","Client");
        System.out.println("-------------------------------------------------------------------------------");
        for (Project p : myProject) {
            System.out.printf("%-5d %-20s %-20s %-15s %-15s%n",
                    p.getId(), p.getNom(), p.getCoutTotal(), p.getEtatProjet(),p.getClient().getFirstName()
            );
        }
    }
    public static void displayMaterialDetails(List<Material>materials,ComponentServiceInterface<Material> materialService) {
        System.out.println("================================================================================================");
        System.out.println("=                                     Material Details                                            ");
        System.out.println("================================================================================================");
        System.out.println("------------------------------------------------------------------------------------------------");
        Double totalwtiva=0.0;
        Double total=0.0;
        for (Material material : materials) {
            Double mcost=materialService.CostWTVA(material);
            Double mcosttva=materialService.calculateCost(material);
            System.out.println("[" + material.getNom() + "]" + "\t\t Quantity : " + material.getQuantite() + "\t\tUnit Cost : " + material.getCoutUnitaire() + "\t\t Transport Cost : " + material.getCoutTransport() + "\t\t Quality Coefficient : " + material.getCoefficientQualite() + "\n" +
                    "Total Cost: " + mcost + "\n" +
                    "Total Cost with VAT: " + mcosttva + "\n");
            System.out.println("------------------------------------------------------------------------------------------------");
            total+=mcost;
            totalwtiva+=mcosttva;
        }

        System.out.println("Total Cost for all materials without VAT: " + total);
        System.out.println("Total Cost for all materials with VAT: " + totalwtiva);
        System.out.println("================================================================================================");
    }
    public static void displayLaborDetails(List<Labor> labors,ComponentServiceInterface<Labor> laborService) {
        Double total=0.0;
               Double totaltva=0.0;

        for (Labor labor : labors) {
            System.out.println("[" + labor.getNom() + "]" + "\t\t Hourly Rate : " + labor.getTauxHoraire() + "\t\t Working Hours : " + labor.getHeuresTravail() + "\t\t Worker Productivity : " + labor.getProductiviteOuvrier() + "\n" +
                    "Total Cost: " + laborService.CostWTVA(labor) + "\n" +
                    "Total Cost with VAT: " + laborService.calculateCost(labor) + "\n");
            System.out.println("------------------------------------------------------------------------------------------------");
        total+=laborService.CostWTVA(labor);
        totaltva+=laborService.calculateCost(labor);
        }



        System.out.println("Total Cost for all labors without VAT: " + total);
        System.out.println("Total Cost for all labors with VAT: " + totaltva);

    }
        public static Estimate quoteDisplay(Scanner scanner){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) :");
        String emission = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(emission, formatter);
        Date dateEmission = Date.from(checkInDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) :");
        String validity = scanner.nextLine();
        LocalDate validityDate = LocalDate.parse(validity, formatter);
        Date dateValidite = Date.from(validityDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("are you sure you want to proceed?? : (1.yes / 2.no)");
        int choix=scanner.nextInt();
        if (choix==1){
            Estimate estimate=new Estimate(dateEmission,dateValidite,true);
            return estimate;
        }else{
            Estimate estimate=new Estimate(dateEmission,dateValidite,false);
            return estimate;
        }


    }

}
