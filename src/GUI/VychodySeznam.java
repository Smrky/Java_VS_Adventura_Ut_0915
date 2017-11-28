/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Prostor;
import main.Main;
import utils.Observer;

/**
 * Třída zobrazuje grafické rozhraní východů
 *
 * @author Ondřej Smrček
 */
public class VychodySeznam extends ListView implements Observer{
    
    public IHra hra;
    private Main main;
    public TextArea centralText;
    ObservableList<FlowPane> dataVychodu = FXCollections.observableArrayList();

    /**
     * Kontstruktor třídy
     * 
     * @param hra
     * @param centralText
     * @param main 
     */
    public VychodySeznam (IHra hra, TextArea centralText, Main main) {
        this.main = main;
        this.hra = hra;
        this.centralText = centralText;
        init();
    }
    
    /**
     * Počáteční nastavení třídy
     * 
     */
    private void init() {
        hra.getHerniPlan().registerObserver(this);
        this.setItems(dataVychodu);
        this.setPrefHeight(300);
        update();
    }
    
    /**
     * Restartování třídy při nové hře
     * 
     * @param novaHra 
     */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    /**
     * Aktualizace třídy
     */
    @Override
    public void update() {
        dataVychodu.clear();
        
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()) {
            FlowPane vychod = new FlowPane();
            String nazevVychodu = prostor.getJmeno();
            Button button = new Button(nazevVychodu);
            button.setPrefWidth(280);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String prikaz = "jdi " + prostor.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                }
            });
            vychod.getChildren().add(button);
            dataVychodu.add(vychod);
            
        }
        main.konecHry();
    }
    
}
