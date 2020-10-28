package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SachbearbeiterStelltAusRechnungIntegrationTest {

    private static MitarbeiterRepository mitarbeiterRepository;
    private static RechnungRepository rechnungRepository;

    @BeforeAll
    public static void setup() {
        mitarbeiterRepository = new MitarbeiterRepository();
        rechnungRepository = new RechnungRepository();
    }

    @AfterAll
    public static void done() {
        mitarbeiterRepository.closeConnection();
        rechnungRepository.closeConnection();
    }

    @Test
    public void setMitarbeiterInRechnung() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();

        Rechnung rechnung1 = createNewRechnung();
        Rechnung rechnung2 = createNewRechnung();

        rechnung1.setMitarbeiter(mitarbeiter);
        rechnung2.setMitarbeiter(mitarbeiter);

        Rechnung savedRechnung1 = rechnungRepository.updateWithMerge(rechnung1);
        Rechnung savedRechnung2 = rechnungRepository.updateWithMerge(rechnung2);

        assertThat(savedRechnung1.getMitarbeiter(), is(mitarbeiter));
        assertThat(savedRechnung2.getMitarbeiter(), is(mitarbeiter));
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

    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        mitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        //stichprobenartig Felder testen, da davon ausgegangen werden kann, dass Erstellung damit funktioniert hat
        assertThat(mitarbeiter.getVorname(), is("Horst"));
        assertThat(mitarbeiter.getGehalt(), is(3000));
        //Die MitarbeiterId sollte nicht statische erzeugt werden, kann aber nie null sein
        assertNotNull(mitarbeiter.getMitarbeiterId());

        return mitarbeiter;
    }
}
