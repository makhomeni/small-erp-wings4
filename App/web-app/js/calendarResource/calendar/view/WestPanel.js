
	
Ext.define('Ext.ux.calendar.WestPanel', {
	
    extend : 'Ext.Panel',
    
    title: 'Powered by JabaIT',
    
    iconCls: 'icon-cubedrive',
        
    cls : 'x-calendar-west',
    
    collapsible : true,
    
    region : 'west',
    
    minWidth : 200,
    
    width : 200,    
    
    style: 'padding:5px;',
        
    bodyStyle: 'border:none',
    
    layout : 'border',    
    
    initComponent : function() {
        var eh = this.ehandler;
        eh.applyCalendarSetting(this);
        this.ds = eh.ds;
        var lan = Ext.ux.calendar.Mask.WestPanel;
        this.datePicker =Ext.create('Ext.util.DatePicker',{
        	region: 'north',
        	style:'border:1px solid #a3bad9;',
        	cls:'x-west-datepicker',
            value : new Date(),
            startDay : this.startDay
        });
        this.poweredBy = "http://www.jabait.com";
        /*
			 * for my calendar panel
			 */
        /*=================Begin:No share function==================*/
        this.showAllBtn =Ext.create('Ext.Button',{ 
            iconCls : 'icon_feyaCalendar_calendar_show',
            text : lan['myShowAllBtn.text'],
            handler : this.onShowAllFn,
            scope : this
        });
	
        this.addBtn =Ext.create('Ext.Button',{
            iconCls : 'icon_feyaCalendar_add_calendar',
            text : lan['myAddBtn.text'],
            handler : this.onAddFn,
            scope : this
        });
        var bbar = [this.showAllBtn, '->'];
        if (!this.readOnly) {
            bbar.push(this.addBtn);
        }
        this.myCalendarPanel =Ext.create('Ext.Panel',{
            region : 'center',
            iconCls : 'icon_feyaCalendar_calendar',
            title : lan['myCalendarPanel.title'],
            style: 'padding-top:5px;',
            bodyStyle : 'padding:2px;background-color:white;overflow-x:hidden;position:relative;',            
            autoScroll : true,
            bbar : bbar
        });
        this.myCalendarPanel.on('render', this.onMyCalendarRenderFn, this);
	        
        this.items = [this.datePicker, this.myCalendarPanel];
        
        this.callParent(arguments);
        
        this.addEvents('changedate');
        this.datePicker.on('aferinitevent', this.afterDatePickerInitFn, this);
        this.datePicker.on('select', this.onSelectFn, this);
        this.on('changedate', this.changeDateLabel, this);
        this.on('afterrender', this.onRenderFn, this);        
    },
    
    onOtherCalendarRenderFn : function(p) {
        var eh = this.ehandler;
        eh.renderSharedCalendar(p.body);
    },
	
    onRenderFn : function(p) {
        p.header.on('click', this.onHeaderClickFn, this);
        p.header.addCls('x-panel-header-default-custom');
    },
	
    onHeaderClickFn : function(e) {
        var target = e.getTarget();
        var uxhasClass=Ext.ux.calendar.Mask.includeIsClass;
        if(uxhasClass(target.className,'x-panel-header-text')){
            window.open(this.poweredBy, '_blank');
        }
    },
	
    onShowAllFn : function() {
        this.ehandler.onShowAllFn();
    },
	
    onAddFn : function() {
        this.ehandler.ceditor.popup({
            action : 'add'
        });
    },
	
    onSelectFn : function(dp, date) {
        var calendarContainer = this.ownerCt.calendarContainer;
        calendarContainer.showDay(date);
    },
	
    onMyCalendarRenderFn : function(p) {
        var eh = this.ehandler;
        eh.renderOwnedCalendar(p.body);
    },
	
    changeDateLabel : function(fromDate, toDate) {
        this.updateDatePicker(fromDate, toDate);
    },
	
    updateDatePicker : function(fromDate, toDate) {
        if (fromDate && toDate) {
            var vDate = Ext.Date;
            var from = vDate.format(fromDate, this.fromtoFormat);
            this.datePicker.setRange(fromDate, toDate);
            var dnum = Ext.ux.calendar.Mask.getDayOffset(fromDate, toDate);
            if (7 < dnum) {
                var fd = vDate.getFirstDateOfMonth(fromDate);
                var fday = vDate.format(fd, 'Y-m-d');
                from = vDate.format(fromDate, 'Y-m-d');
                if (from != fday) {
                    fd = vDate.add(vDate.getLastDateOfMonth(fromDate),
                        Ext.Date.DAY, 1);
                }
                this.datePicker.setValue(fd);
            } else {
            	this.datePicker.setValue(fromDate);
            }
        }
    },
	
    afterDatePickerInitFn : function() {
        var cview = this.ownerCt.calendarContainer.currentView;
        this.updateDatePicker(cview.daySet[0], cview.daySet[cview.daySet.length - 1]);
    }
});