/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.BatohSeznam;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.PostavySeznam;
import GUI.VeciSeznam;
import GUI.VychodySeznam;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 * Hlavní třída
 *
 * @author Ondřej Smrček
 */
public class Main extends Application {


     
    public TextArea centralText = new TextArea();
    
    
    private IHra hra;

    public TextField zadejPrikazTextArea;
    
    
    private Mapa mapa;
    private MenuLista menuLista;
    private VychodySeznam vychodySeznam;
    private BatohSeznam batohSeznam;
    private VeciSeznam veciSeznam;
    private PostavySeznam postavySeznam;
    
    private Stage stage;

    /**
     * Startovací metoda třídy
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
                
        hra = new Hra(centralText);
        
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        vychodySeznam = new VychodySeznam(hra, centralText, this);
        batohSeznam = new BatohSeznam(hra, centralText, this);
        veciSeznam = new VeciSeznam(hra, centralText, this);
        postavySeznam = new PostavySeznam(hra, centralText, this);
        
        BorderPane borderPane = new BorderPane();
        

        //centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        //Listener - bottom autoscroll
        centralText.textProperty().addListener(new ChangeListener<Object>() {
        @Override
        public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
        centralText.setScrollTop(Double.MAX_VALUE);
        
        
  }
});

        Label zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        zadejPrikazTextArea = new TextField();
        
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                hra.zpracujPrikaz(vstupniPrikaz);
                            
                
                zadejPrikazTextArea.setText("");
                
                konecHry();
                
            }
        });
        
      //Levý panel
      BorderPane levyPanel = new BorderPane();
      levyPanel.setTop(mapa);
      levyPanel.setCenter(vychodySeznam);
      levyPanel.setBottom(veciSeznam);
      
      //Pravý panel
      BorderPane pravyPanel = new BorderPane();
      pravyPanel.setTop(batohSeznam);
      pravyPanel.setCenter(postavySeznam);
      
      //Dolní lišta s elementy
      FlowPane dolniLista = new FlowPane();
      dolniLista.setAlignment(Pos.CENTER);
      dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
      
      //Celkové rozložení
      borderPane.setLeft(levyPanel);
      borderPane.setRight(pravyPanel);
      borderPane.setBottom(dolniLista);
      borderPane.setTop(menuLista);
      
        
      
      Scene scene = new Scene(borderPane, 1500, 950);
      primaryStage.setTitle("Adventura");

      primaryStage.setScene(scene);
      primaryStage.show();
      zadejPrikazTextArea.requestFocus();
      
      
    }

    /**
     * Vrací stage
     * 
     * @return 
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Vrací mapu 
     * 
     * @return 
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * Metoda pro rozhodnutí, jestli pustit hru v textovém nebo grafickém rozhraní
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            } else {
                System.out.println("Neplatný parametr");
                System.exit(1);
            }
        }
    }
    
    /**
     * Vrací centralText
     * 
     * @return 
     */
    public TextArea getCentralText() {
        return centralText;
    }
    
    /**
     * Nastavuje novou hru
     * 
     * @param hra 
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }

    /**
     * Nastavuje stage
     * 
     * @param primaryStage 
     */
    private void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Vrací seznam východů v grafickém rozhraní
     * 
     * @return 
     */
    public VychodySeznam getVychodySeznam() {
        return vychodySeznam;
    }

    /**
     * Vrací batoh v grafickém rozhraní
     * 
     * @return 
     */
    public BatohSeznam getBatohSeznam() {
        return batohSeznam;
    }

    /**
     * Vrací seznam věcí v grafickém rozhraní
     * 
     * @return 
     */
    public VeciSeznam getVeciSeznam() {
        return veciSeznam;
    }

    /**
     * Vrací seznam postav v grafickém rozhraní
     * 
     * @return 
     */
    public PostavySeznam getPostavySeznam() {
        return postavySeznam;
    }
    
    /**
     * Metoda pro kontrolu konce hry
     * 
     */
    public void konecHry(){
        if(hra.konecHry()){
            centralText.appendText(hra.vratEpilog());
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Konec hry");
            alert.setHeaderText(hra.getTextKVypsani());
            alert.setContentText("Chcete hrát znovu?");
            ButtonType buttonNewGame = new ButtonType("Nová hra");
            ButtonType buttonKonec = new ButtonType("Konec");
            alert.getButtonTypes().setAll(buttonNewGame, buttonKonec);
            Optional<ButtonType> vysledek = alert.showAndWait();
            if (vysledek.get() == buttonNewGame){
                newGame();
            }
            else if (vysledek.get() == buttonKonec) {
                System.exit(0);
            }
        }
    }
    
    /**
     * Metoda pro nastartování nové hry
     * 
     */
    public void newGame(){
        hra = new Hra(centralText);
        getMapa().newGame(hra);
        getBatohSeznam().newGame(hra);
        getVeciSeznam().newGame(hra);
        getVychodySeznam().newGame(hra);
        getPostavySeznam().newGame(hra);
        setHra(hra);
        getCentralText().setText(hra.vratUvitani());
        zadejPrikazTextArea.setEditable(true);
        zadejPrikazTextArea.clear();
    }
}
