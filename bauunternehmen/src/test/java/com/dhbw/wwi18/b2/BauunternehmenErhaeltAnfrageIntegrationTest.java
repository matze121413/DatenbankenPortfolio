package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Anfrage;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.AnfrageRepository;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauunternehmenErhaeltAnfrageIntegrationTest {

    private BauunternehmenRepository bauunternehmenRepository;
    private AnfrageRepository anfrageRepository;

    @BeforeAll
    public void setup() {
        bauunternehmenRepository = new BauunternehmenRepository();
        anfrageRepository = new AnfrageRepository();
    }

    @AfterAll
    public void done() {
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

        Anfrage savedAnfrage1 = anfrageRepository.update(anfrage1);
        Anfrage savedAnfrage2 = anfrageRepository.update(anfrage2);

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

        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.save(bauunternehmen);

        assertNotNull(savedBauunternehmen.getUnternehmenId());
        assertThat(bauunternehmen.getName(), is("BobDerMeister"));
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

        Anfrage savedAnfrage = anfrageRepository.save(anfrage);

        assertNotNull(savedAnfrage.getAnfrageId());
        assertThat(anfrage.getStrasse(), is("Binger"));
        return savedAnfrage;
    }
}
