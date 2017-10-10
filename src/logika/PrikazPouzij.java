/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazPouzij implementuje pro hru příkaz použij.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class PrikazPouzij implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "pouzij";
    HerniPlan hPlan;
    boolean batoh;
    int pouzityKamen;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param hPlan plán, po kterém se hráč pohybuje
     */
    public PrikazPouzij(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz použij. Používá věci různým způsobem. Pokud je věc použitelná v daném prostoru,
     * použije se. Pokud není použitelná v aktuálním prostoru, vypíše se chyba.
     * 
     * @param parametry název věci, která se má použít
     * @return zpráva, která se vypíše
     */
    public String proved(String... parametry)
    {
        if (parametry.length < 1)
        {
            return "Co mám použít?";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        batoh = false;
        
        if (vec == null)
        {
            vec = hPlan.getBatoh().odeberVec(nazevVeci);
            batoh = true;
        }
        
        if (vec == null)
        {
            return nazevVeci + " tu není.";
        }
        
        if (vec.isPouzitelna())
        {
            if (hPlan.getAktualniProstor().equals(vec.getPouzitelnaV()) || vec.getPouzitelnaV() == null)
            {
                if (nazevVeci.equals("plamenomet"))
                {
                    hPlan.setKonecHry(true);
                    return "Záhadné postavě se nelíbilo, když jsi začal sahat po plamenometu, proto tě zastavila. Bohužel jsi to nepřežil.";
                }
                
                if (nazevVeci.equals("lektvar"))
                {
                    Vec mec = hPlan.getAktualniProstor().odeberVec("mec");
                    mec.setPrenositelna(true);
                    hPlan.getAktualniProstor().vlozVec(mec);
                    Prostor sousedni = hPlan.getAktualniProstor().vratSousedniProstor("s_detektorem");
                    sousedni.setZamceno(false);
                    return "Najednou se cítíš plný síly, že bys nadzvedl obrovský balvan. Nebo něco, co je v kameni zabořené.";
                }
                
                if (nazevVeci.equals("mec"))
                {
                    hPlan.getAktualniProstor().vlozVec(vec);
                    return "Když má na sobě pořád ten kámen, špatně se s ním operuje. Zkus těma dveřma normálně projít, třeba je svou nově nabytou silou odsekneš.";
                }
                
                if (nazevVeci.equals("kamen"))
                {
                    hPlan.getAktualniProstor().vlozVec(vec);
                    if (pouzityKamen == 0)
                    {
                        pouzityKamen++;
                        return "Pohladil jsi kámen a kupodivu tě to uvolnilo. Máš nutkání to udělat znovu.";
                    }
                    
                    if (pouzityKamen == 1)
                    {
                        pouzityKamen++;
                        return "Pořád ještě hladíš kámen? Je vidět, že to k něčemu bylo. Nyní je mnohem čistější než předtím.";
                    }
                    
                    if (pouzityKamen == 2)
                    {
                        pouzityKamen++;
                        return "Po třetím pohlazení se ozval hrubý neznámý hlas. Hlubokým tónem řekl: \"Není všechno zlato, co se třpytí.\" Což je asi pravda, protože se teď kámen třpytí, ale není ze zlata.";
                    }
                    
                    if (pouzityKamen == 3)
                    {
                        hPlan.setKonecHry(true);
                        return "\"Varoval jsem tě,\" ozval se stejný hlas. \"Teď budeš trpět!\"\nZemřel jsi tak hroznou smrtí, že si ji ani nedokážeš představit. Proto pro mě nemá smysl, abych ji popisoval.";
                    }
                }
                
                if (nazevVeci.equals("detektor"))
                {
                    hPlan.getBatoh().getSeznam().clear();
                    hPlan.getAktualniProstor().setZmeneno(true);
                    return "Vysypal jsi všechny věci z batohu na pás. Neboj se, snad se ti v příští místnosti vrátí.";                    
                }
                
                if (nazevVeci.equals("paka"))
                {
                    hPlan.setKonecHry(true);
                    return "Po zatáhnutí za páku se spustil alarm a otevřely se dveře, z nich vyskočil velký šváb a se zlodušským smíchem začal vyprávět: \n\"Konečně! Ani nevíš, jak dlouho jsem na tuto chvíli čekal. Když jsem si nechával postavit tenhle bunkr, nikdy mě nenapadlo, že na tu páku nedosáhnu.. Ale teď, když jsi za ni zatáhl, jsi na celý svět vypustil několik atomových bomb. \nGratuluji, vypadá to, že jsi jediný člověk, co přežil.\"";
                }
                return "Věc " + nazevVeci + "použita.";
            }
        }
        
        if (batoh)
        {
            hPlan.getBatoh().pridejVec(vec);
        }
        else
        {
            hPlan.getAktualniProstor().vlozVec(vec);
        }
                    
        if (nazevVeci.equals("lektvar"))
        {
            return "Možná to bude znít hloupě, ale lektvar můžeš použít jen v místnosti s mečem.";
        }
        
        return nazevVeci + " tady nemůžeš použít.";
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
