$('#ListarModal').on('show.bs.modal', function(e) {
    var detalle = $(e.relatedTarget).data('detalle');
    $(e.currentTarget).find('table[name="detalle"]').val(detalle);
    
    
});