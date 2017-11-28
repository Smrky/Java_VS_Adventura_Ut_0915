/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazDej implementuje pro hru příkaz dej.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazDej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "dej";
    HerniPlan hPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param hPlan plán, po kterém se hráč pohybuje
     */
    public PrikazDej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz dej. Dává věci výměnou za jiné. Pokud je věc v batohu a postava ji chce, proběhne výměna věcí.
     * Pokud věc v batohu není nebo ji postava nechce, vypíše se chyba
     * 
     * @param parametry název věci, která se má dát a postava, které se věc má dát
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry)
    {
        if (parametry.length < 1)
        {
            return "Co mám komu dát?";
        }
        
        if (parametry.length < 2)
        {
            return "Komu to mám dát?";
        }
        
        String nazevVeci = parametry[0];
        String jmenoPostavy = parametry[1];
        
        Postava postava = hPlan.getAktualniProstor().odeberPostavu(jmenoPostavy);
        Vec vec = hPlan.getBatoh().odeberVec(nazevVeci);
        
        if (vec == null)
        {
            return "Jak chceš někomu dávat něco, co nemáš?";
        }
        
        if (postava == null)
        {
            hPlan.getBatoh().pridejVec(vec);
            return "Taková postava tady bohužel není.";
        }
        
        hPlan.getAktualniProstor().vlozPostavu(postava);
        
        if (postava.isVymena())
        {
            return "Díky, ale víc toho už nepotřebuju.";
        }
        
        if (postava.vymena(vec) != null)
        {
            hPlan.getBatoh().pridejVec(postava.vymena(vec));
        }
        
        if (postava.isVymena())
        {
            return postava.getRecChce() + "\n" + "Do batohu přidána věc '" + postava.getMa() + "'.";
        }
        hPlan.getBatoh().pridejVec(vec);
        return postava.getRecNechce();
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
}
