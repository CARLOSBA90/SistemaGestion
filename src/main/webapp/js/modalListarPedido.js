$('#ListarModal').on('show.bs.modal', function(e) {
    var detalle = $(e.relatedTarget).data('detalle');
    
    separador = "+";
    
    arreglo = detalle.split(separador);
    
    var lista;
    
    for(i=1;i<detalle.length;i++){
    
    	lista = arreglo[i];
    
    $(e.currentTarget).find('[name="detalle_tabla"]').val(detalle);
    
    }
});