package org.fjepa.app.web.rest;

import org.fjepa.app.FjepaApp;

import org.fjepa.app.domain.Photolist;
import org.fjepa.app.repository.PhotolistRepository;
import org.fjepa.app.service.PhotolistService;
import org.fjepa.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static org.fjepa.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PhotolistResource REST controller.
 *
 * @see PhotolistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FjepaApp.class)
public class PhotolistResourceIntTest {

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_AID = "AAAAAAAAAA";
    private static final String UPDATED_AID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_STORY = "AAAAAAAAAA";
    private static final String UPDATED_STORY = "BBBBBBBBBB";

    private static final String DEFAULT_PICURL = "AAAAAAAAAA";
    private static final String UPDATED_PICURL = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SYS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OWN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OWN_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_LOGIN_CHECK = false;
    private static final Boolean UPDATED_IS_LOGIN_CHECK = true;

    private static final Boolean DEFAULT_IS_RECOMMENT = false;
    private static final Boolean UPDATED_IS_RECOMMENT = true;

    private static final ZonedDateTime DEFAULT_UPLOAD_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPLOAD_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_ORDERINDEX = 1;
    private static final Integer UPDATED_ORDERINDEX = 2;

    @Autowired
    private PhotolistRepository photolistRepository;

    @Autowired
    private PhotolistService photolistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhotolistMockMvc;

    private Photolist photolist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhotolistResource photolistResource = new PhotolistResource(photolistService);
        this.restPhotolistMockMvc = MockMvcBuilders.standaloneSetup(photolistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Photolist createEntity(EntityManager em) {
        Photolist photolist = new Photolist()
            .uid(DEFAULT_UID)
            .aid(DEFAULT_AID)
            .title(DEFAULT_TITLE)
            .author(DEFAULT_AUTHOR)
            .story(DEFAULT_STORY)
            .picurl(DEFAULT_PICURL)
            .sysType(DEFAULT_SYS_TYPE)
            .ownType(DEFAULT_OWN_TYPE)
            .isLoginCheck(DEFAULT_IS_LOGIN_CHECK)
            .isRecomment(DEFAULT_IS_RECOMMENT)
            .uploadTime(DEFAULT_UPLOAD_TIME)
            .orderindex(DEFAULT_ORDERINDEX);
        return photolist;
    }

    @Before
    public void initTest() {
        photolist = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhotolist() throws Exception {
        int databaseSizeBeforeCreate = photolistRepository.findAll().size();

        // Create the Photolist
        restPhotolistMockMvc.perform(post("/api/photolists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photolist)))
            .andExpect(status().isCreated());

        // Validate the Photolist in the database
        List<Photolist> photolistList = photolistRepository.findAll();
        assertThat(photolistList).hasSize(databaseSizeBeforeCreate + 1);
        Photolist testPhotolist = photolistList.get(photolistList.size() - 1);
        assertThat(testPhotolist.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testPhotolist.getAid()).isEqualTo(DEFAULT_AID);
        assertThat(testPhotolist.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPhotolist.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testPhotolist.getStory()).isEqualTo(DEFAULT_STORY);
        assertThat(testPhotolist.getPicurl()).isEqualTo(DEFAULT_PICURL);
        assertThat(testPhotolist.getSysType()).isEqualTo(DEFAULT_SYS_TYPE);
        assertThat(testPhotolist.getOwnType()).isEqualTo(DEFAULT_OWN_TYPE);
        assertThat(testPhotolist.isIsLoginCheck()).isEqualTo(DEFAULT_IS_LOGIN_CHECK);
        assertThat(testPhotolist.isIsRecomment()).isEqualTo(DEFAULT_IS_RECOMMENT);
        assertThat(testPhotolist.getUploadTime()).isEqualTo(DEFAULT_UPLOAD_TIME);
        assertThat(testPhotolist.getOrderindex()).isEqualTo(DEFAULT_ORDERINDEX);
    }

    @Test
    @Transactional
    public void createPhotolistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = photolistRepository.findAll().size();

        // Create the Photolist with an existing ID
        photolist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhotolistMockMvc.perform(post("/api/photolists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photolist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Photolist> photolistList = photolistRepository.findAll();
        assertThat(photolistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPhotolists() throws Exception {
        // Initialize the database
        photolistRepository.saveAndFlush(photolist);

        // Get all the photolistList
        restPhotolistMockMvc.perform(get("/api/photolists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(photolist.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID.toString())))
            .andExpect(jsonPath("$.[*].aid").value(hasItem(DEFAULT_AID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())))
            .andExpect(jsonPath("$.[*].story").value(hasItem(DEFAULT_STORY.toString())))
            .andExpect(jsonPath("$.[*].picurl").value(hasItem(DEFAULT_PICURL.toString())))
            .andExpect(jsonPath("$.[*].sysType").value(hasItem(DEFAULT_SYS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ownType").value(hasItem(DEFAULT_OWN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isLoginCheck").value(hasItem(DEFAULT_IS_LOGIN_CHECK.booleanValue())))
            .andExpect(jsonPath("$.[*].isRecomment").value(hasItem(DEFAULT_IS_RECOMMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].uploadTime").value(hasItem(sameInstant(DEFAULT_UPLOAD_TIME))))
            .andExpect(jsonPath("$.[*].orderindex").value(hasItem(DEFAULT_ORDERINDEX)));
    }

    @Test
    @Transactional
    public void getPhotolist() throws Exception {
        // Initialize the database
        photolistRepository.saveAndFlush(photolist);

        // Get the photolist
        restPhotolistMockMvc.perform(get("/api/photolists/{id}", photolist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(photolist.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID.toString()))
            .andExpect(jsonPath("$.aid").value(DEFAULT_AID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()))
            .andExpect(jsonPath("$.story").value(DEFAULT_STORY.toString()))
            .andExpect(jsonPath("$.picurl").value(DEFAULT_PICURL.toString()))
            .andExpect(jsonPath("$.sysType").value(DEFAULT_SYS_TYPE.toString()))
            .andExpect(jsonPath("$.ownType").value(DEFAULT_OWN_TYPE.toString()))
            .andExpect(jsonPath("$.isLoginCheck").value(DEFAULT_IS_LOGIN_CHECK.booleanValue()))
            .andExpect(jsonPath("$.isRecomment").value(DEFAULT_IS_RECOMMENT.booleanValue()))
            .andExpect(jsonPath("$.uploadTime").value(sameInstant(DEFAULT_UPLOAD_TIME)))
            .andExpect(jsonPath("$.orderindex").value(DEFAULT_ORDERINDEX));
    }

    @Test
    @Transactional
    public void getNonExistingPhotolist() throws Exception {
        // Get the photolist
        restPhotolistMockMvc.perform(get("/api/photolists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhotolist() throws Exception {
        // Initialize the database
        photolistService.save(photolist);

        int databaseSizeBeforeUpdate = photolistRepository.findAll().size();

        // Update the photolist
        Photolist updatedPhotolist = photolistRepository.findOne(photolist.getId());
        updatedPhotolist
            .uid(UPDATED_UID)
            .aid(UPDATED_AID)
            .title(UPDATED_TITLE)
            .author(UPDATED_AUTHOR)
            .story(UPDATED_STORY)
            .picurl(UPDATED_PICURL)
            .sysType(UPDATED_SYS_TYPE)
            .ownType(UPDATED_OWN_TYPE)
            .isLoginCheck(UPDATED_IS_LOGIN_CHECK)
            .isRecomment(UPDATED_IS_RECOMMENT)
            .uploadTime(UPDATED_UPLOAD_TIME)
            .orderindex(UPDATED_ORDERINDEX);

        restPhotolistMockMvc.perform(put("/api/photolists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhotolist)))
            .andExpect(status().isOk());

        // Validate the Photolist in the database
        List<Photolist> photolistList = photolistRepository.findAll();
        assertThat(photolistList).hasSize(databaseSizeBeforeUpdate);
        Photolist testPhotolist = photolistList.get(photolistList.size() - 1);
        assertThat(testPhotolist.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testPhotolist.getAid()).isEqualTo(UPDATED_AID);
        assertThat(testPhotolist.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPhotolist.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testPhotolist.getStory()).isEqualTo(UPDATED_STORY);
        assertThat(testPhotolist.getPicurl()).isEqualTo(UPDATED_PICURL);
        assertThat(testPhotolist.getSysType()).isEqualTo(UPDATED_SYS_TYPE);
        assertThat(testPhotolist.getOwnType()).isEqualTo(UPDATED_OWN_TYPE);
        assertThat(testPhotolist.isIsLoginCheck()).isEqualTo(UPDATED_IS_LOGIN_CHECK);
        assertThat(testPhotolist.isIsRecomment()).isEqualTo(UPDATED_IS_RECOMMENT);
        assertThat(testPhotolist.getUploadTime()).isEqualTo(UPDATED_UPLOAD_TIME);
        assertThat(testPhotolist.getOrderindex()).isEqualTo(UPDATED_ORDERINDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingPhotolist() throws Exception {
        int databaseSizeBeforeUpdate = photolistRepository.findAll().size();

        // Create the Photolist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPhotolistMockMvc.perform(put("/api/photolists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photolist)))
            .andExpect(status().isCreated());

        // Validate the Photolist in the database
        List<Photolist> photolistList = photolistRepository.findAll();
        assertThat(photolistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePhotolist() throws Exception {
        // Initialize the database
        photolistService.save(photolist);

        int databaseSizeBeforeDelete = photolistRepository.findAll().size();

        // Get the photolist
        restPhotolistMockMvc.perform(delete("/api/photolists/{id}", photolist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Photolist> photolistList = photolistRepository.findAll();
        assertThat(photolistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Photolist.class);
        Photolist photolist1 = new Photolist();
        photolist1.setId(1L);
        Photolist photolist2 = new Photolist();
        photolist2.setId(photolist1.getId());
        assertThat(photolist1).isEqualTo(photolist2);
        photolist2.setId(2L);
        assertThat(photolist1).isNotEqualTo(photolist2);
        photolist1.setId(null);
        assertThat(photolist1).isNotEqualTo(photolist2);
    }
}
