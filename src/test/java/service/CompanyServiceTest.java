package service;

import com.serheev.config.AppConfig;
import com.serheev.model.CompanyEntity;
import com.serheev.service.CompanyService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Transactional
@DirtiesContext
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyServiceTest {
    private static Logger log = Logger.getLogger(CompanyServiceTest.class);

    @Autowired
    private CompanyService companyService;

    private CompanyEntity newRecord;
    private CompanyEntity company;
    private CompanyEntity recordFromDB;
    @Value("${test.company.name}")
    private String companyName;
    @Value("${test.company.country}")
    private String companyCountry;

    @Before
    public void initDataBeforeTest() {
        newRecord = new CompanyEntity();
        newRecord.setName(companyName);
        newRecord.setCountry(companyCountry);
        company = companyService.save(newRecord);
        recordFromDB = companyService.findById(company.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = companyService.findById(company.getId());
        if (recordFromDB != null) {
            companyService.deleteById(company.getId());
        }
    }

    @Test
    public void newRecordShouldBeAdded() {
        assertTrue(5 == companyService.count());
        assertTrue(company.getId() == recordFromDB.getId());
        assertTrue(company.getName() == recordFromDB.getName());
        assertTrue(company.getCountry() == recordFromDB.getCountry());

        log.info(recordFromDB.getId() + " : " + recordFromDB.getName() + " : " + recordFromDB.getCountry());
    }

    @Test
    public void newRecordShouldBeDeleted() {
        assertNotNull(recordFromDB);

        companyService.delete(recordFromDB);

        recordFromDB = companyService.findById(company.getId());
        assertNull(recordFromDB);
    }

    @Test
    public void recordShouldBeRead() {
        assertTrue(recordFromDB.getName() == companyName);
        assertTrue(recordFromDB.getCountry() == companyCountry);
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.getId();

        recordFromDB.setName("Microsoft");
        recordFromDB.setCountry("Moldova");
        companyService.save(recordFromDB);

        long entryIdAfterUpdate = recordFromDB.getId();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.getName() == "Microsoft");
        assertTrue(recordFromDB.getCountry() == "Moldova");
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<CompanyEntity> companies = companyService.findAll();
        companies.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getCountry() + ";").forEach(log::info);
        assertTrue(companies.stream().count() == 5);
    }

}