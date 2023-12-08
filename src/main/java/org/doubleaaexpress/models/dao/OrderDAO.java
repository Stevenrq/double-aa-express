package org.doubleaaexpress.models.dao;

import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.models.Order;
import org.doubleaaexpress.models.Product;
import org.doubleaaexpress.util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final Connection connection = DBConnection.getInstance().getConnection();

    public void add(Order order) {
        PreparedStatement ps;
        String sql = "INSERT INTO orders (order_id, date, buyer, address, product, quantity, unit_price, " +
                "total_price, status) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, order.getId());
            ps.setDate(2, Date.valueOf(order.getDate()));
            ps.setString(3, order.getBuyerName());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getProduct().getName());
            ps.setInt(6, order.getQuantity());
            ps.setDouble(7, order.getUnitPrice());
            ps.setDouble(8, order.getTotalPrice());
            ps.setString(9, order.getStatus());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public Order getById(Long id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        PreparedStatement ps;
        ResultSet rs;
        Order order = new Order();

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                order.setId(rs.getLong("order_id"));
                order.setDate(rs.getDate("date").toLocalDate());
                order.setBuyerName(rs.getString("buyer"));
                order.setAddress(rs.getString("address"));
                order.setProduct((Product) rs.getObject("product"));
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unit_price"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setStatus(rs.getString("status"));
            } else
                JOptionPane.showMessageDialog(null, "The order is not registered");
        } catch (SQLException e) {
            System.out.println("Error getting order: " + e.getMessage());
        }
        return order;
    }

    public void update(Order order) {
        PreparedStatement ps;
        String sql = "UPDATE orders SET date = ?, buyer = ?, address = ?, product = ?, quantity = ?, " +
                "unit_price = ?, total_price = ?, status = ? WHERE order_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(order.getDate()));
            ps.setString(2, order.getBuyerName());
            ps.setString(3, order.getAddress());
            ps.setObject(4, order.getProduct().getName());
            ps.setInt(5, order.getQuantity());
            ps.setDouble(6, order.getUnitPrice());
            ps.setDouble(7, order.getTotalPrice());
            ps.setString(8, order.getStatus());
            ps.setLong(9, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        PreparedStatement ps;
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    public List<Order> getAll() {
        PreparedStatement ps;
        String sql = "SELECT * FROM orders";
        ResultSet rs;
        List<Order> orders = new ArrayList<>();

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                Motorcycle motorcycle = new Motorcycle();

                order.setId(rs.getLong("order_id"));
                order.setDate(rs.getDate("date").toLocalDate());
                order.setBuyerName(rs.getString("buyer"));
                order.setAddress(rs.getString("address"));
                motorcycle.setName(rs.getString("product"));
                order.setProduct(motorcycle);
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unit_price"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all orders: " + e.getMessage());
        }
        return orders;
    }
}
