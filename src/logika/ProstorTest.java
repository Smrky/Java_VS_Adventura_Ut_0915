package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Ondřej Smrček
 * @version   ZS 2016/2017
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public void testLzeProjit() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", "", false);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", "", false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
    @Test
    public void testVeci()
    {
        logika.Prostor prostor1 = new logika.Prostor(null, null, null, false);
        logika.Vec vec1 = new logika.Vec("a", "popis a", true, true);
        logika.Vec vec2 = new logika.Vec("b", "popis b", false, true);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("c"));
    }

	@Test
	public void testPostav()
	{
		logika.Prostor prostor1 = new logika.Prostor(null, null, null, false);
		logika.Postava postava1 = new logika.Postava("a", "popis a", "ahoj");
        logika.Postava postava2 = new logika.Postava("b", "popis b", "čau");
        prostor1.vlozPostavu(postava1);
        prostor1.vlozPostavu(postava2);
        assertSame(postava1, prostor1.odeberPostavu("a"));
        assertNull(prostor1.odeberPostavu("c"));
	}


}
