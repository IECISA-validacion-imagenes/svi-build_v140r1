angular.module(appTeclo).controller('consultaReporteDinamicoController',
	function($scope, growl, showAlert, $location, $filter, reporteService, $timeout, admiReporteService) {
		
		$scope.listaReporte = [];
		$scope.rowCollection = [];
		$scope.dataListaReporteTMP = [];
		$scope.cabeceras = [];
		$scope.filtroBusqueda = "";
		$scope.catalogosBus = {
				tiposReportes : [{'idTipoReporte':100,
								  'cdTipoReporte':'TODOS',
								  'nbTipoReporte':'TODOS',
								  'txTipoReporte':'TODOS'}]
		};
		$scope.filtroBusquedaRep= {'tipoReporteVO':{},
								'nbReporte': undefined,
								'valorRequerdio':false};
		
		
		$scope.gridOptions = {
			data : [],
			urlSync : false,
			pagination : {
				itemsPerPage : '10'
			},
			enableHorizontalScrollbar : true,
			enableVerticalScrollbar : true
		};
		//asociarValoresReporte (listaReportes.data);
		
		$scope.refreshData = function() {
			$scope.gridOptions.data = $filter('filter')($scope.dataListaReporteTMP, $scope.filtroBusqueda);
			$scope.rowCollection = $scope.gridOptions.data;
		};
		
		$scope.comprobarTipoReporte = function (data){
			if(data!= null && data.cdTipoReporte === 'TODOS'){
				$scope.filtroBusquedaRep.valorRequerdio = false;
				$scope.filtroBusquedaRep.nbReporte = undefined;
			}else if(data!= null && data.cdTipoReporte !== 'TODOS'){
				$scope.filtroBusquedaRep.valorRequerdio = true;
			}
		};
		
		$scope.ejecutarBusquedaReportes = function (datos){
			if($scope.formBusqueda.$invalid) {
				showAlert.requiredFields($scope.formBusqueda);
				growl.error('Formulario incompleto');
				return;
			}else{
				peticionListaReportes(datos);
			}
		};
		
		/**Función para recorrer los formatos de descarga
		 * y filtrar el tipo de agrupación de hojas
		 * @author Jorge Luis
		 * */
		function comprobarFormatosDisponibles() {
			var listaFormatos = [];
			var objetoFormato = {};
			var extensiones = '';
			var columnaPaginacion = '';
			var columnaAgrupacion = '';
			for (var i = 0; i < $scope.listaReporte.length; i++) {
				listaFormatos = $scope.listaReporte[i].listaReportesFormatos;
				if (listaFormatos.length > 0) {
					extensiones = '';
					columnaAgrupacion = '';
					for (var j = 0; j < listaFormatos.length; j++) {
						if (j > 0)
							extensiones += ', ';
						extensiones += listaFormatos[j].formatoDescarga.cdFormato;
					}
					columnaAgrupacion = listaFormatos[0].nbColumnaAgrupacion;
					if (columnaAgrupacion != null) {
						objetoFormato = {
							'extensiones' : extensiones,
							'agrupacion' : listaFormatos[0].agrupacionHojas.cdTipoAgrupacion + ' : ' + columnaAgrupacion
						};
					} else {
						objetoFormato = {
							'extensiones' : extensiones,
							'agrupacion' : listaFormatos[0].agrupacionHojas.cdTipoAgrupacion
						};
					}
					$scope.listaReporte[i].objetoFormato = objetoFormato;
				}
			}
		};
		
		/**Función para mostrar la confirmación al accionar el botón de eliminar
		 * @author Jorge Luis
		 * */
		$scope.eliminarReporte = function (reporteVO){
			showAlert.confirmacion("El reporte se eliminará definitivamente, ¿Desea continuar?", 
					confirmaEliminacion, reporteVO, cancelaEliminacion,reporteVO);
		};
		
		/**Función para eliminar el reporte actual seleccionado
		 * @author Jorge Luis
		 * */
		confirmaEliminacion = function (reporteVO){
			reporteService.deleteReporteDinamico(reporteVO.idReporte).success(function(data, status, headers, config) {
				if(data){
					growl.success('El reporté se eliminó correctamente' , {ttl: 5000});
					deleteElementIntoList(reporteVO,$scope.listaReporte);
					/*$timeout(function (){//timeout para que angular le de tiempo limpiar el form
						peticionListaReportes($scope.filtroBusquedaRep);
					},1000);*/
				}else{
					growl.error('ERROR: ' + data.message, {ttl:5000});
				}
			}).error(function(data, status, headers, config) {
				if(data.message != null){
					growl.error('ERROR: '+': ' + data.message, {ttl:5000});	
				}else{
					growl.error('Algo salió mal al procesar la solicitud'+': ' + data, {ttl:5000});
				}
			})
		};
		cancelaEliminacion = function (reporteVO){
			return;
		};
		
		function deleteElementIntoList (objectDelete, reportesList){
			if(reportesList.length > 0)
				for(var i=0; i < reportesList.length; i++){
					if(objectDelete.idReporte == reportesList[i].idReporte){
						reportesList.splice(i, 1);
					}
				}
			comprobarFormatosDisponibles();
			angular.copy(reportesList, $scope.gridOptions.data);
			angular.copy(reportesList, $scope.rowCollection);
			angular.copy(reportesList, $scope.dataListaReporteTMP);
		};
		
		/**Función para mandar petición y recuperar las lista de reportes nuevamente
		 * @author Jorge Luis
		 * */
		function peticionListaReportes (objectPeticion){
				$scope.listaReporte = [];
				reporteService.getReporteListaDinamic(objectPeticion).success(function(data, status, headers, config) {
					asociarValoresReporte(data);
				}).error(function(data, status, headers, config) {
					if(data != null && data.message != null && data.status === 404){
						growl.error(data.message, {ttl:4000});	
					}else{
						growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud')+': ' + data, {ttl:4000});
					}
				});
		};
		/**Función para poblar las listas temporales de filtro
		 * @author Jorge Luis
		 * */
		function asociarValoresReporte (listaRecuperada){
			angular.copy(listaRecuperada,$scope.listaReporte);
			comprobarFormatosDisponibles();
			angular.copy($scope.listaReporte, $scope.gridOptions.data);
			angular.copy($scope.listaReporte, $scope.rowCollection);
			angular.copy($scope.listaReporte, $scope.dataListaReporteTMP);
		};
		
		$scope.editarReporte = function (reporteVO){
			showAlert.confirmacion("Se redireccionará a otra página para la edición, ¿Desea continuar?", 
					confirmaEdicionReporte, reporteVO, cancelaEdicionReporte,reporteVO);
		};
		
		confirmaEdicionReporte = function (reporteVO){
			$timeout(function() {
				$location.path('/editaReporte/' + reporteVO.idReporte);
			}, 300);
		};
		cancelaEdicionReporte = function (reporteVO){
			return;
		};
		/**Inicia bloque para consultar todos los catálogos 
		 * que se usarán en el alta de reportes*/
		function obtenerTipoReportes(){
			admiReporteService.listTiposReportes().success(function(data) {
					if(data.length > 0){
						var listTMP = $scope.catalogosBus.tiposReportes.concat(data); 
						angular.copy(listTMP, $scope.catalogosBus.tiposReportes);
						$scope.filtroBusquedaRep.tipoReporteVO = $scope.catalogosBus.tiposReportes[0];
						$("#select2-tipoBusqueda-container").text($scope.catalogosBus.tiposReportes[0].nbTipoReporte);
						$scope.filtroBusquedaRep.valorRequerdio = false;
					}
				}).error(function(data) {
					$scope.catalogosBus.tiposReportes = [];
			});
		};
		
		$scope.comprobarEstatus = function (data, newValue, oldValue){
			var reporte = {'reporteVO': data, 'newVal': newValue, 'oldValue':oldValue};
			if(newValue == 0){
				showAlert.confirmacion("El reporte se deshabilitará y no estará disponible para consulta",
						confirmaDeshabilitar, reporte, cancelaDeshabilitar,reporte);
				$timeout(function() {data.stActivo = oldValue;}, true);
			}else if(newValue == 1){
				showAlert.confirmacion("El reporte habilitará y estará disponible para consulta y modificación",
						confirmaHabilitar, reporte, cancelaHabilitar,reporte);
				$timeout(function() {data.stActivo = oldValue;}, true);
			}
		};
		
		confirmaDeshabilitar = function (data){
			data.reporteVO.stActivo = data.newVal;
			var objectPetition = {'idReporte':data.reporteVO.idReporte, 'stActivo':data.newVal};
			toUpdateStReporte(objectPetition);
		};
		cancelaDeshabilitar = function (data){
			return;
		};
		
		confirmaHabilitar = function (data){
			data.reporteVO.stActivo = data.newVal;
			var objectPetition = {'idReporte':data.reporteVO.idReporte, 'stActivo':data.newVal};
			toUpdateStReporte(objectPetition);
		};
		cancelaHabilitar = function (data){
			return;
		};
		
		function toUpdateStReporte(objectVO){
			reporteService.changeEstatusRep(objectVO).success(function(data, status, headers, config) {
				if(data)
					growl.success('El estatus se actualizó correctamente', {ttl:4000});
			}).error(function(data, status, headers, config) {
				growl.error('Algo salió mal al actualizar el estatus del reporte: ' + data, {ttl:4000});
			});
		};
		
		obtenerTipoReportes();
		
		
	});