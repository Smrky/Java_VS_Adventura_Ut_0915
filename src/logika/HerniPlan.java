package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Ondřej Smrček
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{
    private Prostor aktualniProstor;
    private Batoh batoh;
    private boolean konecHry;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        this.batoh = new Batoh();
        zalozProstoryHry();
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor mSPostavou = new Prostor("sPostavou", "Místnost s postavou", " s postavou. V místnosti nic moc není, pouze tajemná postava", "", false, 50, 48, 1);
        Prostor mSMecem = new Prostor("sMecem", "Místnost s mečem", " s mečem. V místnosti vidíš velký meč zaražený v kameni připomínající Excalibur. Místnost osvětlují zapálené louče.\n" +
                                                 "Z jednoho východu vychází uklidňující modrá záře", ", kde býval meč v kameni.", false, 140, 48, 2);
        Prostor mSKamenem = new Prostor("sKamenem", "Místnost s kamenem", " s kamenem. Zajímavé.. Z této místnosti vycházela modrá záře, ale ve skutečnosti se v ní nachází špinavý, zaprášený kámen zarostlý mechem. Je za ním cedulka, která píše \"Prosím, nechte kámen na svém místě\"", "", false, 230, 50, 2);
        Prostor mSDetektorem = new Prostor("sDetektorem", "Místnost s detektorem", " s detektorem. Vidíš velký detektor kovu, jako ty, které bývají na letištích, ale není u nich obsluha. Vypadá, že už dlouho nebyl používán a snad ani nefunguje.", " s detektorem, který sežral tvůj starý batoh a vybuchl.", true, 140, 134, 3);
        Prostor mSHlavici = new Prostor("sHlavici", "Místnost s hlavicí", " s atomovou hlavicí. Uprostřed místnosti je velká atomová hlavice.", ", kde původně byla atomová hlavice.", false, 140, 218, 4);
        Prostor sklad = new Prostor("sklad", "Sklad", " sklad. Je tu jen pár věcí, jinak samé harampádí.", "", false, 230, 218, 4);
        Prostor mSDvermi = new Prostor("sDvermi", "Místnost s dveřmi", " s těžkými tajuplně vypadajícími dveřmi. Vedle nich je klasická masivní páka, která pravděpodobně slouží na jejich otevírání", "", false, 140, 302, 5);
        Prostor zaDvermi = new Prostor("zaDvere", "Místnost za dveřmi", " za těžkými dveřmi. Hned, jak jsi je otevřel, jsi na něco šlápl. Po bližším prozkoumání jsi zjistil, že to byl šváb.", "", false, 140, 386, 5);                
        
        
        
        mSPostavou.setVychod(mSMecem);
        mSMecem.setVychod(mSPostavou);
        mSMecem.setVychod(mSKamenem);
        mSKamenem.setVychod(mSMecem);
        mSMecem.setVychod(mSDetektorem);
        mSDetektorem.setVychod(mSMecem);
        mSDetektorem.setVychod(mSHlavici);
        mSHlavici.setVychod(mSDetektorem);
        mSHlavici.setVychod(sklad);
        sklad.setVychod(mSHlavici);
        mSHlavici.setVychod(mSDvermi);
        mSDvermi.setVychod(mSHlavici);
        mSDvermi.setVychod(zaDvermi);
        zaDvermi.setVychod(mSDvermi);
        
        mSDetektorem.nastavZamceno("Dveře jsou zaseknuté. Možná by pomohl ten meč, který je uprostřed místnosti.");
        
        Vec plamenomet = new Vec("plamenomet", "Plamenomet", "Nejlepší přítel člověka. Ještě nikdy nezklamal.", true, true);
        plamenomet.nastavPouzitelnost(mSPostavou);
        Vec lektvar = new Vec("lektvar", "Lektvar", "Lektvar s podivným zápachem.", true, true);
        Vec louc = new Vec("louc", "Louč", "Klasická zapálená louč.", true, false);
        Vec mec = new Vec("mec", "Meč", "Velký meč v kameni.", false, false);
        mec.nastavPouzitelnost(mSMecem);
        Vec kamen = new Vec("kamen", "Kámen", "Ošklivý kámen.", true, true);
        kamen.nastavPouzitelnost(mSKamenem);
        Vec detektor = new Vec("detektor", "Detektor", "Velký detektor kovu připevněný k zemi.", false, true);
        detektor.nastavPouzitelnost(mSDetektorem);
        Vec hlavice = new Vec("hlavice", "Hlavice", "Atomová hlavice.", true, false);
        Vec pneumatika = new Vec("pneumatika", "Pneumatika", "Pneumatika na auto. Pravděpodobně ti k ničemu nebude.", true, false);
        Vec helma = new Vec("helma", "Helma", "Vojenská helma.", true, false);
        Vec paka = new Vec("paka", "Páka", "Masivní páka, která asi něco udělá, když ji použiješ.", false, true);
        paka.nastavPouzitelnost(mSDvermi);
        
        String reklamniLetak = "Reklamní leták od společnosti na výrobu detektorů kovu.";
        
        Vec reklama1 = new Vec("reklama1", "Reklamní leták", reklamniLetak, true, false);
        Vec reklama2 = new Vec("reklama2", "Reklamní leták", reklamniLetak, true, false);
        Vec reklama3 = new Vec("reklama3", "Reklamní leták", reklamniLetak, true, false);
        Vec reklama4 = new Vec("reklama4", "Reklamní leták", reklamniLetak, true, false);
        Vec reklama5 = new Vec("reklama5", "Reklamní leták", reklamniLetak, true, false);
        

        
        Postava tajemnaPostava = new Postava("postava", "Postava", "Tajemná postava, tvář jí zakrývá černá kapuce, přesto vypadá ohyzdně. Má cigáro v puse a nervózně překračuje po místnosti.", "\"Čágo, nemáš náhodou voheň? Vyplatí se ti to.\"");
        tajemnaPostava.nastavVymenu(lektvar, louc, "\"Už jsem ti snad dal lektvar. Co po mně ještě chceš?\"", "\"Dík, jseš kámoš. Tady máš tohle, mně to nějak nechutná a už je to stejně asi prošlý.\"", "\"Co s tim jako mám dělat?\"");
        
        mSPostavou.vlozPostavu(tajemnaPostava);
        
        mSMecem.vlozVec(louc);
        mSMecem.vlozVec(mec);
        mSKamenem.vlozVec(kamen);
        mSDetektorem.vlozVec(detektor);
        mSHlavici.vlozVec(hlavice);
        mSHlavici.vlozVec(reklama1);
        mSHlavici.vlozVec(reklama2);
        mSHlavici.vlozVec(reklama3);
        mSHlavici.vlozVec(reklama4);
        mSHlavici.vlozVec(reklama5);
        sklad.vlozVec(pneumatika);
        sklad.vlozVec(helma);
        mSDvermi.vlozVec(paka);
        
        batoh.pridejVec(plamenomet);
        
        
        aktualniProstor = mSPostavou;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Vraci hodnotu, jestli je konec hry.
     * 
     * @return hodnota true, pokud už je konec hry, jinak false
     */
    public boolean hracVyhral() {
        return konecHry;
    }
    
    /**
     * Nastavuje, jestli je konec hry.
     * 
     * @param konecHry hodnota true, pokud má nastat konec hry, jinak false
     */
    public void setKonecHry(boolean konecHry)
    {
        this.konecHry = konecHry;
    }
    
    /**
     * Metoda vrací odkaz na hráčův batoh.
     * 
     * @return hráčův batoh
     */
    public Batoh getBatoh()
    {
        return batoh;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
    

}
