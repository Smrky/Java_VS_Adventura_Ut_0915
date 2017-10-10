package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha, Ondřej Smrček
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private boolean detektor;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else if (!sousedniProstor.isZamceno())
        {
            plan.setAktualniProstor(sousedniProstor);
            if (smer.equals("s_hlavici"))
            {
                if (!detektor && plan.getBatoh().getSeznam().size() > 0)
                {
                    plan.setKonecHry(true);
                    return "Když ses snažil projít detektorem, zapípal a vystřelily z něj lasery. Bohužel se všechny trefily do tebe, takže jsou z tebe teď puzzle.";
                }
                else if (!detektor)
                {
                    Vec vec = plan.getAktualniProstor().odeberVec("reklama1");
                    plan.getBatoh().pridejVec(vec);
                    vec = plan.getAktualniProstor().odeberVec("reklama2");
                    plan.getBatoh().pridejVec(vec);
                    vec = plan.getAktualniProstor().odeberVec("reklama3");
                    plan.getBatoh().pridejVec(vec);
                    vec = plan.getAktualniProstor().odeberVec("reklama4");
                    plan.getBatoh().pridejVec(vec);
                    vec = plan.getAktualniProstor().odeberVec("reklama5");
                    plan.getBatoh().pridejVec(vec);
                    detektor = true;
                    return "Ve stroji se nějak zachrastilo a začalo se z něj kouřit. \nVyjel z něj batoh, ale je úplně jiný, než ten tvůj."
                           + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            }
            
            if (smer.equals("za_dvere"))
            {
                plan.setKonecHry(true);
                return "Hned, jak jsi otevřel tyto dveře, jsi na něco šlápl. Po bližším prozkoumání jsi zjistil, že to byl obrovský šváb.\nVidíš před sebou klec a v ní zohyzdněné individuum. Když jsi mu otevřel klec, radostně tě obejmul a začal povídat:\n\"Och, ani nevíš, jak jsem ti vděčný! Byl jsem tady vězněn již několik let.. Ten šváb, kterého jsi zašlápl, byl švábní generál, který měl v plánu zničit celý svět. Naštěstí jsi mu v tom včas zabránil. \nDobrá práce!\"";
            }
            return sousedniProstor.dlouhyPopis() + "\n" + plan.getBatoh().obsahBatohu();
        }
        
        return sousedniProstor.getPopisZamceno();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
