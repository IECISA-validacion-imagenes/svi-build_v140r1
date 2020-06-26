angular.module(appTeclo).controller("reporteValidacionController",function($scope,evaReportesService,growl, showAlert)
{
	$scope.advancedSearch=false;
	$scope.listaEntregables=[];
	$scope.comboEntregablesSelected=[];
	

	resetBusquedaVO = function(){
		$scope.busquedaVO = {
				idEntregable:null,
				fhInicio:null,
				fhFin:null
			}
	};
	resetBusquedaVO();

	$scope.changeSearch = function(){
		$scope.advancedSearch=!$scope.advancedSearch;
		if($scope.advancedSearch===false){
			$scope.listaReporte=[];
			resetBusquedaVO();
			$scope.busquedaAllPTS();
		}
	}

	$scope.hasValue = function(value){ 
		var hasval=false;
		
		if(value!==null){
			if(value !== undefined){
				if(value.trim()!==''){
					hasval=true;
				}
			}		
		}		
		return hasval;
	};

	$scope.init= function(){
		evaReportesService.catalogoTipoBusqueda().success(function(data){
			$scope.listaEntregables= data;
		}).error(function(){
			alert("No se ha podido consultar la lista de entregables disponibles");
		});
	}

	$scope.busquedaAllPTS = function(){
		evaReportesService.consultaReporteValidacion($scope.busquedaVO).success(function(data){
			if(data.length >0){
				$scope.listaReporte = data;
				console.log(data);
			}else{
				showAlert.aviso("No se han encontrado registros para descargar.");
			}
		}).error(function(){
			alert("algo ha fallado");
		});
	}
	$scope.busquedaAllPTS();

	$scope.consultarReportesValidacion= function(){
		$scope.busquedaVO.idEntregable = $scope.comboEntregablesSelected === null ? null : $scope.comboEntregablesSelected.idCat;
		//!$scope.hasValue($scope.busquedaVO.fhInicio)||!$scope.hasValue($scope.busquedaVO.fhFin)
		if($scope.busquedaVO.idEntregable===null||$scope.busquedaVO.idEntregable===undefined){
			alert('Todos los campos son requeridos');
		}else{
			$scope.listaReporte = []
			console.log($scope.busquedaVO);
			evaReportesService.consultaReporteValidacion($scope.busquedaVO).success(function(data){
				if(data.length >0){
					$scope.listaReporte = data;
					console.log(data);
				}else{
					showAlert.aviso("No se han encontrado registros para descargar.");
				}
			}).error(function(){
				alert("algo ha fallado");
			});			
		}
	}

	$scope.init();
	

	/********************************************************* */
	
	
	$scope.tipoBusq = {
		lista:[],
		valor:{}
	}
	
	$scope.catPeriodo = {
			lista:[],
			valor:{}
		}

	
	/*
	$scope.consultarReportesValidacion= function(){
		if($scope.formConsultaReporte.$invalid){
			showAlert.requiredFields($scope.formConsultaReporte);
		}else{
			$scope.listaReporte = []
			$scope.busquedaVO.tipo = tipoBusq.idCat;
			$scope.busquedaVO.periodo = periodo != undefined ? periodo.nameCat != undefined ? periodo.nameCat :" ":" ";
			
			evaReportesService.consultaReporteValidacion($scope.busquedaVO).success(function(data){
				if(data.length >0){
					$scope.listaReporte = data;
				}else{
					showAlert.aviso("No se han encontrado registros.");
				}
			}).error(function(){
				alert("algo ha fallado");
			});
		}
	}
	*/
	$scope.descargarZIPReporte = function(idPt){
		evaReportesService.generarZip(idPt).success(function(data, status, headers){
			var filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/zip;base64,'});
			evaReportesService.downloadZip(file, filename);
		}).error(function(err, code){
			if(code = 400)
			showAlert.error("No se puede descargar el archivo, tiene archivos csv pendientes");
		});
	}
	
});