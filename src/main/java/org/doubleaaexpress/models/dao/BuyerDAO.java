package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAO implements GenericUserDAO<Buyer> {

    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void add(Buyer buyer) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO buyers (buyer_id, first_Name, last_name, phone_number, address, birth_date, email, password) VALUES (?,?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(buyer.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, buyer.getId());
            preparedStatement.setString(2, buyer.getFirstName());
            preparedStatement.setString(3, buyer.getLastName());
            preparedStatement.setString(4, buyer.getPhoneNumber());
            preparedStatement.setString(5, buyer.getAddress());
            preparedStatement.setDate(6, birthDate);
            preparedStatement.setString(7, buyer.getEmail());
            preparedStatement.setString(8, buyer.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding buyer: " + e.getMessage());
        }
    }

    @Override
    public Buyer getById(Long id) {
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
                buyer.setAddress(resultSet.getString("address"));
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

    @Override
    public void update(Buyer buyer) {
        PreparedStatement ps;
        String sql = "UPDATE buyers SET first_name = ?, last_name = ?, phone_number = ?, address = ?, birth_date = ?, email = ?, password = ? WHERE buyer_id = ?";
        Date birthDate = Date.valueOf(buyer.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, buyer.getFirstName());
            ps.setString(2, buyer.getLastName());
            ps.setString(3, buyer.getPhoneNumber());
            ps.setString(4, buyer.getAddress());
            ps.setDate(5, birthDate);
            ps.setString(6, buyer.getEmail());
            ps.setString(7, buyer.getPassword());
            ps.setLong(8, buyer.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating buyer: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
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

    @Override
    public List<Buyer> getAll() {
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
                buyer.setAddress(resultSet.getString("address"));
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

    @Override
    public boolean get(String email, String password) {
        String sql = "SELECT * FROM buyers WHERE email = ? and  password = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Buyer buyer = new Buyer();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                buyer.setId(resultSet.getLong("buyer_id"));
                buyer.setFirstName(resultSet.getString("first_name"));
                buyer.setLastName(resultSet.getString("last_name"));
                buyer.setPhoneNumber(resultSet.getString("phone_number"));
                buyer.setAddress(resultSet.getString("address"));
                buyer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                buyer.setEmail(resultSet.getString("email"));
                buyer.setPassword(resultSet.getString("password"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error getting buyer: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Buyer getUser(String email, String password) {
        String sql = "SELECT * FROM buyers WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Buyer buyer = new Buyer();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                buyer.setId(resultSet.getLong("buyer_id"));
                buyer.setFirstName(resultSet.getString("first_name"));
                buyer.setLastName(resultSet.getString("last_name"));
                buyer.setPhoneNumber(resultSet.getString("phone_number"));
                buyer.setAddress(resultSet.getString("address"));
                buyer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                buyer.setEmail(resultSet.getString("email"));
                buyer.setPassword(resultSet.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting buyer: " + e.getMessage());
        }
        return buyer;
    }

    @Override
    public boolean signIn(Buyer buyer) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM buyers WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, buyer.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (buyer.getPassword().equals(resultSet.getString(8))) {
                    buyer.setId(resultSet.getLong(1));
                    buyer.setFirstName(resultSet.getString(2));
                    buyer.setLastName(resultSet.getString(3));
                    buyer.setPhoneNumber(resultSet.getString(4));
                    buyer.setAddress(resultSet.getString(5));
                    buyer.setBirthDate(resultSet.getDate(6).toLocalDate());
                    buyer.setEmail(resultSet.getString(7));
                    buyer.setPassword(resultSet.getString(8));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The buyer is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("failed to sign in: " + e.getMessage());
            return false;
        }
    }
}
