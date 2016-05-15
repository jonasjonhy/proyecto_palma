/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function cargarInformacion(idSelect,pal)
{
    //Obtengo el select criterio
    var select=document.getElementById(idSelect);
    //Obtengo la opcion seleccionada
    var criterio=select.options[select.selectedIndex].value;
    //Obtengo palabra clave
    var word=document.getElementsById(pal).value;
    alert(word)
    var ajax=nuevoAjax();
    ajax.open("GET","table_proceso.jsp?criterio="+criterio+"&palabra="+word);
}