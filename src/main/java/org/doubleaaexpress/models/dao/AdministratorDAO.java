package org.doubleaaexpress.models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.dao.abstractfactory.GenericDAO;
import org.doubleaaexpress.models.dao.iterator.AdministratorIterator;
import org.doubleaaexpress.util.DBConnection;

public class AdministratorDAO implements GenericDAO<Administrator> {

    private Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void add(Administrator administrator) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO administrators (administrator_id, first_Name, last_name, phone_number, birth_date, email, password) VALUES (?,?,?,?,?,?,?)";
        Date birthDate = Date.valueOf(administrator.getBirthDate());

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, administrator.getId());
            preparedStatement.setString(2, administrator.getFirstName());
            preparedStatement.setString(3, administrator.getLastName());
            preparedStatement.setString(4, administrator.getPhoneNumber());
            preparedStatement.setDate(5, birthDate);
            preparedStatement.setString(6, administrator.getEmail());
            preparedStatement.setString(7, administrator.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding administrator: " + e.getMessage());
        }
    }

    @Override
    public Administrator getById(Long id) {
        String sql = "SELECT * FROM administrators WHERE administrator_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Administrator administrator = new Administrator();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                administrator.setId(resultSet.getLong("administrator_id"));
                administrator.setFirstName(resultSet.getString("first_name"));
                administrator.setLastName(resultSet.getString("last_name"));
                administrator.setPhoneNumber(resultSet.getString("phone_number"));
                administrator.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                administrator.setEmail(resultSet.getString("email"));
                administrator.setPassword(resultSet.getString("password"));
            } else
                JOptionPane.showMessageDialog(null, "The administrator is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting administrator: " + e.getMessage());
        }
        return administrator;
    }

    @Override
    public boolean get(String email, String password) {
        String sql = "SELECT * FROM administrators WHERE email = ? and  password = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Administrator administrator = new Administrator();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                administrator.setId(resultSet.getLong("administrator_id"));
                administrator.setFirstName(resultSet.getString("first_name"));
                administrator.setLastName(resultSet.getString("last_name"));
                administrator.setPhoneNumber(resultSet.getString("phone_number"));
                administrator.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                administrator.setEmail(resultSet.getString("email"));
                administrator.setPassword(resultSet.getString("password"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "The administrator is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error getting administrator: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void update(Administrator administrator) {
        PreparedStatement ps;
        String sql = "UPDATE administrators SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, email = ?, password = ? WHERE administrator_id = ?";
        Date birthDate = Date.valueOf(administrator.getBirthDate());

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, administrator.getFirstName());
            ps.setString(2, administrator.getLastName());
            ps.setString(3, administrator.getPhoneNumber());
            ps.setDate(4, birthDate);
            ps.setString(5, administrator.getEmail());
            ps.setString(6, administrator.getPassword());
            ps.setLong(7, administrator.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating administrator: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM administrators WHERE administrator_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting administrator: " + e.getMessage());
        }
    }

    @Override
    public List<Administrator> getAll() {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM administrators";
        ResultSet resultSet;
        List<Administrator> administrators = new ArrayList<>();
        AdministratorIterator iterator = new AdministratorIterator(administrators);

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getLong("administrator_id"));
                administrator.setFirstName(resultSet.getString("first_name"));
                administrator.setLastName(resultSet.getString("last_name"));
                administrator.setPhoneNumber(resultSet.getString("phone_number"));
                administrator.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                administrator.setEmail(resultSet.getString("email"));
                administrator.setPassword(resultSet.getString("password"));
                administrators.add(administrator);
            }
            while (iterator.hasNext()) {
                Administrator administrator = iterator.next();
                System.out.println(administrator.getFirstName() + " " + administrator.getLastName());
            }
        } catch (SQLException e) {
            System.out.println("Error getting all administrators: " + e.getMessage());
        }
        return administrators;
    }

    @Override
    public boolean signIn(Administrator administrator) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM administrators WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, administrator.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (administrator.getPassword().equals(resultSet.getString(7))) {
                    administrator.setId(resultSet.getLong(1));
                    administrator.setFirstName(resultSet.getString(2));
                    administrator.setLastName(resultSet.getString(3));
                    administrator.setPhoneNumber(resultSet.getString(4));
                    administrator.setBirthDate(resultSet.getDate(5).toLocalDate());
                    administrator.setEmail(resultSet.getString(6));
                    administrator.setPassword(resultSet.getString(7));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The administrator is not registered");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("failed to login: " + e.getMessage());
            return false;
        }
    }
}
