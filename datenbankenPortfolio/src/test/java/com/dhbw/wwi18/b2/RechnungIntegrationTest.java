package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RechnungIntegrationTest {

    private static RechnungRepository rechnungRepository;

    @BeforeAll
    public static void setup() {rechnungRepository = new RechnungRepository();}

    @AfterAll
    public static void afterAll() {
        rechnungRepository.closeConnection();
    }

    @Test
    public void createRechnung(){
        createNewRechnung();
    }

    @Test
    public void getRechnung(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Rechnung rechnung = createNewRechnung();
        Rechnung savedRechnung = rechnungRepository.findById(rechnung.getRechnungId());
        assertThat(savedRechnung, is(rechnung));
    }

    @Test
    public void updateRechnung(){
        Rechnung rechnung = createNewRechnung();
        rechnung.setPreis(5000000);
        Rechnung updatedRechnung = rechnungRepository.updateWithMerge(rechnung);

        assertThat(updatedRechnung, is(rechnung));
    }

    @Test
    public void deleteRechnung(){
        Rechnung rechnung = createNewRechnung();
        rechnungRepository.deleteEntity(rechnung);

        Rechnung deletedRechnung = rechnungRepository.findById(rechnung.getRechnungId());
        assertNull(deletedRechnung);

    }

    private Rechnung createNewRechnung(){
        Rechnung rechnung = new Rechnung();
        rechnung.setPreis(2000000);
        rechnung.setZahlungsart("Paypal");
        rechnung.setStatus("In Bearbeitung");
        rechnung.setFrist(16101996);

        Rechnung savedRechnung = rechnungRepository.createEntity(rechnung);

        assertNotNull(savedRechnung.getRechnungId());
        assertThat(rechnung.getPreis(), is(2000000));
        return savedRechnung;
    }
}
