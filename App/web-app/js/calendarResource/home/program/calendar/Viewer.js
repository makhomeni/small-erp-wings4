
Ext.onReady(function(){    
    Ext.BLANK_IMAGE_URL = Ext.ux.calendar.CONST.BLANK_IMAGE_URL;    
    Ext.QuickTips.init();
    var wait = new Ext.LoadMask(document.body, {msg:'<b>Welcome to OceanGroup Calendar</b><br>Please wait, loading Setting...'});
    wait.show();
    
    //hard code userId here
    var userId = 1;    
    var ds = new Ext.ux.calendar.DataSource();    
    ds.initialLoad(userId, function(backObj){
        var cs = backObj.cs;
        ds.initialObj = backObj;
        if(!cs['language']){
            var params = Ext.urlDecode(window.location.search.substring(1));
            if(params.lang){
                cs.language = params.lang;
            }else{
                cs.language = 'en_US';
            }
        }
        /*
         * here add the related language file
         */
        if(Ext.ux.calendar.CONST.SHOW_LANGUAGE_MENU){
            Ext.ux.calendar.LanManager.addJavaScript(cs.language);
        }
        var count = 0;
        var fn = function(){
            if(!Ext.ux.calendar.Language && count++ < 40){
                /*
                 * need defer to wait the js file loaded
                 */
                fn.defer(50);
            }else{
                var win = new Ext.ux.calendar.CalendarWin({
                    iconCls:'icon_feyaCalendar_calendar',
                    title: 'OceanGroup Calendar',
                    width:1200,
                    height:560,                    
                    maximizable:true,
                    minimizable:true,
                    shim:false,
                    animCollapse:false,
                    closable:true,
                    datasource:ds,
                    userId:userId,
                    calendarSetting:cs
                });                
                win.show();
                wait.hide();
            }
        };        
        fn();
    });
});