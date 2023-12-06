package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Mechanic;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MechanicDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void addMechanic(Mechanic mechanic) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO mechanics (mechanic_id, first_Name, last_name, phone_number, address, birth_date, email, password) VALUES (?,?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(mechanic.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, mechanic.getId());
            preparedStatement.setString(2, mechanic.getFirstName());
            preparedStatement.setString(3, mechanic.getLastName());
            preparedStatement.setString(4, mechanic.getPhoneNumber());
            preparedStatement.setString(5, mechanic.getAddress());
            preparedStatement.setDate(6, birthDate);
            preparedStatement.setString(7, mechanic.getEmail());
            preparedStatement.setString(8, mechanic.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding mechanic: " + e.getMessage());
        }
    }

    public Mechanic getMechanicById(Long id) {
        String sql = "SELECT * FROM mechanics WHERE mechanic_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Mechanic mechanic = new Mechanic();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mechanic.setId(resultSet.getLong("mechanic_id"));
                mechanic.setFirstName(resultSet.getString("first_name"));
                mechanic.setLastName(resultSet.getString("last_name"));
                mechanic.setPhoneNumber(resultSet.getString("phone_number"));
                mechanic.setAddress(resultSet.getString("address"));
                mechanic.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                mechanic.setEmail(resultSet.getString("email"));
                mechanic.setPassword(resultSet.getString("password"));
            } else
                JOptionPane.showMessageDialog(null, "The mechanic is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting mechanic: " + e.getMessage());
        }
        return mechanic;
    }

    public void updateMechanic(Mechanic mechanic) {
        PreparedStatement ps;
        String sql = "UPDATE mechanics SET first_name = ?, last_name = ?, phone_number = ?, address = ?, birth_date = ?, email = ?, password = ? WHERE mechanic_id = ?";
        Date birthDate = Date.valueOf(mechanic.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, mechanic.getFirstName());
            ps.setString(2, mechanic.getLastName());
            ps.setString(3, mechanic.getPhoneNumber());
            ps.setString(4, mechanic.getAddress());
            ps.setDate(5, birthDate);
            ps.setString(6, mechanic.getEmail());
            ps.setString(7, mechanic.getPassword());
            ps.setLong(8, mechanic.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating mechanic: " + e.getMessage());
        }
    }

    public void deleteMechanic(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM mechanics WHERE mechanic_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting mechanic: " + e.getMessage());
        }
    }

    public List<Mechanic> getAllMechanics() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM mechanics";
        ResultSet resultSet;
        List<Mechanic> mechanics = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Mechanic mechanic = new Mechanic();
                mechanic.setId(resultSet.getLong("mechanic_id"));
                mechanic.setFirstName(resultSet.getString("first_name"));
                mechanic.setLastName(resultSet.getString("last_name"));
                mechanic.setPhoneNumber(resultSet.getString("phone_number"));
                mechanic.setAddress(resultSet.getString("address"));
                mechanic.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                mechanic.setEmail(resultSet.getString("email"));
                mechanic.setPassword(resultSet.getString("password"));
                mechanics.add(mechanic);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all mechanics: " + e.getMessage());
        }
        return mechanics;
    }
}
