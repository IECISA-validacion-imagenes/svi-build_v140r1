angular.module(appTeclo).controller("cargaDirectoriosPTController",
    function($rootScope, $scope, $location, $window, $timeout,cargaDirectoriosPTService,growl, showAlert, config) {

        "use strict";
        $scope.crearArchivo = function(){
        	cargaDirectoriosPTService
        	.crearArchivo()
    		.success(
    				function(data) {
    					if (data.success) {
    						var jnlpFile = config.sourceJnlp;
    			            deployJava.launchWebStartApplication(jnlpFile,'1.7');
    					} else {
    						console.log(data.message);
    						
    					}

    				}).error(function(data) {

    		});

        }
       
});/*Fin de liberacionImagenesController*/

