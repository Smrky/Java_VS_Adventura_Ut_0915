/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazOdeber implementuje pro hru příkaz odeber.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazOdeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odeber";
    
    private HerniPlan hPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param hPlan plán, po kterém se hráč pohybuje
     */
    public PrikazOdeber(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz vezmi. Sbírá věci. Pokud je věc v prostoru, sebere věc.
     * Pokud věc v prostoru není, vypíše se chyba
     * 
     * @param parametry název věci, která se má sebrat
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry)
    {
        if (parametry.length < 1)
        {
            return "nevím, co mám odebrat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getBatoh().odeberVec(nazevVeci);
        
        if (vec == null)
        {
            return "věc '" + nazevVeci + "' v batohu nemáš";
        }
        
        if (nazevVeci.equals("mec"))
        {
            vec.setPouzitelna(false);
        }
        hPlan.getAktualniProstor().vlozVec(vec);
        return "věc '" + nazevVeci + "' odebrána z batohu";
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
