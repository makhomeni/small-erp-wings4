
Ext.ns('Ext.ux.calendar');

Ext.ux.calendar.Editor = function(config){
	Ext.apply(this, config);
    this.ehandler.applyCalendarSetting(this);    
    var lan = Ext.ux.calendar.Mask.Editor;

	this.timeField = this.timeField || new Ext.util.LabelField({
        fieldLabel:lan['startDayField.label'],        
        anchor:'99%'
    });

    this.subjectField = this.subjectField || new Ext.form.TextField({
		fieldLabel:lan['subjectField.label'],
		anchor:'99%'
	});

	this.contentField = this.contentField || new Ext.form.TextField({
		fieldLabel:lan['contentField.label'],        
		anchor:'99%'
	});
		
    this.ctpl = this.ctpl || '<tpl for=".">' +
        '<div class="x-combo-list-item">' +
            this.ehandler.cTplStr +
        '</div>' +
    '</tpl>';

    this.calendarField = this.calendarField || new Ext.form.ComboBox({
        fieldLabel:lan['calendarField.label'],							
		store:Ext.ux.calendar.Mask.getCalendarStore(),
		displayField:'title',
        valueField:'id' ,
        typeAhead:true,
        mode:'local',        
        triggerAction:'all',
        selectOnFocus:true,
        allowBlank: false,
        anchor:'99%',
        editable:false,
        tpl:this.ctpl,
        initList:function(){
            Ext.form.ComboBox.prototype.initList.call(this);
            this.list.setZIndex(999999);
        }
	});

    this.alertCB = this.alertCB || new Ext.form.Checkbox({
        anchor:'99%',
        boxLabel:lan['alertCB.label']
    });

	this.deleteBtn = this.deleteBtn || new Ext.Button({
		iconCls:'icon_feyaCalendar_delete',		
		text:lan['deleteBtn.text'],
		disabled:true,
		handler:this.onRemoveFn,
		scope:this
	});
	
	this.saveBtn = this.saveBtn || new Ext.Button({
		iconCls:'icon_feyaCalendar_accept',		
		text:lan['saveBtn.text'],
		handler:this.onSaveFn,
		scope:this
	});	

    this.detailBtn = this.detailBtn || new Ext.Button({
        text:lan['detailSetting'],
        handler:this.onDetailFn,
        scope:this
    });

    this.cancelBtn = this.cancelBtn || new Ext.Button({
		iconCls:'icon_feyaCalendar_cancel',
		minWidth:80,
		text:lan['cancelBtn.text'],
		handler:this.onCancelFn,
		scope:this
	});

	this.formpanel = this.formpanel || new Ext.form.FormPanel({
		border:false,        
		style:'padding:10px;',        
		labelWidth:75,
        frame:true,
		items:[
			this.timeField,
            this.subjectField,
            this.contentField,
            {
                border:false,
                layout:'column',
                items:[{
                    columnWidth:.65,
                    border:false,
                    layout:'form',
                    items:[this.calendarField]
                }, {
                columnWidth:.35,
                border:false,                
                items:[this.alertCB]
            }]
            }
		],
		buttonAlign:'center',
		buttons:[this.detailBtn, this.deleteBtn, this.saveBtn, this.cancelBtn]
	});
	
	Ext.ux.calendar.Editor.superclass.constructor.call(this, {        
		items:[this.formpanel]
	});
    this.addEvents(
        'showdetailsetting',
        'hided',
        'hideeditor',
        'showed'
    );
    this.on('render', this.onRenderFn, this);
	this.on('showed', this.onShowFn, this);
	this.on('hided', this.onHideFn, this);
    this.on('hideeditor', this.onHideEditorFn, this);
    this.calendarField.on('select', this.onCalendarSelectFn, this);
};

Ext.extend(Ext.ux.calendar.Editor, Ext.Panel, {
    style:'left:-1000px;top:-1000px;',
    
    title:' ',

	width:460,

	height:180,

    baseCls:'x-tip',

    closable:true,

    closeAction:'onCancelFn',

    resizable:false,

    frame:true,
   
    floating:{shadow:true,shim:true,useDisplay:true,constrain:false},
    
    initComponent:function(){
        Ext.ux.calendar.Editor.superclass.initComponent.call(this);
        if(this.closable && !this.title){
            this.elements += ',header';
        }
    },

    afterRender:function(){
        Ext.ux.calendar.Editor.superclass.afterRender.call(this);
        if(this.closable){
            this.addTool({
                id:'close',
                handler:this[this.closeAction],
                scope:this
            });
        }
    },

    onRenderFn:function(p){
        p.getEl().on('mousedown', function(e){           
            this.mdFlag = true;
        }, this);
        p.getEl().on('mouseup', function(e){
            delete(this.mdFlag);
            e.stopPropagation();
        }, this);
    },

    onDetailFn:function(){
        this.hideEditor();        
        this.fireEvent('showdetailsetting', this.obj);
    },    

    onCalendarSelectFn:function(combo, rd, index){
        var coverEl = this.bindEl;
        if(coverEl && !coverEl.hold){
            var event = coverEl.bindEvent;
            var cview = coverEl.cview;
            var eh = cview.ehandler;
            var color = eh.calendarSet[rd.data.id].color;
            var arr = Ext.DomQuery.select('div[name=x-event-'+event.day+'-'+event.eday+'-'+event.eventId+']', cview.body.dom);
            for(var i = 0, len = arr.length; i < len; i++){
                coverEl = Ext.get(arr[i]);                                                
                if(0 == event.startRow && this.rowCount == event.endRow){
                    if(this.oldColor != color){
                        eh.changeWholeColor(coverEl, this.oldColor, color);
                    }
                }else{
                    if(this.oldColor != color){
                        if(cview instanceof Ext.ux.calendar.DayView){
                            eh.changeEventColor(coverEl, this.oldColor, color);
                        }else{
                            eh.changeLegendColor(coverEl, this.oldColor, color);
                        }
                    }
                }
            }
        }
        this.oldColor = color;
    },        
	
	onRemoveFn:function(){
        var lan = Ext.ux.calendar.Mask.EventHandler;
		var coverEl = this.bindEl;
		var be = coverEl.bindEvent;
		var cview = coverEl.cview;
		var eh = cview.ehandler;
		var col = coverEl.col;        
		if(coverEl){                        
            if('string' == Ext.type(be.repeatType)){
                eh.freeEventEl(coverEl);
                eh.deleteEvent(be, cview, col);
            }else{                
                Ext.Msg.show({
                    title:lan['deleteRepeatPopup.title'],
                    msg:lan['deleteRepeatPopup.msg'],
                    buttons: Ext.Msg.YESNOCANCEL,
                    fn: function(bid, text){
                        if('yes' == bid){
                            eh.freeEventEl(coverEl);
                            eh.deleteRepeatEvent(be, cview);
                        }else if('no' == bid){
                            eh.freeEventEl(coverEl);
                            eh.deleteRepeatEvent(be, cview, true);
                        }
                    },
                    icon: Ext.MessageBox.QUESTION
                });
            }
		}
        cview.fireEvent('canceldetail');
        this.hideEditor();
	},
	
	onSaveFn:function(){
		if(this.formpanel.form.isValid()){
            var eh = this.ehandler;
            var cview = this.cview;
			if(this.bindEl){
				var coverEl = this.bindEl;
				var event = coverEl.bindEvent;               
                var oevent = Ext.apply({}, event);				
				if('add' == this.action && !coverEl.hold){
		    		coverEl.remove();
		    	}
                event.repeatType = event.repeatType || 'no';
                event.allDay = false;
                if(this.alertCB.checked){
                    if(!event.alertFlag){
                        event.alertFlag = this.getAlertSetting();
                    }
                }else{
                    delete(event.alertFlag);
                }
                event.locked = event.locked || false;
                event.subject = this.subjectField.getValue();
                event.content = this.contentField.getValue();
                event.calendarId = this.calendarField.getValue();
                event.color = eh.calendarSet[event.calendarId].color;                
		        if('add' == this.action){                    
		        	if('string' == Ext.type(event.repeatType)){
                        eh.createEvent(event, cview);
                    }else{
                        eh.createRepeatEvent(event, cview);
                    }
		        }else if('update' == this.action){
                    if(!Ext.ux.calendar.Mask.isEqualObj(oevent, event)){
                        if('string' == Ext.type(oevent.repeatType) && 'string' == Ext.type(event.repeatType)){
                            eh.updateEvent(event, cview, null, oevent, this.noLayout);
                        }else{
                            if('string' != Ext.type(oevent.repeatType)){
                                /*
                                 * need ask user to choose apply all or just current one
                                 */
                                var lan = Ext.ux.calendar.Mask.EventHandler;
                                Ext.Msg.show({
                                    title:lan['updateRepeatPopup.title'],
                                    msg:lan['updateRepeatPopup.msg'],
                                    buttons: Ext.Msg.YESNOCANCEL,
                                    fn: function(bid, text){
                                        if('yes' == bid){
                                            eh.updateRepeatEvent(event, cview, oevent);
                                        }else if('no' == bid){
                                            event.repeatType = 'exception';                                            
                                            eh.updateRepeatEvent(event, cview, oevent);
                                        }
                                    },
                                    icon: Ext.MessageBox.QUESTION
                                });
                            }else{
                                eh.updateRepeatEvent(event, cview, oevent);
                            }
                        }
                    }
                }
			}
            cview.fireEvent('canceldetail');
            this.hideEditor();
		}
	},

    getAlertSetting:function(){
        var arr;
        if(Ext.ux.calendar.CONST.VERSION >= '2.0.5'){
            arr = [{
                type:'popup',
                early:10,
                unit:'minute'
            }];
        }else{
            arr = [{
                type:'popup',
                early:0,
                unit:'minute'
            }];
        }
        return arr;
    },

	onCancelFn:function(){
        var coverEl = this.bindEl;
        if(coverEl){
            var cview = this.cview;
            var event = coverEl.bindEvent;
            var eh = this.ehandler;
            if(!coverEl.hold){
                if('add' == this.action){
                    coverEl.remove();
                }else{
                    var color = eh.calendarSet[event.calendarId].color;
                    if(0 == event.startRow && this.rowCount == event.endRow){
                        if(this.oldColor != color){
                            eh.changeWholeColor(coverEl, this.oldColor, color);
                        }
                    }else{
                        if(this.oldColor != color){
                            if(cview instanceof Ext.ux.calendar.DayView){
                                eh.changeEventColor(coverEl, this.oldColor, color);
                            }else{
                                eh.changeLegendColor(coverEl, this.oldColor, color);
                            }
                        }
                    }
                }
            }
            this.hideEditor();
        }
	},
	
	popup:function(obj){
        var eh = this.ehandler;
        eh.floating = true;
        this.obj = obj;
        this.noLayout = obj.noLayout;
		this.bindEl = obj.bindEl;
        this.cview = obj.cview;
		this.action = obj.action;
        var lan = Ext.ux.calendar.Mask.Editor;
		if('add' == this.action){            
			this.deleteBtn.disable();
            this.setIconClass('x-event-editor-title-add');
            this.setTitle(lan['new.title']);
		}else{
			this.deleteBtn.enable();
            this.setTitle(lan['edit.title']);
            this.setIconClass('x-event-editor-title-edit');
		}        
        this.showAt(this.adjustXY(this.bindEl));
	},		
	
	adjustXY:function(pn){
        var pxy = pn.getXY();
        var cview = pn.cview;
		var xy = [0, 0];
        var w = this.width, h = this.height;
		var r = pxy[0]+w;
		xy[0] = pxy[0];
        var right = cview.body.getRight();
		if(r > right){
			xy[0] = right-w;
		}
		xy[1] = pxy[1]-h;
        var top = cview.body.getTop();
		if(xy[1] < top){
			if(pxy[1] > top){
				xy[1] = pxy[1]+20;
			}else{
				xy[1] = top+20;
			}
		}
		return xy;
	},

    reloadCalendar:function(eh){
        var store = this.calendarField.store;
        store.removeAll();
        for(var p in eh.calendarSet){
            var calendar = eh.calendarSet[p];
            if(true !== calendar.hide){
                var rd = new (store.recordType)({
                    id:calendar.id,
                    title:calendar.name,
                    description:calendar.description,
                    color:calendar.color
                });
                store.add(rd);
            }
        }
    },

    onShowFn:function(){
        var eh = this.ehandler;                
    	if(this.bindEl){
    		var coverEl = this.bindEl;    				
            if(!coverEl.hold){
                eh.setEditingStatus(coverEl, true);                
            }
    		var bindEvent = coverEl.bindEvent;
            var time = '<b>'+eh.generateInfo(bindEvent)+'</b>';
            this.timeField.setText(time);
            this.subjectField.setValue(bindEvent.subject);
            this.contentField.setValue(bindEvent.content);
            if(bindEvent.alertFlag){
                this.alertCB.setValue(true);
            }else{
                this.alertCB.setValue(false);
            }
            this.reloadCalendar(eh);
            this.calendarField.setValue(bindEvent.calendarId);
            this.oldColor = eh.calendarSet[bindEvent.calendarId].color;            
    	}else{
            
        }        
    },
    
    onHideFn:function(){
        var eh = this.ehandler;
        eh.floating = false;
        var cview = this.cview;
    	if(this.bindEl){            
            //cview.resizePort();
            cview.resetSCover();
    	}
        delete(this.bindEl);
        delete(this.cview);        
        delete(this.noLayout);
        delete(this.action)
    },

    hideEditor:function(){
        if(!this.hided){
            this.hided = true;
            this.showAt([-1000, -1000], true);
            this.fireEvent('hided');
        }
    },

    showAt:function(xy, hold){
        if(!hold){
            this.fireEvent('showed');
            delete(this.hided);
        }
        this.setPagePosition(xy[0], xy[1]);
    },

    onHideEditorFn:function(){
        if(!this.mdFlag){
            this.onCancelFn();
        }
    }
});
