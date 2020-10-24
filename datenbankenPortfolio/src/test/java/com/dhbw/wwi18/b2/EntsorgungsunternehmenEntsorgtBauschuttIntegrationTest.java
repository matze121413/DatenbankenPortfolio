package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauschutt;
import com.dhbw.wwi18.b2.model.Entsorgungsunternehmen;
import com.dhbw.wwi18.b2.repositories.BauschuttRepository;
import com.dhbw.wwi18.b2.repositories.EntsorgungsunternehmenRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntsorgungsunternehmenEntsorgtBauschuttIntegrationTest {
    private static EntsorgungsunternehmenRepository entsorgungsunternehmenRepository;
    private static BauschuttRepository bauschuttRepository;

    @BeforeClass
    public static void setup() {
        entsorgungsunternehmenRepository = new EntsorgungsunternehmenRepository();
        bauschuttRepository = new BauschuttRepository();
    }



    @AfterClass
    public static void done() {
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

        Bauschutt savedBauschutt1 = bauschuttRepository.updateWithMerge(bauschutt1);
        Bauschutt savedBauschutt2 = bauschuttRepository.updateWithMerge(bauschutt2);

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

        Entsorgungsunternehmen savedEntsorgungsunternehmen =
                entsorgungsunternehmenRepository.createEntity(entsorgungsunternehmen);
        assertNotNull(savedEntsorgungsunternehmen.getEntsorgungId());
        assertThat(savedEntsorgungsunternehmen.getName(), is("AVR"));
        return savedEntsorgungsunternehmen;
    }

    private Bauschutt createNewBauschutt(){
        Bauschutt bauschutt = new Bauschutt();
        bauschutt.setArt("Ziegel");
        bauschutt.setGewicht(200);
        bauschutt.setKilopreis(3);
        bauschutt.setMenge(7);

        Bauschutt savedBauschutt = bauschuttRepository.createEntity(bauschutt);
        assertNotNull(savedBauschutt.getBauschuttId());
        assertThat(savedBauschutt.getArt(), is("Ziegel"));
        return savedBauschutt;
    }

}
