package main.Repository.Implementation;

import main.Entity.Client;
import main.Repository.GenericsRepo;
import main.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepoImpl implements GenericsRepo<Client> {

    private Connection connection;

    public ClientRepoImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Client create(Client client) {
        String sql = "INSERT INTO client (nom, adresse, telephone, \"isProfessionnel\") VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAdresse());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isProfessionnel());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("id"));
            }
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Client> findById(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Client client = mapResultSetToClient(rs);
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = mapResultSetToClient(rs);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client update(Client client) {
        String sql = "UPDATE client SET nom = ?, adresse = ?, telephone = ?, est_professionnel = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAdresse());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isProfessionnel());
            statement.setLong(5, client.getId());
            statement.executeUpdate();
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to map ResultSet to a Client object
    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getString("nom"),
                rs.getString("adresse"),
                rs.getString("telephone"),
                rs.getBoolean("est_professionnel")
        );
        client.setId(rs.getInt("id"));
        return client;
    }
}
