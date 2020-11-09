package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Projektleiter;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.ProjektleiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjektleiterErstelltVertragIntegrationTest {


    private ProjektleiterRepository projektleiterRepository;
    private VertragRepository vertragRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        vertragRepository = new VertragRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
        projektleiterRepository.closeConnection();
        vertragRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setProjektleiterInVertrag() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);

        Vertrag vertrag1 = createNewVertrag();
        Vertrag vertrag2 = createNewVertrag();

        vertrag1.setProjektleiter(projektleiter);
        vertrag2.setProjektleiter(projektleiter);

        Vertrag savedVertrag1 = vertragRepository.update(vertrag1);
        Vertrag savedVertrag2 = vertragRepository.update(vertrag2);

        assertThat(savedVertrag1.getProjektleiter(), is(projektleiter));
        assertThat(savedVertrag2.getProjektleiter(), is(projektleiter));
    }


    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return mitarbeiterRepository.save(mitarbeiter);
    }

    private Projektleiter createNewProjektleiter(Mitarbeiter savedMitarbeiter) {
        Projektleiter projektleiter = new Projektleiter();
        projektleiter.setAktProjektanzahl(1);
        projektleiter.setGesamtProjektanzahl(42);
        projektleiter.setMitarbeiter(savedMitarbeiter);
        projektleiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        return projektleiterRepository.save(projektleiter);
    }

    private Vertrag createNewVertrag(){
        Vertrag vertrag = new Vertrag();
        vertrag.setPreis(200000);
        vertrag.setUnterschrift(true);
        vertrag.setLaufzeit(30);
        vertrag.setGegenstand("Haus");

        return vertragRepository.save(vertrag);
    }
}
