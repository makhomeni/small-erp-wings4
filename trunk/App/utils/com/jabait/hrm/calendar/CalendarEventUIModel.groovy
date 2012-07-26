package com.jabait.hrm.calendar

/**
 * Created by IntelliJ IDEA.
 * User: hossaindoula
 * Date: 6/6/12
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
class CalendarEventUIModel {

    String subject
    String description
    String startTime
    String endTime
    String id
    String calendarId
    String color
    String ymd
    String eymd
    String alertFlag
    int alertEarlyTime
    String repeatType
    boolean locked  // true or false - this is for locking events.

}
