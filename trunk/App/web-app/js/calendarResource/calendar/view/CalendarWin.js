
Ext.define('Ext.ux.calendar.CalendarWin', {
	
    extend : 'Ext.Window',
    
	initComponent: function(){        
		 this.mainPanel =Ext.create('Ext.ux.calendar.MainPanel',{
        	border: false,
        	datasource:this.datasource,
            calendarSetting:this.calendarSetting,
            userId:this.userId
        });
        Ext.apply(this,  {
        	title : 'Calendar  [' + Ext.ux.calendar.CONST.VERSION + ']',
		    iconCls : 'icon_feyaCalendar_calendar',		    
		    style:'padding:0px;margin:0px;',
            layout:'fit',
            width:(Ext.getBody()).getWidth(),
            height:(Ext.getBody()).getHeight(),
            items:[this.mainPanel]
        })
        this.callParent(arguments);
    } 
});