/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Postava;
import main.Main;
import utils.Observer;

/**
 *  Třída zobrazuje grafické rozhraní seznamu postav a proveditelných příkazů s vazbou na postavy
 * 
 * @author Smrky
 */
public class PostavySeznam extends ListView implements Observer{
    
    public IHra hra;
    private Main main;
    public TextArea centralText;
    ObservableList<BorderPane> dataPostav = FXCollections.observableArrayList();

    /**
     * Konstruktor třídy
     * 
     * @param hra
     * @param centralText
     * @param main 
     */
    public PostavySeznam (IHra hra, TextArea centralText, Main main) {
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
        this.setItems(dataPostav);
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
     * 
     */
    @Override
    public void update() {
        dataPostav.clear();
        
        for (Postava postava : hra.getHerniPlan().getAktualniProstor().postavy.values()) {
            BorderPane postavaOkno = new BorderPane();
            
            FlowPane postavaX = new FlowPane();
            String nazevPostavy = postava.getJmeno();
            Label labelPostava = new Label(nazevPostavy);
            postavaOkno.setTop(labelPostava);
            
            Button buttonProzkoumej = new Button("Prozkoumej");
            buttonProzkoumej.setOnMouseClicked((event) -> {
                    String prikaz = "prozkoumej " + postava.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            Button buttonMluv = new Button("Mluv");
            buttonMluv.setOnMouseClicked((event) -> {
                    String prikaz = "mluv " + postava.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            postavaX.getChildren().addAll(buttonProzkoumej, buttonMluv);
            postavaOkno.setCenter(postavaX);
            dataPostav.add(postavaOkno);
            main.konecHry();
        }   
    }
    
}
