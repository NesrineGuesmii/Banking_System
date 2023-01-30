/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.mycompany.bankingsystem.adminHome;
import com.mycompany.bankingsystem.home;
import com.mycompany.bankingsystem.signup;
import com.mycompany.bankingsystem.transactions;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.User;
import model.cheque;
import model.transaction;
/**
 *
 * @author GuesmiN
 */
public class UserDAO {
    public static void save(User user){
        
        try{
            
            String Query ="insert into users values (?,?,?,?,?,?,?,?)";
            
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ptstmt = con.prepareStatement(Query);
            
            
            ptstmt.setString(1, user.getName());
            ptstmt.setString(2, user.getEmail());
            ptstmt.setString(3, user.getAccount());
            ptstmt.setString(4, user.getPassword());
            ptstmt.setString(5, user.getSecurityQuestion());
            ptstmt.setString(6, user.getAnswer());
            ptstmt.setString(7, user.getAddress());
            ptstmt.setString(8, "false");
            
            ptstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registered Successfully ! Wait for Admin Approval");
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public static void login(User user){
        
        int check = 0;
        String Email = user.getEmail();
        String Password = user.getPassword();
        
        
        
        if (Email.equals("admin@gmail.com") && Password.equals("admin")) {
            
            check =1;
            new adminHome().setVisible(true);
        }
        
        else{
            try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select *from users where email=? and password=?";
            PreparedStatement ptstmt = con.prepareStatement(sql);
            
            ptstmt.setString(1,Email);
            ptstmt.setString(2, Password);
            
            ResultSet rs = ptstmt.executeQuery();
            
            if(rs.next()){
                check = 1;
                if(rs.getString(8).equals("true")){
                    new home().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wait For Admin Approval");
                }
                if (check == 0){
                    JOptionPane.showMessageDialog(null, "incorrect email or password");
           }
            }
            
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
            
        }
        
        if (check == 0){
            JOptionPane.showMessageDialog(null, "incorrect email or password");
        }
    }
    
    public static void SelectAccountBalance(){
        int ID=2;
        
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select accbalance from amount where ID=? ";
            
            PreparedStatement ptstmt = con.prepareStatement(sql);
            
            ptstmt.setInt(1,ID);
            
            ResultSet rs = ptstmt.executeQuery();
            
            if(rs.next()){
                
                JOptionPane.showMessageDialog(null ,"Your Amount Is : " + rs.getString("accbalance") + "." );
            }
            
            con.close();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void VerifyAmount(transaction TR , String amount){
        try{
            ResultSet result = Select.getData("select accbalance from amount where ID=2");
            if(result.next()){
                int amntInt= Integer.parseInt(amount);
                String accbal= result.getString("accbalance");
                int accbalanceInt= Integer.parseInt(accbal);
                
                int leftover = accbalanceInt - amntInt;
                
                if(leftover >=0){
                    transactionDAO.Transaction(TR);
                    InsertUpdateDelete.setData("update amount set accbalance='"+leftover+"' where ID=2"  , "Account Balance Updated");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Account Balance Not Enough!");
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ArrayList<transaction> getAllRecord(){
        ArrayList<transaction> arrayList = new ArrayList<>();
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select *from transaction";
            PreparedStatement ptstmt = con.prepareStatement(sql);
           
            ResultSet rs = ptstmt.executeQuery();
            
            while(rs.next()) {
                transaction TR = new transaction();
                
                TR.setBank(rs.getString("bank"));
                TR.setAccountNumber(rs.getString("account number"));
                TR.setAmount(rs.getString("amount"));
                TR.setPurpose(rs.getString("purpose"));
                TR.setPayeeEmail(rs.getString("payee email"));
                TR.setPayeeMobile(rs.getString("payee mobile"));
                TR.setNote(rs.getString("note"));
                arrayList.add(TR);
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }
    
    public static void OrderCheque(cheque ch){
        try{
            
            String Query ="insert into ordercheque values (?)";
            
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ptstmt = con.prepareStatement(Query);
            
            
            ptstmt.setString(1, ch.getChequeLeaves());
            
            
            ptstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cheque Book Ordered Successfully !");
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updatePassword(String password , String email , String SecurityQuestion , String Answer ) {
        
        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "update users set password=? where email=? and securityQuestion=? and answer=?";
            
            PreparedStatement ptstmt = con.prepareStatement(sql);
            
            ptstmt.setString(1, password);
            ptstmt.setString(2,email);
            ptstmt.setString(3,SecurityQuestion);
            ptstmt.setString(4, Answer);
            
            ptstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Updated Successfully !");
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e , "Message" ,JOptionPane.ERROR_MESSAGE);
        }
         
    }
   
}

    
    

    
        
    

