angular.module(appTeclo).service("asignacionService", function($http, config) {
	
	 var filtro = undefined;

	this.getInicio = function(parametros, nivel) {	
		if(parametros == null || parametros == undefined){
			return $http.get(config.baseUrl + "/asignaciones/inicio");	
		}else{
			return $http.get(config.baseUrl + "/asignaciones/inicio", {params:{"filtro":JSON.stringify(parametros), "nivel": nivel}});		
		}			
	};


	this.getExpedientes = function(parametros, nivel) {
		return $http.get(config.baseUrl + "/asignaciones/busqueda", {params:{"filtro":JSON.stringify(parametros), "nivel": nivel}});		
	};
	
	this.getExpedientesEtiquetados = function(parametro, nivel) {
		return $http.get(config.baseUrl + "/asignaciones/etiquetas/"+parametro+"/filtro", {params:{"nivel": nivel}});		
	};
	
	this.getLote = function(parametro, filtro) {		
		if(filtro == null || filtro == undefined){
			return $http.get(config.baseUrl + "/asignaciones/lotes/"+parametro+"/detalle");
		}else{
			this.setFiltro(filtro);
			return $http.get(config.baseUrl + "/asignaciones/lotes/"+parametro+"/detalle", {params:filtro});
		}
	};
	
	this.getCsv = function(parametro, filtro) {		
		if(filtro == null || filtro == undefined){
			return $http.get(config.baseUrl + "/asignaciones/csvs/"+parametro+"/detalle");
		}else{
			this.setFiltro(filtro);
			return $http.get(config.baseUrl + "/asignaciones/csvs/"+parametro+"/detalle", {params:filtro});
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
	
	//Adicionado Gibran
	this.confirmaPuntoTactico = function(idPtLote){
		return $http.get(config.baseUrl + "/verificaPuntoTactico", {params:{"idPtLote": idPtLote}});
	}

	this.catalogoMotivoGeneral = function(idPtLote){
		return $http.get(config.baseUrl + "/getCatalogoMotivoRevalidacionGeneral");
	}

	this.catalogoMotivoDetalle = function(idPtLote){
		return $http.get(config.baseUrl + "/getCatalogoMotivoRevalidacionDetalle");
	}

	this.getCatEtiquetasReval = function(){
		return $http.get(config.baseUrl + "/getEtiquetas");
	}

	this.getCatalogoValidadores = function(){
		return $http.get(config.baseUrl + "/getCatalogoValidadores");
	}
	
	this.reasignaPTLote = function(AsignaRevalidacionVO){
		return $http.put(config.baseUrl + "/asignaciones/reasignaPTLote", AsignaRevalidacionVO);
	}

	this.deshabilitarEtiqueta = function(id){
		return $http.get(config.baseUrl + "/asignaciones/deshabilitarEtiqueta", {params:{"id": id}});
	}
		
});