/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.IHra;
import main.Main;

/**
 * Zobrazuje grafické rozhraní horní lišty hry
 *
 * @author Ondřej Smrček
 */
public class MenuLista extends MenuBar {
    
    private IHra hra;
    private Main main;
    
    /**
     * Konstruktor třídy
     * 
     * @param hra
     * @param main 
     */
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    
    /**
     * Počáteční nastavení třídy
     * 
     */
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Nápověda");
        
        MenuItem novaHra = new MenuItem("Nová hra");
        //MenuItem novaHra = new MenuItem("Nová hra", new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konecHry = new MenuItem("Konec hry");
        konecHry.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Napoveda");
        napovedaItem.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            
            @Override
            public void handle(ActionEvent event) {
                main.newGame();
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programu");
                oProgramuAlert.setHeaderText("Adventura pro kurz 4IT115 Softwarové inženýrství");
                oProgramuAlert.setContentText("Vytvořil Ondřej Smrček - smro00");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene (webView, 500,500));
                stage.show();
                
            }
        });
    }
    
}
