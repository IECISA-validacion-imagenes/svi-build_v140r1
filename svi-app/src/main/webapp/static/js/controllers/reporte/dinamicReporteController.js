angular.module(appTeclo).controller('dinamicReporteController',function($window,$scope,reporteService,growl,showAlert,reporteDinamicosServices) {
	
	$scope.mostrarBotonMius=true;
	$scope.nombreReporte="";
	$scope.classCollapsedBoxPrincipal="box box-danger";
	$scope.styleCollapse={};
	$scope.helpers = {};
	
	
	$scope.cambiaIcono=function(fafIconReportes){
		if(fafIconReportes=="fa fa-chevron-down")
			fafIconReportes="fa fa-chevron-up";
		else
			fafIconReportes="fa fa-chevron-down";
	return fafIconReportes;
	}

	/*Funciones para Agrupar */
	function agruparLista(l) {
		var many = 3,
			object;
		$scope.listaAgrupada = [];
		for (var i = 0; i < l.length; i += many) {
			object = {
				reporte1 : l[i]
			};

			if (l[i + 1] && (many == 2 || many == 3)) {
				object.reporte2 = l[i + 1];
			}
			if (l[i + (many - 1)] && many == 3) {
				object.reporte3 = l[i + 2];
			}
			$scope.listaAgrupada.push(object);
		}
		//console.log($scope.listaAgrupada);
	}
	;

	configReporte = function(allReportes) {
		var listReportes = allReportes.reportes;
		var listTipos = allReportes.tipoReporte;
		
		angular.forEach(listTipos, function(tipo, key){
			tipo.subReportes = [];
			angular.forEach(listReportes, function(reporte, key) {
				if(tipo.idTipoReporte === reporte.idTipoReporte){
					tipo.subReportes.push(reporte);
				}
			});
	    });
		
		if (listTipos.length > 0) {
			$scope.lAux = [];
			for (var i = 0; i < listTipos.length; i++) {
				if (listTipos[i].subReportes.length >= 1) {
					$scope.lAux.push(listTipos[i]);
				}
			}
		}
		angular.copy($scope.lAux,listTipos);
		agruparLista(listTipos);
};

	/*Funciones para cargar los reportes que se puede accesar ,segun el nivel de usuario y permisso */

//$scope.direccion = function (urlReporte,IdReporte){
//	$scope.direccionCatalogos =urlReporte;
//}

	getlistaReportes = function (){
		reporteDinamicosServices.listaReportes().success(function(data){
				$scope.listCatReportes = [];
				$scope.listCatReportes = configReporte(data);
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
		    growl.warning("No Existen Reportes Dados de Alta ", {ttl: 3000});
			$scope.TiposReportes = {};
		});
	};

	$scope.mostrarPanelTipoReporte = function(urlReporte,IdReporte){
			$scope.minimPanelBusqueda=true;
			$scope.direccionCatalogos =urlReporte;
			$scope.classCollapsedBoxPrincipal="box box-danger collapsed-box";
			$scope.classIconBoxPrincipal="fa fa-plus";
			$scope.mostrarBotonMius=false;
			$scope.styleCollapse="diaplay:none !important;";
	}
	getlistaReportes();

	/*$scope.buscarRegistros = function (idReporte){
		console.log(idReporte);
	};*/
});