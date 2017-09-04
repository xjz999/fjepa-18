package org.fjepa.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.fjepa.app.domain.Photolist;
import org.fjepa.app.service.PhotolistService;
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
 * REST controller for managing Photolist.
 */
@RestController
@RequestMapping("/api")
public class PhotolistResource {

    private final Logger log = LoggerFactory.getLogger(PhotolistResource.class);

    private static final String ENTITY_NAME = "photolist";

    private final PhotolistService photolistService;

    public PhotolistResource(PhotolistService photolistService) {
        this.photolistService = photolistService;
    }

    /**
     * POST  /photolists : Create a new photolist.
     *
     * @param photolist the photolist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new photolist, or with status 400 (Bad Request) if the photolist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/photolists")
    @Timed
    public ResponseEntity<Photolist> createPhotolist(@RequestBody Photolist photolist) throws URISyntaxException {
        log.debug("REST request to save Photolist : {}", photolist);
        if (photolist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new photolist cannot already have an ID")).body(null);
        }
        Photolist result = photolistService.save(photolist);
        return ResponseEntity.created(new URI("/api/photolists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /photolists : Updates an existing photolist.
     *
     * @param photolist the photolist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated photolist,
     * or with status 400 (Bad Request) if the photolist is not valid,
     * or with status 500 (Internal Server Error) if the photolist couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/photolists")
    @Timed
    public ResponseEntity<Photolist> updatePhotolist(@RequestBody Photolist photolist) throws URISyntaxException {
        log.debug("REST request to update Photolist : {}", photolist);
        if (photolist.getId() == null) {
            return createPhotolist(photolist);
        }
        Photolist result = photolistService.save(photolist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, photolist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /photolists : get all the photolists.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of photolists in body
     */
    @GetMapping("/photolists")
    @Timed
    public ResponseEntity<List<Photolist>> getAllPhotolists(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Photolists");
        Page<Photolist> page = photolistService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/photolists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /photolists/:id : get the "id" photolist.
     *
     * @param id the id of the photolist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the photolist, or with status 404 (Not Found)
     */
    @GetMapping("/photolists/{id}")
    @Timed
    public ResponseEntity<Photolist> getPhotolist(@PathVariable Long id) {
        log.debug("REST request to get Photolist : {}", id);
        Photolist photolist = photolistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(photolist));
    }

    /**
     * DELETE  /photolists/:id : delete the "id" photolist.
     *
     * @param id the id of the photolist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/photolists/{id}")
    @Timed
    public ResponseEntity<Void> deletePhotolist(@PathVariable Long id) {
        log.debug("REST request to delete Photolist : {}", id);
        photolistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
