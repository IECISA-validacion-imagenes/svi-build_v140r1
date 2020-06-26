angular.module(appTeclo).controller("revalidacionController",function($window, $location, growl, expediente,  $routeParams, $scope,$timeout,$filter,supervisionService,growl, showAlert)
{
	$scope.tipoBusq = {valor:null}; 
	$scope.detalleRevalidacion={lista:null};
	$scope.catMotivo={lista:null};
	$scope.validadores={listaOriginal:null};
	$scope.incidenciaSeleccionada = {idArchivoCsv:undefined, idPtLote:undefined}

	$scope.tipoBusq.valor = $routeParams.tipoBusqueda;

	var data = expediente.data;
	console.log(expediente);
	console.log($scope.tipoBusq.valor);
	
	$scope.detalleRevalidacion.lista = data.detallePT;
	$scope.catMotivo.lista = data.catMotivo;
	$scope.validadores.listaOriginal = data.catValidadores;
	$scope.validadores.lista = angular.copy($scope.validadores.listaOriginal);
	console.log($scope.incidenciaSeleccionada);
	console.log($scope.detalleRevalidacion.lista);

	$scope.regresar = function (){
		$location.path("/asignaIncidencias");
	};

	$scope.revalidar1= function(revalidaTodo){
		$scope.object="hey";
		showAlert.confirmacion("¿Deseas continuar con la asignación a revalidar?", revalidar, revalidaTodo, noaction);
	}
	function noaction(){
	}

	function revalidar(revalidaTodo){
		var isAllAsigned = true;

		if(!revalidaTodo){
			if($scope.tipoBusq.valor == 1){
				$scope.detalleRevalidacion.lista.map(function(el){
					if(el.validador == undefined)
						isAllAsigned = false;
				});
				
				if(!isAllAsigned){
					growl.error("Hay archivos que no se le han asignado validadores")
				}
				
				if(isAllAsigned && $scope.catMotivo.valor == undefined){
					isAllAsigned = false;
					growl.error("No se ha especificado el motivo")
				}
				
			} else if($scope.tipoBusq.valor == 2){
				if($scope.detalleRevalidacion.validador != undefined){
					if($scope.catMotivo.valor == undefined){
						isAllAsigned = false;
						growl.error("No se ha especificado el motivo")
					}
				}else{
					isAllAsigned = false;
					growl.error("No se le ha asignado validador")
				}
			}
		}else{
			if($scope.tipoBusq.valor == 1){
				
				//Asignamos todos al mismo validador
				$scope.detalleRevalidacion.lista = $scope.detalleRevalidacion.lista.map(function(el){
					el.validador = $scope.detalleRevalidacion.validador;
					return el;
				});
				
				if(isAllAsigned && $scope.catMotivo.valor == undefined){
					isAllAsigned = false;
					growl.error("No se ha especificado el motivo")
				}
			} else if($scope.tipoBusq.valor == 2){
				if($scope.detalleRevalidacion.validador != undefined){
					if($scope.catMotivo.valor == undefined){
						isAllAsigned = false;
						growl.error("No se ha especificado el motivo")
					}
				}else{
					isAllAsigned = false;
					growl.error("No se le ha asignado validador")
				}
			}
		}
		
		if(isAllAsigned){
			$scope.reqVO = angular.copy($scope.detalleRevalidacion);
			$scope.reqVO.motivo = $scope.catMotivo.valor;
			console.log($scope.reqVO);
			if(!revalidaTodo){
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? angular.copy($scope.detalleARevalidar) : $scope.reqVO.lista;
			}else{
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? $scope.reqVO.lista.map(function(el){return el.idRegistroCsv}) : $scope.reqVO.lista;
			}
			//$scope.reqVO.idArchivoCsv = $scope.incidenciaSeleccionada.idArchivoCsv != undefined ? $scope.incidenciaSeleccionada.idArchivoCsv : undefined;
			console.log($scope.reqVO);
			
			if($scope.reqVO.lista.length != 0){
				supervisionService.revalidar($scope.tipoBusq.valor, $scope.reqVO).success(function(data){
					$scope.catMotivo.valor = undefined;
					$scope.detalleRevalidacion.validador = undefined;
					$scope.detalleARevalidar = [];
					$("#select2-comboMotivoRevalidaRapida-container").text("Seleccione");
					$("#select2-comboRevalidaValidadorRapida-container").text("Seleccione");
					if(revalidaTodo){
						$scope.showModalRevalidacionRapida = false;
					}else{
						$scope.showModalRevalidacion = false;
					}
					growl.success('La asignación se realizó correctamente');
					$scope.regresar();
					growl
					$scope.incidenciaSeleccionada = {};				
				}).error(function(err){
					showAlert.error(err);
				});
			}else{
				growl.error("Debe de seleccionar al menos un registro para revalidar")
			}
		}
	}

});