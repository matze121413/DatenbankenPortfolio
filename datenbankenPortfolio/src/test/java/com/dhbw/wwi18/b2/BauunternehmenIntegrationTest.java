package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bautechnik;
import com.dhbw.wwi18.b2.model.Bauunternehmen;
import com.dhbw.wwi18.b2.repositories.BautechnikRepository;
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

    private static BauunternehmenRepository bauunternehmenRepository;

    @BeforeAll
    public static void setup() {
        bauunternehmenRepository = new BauunternehmenRepository();
    }

    @AfterAll
    public static void afterAll() {
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
        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.findById(bauunternehmen.getBauunternehmenId());
        assertThat(savedBauunternehmen, is(bauunternehmen));
    }

    @Test
    public void updateBauunternehmen(){
        Bauunternehmen bauunternehmen = createNewBauunternehmen();
        bauunternehmen.setName("Neuer Name");
        Bauunternehmen updatedBauunternehmen = bauunternehmenRepository.updateWithMerge(bauunternehmen);

        assertThat(updatedBauunternehmen, is(bauunternehmen));
    }

    @Test
    public void deleteBauunternehmen(){
        Bauunternehmen bauunternehmen = createNewBauunternehmen();
        bauunternehmenRepository.deleteEntity(bauunternehmen);

        Bauunternehmen deletedBauunternehmen = bauunternehmenRepository.findById(bauunternehmen.getBauunternehmenId());
        assertNull(deletedBauunternehmen);

    }

    private Bauunternehmen createNewBauunternehmen(){
        Bauunternehmen bauunternehmen = new Bauunternehmen();
        bauunternehmen.setName("BobDerMeister");
        bauunternehmen.setTelefonnummer("1234");
        bauunternehmen.setStrasse("Weg");
        bauunternehmen.setOrt("Karlsruhe");
        bauunternehmen.setPlz("53604");

        Bauunternehmen savedBauunternehmen = bauunternehmenRepository.createEntity(bauunternehmen);

        assertNotNull(savedBauunternehmen.getBauunternehmenId());
        assertThat(bauunternehmen.getName(), is("könnenwirdasschaffen"));
        return savedBauunternehmen;
    }

}

