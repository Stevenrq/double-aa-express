package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Seller;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void addSeller(Seller seller) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO sellers (seller_id, first_Name, last_name, phone_number, birth_date, email, password) " +
                "VALUES (?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(seller.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, seller.getId());
            preparedStatement.setString(2, seller.getFirstName());
            preparedStatement.setString(3, seller.getLastName());
            preparedStatement.setString(4, seller.getPhoneNumber());
            preparedStatement.setDate(5, birthDate);
            preparedStatement.setString(6, seller.getEmail());
            preparedStatement.setString(7, seller.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding seller: " + e.getMessage());
        }
    }

    public Seller getSellerById(Long id) {
        String sql = "SELECT * FROM sellers WHERE seller_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Seller seller = new Seller();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                seller.setId(resultSet.getLong("seller_id"));
                seller.setFirstName(resultSet.getString("first_name"));
                seller.setLastName(resultSet.getString("last_name"));
                seller.setPhoneNumber(resultSet.getString("phone_number"));
                seller.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                seller.setEmail(resultSet.getString("email"));
                seller.setPassword(resultSet.getString("password"));
            } else
                JOptionPane.showMessageDialog(null, "The seller is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting seller: " + e.getMessage());
        }
        return seller;
    }

    public void updateSeller(Seller seller) {
        PreparedStatement ps;
        String sql = "UPDATE sellers SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, email = ?, " +
                "password = ? WHERE seller_id = ?";
        Date birthDate = Date.valueOf(seller.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, seller.getFirstName());
            ps.setString(2, seller.getLastName());
            ps.setString(3, seller.getPhoneNumber());
            ps.setDate(4, birthDate);
            ps.setString(5, seller.getEmail());
            ps.setString(6, seller.getPassword());
            ps.setLong(7, seller.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating seller: " + e.getMessage());
        }
    }

    public void deleteSeller(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM sellers WHERE seller_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting seller: " + e.getMessage());
        }
    }

    public List<Seller> getAllSellers() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM sellers";
        ResultSet resultSet;
        List<Seller> sellers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Seller seller = new Seller();
                seller.setId(resultSet.getLong("seller_id"));
                seller.setFirstName(resultSet.getString("first_name"));
                seller.setLastName(resultSet.getString("last_name"));
                seller.setPhoneNumber(resultSet.getString("phone_number"));
                seller.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                seller.setEmail(resultSet.getString("email"));
                seller.setPassword(resultSet.getString("password"));
                sellers.add(seller);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all sellers: " + e.getMessage());
        }
        return sellers;
    }
}
