angular.module(appTeclo)
.service('evaReportesService',
function($http, $route,config) {

	this.catalogoTipoBusqueda = function(){
		return $http.get(config.baseUrl + "/catTipoBusqReporteValidacion");
	}
	 
	this.consultaReporteValidacion = function (VO){
		return $http({
			method: 'GET',
			url: config.baseUrl + "/consultaReporteResultValidacion",
			params: {
				"identregable":VO.idEntregable
			},
			dataType: "json",
			header :{ "Content-type": "application/json"
			},
			responseType: 'json'
		});
	} 
	
    this.generarZip = function (idPt){
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
    }
});