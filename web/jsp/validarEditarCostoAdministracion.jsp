<%-- 
    Document   : validarEditarNivelacionNutriente
    Created on : 22/11/2014, 09:30:03 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <jsp:useBean id="facadeRosemberg" scope="page" class="Facade.FacadeRosemberg" />
        <jsp:useBean id="costo" scope="page" class="Dto.CostoAdministracion" />
        

        <%
        
        
        
        HttpSession sesionU = request.getSession();
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idPropietario = (String) sesionU.getAttribute("id");
        
        String fecha = request.getParameter("fecha");
        String actividad = request.getParameter("actividad");
        System.out.println(actividad+"acccccccccc");
        String otraActividad = request.getParameter("OtraEnmienda");
        String tiempo = request.getParameter("tiempo");
        String costoJornal = request.getParameter("costo");
        String idCosto = request.getParameter("id");
        
        if(actividad.equals("0|")){
            if(otraActividad.isEmpty()){
                   %>
            <script type="text/javascript"> 
                  alert("ERROR... Debe llenar el campo Otro para continuar ya que ha seleccionado otro tipo de insumo")
                  location.href="../jsp/tablaAdministracion.jsp";
                  
              </script>
            <%
                return;
            }
            actividad=facadeRosemberg.registrarActividad(otraActividad,idPropietario,fecha);
            
        }
        costo.setId(idCosto);
        costo.setFecha_registro(fecha);
        costo.setDias_laborados(tiempo);
        costo.setCosto_jornal(costoJornal);
        costo.setTipo_actividad_id(actividad);
        boolean registro = facadeRosemberg.actualizarCostoAdministracion(costo,hacienda);
        
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR... No se pudo actualizar")
                  location.href="../jsp/tablaAdministracion.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("ACTUALIZACION EXITOSO\n Se ha actualizado el registro de costos de administracion")
                  location.href="../jsp/tablaAdministracion.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
