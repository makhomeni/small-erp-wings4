
Ext.ns("Ext.ux.calendar");

Ext.ux.calendar.Block = function(config){
	var obj = {};
	obj.id = 0;
	obj.colNum = 0;
	obj.startRow = 100;
	obj.endRow = -1;
	obj.eventList = new Array();
	obj.addEvent = function(event){
		if(event.startRow < obj.startRow){
			obj.startRow = event.startRow;
		}
		if(event.endRow > obj.endRow){
			obj.endRow = event.endRow;
		}
        
		obj.eventList[obj.eventList.length] = event;
	}
	Ext.apply(obj, config);
	return obj;
}