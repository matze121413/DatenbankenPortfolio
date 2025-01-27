package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Entsorgungsunternehmen;
import com.dhbw.wwi18.b2.repositories.EntsorgungsunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntsorgungsunternehmenIntegrationTest {
    private EntsorgungsunternehmenRepository entsorgungsunternehmenRepository;

    @BeforeAll
    public void setup() {
        entsorgungsunternehmenRepository = new EntsorgungsunternehmenRepository();
    }


    @AfterAll
    public void afterAll() {
        entsorgungsunternehmenRepository.closeConnection();
    }

    @Test
    public void createEntsorgungsunternehmen(){
        createNewEntsorgungsunternehmen();
    }

    @Test
    public void getEntsorgungsunternehmen(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Entsorgungsunternehmen entsorgungsunternehmen = createNewEntsorgungsunternehmen();
        Entsorgungsunternehmen savedEntsorgungsunternehmen =
                entsorgungsunternehmenRepository.findById(entsorgungsunternehmen.getEntsorgungId());
        assertThat(savedEntsorgungsunternehmen, is(entsorgungsunternehmen));
    }

    @Test
    public void updateEntsorgungsunternehmen(){
        Entsorgungsunternehmen entsorgungsunternehmen = createNewEntsorgungsunternehmen();
        entsorgungsunternehmen.setAbholzeit("13:00");
        Entsorgungsunternehmen updatedEntsorgungsunternehmen =
                entsorgungsunternehmenRepository.update(entsorgungsunternehmen);

        assertThat(updatedEntsorgungsunternehmen, is(entsorgungsunternehmen));
    }

    @Test
    public void deleteEntsorgungsunternehmen(){
        Entsorgungsunternehmen entsorgungsunternehmen = createNewEntsorgungsunternehmen();
        entsorgungsunternehmenRepository.delete(entsorgungsunternehmen);

        Entsorgungsunternehmen deletedEntsorgungsunternehmen =
                entsorgungsunternehmenRepository.findById(entsorgungsunternehmen.getEntsorgungId());

        assertNull(deletedEntsorgungsunternehmen);
    }


    private Entsorgungsunternehmen createNewEntsorgungsunternehmen(){
        Entsorgungsunternehmen entsorgungsunternehmen = new Entsorgungsunternehmen();
        entsorgungsunternehmen.setAbholtag(4);
        entsorgungsunternehmen.setAbholzeit("15:00");
        entsorgungsunternehmen.setErfahrung(69);
        entsorgungsunternehmen.setName("AVR");
        entsorgungsunternehmen.setTelefonnummer("07261/931-0");

        Entsorgungsunternehmen savedEntsorgungsunternehmen =
                entsorgungsunternehmenRepository.save(entsorgungsunternehmen);
        assertNotNull(savedEntsorgungsunternehmen.getEntsorgungId());
        assertThat(savedEntsorgungsunternehmen.getName(), is("AVR"));
        return savedEntsorgungsunternehmen;
    }
}
