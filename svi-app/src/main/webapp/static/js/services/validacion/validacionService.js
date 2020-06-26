angular.module(appTeclo)
.service('validacionService',function($rootScope,$http,$localStorage,$window,$q,$route,config) { "use strict";
    
	const myPath = "/validacionExpediente";
    
    this.tieneAsignacionesRest = function () {
        var retorno = $http.get(config.baseUrl + myPath + "/tieneAsignaciones");
	    return retorno;
    };  
    
    this.asignacionInicial = function () {
		return $http.get(config.baseUrl + myPath + "/asignacionInicialVO");
	};
	
	this.validacionRest = function(currenData  ){
		return $http.put(config.baseUrl + "/validacion/validacionRegistro", currenData);
	};
	
//	this.archivoEstaValidado = function(idArchivo){
//		return $http.get(config.baseUrl + "/validacion/archivoEstaValidado", idArchivo);
//	};
	
	this.archivoEstaValidado = function (idArchivo) {
        let datos = {   "totalValidadas" : null,
                    "totalRegistros" : null,
                    "totalRestantes" : null,
                    "idArchivo" : idArchivo,
                    "archivoValidado" : false
                };

        return $http.post(config.baseUrl + "/validacion/archivoEstaValidado", datos );
    };
	
	
 
	this.getcatClasifExpediente = function () {
		return $http.get(config.baseUrl + "/validacion/todoClasifExpedientes");
    };
    
    
    this.getCatalogoEntidades = function () {
		return $http.get(config.baseUrl + "/validacion/todasEntidades");
    };
    
    
    this.getcatalogoPerfiles = function () {
		return $http.get(config.baseUrl + "/validacion/getCatalogoPerfiles");
    };
    
    
    this.getcatValidacionSiluetas = function () {
		return $http.get(config.baseUrl + "/validacion/getCatalogoValidacionSiluetas");
    };
    
    this.getcatalogoMarcas = function () {
  		return $http.get(config.baseUrl + "/validacion/getCatalogoMarcasPerfil");
    };
    
});
