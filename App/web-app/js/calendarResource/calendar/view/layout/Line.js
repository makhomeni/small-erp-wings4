
Ext.ns("Ext.ux.calendar");

Ext.ux.calendar.Line = function(config){
	var obj = {};
	obj.areaList = new Array();
	obj.block = null;
	Ext.apply(obj, config);
	return obj;
}