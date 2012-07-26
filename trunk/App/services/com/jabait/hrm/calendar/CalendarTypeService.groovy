package com.jabait.hrm.calendar

import com.jabait.hrm.Organization

class CalendarTypeService {

    def calendarEventService
    boolean transactional = true

    // check to see whether the name already exist in system for this user ...
    def isDuplicateName(organizationId, name){
        Long organizationIdLong = Long.parseLong(organizationId)
        def result = true
        // get all name by me
        def calendarLists = CalendarType.withCriteria{
            and{
                eq("organization.id", organizationIdLong)
                eq("name", name)
            }
        }

        if (calendarLists.size() == 0) {
            result = false
        }

        return result
    }

    def getListOfCalendarType(loginUser,organization){
        def calendars = CalendarShare.withCriteria{
            eq("organization.id", Long.parseLong(1.toString()))
        }
        // ok add condition if calendarLists is null, add one new calendar
        if (null == calendars || calendars.size() == 0) {

            def defaultCalendar = new CalendarType(
                    name: "late",
                    organization: organization
            )
//            defaultCalendar.save()
            def calendarShare = new CalendarShare(
                    calendarType:defaultCalendar,
                    organization: organization
            )
            calendarShare.save()
            calendars = CalendarShare.withCriteria{
                and{
                    eq("organization.id", Long.parseLong(1.toString()))
                }
            }
        }
        def ownedCalendarList = []
        calendars.each{
            def ct = it.calendarType
            def shares = CalendarShare.withCriteria{
                and{
                    eq("calendarType.id", ct.id)

                }
            }
            def userList = []
            shares.each{
                def userUI = new UIUser(
                        id:new Long(1),
                        username:Organization.get(1),
                        userRealName:Organization.get(1).organizationName,
                        enabled:true,
                        email:Organization.get(1).email.address
                )
                userList.add(userUI)
            }
            def calendarTypeUI = new CalendarTypeUIModel(
                    id:it.calendarType.id,
                    name:it.calendarType.name,
                    description:it.calendarType.description,
                    color:it.calendarType.color,
                    hide:it.calendarType.hide,
                    isShared:false,
                    permit:it.permit,
                    shares:userList
            )
            ownedCalendarList.add(calendarTypeUI)
        }

        calendars = CalendarShare.withCriteria{
            eq("organization.id", Long.parseLong(1.toString()))
        }

        def sharedCalendarList = []
        calendars.each{
            def ct = it.calendarType
            def shares = CalendarShare.withCriteria{
                and{
                    eq("calendarType.id", ct.id)
                }
            }
            def userList = []
            shares.each{
                def userUI = new UIUser(
                        id:new Long(1),
                        username:Organization.get(1),
                        userRealName:Organization.get(1).organizationName,
                        enabled:true,
                        email:Organization.get(1).email.address
                )
                userList.add(userUI)
            }
            def calendarTypeUI = new CalendarTypeUIModel(
                    id:it.calendarType.id,
                    name:it.calendarType.name,
                    description:it.calendarType.description,
                    color:it.calendarType.color,
                    hide:it.calendarType.hide,
                    isShared:true,
                    permit:it.permit,
                    shares:userList
            )
            sharedCalendarList.add(calendarTypeUI)
        }
        //return a bunch of json data with metadata.
        def json = [
                total: ownedCalendarList.size()+sharedCalendarList.size(),
                owned: ownedCalendarList,
                shared: sharedCalendarList
        ]
        calendars = null
        ownedCalendarList = null
        sharedCalendarList = null

        return json
    }

    def showAllCalendarType(loginUser){
        def json = [success : "true", info : "You have success show all calendars"]
        def calendars = CalendarShare.withCriteria{
            and{
                eq("organization.id", Long.parseLong(1.toString()))
            }
        }
        calendars.each {
            it.calendarType.hide = false
            it.calendarType.save()
        }
        calendars = null

        return json
    }

    def showOnlyCalendarType(){
        def json
        def item = CalendarType.get(1)
        if (item) {
            item.hide = false
            def calendars = CalendarShare.withCriteria{
                and{
                    or{
                        eq("organization.id",Long.parseLong(1.toString()))
                    }
                    ne("calendarType.id", new Long(1))
                }
            }
            calendars.each{
                it.calendarType.hide = true
                it.save()
            }
        }
        if(!item.hasErrors() && item.save()) {
            json = [success : "true", info : "You have success show this calendar", id : item.id]
        } else {
            def errors = "Error is: "
            item.errors.allErrors.each {
                errors = errors + it
            }
            json = [success : "false", errorInfo: errors]
        }

        return json
    }

    def creatUpdateCalendarType(params){
        def json
        boolean isNew = false
        def item = null
        println "params = $params"
        if(null != params.id){
            item = CalendarType.get(params.id)
        }
        if (item) {
            println "item = $item.organization"
            if ("true" == params.hide)  {
                item.hide = true
            } else {
                item.hide = false
            }
            item.name = params.name
            if (params.description) {
                item.description = params.description
            }
            if (params.color) {
                item.color = params.color
            }
        } else {
            if (isDuplicateName(params.userId, params.name)) {
                json = [success : "false", errorInfo : "This calendar name has already been used, please select another one"]
                return json
            }
            if ("true" == params.hide) {
                params.hide = true
            } else {
                params.hide = false
            }
            item = new CalendarType()
            item.hide = params.hide;
            item.color = params.color;
            item.description = params.description;
            item.organization = Organization.get(params.userId);
            item.name = params.name;

            item.save();

            def calendarShare = new CalendarShare(
                    calendarType:item,
                    organization:  Organization.get(1)
            )
            calendarShare.save()
            isNew = true
        }
        if(!item.hasErrors() && item.save()) {
            json = [success : "true", info : "You have success creat/update this calendar", id : item.id]
        } else {
            def errors = "Error is: "
            item.errors.allErrors.each {
                errors = errors + it
            }
            json = [success : "false", errorInfo: errors]
        }
        item = null

        return json
    }

    def deleteCalendarType(params){
        def json = [success : "true", info : "You have success delete this calendar"]
        def item = CalendarType.get(params.id)
        if (item) {
            // delete all the calendar event related to this calendarType
            def events = CalendarEvent.withCriteria{
                and{
                    eq("calendarType.id", new Long(params.id))
                }
            }

            // delete stuff ...
            calendarEventService.deleteEvents(events)

            events = null
            def calendars = CalendarShare.withCriteria{
                eq("calendarType.id", item.id)
            }
            calendars.each {
                it.delete()
            }
            calendars = null
            // delete this calendar type
            item.delete();
        }
        return json
    }

    def shareCalendarType(params){
        def cs = new CalendarShare(
                calendarType:params.calendarType,
                organization: Organization.get(1)
        )
        def json = [success : "true", info : "You have success share this calendar", id:cs.id]
    }
}
