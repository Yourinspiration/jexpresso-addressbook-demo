package de.yourinspiration.jexpresso.addressbook;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task for cleanup the address book data.
 * 
 * @author Marcel Härle
 *
 */
@Component
public class CleanupTask {

    private final AddressRepository repo;

    @Autowired
    public CleanupTask(final AddressRepository repo) {
        this.repo = repo;
    }

    /**
     * Deletes all address book data.
     */
    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void cleanUp() {
        repo.deleteAll();
        Logger.info("Cleanup task done");
    }

}
