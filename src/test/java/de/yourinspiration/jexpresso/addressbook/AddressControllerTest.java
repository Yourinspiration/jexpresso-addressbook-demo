package de.yourinspiration.jexpresso.addressbook;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.yourinspiration.jexpresso.JExpresso;
import de.yourinspiration.jexpresso.Request;
import de.yourinspiration.jexpresso.Response;
import de.yourinspiration.jexpresso.RouteHandler;

/**
 * Test case for {@link AddressController}.
 * 
 * @author Marcel Härle
 *
 */
public class AddressControllerTest {

    private AddressController controller;

    @Mock
    private JExpresso jexpresso;
    @Mock
    private AddressRepository repo;
    @Mock
    private Request req;
    @Mock
    private Response res;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AddressController(jexpresso, repo);
    }

    @Test
    public void testInit() {
        controller.init();

        Mockito.verify(jexpresso).get(Matchers.eq("/address"), Matchers.any(RouteHandler.class));
        Mockito.verify(jexpresso).get(Matchers.eq("/address/:id"), Matchers.any(RouteHandler.class));
        Mockito.verify(jexpresso).post(Matchers.eq("/address"), Matchers.any(RouteHandler.class));
        Mockito.verify(jexpresso).delete(Matchers.eq("/address/:id"), Matchers.any(RouteHandler.class));
    }

    @Test
    public void testFindAll() {
        final List<Address> addresses = new ArrayList<>();

        Mockito.when(repo.findAll()).thenReturn(addresses);

        controller.findAll(req, res);

        Mockito.verify(res).json(addresses);
    }

    @Test
    public void testFindOne() {
        final Address address = new Address();

        Mockito.when(repo.findOne("1234")).thenReturn(address);
        Mockito.when(req.param("id")).thenReturn("1234");

        controller.findOne(req, res);

        Mockito.verify(res).json(address);
    }

    @Test
    public void testSave() {
        final Address address = new Address();

        Mockito.when(req.json(Address.class)).thenReturn(address);
        Mockito.when(repo.save(address)).thenReturn(address);

        controller.save(req, res);

        Mockito.verify(res).json(address);
    }

    @Test
    public void testDelete() {
        Mockito.when(req.param("id")).thenReturn("1234");

        controller.delete(req, res);

        Mockito.verify(repo).delete("1234");
        Mockito.verify(res).send("");
    }

}
