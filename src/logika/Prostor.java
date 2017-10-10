package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Ondřej Smrček
 * @version ZS 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map<String, Postava> postavy;
    private boolean zamceno;
    private String popisZamceno;
    private String popisPo;
    private boolean zmeneno;
    
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param popisPo popis prostoru po nějaké akci
     * @param zamceno hodnota true, pokud je prostor zamceny, jinak false
     */
    public Prostor(String nazev, String popis, String popisPo, boolean zamceno) {
        this.nazev = nazev;
        this.popis = popis;
        this.popisPo = popisPo;
        this.zamceno = zamceno;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
    }
    
    /**
     * Nastavuje popis, pokud je prostor zamčený.
     * 
     * @param popisZamceno popis zamčeného prostoru
     */
    public void nastavZamceno(String popisZamceno)
    {
        this.popisZamceno = popisZamceno;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        if (zmeneno)
        {
            return "Jsi v mistnosti/prostoru" + popisPo + ".\n"
                + popisVychodu() + "\n"
                + popisPostav() + "\n"
                + popisVeci();
        }
        return "Jsi v mistnosti/prostoru" + popis + ".\n"
                + popisVychodu() + "\n"
                + popisPostav() + "\n"
                + popisVeci();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci, které se nachází v aktuálním prostoru.
     * 
     * @return Popis věcí - názvů věcí v prostoru
     */
    public String popisVeci() {
        String vracenyText = "veci:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }
        return vracenyText;
    }
    /**
     * Vrací textový řetězec, který popisuje postavy, které se nachází v aktuálním prostoru.
     * 
     * @return popis postav - jména postav
     */
    public String popisPostav()
    {
        String vracenyText = "postavy:";
        for (String nazev : postavy.keySet())
        {
            vracenyText += " " + nazev;
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vkládá věci do prostoru.
     * 
     * @param vec věc, která se má do prostoru vložit
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }
    
    /**
     * Odebírá věci z prostoru.
     * 
     * @param vec věc, která se má z prostoru odebrat
     * @return věc, která se odebrala
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }
    
    /**
     * Vkládá postavy do prostoru.
     * 
     * @param postava postava, která se má do prostoru vložit
     */
    public void vlozPostavu(Postava postava)
    {
        postavy.put(postava.getJmeno(), postava);
    }
    
    /**
     * Odebírá postavy z prostoru.
     * 
     * @param jmeno jméno postavy, která se má odebrat
     * @return postava, která se odebrala
     */
    public Postava odeberPostavu(String jmeno)
    {
        return postavy.remove(jmeno);
    }
    
    /**
     * Vrací hodnotu zamčenosti.
     * 
     * @return hodnota true, pokud je prostor zamčený, jinak false
     */
    public boolean isZamceno()
    {
        return zamceno;
    }
    
    /**
     * Nastavuje hodnotu zamčenosti.
     * 
     * @param zamceno hodnota true, pokud je prostor zamčený, jinak false
     */
    public void setZamceno(boolean zamceno)
    {
        this.zamceno = zamceno;
    }
    
    /**
     * Vrací popis prostoru, který je zamčený.
     * 
     * @return popis zamčeného prostoru
     */
    public String getPopisZamceno()
    {
        return popisZamceno;
    }
    
    /**
     * Vrací hodnotu, jestli bylo něco v prostoru změněno.
     * 
     * @return hodnota true, pokud neco bylo zmeneno, jinak false
     */
    public boolean isZmeneno()
    {
        return zmeneno;
    }
    
    /**
     * Nastavuje hodnotu, jestli bylo něco v prostoru změněno.
     * 
     * @param zmeneno hodnota true, pokud neco bylo zmeneno, jinak false
     */
    public void setZmeneno(boolean zmeneno)
    {
        this.zmeneno = zmeneno;
    }
}
