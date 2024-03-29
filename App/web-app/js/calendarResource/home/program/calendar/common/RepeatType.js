
Ext.ns("Ext.ux.calendar");

Ext.ux.calendar.RepeatType = {
    getEvent:function(re, day){
        var rt = re.repeatType;
        var rtype = rt.rtype;
        var eps = rt.exceptions;
        if(rt.beginDay <= day && ('no' == rt.endDay || day <= rt.endDay) && (!eps || !eps[day])){
            var e;
            if('day' == rtype){
                e = Ext.ux.calendar.RepeatType.getRepeatDayEvent(day, re);
            }else if('week' == rtype){
                e = Ext.ux.calendar.RepeatType.getRepeatWeekEvent(day, re);
            }else if('month' == rtype){
                e = Ext.ux.calendar.RepeatType.getRepeatMonthEvent(day, re);
            }else if('year' == rtype){
                e = Ext.ux.calendar.RepeatType.getRepeatYearEvent(day, re);
            }
            return e;
        }
    },

    getRepeatDayEvent:function(day, re){        
        var rt = re.repeatType;
        var beginDay = rt.beginDay;
        var intervalSlot = rt.intervalSlot;
        var dspan = rt.dspan;
        var rtime = rt.rtime;
        var dnum = Ext.ux.calendar.Mask.getDayOffset(beginDay, day);        
        var r = dnum%intervalSlot;
        var t = Math.floor(dnum/intervalSlot);
        if(0 == r && (!rtime || t < rtime)){
            var e = Ext.apply({}, re);
            e.day = day;
            var date = Date.parseDate(day, 'Y-m-d');
            e.eday = date.add(Date.DAY, dspan).format('Y-m-d');
            delete(e.lflag);
            delete(e.rflag);
            return e;
        }
    },

    getRepeatWeekEvent:function(day, re){
        var rt = re.repeatType;
        var beginDay = rt.beginDay;
        var beginDate = Date.parseDate(beginDay, 'Y-m-d');        
        var bn = beginDate.format('N');
        var date = Date.parseDate(day, 'Y-m-d');        
        var n = date.format('N');        
        var rday = rt.rday;
        if('{}' == Ext.encode(rday)){
            rday[bn] = true;
        }
        if(rday[n]){
            var intervalSlot = rt.intervalSlot;
            var dspan = rt.dspan;
            var rtime = rt.rtime;
            var dnum = Math.floor((Ext.ux.calendar.Mask.getDayOffset(beginDay, day)-n-bn)/7)+1;
            var r = dnum%intervalSlot;
            var t = Math.floor(dnum/intervalSlot);
            if(0 == r && (!rtime || t < rtime)){
                var e = Ext.apply({}, re);
                e.day = day;
                var date = Date.parseDate(day, 'Y-m-d');
                e.eday = date.add(Date.DAY, dspan).format('Y-m-d');
                delete(e.lflag);
                delete(e.rflag);
                return e;
            }
        }
    },

    getRepeatMonthEvent:function(day, re){
        var rt = re.repeatType;
        var beginDay = rt.beginDay;
        var beginDate = Date.parseDate(beginDay, 'Y-m-d');
        var by = beginDate.format('Y');
        var bm = beginDate.format('n');
        var bd = beginDate.format('j');
        
        var date = Date.parseDate(day, 'Y-m-d');
        var y = date.format('Y');
        var m = date.format('n');
        var d = date.format('j');
        
        var rby = rt.rby;
        
        var bn = beginDate.format('N');
        var bw = Math.floor(bd/7)+1;
            
        var n = date.format('N');
        var w = Math.floor(d/7)+1;        
        if(('date' == rby && bd == d) || ('day' == rby && w == bw && n == bn)){
            var intervalSlot = rt.intervalSlot;
            var dspan = rt.dspan;
            var rtime = rt.rtime;
            var dnum = 12*y+m-12*by-bm;
            var r = dnum%intervalSlot;
            var t = Math.floor(dnum/intervalSlot);            
            if(0 == r && (!rtime || t < rtime)){
                var e = Ext.apply({}, re);
                e.day = day;                
                e.eday = date.add(Date.DAY, dspan).format('Y-m-d');
                delete(e.lflag);
                delete(e.rflag);
                return e;
            }
        }
    },

    getRepeatYearEvent:function(day, re){
        var rt = re.repeatType;
        var beginDay = rt.beginDay;
        var beginDate = Date.parseDate(beginDay, 'Y-m-d');
        var by = beginDate.format('Y');
        var bm = beginDate.format('n');
        var bd = beginDate.format('j');

        var date = Date.parseDate(day, 'Y-m-d');
        var y = date.format('Y');
        var m = date.format('n');
        var d = date.format('j');
        
        if(bm == m && bd == d){            
            var intervalSlot = rt.intervalSlot;
            var dspan = rt.dspan;
            var rtime = rt.rtime;
            var dnum = y-by;
            var r = dnum%intervalSlot;
            var t = Math.floor(dnum/intervalSlot);
            if(0 == r && (!rtime || t < rtime)){
                var e = Ext.apply({}, re);
                e.day = day;                
                e.eday = date.add(Date.DAY, dspan).format('Y-m-d');
                delete(e.lflag);
                delete(e.rflag);
                return e;
            }
        }
    }
};