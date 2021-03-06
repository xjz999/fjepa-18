package org.fjepa.app.service.impl;

import org.fjepa.app.service.NewsService;
import org.fjepa.app.domain.News;
import org.fjepa.app.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing News.
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService{

    private final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final NewsRepository newsRepository;
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Save a news.
     *
     * @param news the entity to save
     * @return the persisted entity
     */
    @Override
    public News save(News news) {
        log.debug("Request to save News : {}", news);
        return newsRepository.save(news);
    }

    /**
     *  Get all the news.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<News> findAll(Pageable pageable) {
        log.debug("Request to get all News");
        return newsRepository.findAll(pageable);
    }

    /**
     *  Get one news by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public News findOne(Long id) {
        log.debug("Request to get News : {}", id);
        return newsRepository.findOne(id);
    }

    /**
     *  Delete the  news by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete News : {}", id);
        newsRepository.delete(id);
    }
}
