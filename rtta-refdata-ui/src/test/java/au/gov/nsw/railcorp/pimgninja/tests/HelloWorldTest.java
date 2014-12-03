/*
package au.gov.nsw.railcorp.pimgninja.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import au.gov.nsw.railcorp.pimgninja.outputmodels.NinjaModelN;
import au.gov.nsw.railcorp.pimgninja.servlet.NinjaIsland;
import au.gov.nsw.railcorp.pimgninja.tools.NinjaTemple;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldTest extends TestCase {

    private static final Logger log = LoggerFactory
    .getLogger(HelloWorldTest.class);

    @InjectMocks
    private NinjaIsland hw;

    @Override
    public void setUp() {

        hw = new NinjaIsland(); 
    }

    @Override
    public void tearDown() {
        hw = null;
    }

    // Test cases:
    // change the focus clan
    // change the status of a ninjit to error
    @Test
    public void testSendClans() throws IOException, ServletException {

        
        log.info("HelloWorldTest - testSendaFocusClan");
        List<NinjaModelN> ninjas = createFakeNinjas();
        hw.getNinjaManger().setNinjas(ninjas);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter out = mock(PrintWriter.class);
        final Gson gson = new Gson();

        when(response.getWriter()).thenReturn(out);

        hw.doGet(request, response);

        verify(out).println(gson.toJson(ninjas));
    }

    private List<NinjaModelN> createFakeNinjas() {

        List<NinjaModelN> ninjas = new ArrayList<NinjaModelN>();

        final NinjaModelN ninja1 = new NinjaModelN();
        ninja1.setClan("fire");
        ninja1.setTitle("Ninja1");
        ninja1.setMission("one");
        ninjas.add(ninja1);

        final NinjaModelN ninja2 = new NinjaModelN();
        ninja2.setClan("fire");
        ninja2.setTitle("Ninja1");
        ninja2.setMission("two");
        ninjas.add(ninja2);

        final NinjaModelN ninja3 = new NinjaModelN();
        ninja3.setClan("water");
        ninja3.setTitle("Ninja1");
        ninja3.setMission("three");
        ninjas.add(ninja3);

        return ninjas;
    }

}
*/