package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Immobilie;
import com.dhbw.wwi18.b2.model.Ingenieur;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.ImmobilieRepository;
import com.dhbw.wwi18.b2.repositories.IngenieurRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngenieurGibtFreiImmobilieIntegrationTest {


    private static IngenieurRepository ingenieurRepository;
    private static ImmobilieRepository immobilieRepository;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public static void setup() {
        ingenieurRepository = new IngenieurRepository();
        immobilieRepository = new ImmobilieRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public static void done() {
        ingenieurRepository.closeConnection();
        immobilieRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setIngenieurInImmobilie() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Ingenieur ingenieur = createNewIngenieur(mitarbeiter);

        Immobilie immobilie1 = createNewImmobilie();
        Immobilie immobilie2 = createNewImmobilie();

        immobilie1.setIngenieur(ingenieur);
        immobilie2.setIngenieur(ingenieur);

        Immobilie savedImmobilie1 = immobilieRepository.updateWithMerge(immobilie1);
        Immobilie savedImmobilie2 = immobilieRepository.updateWithMerge(immobilie2);

        assertThat(savedImmobilie1.getIngenieur(), is(ingenieur));
        assertThat(savedImmobilie2.getIngenieur(), is(ingenieur));
    }

    private Ingenieur createNewIngenieur(Mitarbeiter savedMitarbeiter) {
        Ingenieur ingenieur = new Ingenieur();
        ingenieur.setSelbständig(false);
        ingenieur.setStrasse("im Sand");
        ingenieur.setOrt("Rheinbreitbach");
        ingenieur.setPlz("56846");
        ingenieur.setMitarbeiter(savedMitarbeiter);
        ingenieur.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        ingenieur = ingenieurRepository.createEntity(ingenieur);

        assertThat(ingenieur.getStrasse(), is("im Sand"));
        assertThat(ingenieur.getMitarbeiterId(), is(savedMitarbeiter.getMitarbeiterId()));

        return ingenieur;
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

    private Immobilie createNewImmobilie(){
        Immobilie immobilie = new Immobilie();
        immobilie.setFarbe("gelb");
        immobilie.setStatus("fertig");
        immobilie.setFlaeche(150);

        Immobilie savedImmobilie = immobilieRepository.createEntity(immobilie);

        assertNotNull(savedImmobilie.getImmobilieId());
        assertThat(immobilie.getFarbe(), is("gelb"));
        return savedImmobilie;
    }


}
