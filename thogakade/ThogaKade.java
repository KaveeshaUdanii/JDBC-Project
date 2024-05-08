package thogakade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThogaKade {

    public static void main(String[] args) {
        String SQL = "Select * From Customer";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root", "mysql");
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery(SQL);
            while (rst.next()) {
                String id = rst.getString("id");
                String name = rst.getString("name"); //rst.getString(2);
                String address = rst.getString("address");
                double salary = rst.getDouble("salary");
                System.out.println(id + "\t" + name + "\t" + address + "\t" + salary);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
