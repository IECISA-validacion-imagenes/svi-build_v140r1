angular.module(appTeclo).service("filtroExpedienteService", function($http, config) {

	this.crearFiltro = function(nbEtiqueta, filtro) {
		var etiqueta = {"nbEtiqueta": nbEtiqueta, "filtro": filtro}
		return $http.post(config.baseUrl + "/expedientes/filtros", etiqueta);
	};

});