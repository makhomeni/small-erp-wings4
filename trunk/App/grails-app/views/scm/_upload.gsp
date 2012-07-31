
{% for (var i=0, file; file=o.files[i]; i++) { %}
<tr class="template-upload fade">
    <td class="preview"><span class="fade"></span></td>
    <td class="name"><span>{%=file.name%}</span></td>
    <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
    {% if (file.error) { %}
    <td class="error" colspan="2"><span class="label label-important">${message(code:'fileupload.error')}</span>
        {% if (file.error == 'maxFileSize') { %}
        ${message(code: 'fileupload.errors.maxFileSize')}
        {% } else if(file.error == 'minFileSize') { %}
        ${message(code: 'fileupload.errors.minFileSize')}
        {% } else if(file.error == 'acceptFileTypes') { %}
        ${message(code: 'fileupload.errors.acceptFileTypes')}
        {% } else if(file.error == 'maxNumberOfFiles') { %}
        ${message(code: 'fileupload.errors.maxNumberOfFiles')}
        {% } else if(file.error == 'uploadedBytes') { %}
        ${message(code: 'fileupload.errors.uploadedBytes')}
        {% } else if(file.error == 'emptyResult') { %}
        ${message(code: 'fileupload.errors.emptyResult')}
        {% } else { %}
        {%=file.error%}
        {% } %}
    </td>
    {% } else if (o.files.valid && !i) { %}
    <td>
        <div class="progress progress-success progress-striped active"><div class="bar" style="width:0%;"></div></div>
    </td>
    <td class="start">{% if (!o.options.autoUpload) { %}
        <button class="btn btn-primary">
            <i class="icon-upload icon-white"></i>
            <span>${message(code:'fileupload.start')}</span>
        </button>
        {% } %}</td>
    {% } else { %}
    <td colspan="2"></td>
    {% } %}
    <td class="cancel">{% if (!i) { %}
        <button class="btn btn-warning">
            <i class="icon-ban-circle icon-white"></i>
            <span>${message(code:'fileupload.cancel')}</span>
        </button>
        {% } %}</td>
</tr>
{% } %}
