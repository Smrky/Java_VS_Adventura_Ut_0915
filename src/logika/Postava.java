/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Postava představuje postavy ve hře a s nimi spojené akce.
 *
 * @author    Ondřej Smrček
 * @version   1.00.000
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String popis;
    private Vec chce;
    private Vec ma;
    private String mluvPred;
    private String mluvPo;
    private String recChce;    
    private String recNechce;
    boolean vymena = false;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param jmeno jméno postavy
     *  @param popis popis postavy
     *  @param proslov proslov postavy
     */
    public Postava(String jmeno, String popis, String proslov)
    {
        this.jmeno = jmeno;
        this.popis = popis;
        mluvPred = proslov;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda slouží pro rozšíření konstruktoru, pokud s postavou bude možné měnit věci
     * 
     * @param ma věc, kterou postava má a nabízí ji.
     * @param chce věc, kterou postava chce a výměnou za ni nabízí jinou věc
     * @param mluvPo String s proslovem, co bude říkat po úspěšné výměně
     * @param recChce String s proslovem, co řekne při úspěšné výměně
     * @param recNechce String s proslovem, co řekne při neúspěšné výměně
     */
    public void nastavVymenu(Vec ma, Vec chce, String mluvPo, String recChce, String recNechce)
    {
        this.ma = ma;
        this.chce = chce;
        this.mluvPo = mluvPo;
        this.recChce = recChce;
        this.recNechce = recNechce;
    }
    
    /**
     * Vrací jméno postavy
     * 
     * @return jméno postavy
     */
    public String getJmeno()
    {
        return jmeno;
    }
    
    /**
     * Vrací popis postavy
     * 
     * @return popis postavy
     */
    public String getPopis()
    {
        return popis;
    }
    
    /**
     * Vraci proslov postavy. Určuje se podle toho, jestli již proběhla výměna.
     * 
     * @return proslov postavy
     */
    public String getMluv()
    {
        if (vymena)
        {
            return mluvPo;
        }
        return mluvPred;
    }
    
    /**
     * Metoda určuje, jestli je nabízená věc i věcí chtěnou. Vrací věc, která se dá hráčovi.
     * 
     * @param nabidka věc, kterou nabízí hráč postavě
     * @return vymena věc, kterou dostane hráč od postavy
     */
    public Vec vymena(Vec nabidka)
    {
        if (nabidka.equals(chce))
        {
            vymena = true;
            return ma;
        }
        vymena = false;
        return null;
    }
    
    /**
     * Vrací řeč, co řekne postava, pokud jí bude nabídnuta věc, kterou chce.
     * 
     * @return řeč, co řekne postava, pokud jí bude nabídnuta věc, kterou chce
     */
    public String getRecChce()
    {
        return recChce;
    }
    
    /**
     * Vrací řeč, co řekne postava, pokud jí bude nabídnuta věc, kterou nechce.
     * 
     * @return řeč, co řekne postava, pokud jí bude nabídnuta věc, kterou nechce
     */
    public String getRecNechce()
    {
        return recNechce;
    }
    
    /**
     * Vrací hodnotu, jestli výměna proběhla v pořádku.
     * 
     * @return true pokud výměna byla v pořádku, jinak false
     */
    public boolean isVymena()
    {
        return vymena;
    }
    
    /**
     * Vrací název věci, kterou postava má a nabízí ji hráči.
     * 
     * @return název věci
     */
    public String getMa()
    {
        return ma.getNazev();
    }
    
    /**
     * Vrací název věci, kterou postava chce.
     * 
     * @return název věci
     */
    public String getChce()
    {
        return chce.getNazev();
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
