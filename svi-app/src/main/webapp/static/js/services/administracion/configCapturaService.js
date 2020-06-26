angular.module(appTeclo).service(
		"configCapturaService",
		function($http, config) {
			this.guardarConfiguracion = function(configuracionVO) {
				return $http.put(config.baseUrl
						+ "/aplicacion/capturas/1/configuracion",
						configuracionVO);
			}

			this.recuperarConfiguracion = function(configuracionVO) {
				return $http.get(config.baseUrl
						+ "/aplicacion/capturas/1/configuracion");
			}

		});
