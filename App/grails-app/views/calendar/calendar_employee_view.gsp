<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/7/12
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>

    <link rel="stylesheet" href="${createLinkTo(dir: 'js', file: 'extjs/resources/css/ext-all.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'xtheme-jabait-theme.css')}"/>

    <link rel="stylesheet" href="${createLinkTo(dir: 'js/calendarResource/calendar/css',file:'calendar.css')}" />
    <script src="${createLinkTo(dir: 'js', file: 'extjs/adapter/ext/ext-base-debug.js')}" type="text/javascript"></script>
    <script src="${createLinkTo(dir: 'js', file: 'extjs/ext-all-debug.js')}" type="text/javascript"></script>

    <g:javascript src="calendarResource/home/program/calendar/util/LabelField.js"/>
    <g:javascript src="calendarResource/home/program/calendar/util/DatePicker.js"/>
    <g:javascript src="calendarResource/home/program/calendar/util/SearchField.js"/>

    <g:javascript src="calendarResource/home/program/calendar/common/CONST_GRAILS.js"/>

    <g:javascript src="calendarResource/home/program/calendar/common/LanManager.js"/>
    <g:javascript src="calendarResource/home/program/calendar/common/Mask.js"/>
    <g:javascript src="calendarResource/home/program/calendar/common/RepeatType.js"/>

    <g:javascript src="calendarResource/home/program/calendar/CommentTip.js"/>
    <g:javascript src="calendarResource/home/program/calendar/BasicView.js"/>
    <g:javascript src="calendarResource/home/program/calendar/layout/Block.js"/>
    <g:javascript src="calendarResource/home/program/calendar/layout/BlockMap.js"/>
    <g:javascript src="calendarResource/home/program/calendar/layout/Line.js"/>
    <g:javascript src="calendarResource/home/program/calendar/layout/LayoutGrid.js"/>
    <g:javascript src="calendarResource/home/program/calendar/layout/CalendarLayout.js"/>
    <g:javascript src="calendarResource/home/program/calendar/ExpirePopup.js"/>
    <g:javascript src="calendarResource/home/program/calendar/SettingPopup.js"/>
    <g:javascript src="calendarResource/home/program/calendar/BackThread.js"/>
    <g:javascript src="calendarResource/home/program/calendar/DataSource.js"/>
    <g:javascript src="calendarResource/home/program/calendar/Editor.js"/>
    <g:javascript src="calendarResource/home/program/calendar/DetailEditor.js"/>
    <g:javascript src="calendarResource/home/program/calendar/CalendarEditor.js"/>


    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'EventHandler.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'DayView.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'MonthView.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'ResultView.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'CalendarContainer.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'WestPanel.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'MainPanel.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'CalendarWin.js' )}"></script>
    <script type="text/javascript" src="${resource(dir: 'js/calendarResource/home/program/calendar', file: 'Viewer-tabpanel.js' )}"></script>

</head>
<body>
    <div id="calendar-container"></div>
</body>
</html>