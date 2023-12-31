package org.doubleaaexpress.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.util.DBConnection;

public class MotorcycleDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void addMotorcycle(Motorcycle motorcycle) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO motorcycles (motorcycle_id, name, price, quantity, model, year, plate_number, status) VALUES (?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, motorcycle.getId());
            preparedStatement.setString(2, motorcycle.getName());
            preparedStatement.setDouble(3, motorcycle.getPrice());
            preparedStatement.setInt(4, motorcycle.getQuantity());
            preparedStatement.setString(5, motorcycle.getModel());
            preparedStatement.setShort(6, motorcycle.getYear());
            preparedStatement.setString(7, motorcycle.getPlateNumber());
            preparedStatement.setString(8, motorcycle.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding motorcycle: " + e.getMessage());
        }
    }

    public Motorcycle getMotorcycleById(Long id) {
        String sql = "SELECT * FROM motorcycles WHERE motorcycle_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Motorcycle motorcycle = new Motorcycle();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                motorcycle.setId(resultSet.getLong("motorcycle_id"));
                motorcycle.setName(resultSet.getString("name"));
                motorcycle.setPrice(resultSet.getDouble("price"));
                motorcycle.setQuantity(resultSet.getInt("quantity"));
                motorcycle.setModel(resultSet.getString("model"));
                motorcycle.setYear(resultSet.getShort("year"));
                motorcycle.setPlateNumber(resultSet.getString("plate_number"));
                motorcycle.setStatus(resultSet.getString("status"));
            } else
                JOptionPane.showMessageDialog(null, "The motorcycle is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting motorcycle: " + e.getMessage());
        }
        return motorcycle;
    }

    public void updateMotorcycle(Motorcycle motorcycle) {
        PreparedStatement ps;
        String sql = "UPDATE motorcycles SET name = ?, price = ?, quantity = ?, model = ?, year = ?, plate_number = ?, status = ? WHERE motorcycle_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, motorcycle.getName());
            ps.setDouble(2, motorcycle.getPrice());
            ps.setInt(3, motorcycle.getQuantity());
            ps.setString(4, motorcycle.getModel());
            ps.setShort(5, motorcycle.getYear());
            ps.setString(6, motorcycle.getPlateNumber());
            ps.setString(7, motorcycle.getStatus());
            ps.setLong(8, motorcycle.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating motorcycle: " + e.getMessage());
        }
    }

    public void updateMotorcycle(long id, int quantity) {
        PreparedStatement ps;
        String sql = "UPDATE motorcycles SET quantity = ? WHERE motorcycle_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(quantity));
            ps.setString(2, String.valueOf(id));
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating motorcycle: " + e.getMessage());
        }
    }

    public void deleteMotorcycle(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM motorcycles WHERE motorcycle_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting motorcycle: " + e.getMessage());
        }
    }

    public List<Motorcycle> getAll() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM motorcycles";
        ResultSet resultSet;
        List<Motorcycle> motorcycles = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motorcycle motorcycle = new Motorcycle();
                motorcycle.setId(resultSet.getLong("motorcycle_id"));
                motorcycle.setName(resultSet.getString("name"));
                motorcycle.setPrice(resultSet.getDouble("price"));
                motorcycle.setQuantity(resultSet.getInt("quantity"));
                motorcycle.setModel(resultSet.getString("model"));
                motorcycle.setYear(resultSet.getShort("year"));
                motorcycle.setPlateNumber(resultSet.getString("plate_number"));
                motorcycle.setStatus(resultSet.getString("status"));
                motorcycles.add(motorcycle);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all motorcycles: " + e.getMessage());
        }
        return motorcycles;
    }
}
