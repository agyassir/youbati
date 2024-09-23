package main.ui;
import main.Entity.*;
import main.Service.ClientServiceInterface;
import main.Service.ComponentServiceInterface;

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

    public static List<Component>   printProjectMenuHeader(Scanner scanner, ComponentServiceInterface<Material> materialService,ComponentServiceInterface<Labor> laborService, double taxRate) {
        List<Component> insertedMaterials = new ArrayList<>();
        int choice;
        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       \uD83D\uDEE0\uFE0F create the project                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");
        System.out.println("1. ➤ nom de projet");
        scanner.nextLine();
        String nom = scanner.nextLine();
        System.out.println("2. ➤ surface du cuisine");
        int surface = scanner.nextInt();

        do {
            Material mat = createMaterial(materialService, scanner, taxRate);
            insertedMaterials.add(mat);
            System.out.println("Do you want to add another material? (YES:1 / NO:2): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice == 1);

        int choice2;
        do {
            Labor labor = addLabor(laborService, scanner, taxRate);
            insertedMaterials.add(labor);
            System.out.println("Do you want to add another labor? (YES:1 / NO:2): ");
            choice2 = scanner.nextInt();
            scanner.nextLine();
        } while (choice2 == 1);


        return insertedMaterials;
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
        return laborService.create(labor);
    }


    // Method to display Client Menu Options
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
}
