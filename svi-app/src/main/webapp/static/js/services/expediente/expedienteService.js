angular.module(appTeclo).service("expedienteService", function($http, config) {
	
	 var filtro = undefined;

	this.getInicio = function(parametros) {	
		if(parametros == null || parametros == undefined){
			return $http.get(config.baseUrl + "/expedientes/inicio");	
		}else{
			return $http.get(config.baseUrl + "/expedientes/inicio", {params:{"filtro":JSON.stringify(parametros)}});		
		}			
	};


	this.getExpedientes = function(parametros) {
		return $http.get(config.baseUrl + "/expedientes/busqueda", {params:{"filtro":JSON.stringify(parametros)}});		
	};
	
	this.getExpediente = function(parametro, filtro) {		
		if(filtro == null || filtro == undefined){
			return $http.get(config.baseUrl + "/expedientes/"+parametro+"/detalle");
		}else{
			this.setFiltro(filtro);
			return $http.get(config.baseUrl + "/expedientes/"+parametro+"/detalle", {params:filtro});
		}
	};
	
	this.setFiltro = function(filtro){
		this.filtro = filtro;
	}
	
	this.getFiltro = function(){
		return this.filtro;
	}
	
	this.inicializaFiltro = function(){
		return this.getFiltro();
	}
	
	this.marcarExpediente = function(idExpediente){
		return $http.put(config.baseUrl + "/expedientes/"+idExpediente);		
	}	
	
});