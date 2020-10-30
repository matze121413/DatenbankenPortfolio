package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Rechnung;
import com.dhbw.wwi18.b2.model.Sachbearbeiter;
import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.RechnungRepository;
import com.dhbw.wwi18.b2.repositories.SachbearbeiterRepository;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SachbearbeiterStelltAusRechnungIntegrationTest {

    private SachbearbeiterRepository sachbearbeiterRepository;
    private RechnungRepository rechnungRepository;
    private MitarbeiterRepository mitarbeiterRepository;
    private VertragRepository vertragRepository;

    @BeforeAll
    public void setup() {
        sachbearbeiterRepository = new SachbearbeiterRepository();
        rechnungRepository = new RechnungRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
        vertragRepository = new VertragRepository();
    }


    @AfterAll
    public void done() {
        sachbearbeiterRepository.closeConnection();
        rechnungRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
        vertragRepository.closeConnection();
    }

    @Test
    public void setEntsorgungsunternehmenInBauschutt() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Sachbearbeiter sachbearbeiter = createNewSachbearbeiter(mitarbeiter);

        Rechnung rechnung1 = createNewRechnung();
        Rechnung rechnung2 = createNewRechnung();

        rechnung1.setSachbearbeiter(sachbearbeiter);
        rechnung2.setSachbearbeiter(sachbearbeiter);

        Rechnung savedRechnung1 = rechnungRepository.updateWithMerge(rechnung1);
        Rechnung savedRechnung2 = rechnungRepository.updateWithMerge(rechnung2);

        assertThat(savedRechnung1.getSachbearbeiter(), is(sachbearbeiter));
        assertThat(savedRechnung2.getSachbearbeiter(), is(sachbearbeiter));
    }

    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return mitarbeiterRepository.createEntity(mitarbeiter);
    }

    private Rechnung createNewRechnung(){
        Rechnung rechnung = new Rechnung();
        rechnung.setPreis(2000000);
        rechnung.setZahlungsart("Paypal");
        rechnung.setStatus("In Bearbeitung");
        rechnung.setFrist(16101996);
        rechnung.setVertrag(createNewVertrag());

        Rechnung savedRechnung = rechnungRepository.createEntity(rechnung);

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

        return vertragRepository.createEntity(vertrag);
    }

    private Sachbearbeiter createNewSachbearbeiter(Mitarbeiter savedMitarbeiter) {
        Sachbearbeiter sachbearbeiter = new Sachbearbeiter();
        sachbearbeiter.setTarif(5);
        sachbearbeiter.setMitarbeiter(savedMitarbeiter);
        sachbearbeiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        sachbearbeiter = sachbearbeiterRepository.createEntity(sachbearbeiter);

        assertThat(sachbearbeiter.getTarif(), is(5));
        assertThat(sachbearbeiter.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return sachbearbeiter;
    }
}
