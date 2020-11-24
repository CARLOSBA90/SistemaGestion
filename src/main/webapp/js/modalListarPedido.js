$('#ListarModal').on('show.bs.modal', function(e) {
    var detalle = $(e.relatedTarget).data('detalle');
    
    separador = "+";
    
    arreglo = detalle.split(separador);
    
    console.log(arreglo);
    
    for(i=1;i<arreglo.length;i++){
    
    $(e.currentTarget).find('name="detalletabla"').val(arreglo[i]);
    }
    
    
});