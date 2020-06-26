angular.module(appTeclo)
.service('cargaDirectoriosPTService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";

        
    this.crearArchivo = function (){
 		return $http.post(config.baseUrl + "/jnlp/creararchivo");
	}
    
   

});