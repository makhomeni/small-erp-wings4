
Ext.ns("Ext.ux.calendar");

Ext.ux.calendar.CONST = {
    /*
     * The version number of myCalendar
     */
    VERSION:'2.6.1',
    /*
     *true to show the language submenu in myCalendar, or not
     *
     */
    SHOW_LANGUAGE_MENU:true,
        
    /*
     *define the main path of myCalendar
     */
    MAIN_PATH:'js/calendarResource/calendar/',
    /*
     *define the multi-language path of myCalendar
     */
    CALENDAR_LANGUAGE_PATH:'js/calendarResource/calendar/multi-language/',
    /*
     *define the multi-language path of EXT
     */
    EXT_LANGUAGE_PATH:'js/extjs/locale/',
    /*
     * define the some url here for datasource
     */
    searchURL:'calendarEvent/search',

    showAllCalendarURL:'calendarType/showAll',

    showOnlyCalendarURL:'calendarType/showOnly',

    createUpdateCalendarURL:'calendarType/createUpdate',

    deleteEventsByCalendarURL:'calendarEvent/deleteByCalendar',

    deleteCalendarURL:'calendarType/delete',

    loadCalendarURL:'calendarType/list',

    loadEventURL:'fakeData/listEvent.json',

    loadRepeatEventURL:'calendarEvent/loadRepeatEvents',

    createEventURL:'calendarEvent/createEditEvent',

    updateEventURL:'calendarEvent/createEditEvent',

    deleteEventURL:'calendarEvent/deleteEvent',

    deleteRepeatEventURL:'calendarEvent/deleteRepeatEvent',

    changeDayURL:'calendarEvent/updateDayEvents',

    deleteDayURL:'calendarEvent/deleteDayEvents',

    loadSettingURL:'calendarSetting/list',

    updateSettingURL:'calendarSetting/update',

    createUpdateRepeatEventURL:'calendarEvent/createUpdateRepeatEvent',

    initialLoadURL:'fakeData/initLoad.json',

    listUserURL:'calendar/queryUser'
};