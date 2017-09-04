package org.fjepa.app.service.impl;

import org.fjepa.app.service.MemuserService;
import org.fjepa.app.domain.Memuser;
import org.fjepa.app.repository.MemuserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Memuser.
 */
@Service
@Transactional
public class MemuserServiceImpl implements MemuserService{

    private final Logger log = LoggerFactory.getLogger(MemuserServiceImpl.class);

    private final MemuserRepository memuserRepository;
    public MemuserServiceImpl(MemuserRepository memuserRepository) {
        this.memuserRepository = memuserRepository;
    }

    /**
     * Save a memuser.
     *
     * @param memuser the entity to save
     * @return the persisted entity
     */
    @Override
    public Memuser save(Memuser memuser) {
        log.debug("Request to save Memuser : {}", memuser);
        return memuserRepository.save(memuser);
    }

    /**
     *  Get all the memusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Memuser> findAll(Pageable pageable) {
        log.debug("Request to get all Memusers");
        return memuserRepository.findAll(pageable);
    }

    /**
     *  Get one memuser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Memuser findOne(Long id) {
        log.debug("Request to get Memuser : {}", id);
        return memuserRepository.findOne(id);
    }

    /**
     *  Delete the  memuser by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Memuser : {}", id);
        memuserRepository.delete(id);
    }
}
