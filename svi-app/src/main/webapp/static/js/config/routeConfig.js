angular.module(appTeclo).config(function($routeProvider, $locationProvider) {
	
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/login", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/error", {
		templateUrl : "views/error.html",
	});
	
	$routeProvider.when("/index",{
		templateUrl : "views/index.html",
	});
	
	$routeProvider.when("/accesoNegado", {
		templateUrl : "views/accesoNegado.html",
	});
	
	$routeProvider.otherwise({redirectTo: "/index"});
	
	/*___________________________________________________________
	________** INICIO -> ADMINISTRACIÓN CONTROLLERS ** ________*/
	$routeProvider.when("/administracionModificaClave",{
		templateUrl : "views/administracion/administracionModificaClave.html",
		controller : "administracionModificaClaveController"
	});
	
//	Configurar Aplicación
	$routeProvider.when("/configuracion", {
		templateUrl : "views/administracion/configuracionApp.html",
		controller: "configuracionAppController"
    });
	
//	Configurar formulario de revalidación
	$routeProvider.when("/configuracion/captura", {
		templateUrl : "views/administracion/captura.html",
		controller: "configuracionCapturaController"
    });


//	Componentes Web
	$routeProvider.when("/componentesWeb",{
		templateUrl : "views/administracion/resources/pluginsWeb.html",
		controller : "pluginsWebController"
	});
	
//	Validación de imágenes
	$routeProvider.when("/validacionImagen",{
		templateUrl : "views/validacion/validacion.html",
		controller : "validacionController"
	});

//	Validación de inconsistencias
	$routeProvider.when("/validacionInconsistencias",{
		templateUrl : "views/validacion/validacionInconsistencias.html",
		controller : "validacionInconsistenciasController"
	});
	
//	Consulta de expedientes
	$routeProvider.when("/expedientes",{
		templateUrl : "views/expediente/expedientes.html",
		controller : "buscadorExpedientesController",
		resolve: {
			filtroString: function (expedienteService, $route, $location) {
						return expedienteService.inicializaFiltro();
		}}
	});
	
	$routeProvider.when("/expedientes/:idRegistroCsv/detalle",{
		templateUrl : "views/expediente/expediente.html",
		controller : "expedienteController",
		resolve: {
			expediente: function (expedienteService, $route, $location) {
						return expedienteService.getExpediente($route.current.params.idRegistroCsv, $location.search());
		}}
	});
	
	
	
//	Carga de Directorios
	$routeProvider.when("/cargaCCV",{
		templateUrl : "views/aplicacionCargaDirectoriosPT/aplicacionCargaDirectoriosPT.html",
		controller : "cargaDirectoriosPTController"
	});
	
//	Reporte Validaciones
	$routeProvider.when("/reporteValidacion",{
		templateUrl : "views/evaReportes/resultadoValidacion.html",
		controller : "reporteValidacionController"
	});
	
//	Supervision
	$routeProvider.when("/asignaIncidencias",{
		templateUrl : "views/supervision/asignaciones.html",
		controller : "buscadorAsignacionesController",
		resolve: {
			filtroString: function (asignacionService, $route, $location) {
				return asignacionService.inicializaFiltro();
		}}
	});
	
	$routeProvider.when("/asignaIncidencias/lotes/:idPtLote/detalle",{
		templateUrl : "views/supervision/asignacion.html",
		controller : "asignacionController",
		resolve: {
			itemSeleccionado: function (asignacionService, $route, $location) {
						return asignacionService.getLote($route.current.params.idPtLote, $location.search());
		}}
	});
	
	$routeProvider.when("/asignaIncidencias/csvs/:idPtCsv/detalle",{
		templateUrl : "views/supervision/asignacion.html",
		controller : "asignacionController",
		resolve: {
			itemSeleccionado: function (asignacionService, $route, $location) {
						return asignacionService.getCsv($route.current.params.idPtCsv, $location.search());
		}}
	});

	/* Reporteador Dinamico 
	 * 							Lista de reportes */
	$routeProvider.when("/reporteDinamico",{
		templateUrl : "views/reporte/dinamicReporte.html",
		controller : "dinamicReporteController"
	}); 
	
	/* Asignar reprote por perfil */
	$routeProvider.when("/asignacionReportes",{
		templateUrl : "views/administracion/asignarReportes.html",
		controller : "asignarReportesController",
		resolve :{
			dataRepPerfiles: function (reporteService) {
				return reporteService.getDataPefilesReportes();
			}
		}
	});
	/* editar reprote */
	$routeProvider.when("/consultaReporte",{
		templateUrl : "views/reporte/consultaReporteDinamico.html",
		controller : "consultaReporteDinamicoController"
	});
	/* Reprote Dinamico */
	$routeProvider.when("/reporteDinamicoConsulta/:idReporte", {
		templateUrl : "views/reporte/formBusqueda.html",
		controller: "formBusquedaController",
		resolve: {
		reporte: function (reporteService, $route) {
					return reporteService.getReporte($route.current.params.idReporte);
		}}
	});
	/***ALRA DE REPORTES***/
	$routeProvider.when("/altaReporte",{
		templateUrl : "views/reporte/nuevoReporteDinamico.html",
		controller : "nuevoReporteController"
	});	
	/**EDICIÓN DE REPORTES*/
	$routeProvider.when("/editaReporte/:idReporte",{
		templateUrl : "views/reporte/nuevoReporteDinamico.html",
		controller : "editaReporteDinamicoController",
		resolve:{
			reporteVO: function (reporteService, $route) {
				return reporteService.getReporteEdit($route.current.params.idReporte);
			}
		}
	});
//	CancelaAsignacion
	$routeProvider.when("/disponerAsignacion",{
		templateUrl : "views/cancelaAsignacion/cancelaAsignacion.html",
		controller : "cancelaAsignacionController"
	});
	/*___________________________________________________________
	________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/
	
});