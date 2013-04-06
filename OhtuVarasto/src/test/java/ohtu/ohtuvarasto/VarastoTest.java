package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto tilavuusNollaVarasto;
    Varasto tilavuusMiinusVarasto;
    
    Varasto varastoSaldoAnnetaanKonstruktorissa;
    
    double vertailuTarkkuus = 0.0001;
    
    

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        tilavuusNollaVarasto = new Varasto(0);
        tilavuusMiinusVarasto = new Varasto(-1);
        varastoSaldoAnnetaanKonstruktorissa = new Varasto (5.0, 4.5);
    }
    
    @Test
    public void varastonLuontiNollaTilavuudella() {
        assertEquals(0, tilavuusNollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
     @Test
    public void varastonLuontidoubleNollaTilavuudella() {
        Varasto tilavuusNollaNollaVarasto = new Varasto(0.0);
        assertEquals(0, tilavuusNollaNollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiMiinusTilavuudella() {
        
        assertEquals(0, tilavuusMiinusVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiMiinusMiinusTilavuudella() {
        Varasto tilavuusMiinusMiinusVarasto = new Varasto(-1.1);
        assertEquals(0, tilavuusMiinusMiinusVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiMiinusMiinusTilavuudellajaPositiivisellaSaldolla() {
        Varasto tilavuusMiinusMiinusVarasto = new Varasto(-1.1,8.8);
        assertEquals(0, tilavuusMiinusMiinusVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, tilavuusMiinusMiinusVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNormaaliTilavuudellajaNegatiivisellaSaldolla() {
       Varasto tilavuusPlusMiinusVarasto = new Varasto(10.5,-8.8);
        assertEquals("tilavuus ei ollut 10.5",10.5, tilavuusPlusMiinusVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals("saldo ei ollut 0",0, tilavuusPlusMiinusVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNormaaliTilavuudellajaNormaaliSaldolla() {
        Varasto normaaliVarasto = new Varasto(5.5,4);
        assertEquals(5.5, normaaliVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(4, normaaliVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiSamallaTilavuudellaJaSaldolla() {
        Varasto normaaliVarasto = new Varasto(5.5,5.5);
        assertEquals(5.5, normaaliVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5.5, normaaliVarasto.getSaldo(), vertailuTarkkuus);
    }
    

    
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        //rikotaan tuo testi vaihtamalla 8:n tilalle 7
        //assertEquals(7, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaNollalla() {
        double expectedSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(0);

        // saldon pitäisi olla sama kuin ennen lisäystä
        
        assertEquals(expectedSaldo, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void lisaysLisaaSaldoaNegatiivinenLuku() {
        double expectedSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-2);

        // saldon ei pitäisi olla muuttunut
        assertEquals(expectedSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaEnemmanKuinMahtuu() {
        double paljonkoMahtuu = varasto.paljonkoMahtuu();
        varasto.lisaaVarastoon(paljonkoMahtuu + 2);

        // saldon pitäisi olla sama kuin tilavuus
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiOnnistuMiinusTilavuuksiseenVarastoon() {
        tilavuusMiinusVarasto.lisaaVarastoon(8);

        // saldon pitäisi pysyä nollana
        assertEquals(0, tilavuusMiinusVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void miinuslisaysEiOnnistuMiinusTilavuuksiseenVarastoon() {
        tilavuusMiinusVarasto.lisaaVarastoon(-8);

        // saldon pitäisi pysyä nollana
        assertEquals(0, tilavuusMiinusVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiOnnistuNollaTilavuuksiseenVarastoon() {
        tilavuusNollaVarasto.lisaaVarastoon(8);

        // saldon pitäisi pysyä nollana
        assertEquals(0, tilavuusNollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void miinuslisaysEiOnnistuNollaTilavuuksiseenVarastoon() {
        tilavuusNollaVarasto.lisaaVarastoon(-8);

        // saldon pitäisi pysyä nollana
        assertEquals(0, tilavuusNollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenVarastostaNegatiivinen() {
        varasto.lisaaVarastoon(8);
        double expectedResult = varasto.getSaldo();

        varasto.otaVarastosta(-4);

        // varastoston määrän pitäisi olla sama kuin ennen ottamista
        assertEquals(expectedResult, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenVarastostaSamanVerranKuinOn() {
        double expectedResult = 0.0;
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(8);

        // varastoston määrän pitäisi olla sama kuin ennen ottamista
        assertEquals(expectedResult, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenVarastostaEnemmanKuinOn() {
        double tyhja = 0;
        varasto.lisaaVarastoon(8);
        double varastossaNyt = varasto.getSaldo();

        varasto.otaVarastosta(varastossaNyt + 6);

        // varastoston määrän pitäisi olla tyhja
        assertEquals(tyhja, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenMiinusTilavuuksisestaVarastosta() {
        double tyhja = 0;
        tilavuusMiinusVarasto.otaVarastosta(6);

        // varastoston määrän pitäisi olla tyhja
        assertEquals(tyhja, tilavuusMiinusVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanSaldoTyhjaksi() {
        double tyhja = 0;
        
        varastoSaldoAnnetaanKonstruktorissa.otaVarastosta(4.5);
        // varastoston määrän pitäisi olla tyhja
        assertEquals(tyhja, varastoSaldoAnnetaanKonstruktorissa.getSaldo(), vertailuTarkkuus);
    }
    
    
    @Test
    public void otetaanVarastostaTilavuudenVerranEliEnemmanKuinOnSaldoa() {
        double tyhja = 0;
        
        varastoSaldoAnnetaanKonstruktorissa.otaVarastosta(varastoSaldoAnnetaanKonstruktorissa.getTilavuus());
        // varastoston määrän pitäisi olla tyhja
        assertEquals(tyhja, varastoSaldoAnnetaanKonstruktorissa.getSaldo(), vertailuTarkkuus);
    }
       
    

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}