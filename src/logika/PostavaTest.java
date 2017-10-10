/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PostavaTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PostavaTest
{
    private Vec a;
    private Vec b;
    private Postava postava1;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        a = new Vec("a", "popis a", true, true);
        b = new Vec("b", "popis b", true, true);
        postava1 = new Postava("1", "postava 1", "ahoj");
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testVymena()
    {
        assertEquals(null, postava1.vymena(a));
        postava1.nastavVymenu(b, a, "dobře", "díky", "nechci");
        assertEquals(b, postava1.vymena(a));
        assertEquals(null, postava1.vymena(b));
    }
    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
}
