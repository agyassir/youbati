package main.Repository.Implementation;

import main.Entity.Client;
import main.Entity.Component;
import main.Entity.Project;
import main.Repository.GenericsRepo;
import main.Repository.ProjectRepo;
import main.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepoImpl implements ProjectRepo {


    private Connection connection;

    public ProjectRepoImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Project create(Project project) {
        String sql = "INSERT INTO project (nom_projet, marge_beneficiaire, cout_total, etat_projet, client_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getNom());
            statement.setDouble(2, project.getMargeBeneficiaire());
            statement.setDouble(3, project.getCoutTotal());
            statement.setString(4, project.getEtatProjet().toString()); // Assuming `etatProjet` is stored as a String
            statement.setLong(5, project.getClient().getId()); // Assuming `Client` has an `id` field

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                project.setId(rs.getInt("id"));
                project.getComponents();
            }
            addComponents(project);
            return project;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Project> findById(int id) {
        String sql = "SELECT * FROM project WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Project project = SetToProject(rs);
                return Optional.of(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Project project = SetToProject(rs);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project update(Project project) {
        String sql = "UPDATE project SET nom_projet = ?, marge_beneficiaire = ?, cout_total = ?, etat_projet = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getNom());
            statement.setDouble(2, project.getMargeBeneficiaire());
            statement.setDouble(3, project.getCoutTotal());
            statement.setString(4, project.getEtatProjet().toString());
            statement.setLong(5, project.getClient().getId());
            statement.setLong(6, project.getId());
            statement.executeUpdate();
            return project;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM project WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to map ResultSet to a Project object
    private Project SetToProject(ResultSet rs) throws SQLException {
        // Assuming Project has a ProjectStatus Enum, and client is loaded separately
        Project project = new Project(
                rs.getString("nom_projet"),
                rs.getDouble("cout_total"),
                rs.getDouble("marge_beneficiaire"),
                Project.ProjectStatus.valueOf(rs.getString("etat_projet"))
        );
        project.setId(rs.getInt("id"));
        project.setCoutTotal(rs.getDouble("cout_total"));

        // Fetch the Client entity using client_id (this can be done by using ClientRepository)
        int clientId = rs.getInt("client_id");
        Optional<Client> client = new ClientRepoImpl().findById(clientId);
        client.ifPresent(project::setClient);

        return project;
    }

    @Override

    public List<Project> findMyProjectByid(int id){
        List<Project> projects=new ArrayList<>();
        String sql="SELECT * FROM project WHERE client_id=?";
        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Project prjet= SetToProject(rs);
                projects.add(prjet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }


    public void addComponents(Project project){
        String sql = "UPDATE component SET project_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Component component : project.getComponents()) {

                statement.setInt(1, project.getId());

                statement.setInt(2, component.getId());

                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
