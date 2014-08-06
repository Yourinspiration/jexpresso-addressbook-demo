package de.yourinspiration.jexpresso.addressbook;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for addresses.
 * 
 * @author Marcel Härle
 *
 */
public interface AddressRepository extends MongoRepository<Address, String> {

}
