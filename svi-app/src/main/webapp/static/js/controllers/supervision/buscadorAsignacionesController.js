/**
 * Maneja la lógica para la busqueda de expedientes y recargar el grid de
 * resultados
 * 
 * @param $window
 * @param $scope
 * @param asignacionService
 * @param growl
 * @param showAlert
 * @returns
 */
angular.module(appTeclo).controller('buscadorAsignacionesController', function ($window, $scope, $timeout, $filter, asignacionService, growl, showAlert, $location, filtroString, $rootScope) {

	$scope.maxRegistros = 120000;
	$scope.tipoBusq = {
		lista: [],
		valor: 2
	}
	$scope.catPeriodo = {
		lista: [],
		valor: undefined
	}

	$scope.catCSV = {
		lista: [],
		valor: undefined
	}

	$scope.catPT = {
		lista: [],
		valor: undefined
	}
	$scope.catMarca = {
		lista: [],
		valor: undefined
	}

	$scope.catSubMarca = {
		lista: [],
		valor: undefined
	}

	$scope.catPerfil = {
		lista: [],
		valor: undefined
	}

	$scope.catPerfilDistinct = {
		lista: [],
		valor: undefined
	}

	$scope.catIncidencia = {
		lista: [],
		valor: undefined
	};

	$scope.catBusqMarca = {
		lista: [],
		valor: undefined
	}

	$scope.catBusqPT = {
		lista: [],
		valor: undefined
	}

	$scope.catBusqCSV = {
		lista: [],
		valor: undefined
	};

	$scope.catBusqSubMarca = {
		lista: [],
		valor: undefined
	}

	$scope.catBusqPerfil = {
		lista: [],
		valor: undefined
	}
	$scope.filtro = undefined;
	$scope.dataRegistros = [];
	$scope.isOpen = true;
	$scope.cdPlaca = "";
	$scope.nuExpediente = "";

	// Adición GIbran
	$scope.catEtiquetas = {
		lista: [],
		valor: undefined
	}

	$scope.catMotivoGeneral = {
		lista: [],
		valor: undefined
	}

	$scope.catMotivoDetalle = {
		lista: [],
		valor: undefined
	}
	$scope.estatus = {
		validado: "VALIDADO",
		revalidado: "REVALIDADO",
		pendiente: "PENDIENTE",
		inactivo: "INACTIVO",
		reasignado: "REASIGNADO",
		anadido: "AÑADIDO"
	};
	$scope.revalidacionRapida = {
		pt: "revalRapidaPT",
		csv: "revalRapidaCSV"
	};

	$scope.showModalRevalidacionRapida = false;
	$scope.validadores = {}
	//Nivel CSV
	$scope.currentArchivoCSV = [];

	// Variablespara construir el objeto que se manda a revalidar
	$scope.AsignaRevalidacionVO = {};

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
	$scope.inicializarObjetoAsignReval();

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

	function createDetalleVO(idArchivo, idMotivoArchivo, idValidadorArchivo, idMotivoExpediente, totalExpedientes) {
		this.idArchivo = idArchivo;
		this.idMotivoArchivo = idMotivoArchivo;
		this.idValidadorArchivo = idValidadorArchivo;
		this.idMotivoExpediente = idMotivoExpediente;
		this.totalExpedientes = totalExpedientes;
		return {
			idArchivo: this.idArchivo,
			idMotivoArchivo: this.idMotivoArchivo,
			idValidadorArchivo: this.idValidadorArchivo,
			idMotivoExpediente: this.idMotivoExpediente,
			totalExpedientes: this.totalExpedientes
		}
	};


	// primer metodo al cargar la pagina
	// obtiene catalogos y/o expedientes
	// dependiendo si existe filtro anterior
	$scope.init = function () {
		asignacionService.setFiltro(undefined);
		if (filtroString != undefined && filtroString != "" && filtroString.toString().startsWith("{")) {
			$scope.filtro = JSON.parse(filtroString);
		}
		getCargaInicial();
	}

	function pintaReasignadosLoteScope(estatus) {
		for (var j = 0; j < $scope.dataRegistros.length; j++) {
			if ($scope.dataRegistros[j].idPtLote === $scope.AsignaRevalidacionVO.idPtLote) {
				$scope.dataRegistros[j].nbEstado = estatus;
				break;
			}
		}
	}

	function pintaReasignadosCSVScope(estatus) {
		for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
			for (var j = 0; j < $scope.dataRegistros.length; j++) {
				if ($scope.dataRegistros[j].idArchivoCsv === $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo) {
					$scope.dataRegistros[j].nbEstado = estatus;
				}
			}
		}
	}

	function pintaReasignadosScope(estatus) {
		if ($rootScope.AsignaRevalidacionVO != undefined) {
			$scope.AsignaRevalidacionVO = $rootScope.AsignaRevalidacionVO;
			// console.log($scope.AsignaRevalidacionVO);
			for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
				for (var j = 0; j < $scope.dataRegistros.length; j++) {
					if ($scope.dataRegistros[j].idArchivoCsv === $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo) {
						$scope.dataRegistros[j].nbEstado = estatus;
					}
				}
			}

		}
	}

	function getCargaInicial() {
		asignacionService.getInicio($scope.filtro, $scope.tipoBusq.valor).success(function (data) {
			$scope.catPeriodo.lista = data.catPeriodo;
			$scope.catCSV.lista = data.catCSV;
			$scope.catPT.lista = data.catPT;
			$scope.catMarca.lista = data.catMarca;
			$scope.catSubMarca.lista = data.catSubMarca;
			$scope.catPerfil.lista = data.catPerfil;
			$scope.catPerfilDistinct.lista = data.catPerfilDistinct;
			$scope.catIncidencia.lista = data.catIncidencias;
			$scope.catEtiquetas.lista = data.catEtiquetas;
			if (data.expedientes != null && data.expedientes != undefined && data.expedientes.length > 0) {
				$scope.dataRegistros = data.expedientes;
				// console.log($scope.dataRegistros);
				pintaReasignadosScope($scope.estatus.anadido);
			}

			refrescarFormularioDeBusqueda();

			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();

		}).error(function (err) {
			showAlert.aviso(err.message);
		});
	}

	function refrescarFormularioDeBusqueda() {
		$scope.catBusqPT.lista = [...$scope.catPT.lista];
		$scope.catBusqCSV.lista = [...$scope.catCSV.lista];
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		$scope.catEtiquetas.lista = [...$scope.catEtiquetas.lista];

		if ($scope.filtro != null && $scope.filtro != undefined) {
			$scope.cdPlaca = $scope.filtro.cdPlaca;
			$scope.nuExpediente = $scope.filtro.nuExpediente;
			$scope.catPeriodo.valor = getEntregasSeleccionadas();
			$scope.catBusqPT.valor = getPtsSeleccionados();
			$scope.catBusqCSV.valor = getCsvsSeleccionados();
			$scope.catBusqMarca.valor = getMarcasSeleccionadas();
			$scope.catBusqSubMarca.valor = getSubMarcasSeleccionadas();
			$scope.catBusqPerfil.valor = getPerfilesSeleccionados();
			$scope.catIncidencia.valor = getIncidenciasSeleccionadas();
			$scope.catEtiquetas.valor = getEtiquetasSeleccionadas();
			$scope.filtrarEntregas();
			$scope.filtrarPuntosTacticos();
			$scope.tipoBusq.valor = $scope.filtro.tipoBusqueda;
		}

	}

	function deleteEtiqueta(id){
		if($scope.catEtiquetas.valor!=null){	
			asignacionService.deshabilitarEtiqueta(parseInt($scope.catEtiquetas.valor.idEtiqReval)).success(function (data) {
				if (data) {
					
					for(var i=0;i<$scope.catEtiquetas.lista.length;i++){
						if($scope.catEtiquetas.lista[i].idEtiqReval==$scope.catEtiquetas.valor.idEtiqReval){
							$scope.catEtiquetas.lista.splice(i,1);
							$('#select2-comboEtiqueta-container').text('Seleccione');
							$scope.catEtiquetas.valor=null;
							break;
						}
					}
					growl.success('La etiqueta se deshabilitó satisfactoriamente.');

				} else {
					growl.error('No es posbile deshabilitar la etiqueta en este momento. Inténtelo más tarde.');
				}
			}).error(function (err) {
				showAlert.aviso(err.message);
	
			})

		}
	}



	$scope.verDetalle = function (idPtLote, idPtCsv) {
		if($scope.catEtiquetas.valor!=null){
			$rootScope.idEtiqueta = $scope.catEtiquetas.valor.idEtiqReval;
		}
		
		var filtroString = JSON.stringify($scope.filtro);
		// console.log(filtroString);
		if (idPtLote != null && !isNaN(idPtLote)) {
			$location.path('/asignaIncidencias/lotes/' + idPtLote + '/detalle').search({ filtro: filtroString });
		} else {
			$rootScope.AsignaRevalidacionVO = $scope.AsignaRevalidacionVO;
			$location.path('/asignaIncidencias/csvs/' + idPtCsv + '/detalle').search({ filtro: filtroString });

		}
	};


	$scope.buscarExpedientes = function () {
		if ($scope.tipoBusq.valor === 2) {

		} else {
			$scope.inicializarObjetoAsignReval();
		}
		getExpedientes();
	}

	function getExpedientes() {
		$scope.filtro = {};
		$scope.dataRegistros = [];

		$scope.filtro.listaEntregas = $scope.catPeriodo.valor != undefined ? $scope.catPeriodo.valor.map(function (el) { return el.idCat }) : [];
		$scope.filtro.listaLotes = $scope.catBusqPT.valor != undefined ? $scope.catBusqPT.valor.map(function (el) { return el.idPt }) : [];
		$scope.filtro.listaCsvs = $scope.catBusqCSV.valor != undefined ? $scope.catBusqCSV.valor.map(function (el) { return el.idArchivoCsv }) : [];
		$scope.filtro.listaMarcas = $scope.catBusqMarca.valor != undefined ? $scope.catBusqMarca.valor.map(function (el) { return el.idPtMarca }) : [];
		$scope.filtro.listaSubMarcas = $scope.catBusqSubMarca.valor != undefined ? $scope.catBusqSubMarca.valor.map(function (el) { return el.idPtSubMarca }) : [];
		$scope.filtro.listaPerfiles = $scope.catBusqPerfil.valor != undefined ? $scope.catBusqPerfil.valor.map(function (el) { return el.idPtPerfil }) : [];
		$scope.filtro.listaIncidencias = $scope.catIncidencia.valor != undefined ? $scope.catIncidencia.valor.map(function (el) { return el.idIncidencia }) : [];
		$scope.filtro.cdPlaca = $scope.cdPlaca.toUpperCase();
		$scope.filtro.nuExpediente = $scope.nuExpediente.toUpperCase();
		$scope.filtro.tipoBusqueda = $scope.tipoBusq.valor;

		if (!validarFiltroBusqueda()) {
			growl.error('Por favor ingrese un parámetro de búsqueda.');
			return false;
		}

		asignacionService.getExpedientes($scope.filtro, $scope.tipoBusq.valor).success(function (data) {
			if (data.length > 0) {
				$scope.dataRegistros = data;
				// console.log($scope.dataRegistros);
				$scope.isOpen = false;
			} else {
				growl.error('No hay registros');
			}
		}).error(function (err) {
			showAlert.aviso(err.message);

		})

	}

	function validarFiltroBusqueda() {
		if ($scope.filtro == undefined) {
			return false;
		}
		if ($scope.filtro.listaEntregas != undefined && $scope.filtro.listaEntregas.length > 0) { return true }
		if ($scope.filtro.listaLotes != undefined && $scope.filtro.listaLotes.length > 0) { return true }
		if ($scope.filtro.listaCsvs != undefined && $scope.filtro.listaCsvs.length > 0) { return true }
		if ($scope.filtro.listaMarcas != undefined && $scope.filtro.listaMarcas.length > 0) { return true }
		if ($scope.filtro.listaSubMarcas != undefined && $scope.filtro.listaSubMarcas.length > 0) { return true }
		if ($scope.filtro.listaPerfiles != undefined && $scope.filtro.listaPerfiles.length > 0) { return true }
		if ($scope.filtro.cdPlaca != undefined && $scope.filtro.cdPlaca != "") { return true }
		if ($scope.filtro.nuExpediente != undefined && $scope.filtro.nuExpediente != "") { return true }
		if ($scope.filtro.listaIncidencias != undefined && $scope.filtro.listaIncidencias.length > 0) { return true }
		return false;
	}

	$scope.filtrarEntregas = function () {
		$scope.catBusqPT.lista = [];
		if ($scope.catPeriodo.valor != undefined) {
			for (var i = 0; i < $scope.catPeriodo.valor.length; i++) {
				var tmp = $filter('filter')($scope.catPT.lista, { nbEntrega: $scope.catPeriodo.valor[i].nameCat });
				$scope.catBusqPT.lista = $scope.catBusqPT.lista.concat(tmp);
			}
		}
	}

	$scope.filtrarPuntosTacticos = function () {
		$scope.catBusqCSV.lista = [];
		if ($scope.catBusqPT.valor != undefined) {
			for (var i = 0; i < $scope.catBusqPT.valor.length; i++) {
				var tmp = $filter('filter')($scope.catCSV.lista, { nbPt: $scope.catBusqPT.valor[i].nbNombre, idPt: $scope.catBusqPT.valor[i].idPt });
				$scope.catBusqCSV.lista = $scope.catBusqCSV.lista.concat(tmp);
			}
		}
	}

	$scope.filtrarMarcas = function () {
		if (isTodosCatlgsPerfilVacio()) {
			restaurarTodosCatlgsPerfil();
			return;
		}
		if ($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0) {
			$scope.catBusqSubMarca.lista = [];
			if ($scope.catBusqMarca.valor != undefined && $scope.catBusqMarca.valor.length > 0) {
				for (var i = 0; i < $scope.catBusqMarca.valor.length; i++) {
					for (var j = 0; j < $scope.catSubMarca.lista.length; j++) {
						if (parseInt(($scope.catBusqMarca.valor[i]).idPtMarca) == parseInt($scope.catSubMarca.lista[j].idPtMarca)) {
							$scope.catBusqSubMarca.lista.push($scope.catSubMarca.lista[j]);
						}
					}
				}
				$scope.filtrarSubMarcas();
			}
		}
	}

	$scope.filtrarSubMarcas = function () {
		if (isTodosCatlgsPerfilVacio()) {
			restaurarTodosCatlgsPerfil();
			return;
		}
		if ($scope.catBusqPerfil.valor == undefined || $scope.catBusqPerfil.valor.length == 0) {
			$scope.catBusqPerfil.lista = [];
			if ($scope.catBusqSubMarca.valor != undefined && $scope.catBusqSubMarca.valor.length > 0) {
				for (var i = 0; i < $scope.catBusqSubMarca.valor.length; i++) {
					for (var j = 0; j < $scope.catPerfil.lista.length; j++) {
						if (parseInt(($scope.catBusqSubMarca.valor[i]).idPtSubMarca) == parseInt($scope.catPerfil.lista[j].idPtSubMarca)) {
							if (isPreexistente($scope.catBusqPerfil.lista, "idPtPerfil", $scope.catPerfil.lista[j].idPtPerfil) < 0) {
								$scope.catBusqPerfil.lista.push($scope.catPerfil.lista[j]);
							}
						}
					}
				}
			}
		}
		if ($scope.catBusqMarca.valor == undefined || $scope.catBusqMarca.valor.length == 0) {
			$scope.catBusqMarca.lista = [];
			if ($scope.catBusqSubMarca.valor != undefined && $scope.catBusqSubMarca.valor.length > 0) {
				for (var i = 0; i < $scope.catBusqSubMarca.valor.length; i++) {
					for (var j = 0; j < $scope.catMarca.lista.length; j++) {
						if (parseInt($scope.catMarca.lista[j].idPtMarca) == parseInt(($scope.catBusqSubMarca.valor[i]).idPtMarca)) {
							$scope.catBusqMarca.lista.push($scope.catMarca.lista[j]);
						}
					}
				}
			}
		}
	}

	$scope.filtrarSubMarcasPorPerfil = function () {
		if (isTodosCatlgsPerfilVacio()) {
			restaurarTodosCatlgsPerfil();
			return;
		}
		if ($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0) {
			$scope.catBusqSubMarca.lista = [];
			if ($scope.catBusqPerfil.valor != undefined && $scope.catBusqPerfil.valor.length > 0) {
				for (var i = 0; i < $scope.catBusqPerfil.valor.length; i++) {
					for (var j = 0; j < $scope.catPerfil.lista.length; j++) {
						if (parseInt(($scope.catBusqPerfil.valor[i]).idPtPerfil) == parseInt($scope.catPerfil.lista[j].idPtPerfil)) {
							if (isPreexistente($scope.catBusqSubMarca.lista, "idPtPerfil", $scope.catPerfil.lista[j].idPtPerfil)) {
								$scope.catBusqSubMarca.lista.push($scope.catPerfil.lista[j]);
							}
						}
					}
				}
				$scope.filtrarSubMarcas();
			}
		}
	}

	function isPreexistente(array, attr, value) {
		for (var i = 0; i < array.length; i += 1) {
			if (array[i][attr] === value) {
				return i;
			}
		}
		return -1;
	}

	function getEntregasSeleccionadas() {
		var tmpEntregasSeleccionadas = [];
		if ($scope.filtro.listaEntregas.length == 0) {
			return tmpEntregasSeleccionadas;
		}
		for (var i = 0; i < $scope.filtro.listaEntregas.length; i++) {
			var tmp = $filter('filter')($scope.catPeriodo.lista, { idCat: $scope.filtro.listaEntregas[i] });
			tmpEntregasSeleccionadas = tmpEntregasSeleccionadas.concat(tmp);
		}
		return tmpEntregasSeleccionadas;
	}

	function getPtsSeleccionados() {
		var tmpLotesSeleccionados = [];
		if ($scope.filtro.listaLotes.length == 0) {
			return tmpLotesSeleccionados;
		}
		for (var i = 0; i < $scope.filtro.listaLotes.length; i++) {
			for (var j = 0; j < $scope.catPT.lista.length; j++) {
				if (parseInt($scope.catPT.lista[j].idPt) == parseInt($scope.filtro.listaLotes[i])) {
					tmpLotesSeleccionados.push($scope.catPT.lista[j]);
				}
			}
		}
		return tmpLotesSeleccionados;
	}

	function getCsvsSeleccionados() {
		var tmpCsvsSeleccionados = [];
		if ($scope.filtro.listaCsvs.length == 0) {
			return tmpCsvsSeleccionados;
		}
		for (var i = 0; i < $scope.filtro.listaCsvs.length; i++) {
			for (var j = 0; j < $scope.catCSV.lista.length; j++) {
				if (parseInt($scope.catCSV.lista[j].idArchivoCsv) == parseInt($scope.filtro.listaCsvs[i])) {
					tmpCsvsSeleccionados.push($scope.catCSV.lista[j]);
				}
			}
		}
		return tmpCsvsSeleccionados;
	}

	function getMarcasSeleccionadas() {
		var tmpMarcasSeleccionadas = [];
		if ($scope.filtro.listaMarcas.length == 0) {
			return tmpMarcasSeleccionadas;
		}
		for (var i = 0; i < $scope.filtro.listaMarcas.length; i++) {
			for (var j = 0; j < $scope.catMarca.lista.length; j++) {
				if (parseInt($scope.catMarca.lista[j].idPtMarca) == parseInt($scope.filtro.listaMarcas[i])) {
					tmpMarcasSeleccionadas.push($scope.catMarca.lista[j]);
				}
			}
		}
		return tmpMarcasSeleccionadas;
	}

	function getSubMarcasSeleccionadas() {
		var tmpSubMarcasSeleccionadas = [];
		if ($scope.filtro.listaSubMarcas.length == 0) {
			return tmpSubMarcasSeleccionadas;
		}
		for (var i = 0; i < $scope.filtro.listaSubMarcas.length; i++) {
			for (var j = 0; j < $scope.catSubMarca.lista.length; j++) {
				if (parseInt($scope.catSubMarca.lista[j].idPtSubMarca) == parseInt($scope.filtro.listaSubMarcas[i])) {
					tmpSubMarcasSeleccionadas.push($scope.catSubMarca.lista[j]);
				}
			}
		}
		return tmpSubMarcasSeleccionadas;
	}

	function getPerfilesSeleccionados() {
		var tmpPerfilesSeleccionados = [];
		if ($scope.filtro.listaPerfiles.length == 0) {
			return tmpPerfilesSeleccionados;
		}
		for (var i = 0; i < $scope.filtro.listaPerfiles.length; i++) {
			for (var j = 0; j < $scope.catPerfilDistinct.lista.length; j++) {
				if (parseInt($scope.catPerfilDistinct.lista[j].idPtPerfil) == parseInt($scope.filtro.listaPerfiles[i])) {
					tmpPerfilesSeleccionados.push($scope.catPerfilDistinct.lista[j]);
				}
			}
		}
		return tmpPerfilesSeleccionados;
	}

	function getIncidenciasSeleccionadas() {
		var tmpIncidenciasSeleccionadas = [];
		if($scope.filtro.listaIncidencias!=null){
			if ($scope.filtro.listaIncidencias.length == 0) {
				return tmpIncidenciasSeleccionadas;
			}
			for (var i = 0; i < $scope.filtro.listaIncidencias.length; i++) {
				for (var j = 0; j < $scope.catIncidencia.lista.length; j++) {
					if (parseInt($scope.catIncidencia.lista[j].idIncidencia) == parseInt($scope.filtro.listaIncidencias[i])) {
						tmpIncidenciasSeleccionadas.push($scope.catIncidencia.lista[j]);
					}
				}
			}
		}
		
		return tmpIncidenciasSeleccionadas;
	}

	function getEtiquetasSeleccionadas() {
		var tmpEtiquetaSeleccionada = {};

		if($scope.catEtiquetas.valor!=null){
			for (var j = 0; j < $scope.catEtiquetas.lista.length; j++) {
				if (parseInt($scope.catEtiquetas.lista[j].idEtiqReval) == parseInt($scope.catEtiquetas.valor.idEtiqReval)) {
					tmpEtiquetaSeleccionada = $scope.catEtiquetas.lista[j];
					$('#select2-comboEtiqueta-container').text(tmpEtiquetaSeleccionada.nbEtiqueta);
					break;
				}
			}
		}else{
			if ($rootScope.idEtiqueta!=null) {
				for (var j = 0; j < $scope.catEtiquetas.lista.length; j++) {
					if (parseInt($scope.catEtiquetas.lista[j].idEtiqReval) == parseInt($rootScope.idEtiqueta)) {
						tmpEtiquetaSeleccionada = $scope.catEtiquetas.lista[j];
						$('#select2-comboEtiqueta-container').text(tmpEtiquetaSeleccionada.nbEtiqueta);
						break;
					}
				}
				$rootScope.idEtiqueta=null;
			}
		}
		return tmpEtiquetaSeleccionada;
	}

	// Adición GIbran

	$scope.marcarPTValidado = function (id) {
		showAlert.confirmacion("¿Estas seguro de marcar este PT como validado?. Al realizar esta acción afirmas que toda la información contenida es correcta.", confirmarPT, id, noaction);
	}

	function confirmarPT(id) {
		asignacionService.confirmaPuntoTactico(id).success(function (data) {
			if (data) {
				$scope.dataRegistros = $scope.dataRegistros.map(function (el) {
					if (el.idPtLote == id)
						el.nbEstado = $scope.estatus.validado;
					return el;
				});
				growl.success("El punto táctico ha sido confirmado como validado");
			} else {
				growl.error("No se pudo confirmar el punto táctico");
			}

		}).error(function (err) {
			growl.error(err.message, { title: "Error:", ttl: 9000 });
		});
	};

	//Métodos a Nivel de Lote
	$scope.revalidacionRapida = function (id, tipoRevalidacion) {
		$scope.catMotivoDetalle.valor=null;
		$scope.catMotivoGeneral.valor=null;
		$scope.validadores.valor=null;
		$('#select2-comboMotivoRevalidaRapida-container').text("Seleccione");
		$('#select2-comboMotivoDetalleRevalidaRapida-container').text("Seleccione");
		$('#select2-comboRevalidaValidadorRapida-container').text("Seleccione");
		
		$scope.inicializarObjetoAsignReval();
		$scope.AsignaRevalidacionVO.idPtLote = id;

		$scope.AsignaRevalidacionVO.filtro = JSON.stringify($scope.filtro);

		if (tipoRevalidacion === $scope.revalidacionRapida.pt) {
			$scope.AsignaRevalidacionVO.idTiporeval = 5;
		}
		$scope.showModalRevalidacionRapida = true;
	}
	//Métodos a nivel CSV
	function pintaCombosGuardados(idArchivo) {
		$scope.catMotivoDetalle.valor = null;
		$('#select2-comboMotivoDetalleRevalidaRapida-container').text("Seleccione");
		var clean = false;
		if ($scope.AsignaRevalidacionVO.listaArchivosCsv.length > 0) {
			for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
				if ($scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo === idArchivo) {

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
					clean = false;
					break;
				} else { clean = true; }
			}
		} else {
			clean = true;
		}
		if (clean) {
			$scope.catMotivoGeneral.valor = null;
			$('#select2-comboMotivoRevalidaRapida-container').text("Seleccione");
			$scope.validadores.valor = null;
			$('#select2-comboRevalidaValidadorRapida-container').text("Seleccione");
		}

	}
	$scope.crearGrupoFullCSV = function (id) {
		// $scope.AsignaRevalidacionVO.idPtLote=$scope.detalleAsignacion.idPtLote;
		pintaCombosGuardados(id);
		$scope.currentArchivoCSV = null;
		$scope.AsignaRevalidacionVO.idTiporeval = 4;
		$scope.AsignaRevalidacionVO.filtro = JSON.stringify($scope.filtro);
		$scope.currentArchivoCSV = createArchivoCsvVO(
			id,
			null,
			null,
			[]
		);
		$scope.showModalRevalidacionRapida = true;
	}

	$scope.getCatEtiquetas = function () {
		asignacionService.getCatEtiquetasReval().success(function (data) {
			$scope.catEtiquetas.lista = data;
			// console.log($scope.catEtiquetas.lista);
		}).error(function (err) {
			growl.error(err.message, { title: "Error:", ttl: 4000 });
		});
	}
	$scope.getCatEtiquetas();

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

	$scope.validacionCampos = function (tipoRevalidacion) {
		switch (tipoRevalidacion) {
			case 'revalRapidaPT':
				if ($scope.catMotivoGeneral.valor == null) {
					growl.error("Selecciona un motivo de re validación CSV")
					return false;
				} else if ($scope.catMotivoDetalle.valor == null) {
					growl.error("Selecciona un motivo de re validación del Expediente")
					return false;
				} else if ($scope.validadores.valor == null) {
					growl.error("Selecciona un validador")
					return false;
				}
				break;
			case 'revalGrupoCSV':
				if ($scope.catMotivoGeneral.valor == null) {
					growl.error("Selecciona un motivo de re validación CSV")
					return false;
				} else if ($scope.catMotivoDetalle.valor == null) {
					growl.error("Selecciona un motivo de re validación del Expediente")
					return false;
				} else if ($scope.validadores.valor == null) {
					growl.error("Selecciona un validador")
					return false;
				}
				break;
			default:
			// code block
		}
		return true;
	}

	$scope.preparacionValidacion = function (tipoRevalidacion) {
		if (tipoRevalidacion === 'revalRapidaPT') {
			$scope.AsignaRevalidacionVO.idMotivoGeneralLote = $scope.catMotivoGeneral.valor.idCat;
			$scope.AsignaRevalidacionVO.idMotivoDetalleLote = $scope.catMotivoDetalle.valor.idCat;
			$scope.AsignaRevalidacionVO.idValidadorLote = $scope.validadores.valor.idValidador;
			$scope.AsignaRevalidacionVO.listaArchivosCsv = null;
		} else if (tipoRevalidacion === 'revalGrupoCSV') {
			$scope.AsignaRevalidacionVO.idTiporeval=4;
			if ($scope.currentArchivoCSV != [] && $scope.currentArchivoCSV != undefined) {
				var crearArchivo = false;
				if ($scope.AsignaRevalidacionVO.listaArchivosCsv.length > 0) {
					//Buscamos si ya existe el archivo en la lista y de ser así solo le añadimos un grupo de expedientes
					for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
						if ($scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo === $scope.currentArchivoCSV.idArchivo) {
							var grupoExp = createGrupoExpedientesVO(
								$scope.catMotivoDetalle.valor.idCat,
								JSON.stringify($scope.filtro),
								[]
							);
							$scope.AsignaRevalidacionVO.listaArchivosCsv[i].idValidador = $scope.validadores.valor.idValidador;
							$scope.AsignaRevalidacionVO.listaArchivosCsv[i].idMotivo = $scope.catMotivoGeneral.valor.idCat;
							// console.log($scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes);
							// console.log(grupoExp);
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
					$scope.currentArchivoCSV.idValidador = $scope.validadores.valor.idValidador;
					$scope.currentArchivoCSV.idMotivo = $scope.catMotivoGeneral.valor.idCat;
					var listGrupos = [];
					var grupoExp = createGrupoExpedientesVO(
						$scope.catMotivoDetalle.valor.idCat,
						JSON.stringify($scope.filtro),
						[]
					);
					listGrupos.push(grupoExp);
					$scope.currentArchivoCSV.listaGrupoExpedientes = listGrupos;
					$scope.AsignaRevalidacionVO.listaArchivosCsv.push($scope.currentArchivoCSV);
				}
				//Modificamos en la etiqueta su estatus a añadido
				for (var j = 0; j < $scope.dataRegistros.length; j++) {
					if ($scope.dataRegistros[j].idArchivoCsv === $scope.currentArchivoCSV.idArchivo) {
						$scope.dataRegistros[j].nbEstado = $scope.estatus.anadido;
					}
				}
				//Cerramos el modal y mostramos al usuario el msj
				$scope.showModalRevalidacionRapida = false;
				growl.success('Se han añadido todos los expedientes del CSV al conjunto de grupos para su revalidación');

			} else {
				growl.error('Ocurrió un error al intentar generar la agrupación de archivos, por favor refresque y vuelva a intentarlo');
			}
		}else if('enviarGroupCSV'){
			$scope.AsignaRevalidacionVO.idTiporeval=4;
		}
	}

	$scope.none = function () {
		$scope.showModalRevalidacionRapida = true;
	}

	$scope.revalidar = function (tipoRevalidacion) {
		if (!$scope.validacionCampos(tipoRevalidacion)) {
			return false;
		}
		if (tipoRevalidacion == 'enviarGroupCSV') {
			$scope.listaDetalle = [];
			for (var i = 0; i < $scope.AsignaRevalidacionVO.listaArchivosCsv.length; i++) {
				for (var j = 0; j < $scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes.length; j++) {
					// console.log($scope.AsignaRevalidacionVO.listaArchivosCsv[i]);
					
					var nomMotivoGeneral='';
					$scope.catMotivoGeneral.lista.map(function (el) {
						if (el.idCat == $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idMotivo) {
							nomMotivoGeneral = el.nameCat;
						}
					});
					var nomMotivoDetalles='';
					$scope.catMotivoDetalle.lista.map(function (el) {
						if (el.idCat == $scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes[j].idMotivo) {
							nomMotivoDetalles = el.nameCat;
						}
					});
					var validdr='';
					$scope.validadores.lista.map(function (el) {
						if (el.idValidador == $scope.AsignaRevalidacionVO.listaArchivosCsv[i].idValidador) {
							validdr = el.nbValidador;
						}
					});

					var detalle = createDetalleVO(
						$scope.AsignaRevalidacionVO.listaArchivosCsv[i].idArchivo, 
						nomMotivoGeneral, 
						validdr, 
						nomMotivoDetalles, 
						$scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes[j].listaExpedientes.length>0 ? $scope.AsignaRevalidacionVO.listaArchivosCsv[i].listaGrupoExpedientes[j].listaExpedientes.length : 'todos'
						
					);
					$scope.listaDetalle.push(detalle);
				}
			}
			// console.log($scope.listaDetalle);
			$scope.showModalDetalle = true;
		} else {
			showAlert.confirmacion("¿Deseas continuar con la asignación a revalidar?", confirmValidacion, tipoRevalidacion, noaction);
		}
	}
	function noaction() {
	}

	$scope.confirmEnvio = function (tipoRevalidacion) {
		showAlert.confirmacion("¿Deseas continuar con la asignación a revalidar?", confirmValidacion, tipoRevalidacion, noaction);
	}

	$scope.printasign = function(){
		// console.log($scope.AsignaRevalidacionVO);
		// console.log($rootScope.AsignaRevalidacionVO);
		// console.log($scope.AsignaRevalidacionVO);
	}
	$scope.cleanvariables = function(){
		$scope.AsignaRevalidacionVO.listaArchivosCsv = [];
		$rootScope.AsignaRevalidacionVO=[];
		$scope.inicializarObjetoAsignReval();
		$scope.detalle=[];
	}


	function confirmValidacion(tipoRevalidacion) {
		$scope.preparacionValidacion(tipoRevalidacion);
		// console.log($scope.AsignaRevalidacionVO);
		if (tipoRevalidacion === 'revalRapidaPT' || tipoRevalidacion === 'enviarGroupCSV') {
			
			asignacionService.reasignaPTLote($scope.AsignaRevalidacionVO).success(function (data) {
				
				if (data) {
					if (tipoRevalidacion === 'revalRapidaPT') {

						pintaReasignadosLoteScope($scope.estatus.reasignado);
						$scope.showModalRevalidacionRapida = false;
						
						growl.success("La asignación a revalidación del lote seleccionados se ha realizado satisfactoriamente");

					} else if (tipoRevalidacion === 'enviarGroupCSV') {

						$scope.showModalDetalle = false;
						pintaReasignadosCSVScope($scope.estatus.reasignado);
						growl.success("La asignación a revalidación de los conjuntos de expedientes seleccionados se ha realizado satisfactoriamente");

					}

					if($scope.catEtiquetas.valor!=null&&$scope.catEtiquetas.valor.idEtiqReval!=null
						&&!isNaN($scope.catEtiquetas.valor.idEtiqReval)){
						showAlert.confirmacion("¿Deseas deshabilitar la etiqueta seleccionada?", deleteEtiqueta, null, noaction);
					}
					$scope.cleanvariables();
				}
			}).error(function (err) {
				growl.error(err.message, { title: "Error:", ttl: 9000 });
				return false;
			})
		}
	}

	$scope.limpiarFomulario = function (event) {
		event.preventDefault();
		restaurarFiltro();
	}

	function restaurarFiltro() {
		$scope.filtro = {};

		$scope.catPeriodo.lista = [...$scope.catPeriodo.lista];
		$scope.catBusqPT.lista = [...$scope.catPT.lista];
		$scope.catBusqCSV.lista = [...$scope.catCSV.lista];
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];
		$scope.catIncidencia.lista = [...$scope.catIncidencia.lista];
		$scope.catEtiquetas.lista = [...$scope.catEtiquetas.lista];


		$scope.catPeriodo.valor = [];
		$scope.catBusqPT.valor = [];
		$scope.catBusqCSV.valor = [];
		$scope.catBusqMarca.valor = [];
		$scope.catBusqSubMarca.valor = [];
		$scope.catBusqPerfil.valor = [];
		$scope.catIncidencia.valor = [];
		// $scope.catEtiquetas.valor = {};
		$('#select2-comboEtiqueta-container').text("");


		$scope.cdPlaca = "";
		$scope.nuExpediente = "";
		asignacionService.setFiltro(undefined);
		$scope.dataRegistros = [];
	}


	$scope.getFiltroEtiquetado = function () {
		var idEtiqueta = null;
		if($scope.catEtiquetas.valor!=null){
			idEtiqueta = $scope.catEtiquetas.valor.idEtiqReval;
			if (idEtiqueta == undefined || isNaN(idEtiqueta)) {
				growl.error('Por favor ingrese un parámetro de búsqueda.');
			}
			$scope.tipoBusq.valor=2;
			
			asignacionService.getExpedientesEtiquetados(idEtiqueta, $scope.tipoBusq.valor).success(function (data) {
				$scope.isOpen = false;
				restaurarFiltro();
				if (data.expedientes != null && data.expedientes != undefined && data.expedientes.length > 0) {
					$scope.dataRegistros = data.expedientes;
				}
	
				if (data.filtro != null) {
					$scope.filtro = data.filtro;
				}
				refrescarFormularioDeBusqueda();
				$scope.tipoBusq.valor=2;
				getExpedientes();
	
			}).error(function (err) {
				showAlert.aviso(err.message);
			});

		}
		

		
	}

	$scope.limpiarTabla = function () {
		$scope.dataRegistros = [];
	}

	function restaurarTodosCatlgsPerfil() {
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];

	}

	function isTodosCatlgsPerfilVacio() {
		if (($scope.catBusqMarca.valor == undefined || $scope.catBusqMarca.valor.length == 0) &&
			($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0) &&
			($scope.catBusqPerfil.valor == undefined || $scope.catBusqPerfil.valor.length == 0)) {
			return true;
		}
		return false;
	}

	$scope.init();

});