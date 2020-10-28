package com.dhbw.wwi18.b2;

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
public class BauunternehmenErhaeltAnfrageIntegrationTest {

    private static BauunternehmenRepository bauunternehmenRepository;
    private static AnfrageRepository anfrageRepository;

    @BeforeAll
    public static void setup() {
        bauunternehmenRepository = new BauunternehmenRepository();
        anfrageRepository = new AnfrageRepository();
    }

    @AfterAll
    public static void done() {
        bauunternehmenRepository.closeConnection();
        anfrageRepository.closeConnection();
    }

    @Test
    public void setBauunternehmenInAnfrage() {
        Bauunternehmen bauunternehmen = createNewBauunternehmen();

        Anfrage anfrage1 = createNewAnfrage();
        Anfrage anfrage2 = createNewAnfrage();

        anfrage1.setBauunternehmen(bauunternehmen);
        anfrage2.setBauunternehmen(bauunternehmen);

        Anfrage savedAnfrage1 = anfrageRepository.updateWithMerge(anfrage1);
        Anfrage savedAnfrage2 = anfrageRepository.updateWithMerge(anfrage2);

        assertThat(savedAnfrage1.getBauunternehmen(), is(bauunternehmen));
        assertThat(savedAnfrage2.getBauunternehmen(), is(bauunternehmen));
    }

    private Bauunternehmen createNewBauunternehmen(){
        Bauunternehmen bauunternehmen = new Bauunternehmen();
        bauunternehmen.setName("BobDerMeister");
        bauunternehmen.setTelefonnummer("1234");
        bauunternehmen.setStrasse("Weg");
        bauunternehmen.setOrt("Kaiserslautern");
        bauunternehmen.setPlz("53604");

        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.createEntity(bauunternehmen);

        assertNotNull(savedBauunternehmen.getUnternehmenId());
        assertThat(bauunternehmen.getName(), is("könnenwirdasschaffen"));
        return savedBauunternehmen;
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
