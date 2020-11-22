$('#ListarModal').on('show.bs.modal', function(e) {
    var detalle = $(e.relatedTarget).data('detalle');
    
    separador = "+";
    
    arreglo = detalle.split(separador);
    
    for(i=1;i<arreglo.length;i++){
    
    $(e.currentTarget).find('[name="detalle_tabla"]').val(arreglo[i]);
    
    }
});