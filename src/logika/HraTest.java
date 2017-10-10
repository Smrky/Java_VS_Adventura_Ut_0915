package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author   Jarmila Pavlíčková
 * @version  ZS 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals("veci: louc mec", hra1.getHerniPlan().getAktualniProstor().popisVeci());
        hra1.zpracujPrikaz("vezmi mec");
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi louc");
		assertEquals(false, hra1.konecHry());
        Vec louc = hra1.getHerniPlan().getBatoh().odeberVec("louc");
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("louc"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        hra1.zpracujPrikaz("jdi s_postavou");
		assertEquals(false, hra1.konecHry());
        assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej louc postava");
		assertEquals(false, hra1.konecHry());
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        Vec lektvar = hra1.getHerniPlan().getBatoh().odeberVec("lektvar");
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("pouzij lektvar");
		assertEquals(false, hra1.konecHry());
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("jdi s_mecem");
		assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("pouzij lektvar");
		assertEquals(false, hra1.konecHry());
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.zpracujPrikaz ("vezmi mec");
		assertEquals(false, hra1.konecHry());
        Vec mec = hra1.getHerniPlan().getBatoh().odeberVec("mec");
        hra1.getHerniPlan().getBatoh().pridejVec(mec);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("mec"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_detektorem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("pouzij detektor");
		assertEquals(false, hra1.konecHry());
		assertEquals(true, hra1.getHerniPlan().getBatoh().jePrazdny());
		hra1.zpracujPrikaz("jdi s_hlavici");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_hlavici", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("jdi s_dvermi");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_dvermi", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("pouzij paka");
		assertEquals(true, hra1.konecHry());
		
		hra1 = new Hra();
		assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals("veci: louc mec", hra1.getHerniPlan().getAktualniProstor().popisVeci());
        hra1.zpracujPrikaz("vezmi mec");
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi louc");
		assertEquals(false, hra1.konecHry());
        louc = hra1.getHerniPlan().getBatoh().odeberVec("louc");
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("louc"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        hra1.zpracujPrikaz("jdi s_postavou");
		assertEquals(false, hra1.konecHry());
        assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej louc postava");
		assertEquals(false, hra1.konecHry());
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        lektvar = hra1.getHerniPlan().getBatoh().odeberVec("lektvar");
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("pouzij lektvar");
		assertEquals(false, hra1.konecHry());
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("jdi s_mecem");
		assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("pouzij lektvar");
		assertEquals(false, hra1.konecHry());
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.zpracujPrikaz ("vezmi mec");
		assertEquals(false, hra1.konecHry());
        mec = hra1.getHerniPlan().getBatoh().odeberVec("mec");
        hra1.getHerniPlan().getBatoh().pridejVec(mec);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("mec"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_detektorem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("pouzij detektor");
		assertEquals(false, hra1.konecHry());
		assertEquals(true, hra1.getHerniPlan().getBatoh().jePrazdny());
		hra1.zpracujPrikaz("jdi s_hlavici");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_hlavici", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("jdi s_dvermi");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_dvermi", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("jdi za_dvere");
		assertEquals("za_dvere", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals(true, hra1.konecHry());
		

		hra1 = new Hra();
		assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals("veci: louc mec", hra1.getHerniPlan().getAktualniProstor().popisVeci());
        hra1.zpracujPrikaz("vezmi mec");
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi louc");
        louc = hra1.getHerniPlan().getBatoh().odeberVec("louc");
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("louc"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
        hra1.zpracujPrikaz("jdi s_postavou");
        assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej louc postava");
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("louc"));
        lektvar = hra1.getHerniPlan().getBatoh().odeberVec("lektvar");
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("pouzij lektvar");
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.getHerniPlan().getBatoh().pridejVec(lektvar);
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("pouzij lektvar");
        assertEquals(null, hra1.getHerniPlan().getBatoh().odeberVec("lektvar"));
        hra1.zpracujPrikaz ("vezmi mec");
        mec = hra1.getHerniPlan().getBatoh().odeberVec("mec");
        hra1.getHerniPlan().getBatoh().pridejVec(mec);
        assertNotNull(hra1.getHerniPlan().getBatoh().odeberVec("mec"));
        assertEquals(null, hra1.getHerniPlan().getAktualniProstor().odeberVec("mec"));
        hra1.getHerniPlan().getBatoh().pridejVec(louc);
		hra1.zpracujPrikaz("jdi s_detektorem");
		assertEquals("s_detektorem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("jdi s_hlavici");
		assertEquals(true, hra1.konecHry());

		hra1 = new Hra();
		assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
		hra1.zpracujPrikaz("pouzij plamenomet");
		assertEquals(true, hra1.konecHry());

		hra1 = new Hra();
		assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals("veci: louc mec", hra1.getHerniPlan().getAktualniProstor().popisVeci());
		hra1.zpracujPrikaz("jdi s_kamenem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_kamenem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("vezmi kamen");
		assertEquals(true, hra1.konecHry());

		hra1 = new Hra();
		assertEquals("s_postavou", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("postavy: postava", hra1.getHerniPlan().getAktualniProstor().popisPostav());
		assertEquals("vychody: s_mecem", hra1.getHerniPlan().getAktualniProstor().popisVychodu());
        hra1.zpracujPrikaz("jdi s_mecem");
        assertEquals(false, hra1.konecHry());
        assertEquals("s_mecem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		assertEquals("veci: louc mec", hra1.getHerniPlan().getAktualniProstor().popisVeci());
		hra1.zpracujPrikaz("jdi s_kamenem");
		assertEquals(false, hra1.konecHry());
		assertEquals("s_kamenem", hra1.getHerniPlan().getAktualniProstor().getNazev());
		hra1.zpracujPrikaz("pouzij kamen");
		assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("pouzij kamen");
		assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("pouzij kamen");
		assertEquals(false, hra1.konecHry());
		hra1.zpracujPrikaz("pouzij kamen");
		assertEquals(true, hra1.konecHry());
        /*
        assertEquals("hluboký_les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
        */
    }
}
