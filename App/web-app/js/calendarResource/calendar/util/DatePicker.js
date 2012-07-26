
Ext.define('Ext.util.DatePicker', {
	extend:'Ext.picker.Date',
    updateRange : function(){
        if(this.startDate && this.endDate){         
            var st = (Ext.Date.clearTime(this.startDate)).getTime();
            var et = (Ext.Date.clearTime(this.endDate)).getTime();
            this.cells.each(function(c){
                var dt = c.dom.firstChild.dateValue;
                if(st <= dt && dt <= et){
                    c.addCls('x-mydate-selected');
                }else{
                    c.removeCls('x-mydate-selected');
                }
            });
        }
    },

    setRange : function(startDate, endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    },

    update : function(date, forceRefresh){
        this.updateRange();     
        this.callParent(arguments);
    },
    
    initEvents : function(){
    	this.callParent(arguments);
    	this.fireEvent('aferinitevent', this);
    }
});