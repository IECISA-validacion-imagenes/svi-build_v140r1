angular.module(appTeclo).service('validacionInconsistenciasService',function($rootScope,$http,$localStorage,$window,$q,$route,config) {
 "use strict";
 	/*Traer archivos a validar */
 this.getArchivosValidar = function (usuario) {
		return $http.get(config.baseUrl + "/validacionInconsistencia/getArchivosAsignadosInconsistentes",usuario);
 };
 
 this.validacionRest = function(currenData){
    return $http.put(config.baseUrl + "/validacionInconsistencia/revalidacionRegistro", currenData);
 };

});