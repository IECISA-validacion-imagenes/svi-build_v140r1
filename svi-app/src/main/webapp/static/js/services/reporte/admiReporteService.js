angular.module(appTeclo).service("admiReporteService", function($http, config) {

	this.checkParams = function(cadena, scriptSelect){
		return $http.get(config.baseUrl + "/adminReporteController/checkParams", {params : {'cadena' : cadena}});
	};
	
	this.crearTestQuery = function(arrayParamValue, cadena){
		return $http.get(config.baseUrl + "/adminReporteController/crearTestQuery", {params : {'arrayParamValue' : arrayParamValue, 'cadena' : cadena}});
	};
	
	this.guadaReporte = function(voObject){
		return $http.post(config.baseUrl + "/adminReporteController/guadaReporte", voObject);
	};
	
	
	this.ejecutarQueryCatalogo = function (query){
		return $http.get(config.baseUrl + "/adminReporteController/ejecutarQueryCat",{
			params : {
				'query':query
			}
		});
	};
	this.guadaEdicionReporte = function(voObject){
		return $http.put(config.baseUrl + "/consulta/guadaEdicionReporte", voObject);
	};
	
	this.getAllCatalogos = function(){
		return $http.get(config.baseUrl + "/adminReporteController/getAllCatalogos");
	};
	
	this.listTiposReportes = function() {
		return $http.get(config.baseUrl + "/adminReporteController/listaTipoReportes");
	};
});