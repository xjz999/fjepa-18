package org.fjepa.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.fjepa.app.domain.Memuser;
import org.fjepa.app.service.MemuserService;
import org.fjepa.app.web.rest.util.HeaderUtil;
import org.fjepa.app.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Memuser.
 */
@RestController
@RequestMapping("/api")
public class MemuserResource {

    private final Logger log = LoggerFactory.getLogger(MemuserResource.class);

    private static final String ENTITY_NAME = "memuser";

    private final MemuserService memuserService;

    public MemuserResource(MemuserService memuserService) {
        this.memuserService = memuserService;
    }

    /**
     * POST  /memusers : Create a new memuser.
     *
     * @param memuser the memuser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new memuser, or with status 400 (Bad Request) if the memuser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/memusers")
    @Timed
    public ResponseEntity<Memuser> createMemuser(@RequestBody Memuser memuser) throws URISyntaxException {
        log.debug("REST request to save Memuser : {}", memuser);
        if (memuser.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new memuser cannot already have an ID")).body(null);
        }
        Memuser result = memuserService.save(memuser);
        return ResponseEntity.created(new URI("/api/memusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /memusers : Updates an existing memuser.
     *
     * @param memuser the memuser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated memuser,
     * or with status 400 (Bad Request) if the memuser is not valid,
     * or with status 500 (Internal Server Error) if the memuser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/memusers")
    @Timed
    public ResponseEntity<Memuser> updateMemuser(@RequestBody Memuser memuser) throws URISyntaxException {
        log.debug("REST request to update Memuser : {}", memuser);
        if (memuser.getId() == null) {
            return createMemuser(memuser);
        }
        Memuser result = memuserService.save(memuser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, memuser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /memusers : get all the memusers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of memusers in body
     */
    @GetMapping("/memusers")
    @Timed
    public ResponseEntity<List<Memuser>> getAllMemusers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Memusers");
        Page<Memuser> page = memuserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/memusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /memusers/:id : get the "id" memuser.
     *
     * @param id the id of the memuser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the memuser, or with status 404 (Not Found)
     */
    @GetMapping("/memusers/{id}")
    @Timed
    public ResponseEntity<Memuser> getMemuser(@PathVariable Long id) {
        log.debug("REST request to get Memuser : {}", id);
        Memuser memuser = memuserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(memuser));
    }

    /**
     * DELETE  /memusers/:id : delete the "id" memuser.
     *
     * @param id the id of the memuser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/memusers/{id}")
    @Timed
    public ResponseEntity<Void> deleteMemuser(@PathVariable Long id) {
        log.debug("REST request to delete Memuser : {}", id);
        memuserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
