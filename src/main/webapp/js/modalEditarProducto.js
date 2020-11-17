$('#EditarModal').on('show.bs.modal', function(e) {
    var codigo = $(e.relatedTarget).data('codigo');
    $(e.currentTarget).find('input[name="codigo"]').val(codigo);
    
    var tipo = $(e.relatedTarget).data('tipo');
    $(e.currentTarget).find('input[name="tipo"]').val(tipo);
    
    var nombre = $(e.relatedTarget).data('nombre');
    $(e.currentTarget).find('input[name="nombre"]').val(nombre);
    
    var precio = $(e.relatedTarget).data('precio');
    $(e.currentTarget).find('input[name="precio"]').val(precio);
    
    var fabricante = $(e.relatedTarget).data('fabricante');
    $(e.currentTarget).find('input[name="fabricante"]').val(fabricante);
    
    var talla = $(e.relatedTarget).data('talla');
    $(e.currentTarget).find('input[name="talla"]').val(talla);
    
    var stock = $(e.relatedTarget).data('stock');
    $(e.currentTarget).find('input[name="stock"]').val(stock);
    
    
    
});