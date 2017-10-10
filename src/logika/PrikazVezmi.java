/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param hPlan plán, po kterém se hráč pohybuje
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz vezmi. Sbírá věci. Pokud je věc v prostoru, sebere věc.
     * Pokud věc v prostoru není, vypíše se chyba
     * 
     * @param parametry název věci, která se má sebrat
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
        if (herniPlan.getBatoh().jePlny()) 
        {
            herniPlan.getAktualniProstor().vlozVec(vec);
            if (nazevVeci.equals("hlavice"))
            {
                return "Promiň, ale tohle se ti do batohu nevejde. Zkus z batohu vyndat alespoň jeden reklamní letáček a pak se ti to tam snad vejít bude.";
            }
            return "batoh je plný";
        }
        
        herniPlan.getBatoh().pridejVec(vec);
        if (nazevVeci.equals("mec"))
        {
            vec.setPouzitelna(true);
            herniPlan.getAktualniProstor().setZmeneno(true);
            return "Máš takovou sílu, že jsi s mečem vyrval i ten obrovský kámen, ve kterém byl. Už bude asi nepoužitelný, ale stejně sis ho dal do batohu.";
        }
        
        if (nazevVeci.equals("kamen"))
        {
            herniPlan.setKonecHry(true);
            return "Zřejmě jsi měl poslechnout tu ceduli.. Takhle jsi umřel.";
        }

        return "věc '" + nazevVeci + "' jsi uložil do batohu";            
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
