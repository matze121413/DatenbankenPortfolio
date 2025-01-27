package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Ingenieur;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Skizze;
import com.dhbw.wwi18.b2.repositories.IngenieurRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.SkizzeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngenieurPrueftSkizzeIntegrationTest {

    private IngenieurRepository ingenieurRepository;
    private SkizzeRepository skizzeRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        ingenieurRepository = new IngenieurRepository();
        skizzeRepository = new SkizzeRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
        ingenieurRepository.closeConnection();
        skizzeRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setIngenieurInSkizze() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Ingenieur ingenieur = createNewIngenieur(mitarbeiter);

        Skizze skizze1 = createNewSkizze();
        Skizze skizze2 = createNewSkizze();

        skizze1.setIngenieur(ingenieur);
        skizze2.setIngenieur(ingenieur);

        Skizze savedSkizze1 = skizzeRepository.update(skizze1);
        Skizze savedSkizze2 = skizzeRepository.update(skizze2);

        assertThat(savedSkizze1.getIngenieur(), is(ingenieur));
        assertThat(savedSkizze2.getIngenieur(), is(ingenieur));
    }

    private Ingenieur createNewIngenieur(Mitarbeiter savedMitarbeiter) {
        Ingenieur ingenieur = new Ingenieur();
        ingenieur.setSelbststaendig(false);
        ingenieur.setStrasse("im Sand");
        ingenieur.setOrt("Rheinbreitbach");
        ingenieur.setPlz("56846");
        ingenieur.setMitarbeiter(savedMitarbeiter);
        ingenieur.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        return ingenieurRepository.save(ingenieur);
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

}
