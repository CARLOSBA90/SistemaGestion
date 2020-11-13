$('#EditarModal').on('show.bs.modal', function(e) {
    var id = $(e.relatedTarget).data('id');
    $(e.currentTarget).find('input[name="id"]').val(id);
    
    var usuario = $(e.relatedTarget).data('usuario');
    $(e.currentTarget).find('input[name="usuario"]').val(usuario);
    
    var correo = $(e.relatedTarget).data('email');
    $(e.currentTarget).find('input[name="correo"]').val(correo);
    
    var fecha = $(e.relatedTarget).data('fecha');
    $(e.currentTarget).find('input[name="fecha"]').val(fecha);
    
    var contra = $(e.relatedTarget).data('contra');
    $(e.currentTarget).find('input[name="contrasena"]').val(contra);
    
    var nivel = $(e.relatedTarget).data('nivel');
    $(e.currentTarget).find('input[name="nivel"]').val(nivel);
    
});