// license-header java merge-point
package org.andromda.timetracker.web.timecardsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.andromda.timetracker.domain.TimecardStatus;
import org.andromda.timetracker.vo.TimecardSearchCriteriaVO;
import org.andromda.timetracker.vo.TimecardSummaryVO;
import org.andromda.timetracker.vo.UserVO;
import org.andromda.timetracker.vo.UserVOComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

/**
 * @see org.andromda.timetracker.web.timecardsearch.SearchController
 */
public class SearchControllerImpl extends SearchController
{
    /**
     * 
     */
    private static final long serialVersionUID = -6874748814240465937L;
    private Log logger = LogFactory.getLog(SearchControllerImpl.class);
    private static final String ALL_STRING = "-- All --";

    /**
     * @see org.andromda.timetracker.web.timecardsearch.SearchController#populateSearchScreen(org.apache.struts.action.ActionMapping, PopulateSearchScreenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public final void populateSearchScreen(ActionMapping mapping, PopulateSearchScreenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (logger.isDebugEnabled()) {
            logger.debug("form: " + form);
        }

        // Get list of users and add the "All" option at the top
        UserVO[] users = getUserService().getAllUsers();
        Arrays.sort(users, new UserVOComparator());
        //List<UserVO> users = new ArrayList<UserVO>(getUserService().getAllUsers());
        //Collections.sort(users, new UserVOComparator());
        List userList = new ArrayList(Arrays.asList(users));
        userList.add(0, new UserVO(null, ALL_STRING, null, null));

        // Populate submitter and approver dropdowns
        form.setSubmitterBackingList(userList, "id", "username");
        form.setApproverBackingList(userList, "id", "username");

        // Populate status dropdown
        List statusLabels = new ArrayList(TimecardStatus.literals());
        List statusValues = new ArrayList(TimecardStatus.literals());
        statusLabels.add(0, ALL_STRING);
        statusValues.add(0, "");
        form.setStatusLabelList(statusLabels.toArray());
        form.setStatusValueList(statusValues.toArray());

        // Populate timecard summaries
        TimecardStatus status = null;
        if (StringUtils.isNotBlank(form.getStatus())) {
            status = TimecardStatus.fromString(form.getStatus());
        }
        TimecardSearchCriteriaVO criteria = new TimecardSearchCriteriaVO(
                form.getSubmitter(),
                form.getApprover(),
                status,
                form.getStartDateMinimumAsDate(),
                form.getStartDateMaximumAsDate());

        TimecardSummaryVO[] timecards = getTimeTrackingService().findTimecards(criteria);
        //Collection<? extends TimecardSummaryVO> timecards = getTimeTrackingService().findTimecards(criteria);
        form.setTimecardSummaries(timecards);
        //form.setTimecardSummaries((TimecardSummaryVO[]) timecards.toArray());
    }

    public final void initializeTimecardId(ActionMapping mapping, InitializeTimecardIdForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        form.setTimecardId(form.getId());
    }
}