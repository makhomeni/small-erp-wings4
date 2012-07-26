
Ext.define('Ext.util.LabelField', {
	extend:'Ext.form.field.Base',
    onRender:function(ct, position){
		Ext.util.LabelField.superclass.onRender.call(this, ct, position);
		this.wrap = this.el.wrap({cls: this.wrapClass});
		if(Ext.isIE) this.wrap.setHeight(20);
		this.labelEl=this.wrap.down('td[class*=x-form-item-body]');
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
    },
    fieldSubTpl: ['']
        
    
});
