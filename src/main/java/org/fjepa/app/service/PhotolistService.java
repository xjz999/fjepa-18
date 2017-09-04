package org.fjepa.app.service;

import org.fjepa.app.domain.Photolist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Photolist.
 */
public interface PhotolistService {

    /**
     * Save a photolist.
     *
     * @param photolist the entity to save
     * @return the persisted entity
     */
    Photolist save(Photolist photolist);

    /**
     *  Get all the photolists.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Photolist> findAll(Pageable pageable);

    /**
     *  Get the "id" photolist.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Photolist findOne(Long id);

    /**
     *  Delete the "id" photolist.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
