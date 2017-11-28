/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Zobrazuje mapu v grafickém prostředí
 *
 * @author Ondřej Smrček
 */
public class Mapa extends AnchorPane implements Observer{
    private Main main;
    private IHra hra;
    private Circle tecka;
    private Image image;
    private ImageView imageView;
    
    /**
     * Konstruktor třídy
     * 
     * @param hra 
     */
    public Mapa(IHra hra){
        this.hra = hra;
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    /**
     * Počáteční nastavení třídy
     * 
     */
    private void init(){
        image = new Image(Main.class.getResourceAsStream("/zdroje/mapa"+hra.getHerniPlan().getAktualniProstor().getLevel()+".jpg"),300,450,false,true);
        imageView = new ImageView(image);
        tecka = new Circle(10, Paint.valueOf("red"));
        
//        this.setTopAnchor(tecka, 0.0);
//        this.setLeftAnchor(tecka, 0.0);
        
        this.getChildren().addAll(imageView, tecka);
        update();
    }
    
    /**
     * Restartování třídy při započetí nové hry
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
        image = new Image(Main.class.getResourceAsStream("/zdroje/mapa"+hra.getHerniPlan().getAktualniProstor().getLevel()+".jpg"),300,450,false,true);
        imageView.setImage(image);
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
