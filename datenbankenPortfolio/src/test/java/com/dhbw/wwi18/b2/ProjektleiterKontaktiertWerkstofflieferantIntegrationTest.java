package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Projektleiter;
import com.dhbw.wwi18.b2.model.Werkstofflieferant;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.ProjektleiterRepository;
import com.dhbw.wwi18.b2.repositories.WerkstofflieferantRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjektleiterKontaktiertWerkstofflieferantIntegrationTest {

    private ProjektleiterRepository projektleiterRepository;
    private WerkstofflieferantRepository werkstofflieferantRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        werkstofflieferantRepository = new WerkstofflieferantRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void done() {
        projektleiterRepository.closeConnection();
        werkstofflieferantRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setEntsorgungsunternehmenInBauschutt() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);

        Werkstofflieferant werkstofflieferant1 = createNewWerkstofflieferant();
        Werkstofflieferant werkstofflieferant2 = createNewWerkstofflieferant();

        werkstofflieferant1.setProjektleiter(projektleiter);
        werkstofflieferant2.setProjektleiter(projektleiter);

        Werkstofflieferant savedWerkstofflieferant1 = werkstofflieferantRepository.updateWithMerge(werkstofflieferant1);
        Werkstofflieferant savedWerkstofflieferant2 = werkstofflieferantRepository.updateWithMerge(werkstofflieferant2);

        assertThat(savedWerkstofflieferant1.getProjektleiter(), is(projektleiter));
        assertThat(savedWerkstofflieferant2.getProjektleiter(), is(projektleiter));
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

    private Projektleiter createNewProjektleiter(Mitarbeiter savedMitarbeiter) {
        Projektleiter projektleiter = new Projektleiter();
        projektleiter.setAktProjektanzahl(1);
        projektleiter.setGesamtProjektanzahl(42);
        projektleiter.setMitarbeiter(savedMitarbeiter);
        projektleiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        return projektleiterRepository.createEntity(projektleiter);
    }

    private Werkstofflieferant createNewWerkstofflieferant() {
        Werkstofflieferant werkstofflieferant = new Werkstofflieferant();
        werkstofflieferant.setName("Eichsfeld Holz GmbH");
        werkstofflieferant.setErfahrung(62);
        werkstofflieferant.setOrt("Leinefelde");
        werkstofflieferant.setPlz("37327");
        werkstofflieferant.setStrasse("Stammweg");
        werkstofflieferant.setTelefonnummer("0360555970");

        return werkstofflieferantRepository.createEntity(werkstofflieferant);
    }

}
