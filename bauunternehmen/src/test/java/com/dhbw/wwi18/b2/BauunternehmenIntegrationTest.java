package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.BauunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauunternehmenIntegrationTest {

    private BauunternehmenRepository bauunternehmenRepository;

    @BeforeAll
    public void setup() {
        bauunternehmenRepository = new BauunternehmenRepository();
    }

    @AfterAll
    public void afterAll() {
        bauunternehmenRepository.closeConnection();
    }

    @Test
    public void createBauunternehmen(){
        createNewBauunternehmen();
    }

    @Test
    public void getBauunternehmen(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Bauunternehmen bauunternehmen = createNewBauunternehmen();
        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.findById(bauunternehmen.getUnternehmenId());
        assertThat(savedBauunternehmen, is(bauunternehmen));
    }

    @Test
    public void updateBauunternehmen(){
        Bauunternehmen bauunternehmen = createNewBauunternehmen();
        bauunternehmen.setName("Neuer Name");
        Bauunternehmen updatedBauunternehmen = bauunternehmenRepository.update(bauunternehmen);

        assertThat(updatedBauunternehmen, is(bauunternehmen));
    }

    @Test
    public void deleteBauunternehmen(){
        Bauunternehmen bauunternehmen = createNewBauunternehmen();
        bauunternehmenRepository.delete(bauunternehmen);

        Bauunternehmen deletedBauunternehmen = bauunternehmenRepository.findById(bauunternehmen.getUnternehmenId());
        assertNull(deletedBauunternehmen);

    }

    private Bauunternehmen createNewBauunternehmen(){
        Bauunternehmen bauunternehmen = new Bauunternehmen();
        bauunternehmen.setName("BobDerMeister");
        bauunternehmen.setTelefonnummer("1234");
        bauunternehmen.setStrasse("Weg");
        bauunternehmen.setOrt("Karlsruhe");
        bauunternehmen.setPlz("53604");

        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.save(bauunternehmen);

        assertNotNull(savedBauunternehmen.getUnternehmenId());
        assertThat(bauunternehmen.getName(), is("BobDerMeister"));
        return savedBauunternehmen;
    }

}


