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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída zobrazuje grafické rozhraní věcí v prostoru
 *
 * @author Ondřej Smrček
 */
public class VeciSeznam extends ListView implements Observer{
    
    public IHra hra;
    private Main main;
    public TextArea centralText;
    ObservableList<BorderPane> dataVeci = FXCollections.observableArrayList();
    
    /**
     * Konstruktor třídy
     * 
     * @param hra
     * @param centralText
     * @param main 
     */
    public VeciSeznam (IHra hra, TextArea centralText, Main main) {
        this.hra = hra;
        this.main = main;
        this.centralText = centralText;
        init();
    }
    
    
    /**
     * Počáteční nastavení třídy
     * 
     */
    private void init() {
        hra.getHerniPlan().registerObserver(this);
        this.setItems(dataVeci);
        this.setPrefWidth(170);
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
    dataVeci.clear();
    
        for(Vec vec : hra.getHerniPlan().getAktualniProstor().veci.values()) {
            BorderPane veciOkno = new BorderPane();
            FlowPane vecX = new FlowPane();
            
            String nazev = "/zdroje/" + vec.getNazev() + ".jpg";

            
            
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream(nazev), 50, 50, false, false));
            Label label = new Label(vec.getJmeno(), imageView);
            veciOkno.setTop(label);
            
            Button buttonProzkoumej = new Button("Prozkoumej");
            Button buttonPouzij = new Button("Použij");
            Button buttonVezmi = new Button("Vezmi");
            
            buttonProzkoumej.setOnMouseClicked((event) -> {
                    String prikaz = "prozkoumej " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            buttonPouzij.setOnMouseClicked((event) -> {
                    String prikaz = "pouzij " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            buttonVezmi.setOnMouseClicked((event) -> {
                    String prikaz = "vezmi " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            vecX.getChildren().addAll(buttonProzkoumej, buttonPouzij, buttonVezmi);
            veciOkno.setCenter(vecX);
            dataVeci.add(veciOkno);
            
            
        }
        main.konecHry();
    }  
}
