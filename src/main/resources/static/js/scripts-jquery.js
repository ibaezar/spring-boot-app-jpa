$(document).ready(function(){
    $("#search_product").autocomplete({
        source: function(request, response){
            $.ajax({
                url: "/facturas/cargar-productos/" + request.term,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function(data){
                    response($.map(data, function(item){
                        return {
                            value: item.id,
                            label: item.name,
                            amount: item.amount,
                        }
                    }))
                }
            })
        },
        select: function(event, ui){
            //$("#search_product").val(ui.item.label);

            if(itemsHelper.hasProduct(ui.item.value)){
                itemsHelper.incrementarCantidad(ui.item.value, ui.item.amount);
                return false;
            }

            var line = $("#itemTemplateInvoice").html();

            line = line.replace(/{ID}/g, ui.item.value);
            line = line.replace(/{NOMBRE}/g, ui.item.label);
            line = line.replace(/{PRECIO}/g, ui.item.amount);

            $("#loadProductItems tbody").append(line);

            itemsHelper.calcularImporte(ui.item.value, ui.item.amount, 1);

            return false;
        }
    });

    $("form").submit(function(){
        $("#itemTemplateInvoice").remove();
        return;
    });
});

var itemsHelper = {
    calcularImporte: function (id, amount, quantity) {
        $("#total_importe_" + id).html(parseInt(amount) * parseInt(quantity));
        this.calcularGranTotal();
    },
    hasProduct: function (id) {
        var resultado = false;

        $('input[name="item_id[]"]').each(function () {
            if(parseInt(id) == parseInt($(this).val())){
                resultado = true;
            }
        });
        return resultado;
    },
    incrementarCantidad: function(id, amount){
        var cantidad = $("#quantity_" + id).val() ? parseInt($("#quantity_" + id).val()) : 0;
        $("#quantity_" + id).val(++cantidad);
        this.calcularImporte(id, amount, cantidad);
    },
    eliminarLinea: function (id) {
        $("#row_" + id).remove();
        this.calcularGranTotal();
    },
    calcularGranTotal: function(){
        var total = 0;
        $('span[id^="total_importe_"]').each(function(){
            total += parseInt($(this).html());
        });
        $("#gran_total").html("$ " + total);
    }
}