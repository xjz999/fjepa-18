package org.fjepa.app.service;

import org.fjepa.app.domain.Memuser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Memuser.
 */
public interface MemuserService {

    /**
     * Save a memuser.
     *
     * @param memuser the entity to save
     * @return the persisted entity
     */
    Memuser save(Memuser memuser);

    /**
     *  Get all the memusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Memuser> findAll(Pageable pageable);

    /**
     *  Get the "id" memuser.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Memuser findOne(Long id);

    /**
     *  Delete the "id" memuser.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
