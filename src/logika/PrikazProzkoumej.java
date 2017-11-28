/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param hPlan herní plán, ve kterém se hráč pohybuje
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz prozkoumej. Prozkoumává věci. Pokud se nezadá žádný parametr, 
     * prozkoumá se aktuální prostor. Pokud věc v prostoru není, vypíše se chyba.
     * 
     * @param parametry název věci, která se má prozkoumat
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return hPlan.getAktualniProstor().dlouhyPopis() + "\n" + hPlan.getBatoh().obsahBatohu();
        }
        
        String nazevVeci = parametry[0];
        String vracenyText = nazevVeci + " - ";
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        Vec vecZBatohu = hPlan.getBatoh().odeberVec(nazevVeci);
        Postava postava = hPlan.getAktualniProstor().odeberPostavu(nazevVeci);
        if (vec == null && postava == null && vecZBatohu == null) 
        {
            vracenyText += "tu není";
        }
        else if (vec == null && vecZBatohu == null)
        {
            hPlan.getAktualniProstor().vlozPostavu(postava);
            vracenyText += postava.getPopis();
        }
        else if (postava == null && vecZBatohu == null)
        {
            hPlan.getAktualniProstor().vlozVec(vec);
            vracenyText += vec.getPopis();
        }
        else if (vec == null && postava == null)
        {
            hPlan.getBatoh().pridejVec(vecZBatohu);
            vracenyText += vecZBatohu.getPopis();
        }
        
        return vracenyText;
    }
    
    /**
     * Metoda vrací název příkazu
     * 
     * @return název příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
