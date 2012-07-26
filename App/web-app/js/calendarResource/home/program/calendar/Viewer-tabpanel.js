
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
                var mp = new Ext.ux.calendar.MainPanel({
                    title:'OceanGroup MyCalendar',
                    datasource:ds,
                    calendarSetting:cs,
                    userId:userId
                });
                var dv = new Ext.Viewport({
                    layout:'fit',
                    items:[{
                        xtype:'tabpanel',
                        activeTab:0,
                        deferredRender:true,
                        resizeTabs:true,
                        tabWidth:300,
                        minTabWidth:0,
                        layoutOnTabChange:true,
                        hideMode:'offsets',
                        border:false,                        
                        items:[mp]
                    }]
                });
                wait.hide();
            }
        };        
        fn();
    });
});