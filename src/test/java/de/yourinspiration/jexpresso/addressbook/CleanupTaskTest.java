package de.yourinspiration.jexpresso.addressbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test case for {@link CleanupTask}.
 * 
 * @author Marcel Härle
 *
 */
public class CleanupTaskTest {

    private CleanupTask task;

    @Mock
    private AddressRepository repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        task = new CleanupTask(repo);
    }

    @Test
    public void testCleanUp() {
        task.cleanUp();

        Mockito.verify(repo).deleteAll();
    }

}
