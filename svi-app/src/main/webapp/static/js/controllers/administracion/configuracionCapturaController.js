angular.module(appTeclo).controller(
		'configuracionCapturaController',
		function($scope, $filter, showAlert, growl, configCapturaService,
				administracionService, ModalService) {

			// Banderas de edicion o solo lectura en formulario de revalidacion
			$scope.formConfigCaptura = {};

			$scope.formConfigCaptura.pDplacaDelantera = true;
			$scope.formConfigCaptura.pDoficial = true;
			$scope.formConfigCaptura.pDsinPlaca = true;
			$scope.formConfigCaptura.pDentidadDelantera = true;
			$scope.formConfigCaptura.pDsinEntidad = true;
			$scope.formConfigCaptura.pTplacaTrasera = true;
			$scope.formConfigCaptura.pToficial = true;
			$scope.formConfigCaptura.pTsinPlaca = true;
			$scope.formConfigCaptura.pTentidadTrasera = true;
			$scope.formConfigCaptura.pTsinEntidad = true;
			$scope.formConfigCaptura.eXsilueta = true;
			$scope.formConfigCaptura.eXdoblePlaca = true;
			$scope.formConfigCaptura.eXpochimovil = true;
			$scope.formConfigCaptura.eXperfil = true;
			$scope.formConfigCaptura.eXclasificacion = true;

			$scope.init = function() {
				configCapturaService.recuperarConfiguracion().success(
						function(data) {
							$scope.formConfigCaptura = data;
						}).error(function(data) {
					growl.error(data.msg, {
						title : "Error:",
						ttl : 3000
					});

				});
			}

			$scope.guardarConfiguracion = function() {
				if ($scope.formConfigCaptura.$invalid) {
					growl.error('Formulario incompleto');
					return;
				}
				configCapturaService.guardarConfiguracion(
						$scope.formConfigCaptura).success(function(data) {

					growl.success('La configuracion ha sido guardada', {
						title : "¡Éxito!",
						ttl : 3000
					});

				}).error(function(data) {
					growl.error(data.msg, {
						title : "Error:",
						ttl : 3000
					});

				});
			}

			$scope.init();
		});
