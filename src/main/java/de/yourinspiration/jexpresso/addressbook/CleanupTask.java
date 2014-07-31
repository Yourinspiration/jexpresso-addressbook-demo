package de.yourinspiration.jexpresso.addressbook;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupTask {

    private final AddressRepository repo;

    @Autowired
    public CleanupTask(final AddressRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void cleanUp() {
        repo.deleteAll();
        Logger.info("Cleanup task done");
    }

}
