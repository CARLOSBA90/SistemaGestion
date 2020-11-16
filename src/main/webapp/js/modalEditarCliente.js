$('#EditarModal').on('show.bs.modal', function(e) {
    var id = $(e.relatedTarget).data('id');
    $(e.currentTarget).find('input[name="id"]').val(id);
    
    var nombre = $(e.relatedTarget).data('nombre');
    $(e.currentTarget).find('input[name="nombre"]').val(nombre);
    
    var apellido = $(e.relatedTarget).data('apellido');
    $(e.currentTarget).find('input[name="apellido"]').val(apellido);
    
    var dni = $(e.relatedTarget).data('dni');
    $(e.currentTarget).find('input[name="dni"]').val(dni);
    
    var telefono = $(e.relatedTarget).data('telefono');
    $(e.currentTarget).find('input[name="telefono"]').val(telefono);
    
    var direccion = $(e.relatedTarget).data('direccion');
    $(e.currentTarget).find('input[name="direccion"]').val(direccion);
    
    var correo = $(e.relatedTarget).data('correo');
    $(e.currentTarget).find('input[name="correo"]').val(correo);
    
    
    
});