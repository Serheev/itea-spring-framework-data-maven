package service;

import com.serheev.config.AppConfig;
import com.serheev.model.DeveloperEntity;
import com.serheev.service.DeveloperService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeveloperServiceTest {
    private static Logger log = Logger.getLogger(DeveloperServiceTest.class);

    @Autowired
    private DeveloperService developerService;

    private DeveloperEntity newRecord;
    private DeveloperEntity developer;
    private DeveloperEntity recordFromDB;

    @Before
    public void initDataBeforeTest() {
        newRecord = new DeveloperEntity();
        newRecord.setName("Sarah Connor");
        newRecord.setAge(25);
        newRecord.setSex('w');
        newRecord.setSalary(33333);
        newRecord.setOnLeave(false);
        developer = developerService.save(newRecord);
        log.info(newRecord.getName());
        recordFromDB = developerService.findById(developer.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = developerService.findById(developer.getId());
        if (recordFromDB != null) {
            developerService.deleteById(developer.getId());
        }
    }

    @Test
    public void newRecordShouldBeAdded() {
        assertTrue(developer.getId() == recordFromDB.getId());
        assertTrue(developer.getName() == recordFromDB.getName());
        assertTrue(developer.getAge() == recordFromDB.getAge());
        assertTrue(developer.getSex() == recordFromDB.getSex());
    }

    @Test
    public void newRecordShouldBeDeleted() {
        assertNotNull(recordFromDB);

        developerService.deleteById(recordFromDB.getId());

        recordFromDB = developerService.findById(developer.getId());
        assertNull(recordFromDB);
    }

    @Test
    public void recordShouldBeRead() {
        assertTrue(recordFromDB.getName() == "Sarah Connor");
        assertTrue(recordFromDB.getAge() == 25);
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.getId();

        recordFromDB.setName("Anna Karenina");
        recordFromDB.setAge(30);
        developerService.save(recordFromDB);

        long entryIdAfterUpdate = recordFromDB.getId();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.getName() == "Anna Karenina");
        assertTrue(recordFromDB.getAge() == 30);
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<DeveloperEntity> developers = developerService.findAll();
        developers.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getAge() + ";").forEach(log::info);
        assertTrue(developers.stream().count() == 7);
    }
}