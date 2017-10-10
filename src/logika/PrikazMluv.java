/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    HerniPlan hPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param hPlan plán, po kterém se hráč pohybuje
     */
    public PrikazMluv(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz mluv. Mluví s postavami. Pokud je postava v prostoru, promluví s ní.
     * Pokud postava v prostoru není, vypíše se chyba.
     * 
     * @param parametry jméno osoby, se kterou se má mluvit
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry)
    {
        if (parametry.length < 1)
        {
            return "Promluvil jsi sám se sebou.";
        }
        
        String jmenoPostavy = parametry[0];
        
        Postava postava = hPlan.getAktualniProstor().odeberPostavu(jmenoPostavy);
        if (postava == null)
        {
            return "Postava " + jmenoPostavy + " tu není.";
        }
        
        hPlan.getAktualniProstor().vlozPostavu(postava);
        return postava.getMluv();
    }
    
    /**
     * Metoda vrací název příkazu
     * 
     * @return název příkazu
     */
    public String getNazev()
    {
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
