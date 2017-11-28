/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Set;



/*******************************************************************************
 * Třída Vec představuje všechny věci ve hře a s nimi spojené akce.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String jmeno;
    private String popis;
    private boolean prenositelna;
    private boolean pouzitelna;
    private Prostor pouzitelnaV;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param nazev název věci (používá se v kódu)
     *  @param jmeno jméno věci (hezčí název - používá se v GUI)
     *  @param popis popis věci
     *  @param prenositelna hodnota true, pokud je věc přenositelná, jinak false
     *  @param pouzitelna hodnota true, pokud je věc použitelná, jinak false
     */
    public Vec(String nazev, String jmeno, String popis, boolean prenositelna, boolean pouzitelna)
    {
        this.nazev = nazev;
        this.jmeno = jmeno;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.pouzitelna = pouzitelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Nastavuje použitelnost věci v jedné konkrétní místnosti.
     * 
     * @param pouzitelnaV prostor, ve kterém se dá věc použít
     */
        public void nastavPouzitelnost(Prostor pouzitelnaV)
    {
        this.pouzitelnaV = pouzitelnaV;
    }
    
    /**
     * Vrací název věci
     * 
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Vrací popis věci
     * 
     * @return popis věci
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Vraci hodnotu, jestli je věc přenositelná.
     * 
     * @return hodnota true, pokud je věc přenositelná, jinak false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
    
    /**
     * Vrací hodnotu, jestli je věc použitelná.
     * 
     * @return hodnota true, pokud je věc použitelná, jinak false
     */
    public boolean isPouzitelna()
    {
        return pouzitelna;
    }
    
    /**
     * Vrací prostor, ve kterém je věc použitelná.
     * 
     * @return prostor, ve kterém je věc použitelná.
     */
    public Prostor getPouzitelnaV()
    {
        return pouzitelnaV;
    }
    
    /**
     * Nastavuje hodnotu, jestli je věc přenositelná.
     * 
     * @param prenositelna true, pokud je přenositelná, jinak false
     */
    public void setPrenositelna(boolean prenositelna)
    {
        this.prenositelna = prenositelna;
    }
    
    /**
     * Nastavuje hodnotu, jestli je věc použitelná.
     * 
     * @param pouzitelna true, pokud je použitelná, jinak false
     */
    public void setPouzitelna(boolean pouzitelna)
    {
        this.pouzitelna = pouzitelna;
    }

     /**
     * Vrací jméno věci (GUI účely)
     * 
     * @return
     */
    public String getJmeno() {
        return jmeno;
    }

}
