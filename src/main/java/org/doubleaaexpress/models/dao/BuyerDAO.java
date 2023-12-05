package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void addBuyer(Buyer buyer) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO buyers (buyer_id, first_Name, last_name, phone_number, birth_date, email, password) " +
                "VALUES (?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(buyer.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, buyer.getId());
            preparedStatement.setString(2, buyer.getFirstName());
            preparedStatement.setString(3, buyer.getLastName());
            preparedStatement.setString(4, buyer.getPhoneNumber());
            preparedStatement.setDate(5, birthDate);
            preparedStatement.setString(6, buyer.getEmail());
            preparedStatement.setString(7, buyer.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding buyer: " + e.getMessage());
        }
    }

    public Buyer getBuyerById(Long id) {
        String sql = "SELECT * FROM buyers WHERE buyer_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Buyer buyer = new Buyer();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                buyer.setId(resultSet.getLong("buyer_id"));
                buyer.setFirstName(resultSet.getString("first_name"));
                buyer.setLastName(resultSet.getString("last_name"));
                buyer.setPhoneNumber(resultSet.getString("phone_number"));
                buyer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                buyer.setEmail(resultSet.getString("email"));
                buyer.setPassword(resultSet.getString("password"));
            } else
                JOptionPane.showMessageDialog(null, "The buyer is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting buyer: " + e.getMessage());
        }
        return buyer;
    }

    public void updateBuyer(Buyer buyer) {
        PreparedStatement ps;
        String sql = "UPDATE buyers SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, email = ?, " +
                "password = ? WHERE buyer_id = ?";
        Date birthDate = Date.valueOf(buyer.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, buyer.getFirstName());
            ps.setString(2, buyer.getLastName());
            ps.setString(3, buyer.getPhoneNumber());
            ps.setDate(4, birthDate);
            ps.setString(5, buyer.getEmail());
            ps.setString(6, buyer.getPassword());
            ps.setLong(7, buyer.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating buyer: " + e.getMessage());
        }
    }

    public void deleteBuyer(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM buyers WHERE buyer_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting buyer: " + e.getMessage());
        }
    }

    public List<Buyer> getAllBuyers() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM buyers";
        ResultSet resultSet;
        List<Buyer> buyers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(resultSet.getLong("buyer_id"));
                buyer.setFirstName(resultSet.getString("first_name"));
                buyer.setLastName(resultSet.getString("last_name"));
                buyer.setPhoneNumber(resultSet.getString("phone_number"));
                buyer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                buyer.setEmail(resultSet.getString("email"));
                buyer.setPassword(resultSet.getString("password"));
                buyers.add(buyer);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all buyers: " + e.getMessage());
        }
        return buyers;
    }
}
