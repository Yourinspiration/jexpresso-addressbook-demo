package de.yourinspiration.jexpresso.addressbook;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.yourinspiration.jexpresso.JExpresso;
import de.yourinspiration.jexpresso.Request;
import de.yourinspiration.jexpresso.Response;

/**
 * Controller to retrieve, save and delete address book data.
 * 
 * @author Marcel Härle
 *
 */
@Component
public class AddressController {

    private final JExpresso jexpresso;
    private final AddressRepository repo;

    @Autowired
    public AddressController(JExpresso jexpresso, AddressRepository repo) {
        this.jexpresso = jexpresso;
        this.repo = repo;
    }

    /**
     * Creates the route mappings.
     */
    @PostConstruct
    public void init() {
        jexpresso.get("/address", (req, res) -> findAll(req, res));
        jexpresso.get("/address/:id", (req, res) -> findOne(req, res));
        jexpresso.post("/address", (req, res) -> save(req, res));
        jexpresso.delete("/address/:id", (req, res) -> delete(req, res));
    }

    /**
     * Find all addresses.
     * 
     * @param req
     *            the request
     * @param res
     *            the response
     */
    public void findAll(final Request req, final Response res) {
        res.json(repo.findAll());
    }

    /**
     * Find the address for a given id.
     * 
     * @param req
     *            the request
     * @param res
     *            the response
     */
    public void findOne(final Request req, final Response res) {
        res.json(repo.findOne(req.param("id")));
    }

    /**
     * Save the given address.
     * 
     * @param req
     *            the request
     * @param res
     *            the response
     */
    public void save(final Request req, final Response res) {
        res.json(repo.save(req.json(Address.class)));
    }

    /**
     * Deletes the given address.
     * 
     * @param req
     *            the request
     * @param res
     *            the response
     */
    public void delete(final Request req, final Response res) {
        repo.delete(req.param("id"));
        res.send("");
    }

}
