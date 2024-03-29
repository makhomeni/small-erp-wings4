package com.jabait.hrm.calendar

import com.jabait.hrm.Organization

class CalendarSettingService {

    boolean transactional = true

    def getListOfCalendarSetting(loginUser){
        def calendarSetting = CalendarSetting.withCriteria{
            eq("organization.id", Long.parseLong(1.toString()))
        }

        // ok add condition if calendarSetting is null, add one new calendar Setting for this user
        if (null == calendarSetting || calendarSetting.size() == 0) {
            def defaultCalendarSetting = new CalendarSetting(
                    organization: Organization.get(1)
            )
            defaultCalendarSetting.save()
            defaultCalendarSetting = null
            calendarSetting = CalendarSetting.withCriteria{
                and{
                    eq("organization.id", Long.parseLong(1.toString()))
                }
            }
        }
        def calendarSettingList = []
        calendarSetting.each{
            def calendarSettingUI = new CalendarSettingUIModel(
                    id:it.id,
                    hourFormat:it.hourFormat,
                    dayFormat:it.dayFormat,
                    weekFormat:it.weekFormat,
                    monthFormat:it.monthFormat,
                    fromtoFormat:it.fromtoFormat,
                    language:it.language,
                    activeStartTime:it.activeStartTime,
                    activeEndTime:it.activeEndTime,
                    intervalSlot:it.intervalSlot,
                    startDay:it.startDay,
                    createByDblclick:it.createByDblclick,
                    hideInactiveRow:it.hideInactiveRow,
                    singleDay:it.singleDay,
                    readOnly:it.readOnly,
                    initialView:it.initialView
            )
            calendarSettingList.add(calendarSettingUI)
        }
        calendarSetting = null
        //return a bunch of json data with metadata.
        def json = [
                total: calendarSettingList.size(),
                results: calendarSettingList
        ]

        calendarSettingList = null

        return json
    }

    def updateCalendarSetting(params){
        def items = CalendarSetting.withCriteria{
            eq("organization.id", Long.parseLong(1.toString()))
        }
        def json
        items.each{
            if(null != params.hourFormat)
                it.hourFormat = params.hourFormat
            if(null != params.dayFormat)
                it.dayFormat = params.dayFormat
            if(null != params.weekFormat)
                it.weekFormat = params.weekFormat
            if(null != params.monthFormat)
                it.monthFormat = params.monthFormat
            if(null != params.fromtoFormat)
                it.fromtoFormat = params.fromtoFormat
            if(null != params.language)
                it.language = params.language
            if(null != params.createByDblclick){
                if("true" == params.createByDblclick)
                    it.createByDblclick = true
                else
                    it.createByDblclick = false
            }
            if(null != params.singleDay){
                if("true" == params.singleDay)
                    it.singleDay = true
                else
                    it.singleDay = false
            }
            if(null != params.readOnly){
                if("true" == params.readOnly)
                    it.readOnly = true
                else
                    it.readOnly = false
            }
            if(null != params.activeStartTime)
                it.activeStartTime = params.activeStartTime
            if(null != params.activeEndTime)
                it.activeEndTime = params.activeEndTime
            if(null != params.hideInactiveRow){
                if("true" == params.hideInactiveRow)
                    it.hideInactiveRow = true
                else
                    it.hideInactiveRow = false
            }
            if(null != params.intervalSlot)
                it.intervalSlot = params.intervalSlot
            if(null != params.startDay)
                it.startDay = params.startDay
            if(null != params.initialView)
                it.initialView = Integer.parseInt(params.initialView)

            if(!it.hasErrors() && it.save()) {
                json = [success : "true", info : "You have success update the feyaCalendar setting"]
            } else {
                def errors = "Error is: "
                it.errors.allErrors.each {
                    errors = errors + it
                }
                json = [success : "false", errorInfo: errors]
            }
        }
        items = null

        return json
    }
}
