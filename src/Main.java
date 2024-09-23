import main.Entity.*;
import main.Repository.ClientRepo;
import main.Repository.EstimateRepo;
import main.Repository.GenericsRepo;
import main.Repository.Implementation.*;
import main.Service.ClientServiceInterface;
import main.Service.ComponentServiceInterface;
import main.Service.GenericService;
import main.Service.Implementation.*;
import main.Service.ProjectServiceInterface;
import main.ui.Menu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);

        //hna kayn repositories
        GenericsRepo<Project> projectRepo= new ProjectRepoImpl();
        GenericsRepo<Material> materialRepo = new MaterialRepoImpl();
        ClientRepo clientRepo = new ClientRepoImpl();
        EstimateRepo EstimateRepo = new EstimateRepoImpl();
        GenericsRepo<Labor> laborRepo = new LaborRepoImpl();
        //hna ghadi n3iyet 3la
        ComponentServiceInterface<Material> materialService=new MaterialServiceImpl(materialRepo);
        ComponentServiceInterface<Labor> laborService = new LaborServiceImpl(laborRepo);
        ProjectServiceInterface projectService = new ProjectServiceImpl(projectRepo,materialService,laborService);
        ClientServiceInterface clientService = new ClientServiceImpl(clientRepo);
        GenericService<Estimate> estimateService = new EstimateServiceImpl(EstimateRepo);
        Client client=null;
        int choice =Menu.displayMainMenu(scanner);
        switch (choice){
            case 1:
            int choice2=Menu.displayClientMenu(scanner);
            switch (choice2){
                case 1:
                    HashMap<String,String> name = Menu.ClientSearch(scanner);
                    List<Client> clients=  clientService.findByName(name.get("fname"), name.get("lname"));
                    if (clients.size()==0){
                        System.out.println("Utilisateur non trouvé !");
                    } else if (clients.size()==1) {
                         client=Menu.viewClient(clients.get(0));
                    }else{
                       client= Menu.viewClients(clients,clientService,scanner);
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("\nChoix invalide ! Veuillez réessayer.");
            }
            System.out.println("add tva: (%)");
            Double tva=scanner.nextDouble();
            Project projet= Menu.printProjectMenuHeader(scanner,materialService,laborService,tva);
            projet.setCoutTotal(projectService.calculateTotalCost(projet));
            projet.setClient(client);
                projectService.create(projet);
            break;
            case 2:
                break;
        }

    }
}