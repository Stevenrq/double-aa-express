package org.doubleaaexpress.models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.util.DBConnection;

public class OrderManagerDAO implements GenericUserDAO<OrderManager> {

    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void add(OrderManager orderManager) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO order_managers (order_manager_id, first_Name, last_name, phone_number, birth_date, email, password) VALUES (?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(orderManager.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderManager.getId());
            preparedStatement.setString(2, orderManager.getFirstName());
            preparedStatement.setString(3, orderManager.getLastName());
            preparedStatement.setString(4, orderManager.getPhoneNumber());
            preparedStatement.setDate(5, birthDate);
            preparedStatement.setString(6, orderManager.getEmail());
            preparedStatement.setString(7, orderManager.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding order manager: " + e.getMessage());
        }
    }

    @Override
    public OrderManager getById(Long id) {
        String sql = "SELECT * FROM order_managers WHERE order_manager_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        OrderManager orderManager = new OrderManager();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderManager.setId(resultSet.getLong("order_manager_id"));
                orderManager.setFirstName(resultSet.getString("first_name"));
                orderManager.setLastName(resultSet.getString("last_name"));
                orderManager.setPhoneNumber(resultSet.getString("phone_number"));
                orderManager.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                orderManager.setEmail(resultSet.getString("email"));
            } else
                JOptionPane.showMessageDialog(null, "The order manager is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting order manager: " + e.getMessage());
        }
        return orderManager;
    }

    @Override
    public boolean get(String email, String password) {
        String sql = "SELECT * FROM order_managers WHERE email = ? and  password = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        OrderManager orderManager = new OrderManager();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderManager.setId(resultSet.getLong("order_manager_id"));
                orderManager.setFirstName(resultSet.getString("first_name"));
                orderManager.setLastName(resultSet.getString("last_name"));
                orderManager.setPhoneNumber(resultSet.getString("phone_number"));
                orderManager.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                orderManager.setEmail(resultSet.getString("email"));
                orderManager.setPassword(resultSet.getString("password"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error getting order manager: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void update(OrderManager orderManager) {
        PreparedStatement ps;
        String sql = "UPDATE order_managers SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, email = ?, password = ? WHERE order_manager_id = ?";
        Date birthDate = Date.valueOf(orderManager.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderManager.getFirstName());
            ps.setString(2, orderManager.getLastName());
            ps.setString(3, orderManager.getPhoneNumber());
            ps.setDate(4, birthDate);
            ps.setString(5, orderManager.getEmail());
            ps.setString(6, orderManager.getPassword());
            ps.setLong(7, orderManager.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating order manager: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM order_managers WHERE order_manager_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting order manager: " + e.getMessage());
        }
    }

    @Override
    public List<OrderManager> getAll() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM order_managers";
        ResultSet resultSet;
        List<OrderManager> orderManagers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderManager orderManager = new OrderManager();
                orderManager.setId(resultSet.getLong("order_manager_id"));
                orderManager.setFirstName(resultSet.getString("first_name"));
                orderManager.setLastName(resultSet.getString("last_name"));
                orderManager.setPhoneNumber(resultSet.getString("phone_number"));
                orderManager.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                orderManager.setEmail(resultSet.getString("email"));
                orderManager.setPassword(resultSet.getString("password"));
                orderManagers.add(orderManager);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all order managers: " + e.getMessage());
        }
        return orderManagers;
    }

    @Override
    public boolean signIn(OrderManager orderManager) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM order_managers WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderManager.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (orderManager.getPassword().equals(resultSet.getString(7))) {
                    orderManager.setId(resultSet.getLong(1));
                    orderManager.setFirstName(resultSet.getString(2));
                    orderManager.setLastName(resultSet.getString(3));
                    orderManager.setPhoneNumber(resultSet.getString(4));
                    orderManager.setBirthDate(resultSet.getDate(5).toLocalDate());
                    orderManager.setEmail(resultSet.getString(6));
                    orderManager.setPassword(resultSet.getString(7));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The order manager is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("failed to sign in: " + e.getMessage());
            return false;
        }
    }

}
