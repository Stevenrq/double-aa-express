package org.doubleaaexpress.models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.dao.abstractfactory.GenericDAO;
import org.doubleaaexpress.util.DBConnection;

public class CustomerDAO implements GenericDAO<Customer> {

    private Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void add(Customer customer) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO customers (customer_id, first_Name, last_name, phone_number, birth_date, email, password) "
                +
                "VALUES (?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(customer.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setDate(5, birthDate);
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    @Override
    public Customer getById(Long id) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Customer customer = new Customer();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
            } else
                JOptionPane.showMessageDialog(null, "The customer is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting customer: " + e.getMessage());
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement ps;
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, email = ?, "
                +
                "password = ? WHERE customer_id = ?";
        Date birthDate = Date.valueOf(customer.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhoneNumber());
            ps.setDate(4, birthDate);
            ps.setString(5, customer.getEmail());
            ps.setString(6, customer.getPassword());
            ps.setLong(7, customer.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM customers";
        ResultSet resultSet;
        List<Customer> customers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customers: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean signIn(Customer customer) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM customers WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (customer.getPassword().equals(resultSet.getString(7))) {
                    customer.setId(resultSet.getLong(1));
                    customer.setFirstName(resultSet.getString(2));
                    customer.setLastName(resultSet.getString(3));
                    customer.setPhoneNumber(resultSet.getString(4));
                    customer.setBirthDate(resultSet.getDate(5).toLocalDate());
                    customer.setEmail(resultSet.getString(6));
                    customer.setPassword(resultSet.getString(7));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The client is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("failed to login: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean get(String email, String password) {
        String sql = "SELECT * FROM customers WHERE email = ? and  password = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Customer customer = new Customer();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "The customer is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer: " + e.getMessage());
            return false;
        }
    }
}
