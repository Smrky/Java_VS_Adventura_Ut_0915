/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Postava;
import logika.Vec;
import main.Main;
import utils.Observer;


/**
 * Třída BatohSeznam představuje grafiké rozhraní pro třídu Batoh. Zobrazuje obrázky a tlačítka, které představují možné příkazy
 *
 * @author Ondřej Smrček
 */
public class BatohSeznam extends ListView implements Observer{

    private Main main;
    public IHra hra;
    public TextArea centralText;
    ObservableList<BorderPane> dataBatohu = FXCollections.observableArrayList();
    
    /**
     * Konstruktor třídy
     * 
     * @param hra
     * @param centralText
     * @param main 
     */
    public BatohSeznam(IHra hra, TextArea centralText, Main main) {
        this.hra = hra;
        this.centralText = centralText;
        this.main = main;
        init();
    }
    
    /**
     * Počáteční nastavení třídy
     * 
     */
    private void init() {
        hra.getHerniPlan().registerObserver(this);
        this.setItems(dataBatohu);
        this.setPrefWidth(300);
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
        dataBatohu.clear();
        for(Vec vec : hra.getHerniPlan().getBatoh().getSeznam()) {
            BorderPane batohOkno = new BorderPane();
            FlowPane vecX = new FlowPane();
            
            String nazev = "/zdroje/" + vec.getNazev() + ".jpg";

            
            
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream(nazev), 50, 50, false, false));
            Label label = new Label(vec.getJmeno(), imageView);
            batohOkno.setTop(label);
            
            Button buttonProzkoumej = new Button("Prozkoumej");
            Button buttonPouzij = new Button("Použij");
            Button buttonOdeber = new Button("Odeber");
            Button buttonDej = new Button("Dej");
            
            buttonProzkoumej.setOnMouseClicked((event) -> {
                    String prikaz = "prozkoumej " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);               
                });
            
            buttonDej.setOnMouseClicked((event) -> {
                List<String> choices = new ArrayList<>();
                
                for(Postava postava : hra.getHerniPlan().getAktualniProstor().postavy.values()){
                choices.add(postava.getNazev());
                }
                ChoiceDialog<String> dialog = new ChoiceDialog<>(null,choices);
                dialog.setTitle("Dej " + vec.getJmeno());
                dialog.setHeaderText("Vyber postavu, které chceš věc dát");
                dialog.setContentText("Postava: ");
                
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    String prikaz = "dej " + vec.getNazev() + " " + result.get();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                }
            });
            
            buttonPouzij.setOnMouseClicked((event) -> {
                    String prikaz = "pouzij " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            buttonOdeber.setOnMouseClicked((event) -> {
                    String prikaz = "odeber " + vec.getNazev();
                    centralText.appendText("\n" + prikaz);
                    hra.zpracujPrikaz(prikaz);
                });
            
            vecX.getChildren().addAll(buttonProzkoumej, buttonPouzij, buttonOdeber, buttonDej);
            batohOkno.setCenter(vecX);
            dataBatohu.add(batohOkno);
            
            
        }
        main.konecHry();
    }
    
}
