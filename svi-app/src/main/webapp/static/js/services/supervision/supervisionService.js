angular.module(appTeclo)
.service('supervisionService',
function($http, $route,config) {

	let filter = null;

	this.catalogoTipoBusqueda = function(parametros){
		if(parametros == null || parametros == undefined){
			return $http.get(config.baseUrl + "/catTipoBusqIncidencia");
		}else{
			return $http.get(config.baseUrl + "/catTipoBusqIncidencia",{params:{"filtro":JSON.stringify(parametros)}});
		}	
	}

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
	
	this.consultaIncidencias = function (parametros){
		if(parametros == null || parametros == undefined){
			alert('No hay información de búsqueda o la información esta incompleta');
		}else{
			return $http.get(config.baseUrl + "/consultaIncidencia",{params:{"filtro":JSON.stringify(parametros)}});
		}
		// return $http.get(config.baseUrl + "/consultaIncidencia",{params:{"idTipoBusq":VO.tipo, "valor":VO.busca, "idPeriodo":VO.periodo}});
	}
	
	this.consultaDetalleIncidenciaPT = function(idPt){
		return $http.get(config.baseUrl + "/consultaDetalleIncidenciaPT",{params:{"idPt":idPt}});
	}
	
	this.consultaDetalleIncidenciaCSV = function(idCsv){
		return $http.get(config.baseUrl + "/consultaDetalleIncidenciaCSV",{params:{"idCsv":idCsv}});
	}

	this.consultaDetalleIncidenciaNuExpedientes = function(listIdRegistro){
		return $http.get(config.baseUrl + "/consultaDetalleIncidenciaCSV2",{params:{"listIdRegistro":listIdRegistro}});
	}
	
	this.catValidadorVO = function(){
		return $http.get(config.baseUrl + "/catValidadoresIncidencia");
	}
	
	this.revalidar = function(idTipo, VO){
		console.log(VO);
		console.log(idTipo);
		if(idTipo == 1){
			return $http.put(config.baseUrl + "/reasignaPT", VO);
		}else if(idTipo >= 2){
			return $http.put(config.baseUrl + "/reasignaCSV",VO);
		}
	}
	
	this.confirmaPuntoTactico = function(idPtLote){
		return $http.get(config.baseUrl + "/verificaPuntoTactico", {params:{"idPtLote": idPtLote}});
	}
	
	this.convertListToMap = function(values, keys){
		listReturn = [];
		var object = null;
		for(var x=0; x < values.length; x++){
			object = {};
			for(var y=0; y < keys.length; y++){
				object[keys[y].name]=values[''];
			}
			listReturn.push(object);
		}
		return listReturn;
	}

	this.getCatalog = function(parametros) {	
		if(parametros == null || parametros == undefined){
			return $http.get(config.baseUrl + "/getCataalogosFiltro");	
		}else{
			return $http.get(config.baseUrl + "/getCataalogosFiltro", {params:{"filtro":JSON.stringify(parametros)}});		
		}			
	};
	
	this.setFilter = value => {
		this.filter = value;
	};

	this.getFilter = () => {
		return this.filter;
	};

	this.inicializaFiltro = function(){
		return this.getFilter();
	}

	this.getCatEtiquetasReval = function(){
		return $http.get(config.baseUrl + "/getEtiquetas");
	}

	this.getAccess = function(){
		return $http.get(config.baseUrl + "/getAcccess");
	}

    /*this.generarZip = function (idPt){
    	return $http({
		       method: 'GET',
		       url: config.baseUrl + "/generarZIPCSV",
		       params: {"idPtLote":idPt},
		       dataType: "json",
		       header :{ "Content-type": "application/json",
		       	"Accept"    : "application/x-zip-compressed"
		       },
		       responseType: 'arraybuffer'
		   });
	}
    
    this.downloadZip = function(file, fileName){
    	var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
    }*/
});