package main.Repository.Implementation;

import main.Entity.Material;
import main.Repository.GenericsRepo;
import main.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialRepoImpl implements GenericsRepo<Material> {

    private Connection connection;

    public MaterialRepoImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Material create(Material material) {
        String sql = "INSERT INTO material (nom, cout_unitaire, quantite, cout_transport, coefficient_qualite, taux_tva) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, material.getNom());
            statement.setDouble(2, material.getCoutUnitaire());
            statement.setDouble(3, material.getQuantite());
            statement.setDouble(4, material.getCoutTransport());
            statement.setDouble(5, material.getCoefficientQualite());
            statement.setDouble(6, material.getTauxTVA());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                material.setId(rs.getInt("id"));
            }
            return material;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Material> findById(int id) {
        String sql = "SELECT * FROM material WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Material material = mapResultSetToMaterial(rs);
                return Optional.of(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM material";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Material material = mapResultSetToMaterial(rs);
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    @Override
    public Material update(Material material) {
        String sql = "UPDATE material SET nom = ?, cout_unitaire = ?, quantite = ?, cout_transport = ?, coefficient_qualite = ?, taux_tva = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, material.getNom());
            statement.setDouble(2, material.getCoutUnitaire());
            statement.setDouble(3, material.getQuantite());
            statement.setDouble(4, material.getCoutTransport());
            statement.setDouble(5, material.getCoefficientQualite());
            statement.setDouble(6, material.getTauxTVA());
            statement.setInt(7, material.getId());
            statement.executeUpdate();
            return material;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to map ResultSet to a Material object
    private Material mapResultSetToMaterial(ResultSet rs) throws SQLException
    {
        Material material = new Material(
                rs.getString("nom"),
                rs.getDouble("cout_unitaire"),
                rs.getDouble("quantite"),
                rs.getDouble("taux_tva"),
                rs.getDouble("cout_transport"),
                rs.getDouble("coefficient_qualite")
        );
        material.setId(rs.getInt("id")); // Assuming Material class has an `id` attribute
        return material;
    }
}
