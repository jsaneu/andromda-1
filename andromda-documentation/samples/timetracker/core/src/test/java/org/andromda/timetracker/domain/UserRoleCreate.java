// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by test/EntityCreate.vsl in andromda-ejb3-cartridge.
//
package org.andromda.timetracker.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
//import org.andromda.dbtest.JPAJUnitAncestor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Autogenerated Entity constructor class for UserRole which creates
 * an Entity instance using dummy/default values for all properties, with a method for
 * minimal properties (only required), all properties, and an update from minimal to update the rest of the properties.
 * It assumes that Entities only have relations to other entities, so those constructors can be used.
 *
 * Autogenerated by AndroMDA ejb3\test\EntityConstructor.vsl on 01/17/2014 23:56:44
 *
 * <p>
 * TODO: Model Documentation for UserRole
 * </p>
 */
public class UserRoleCreate
{
    private static Logger LOGGER = LogManager.getLogger(UserRoleCreate.class);

    /**
     * Create an Entity UserRole with all attributes and associations set
     * @param em EntityManager used within this transaction
     * @return UserRole
     */
    public static UserRole createEntity(EntityManager em)
    {
        UserRole entity = new UserRole();
        // Identifier attributes

        // Updatable property
        entity.setRole(Role.fromValue("StandardUser"));

        return entity;
    }

    /**
     * Create an Entity UserRole with all attributes and associations set
     * @param em EntityManager used to findAll
     * @return UserRole
     */
    public static List<UserRole> findAll(EntityManager em)
    {
        //EntityManager em = JPAJUnitAncestor.createEntityManager();
        TypedQuery<UserRole> query = em.createNamedQuery("UserRole.findAll", UserRole.class);
        List<UserRole> results = query.getResultList();
        return results;
    }

    /**
     * Return a persisted Entity UserRole with all attributes and associations set
     * Used to set persistent association relationships in related entities.
     * @param em EntityManager used to findFirst
     * @return UserRole
     */
    public static UserRole findFirst(EntityManager em)
    {
        UserRole entity = null;
        //EntityManager em = JPAJUnitAncestor.createEntityManager();
        TypedQuery<UserRole> query = em.createNamedQuery("UserRole.findAll", UserRole.class);
        List<UserRole> results = query.getResultList();
        if (!results.isEmpty())
        {
            entity = results.get(0);
        }
        else
        {
            entity = createEntity(em);
            em.persist(entity);
        }
        return entity;
    }
}