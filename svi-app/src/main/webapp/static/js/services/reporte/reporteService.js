angular.module(appTeclo).service("reporteService", function($http, config,$timeout) {
	
	this.getReportesLista = function () {
		return $http.get(config.baseUrl + "/reportes/getReportesLista");
	};
	
	this.getDataPefilesReportes = function () {
		return $http.get(config.baseUrl + "/reportes/getDataPefilesReportes");
	};
	
	this.getReporte = function (idReporte) {
		return $http.get(config.baseUrl + "/reportes/getReporte",{
					params:{'idReporte' : idReporte}
		}
		);
	};
	
	this.persisteConfigReportePerf = function (data) {
		var voObject = {
				perfiles: data.perfiles,
				reportes : data.reportes,
				interseccion : data.interseccion
		};
		return $http.post(config.baseUrl + "/reportes/persisteConfigReportePerf",voObject);
	};
	
	this.methodConsultaDinamica = function (parametros) {
		return $http.post(config.baseUrl + "/consulta/reporte",parametros);
	};
	
	this.methodDescargaExcel = function (parametros) {
		return $http({
			method : 'POST',
			url : config.baseUrl + "/consulta/excel",
			dataType : "json",
			data : parametros,
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	};
	
	this.save = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		//a.click();
		$timeout(function() {
			a.click();
		},100);
	};
	
	/**Método que reemplaza carcateres espaciales
	 */
	this.getCleanedString = function (cadena){
			//Lo queremos devolver limpio en minusculas
		   cadena = cadena.toLowerCase();
		   //Definimos los caracteres que queremos eliminar
		   var specialChars = "!@#$^&%*()+=-[]\/{}|:<>?,."; 
		    // Los eliminamos todos
		   for (var i = 0; i < specialChars.length; i++) {
		       cadena= cadena.replace(new RegExp("\\" + specialChars[i], 'gi'), '');
		   }
		   // Quitamos espacios y los sustituimos por _ porque nos gusta mas asi
		   cadena = cadena.replace(/ /g,"");//reemplazar espacios
		   cadena = cadena.replace(/_/g,"");//reemplazar guiones bajos y medios
		   cadena = cadena.replace(/-/g,"");//reemplazar guiones bajos y medios
		   // Quitamos acentos y "ñ". Fijate en que va sin comillas el primer parametro
		   cadena = cadena.replace(/á/gi,"a");
		   cadena = cadena.replace(/é/gi,"e");
		   cadena = cadena.replace(/í/gi,"i");
		   cadena = cadena.replace(/ó/gi,"o");
		   cadena = cadena.replace(/ú/gi,"u");
		   cadena = cadena.replace(/ñ/gi,"n");
		   return cadena;//retornar cadena limpia
	};
	
	/*Método para normalizar las cabeceras
	 * esto se hizo para que el ordenamiento
	 * dinamico funcione sin problemas
	 * */
	this.normalizeHeaders = function (listaHeaders){
		 var obj = {};
		 var headReturn = [];
		 var strHead= '';
		 	if(listaHeaders.length > 0){
		      for(var i=0; i < listaHeaders.length; i++){
		        strHead = this.getCleanedString(listaHeaders[i]);
		        obj = {id:strHead,name:listaHeaders[i]};
		        headReturn.push(obj);
		      }
		    }
		 return headReturn;
	};
	
	this.convertMapKeyAndValue = function(values, keys){
		listReturn = [];
		var object = null;
		for(var x=0; x < values.length; x++){
			object = {};
			for(var y=0; y < values[x].length; y++){
				object[keys[y].id]=values[x][y];
			}
			listReturn.push(object);
		}
		return listReturn;
	};
	
	this.consultaPrevia = function (idParametro, valuesParams){
		return $http.get(config.baseUrl + "/consulta/consultaPrevia",{
				params:{'idParametro' : idParametro,'valores':valuesParams}
			}
		);
	};
	
	/*obtener lista de reportes para la consulta de reportes dinamicos*/
	this.getReporteListaDinamic = function (objectPeticion) {
		return $http.post(config.baseUrl + "/consulta/getReporteListaDinamic",objectPeticion);
	};
	/* Editar reportes */
	this.getReporteEdit = function (idReporte){
		return $http.get(config.baseUrl + "/consulta/getReporteEdit",{
			params:{'idReporte' : idReporte}
		});
	};
	/* Eliminar Reporte  */
	this.deleteReporteDinamico = function (idReporte) {
		return $http.delete(config.baseUrl + "/consulta/deleteReporteDinamico",{
				params:{'idReporte' : idReporte}
			}
		);
	};
	this.changeEstatusRep = function (objectVO){
		return $http.put(config.baseUrl + "/consulta/changeEstatusRep", objectVO);
	};
	
	
 });
