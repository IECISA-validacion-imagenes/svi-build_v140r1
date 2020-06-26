angular.module(appTeclo).controller('asignarReportesController', 
		function($window,$scope,reporteService,growl,showAlert,dataRepPerfiles,$timeout) {

	$scope.reporte = null;
	$scope.showReporte = false;
	
	$scope.object = {co:'Confirmación ',de:'de ',us:'Guardado'};
	
	$scope.original = {
		reportes : dataRepPerfiles.data.reportes,
		perfiles : dataRepPerfiles.data.perfiles,
		interseccion : dataRepPerfiles.data.interseccion
	};
	
	$scope.edition = {};
	
	$scope.setReporte = function (id,nbReporte) {
	   $scope.reporte = id;
	   if($scope.reporte != null)
		   $scope.showReporte = true;
	   $scope.nbReporte = nbReporte;
	   $scope.getBoolean();
	};
	
	$scope.setPerfil = function (id) {
	   $scope.perfil = id;
	   $scope.getBoolean();
	};
	
	$scope.reset = function(){

		$timeout(function (){
			$scope.edition = {};
			$scope.nbReporte = '';
			angular.copy($scope.original, $scope.edition);
			$scope.getBoolean();
			$scope.showReporte = false;
			$scope.reporte = null;
		}, 800);
		
		$scope.getBoolean();
		$scope.showReporte = false;
		//$scope.verifyAll();
		$scope.nbReporte = '';
		$scope.edition = $scope.original;
		//$scope.getBoolean();
		//$scope.showReporte = false;
		$scope.reporte = null;


		$timeout(function (){
			$scope.edition = {};
			$scope.nbReporte = '';
			angular.copy($scope.original, $scope.edition);
			$scope.getBoolean();
			$scope.showReporte = false;
			$scope.reporte = null;
		}, 800);
		//$scope.getBoolean();
		//$scope.showReporte = false;
		//$scope.verifyAll();


	};
	
	$scope.getBoolean = function(){
		for(var x in $scope.edition.reportes){
			found = false;
			for(var y in $scope.edition.interseccion){
				if($scope.edition.interseccion[y].id.perfilId == $scope.perfil
				&& $scope.edition.interseccion[y].id.idReporte == $scope.edition.reportes[x].idReporte){
					found = true;
					break;
				}
			}
			$scope.edition.reportes[x].checked = found;
		}
		for(var x in $scope.edition.perfiles){
			found = false;
			for(var y in $scope.edition.interseccion){
				if($scope.edition.interseccion[y].id.idReporte == $scope.reporte
				&& $scope.edition.interseccion[y].id.perfilId == $scope.edition.perfiles[x].perfilId){
					found = true;
					break;
				}
			}
			$scope.edition.perfiles[x].checked = found;
		}
		$scope.verifyAll();
	};
	
	$scope.verifyAll = function(){
		$scope.verifyAllReportes();
		$scope.verifyAllPerfiles();
	};
	
	$scope.verifyAllPerfiles = function(){
		$scope.allPerfiles = true;
		for(var y in $scope.edition.perfiles){
			found = false;
			for(var x in $scope.edition.interseccion){
				if($scope.edition.interseccion[x].id.idReporte == $scope.reporte
				&& $scope.edition.interseccion[x].id.perfilId == $scope.edition.perfiles[y].perfilId){
					found = true;
					break;
				}
			}
			if(!found){
				$scope.allPerfiles = false;
				break;
			}
		}
	};
	
	$scope.verifyAllReportes = function(){
		$scope.allReportes = true;
		for(var y in $scope.edition.reportes){
			found = false;
			for(var x in $scope.edition.interseccion){
				if($scope.edition.interseccion[x].id.perfilId == $scope.perfil
				&& $scope.edition.interseccion[x].id.idReporte == $scope.edition.reportes[y].idReporte){
					found = true;
					break;
				}
			}
			if(!found){
				$scope.allReportes = false;
				break;
			}
		}
	};
	
	$scope.toogleAllPerfiles = function(){
		if($scope.allPerfiles){
			for(var x in $scope.edition.perfiles){
				found = false;
				for(var y in $scope.edition.interseccion){
					if($scope.edition.interseccion[y].id.idReporte == $scope.reporte
					&& $scope.edition.interseccion[y].id.perfilId == $scope.edition.perfiles[x].perfilId){
						found = true;
						break;
					}
				}
				if(!found){
					$scope.edition.interseccion.push({id:{
						idReporte  : $scope.reporte,
						perfilId : $scope.edition.perfiles[x].perfilId}});
				}
			}
		}else{
			do{
				spliced=false;
				for(var x in $scope.edition.interseccion){
					if($scope.edition.interseccion[x].id.idReporte == $scope.reporte){
						$scope.edition.interseccion.splice(x,1);
						spliced = true;
						break;
					}
				}
			}while(spliced);
		}
		$scope.getBoolean();
	};
	

	
	$scope.marcarDesmarcarPerfil = function(RorP,idPerfil){
		  $scope.perfil = idPerfil;
		  var index=0;
		for(x=0;x<=$scope.edition.perfiles.length;x++){
			if($scope.edition.perfiles[x].perfilId=== idPerfil){
				index=x;
				break;
			}
		}
		
		if(RorP == 'R'){
			if($scope.edition.reportes[index].checked){
				id = {
						perfilId  : $scope.perfil,
						idReporte : $scope.edition.reportes[index].idReporte
					};
					$scope.edition.interseccion[$scope.edition.interseccion.length] = {id:id};
			}
			else{
				for(var x in $scope.edition.interseccion){
					if($scope.edition.interseccion[x].id.idReporte == $scope.edition.reportes[index].idReporte
						&& $scope.edition.interseccion[x].id.perfilId == $scope.perfil){
						$scope.edition.interseccion.splice(x, 1);
						break;
					}
				}
			}
		}else{//P
			if($scope.edition.perfiles[index].checked){
				id = {
					idReporte  : $scope.reporte,
					perfilId : $scope.edition.perfiles[index].perfilId};
				$scope.edition.interseccion[$scope.edition.interseccion.length] = {id:id};
			}
			else{
				for(var x in $scope.edition.interseccion){
					if($scope.edition.interseccion[x].id.perfilId == $scope.edition.perfiles[index].perfilId
						&& $scope.edition.interseccion[x].id.idReporte == $scope.reporte){
						$scope.edition.interseccion.splice(x, 1);
						break;
					}
				}
			}
		}
		$scope.verifyAll();
	};
	
	$scope.revertir = function(){
		showAlert.confirmacion("Los cambios se perderán, ¿Desea continuar?",confirmacionFinal, $scope.object, cancelarFinal);
	};
	
	$scope.guardar = function(){
		showAlert.confirmacion("¿Está seguro de guardar esta configuración?",confirmGuadar, $scope.object, confirmCancelar);
	};
	
	confirmGuadar = function(){
		reporteService.persisteConfigReportePerf($scope.edition).success(function(data, status, headers, config) {
			if(data != null){
				growl.success($scope.mensajeModal('Configuración guardada correctamente'),{title:'¡Éxito!', ttl:4000});
				$scope.original = data;
				$scope.reset();
			}else{
				growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud'), {ttl:3000});
				
			}
		}).error(function(data, status, headers, config) {
			growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud')+': ' + data.message, {ttl:3000});
		});
	};
	confirmCancelar = function (){return;};
	
	
	confirmacionFinal = function() {$scope.reset();};
	cancelarFinal = function(){return;};
	$scope.reset();

});