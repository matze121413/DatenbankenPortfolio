package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
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

    private RechnungRepository rechnungRepository;
    private VertragRepository vertragRepository;

    @BeforeAll
    public void setup() {
        rechnungRepository = new RechnungRepository();
        vertragRepository = new VertragRepository();
    }

    @AfterAll
    public void afterAll() {
        rechnungRepository.closeConnection();
        vertragRepository.closeConnection();
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
        Rechnung updatedRechnung = rechnungRepository.update(rechnung);

        assertThat(updatedRechnung, is(rechnung));
    }

    @Test
    public void deleteRechnung(){
        Rechnung rechnung = createNewRechnung();
        rechnungRepository.delete(rechnung);

        Rechnung deletedRechnung = rechnungRepository.findById(rechnung.getRechnungId());
        assertNull(deletedRechnung);

    }

    private Rechnung createNewRechnung(){
        Rechnung rechnung = new Rechnung();
        rechnung.setPreis(2000000);
        rechnung.setZahlungsart("Paypal");
        rechnung.setStatus("In Bearbeitung");
        rechnung.setFrist(16101996);
        rechnung.setVertrag(createNewVertrag());

        Rechnung savedRechnung = rechnungRepository.save(rechnung);

        assertNotNull(savedRechnung.getRechnungId());
        assertThat(rechnung.getPreis(), is(2000000));
        return savedRechnung;
    }

    private Vertrag createNewVertrag() {
        Vertrag vertrag = new Vertrag();
        vertrag.setPreis(200000);
        vertrag.setUnterschrift(true);
        vertrag.setLaufzeit(30);
        vertrag.setGegenstand("Haus");

        return vertragRepository.save(vertrag);
    }
}
