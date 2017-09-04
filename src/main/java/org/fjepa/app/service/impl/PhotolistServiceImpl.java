package org.fjepa.app.service.impl;

import org.fjepa.app.service.PhotolistService;
import org.fjepa.app.domain.Photolist;
import org.fjepa.app.repository.PhotolistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Photolist.
 */
@Service
@Transactional
public class PhotolistServiceImpl implements PhotolistService{

    private final Logger log = LoggerFactory.getLogger(PhotolistServiceImpl.class);

    private final PhotolistRepository photolistRepository;
    public PhotolistServiceImpl(PhotolistRepository photolistRepository) {
        this.photolistRepository = photolistRepository;
    }

    /**
     * Save a photolist.
     *
     * @param photolist the entity to save
     * @return the persisted entity
     */
    @Override
    public Photolist save(Photolist photolist) {
        log.debug("Request to save Photolist : {}", photolist);
        return photolistRepository.save(photolist);
    }

    /**
     *  Get all the photolists.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Photolist> findAll(Pageable pageable) {
        log.debug("Request to get all Photolists");
        return photolistRepository.findAll(pageable);
    }

    /**
     *  Get one photolist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Photolist findOne(Long id) {
        log.debug("Request to get Photolist : {}", id);
        return photolistRepository.findOne(id);
    }

    /**
     *  Delete the  photolist by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Photolist : {}", id);
        photolistRepository.delete(id);
    }
}
