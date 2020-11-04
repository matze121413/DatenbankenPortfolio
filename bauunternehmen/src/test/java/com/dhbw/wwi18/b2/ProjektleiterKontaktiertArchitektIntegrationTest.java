package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Architekt;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Projektleiter;
import com.dhbw.wwi18.b2.repositories.ArchitektRepository;
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
public class ProjektleiterKontaktiertArchitektIntegrationTest {


    private ProjektleiterRepository projektleiterRepository;
    private MitarbeiterRepository mitarbeiterRepository;
    private ArchitektRepository architektRepository;

    @BeforeAll
    public void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
        architektRepository = new ArchitektRepository();
    }


    @AfterAll
    public void afterAll() {
        mitarbeiterRepository.closeConnection();
        projektleiterRepository.closeConnection();
        architektRepository.closeConnection();
    }

    @Test
    public void setProjektleiterInArchitekt() {
        Mitarbeiter archMitarbeiter = createNewMitarbeiter();
        Architekt architekt = createNewArchitekt(archMitarbeiter);

        Mitarbeiter projMitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(projMitarbeiter);

        architekt.setProjektleiter(projektleiter);

        Architekt savedArchitekt = architektRepository.updateWithMerge(architekt);
        Projektleiter savedProjektleiter = projektleiterRepository.findById(projektleiter.getMitarbeiterId());

        assertThat(savedArchitekt.getProjektleiter(), is(projektleiter));
        assertThat(savedProjektleiter.getArchitekt(), is(architekt));
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

    private Projektleiter createNewProjektleiter(Mitarbeiter savedMitarbeiter) {
        Projektleiter projektleiter = new Projektleiter();
        projektleiter.setAktProjektanzahl(1);
        projektleiter.setGesamtProjektanzahl(42);
        projektleiter.setMitarbeiter(savedMitarbeiter);
        projektleiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        projektleiter = projektleiterRepository.createEntity(projektleiter);

        assertThat(projektleiter.getGesamtProjektanzahl(), is(42));
        assertThat(projektleiter.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return projektleiter;
    }

    private Architekt createNewArchitekt(Mitarbeiter savedMitarbeiter) {
        Architekt architekt = new Architekt();
        architekt.setMitarbeiter(savedMitarbeiter);
        architekt.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());
        architekt.setSelbststaendig(true);
        architekt.setStrasse("Pfad");
        architekt.setOrt("Köln");
        architekt.setPlz("50667");
        architekt.setFachrichtung("Aussenarchitekt");
        architekt = architektRepository.createEntity(architekt);

        assertThat(architekt.getStrasse(), is("Pfad"));
        assertThat(architekt.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return architekt;
    }
}
