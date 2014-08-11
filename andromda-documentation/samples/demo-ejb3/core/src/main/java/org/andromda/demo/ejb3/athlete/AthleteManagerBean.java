// license-header java merge-point
//
// Generated by SessionBeanImpl.vsl in andromda-ejb3-cartridge on 08/06/2014 10:56:22.
// Modify as necessary. If deleted it will be regenerated.
//
package org.andromda.demo.ejb3.athlete;

import java.util.Collection;

/**
 * @see AthleteManagerBase
 *
 * Remember to manually configure the local business interface this bean implements if originally you only
 * defined the remote business interface.  However, this change is automatically reflected in the ejb-jar.xml.
 *
 * Do not specify the javax.ejb.Stateless annotation
 * Instead, the session bean is defined in the ejb-jar.xml descriptor.
 */
// Uncomment to enable webservices for AthleteManagerBean
// @javax.jws.WebService(endpointInterface = "org.andromda.demo.ejb3.athlete.AthleteManagerWSInterface", serviceName = "AthleteManager")
public class AthleteManagerBean
    extends AthleteManagerBase
    implements AthleteManagerRemote
{
    // --------------- Constructors ---------------

    /**
     * Default constructor extending base class default constructor
     */
    public AthleteManagerBean()
    {
        super();
    }

    // -------- Business Methods Impl --------------

    /**
     * @see AthleteManagerBase#addTrackAthlete(TrackAthlete)
     */
    @Override
    protected void handleAddTrackAthlete(TrackAthlete trackAthlete)
        throws Exception
    {
        getTrackAthleteDao().create(trackAthlete);
    }

    /**
     * @see AthleteManagerBase#getAllAthletes()
     */
    @Override
    protected Collection handleGetAllAthletes()
        throws Exception
    {
        return getTrackAthleteDao().loadAll();
    }

    // -------- Lifecycle Callback Implementation --------------
}
