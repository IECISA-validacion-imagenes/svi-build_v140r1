angular.module(appTeclo).service('cancelaAsignacionService',function($http,$route,config) {
    "use strict";
    
    this.catalogoBusqueda = function (){
 		return $http.get(config.baseUrl + "/catTipoBusqReporteAsignacion");
	}
    
    this.consultaAsignaciones = function(VO){
    	return $http.get(config.baseUrl + "/consultaAsignaciones", {params:{"idTipoBusq":VO.tipo, "valor":VO.busca}});
    }
    
    this.cancelarAsignacion = function(idAsigna){
    	return $http.get(config.baseUrl + "/cancelaAsignacion", {params:{"idAsigna":idAsigna}});
    }
    
});