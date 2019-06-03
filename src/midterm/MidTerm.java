/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Rashae
 */
public class MidTerm extends Application {
    private BorderPane orderBorder = new BorderPane();
    private BorderPane menuBorder = new BorderPane();
    private BorderPane orderDetailBorder = new BorderPane();
    private BorderPane customerBorder = new BorderPane();
    private BorderPane birthdayBorder = new BorderPane();
    
    private SplitPane orderSplit = new SplitPane();
    private SplitPane menuSplit = new SplitPane();
    private SplitPane orderDetailSplit = new SplitPane();
    private SplitPane customerSplit = new SplitPane();
    private SplitPane birthdaySplit = new SplitPane();
    
    private Scene orderScene = new Scene(orderBorder, 400, 450);
    private Scene menuScene = new Scene(menuBorder, 400, 450);
    private Scene orderDetailScene = new Scene(orderDetailBorder, 400, 450);
    private Scene customerScene = new Scene(customerBorder, 400, 450);
    private Scene birthdayScene = new Scene(birthdayBorder, 400, 450);
        
    private DataBase data = new DataBase();
    @Override
    public void start(Stage primaryStage) 
    {
        menuUI(primaryStage);
        orderTotal(primaryStage);   
        orderDetails(primaryStage);
        customerInfo(primaryStage);
        birthday(primaryStage);
        
        primaryStage.getIcons().add(new Image(MidTerm.class.getResourceAsStream("NorthWind_Icon.jpg")));
        primaryStage.setTitle("MidTerm");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
    
    public void menuUI(Stage primaryStage)
    {   
        //Calling menurBar method
        menuBar(primaryStage, menuBorder);
        //VBox
        VBox vBox_menuTitle = new VBox();
        //Label
        Label title = new Label("NorthWind\n    Traders");
        //Iamge
        Image image = new Image(MidTerm.class.getResourceAsStream("Logo.bmp"));
        ImageView imageView = new ImageView(image);
        
           
        //Label setting
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
       
        //vBox setting
        vBox_menuTitle.setAlignment(Pos.CENTER);
        vBox_menuTitle.setSpacing(5);
       
        //vBox setting
        vBox_menuTitle.getChildren().addAll(title,imageView);
        
       
        menuSplit.getItems().add(vBox_menuTitle);
        menuSplit.setOrientation(Orientation.VERTICAL);
        menuBorder.setCenter(menuSplit);
    }
    
    
    public void orderTotal(Stage primaryStage)
    {   
        //Calling menuBar method
        menuBar(primaryStage, orderBorder);
        //VBox
        VBox vBox_orderTitle = new VBox();
        //List View
        ListView list = new ListView();
        //Button
        Button submitButton = new Button("Submit");       
        //Labels
        Label calTotalLabel = new Label("Calculate Order Total");
        Label orderNumLabel = new Label("Order Number");
        //TextField
        TextField orderNumText = new TextField();
         
        //Submit Button action
        submitButton.setOnAction((ActionEvent e) ->
        {
            list.getItems().clear();
            try{
                
                data.orderTotal("[Order Details]", orderNumText.getText(), list);
                
                }catch(ClassNotFoundException | SQLException ex)
                    {
                        ex.printStackTrace();
                    }

        });
               
        //Label setting
        calTotalLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        orderNumLabel.setPrefHeight(50);
        //vBox setting
        vBox_orderTitle.setAlignment(Pos.CENTER);
        vBox_orderTitle.setSpacing(5);
        //TextField setting
        orderNumText.setMaxWidth(150);
        //vBox setting
        vBox_orderTitle.getChildren().addAll(calTotalLabel,orderNumLabel,orderNumText,
                submitButton);
        
        
        orderSplit.getItems().addAll(vBox_orderTitle, list);
        orderSplit.setOrientation(Orientation.VERTICAL);
        orderBorder.setCenter(orderSplit);
       
    }
    
    public void orderDetails(Stage primaryStage)
    {   
        //Calling menuBar method
        menuBar(primaryStage, orderDetailBorder);
        //VBox
        VBox vBox_orderDetailTitle = new VBox();
        //List View
        ListView list = new ListView();
        //Button
        Button submitButton = new Button("Submit");       
        //Labels
        Label orderDetailLabel = new Label("Show Order Details");
        Label orderNumLabel = new Label("Order Number");
        //TextField
        TextField orderNumText = new TextField();
         
        
        //Submit Button action
        submitButton.setOnAction((ActionEvent e) ->
        {
            list.getItems().clear();
            try{
                
                data.orderDetail("Orders", orderNumText.getText(),"[Order Details]", list);
                
                }catch(ClassNotFoundException | SQLException ex)
                    {
                        ex.printStackTrace();
                    }

        });
               
        //Label setting
        orderDetailLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        orderNumLabel.setPrefHeight(50);
        //vBox setting
        vBox_orderDetailTitle.setAlignment(Pos.CENTER);
        vBox_orderDetailTitle.setSpacing(5);
        //TextField setting
        orderNumText.setMaxWidth(150);
        //vBox setting
        vBox_orderDetailTitle.getChildren().addAll(orderDetailLabel,orderNumLabel,
                orderNumText,submitButton);
        
        
        orderDetailSplit.getItems().addAll(vBox_orderDetailTitle, list);
        orderDetailSplit.setOrientation(Orientation.VERTICAL);
        orderDetailBorder.setCenter(orderDetailSplit);
    }
    
    public void customerInfo(Stage primaryStage)
    {   
        //Calling menuBar method
        menuBar(primaryStage, customerBorder);
        //VBox
        VBox vBox_customerTitle = new VBox();
        //List View
        ListView list = new ListView();
        //Button
        Button submitButton = new Button("Submit");       
        //Labels
        Label customerLabel = new Label("Customer Info");
        Label customerStateLabel = new Label("State");
        //TextField
        TextField customerStateText = new TextField();
         
        
        //Submit Button action
        submitButton.setOnAction((ActionEvent e) ->
        {
            list.getItems().clear();
            try{
                
                data.customer("Customers", customerStateText.getText(), list);
                
                }catch(ClassNotFoundException | SQLException ex)
                    {
                        ex.printStackTrace();
                    }

        });
               
        //Label setting
        customerLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        customerStateLabel.setPrefHeight(50);
        //vBox setting
        vBox_customerTitle.setAlignment(Pos.CENTER);
        vBox_customerTitle.setSpacing(5);
        //TextField setting
        customerStateText.setMaxWidth(150);
        //vBox setting
        vBox_customerTitle.getChildren().addAll(customerLabel, customerStateLabel,
                customerStateText,submitButton);
        
        
        customerSplit.getItems().addAll(vBox_customerTitle, list);
        customerSplit.setOrientation(Orientation.VERTICAL);
        customerBorder.setCenter(customerSplit);
    }
    
    public void birthday(Stage primaryStage)
    {
        //Calling menuBar method
        menuBar(primaryStage, birthdayBorder);
        //VBox
        VBox vBox_birthdayTitle = new VBox();
        //List View
        ListView list = new ListView();      
        //Button
        Button submitButton = new Button("Submit");       
        //Labels
        Label birthdayLabel = new Label("Employee Birthday");
        Label yearLabel = new Label("Year");
        //TextField
        TextField yearText = new TextField();
         
        
        
        
        //Submit Button action
        submitButton.setOnAction((ActionEvent e) ->
        {
            list.getItems().clear();
            try{
                
                data.birthday("Employees", yearText.getText(), list);
                
                }catch(ClassNotFoundException | SQLException ex)
                    {
                        ex.printStackTrace();
                    }

        });
               
        //Label setting
        birthdayLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        yearLabel.setPrefHeight(50);
        //vBox setting
        vBox_birthdayTitle.setAlignment(Pos.CENTER);
        vBox_birthdayTitle.setSpacing(5);
        //TextField setting
        yearText.setMaxWidth(150);
        //vBox setting
        vBox_birthdayTitle.getChildren().addAll(birthdayLabel, yearLabel,
                yearText,submitButton);
        
        
        birthdaySplit.getItems().addAll(vBox_birthdayTitle, list);
        birthdaySplit.setOrientation(Orientation.VERTICAL);
        birthdayBorder.setCenter(birthdaySplit);
    }
    
    public void menuBar(Stage primaryStage, BorderPane border)
    {   
        //Menu Bar
        MenuBar menuBar = new MenuBar();
        //Menu
        Menu menu = new Menu("File");
        Menu menuOrder = new Menu("Order");
        Menu menuCustomer = new Menu("Customer");
        Menu menuEmployee = new Menu("Employee");
        //Menu Item
        MenuItem menuItemHome = new MenuItem("Home");
        MenuItem menuItemClose = new MenuItem("Close");
        MenuItem menuItemTotal = new MenuItem("Total");
        MenuItem menuItemDetails = new MenuItem("Details");
        MenuItem menuItemState = new MenuItem("State");
        MenuItem menuItemBirthday = new MenuItem("Birthday");
        //VBox
        VBox vBox_menuBar = new VBox();
        vBox_menuBar.getChildren().add(menuBar);
        //----Menu Items settings----\\
        //File-close action
        menuItemClose.setOnAction(e-> {
        System.exit(0);
        });
        //File-home acrion
        menuItemHome.setOnAction(e -> {
        primaryStage.setScene(menuScene); 
        });
        //Order-total action
        menuItemTotal.setOnAction(e -> {
        primaryStage.setScene(orderScene);
        });
        //Order-Details action
        menuItemDetails.setOnAction(e -> {
        primaryStage.setScene(orderDetailScene);    
        });
        //Customer-State
        menuItemState.setOnAction(e -> {
        primaryStage.setScene(customerScene);    
        });
        //Employee-Birthday
        menuItemBirthday.setOnAction(e -> {
        primaryStage.setScene(birthdayScene);    
        });
        //Placing menu item into each menu
        menu.getItems().addAll(menuItemHome, menuItemClose);
        menuOrder.getItems().addAll(menuItemTotal, menuItemDetails);
        menuCustomer.getItems().add(menuItemState);
        menuEmployee.getItems().add(menuItemBirthday);
        
        menuBar.getMenus().addAll(menu,menuOrder,menuCustomer,menuEmployee); 
    
        border.setTop(vBox_menuBar);
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
