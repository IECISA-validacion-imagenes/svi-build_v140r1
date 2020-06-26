angular.module(appTeclo)
.controller("loginController",
function($rootScope,$scope,$location,$window,$timeout,growl,
		loginService,storageService,logOutService, messageFactory) {
	
	var btnLogin = $('#login-btn');
		
	$scope.inputType = 'password';
	$scope.b_hidePassword = true;
	
	$scope.showClave = function() {
		$scope.$evalAsync(function() {
			$scope.inputType = 'text';
			$scope.b_hidePassword = false;
		});
	}
	
	$scope.hideClave = function() {
		$scope.$evalAsync(function() {
			$scope.inputType = 'password';
			$scope.b_hidePassword = true;
		});
	}

	$scope.getInconsistenciasPorValidar = function(){
		loginService.tieneIncidenciasPorValidar().success(function(data) {
			if(data>0){
				if(data == 1){
					$timeout(growl.info('Tienes '+data+' inconsistencia por validar',{title:"Aviso:", ttl: 5000}), 1000);	
				}else{
					$timeout(growl.info('Tienes '+data+' inconsistencias por validar',{title:"Aviso:", ttl: 5000}), 1000);
				}
				
			}
		}).error(function(data) {
			$scope.error = data;
			if(data.status == undefined || data == null) {
	 			$scope.error = {	
					message:messageFactory.getErrorMessage(420),
				}
			}
		});
	}
	
	$scope.login = function() {
		
		loginService.login($scope.user).success(function(data) {
			
			btnLogin.attr('disabled', 'true');
			storageService.setToken(data.token);
			logOutService.StartTimer();
			$location.path('/index');
			$timeout($rootScope.comprobarConfiguracion, 500);
			$timeout($scope.getInconsistenciasPorValidar, 1500);
			
		}).error(function(data) {
			
			$scope.error = data;
			
			if(data.status == undefined || data == null) {
	 			$scope.error = {
					
					message:messageFactory.getErrorMessage(420),
	 			
				}
			}
		});
	};
});