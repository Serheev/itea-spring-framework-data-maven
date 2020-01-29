package service;

import com.serheev.config.AppConfig;
import com.serheev.model.UserEntity;
import com.serheev.service.UserService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Transactional
@DirtiesContext
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceTest {
    private static Logger log = Logger.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    private UserEntity newRecord;
    private UserEntity user;
    private UserEntity recordFromDB;
    @Value("${test.user.name}")
    private String name;
    @Value("${test.user.password}")
    private String password;

    @Before
    public void initDataBeforeTest() {
        newRecord = new UserEntity();
        newRecord.setName(name);
        newRecord.setPassword(password);
        user = userService.save(newRecord);
        recordFromDB = userService.findById(user.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = userService.findById(user.getId());
        if (recordFromDB != null) {
            userService.deleteById(user.getId());
        }
    }

    @Test
    public void newRecordShouldBeAdded() {
        assertTrue(1 == userService.count());
        assertTrue(user.getId() == recordFromDB.getId());
        assertTrue(user.getName() == recordFromDB.getName());
        assertTrue(user.getPassword() == recordFromDB.getPassword());

        log.info(recordFromDB.getId() + " : " + recordFromDB.getName() + " : " + recordFromDB.getPassword());
    }

    @Test
    public void newRecordShouldBeDeleted() {
        assertNotNull(recordFromDB);
        userService.delete(recordFromDB);
        recordFromDB = userService.findById(user.getId());
        assertNull(recordFromDB);
    }

    @Test
    public void recordShouldBeRead() {
        assertTrue(recordFromDB.getName() == name);
        assertTrue(recordFromDB.getPassword() == password);
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.getId();
        recordFromDB.setName("Vadim");
        recordFromDB.setPassword("12345");
        userService.save(recordFromDB);
        long entryIdAfterUpdate = recordFromDB.getId();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.getName() == "Vadim");
        assertTrue(recordFromDB.getPassword() == "12345");
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<UserEntity> users = userService.findAll();
        users.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getPassword() + ";").forEach(log::info);
        assertTrue(users.stream().count() == 1);
    }

    @Test
    public void additionalInfoShouldBeUpdate() {
        Map<String, Object> info = new HashMap<>();
        info.put("entryOne", "Some information");
        info.put("entryTwo", 123456789);
        info.put("entryThree", true);

        log.info("User data before update: " + recordFromDB.getName() + " : " + recordFromDB.getPassword() + " : " + recordFromDB.getAdditionalInfo());
        userService.updateAdditionalInfo(recordFromDB, info);
        log.info("User data after update: " + recordFromDB.getName() + " : " + recordFromDB.getPassword() + " : " + recordFromDB.getAdditionalInfo());
    }
}
