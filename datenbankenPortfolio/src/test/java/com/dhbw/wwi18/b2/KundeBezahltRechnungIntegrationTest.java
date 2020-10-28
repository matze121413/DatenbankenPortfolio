package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.model.Kunde;
import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import com.dhbw.wwi18.b2.repositories.KundeRepository;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KundeBezahltRechnungIntegrationTest {

    private static KundeRepository kundeRepository;
    private static RechnungRepository rechnungRepository;

    @BeforeAll
    public static void setup() {
        kundeRepository = new KundeRepository();
        rechnungRepository = new RechnungRepository();
    }

    @AfterAll
    public static void done() {
        kundeRepository.closeConnection();
        rechnungRepository.closeConnection();
    }

    @Test
    public void setKundeInRechnung() {
        Kunde kunde = createNewKunde();

        Rechnung rechnung1 = createNewRechnung();
        Rechnung rechnung2 = createNewRechnung();

        rechnung1.setKunde(kunde);
        rechnung2.setKunde(kunde);

        Rechnung savedRechnung1 = rechnungRepository.updateWithMerge(rechnung1);
        Rechnung savedRechnung2 = rechnungRepository.updateWithMerge(rechnung2);

        assertThat(savedRechnung1.getKunde(), is(kunde));
        assertThat(savedRechnung2.getKunde(), is(kunde));
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

    private Kunde createNewKunde(){
        Kunde kunde = new Kunde();
        kunde.setVorname("Bert");
        kunde.setNachname("Schmitz");
        kunde.setStrasse("Berliner Weg");
        kunde.setOrt("Berlin");
        kunde.setPlz("53343");

        Kunde savedKunde = kundeRepository.createEntity(kunde);

        assertNotNull(savedKunde.getKundeId());
        assertThat(kunde.getStrasse(), is("Schofer"));
        return savedKunde;
    }
}
