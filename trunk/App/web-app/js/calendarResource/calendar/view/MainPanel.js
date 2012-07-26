Ext.define('Ext.ux.calendar.MainPanel', {
	
			extend : 'Ext.Panel',
			
			initComponent : function() {
				this.datasource = this.datasource|| new Ext.ux.calendar.DataSource();
				this.datasource.mainPanel = this;
				this.commentTip=Ext.create('Ext.ux.calendar.CommentTip');
				this.ehandler=Ext.create('Ext.ux.calendar.EventHandler',{
							ds : this.datasource,
							mainPanel : this,
							commentTip : this.commentTip,
							calendarSetting : this.calendarSetting
						})

				this.editor=Ext.create('Ext.ux.calendar.editor.EventEditor',{
							ehandler : this.ehandler
						})
			   this.ceditor=Ext.create('Ext.ux.calendar.editor.CalendarEditor',{
							ehandler : this.ehandler
						})
				this.ehandler.editor = this.editor;
				this.ehandler.ceditor = this.ceditor;

				 this.westPanel=Ext.create('Ext.ux.calendar.WestPanel',{
							ehandler : this.ehandler
						})
				this.calendarContainer=Ext.create('Ext.ux.calendar.CalendarContainer',{
							ehandler : this.ehandler
						})
				this.backthread=Ext.create('Ext.ux.calendar.BackThread',{
							ehandler : this.ehandler
						})
				Ext.apply(this, {
							layout : 'border',
                            renderTo: 'container-container',
							items : [this.westPanel, this.calendarContainer]
						})
				this.ehandler.on('calendarloaded',
				this.calendarContainer.onCalendarLoadedFn,
				this.calendarContainer);
				this.on('afterrender', this.onAfterRenderFn, this);
				this.on('destroy', this.onDestroyFn, this);
				this.westPanel.relayEvents(this.calendarContainer,['changedate']);
						
				this.relayEvents(this.calendarContainer, ['beforeremoteload','remoteload']);
				this.calendarContainer.relayEvents(this.editor,['showdetailsetting']);
				this.editor.relayEvents(this.calendarContainer, ['hideeditor']);
				this.on('beforeremoteload', this._onBeforeRemoteLoadFn, this);
				this.on('remoteload', this._onRemoteLoadFn, this, {delay : 500});
				this.callParent(arguments);
			},
			_onBeforeRemoteLoadFn : function() {
				if (this.loadMask) {
					this.loadMask.show();
				}
			},
			_onRemoteLoadFn : function() {
				if (this.loadMask) {
					this.loadMask.hide();
				}
			},
			onAfterRenderFn : function(p) {
				this.calendarContainer.relayEvents(p.body, ['mousedown']);
				this.editor.render(p.body);
				this.editor.hideEditor()
				this.loadMask = new Ext.LoadMask(p.body, {
							msg : Ext.ux.calendar.Mask.MainPanel['loadMask.msg']
						});
			},
			onDestroyFn : function(p) {
				this.backthread.destroy();
				var eh = this.ehandler;
				eh.epopup.close();
			}

		});
