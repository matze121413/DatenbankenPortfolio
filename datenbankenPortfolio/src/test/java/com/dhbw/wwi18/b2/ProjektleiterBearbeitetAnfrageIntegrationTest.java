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

    private static MitarbeiterRepository mitarbeiterRepository;
    private static AnfrageRepository anfrageRepository;

    @BeforeAll
    public static void setup() {
        mitarbeiterRepository = new MitarbeiterRepository();
        anfrageRepository = new AnfrageRepository();
    }

    @AfterAll
    public static void done() {
        mitarbeiterRepository.closeConnection();
        anfrageRepository.closeConnection();
    }

    @Test
    public void setMitarbeiterInAnfrage() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();

        Anfrage anfrage1 = createNewAnfrage();
        Anfrage anfrage2 = createNewAnfrage();

        anfrage1.setMitarbeiter(mitarbeiter);
        anfrage2.setMitarbeiter(mitarbeiter);

        Anfrage savedAnfrage1 = anfrageRepository.updateWithMerge(anfrage1);
        Anfrage savedAnfrage2 = anfrageRepository.updateWithMerge(anfrage2);

        assertThat(savedAnfrage1.getMitarbeiter(), is(mitarbeiter));
        assertThat(savedAnfrage2.getMitarbeiter(), is(mitarbeiter));
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

    private Anfrage createNewAnfrage(){
        Anfrage anfrage = new Anfrage();
        anfrage.setAnzRaueme(4);
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
