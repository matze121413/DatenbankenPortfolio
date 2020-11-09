package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauschutt;
import com.dhbw.wwi18.b2.model.Entsorgungsunternehmen;
import com.dhbw.wwi18.b2.repositories.BauschuttRepository;
import com.dhbw.wwi18.b2.repositories.EntsorgungsunternehmenRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntsorgungsunternehmenEntsorgtBauschuttIntegrationTest {
    private EntsorgungsunternehmenRepository entsorgungsunternehmenRepository;
    private BauschuttRepository bauschuttRepository;

    @BeforeAll
    public void setup() {
        entsorgungsunternehmenRepository = new EntsorgungsunternehmenRepository();
        bauschuttRepository = new BauschuttRepository();
    }


    @AfterAll
    public void done() {
        entsorgungsunternehmenRepository.closeConnection();
        bauschuttRepository.closeConnection();
    }

    @Test
    public void setEntsorgungsunternehmenInBauschutt() {
        Entsorgungsunternehmen entsorgungsunternehmen = createNewEntsorgungsunternehmen();

        Bauschutt bauschutt1 = createNewBauschutt();
        Bauschutt bauschutt2 = createNewBauschutt();

        bauschutt1.setEntsorgungsunternehmen(entsorgungsunternehmen);
        bauschutt2.setEntsorgungsunternehmen(entsorgungsunternehmen);

        Bauschutt savedBauschutt1 = bauschuttRepository.update(bauschutt1);
        Bauschutt savedBauschutt2 = bauschuttRepository.update(bauschutt2);

        assertThat(savedBauschutt1.getEntsorgungsunternehmen(), is(entsorgungsunternehmen));
        assertThat(savedBauschutt2.getEntsorgungsunternehmen(), is(entsorgungsunternehmen));
    }


    private Entsorgungsunternehmen createNewEntsorgungsunternehmen(){
        Entsorgungsunternehmen entsorgungsunternehmen = new Entsorgungsunternehmen();
        entsorgungsunternehmen.setAbholtag(4);
        entsorgungsunternehmen.setAbholzeit("15:00");
        entsorgungsunternehmen.setErfahrung(69);
        entsorgungsunternehmen.setName("AVR");
        entsorgungsunternehmen.setTelefonnummer("07261/931-0");

        return entsorgungsunternehmenRepository.save(entsorgungsunternehmen);
    }

    private Bauschutt createNewBauschutt(){
        Bauschutt bauschutt = new Bauschutt();
        bauschutt.setArt("Ziegel");
        bauschutt.setGewicht(200);
        bauschutt.setKilopreis(3);
        bauschutt.setMenge(7);

        return bauschuttRepository.save(bauschutt);
    }

}
