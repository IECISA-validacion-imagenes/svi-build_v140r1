angular.module(appTeclo).controller('asignacionController',
	function ($window, $scope, asignacionService, growl, showAlert, $filter,
		$location, itemSeleccionado, $rootScope, $uibModalStack,$timeout) {

		// lista de items disponibles a reasignación
		$scope.detalleAsignacion = {};
		// filtro usado en la pantalla anterior
		$scope.filtro = {}

		$scope.dataRegistros = [];

		//Variablespara construir el objeto que se manda a revalidar
		$scope.AsignaRevalidacionVO = {};

		$scope.catMotivoGeneral = {
			lista: [],
			valor: undefined
		}

		$scope.catMotivoDetalle = {
			lista: [],
			valor: undefined
		}

		$scope.validadores = {}
		$scope.detalleARevalidar = [];

		$scope.opcionesWebuipopoverEtiq = {
			closeable: true,
			onHide: function (e) {
			},
		};

		$scope.getCatMotivoGeneral = function () {
			asignacionService.catalogoMotivoGeneral().success(function (data) {
				$scope.catMotivoGeneral.lista = data;
			}).error(function (err) {
				growl.error(err.message, { title: "Error:", ttl: 4000 });
			});
		}
		$scope.getCatMotivoGeneral();

		$scope.getCatMotivoDetalle = function () {
			asignacionService.catalogoMotivoDetalle().success(function (data) {
				$scope.catMotivoDetalle.lista = data;
			}).error(function (err) {
				growl.error(err.message, { title: "Error:", ttl: 4000 });
			});
		}
		$scope.getCatMotivoDetalle();

		$scope.getCatalogoValidadores = function () {
			asignacionService.getCatalogoValidadores().success(function (data) {
				$scope.validadores.lista = data;
			}).error(function (err) {
				growl.error(err.message, { title: "Error:", ttl: 4000 });
			});
		}
		$scope.getCatalogoValidadores();

		$scope.inicializarObjetoAsignReval = function () {
			$scope.AsignaRevalidacionVO = {
				idPtLote: null,
				idTiporeval: null,
				filtro: null,
				idMotivoGeneralLote: null,
				idMotivoDetalleLote: null,
				idValidadorLote: null,
				listaArchivosCsv: []
			}
		}

		function getScopeData() {
			$scope.AsignaRevalidacionVO = $rootScope.AsignaRevalidacionVO;
			for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
				if ($scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo === $scope.detalleAsignacion.idPtCsv) {

					$scope.catMotivoGeneral.lista.map(function (el) {
						if (el.idCat == $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idMotivo) {
							$scope.catMotivoGeneral.valor = el;
							$('#select2-comboMotivoRevalidaRapida-container').text($scope.catMotivoGeneral.valor.nameCat);
						}
					});
					$scope.validadores.lista.map(function (el) {
						if (el.idValidador == $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idValidador) {
							$scope.validadores.valor = el;
							$('#select2-comboRevalidaValidadorRapida-container').text($scope.validadores.valor.nbValidador);
						}
					});

					// console.log($scope.catMotivoGeneral);
					// console.log($scope.validadores);

				}
			}
		}

		$scope.checkDetalleArchivos = function (val, check) {
			if (check) {
				$scope.detalleARevalidar.push(val);
				// console.log($scope.detalleARevalidar);
			} else {
				$scope.detalleARevalidar = $scope.detalleARevalidar.filter(function (el) { return el != val });
				// console.log($scope.detalleARevalidar);
			}
		}

		$scope.init = function () {
			$scope.detalleAsignacion = itemSeleccionado.data;
			$scope.dataRegistros = itemSeleccionado.data.asignaciones;
			$scope.filtro = itemSeleccionado.data.filtroAplicado;
			// console.log($scope.detalleAsignacion);
			for(var i=0;i<$scope.detalleAsignacion.asignaciones.length;i++){
				if($scope.detalleAsignacion.asignaciones[i].stPreseleccion==true){
					$scope.checkDetalleArchivos($scope.detalleAsignacion.asignaciones[i].idRegistroCsv, true);
				}
			}
			

			if ($scope.detalleAsignacion.idPtCsv != null) {
				if ($rootScope.AsignaRevalidacionVO != undefined) {
					// console.log($rootScope.AsignaRevalidacionVO);
					
					$timeout(getScopeData, 1000);
				} else {
					$scope.inicializarObjetoAsignReval();
				}
			}
		}

		$scope.regresar = function () {
			asignacionService.setFiltro(JSON.stringify($scope.filtro));
			$location.path("/asignaIncidencias").search({});
		}

		function getDetalle(idRegistroCsv) {
			if (!isNaN(idRegistroCsv)) {
				asignacionService.getDetalle(
					parseInt(idRegistroCsv), null).success(
						function (data) {
							$scope.expediente = data;
						}).error(function (err) {
							alert("Hubo un error");
						})
			}
		}
		function cerrarPopOver() {
			WebuiPopovers.hideAll();
		}

		$scope.init();

		//Adicionado Gibran


		function createGrupoExpedientesVO(idMotivo, filtro, listaExpedientes) {
			this.idMotivo = idMotivo;
			this.filtro = filtro
			this.listaExpedientes = listaExpedientes
			return {
				idMotivo: this.idMotivo,
				filtro: this.filtro,
				listaExpedientes: this.listaExpedientes
			}
		};

		function createArchivoCsvVO(idArchivo, idValidador, idMotivo, listaGrupoExpedientes) {
			this.idArchivo = idArchivo;
			this.idValidador = idValidador;
			this.idMotivo = idMotivo;
			this.listaGrupoExpedientes = listaGrupoExpedientes;
			return {
				idArchivo: this.idArchivo,
				idValidador: this.idValidador,
				idMotivo: this.idMotivo,
				listaGrupoExpedientes: this.listaGrupoExpedientes
			}
		};

		$scope.preparacionValidacion = function (listarchivos, tipoRevalidacion) {

			if (tipoRevalidacion == 'revalDetallePT') {
				$scope.AsignaRevalidacionVO.idPtLote = $scope.detalleAsignacion.idPtLote;
				$scope.AsignaRevalidacionVO.idTiporeval = 6;
				$scope.AsignaRevalidacionVO.filtro = JSON.stringify($scope.filtro);
				$scope.AsignaRevalidacionVO.listaArchivosCsv = [];

				var idMotivo = $scope.catMotivoGeneral.valor.idCat;
				var idMotivoDet = $scope.catMotivoDetalle.valor.idCat;
				for (var i = 0; i < listarchivos.length; i++) {
					var listGrupos = [];
					var grupoExp = createGrupoExpedientesVO(
						idMotivoDet,
						JSON.stringify($scope.filtro),
						[]
					);
					listGrupos.push(grupoExp);
					var archivoCSV = createArchivoCsvVO(
						listarchivos[i].idArchivoCsv,
						listarchivos[i].idValidador.idValidador,
						idMotivo,
						listGrupos
					);

					$scope.AsignaRevalidacionVO.listaArchivosCsv.push(archivoCSV);

				}
			} else if (tipoRevalidacion == 'revalDetalleCSV') {

				var listaExpedientes = listarchivos;
				if ($scope.AsignaRevalidacionVO.listaArchivosCsv.length > 0) {
					//Buscamos si ya existe el archivo en la lista y de ser así solo le añadimos un grupo de expedientes
					for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
						if ($scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo === $scope.detalleAsignacion.idPtCsv) {
							var grupoExp = createGrupoExpedientesVO(
								$scope.catMotivoDetalle.valor.idCat,
								JSON.stringify($scope.filtro),
								listaExpedientes
							);
							$scope.AsignaRevalidacionVO.listaArchivosCsv[i].idValidador = $scope.validadores.valor.idValidador;
							$scope.AsignaRevalidacionVO.listaArchivosCsv[i].idMotivo = $scope.catMotivoGeneral.valor.idCat;

							$scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes.push(grupoExp);
							crearArchivo = false;
							break;
						} else { crearArchivo = true; }
					}
				} else {
					crearArchivo = true;
				}
				//De ser necesario creamos un archivo y lo añadimos al grupo de archivos
				if (crearArchivo) {


					var listGrupos = [];
					var grupoExp = createGrupoExpedientesVO(
						$scope.catMotivoDetalle.valor.idCat,
						JSON.stringify($scope.filtro),
						listaExpedientes
					);
					listGrupos.push(grupoExp);
					var archivoCSV = createArchivoCsvVO(
						$scope.detalleAsignacion.idPtCsv,
						$scope.validadores.valor.idValidador,
						$scope.catMotivoGeneral.valor.idCat,
						listGrupos
					);
					$scope.AsignaRevalidacionVO.listaArchivosCsv.push(archivoCSV);
				}
				//Lo guardo en el scope para que se pueda utilizar en la vista anterior
				$rootScope.AsignaRevalidacionVO = $scope.AsignaRevalidacionVO;
				$scope.regresar();
			}
		}

		$scope.getArchivosARevalidar = function () {
			var listarchivos = [];
			$scope.dataRegistros.map(function (el) {
				if (el.idValidador != null)
					listarchivos.push(el);
			});
			return listarchivos
		}

		$scope.validacionCampos = function (listarchivos, tipoRevalidacion) {
			if (tipoRevalidacion == 'revalDetallePT') {
				if (listarchivos.length > 0) {
					if ($scope.catMotivoGeneral.valor == null) {
						growl.error("Selecciona un motivo de revalidación CSV")
						return false;
					} else if ($scope.catMotivoDetalle.valor == null) {
						growl.error("Selecciona un motivo de revalidación del Expediente")
						return false;
					}
					return true;
				} else {
					growl.error('Debes seleccionar el validador para el archivo que deseas enviar a revalidación');
					return false;
				}
			} else if (tipoRevalidacion == 'revalDetalleCSV') {
				if (listarchivos.length > 0) {
					if ($scope.catMotivoGeneral.valor == null) {
						growl.error("Selecciona un motivo de revalidación CSV")
						return false;
					} else if ($scope.catMotivoDetalle.valor == null) {
						growl.error("Selecciona un motivo de revalidación del Expediente")
						return false;
					} else if ($scope.validadores.valor == null) {
						growl.error("Selecciona al validador")
						return false;
					}
					return true;
				} else {
					growl.error('Para agregar a revalidación debes seleccionar al menos un expediente');
					return false;
				}
			}


		}

		function deleteEtiqueta(id){
			if($scope.filtro.idEtiqReval!=null){
				asignacionService.deshabilitarEtiqueta($scope.filtro.idEtiqReval).success(function (data) {
					if (data) {
						growl.success('La etiqueta se deshabilitó satisfactoriamente.');
					} else {
						growl.error('No es posbile deshabilitar la etiqueta en este momento. Inténtelo más tarde.');
					}
				}).error(function (err) {
					showAlert.aviso(err.message);
		
				})
			}
		}


		$scope.revalidar = function (tipoRevalidacion) {

			if (tipoRevalidacion == 'revalDetallePT') {
				var listarchivos = $scope.getArchivosARevalidar();
				var objdata = { listarchivos: listarchivos, tipoRevalidacion: tipoRevalidacion };
				if ($scope.validacionCampos(listarchivos, tipoRevalidacion)) {
					var txtcant='';
					if(listarchivos.length==1){txtcant='archivo'}else{txtcant='archivos'}
					showAlert.confirmacion("¿Estas seguro de mandar a revalidación " + listarchivos.length + " de " + $scope.dataRegistros.length + " "+txtcant+"?", confirmRevalidacion, objdata, noaction);
					// console.log($scope.dataRegistros);
				}
			} else if (tipoRevalidacion == 'revalDetalleCSV') {
				var objdata = { listarchivos: $scope.detalleARevalidar, tipoRevalidacion: tipoRevalidacion };
				var txtcant='';
				if($scope.detalleARevalidar.length==1){txtcant='expediente'}else{txtcant='expedientes'}
				if ($scope.validacionCampos($scope.detalleARevalidar, tipoRevalidacion)) {
					showAlert.confirmacion("¿Estas seguro de agregar a revalidación " + $scope.detalleARevalidar.length + " de " + $scope.dataRegistros.length + " "+txtcant+"?", confirmRevalidacion, objdata, noaction);
					// console.log($scope.dataRegistros);
				}
			}



		}
		function noaction() {
		}
		
		function confirmRevalidacion(objdata) {
			// console.log(objdata);

			$scope.preparacionValidacion(objdata.listarchivos, objdata.tipoRevalidacion);
			$uibModalStack.dismissAll();

			if (objdata.tipoRevalidacion == 'revalDetallePT') {

				asignacionService.reasignaPTLote($scope.AsignaRevalidacionVO).success(function (data) {
					growl.success("Los archivos CSV han sido asignados para su revalidación");
					if (data) {					
						for(var i=0;i<$scope.dataRegistros.length;i++){
							for(var j=0;j<$scope.AsignaRevalidacionVO.listaArchivosCsv.length;j++){
								if ($scope.dataRegistros[i].idArchivoCsv == $scope.AsignaRevalidacionVO.listaArchivosCsv[j].idArchivo){
									$scope.dataRegistros.splice(i, 1);
									break;
								}
							}
						}

						if($scope.filtro.idEtiqReval .valor!=null){
							showAlert.confirmacion("¿Deseas deshabilitar la etiqueta seleccionada?", deleteEtiqueta, null, noaction);
						}

						$scope.regresar();
						growl.success('El archivo se ha asignado correctamente');
					}
	
	
				}).error(function (err) {
					growl.error(err.message, { title: "Error:", ttl: 9000 });
					return false;
				})

			}else if (objdata.tipoRevalidacion == 'revalDetalleCSV') {
			
			}
			
		}

		$scope.checkAllDetalleArchivos = function(check){
			
			if(check){
				$scope.dataRegistros = $scope.dataRegistros.map(function(el){
					el.stPreseleccion = true;
					return el;
				})
				$scope.detalleARevalidar = $scope.dataRegistros.map(function(el){
					return el.idRegistroCsv;
				});
			}else{
				$scope.dataRegistros = $scope.dataRegistros.map(function(el){
					el.stPreseleccion = false;
					return el;
				})
				$scope.detalleARevalidar = [];
			}
		}

	});