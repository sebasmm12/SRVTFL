$(document).ready(function(){
		
		$("#buscar_rol").autocomplete({
			
			source: function(request, response){
				$.ajax({
					url:"/Administrador/cargar-roles/" + request.term,
					dataType: "json",
					data: {
						term: request.term
					},
					success: function(data){
						response($.map(data, function(item){
							return {
								value: item.Id,
								label: item.nombreRol,
							};
						}));
					},
				});
			},
			
			select: function(event, ui){
				$("#buscar_rol").val(ui.item.label);
				/*if(itemsHelper.hasProducto(ui.item.value)){
					itemsHelper.incrementaCantidad(ui.item.value,ui.item.precio);
					return false;
				}
				
				var linea = $("#plantillaItemsFactura").html(); //obtiene el contenido del componente HTML
				linea = linea.replace(/{ID}/g, ui.item.value);
				linea = linea.replace(/{NOMBRE}/g, ui.item.label);
				linea = linea.replace(/{PRECIO}/g, ui.item.precio);
				//cambiando los valores que tendra cada linea
				
				$("#cargarItemProductos tbody").append(linea);
				itemsHelper.calcularImporte(ui.item.value,ui.item.precio,1);*/
				
				return false;
			}
		});
	});

