
Ext.ns('Ext.util');

Ext.util.DatePicker = Ext.extend(Ext.DatePicker, {

    onRender : function(container, position){
        Ext.util.DatePicker.superclass.onRender.call(this, container, position);
    },

    updateRange : function(){
        if(this.startDate && this.endDate){            
            var st = this.startDate.clearTime().getTime();
            var et = this.endDate.clearTime().getTime();
            this.cells.each(function(c){
                var dt = c.dom.firstChild.dateValue;
                if(st <= dt && dt <= et){
                    c.addClass('x-date-selected');
                }else{
                    c.removeClass('x-date-selected');
                }
            });
        }
    },

    setRange : function(startDate, endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    },

    update : function(date, forceRefresh){
        Ext.util.DatePicker.superclass.update.call(this, date, forceRefresh);
        this.updateRange();
    }
});
