
Ext.onReady(function(){        
    Ext.QuickTips.init();
    var wait = new Ext.LoadMask(document.body, {msg:'<b>Welcome to Ocean MyCalendar</b><br>Please wait, loading Setting...'});
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
                cs.language = 'en';
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
                Ext.defer(fn, 50);
            }else{
            	var wn =Ext.create('Ext.ux.calendar.CalendarWin', { 
            	    title : 'Calendar | CubeDrive Version [' + Ext.ux.calendar.CONST.VERSION + ']',
                    datasource : ds,
                    calendarSetting : cs,
                    userId : userId
                });
                wn.show();                
                wait.hide();
            }
        };        
        fn();
    });
});