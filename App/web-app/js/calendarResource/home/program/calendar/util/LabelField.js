
Ext.ns('Ext.util');

Ext.util.LabelField = Ext.extend(Ext.form.Field, {

    onRender:function(ct, position){
		Ext.util.LabelField.superclass.onRender.call(this, ct, position);
		this.wrap = this.el.wrap({cls: this.wrapClass});
		if(Ext.isIE) this.wrap.setHeight(20);
		this.el.addClass('x-hidden');
		this.labelEl = Ext.DomHelper.append(this.wrap, '<div style="padding-top:3px;"></div>', true);
        this.labelEl.dom.innerHTML = this.text;
	},

    setText:function(v){
        if(this.labelEl){
            this.labelEl.dom.innerHTML = v;
        }else{
            this.text = v;
        }
    },

    getText:function(){
        return this.labelEl.dom.innerHTML;
    }
});
