package main.Repository.Implementation;

import main.Entity.Estimate;
import main.Entity.Project;
import main.Repository.EstimateRepo;
import main.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstimateRepoImpl implements EstimateRepo {

    private Connection connection;

    public EstimateRepoImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Estimate create(Estimate estimate) {
        String sql = "INSERT INTO estimate (montant_estime, date_emission, date_validite, accepte, project_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, estimate.getMontantEstime());
            statement.setDate(2, new java.sql.Date(estimate.getDateEmission().getTime()));
            statement.setDate(3, new java.sql.Date(estimate.getDateValidite().getTime()));
            statement.setBoolean(4, estimate.isAccepte());
            statement.setLong(5, estimate.getProject().getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                estimate.setId(rs.getInt("id"));
            }
            return estimate;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Estimate> findById(int id) {
        String sql = "SELECT * FROM estimate WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Estimate estimate = mapResultSetToEstimate(rs);
                return Optional.of(estimate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Estimate> findAll() {
        List<Estimate> estimates = new ArrayList<>();
        String sql = "SELECT * FROM estimate";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Estimate estimate = mapResultSetToEstimate(rs);
                estimates.add(estimate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimates;
    }

    @Override
    public Estimate update(Estimate estimate) {
        String sql = "UPDATE estimate SET montant_estime = ?, date_emission = ?, date_validite = ?, accepte = ?, project_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, estimate.getMontantEstime());
            statement.setDate(2, new java.sql.Date(estimate.getDateEmission().getTime()));
            statement.setDate(3, new java.sql.Date(estimate.getDateValidite().getTime()));
            statement.setBoolean(4, estimate.isAccepte());
            statement.setLong(5, estimate.getProject().getId());
            statement.setLong(6, estimate.getId());
            statement.executeUpdate();
            return estimate;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM estimate WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Estimate> findByProjectId(int projectId) {
        List<Estimate> estimates = new ArrayList<>();
        String sql = "SELECT * FROM estimate WHERE project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, projectId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Estimate estimate = mapResultSetToEstimate(rs);
                estimates.add(estimate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimates;
    }

    // Utility method to map ResultSet to Estimate object
    private Estimate mapResultSetToEstimate(ResultSet rs) throws SQLException {
        Optional<Project> oproject = new ProjectRepoImpl()
                .findById(rs.getInt("project_id"));

        Estimate estimate = new Estimate(
                rs.getDouble("montant_estime"),
                rs.getDate("date_emission"),
                rs.getDate("date_validite"),
                rs.getBoolean("accepte")
        );

        estimate.setProject(oproject.orElse(null));



        return estimate;
    }
}

