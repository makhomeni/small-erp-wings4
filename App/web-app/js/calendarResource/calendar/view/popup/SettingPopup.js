
Ext.define('Ext.ux.calendar.popup.SettingPopup', {
	extend : 'Ext.Window',
	initComponent : function() {
		var lan = Ext.ux.calendar.Mask.SettingPopup;
		this.hourFormatField =Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['hourFormatField.label'],
					fieldWidth : 280,
					labelWidth : 180,
					store : Ext.ux.calendar.Mask.getHourFormatStore(),
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					valueField : 'id',
					displayField : 'text',
					selectOnFocus : true,
					editable : false,
					allowBlank : false,
					anchor : '95%'
				});
		this.dayFormatField =Ext.create('Ext.form.TextField',{
					fieldLabel : lan['dayFormatField.label'],
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.weekFormatField =Ext.create('Ext.form.TextField',{
					fieldLabel : lan['weekFormatField.label'],
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.monthFormatField = Ext.create('Ext.form.TextField',{
					fieldLabel : lan['monthFormatField.label'],
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.fromtoFormatField = Ext.create('Ext.form.TextField',{
					fieldLabel : lan['fromtoFormatField.label'],
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.languageField =Ext.create('Ext.form.ComboBox',{
					hideLabel : !Ext.ux.calendar.CONST.SHOW_LANGUAGE_MENU,
					hidden : !Ext.ux.calendar.CONST.SHOW_LANGUAGE_MENU,
					fieldLabel : lan['languageField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getLanguageConfig().store,
					displayField : 'display',
					valueField : 'name',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.createByDblClickField =Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['createByDblClickField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getEDStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.singleDayField = Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['singleDayField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getEDStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					labelWidth : 180,
					selectOnFocus : true,
					allowBlank : false,
					anchor : '95%'
				});

		this.activeStartTimeField =Ext.create('Ext.form.TextField',{
					fieldLabel : lan['activeStartTimeField.label'],
					allowBlank : false,
					anchor : '95%',
					labelWidth : 180,
					validator : this.timeValidator,
					sender : this
				});

		this.activeEndTimeField =Ext.create('Ext.form.TextField',{
					fieldLabel : lan['activeEndTimeField.label'],
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%',
					validator : this.timeValidator,
					sender : this
				});

		this.hideInactiveTimeField =Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['hideInactiveTimeField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getEDStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.readOnlyField = Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['readOnlyField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getEDStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.intervalField = Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['intervalField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getIntervalStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.startDayField =Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['weekStartDayField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getStartDayStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.initialViewField =Ext.create('Ext.form.ComboBox',{
					fieldLabel : lan['initialViewField.label'],
					editable : false,
					store : Ext.ux.calendar.Mask.getInitialViewStore(),
					displayField : 'display',
					valueField : 'value',
					typeAhead : true,
					queryMode : 'local',
					triggerAction : 'all',
					selectOnFocus : true,
					allowBlank : false,
					labelWidth : 180,
					anchor : '95%'
				});

		this.generalForm =Ext.create('Ext.form.FormPanel',{
					title : lan['generalForm.title'],
					frame : true,
					border : false,
					baseCls : 'x-panel-body-default-framed',
					style : 'padding:10px;',
					bodyStyle : 'padding:10px;background:transparent;',
					fieldWidth : 180,
					items : [this.hourFormatField, this.fromtoFormatField,
							this.startDayField, this.readOnlyField,
							this.languageField, this.initialViewField]
				});

		this.dwViewForm =Ext.create('Ext.form.FormPanel',{
					title : lan['dwViewForm.title'],
					frame : true,
					border : false,
					style : 'padding:10px;',
					baseCls : 'x-panel-body-default-framed',
					bodyStyle : 'background:transparent;',
					labelWidth : 180,
					items : [this.dayFormatField, this.weekFormatField,
							this.createByDblClickField, this.singleDayField,
							this.activeStartTimeField, this.activeEndTimeField,
							this.hideInactiveTimeField, this.intervalField]
				});

		this.monthViewForm = Ext.create('Ext.form.FormPanel',{
					title : lan['monthViewForm.title'],
					baseCls : 'x-panel-body-default-framed',
					frame : true,
					border : false,
					style : 'padding:10px;',
					bodyStyle : 'background:transparent;',
					labelWidth : 180,
					items : [this.monthFormatField]
				});

		this.tabs =Ext.create('Ext.TabPanel',{
					//border:false,
					activeTab : 0,
					deferredRender : true,
					resizeTabs : true,
					tabWidth : 1000,
					minTabWidth : 0,
					layoutOnTabChange : false,
					// cls:'x-feyaCalendar-setting',        
					items : [this.generalForm, this.dwViewForm,
							this.monthViewForm]
				});

		this.applyBtn =Ext.create('Ext.Button',{
					iconCls : 'icon_feyaCalendar_accept',
					text : lan['applyBtn.text'],
					handler : this.onApplyFn,
					scope : this
				});

		this.resetBtn = Ext.create('Ext.Button',{
					iconCls : 'icon_feyaCalendar_cancel',
					text : lan['resetBtn.text'],
					handler : this.onResetFn,
					scope : this
				});

		this.closeBtn = Ext.create('Ext.Button',{
					iconCls : 'icon_feyaCalendar_door_out',
					text : lan['closeBtn.text'],
					handler : this.onCloseFn,
					scope : this
				});

		Ext.apply(this, {
					border : false,
					iconCls : 'icon_feyaCalendar_setting',
					cls : 'x-feyaCalendar-setting',
					title : lan['title'],
					width : 500,
					height : 360,
					closable : true,
					resizable : false,
					closeAction : 'hide',
					modal : true,
					layout : 'fit',
					items : [this.tabs],
					buttons : [this.resetBtn, this.applyBtn, this.closeBtn]
				})
		this.callParent(arguments);
	},
	timeValidator : function(v) {
		var lan = Ext.ux.calendar.Mask.SettingPopup;
		var pattern = new RegExp(/[0-2]\d:[0-5]\d/);
		v = v.toString();
		if (5 == v.length && pattern.test(v)) {
			var sender = this.sender;
			if (sender.activeStartTimeField && sender.activeEndTimeField) {
				var activeEndTime = sender.activeEndTimeField.getValue();
				if (activeEndTime
						&& sender.activeStartTimeField.getValue() >= activeEndTime) {
					return lan['startEndInvalid'];
				}
			}
			return true;
		} else {
			return lan['formatInvalid'];
		}
	},

	popup : function(data) {
		this.hourFormatField.setValue(data.hourFormat);
		this.dayFormatField.setValue(data.dayFormat);
		this.weekFormatField.setValue(data.weekFormat);
		this.monthFormatField.setValue(data.monthFormat);
		this.fromtoFormatField.setValue(data.fromtoFormat);
		this.languageField.setValue(data.language);
		this.createByDblClickField.setValue(data.createByDblclick);
		this.singleDayField.setValue(!data.singleDay);
		this.activeStartTimeField.setValue(data.activeStartTime);
		this.activeEndTimeField.setValue(data.activeEndTime);
		this.hideInactiveTimeField.setValue(data.hideInactiveRow);
		this.intervalField.setValue(parseInt(data.intervalSlot));
		this.startDayField.setValue(data.startDay);
		this.readOnlyField.setValue(data.readOnly);
		this.initialViewField.setValue(data.initialView);
		this.show();
	},

	onApplyFn : function() {
		var flag = true;
		if (!this.generalForm.getForm().isValid()) {
			flag = false;
			this.tabs.setActiveTab(this.generalForm);
		} else if (!this.dwViewForm.getForm().isValid()) {
			flag = false;
			this.tabs.setActiveTab(this.dwViewForm);
		} else if (!this.monthViewForm.getForm().isValid()) {
			flag = false;
			this.tabs.setActiveTab(this.monthViewForm);
		}
		if (flag) {
			var cc = this.calendarContainer;
			var wp = cc.ownerCt.westPanel;
			var eh = cc.ehandler;
			var hourFormat = this.hourFormatField.getValue();
			var dayFormat = this.dayFormatField.getValue();
			var weekFormat = this.weekFormatField.getValue();
			var monthFormat = this.monthFormatField.getValue();
			var fromtoFormat = this.fromtoFormatField.getValue();
			var language = this.languageField.getValue();
			var createByDblclick = this.createByDblClickField.getValue();
			var singleDay = !this.singleDayField.getValue();
			var activeStartTime = this.activeStartTimeField.getValue();
			var activeEndTime = this.activeEndTimeField.getValue();
			var hideInactiveRow = this.hideInactiveTimeField.getValue();
			var intervalSlot = this.intervalField.getValue();
			var startDay = this.startDayField.getValue();
			var readOnly = this.readOnlyField.getValue();
			var initialView = this.initialViewField.getValue();
			var params = {
				hourFormat : hourFormat,
				dayFormat : dayFormat,
				weekFormat : weekFormat,
				monthFormat : monthFormat,
				fromtoFormat : fromtoFormat,
				language : language,
				createByDblclick : createByDblclick,
				singleDay : singleDay,
				activeStartTime : activeStartTime,
				activeEndTime : activeEndTime,
				hideInactiveRow : hideInactiveRow,
				readOnly : readOnly,
				intervalSlot : intervalSlot,
				startDay : startDay,
				initialView : initialView
			};
			eh.ds.updateSetting(params, function(backObj) {
						if (eh.language != language
								|| eh.createByDblclick != createByDblclick
								|| eh.singleDay != singleDay
								|| eh.activeStartTime != activeStartTime
								|| eh.activeEndTime != activeEndTime
								|| eh.hideInactiveRow != hideInactiveRow
								|| eh.intervalSlot != intervalSlot
								|| eh.startDay != startDay
								|| eh.readOnly != readOnly) {
							window.location = window.location.href;
							return;
						}
						var cs = eh.calendarSetting;
						Ext.apply(cs, params);

						eh.applyCalendarSetting(eh);

						var de = cc.detailEditor;
						de.reloadStartTimeStore();
						var sv = de.startTimeField.getValue();
						de.startTimeField.setValue(sv);
						de.reloadEndTimeStore(sv);
						de.endTimeField.setValue(de.endTimeField.getValue());

						var cv = cc.currentView;
						eh.checkExpireEvents();

						eh.applyCalendarSetting(wp);
						wp.changeDateLabel(cv.daySet[0],
								cv.daySet[cv.daySet.length - 1]);

						var dv = cc.dayView;
						dv.dayFormat = dayFormat;
						eh.applyCalendarSetting(dv);
						dv.refreshLefter();
						dv.refreshDate();

						var wv = cc.weekView;
						wv.dayFormat = weekFormat;
						eh.applyCalendarSetting(wv);
						wv.refreshLefter();
						wv.refreshDate();

						var wov = cc.weekOnlyView;
						wov.dayFormat = weekFormat;
						eh.applyCalendarSetting(wov);
						wov.refreshLefter();
						wov.refreshDate();

						var mv = cc.monthView;
						mv.dayFormat = monthFormat;
						eh.applyCalendarSetting(mv);
						mv.refreshDate();

						cv.checkLayout(true);
					}, this);
			this.onCloseFn();
		}
	},

	onCloseFn : function() {
		this.hide(Ext.getBody());
	},

	onResetFn : function() {
		this.generalForm.getForm().reset();
		this.dwViewForm.getForm().reset();
		this.monthViewForm.getForm().reset();
	}
});