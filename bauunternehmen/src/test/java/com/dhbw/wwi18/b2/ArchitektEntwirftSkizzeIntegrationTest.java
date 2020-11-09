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

    private ArchitektRepository architektRepository;
    private SkizzeRepository skizzeRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        architektRepository = new ArchitektRepository();
        skizzeRepository = new SkizzeRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
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

        Skizze savedSkizze1 = skizzeRepository.update(skizze1);
        Skizze savedSkizze2 = skizzeRepository.update(skizze2);

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

        return mitarbeiterRepository.save(mitarbeiter);
    }

    private Skizze createNewSkizze(){
        Skizze skizze = new Skizze();
        skizze.setDetailgrad("sehr hoch");
        skizze.setFlaeche(500);
        skizze.setRaum("Raum");

        return skizzeRepository.save(skizze);
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

        return architektRepository.save(architekt);
    }
}
