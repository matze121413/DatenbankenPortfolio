package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Architekt;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Skizze;
import com.dhbw.wwi18.b2.repositories.ArchitektRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.SkizzeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArchitektEntwirftSkizzeIntegrationTest {

    private static ArchitektRepository architektRepository;
    private static SkizzeRepository skizzeRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public static void setup() {
        architektRepository = new ArchitektRepository();
        skizzeRepository = new SkizzeRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public static void done() {
        architektRepository.closeConnection();
        skizzeRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setArchitektInSkizze() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Architekt architekt = createNewArchitekt(mitarbeiter);

        Skizze skizze1 = createNewSkizze();
        Skizze skizze2 = createNewSkizze();

        skizze1.setArchitekt(architekt);
        skizze2.setArchitekt(architekt);

        Skizze savedSkizze1 = skizzeRepository.updateWithMerge(skizze1);
        Skizze savedSkizze2 = skizzeRepository.updateWithMerge(skizze2);

        assertThat(savedSkizze1.getArchitekt(), is(architekt));
        assertThat(savedSkizze2.getArchitekt(), is(architekt));
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

    private Skizze createNewSkizze(){
        Skizze skizze = new Skizze();
        skizze.setDetailgrad("sehr hoch");
        skizze.setFlaeche(500);
        skizze.setRaum("Raum");

        Skizze savedSkizze = skizzeRepository.createEntity(skizze);

        assertNotNull(savedSkizze.getSkizzeId());
        assertThat(skizze.getDetailgrad(), is("sehr hoch"));
        return savedSkizze;
    }

    private Architekt createNewArchitekt(Mitarbeiter savedMitarbeiter){
        Architekt architekt= new Architekt();
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
