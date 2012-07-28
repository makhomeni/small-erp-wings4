<%--
  Created by IntelliJ IDEA.
  User: MASUM
  Date: 5/12/12
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page import="com.jabait.security.SecurityService" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta name="layout" content="page"/>
		<title><g:layoutTitle/></title>

		<!-- Link shortcut icon-->
		<link rel="shortcut icon" type="image/ico" href="${resource(dir: 'images', file: 'favicon.ico')}"/>



		<link rel="stylesheet" href="${resource(plugin: 'extjs', file: 'ext/resources/css/ext-all.css')}"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'xtheme-jabait-theme.css')}"/>

		<link rel="stylesheet" href="${resource(dir: 'css', file: 'breadcrumb.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'misc.style.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'accordionmenu.css')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.property.css')}">
		<g:javascript library="jquery" plugin="jquery"/>
		<script src="${resource(dir: 'js', file: 'highcharts.src.js')}" type="text/javascript"></script>




		<script src="${resource(plugin: 'extjs', file: 'ext/adapter/ext/ext-base-debug.js')}" type="text/javascript"></script>
		<script src="${resource(plugin: 'extjs', file: 'ext/ext-all-debug.js')}" type="text/javascript"></script>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'Ext.ux.form.LovCombo.css')}"/>
		<script src="${resource(dir: 'js', file: 'Ext.ux.form.LovCombo.js')}" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'fixedHeader.css')}">
		<!--[if lte IE 8]>
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'fixedHeaderie6.css')}">
		<![endif]-->
		<link  rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'zice.style.css')}">
		<link  rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'icon.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'ui-custom.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'colorpicker.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'elfinder.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'ui-custom.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'dataTables.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'validationEngine.jquery.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jscrollpane.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'tipsy.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jquery.cleditor.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'chosen.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'jquery.confirm.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'sourcerer.css')}">
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'grid.css')}">


		<!--[if lte IE 8]><script type="text/javascript" src="${resource(dir: 'js', file: 'excanvas.min.js')}" ></script><![endif]-->
		<jqui:resources />
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.autotab.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'colorpicker.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'iphone.check.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'elfinder.full.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'dataTables.min.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'scrolltopcontrol.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'mousewheel.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'mwheelIntent.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jscrollpane.min.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'ui.spinner.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.tipsy.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.cleditor.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'dataTables.min.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'chosen.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.confirm.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.validationEngine-en.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.validationEngine.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'fullcalendar.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'flot.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'flot.resize.min.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'graphtable.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'swfobject.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'uploadify.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'customInput.jquery.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-jrumble.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.filestyle.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.placeholder.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.Jcrop.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.transform.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'webcam.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'rating_star.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'dualListBox.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.smartWizard.min.js')}" ></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.cookie.js')}" ></script>


        <link rel="stylesheet" href="${resource(dir: 'css', file: 'apps.css')}"/>

		<script type="text/javascript">


            var scrolltotop={
                //startline: Integer. Number of pixels from top of doc scrollbar is scrolled before showing control
                //scrollto: Keyword (Integer, or "Scroll_to_Element_ID"). How far to scroll document up when control is clicked on (0=top).
                setting: {startline:200, scrollto: 0, scrollduration:500, fadeduration:[500, 100]},
                controlHTML: '<img src="${resource(dir:'images', file: 'totop.png')}" style="width:21px; height:21px" />', //HTML for control, which is auto wrapped in DIV w/ ID="topcontrol"
                controlattrs: {offsetx:0, offsety:0}, //offset of control relative to right/ bottom of window corner
                anchorkeyword: '#top', //Enter href value of HTML anchors on the page that should also act as "Scroll Up" links

                state: {isvisible:false, shouldvisible:false},

                scrollup:function(){
                    if (!this.cssfixedsupport) //if control is positioned using JavaScript
                        this.$control.css({opacity:0}) //hide control immediately after clicking it
                    var dest=isNaN(this.setting.scrollto)? this.setting.scrollto : parseInt(this.setting.scrollto)
                    if (typeof dest=="string" && jQuery('#'+dest).length==1) //check element set by string exists
                        dest=jQuery('#'+dest).offset().top
                    else
                        dest=0
                    this.$body.animate({scrollTop: dest}, this.setting.scrollduration);
                },

                keepfixed:function(){
                    var $window=jQuery(window)
                    var controlx=$window.scrollLeft() + $window.width() - this.$control.width() - this.controlattrs.offsetx
                    var controly=$window.scrollTop() + $window.height() - this.$control.height() - this.controlattrs.offsety
                    this.$control.css({left:controlx+'px', top:controly+'px'})
                },

                togglecontrol:function(){
                    var scrolltop=jQuery(window).scrollTop()
                    if (!this.cssfixedsupport)
                        this.keepfixed()
                    this.state.shouldvisible=(scrolltop>=this.setting.startline)? true : false
                    if (this.state.shouldvisible && !this.state.isvisible){
                        this.$control.stop().animate({opacity:1}, this.setting.fadeduration[0])
                        this.state.isvisible=true
                    }
                    else if (this.state.shouldvisible==false && this.state.isvisible){
                        this.$control.stop().animate({opacity:0}, this.setting.fadeduration[1])
                        this.state.isvisible=false
                    }
                },

                init:function(){
                    jQuery(document).ready(function($){
                        var mainobj=scrolltotop
                        var iebrws=document.all
                        mainobj.cssfixedsupport=!iebrws || iebrws && document.compatMode=="CSS1Compat" && window.XMLHttpRequest //not IE or IE7+ browsers in standards mode
                        mainobj.$body=(window.opera)? (document.compatMode=="CSS1Compat"? $('html') : $('body')) : $('html,body')
                        mainobj.$control=$('<div id="topcontrol">'+mainobj.controlHTML+'</div>')
                            /*.css({position:mainobj.cssfixedsupport? 'fixed' : 'absolute', bottom:mainobj.controlattrs.offsety, right:mainobj.controlattrs.offsetx, opacity:0, cursor:'pointer'})*/
                                .css({position:mainobj.cssfixedsupport? 'fixed' : 'absolute', bottom:8, right:10, opacity:0, cursor:'pointer','z-index':'100003' })
                                .attr({title:'Click To Top'})
                                .click(function(){mainobj.scrollup(); return false})
                                .appendTo('body')
                        if (document.all && !window.XMLHttpRequest && mainobj.$control.text()!='') //loose check for IE6 and below, plus whether control contains any text
                            mainobj.$control.css({width:mainobj.$control.width()}) //IE6- seems to require an explicit width on a DIV containing text
                        mainobj.togglecontrol()
                        $('a[href="' + mainobj.anchorkeyword +'"]').click(function(){
                            mainobj.scrollup()
                            return false
                        })
                        $(window).bind('scroll resize', function(e){
                            mainobj.togglecontrol()
                        })
                    })
                }
            }

            scrolltotop.init()
            $(document).ready(function(){
                $(".quickIcon").bind("mouseover", function(){
                    $(this).removeClass("sendBack")
                    $(this).addClass("sendTopAnim")
                })

                $(".quickIcon").bind("mouseout", function(){
                    $(this).removeClass("sendTopAnim")
                    $(this).addClass("sendBack")
                })

                $(".setting").bind("mouseover", function(){
                    $(".gear").addClass("gearhover")
                })

                $(".setting").bind("mouseout", function(){
                    $(".gear").removeClass("gearhover")
                })

                $(".logout").bind("mouseover", function(){
                    $(".disconnect").attr("src", "${resource(dir:'images', file: 'disconnect.png')}");
                })

                $(".logout").bind("mouseout", function(){
                    $(".disconnect").attr("src", "${resource(dir:'images', file: 'connect.png')}");
                })
            })

		</script>


		<g:layoutHead/>



	</head>
	<body class="dashborad">
		<div id="alertMessage" class="error"></div>
		<!-- Header -->
		<div id="header-wrap">
			<div id="header-container">
				<div id="header" style="text-align: right">
					<div id="account_info">
						%{--<img src="${resource(dir: 'images', file:'avatar.png')}" alt="Online" class="avatar"/>--}%
						<div class="setting" title="Profile Setting"><b>Welcome, </b> <b class="red">${session?.user?.userCode}</b><img src="${resource(dir: 'images', file:'gear.png')}" class="gear"  alt="Profile Setting" ></div>
                        %{--<img src="${resource(dir: 'images', file:'avatar.png')}" alt="Online" class="avatar"/>--}%
						<g:link class="logout" title="Disconnect" controller="security" action="logout"><b >Logout</b> <img src="${resource(dir: 'images', file:'connect.png')}" name="connect" class="disconnect" alt="disconnect" ></g:link>
					</div>
				</div>
			</div>
		</div>

		<div id="shadowhead"></div>
		<g:set var="classNameDashBoard" value="limenu"></g:set>
		<g:set var="classNameAcl" value="limenu"></g:set>
		<g:set var="classNameHRM" value="limenu"></g:set>
		<g:set var="classNameConfiguration" value="limenu"></g:set>
		<g:set var="classNameSCM" value="limenu"></g:set>
		<g:if test="${params.controller=='application' && params.action == 'index'}">
			  ${classNameDashBoard = "limenu select" }
		</g:if>

		<g:if test="${params.controller=='security' && (params.action == 'userList' || params.action == 'authorityList'
                || params.action == 'featureList') || params.action == 'userGroupList' || params.action == 'createUser'||
                params.action == 'createFeature'|| params.action == 'createAuthority' || params.action == 'createUserGroup' ||
                 params.action == 'editUser' || params.action == 'editUserGroup' || params.action == 'editFeature'|| params.action == 'editAuthority'||
                params.action == 'showUserDetails' || params.action == 'showAuthorityDetails'}">
			${classNameAcl= "limenu select" }
		</g:if>

        <g:if test="${params.controller == 'scm' ||
                params.controller == 'inventory' || params.controller == 'job'}">
            ${classNameSCM = "limenu select"}
        </g:if>

		<g:if test="${(params.controller == 'attendance' && params.action == 'attendanceList') ||
                params.controller=='payroll' || (
                params.controller=='security' ||
                params.controller=='employee' ||
                params.controller == 'calendar' || params.controller == 'leave') && (params.action == 'createEmployee' ||
                params.action == 'calenderList' || params.action == 'employeeList' ||
                params.action == 'systemCalendar' || params.action == 'organizationCalendar' ||
                params.action == 'employeeCalendar' ||  params.action == 'calendarHomeConfig' ||  params.action == 'leave'
                || params.action == 'leaveList' || params.action == 'leaveRequest' || params.action == 'waiverList'
                || params.action == 'waiverRequest')}">
			${classNameHRM = "limenu select" }
		</g:if>

		<g:if test="${params.controller=='configuration' ||
                (params.controller=='attendance' && (params.action == 'lateThresholdList' || params.action == 'attendanceAdjustmentList')) }">
			${classNameConfiguration = "limenu select" }
		</g:if>

		<div id="left_menu">
			<g:set var="securityService" value="${new SecurityService()}"></g:set>
			<ul id="main_menu" class="main_menu">
				<li class="${classNameDashBoard}"><g:link controller="application" action="index"><span class="ico gray shadow home" ></span><b>Dashboard</b></g:link></li>
				<li class="${classNameAcl}" ><a href="#" ><span class="ico gray shadow window"></span><b>Access Control List</b></a>
					<g:if test="${securityService.isAllowedForAcl(session)}">
						<ul>
							<g:if test="${securityService.isAllowedForUser(session)}">
								<li><g:link controller="security" action="userList" class="${htmlClass}" >User</g:link></li>
							</g:if>
							<g:if test="${securityService.isAllowedForAuthority(session)}">
								<li><g:link controller="security" action="authorityList" class="${htmlClass}">Authority</g:link></li>
							</g:if>
							<g:if test="${securityService.isAllowedForFeature(session)}">
								<li><g:link controller="security" action="featureList" class="${htmlClass}">Feature</g:link></li>
							</g:if>
							<g:if test="${securityService.isAllowedForUserGroup(session)}">
								<li><g:link controller="security" action="userGroupList" class="${htmlClass}">User Group</g:link></li>
							</g:if>

						</ul>
					</g:if>
				</li>

				<li class="${classNameHRM}" ><a href="#"><span class="ico gray  dimensions" ></span><b>HRM</b></a>
					<ul>
						<g:if test="${securityService.isAllowedForEmployee(session)}">
                            <li><g:link controller="employee" action="employeeList" class="${htmlClass}">Employee</g:link></li>

						</g:if>
						<g:if test="${securityService.isAllowedForCalender(session)}">
							<li><g:link controller="calendar" action="calendarHomeConfig" class="${htmlClass}">Calender</g:link></li>
						</g:if>
						<g:if test="${securityService.isAllowedForLeaveManagement(session)}">
							<li><g:link controller="leave" action="leave" class="${htmlClass}">Leave</g:link></li>
						</g:if>

                        <g:if test="${securityService.isAllowedForLeaveManagement(session)}">
                            <li><g:link controller="attendance" action="attendanceList" class="${htmlClass}">Attendance</g:link></li>
                        </g:if>

                        %{--<g:if test="${securityService.isAllowedForLeaveManagement(session)}">--}%
                            %{--<li><g:link controller="security" action="leavemanagementList" class="${htmlClass}">Recruitment</g:link></li>--}%
                        %{--</g:if>--}%
                        %{--<g:if test="${securityService.isAllowedForLeaveManagement(session)}">--}%
                            %{--<li><g:link controller="security" action="leavemanagementList" class="${htmlClass}">Training</g:link></li>--}%
                        %{--</g:if>--}%
                        %{--<g:if test="${securityService.isAllowedForLeaveManagement(session)}">--}%
                            %{--<li><g:link controller="security" action="leavemanagementList" class="${htmlClass}">Performance</g:link></li>--}%
                        %{--</g:if>--}%
                        <g:if test="${securityService.isAllowedForLeaveManagement(session)}">
                            <li><g:link controller="payroll" action="home" class="${htmlClass}">Payroll</g:link></li>
                        </g:if>
					</ul>
				</li>
                <li class="${classNameSCM}" ><g:link controller="scm" action="home"><span class="ico gray chain" ></span><b>SCM</b></g:link>
                    <ul>
                        <li><g:link controller="inventory" action="home" class="${htmlClass}">Inventory</g:link></li>
                        <li><g:link controller="job" action="home" class="${htmlClass}">Job</g:link></li>
                        <li><g:link controller="mrp" action="home" class="${htmlClass}">MRP</g:link></li>
                        <li><g:link controller="procurement" action="home" class="${htmlClass}">Procurement</g:link></li>
                    </ul>
                </li>
				<g:if test="${securityService.isAllowedForConfiguration(session)}">
                    <li class="${classNameConfiguration}" ><g:link controller="configuration"><span class="ico gray configuration" ></span><b>Configuration</b></g:link>
                </g:if>
                    <ul>
                       <li><g:link controller="configuration" action="attendanceConfigHome" class="${htmlClass}">Attendance</g:link></li>
                        <li><g:link controller="configuration" action="jobConfigHome" class="${htmlClass}">Job</g:link></li>
                       <li><g:link controller="configuration" action="leaveList" class="${htmlClass}">Leave</g:link></li>
                        <li><g:link controller="configuration" action="organizationConfigHome" class="${htmlClass}">Organization</g:link></li>
                       <li><g:link controller="configuration" action="payrollConfigHome" class="${htmlClass}">Payroll</g:link></li>

                    </ul>

				    </li>
                <li class="${classNameDMS}" ><g:link controller="configuration" action="index"><span class="ico gray document" ></span><b>DMS</b></g:link>
                <li class="${classNameFinance}" ><g:link controller="configuration" action="index"><span class="ico gray money" ></span><b>Finance</b></g:link>
                <li class="${classNameTask}" ><g:link controller="configuration" action="index"><span class="ico gray tasks" ></span><b>Task Management</b></g:link>
                <li class="${classNameCTS}" ><g:link controller="configuration" action="index"><span class="ico gray cts" ></span><b>CTS</b></g:link>
                <li class="${classNameMIS}" ><g:link controller="configuration" action="index"><span class="ico gray mis" ></span><b>MIS</b></g:link>
                <li class="${classNamePayroll}" ><g:link controller="configuration" action="index"><span class="ico gray salary" ></span><b>Payroll Management</b></g:link>
                <li class="${classNameDocumentLocator}" ><g:link controller="configuration" action="index"><span class="ico gray locator" ></span><b>Document Locator</b></g:link>
                <li class="${classNameCRM}" ><g:link controller="configuration" action="index"><span class="ico gray crm" ></span><b>CRM</b></g:link>
			</ul>
		</div>

		<div id="content">
			<div class="inner">
				<div class="topcolumn">
					<div class="logo"></div>
					<ul  id="shortcut">
						<li> <g:link controller="application" action="index" class="quickIcon"> <img src= "${resource(dir: 'images', file: 'house.png')}" alt="home"/><strong>Home</strong> </g:link> </li>

						<li> <g:link controller="security" action="userList" class="quickIcon"> <img src=" ${resource(dir: 'images', file: 'user_icon_resi.png')}" alt="userList"/><strong>User</strong> </g:link> </li>

						<li> <a href="#" title="Setting" class="quickIcon"> <img src=" ${resource(dir: 'images', file: 'setting.png')}" alt="setting" /><strong>Setting</strong></a> </li>
					</ul>
				</div>
				<div class="clear"></div>

				<div class="mainContainer">
                    <g:pageProperty name="page.rightTag"/>

				</div><!-- End full width -->
			</div><!-- End column_right -->

			<!-- clear fix -->
			<div class="clear"></div>
		</div> <!--// End inner -->

    <div id="footer-wrap">
        <div id="footer-container">
            <div id="footer" style="text-align: center">
                <div id="version">
                    <div class="copyright"> © Copyright 2012  All Rights Reserved <span class="tip"><a href="#" original-title="Ocean Group">OceanGroup</a> </span> </div>
                </div>
            </div>
        </div>
    </div>

	</body>
</html>