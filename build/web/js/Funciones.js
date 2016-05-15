/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//metodo carga una imagen cargando
function loading(rta) {
    $(rta).html("<div class='row'><div class='col-md-6 col-md-offset-5'><img src='../images/cargando.gif' width='32' height='32' alt='cargando' /></div>");
}

function ajax(url, datos, rta) {
    var ajax = $.get(url, datos, function(respuesta) {
        $(rta).html(respuesta);
    });
    return ajax;
}

function nuevoAjax()
{
    /* Crea el objeto AJAX. Esta funcion es generica para cualquier utilidad de este tipo, por
     lo que se puede copiar tal como esta aqui */
    var xmlhttp = false;
    try
    {
        // Creacion del objeto AJAX para navegadores no IE
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e)
    {
        try
        {
            // Creacion del objet AJAX para IE
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (E)
        {
            if (!xmlhttp && typeof XMLHttpRequest !== 'undefined')
                xmlhttp = new XMLHttpRequest();
        }
    }
    return xmlhttp;
}

// Declaro los selects que componen el documento HTML. Su atributo ID debe figurar aqui.
var listadoSelects = new Array();
listadoSelects[0] = "departamento";
listadoSelects[1] = "municipio";



function buscarEnArray(array, dato)
{
    // Retorna el indice de la posicion donde se encuentra el elemento en el array o null si no se encuentra
    var x = 0;
    while (array[x])
    {
        if (array[x] === dato)
            return x;
        x++;
    }
    return null;
}

function cargaContenido(idSelectOrigen)
{
    // Obtengo la posicion que ocupa el select que debe ser cargado en el array declarado mas arriba
    var posicionSelectDestino = buscarEnArray(listadoSelects, idSelectOrigen) + 1;
    // Obtengo el select que el usuario modifico
    var selectOrigen = document.getElementById(idSelectOrigen);
    // Obtengo la opcion que el usuario selecciono
    var opcionSeleccionada = selectOrigen.options[selectOrigen.selectedIndex].value;
    // Si el usuario eligio la opcion "Elige", no voy al servidor y pongo los selects siguientes en estado "Selecciona opcion..."
    if (opcionSeleccionada === 0)
    {
        var x = posicionSelectDestino, selectActual = null;
        // Busco todos los selects siguientes al que inicio el evento onChange y les cambio el estado y deshabilito
        while (listadoSelects[x])
        {
            selectActual = document.getElementById(listadoSelects[x]);
            selectActual.length = 0;

            var nuevaOpcion = document.createElement("option");
            nuevaOpcion.value = 0;
            nuevaOpcion.innerHTML = "Selecciona Opci&oacute;n...";
            selectActual.appendChild(nuevaOpcion);
            selectActual.disabled = true;
            x++;
        }
    }
    // Compruebo que el select modificado no sea el ultimo de la cadena
    else if (idSelectOrigen !== listadoSelects[listadoSelects.length - 1])
    {
        // Obtengo el elemento del select que debo cargar
        var idSelectDestino = listadoSelects[posicionSelectDestino];
        var selectDestino = document.getElementById(idSelectDestino);
        // Creo el nuevo objeto AJAX y envio al servidor el ID del select a cargar y la opcion seleccionada del select origen
        var ajax = nuevoAjax();
        ajax.open("GET", "select_dependientes_proceso.jsp?select=" + idSelectDestino + "&opcion=" + opcionSeleccionada, true);
        ajax.onreadystatechange = function ()
        {
            if (ajax.readyState === 1)
            {
                // Mientras carga elimino la opcion "Selecciona Opcion..." y pongo una que dice "Cargando..."
                selectDestino.length = 0;
                var nuevaOpcion = document.createElement("option");
                nuevaOpcion.value = 0;
                nuevaOpcion.innerHTML = "Cargando...";
                selectDestino.appendChild(nuevaOpcion);
                selectDestino.disabled = true;
            }
            if (ajax.readyState === 4)
            {
                selectDestino.parentNode.innerHTML = ajax.responseText;
            }
        }
        ajax.send(null);
    }
}

function cargarInformacion()
{

    var criterio = $("#criterio").val();
    var palabra = $("#palabra").val();
    var dpto = $("#departamento").val();
    var mun = $("#municipio").val();
    var tipo = $("#tipo").val();

    console.log(criterio);
    console.log(palabra);
    console.log(dpto);
    console.log(mun);
    console.log(tipo);
    var url = "../jsp/table_proceso.jsp?criterio=" + criterio + "&palabra=" + palabra +"&dpto=" + dpto + "&mun=" + mun +"&tipo=" + tipo;
    console.log(url);
    document.getElementById("datos").innerHTML = "";
    jQuery("#datos").load(url);

}

function cargarInput()
{
    var criterio = $("#criterio").val();
    
    if (criterio == 0 || criterio == 1 || criterio == 2 || criterio == 5 || criterio == 6)
    {
        $("#palabra").attr("disabled", false);
        $("#departamento").attr("disabled", true);
        $("#municipio").attr("disabled", true);
    }
    else if (criterio == 3)
    {
        $("#departamento").attr("disabled", false);
        $("#palabra").attr("disabled", true);
        $("#municipio").attr("disabled", true);
    }
    else if (criterio == 4)
    {
        $("#municipio").attr("disabled", false);
        $("#departamento").attr("disabled", false);
        $("#palabra").attr("disabled", true);
    }
    else {
        alert("dato no valido por favor elija de nuevo");
    }
}

function cargarInput2()
{
    var criterio = $("#criterio").val();
    
    if (criterio == 0 || criterio == 1 || criterio == 4)
    {
        $("#palabra").attr("disabled", false);
        $("#departamento").attr("disabled", true);
        $("#municipio").attr("disabled", true);
    }
    else {
        alert("dato no valido por favor elija de nuevo");
    }
}
function habilitarCampoOtroI_registro(){
    
    var opcion = $("#idItem").val();
    
    if(opcion == 0){
        $("#otroI").attr("disabled",false);
    }else{
        $("#otroI").attr("disabled",true);
    }
}
//habilita el campo otro fertilizante para el registro 
function habilitarCampoOtroF_registro(){
    
    var opcion = $("#idFertilizante").val();
    
    if(opcion == 0){
        $("#otroF").attr("disabled",false);
    }else{
        $("#otroF").attr("disabled",true);
    }
}

function habilitarCampoOtraM_registro(){
    
    var opcion1 = $("#idMaquina").val();
    
    if(opcion1 == 0){
        $("#otraM").attr("disabled",false);
    }else{
        $("#otraM").attr("disabled",true);
    }
}

//habilita el campo otra unidad para el registro
function habilitarCampoOtroU_registro(){
    var opcion = $("#idUnidad").val();
    
    if(opcion == 0){
        $("#otroU").attr("disabled",false);
    }else{
        $("#otroU").attr("disabled",true);
    }
}

//habilita el campo otro fertilizante para el registro
function habilitarCampoOtroF_editar(){
    
    var opcion = $("#idF").val();
    
    if(opcion == 0){
        $("#otroFer").attr("disabled",false);
    }else{
        $("#otroFer").attr("disabled",true);
    }
}

function habilitarCampoOtroI_editar(){
    
    var opcion = $("#idI").val();
    
    if(opcion == 0){
        $("#otroIte").attr("disabled",false);
    }else{
        $("#otroIte").attr("disabled",true);
    }
}

//habilita el campo otra unidad para el registro
function habilitarCampoOtroU_editar(){
    var opcion = $("#idU").val();
    
    if(opcion == 0){
        $("#otroUni").attr("disabled",false);
    }else{
        $("#otroUni").attr("disabled",true);
    }
}    


   function FormEditarNivelacionNutriente(idNivelacion){
    var url = "../jsp/formularioEditarNivelacionNutriente.jsp";
    var datos = {idNivelacion:idNivelacion};
    var rta = "#formEditarNivelacion";
    loading(rta);
    ajax(url, datos, rta);
}

   function FormEditarCFMaquinaria(idCFMaquinaria){
    var url = "../jsp/formularioEditarCFMaquinaria.jsp";
    var datos = {idCFMaquinaria:idCFMaquinaria};
    var rta = "#formEditarMaquinaria";
    loading(rta);
    ajax(url, datos, rta);
}

  function FormEditarCostoTierra(idCosto) {
  
    var url = "../jsp/formularioEditarCostoTierra.jsp";
    var datos = {idCosto:idCosto};
    var rta = "#formEditarCostoTierra";
    loading(rta);
    ajax(url, datos, rta);
}
   function FormEditarCostoAdministracion(idCosto) {
   
    var url = "../jsp/formularioEditarAdministracion.jsp";
    var datos = {idCosto:idCosto};
    var rta = "#formEditarCostoAdministracion";
    loading(rta);
    ajax(url, datos, rta);
}

function FormEditarCFMaquinaria(idMaquinaria){
    var url= "../jsp/formularioEditarCFMaquinaria.jsp";
    var datos= {idMaquinaria:idMaquinaria};
    var rta= "#formEditarCFMaquinaria";
    loading(rta);
    ajax (url, datos, rta);
    
}

function FormEditarSiembraCobertura(idCobertura){
    var url = "../jsp/formularioEditarSiembraCobertura.jsp";
    var datos = {idCobertura:idCobertura};
    var rta = "#formEditarCobertura";
    loading(rta);
    ajax(url, datos, rta);
}

function FormEditarCorreccionSuelo(idCorreccion) {
    var url = "../jsp/formularioEditarCorreccionSuelos.jsp";
    var datos = {idCorreccion:idCorreccion};
    var rta = "#formEditarCorreccion";
    loading(rta);
    ajax(url, datos, rta);
}

function FormEditarPalmaSiembra(idSiembra) {
    var url = "../jsp/formularioEditarPalmaSiembra.jsp";
    var datos = {idSiembra:idSiembra};
    var rta = "#formEditarSiembra";
    loading(rta);
    ajax(url, datos, rta);
}

function FormEditarOtros(idOtro) {
    var url = "../jsp/formularioEditarOtros.jsp";
    var datos = {idOtro:idOtro};
    var rta = "#formEditarOtros";
    loading(rta);
    ajax(url, datos, rta);
}

function cargarSubtotal(){
    $(document).ready(function () {
          $("#precioFertilizante").keyup(function () {
              var value = $(this).val();
              $("#subtotal").val(parseInt(value)*10);
          });
      });
}

function cargarSubtotal2(){
   
    $(document).ready(function () {
          $("#precioFertilizante").keyup(function () {
              var precio = $("#precioFertilizante").val();
                var area = $("#areaLote").val();
                var cantidad = $("#cantidadFertilizante").val();
                var total = parseInt(precio)*parseInt(area)*parseInt(cantidad)
              $("#subtotal").val(total);
          });
      });
}

//Calcaula el valor del subtotal en el campo correspondiente a medida que se va digitando
function cargarSubtotal3_registrar(var1, var2, var3){
  
    $(document).ready(function () {
          $(var1).keyup(function () {
              
                var total = parseInt($(var1).val())*parseInt($(var2).val())*parseInt($(var3).val())
              $("#subtotal").val(total);
          });
      });
}

function cargarSubtotalPrecioXUnd_registrar(var1, var2){
  
    $(document).ready(function () {
          $(var1).keyup(function () {              
                var tota = (parseInt($(var1).val())*parseInt($(var2).val()))
              $("#subtotalc").val(tota);
          });
      });
}
function cargarPrecioXañoVidaUtil_registrar(var1,var2){
    $(document).ready(function () {
          $(var1).keyup(function () {              
                var tota = (parseInt($(var2).val())/parseInt($(var1).val()))
              $("#preautil").val(tota);
          });
      });    
}
function cargarCostoHanio(var1, var2){
     $(document).ready(function(){
          $(var1).keyup(function(){              
                var tota=(parseInt($(var2).val())/parseInt($(var1).val()))
              $("#costoHectarea").val(tota);
          });
      });    
}

function cargarTotalM(var1, var2){
    $(document).ready(function(){
          $(var1).keyup(function(){              
                var tota=(parseInt($(var2).val())*parseInt($(var1).val()))
              $("#subtotalMaquinaria").val(tota);
          });
      });      
}

function cargarSubtotalPreciXUnd_editar(var1, var2){
  
    $(document).ready(function () {
          $(var1).keyup(function () {
                var total = parseInt($(var1).val())*parseInt($(var2).val())
              $("#subtotal_ed").val(total);
          });
      });
}
//calcular el 10% del subtotal y lo carga en el campo de costo aplicacion cuando se hace click sobre este 
function cargarCostoAplicacion_registrar(){
    var costo = parseInt($("#subtotal").val())*0.1
    $("#precioAplicacion").val(costo);        
}

//Calcaula el valor del subtotal en el campo correspondiente a medida que se va digitando PARA EL EDITAR
function cargarSubtotal3_editar(var1, var2, var3){
  
    $(document).ready(function () {
          $(var1).keyup(function () {
                var total = parseInt($(var1).val())*parseInt($(var2).val())*parseInt($(var3).val())
              $("#subtotal_e").val(total);
          });
      });
}



//calcular el 10% del subtotal y lo carga en el campo de costo aplicacion cuando se hace click sobre este PARA EL EDITAR
function cargarCostoAplicacion_editar(){
   
    var costo = parseInt($("#subtotal_e").val())*0.1
    $("#precioAplicacion_e").val(costo);        
}

function editarPreparacionSuelo(id){
    var url = "../jsp/editarPreparacionSuelo.jsp";
    var datos = {idPreparacion:id};
    var rta = "#editarSuelo";
    loading(rta);
    ajax(url,datos,rta);
}







