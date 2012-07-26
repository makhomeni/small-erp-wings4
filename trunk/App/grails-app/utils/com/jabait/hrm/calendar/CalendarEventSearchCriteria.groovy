package com.jabait.hrm.calendar

import com.jabait.hrm.Employee

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 6/6/12
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
class CalendarEventSearchCriteria {

    Employee author
    boolean flag
    Date startTime
    Date endTime
    CalendarType calendarType
    String subject = ''
    String description = ''
    String dragDay
    String dropDay
    Integer start
    Integer limit
    String sort
    String dir
}
