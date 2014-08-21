// license-header java merge-point
//
/**
 * Generated by test/EntityTest.java.vsl in andromda-ejb3-cartridge on 10/16/2012 18:00:26.
 * This file can be safely modified. If deleted it will be regenerated.
 */
package org.andromda.timetracker.domain;
/**
 * TestNG Unit Test to Create and Validate and Update and Delete the User Entity
 */

import java.util.List;
import javax.persistence.TypedQuery;
import org.andromda.dbtest.JPAJUnitAncestor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Create USERS row from User Entity
 */
public class UserEntityTest extends JPAJUnitAncestor
{
    private static Logger LOGGER = LogManager.getLogger(UserEntityTest.class);
    /** Persisted Entity to retrieve and update and delete from */
    protected static User entity = null;

    /**
     * Creates USERS record. TestNG creates test group is run before retrieves and deletes.
     */
    @Test(groups = { "deleteAll" }, priority=1000+20)
    public void testDeleteAllUsers()
    {
        LOGGER.info("TEST DELETE ALL User, priority " + (1000+20));
        // Delete all rows from table  - avoid duplicate PK error or created record not found
        this.deleteAll();
        int results = this.logEntities();
        // Will also fail if all rows are not deleted during setUp
        Assert.assertTrue(results == 0, "Users deleted but records found: " + results);
        // Save PK for later retrieve/delete
        LOGGER.info("SUCCESS: DELETED All User " + results);
    }

    /**
     * Creates USERS record. TestNG creates test group is run before retrieves and deletes.
     */
    @Test(groups = { "creates" }, priority=2000+20)
    public void testCreateUser()
    {
        LOGGER.info("TEST CREATE User, priority " + (2000+20));
        User user = UserCreate.createEntity(this.getEm());
        LOGGER.debug("persisting " + user);
        this.getEm().persist(user);
        int results = this.logEntities();
        // Will also fail if all rows are not deleted during setUp
        Assert.assertTrue(results > 0, "User inserted but record not found: " + results);
        // Save PK for later retrieve/delete
        UserEntityTest.entity = user;
        LOGGER.info("SUCCESS: CREATED User " + user);
    }

    /**
     * Retrieving USERS record. Run after TestNG creates test group and before deletes group.
     */
    @Test(groups = { "retrieves" }, ignoreMissingDependencies=true, priority=3000+20)
    public void testRetrieveUser()
    {
        LOGGER.info("TEST RETRIEVE User, priority " + (3000+20));
        Long pk = Long.valueOf(1);
        User user = this.getEm().find(User.class, pk);
        if (user==null)
        {
            LOGGER.info("User not found for Primary Key value: " + pk + ", findingFirst Entity");
            user = UserCreate.findFirst(this.getEm());
        }
        else
        {
            LOGGER.info("SUCCESS: RETRIEVED User: " + user);
        }
    }

    /**
     * Deleting USERS record. Run after TestNG creates and retrieves test group.
     */
    @Test(groups = { "deletes" }, ignoreMissingDependencies=true, priority=5000+20)
    public void testDeleteUser()
    {
        LOGGER.info("TEST DELETE User, priority " + (5000+20));
        User user = UserCreate.findFirst(this.getEm());
        if (!this.getEm().contains(user))
        {
            LOGGER.info("merging User: " + user);
            user = this.getEm().merge(user);
        }
        LOGGER.info("deleting User: " + user);
        this.getEm().remove(user);
        LOGGER.info("SUCCESS: DELETED User");
    }

    /**
     * Find All Entities User - run only against test DB
     * @return User
     */
    public List<User> findAll()
    {
        TypedQuery<User> query = this.getEm().createNamedQuery("User.findAll", User.class);
        List<User> results = query.getResultList();
        return results;
    }

    /*
     * Return a persisted Entity User with all attributes and associations set
     * Used to set persistent association relationships in related entities.
     * Called by test delete method - this method should not be a test method
     * @return User
    public User findFirst()
    {
        User user = null;
        List<User> results = findAll();
        if (results.size() > 0)
        {
            user = results.get(0);
        }
        else
        {
            user = UserCreate.createEntity(this.getEm());
            this.getEm().persist(user);
        }
        return user;
    }
     */

    /**
     * Add any custom code to be executed once before any tests are executed
     */
    @Override
    @BeforeClass(alwaysRun=true)
    public void setUpBeforeClass()
    {
        super.setUpBeforeClass();
        // Insert code here to be executed before any tests are run
    }

    /**
     * Add any custom code to be executed once after all tests are executed
     */
    @Override
    @AfterClass(alwaysRun=true)
    public void tearDownAfterClass()
    {
        super.tearDownAfterClass();
        // Insert code here to be executed after tests are complete
        //super.closeAll();
    }

    /**
     * Add any custom code to be executed before each tests are executed
     */
    @Override
    @BeforeMethod(alwaysRun=true)
    public void setUp()
    {
        super.setUp();
    }

    /**
     * Add any custom code to be executed after each tests are executed
     */
    @Override
    @AfterMethod(alwaysRun=true)
    public void tearDown()
    {
        super.tearDown();
    }

    /**
     * Unit test Constructor with no arguments
     */
    public UserEntityTest()
    {
        super();
        // Public no arg constructor
        this.entityName = "User";
        this.table = "USERS";
    }

    /**
     * Unit test Constructor with test case name
     * @param name test case name
     */
    public UserEntityTest(final String name)
    {
        super(name);
        this.entityName = "User";
        this.table = "USERS";
    }
}