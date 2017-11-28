/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;


/*******************************************************************************
 * Třída Batoh představuje inventář hry a s ním spojené akce.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    private Set<Vec> seznam;
    static final int KAPACITA = 5;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh()
    {
        seznam = new HashSet<>();
    }
    
    /**
     * Metoda přidává věc do batohu
     * 
     * @param vec věc, která se přidává do batohu
     * @return hodnotu true, pokud se daná věc do batohu přidala, jinak false
     */
    public boolean pridejVec(Vec vec)
    {
        if(seznam.size() < KAPACITA)
        {
            return seznam.add(vec);
        }
        return false;
    }
    
    /**
     * Vrací textový řetězec, který vypisuje všechny věci z batohu.
     * 
     * @return Seznam věcí v batohu
     */
    public String obsahBatohu()
    {
        String vracenyText = "batoh:";        
        for(Vec v : seznam)
        {
            vracenyText += " " + v.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Metoda odebírá věc z batohu
     * 
     * @param nazevVeci název věci na odebrání z batohu
     * @return objekt třídy Vec, když je věc odebrána, jinak null
     */
    public Vec odeberVec(String nazevVeci)
    {
        for(Vec v : seznam)
        {
            if(v.getNazev().equals(nazevVeci))
            {
                seznam.remove(v);
                return v;
            }
        }
        return null;
    }
    
    /**
     * Metoda ukazuje, jestli je batoh plný.
     * 
     * @return hodnota true, pokud je batoh plný, jinak false 
     */
    public boolean jePlny()
    {
        return seznam.size() >= KAPACITA;
    }
    
    /**
     * Metoda ukazuje, jestli je batoh prázdný.
     * 
     * @return hodnota true, pokud je batoh prázdný, jinak false
     */
    public boolean jePrazdny()
    {
        boolean prazdno = false;
        if (seznam.isEmpty())
        {
            prazdno = true;
        }
        return prazdno;
    }
    
    /**
     * Metoda vrací celý seznam věcí v batohu.
     * 
     * @return kolekce věcí v batohu
     */
    public Collection<Vec> getSeznam()
    {
        return seznam;
    }

}
