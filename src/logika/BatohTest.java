/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class BatohTest
{
    private Batoh batoh;
    private Vec a;
    private Vec b;
    private Vec c;
    private Vec d;
    private Vec e;
    private Vec f;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        batoh = new Batoh();
        a = new Vec("a", "popis a", true, true);
        b = new Vec("b", "popis b", true, true);
        c = new Vec("c", "popis c", true, true);
        d = new Vec("d", "popis d", true, true);
        e = new Vec("e", "popis e", true, true);
        f = new Vec("f", "popis f", true, true);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testPridejVec()
    {
        assertEquals(true, batoh.pridejVec(a));
        assertEquals(true, batoh.pridejVec(b));
        assertEquals(true, batoh.pridejVec(c));
        assertEquals(true, batoh.pridejVec(d));
        assertEquals(true, batoh.pridejVec(e));
        assertEquals(false, batoh.pridejVec(f));
    }
    
    @Test
    public void testOdeberVec()
    {
        assertEquals(null, batoh.odeberVec("a"));
        batoh.pridejVec(a);
        assertEquals(a, batoh.odeberVec("a"));
    }
    
    @Test
    public void testObsahBatohu()
    {
        assertEquals("batoh:", batoh.obsahBatohu());
        batoh.pridejVec(a);
        assertEquals("batoh: a", batoh.obsahBatohu());
    }
    
    @Test
    public void testJePlny()
    {
        assertEquals(false, batoh.jePlny());
        batoh.pridejVec(a);
        batoh.pridejVec(b);
        batoh.pridejVec(c);
        batoh.pridejVec(d);
        batoh.pridejVec(e);
        assertEquals(true, batoh.jePlny());
    }
    
    @Test
    public void testJePrazdny()
    {
        assertEquals(true, batoh.jePrazdny());
        batoh.pridejVec(a);
        assertEquals(false, batoh.jePrazdny());
        batoh.odeberVec(a.getNazev());
        assertEquals(true, batoh.jePrazdny());
    }
    
    @Test
    public void testGetSeznam()
    {
        batoh.getSeznam();
        
        assertNotNull(batoh.getSeznam());
        assertTrue(batoh.getSeznam().isEmpty());
        
        batoh.pridejVec(a);
        
        assertNotNull(batoh.getSeznam());
        assertEquals(1, batoh.getSeznam().size());
        assertTrue(batoh.getSeznam().contains(a));
    }
}
