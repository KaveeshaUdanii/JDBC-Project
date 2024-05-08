/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogakade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author niroth
 */
public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into Customer values(?,?,?,?)");
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        int res=stm.executeUpdate();
        return res>0;
    }
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());
        int res=stm.executeUpdate();
        return res>0;
    }    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery("Select * From Customer where id='"+id+"'");
        if(rst.next()){
            return new Customer(rst.getString("id"),rst.getString("name"), rst.getString("address"),rst.getDouble("Salary"));
        }
        return null;
    }
    public static  boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
    }
    public static ArrayList<Customer>getAllCustomer() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Customer");
        ArrayList <Customer>customers=new ArrayList<>();
        while(rst.next()){
            customers.add(new Customer(rst.getString("id"),rst.getString("name"), rst.getString("address"),rst.getDouble("Salary")));
        }
        return customers;
    }
}
