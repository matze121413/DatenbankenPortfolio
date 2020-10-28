package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.*;
import com.dhbw.wwi18.b2.repositories.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dhbw.wwi18.b2.model.Anfrage;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.AnfrageRepository;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ProjektleiterBearbeitetAnfrageIntegrationTest {

    private static ProjektleiterRepository projektleiterRepository;
    private static AnfrageRepository anfrageRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public static void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        anfrageRepository = new AnfrageRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public static void done() {
        projektleiterRepository.closeConnection();
        anfrageRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setProjektleiterInAnfrage() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);

        Anfrage anfrage1 = createNewAnfrage();
        Anfrage anfrage2 = createNewAnfrage();

        anfrage1.setProjektleiter(projektleiter);
        anfrage2.setProjektleiter(projektleiter);

        Anfrage savedAnfrage1 = anfrageRepository.updateWithMerge(anfrage1);
        Anfrage savedAnfrage2 = anfrageRepository.updateWithMerge(anfrage2);

        assertThat(savedAnfrage1.getProjektleiter(), is(projektleiter));
        assertThat(savedAnfrage2.getProjektleiter(), is(projektleiter));
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

    private Anfrage createNewAnfrage(){
        Anfrage anfrage = new Anfrage();
        anfrage.setAnzRaeume(4);
        anfrage.setStrasse("Binger");
        anfrage.setOrt("Mühlheim");
        anfrage.setPlz("76185");
        anfrage.setFlaeche(53);
        anfrage.setFarbe("rot");
        anfrage.setPreisvorstellung(500000);

        Anfrage savedAnfrage = anfrageRepository.createEntity(anfrage);

        assertNotNull(savedAnfrage.getAnfrageId());
        assertThat(anfrage.getStrasse(), is("Schofer"));
        return savedAnfrage;
    }
}
