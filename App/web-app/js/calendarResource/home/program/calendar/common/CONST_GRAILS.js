
Ext.ns("Ext.ux.calendar");

Ext.ux.calendar.CONST = {
    /*
     * The version number of myCalendar
     */
    VERSION:'2.0.5',
    /*
     *true to show the language submenu in myCalendar, or not
     *
     */
    SHOW_LANGUAGE_MENU:true,
    
    BLANK_IMAGE_URL:'../static/js/extjs/resources/images/default/s.gif',
    /*
     *define the main path of myCalendar
     */
    MAIN_PATH:'js/feyaSoft/home/program/calendar/',
    /*
     *define the multi-language path of myCalendar
     */
    CALENDAR_LANGUAGE_PATH:'js/feyaSoft/home/program/calendar/multi-language/',
    /*
     *define the multi-language path of EXT
     */
    EXT_LANGUAGE_PATH:'js/extjs/src/locale/',
    /*
     * define the some url here for datasource
     */
    searchURL:'../calendarEvent/search',

    showAllCalendarURL:'../calendarType/showAll',

    showOnlyCalendarURL:'../calendarType/showOnly',

    createUpdateCalendarURL:'../calendarType/createUpdate',

    deleteEventsByCalendarURL:'../calendarEvent/deleteByCalendar',

    deleteCalendarURL:'../calendarType/delete',

    loadCalendarURL:'../calendarType/list',

    loadEventURL:'../calendarEvent/list',

    loadRepeatEventURL:'../calendarEvent/loadRepeatEvents',

    createEventURL:'../calendarEvent/createEditEvent',

    updateEventURL:'../calendarEvent/createEditEvent',

    deleteEventURL:'../calendarEvent/deleteEvent',

    deleteRepeatEventURL:'../calendarEvent/deleteRepeatEvent',

    changeDayURL:'../calendarEvent/updateDayEvents',

    deleteDayURL:'../calendarEvent/deleteDayEvents',

    loadSettingURL:'../calendarSetting/list',

    updateSettingURL:'../calendarSetting/update',

    createUpdateRepeatEventURL:'../calendarEvent/createUpdateRepeatEvent',

    initialLoadURL:'load',

    listUserURL:'queryUser'
};