/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author GuesmiN
 */
public class AdminDAO {
    public static ArrayList<User> getAllRecord(){
        ArrayList<User> arrayList = new ArrayList<>();
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select *from users";
            PreparedStatement ptstmt = con.prepareStatement(sql);
           
            ResultSet rs = ptstmt.executeQuery();
            
            while(rs.next()) {
                User user = new User();
                
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }
    
    public static void updateStatus(String email , String status) {
        
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "update users set status=? where email=?";
            
            PreparedStatement ptstmt = con.prepareStatement(sql);
            
            ptstmt.setString(1, status);
            ptstmt.setString(2,email);
            
            ptstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Updated Successfully !");
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
         
    }
    
    public static ArrayList<User> search(String emailOrname){
        ArrayList<User> arrayList = new ArrayList<>();
        
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select *from users where name=? or email=?";
            PreparedStatement ptstmt = con.prepareStatement(sql);
            
            ptstmt.setString(1, emailOrname);
            ptstmt.setString(2, emailOrname);
            
            ResultSet rs = ptstmt.executeQuery();
            
            while(rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }
    
}
