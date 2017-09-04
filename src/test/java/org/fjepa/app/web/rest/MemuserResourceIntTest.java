package org.fjepa.app.web.rest;

import org.fjepa.app.FjepaApp;

import org.fjepa.app.domain.Memuser;
import org.fjepa.app.repository.MemuserRepository;
import org.fjepa.app.service.MemuserService;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the MemuserResource REST controller.
 *
 * @see MemuserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FjepaApp.class)
public class MemuserResourceIntTest {

    private static final String DEFAULT_TRUENAME = "AAAAAAAAAA";
    private static final String UPDATED_TRUENAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENNAME = "AAAAAAAAAA";
    private static final String UPDATED_ENNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEX = 1;
    private static final Integer UPDATED_SEX = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MEMLEVEL = 1;
    private static final Integer UPDATED_MEMLEVEL = 2;

    private static final String DEFAULT_PORTRAIT = "AAAAAAAAAA";
    private static final String UPDATED_PORTRAIT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_EDITTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EDITTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_QQTOKEN = "AAAAAAAAAA";
    private static final String UPDATED_QQTOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_WECHATTOKEN = "AAAAAAAAAA";
    private static final String UPDATED_WECHATTOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_WEIBOTOKEN = "AAAAAAAAAA";
    private static final String UPDATED_WEIBOTOKEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_REGTYPE = 1;
    private static final Integer UPDATED_REGTYPE = 2;

    private static final String DEFAULT_REGPHOTO = "AAAAAAAAAA";
    private static final String UPDATED_REGPHOTO = "BBBBBBBBBB";

    @Autowired
    private MemuserRepository memuserRepository;

    @Autowired
    private MemuserService memuserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMemuserMockMvc;

    private Memuser memuser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MemuserResource memuserResource = new MemuserResource(memuserService);
        this.restMemuserMockMvc = MockMvcBuilders.standaloneSetup(memuserResource)
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
    public static Memuser createEntity(EntityManager em) {
        Memuser memuser = new Memuser()
            .truename(DEFAULT_TRUENAME)
            .enname(DEFAULT_ENNAME)
            .password(DEFAULT_PASSWORD)
            .sex(DEFAULT_SEX)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .memlevel(DEFAULT_MEMLEVEL)
            .portrait(DEFAULT_PORTRAIT)
            .createtime(DEFAULT_CREATETIME)
            .edittime(DEFAULT_EDITTIME)
            .qqtoken(DEFAULT_QQTOKEN)
            .wechattoken(DEFAULT_WECHATTOKEN)
            .weibotoken(DEFAULT_WEIBOTOKEN)
            .regtype(DEFAULT_REGTYPE)
            .regphoto(DEFAULT_REGPHOTO);
        return memuser;
    }

    @Before
    public void initTest() {
        memuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createMemuser() throws Exception {
        int databaseSizeBeforeCreate = memuserRepository.findAll().size();

        // Create the Memuser
        restMemuserMockMvc.perform(post("/api/memusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memuser)))
            .andExpect(status().isCreated());

        // Validate the Memuser in the database
        List<Memuser> memuserList = memuserRepository.findAll();
        assertThat(memuserList).hasSize(databaseSizeBeforeCreate + 1);
        Memuser testMemuser = memuserList.get(memuserList.size() - 1);
        assertThat(testMemuser.getTruename()).isEqualTo(DEFAULT_TRUENAME);
        assertThat(testMemuser.getEnname()).isEqualTo(DEFAULT_ENNAME);
        assertThat(testMemuser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testMemuser.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testMemuser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMemuser.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testMemuser.getMemlevel()).isEqualTo(DEFAULT_MEMLEVEL);
        assertThat(testMemuser.getPortrait()).isEqualTo(DEFAULT_PORTRAIT);
        assertThat(testMemuser.getCreatetime()).isEqualTo(DEFAULT_CREATETIME);
        assertThat(testMemuser.getEdittime()).isEqualTo(DEFAULT_EDITTIME);
        assertThat(testMemuser.getQqtoken()).isEqualTo(DEFAULT_QQTOKEN);
        assertThat(testMemuser.getWechattoken()).isEqualTo(DEFAULT_WECHATTOKEN);
        assertThat(testMemuser.getWeibotoken()).isEqualTo(DEFAULT_WEIBOTOKEN);
        assertThat(testMemuser.getRegtype()).isEqualTo(DEFAULT_REGTYPE);
        assertThat(testMemuser.getRegphoto()).isEqualTo(DEFAULT_REGPHOTO);
    }

    @Test
    @Transactional
    public void createMemuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = memuserRepository.findAll().size();

        // Create the Memuser with an existing ID
        memuser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemuserMockMvc.perform(post("/api/memusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memuser)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Memuser> memuserList = memuserRepository.findAll();
        assertThat(memuserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMemusers() throws Exception {
        // Initialize the database
        memuserRepository.saveAndFlush(memuser);

        // Get all the memuserList
        restMemuserMockMvc.perform(get("/api/memusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].truename").value(hasItem(DEFAULT_TRUENAME.toString())))
            .andExpect(jsonPath("$.[*].enname").value(hasItem(DEFAULT_ENNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].memlevel").value(hasItem(DEFAULT_MEMLEVEL)))
            .andExpect(jsonPath("$.[*].portrait").value(hasItem(DEFAULT_PORTRAIT.toString())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(sameInstant(DEFAULT_CREATETIME))))
            .andExpect(jsonPath("$.[*].edittime").value(hasItem(sameInstant(DEFAULT_EDITTIME))))
            .andExpect(jsonPath("$.[*].qqtoken").value(hasItem(DEFAULT_QQTOKEN.toString())))
            .andExpect(jsonPath("$.[*].wechattoken").value(hasItem(DEFAULT_WECHATTOKEN.toString())))
            .andExpect(jsonPath("$.[*].weibotoken").value(hasItem(DEFAULT_WEIBOTOKEN.toString())))
            .andExpect(jsonPath("$.[*].regtype").value(hasItem(DEFAULT_REGTYPE)))
            .andExpect(jsonPath("$.[*].regphoto").value(hasItem(DEFAULT_REGPHOTO.toString())));
    }

    @Test
    @Transactional
    public void getMemuser() throws Exception {
        // Initialize the database
        memuserRepository.saveAndFlush(memuser);

        // Get the memuser
        restMemuserMockMvc.perform(get("/api/memusers/{id}", memuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(memuser.getId().intValue()))
            .andExpect(jsonPath("$.truename").value(DEFAULT_TRUENAME.toString()))
            .andExpect(jsonPath("$.enname").value(DEFAULT_ENNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.memlevel").value(DEFAULT_MEMLEVEL))
            .andExpect(jsonPath("$.portrait").value(DEFAULT_PORTRAIT.toString()))
            .andExpect(jsonPath("$.createtime").value(sameInstant(DEFAULT_CREATETIME)))
            .andExpect(jsonPath("$.edittime").value(sameInstant(DEFAULT_EDITTIME)))
            .andExpect(jsonPath("$.qqtoken").value(DEFAULT_QQTOKEN.toString()))
            .andExpect(jsonPath("$.wechattoken").value(DEFAULT_WECHATTOKEN.toString()))
            .andExpect(jsonPath("$.weibotoken").value(DEFAULT_WEIBOTOKEN.toString()))
            .andExpect(jsonPath("$.regtype").value(DEFAULT_REGTYPE))
            .andExpect(jsonPath("$.regphoto").value(DEFAULT_REGPHOTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMemuser() throws Exception {
        // Get the memuser
        restMemuserMockMvc.perform(get("/api/memusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMemuser() throws Exception {
        // Initialize the database
        memuserService.save(memuser);

        int databaseSizeBeforeUpdate = memuserRepository.findAll().size();

        // Update the memuser
        Memuser updatedMemuser = memuserRepository.findOne(memuser.getId());
        updatedMemuser
            .truename(UPDATED_TRUENAME)
            .enname(UPDATED_ENNAME)
            .password(UPDATED_PASSWORD)
            .sex(UPDATED_SEX)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .memlevel(UPDATED_MEMLEVEL)
            .portrait(UPDATED_PORTRAIT)
            .createtime(UPDATED_CREATETIME)
            .edittime(UPDATED_EDITTIME)
            .qqtoken(UPDATED_QQTOKEN)
            .wechattoken(UPDATED_WECHATTOKEN)
            .weibotoken(UPDATED_WEIBOTOKEN)
            .regtype(UPDATED_REGTYPE)
            .regphoto(UPDATED_REGPHOTO);

        restMemuserMockMvc.perform(put("/api/memusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMemuser)))
            .andExpect(status().isOk());

        // Validate the Memuser in the database
        List<Memuser> memuserList = memuserRepository.findAll();
        assertThat(memuserList).hasSize(databaseSizeBeforeUpdate);
        Memuser testMemuser = memuserList.get(memuserList.size() - 1);
        assertThat(testMemuser.getTruename()).isEqualTo(UPDATED_TRUENAME);
        assertThat(testMemuser.getEnname()).isEqualTo(UPDATED_ENNAME);
        assertThat(testMemuser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testMemuser.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testMemuser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMemuser.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testMemuser.getMemlevel()).isEqualTo(UPDATED_MEMLEVEL);
        assertThat(testMemuser.getPortrait()).isEqualTo(UPDATED_PORTRAIT);
        assertThat(testMemuser.getCreatetime()).isEqualTo(UPDATED_CREATETIME);
        assertThat(testMemuser.getEdittime()).isEqualTo(UPDATED_EDITTIME);
        assertThat(testMemuser.getQqtoken()).isEqualTo(UPDATED_QQTOKEN);
        assertThat(testMemuser.getWechattoken()).isEqualTo(UPDATED_WECHATTOKEN);
        assertThat(testMemuser.getWeibotoken()).isEqualTo(UPDATED_WEIBOTOKEN);
        assertThat(testMemuser.getRegtype()).isEqualTo(UPDATED_REGTYPE);
        assertThat(testMemuser.getRegphoto()).isEqualTo(UPDATED_REGPHOTO);
    }

    @Test
    @Transactional
    public void updateNonExistingMemuser() throws Exception {
        int databaseSizeBeforeUpdate = memuserRepository.findAll().size();

        // Create the Memuser

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMemuserMockMvc.perform(put("/api/memusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memuser)))
            .andExpect(status().isCreated());

        // Validate the Memuser in the database
        List<Memuser> memuserList = memuserRepository.findAll();
        assertThat(memuserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMemuser() throws Exception {
        // Initialize the database
        memuserService.save(memuser);

        int databaseSizeBeforeDelete = memuserRepository.findAll().size();

        // Get the memuser
        restMemuserMockMvc.perform(delete("/api/memusers/{id}", memuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Memuser> memuserList = memuserRepository.findAll();
        assertThat(memuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Memuser.class);
        Memuser memuser1 = new Memuser();
        memuser1.setId(1L);
        Memuser memuser2 = new Memuser();
        memuser2.setId(memuser1.getId());
        assertThat(memuser1).isEqualTo(memuser2);
        memuser2.setId(2L);
        assertThat(memuser1).isNotEqualTo(memuser2);
        memuser1.setId(null);
        assertThat(memuser1).isNotEqualTo(memuser2);
    }
}
