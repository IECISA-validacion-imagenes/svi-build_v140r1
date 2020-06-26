angular.module(appTeclo).service("reporteDinamicosServices", function($http, config) {
	
	/* Listar reportes dados de alta */
	this.listaReportes = function(){
		return $http.get(config.baseUrl + "/listaReportes", {});
	};
	

});