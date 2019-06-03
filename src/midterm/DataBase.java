/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.sql.*;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Rashae
 */
public class DataBase {
   private Connection connection;
   private Statement statement; 
 
public DataBase(){    
    try{
       // Load the JDBC driver
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        

        // Connect to a database
        connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Data/Northwind.mdb");
      
        
        //Create Statement 
        statement = connection.createStatement();
        }catch(Exception ex) 
        {
            ex.printStackTrace();
        }
}
        
        public void orderTotal(String table, String orderID, ListView list) throws SQLException, ClassNotFoundException 
        {
         
           ResultSet resultSet = statement.executeQuery("SELECT CONCAT('$', "
                 + "CAST(sum((UnitPrice * Quantity) - ((UnitPrice * Quantity)*Discount))"
                 + " AS DECIMAL(10,2))) FROM " +table + " WHERE OrderID = '"+orderID+"'" );
           
          
           
           while(resultSet.next()){
               
               list.getItems().addAll(resultSet.getString(1));
           
           }
        
        }
        
        public void orderDetail(String table, String orderID, String table2, ListView list) throws SQLException, ClassNotFoundException 
        {    
            ResultSet resultSet = statement.executeQuery("SELECT FORMAT(OrderDate, 'M/d/yyyy') "
                    + "+ ' -Order Date', Freight + ' -Freight Charge', Quantity + ' -Quantity', "
                    + "CONCAT ('$', CAST(UnitPrice AS DECIMAL(10,2))) + ' -Unit Price',"
                    + " CONCAT('%', CAST(Discount *100 AS DECIMAL(5,0))) + ' -Discount' FROM "
                    +table+", "+table2+" WHERE "+table+".OrderID= "+orderID+" AND "+table2+".OrderID = "+orderID );
        
            ResultSetMetaData  rsMetaData = resultSet.getMetaData();
            
            while(resultSet.next())
            {
                for (int i =1; i<= rsMetaData.getColumnCount(); i++)
                {
                  list.getItems().addAll(resultSet.getString(i));
                }
                    
                  list.getItems().add("");
              
            }
        
        
        }
        
        public void customer(String table, String state, ListView list) throws SQLException, ClassNotFoundException 
        {
            ResultSet resultSet = statement.executeQuery("SELECT ContactName + ' -Name',"
                    + " City + ' -City' FROM "+table + " WHERE Region= '"+state+"'");
        
            ResultSetMetaData  rsMetaData = resultSet.getMetaData();
            
            while(resultSet.next())
            {
                for (int i =1; i<= rsMetaData.getColumnCount(); i++)
                {
                  list.getItems().addAll(resultSet.getString(i));
                }
                  list.getItems().add("");
            }
        }
        
        public void birthday(String table, String date, ListView list) throws SQLException, ClassNotFoundException 
        {
            
            ResultSet resultSet = statement.executeQuery("SELECT CONCAT(FirstName,' ' , LastName) FROM "
                    + table + " WHERE BirthDate BETWEEN '01-Jan-"+ date + "' AND '12-Dec-"+date
                    +"' ORDER BY LastName");
        
            ResultSetMetaData  rsMetaData = resultSet.getMetaData();
            
            while(resultSet.next())
            {
                for (int i =1; i<= rsMetaData.getColumnCount(); i++)
                {
                  list.getItems().addAll(resultSet.getString(i));
                }
                 list.getItems().add("");
            }
        }

}
