angular.module(appTeclo).controller("cancelaAsignacionController", function($scope, cancelaAsignacionService, growl, showAlert) {
	"use strict";
	
	$scope.busquedaVO={
			
	};
	
	$scope.tipoBusq = {
		lista:[],
		valor:{}
	}
	
	$scope.listaAsignaciones = [];
	
	$scope.init = function(){
		cancelaAsignacionService.catalogoBusqueda().success(function(data){
			$scope.tipoBusq.lista = data;
			$scope.tipoBusq.valor = $scope.tipoBusq.lista[0];
			$('#select2-comboBusquedaAsignacion-container').text($scope.tipoBusq.valor.nameCat);
		}).error(function(err){
			
		});
	};
	
	$scope.consultarAsignaciones = function(tipoBusq){
		$scope.busquedaVO.tipo = tipoBusq.idCat;
		if($scope.formConsultaAsignacion.$invalid){
			showAlert.requiredFields($scope.formConsultaAsignacion);
		}else{
			$scope.listaAsignaciones = [];
			$scope.busquedaVO.busca = $scope.busquedaVO.busca == undefined ? " " : $scope.busquedaVO.busca;   
			cancelaAsignacionService.consultaAsignaciones($scope.busquedaVO).success(function(data){
				if(data.length > 0){
					$scope.listaAsignaciones = data;
				}else{
					showAlert.aviso("No se encontraron registros");
				}
			}).error(function(err){
				
			});
		}
	}
	
	$scope.cancelarAsignacion= function(asigna){
		showAlert.confirmacion("¿Estas seguro que desea revocarle la asignacion a '"+asigna.nbNombre+"'?, el archivo '"+asigna.nbArchivo+"' quedara disponible para asignar a otro validador", function(){
			cancelaAsignacionService.cancelarAsignacion(asigna.idAsignaValidacion).success(function(data){
				if(data){
					showAlert.aviso("Se revocó la asignacion al usuario '"+asigna.nbNombre+"'");
					$scope.listaAsignaciones = $scope.listaAsignaciones.filter(function(el,idx){return el.idAsignaValidacion != asigna.idAsignaValidacion}) 
				}
			}).error(function(err){
				showAlert.error(err);
			})
			},null, null, null);
	}
	
	$scope.init(); 
});

