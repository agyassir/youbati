package main.Repository.Implementation;

import main.Entity.Labor;
import main.Repository.GenericsRepo;
import main.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LaborRepoImpl implements GenericsRepo<Labor> {
    private Connection connection;

    public LaborRepoImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Labor create(Labor labor) {
        String sql = "INSERT INTO labor (nom, taux_horaire, heures_travail, productivite_ouvrier, taux_tva) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, labor.getNom());
            statement.setDouble(2, labor.getTauxHoraire());
            statement.setDouble(3, labor.getHeuresTravail());
            statement.setDouble(4, labor.getProductiviteOuvrier());
            statement.setDouble(5, labor.getTauxTVA());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                labor.setId(rs.getInt("id"));
            }
            return labor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Labor> findById(int id) {
        String sql = "SELECT * FROM labor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Labor labor = mapResultSetToLabor(rs);
                return Optional.of(labor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Labor> findAll() {
        List<Labor> labors = new ArrayList<>();
        String sql = "SELECT * FROM labor";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Labor labor = mapResultSetToLabor(rs);
                labors.add(labor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labors;
    }

    @Override
    public Labor update(Labor labor) {
        String sql = "UPDATE labor SET nom = ?, taux_horaire = ?, heures_travail = ?, productivite_ouvrier = ?, taux_tva = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, labor.getNom());
            statement.setDouble(2, labor.getTauxHoraire());
            statement.setDouble(3, labor.getHeuresTravail());
            statement.setDouble(4, labor.getProductiviteOuvrier());
            statement.setDouble(5, labor.getTauxTVA());
            statement.setLong(6, labor.getId());
            statement.executeUpdate();
            return labor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM labor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to map ResultSet to a Labor object
    private Labor mapResultSetToLabor(ResultSet rs) throws SQLException {
        Labor labor = new Labor(
                rs.getString("nom"),
                rs.getDouble("taux_horaire"),
                rs.getDouble("heures_travail"),
                rs.getDouble("productivite_ouvrier"),
                rs.getDouble("taux_tva")
        );
        labor.setId(rs.getInt("id")); // Assuming Labor class has an `id` attribute
        return labor;
    }


}
