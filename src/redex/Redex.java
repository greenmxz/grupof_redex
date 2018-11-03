/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Moises
 */
public class Redex extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Vista.Login login = new Vista.Login();
        //Vista.Secretario_Menu s_menu = new Vista.Secretario_Menu();
        //Vista.Secretario_Administrar_Pedido  secretario_administrarPedido = new Vista.Secretario_Administrar_Pedido();
        //Vista.Secretario_Crear_Pedido  secretario_crearPedido = new Vista.Secretario_Crear_Pedido();
        //Vista.Secretario_Modificar_Pedido  secretario_modificarPedido = new Vista.Secretario_Modificar_Pedido();
        //login.show(); // this comment to commit something and test 2
        //s_menu.show();
        //secretario_administrarPedido.show();
        //secretario_crearPedido.show();
        //secretario_modificarPedido.show();
        //launch(args);
        
        /*Vista.frmReporteAeropuerto f = new Vista.frmReporteAeropuerto();
        f.show();*/

        //new Vista.frmMenuProvisional().setVisible(true);
        new Vista.Login().setVisible(true);
    }
    
}
