package de.yourinspiration.jexpresso.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test case for {@link Address}.
 * 
 * @author Marcel Härle
 *
 */
public class AddressTest {

    @Test
    public void testGetterAndSetter() {
        final String id = "l12nr1l2krn1lk2nr";
        final String firstName = "Max";
        final String middleName = "Hans";
        final String lastName = "Mustermann";
        final String country = "Deutschland";
        final String city = "Musterstadt";
        final String zip = "123456";
        final String street = "Musterstrasse";

        final Address address = new Address();

        address.setCity(city);
        address.setCountry(country);
        address.setFirstName(firstName);
        address.setId(id);
        address.setLastName(lastName);
        address.setMiddleName(middleName);
        address.setStreet(street);
        address.setZip(zip);

        assertEquals(id, address.getId());
        assertEquals(firstName, address.getFirstName());
        assertEquals(middleName, address.getMiddleName());
        assertEquals(lastName, address.getLastName());
        assertEquals(country, address.getCountry());
        assertEquals(city, address.getCity());
        assertEquals(zip, address.getZip());
        assertEquals(street, address.getStreet());
    }

}
