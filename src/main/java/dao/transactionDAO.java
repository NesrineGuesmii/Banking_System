/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.transaction;

/**
 *
 * @author GuesmiN
 */
public class transactionDAO {
    public static void Transaction(transaction TR){
        
                try{
                    String Query ="insert into transaction values (?,?,?,?,?,?,?)";
                    
                    Connection conn = ConnectionProvider.getCon();
                    PreparedStatement pt = conn.prepareStatement(Query);
                    
                    pt.setString(1, TR.getBank());
                    pt.setString(2, TR.getAccountNumber());
                    pt.setString(3, TR.getAmount());
                    pt.setString(4, TR.getPurpose());
                    pt.setString(5, TR.getPayeeEmail());
                    pt.setString(6, TR.getPayeeMobile());
                    pt.setString(7, TR.getNote());
                    
                    pt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Transferred Succesfully !");
            
                    conn.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
                }
            }
    
}
     

