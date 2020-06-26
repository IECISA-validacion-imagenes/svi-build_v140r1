angular.module(appTeclo).controller('editaReporteDinamicoController',
	function($scope, growl, showAlert, $location, $filter, reporteService, $timeout, reporteVO, admiReporteService) {
	$scope.reportesTaqVOTMP = {};
	$scope.paramsTMP = [];
	$scope.reportesTaqVO = {url: "/reporteDinamicoConsulta"};
	if(reporteVO.data != null){
		angular.copy(reporteVO.data, $scope.reportesTaqVO);
		angular.copy($scope.reportesTaqVO, $scope.reportesTaqVOTMP);
	}
	/*Iniciliazar listas*/
	$scope.tipoAgrupacion = [];
	$scope.validacionRequire = true;
	$scope.tipoTitulo = [];
	$scope.tiposReportes = [];
	$scope.formatos = [];
	$scope.componentes = [];
	$scope.parametros = [];
	$scope.formatosTMP = [];
	$scope.catalogoTablas = [];
	$scope.catalogoColumnas= [];
	$scope.params = [];
	$scope.arrayParametros = [];
	$scope.paramConfig = false;
	$scope.relacionTipoParamsComps = [];
	$scope.listaCompPropModal = [];
	$scope.parametroActualModal = undefined;
	$scope.relacionCompPropiedad = [];
	$scope.propiedades = [];
	$scope.catTipoOrdenamiento = [];
	$scope.catTipoOperadores = [];
	$scope.objCheck ={};
	$scope.esListaDoble = {};
	$scope.propiedadTipoQuery ={testQuery : undefined};
	/*Variables del modal de configuración de propiedades*/
	$scope.modalConfigParam = false;
	$scope.catCaracteres = [];
	$scope.cambiosEfectuadosTipCat = {};
	$scope.idTablaSeleccionada = undefined;
	$scope.catalogoConfigurado = {identificador: undefined,descripciones: []};
	$scope.objetoConCambiosAplicados = {};
	$scope.preseleccionarColumna = false;
	$scope.objeto = {iden: [],desc: [],restri: [],order: []};
	$scope.modalConfigTipoCat = false;
	$scope.parametrosDependientes = {paramestrosTipoCatalogo : [],elementosDependientes:[],catInSelect:[],catInSelectTMP:[],listInSelect: {}};
	$scope.resultadoCatalogo = {};
	$scope.edicionQuery = {
			editable : false,
			respaldoParametros: [],
			respaldoSelect : '',
			respaldoFrom : '',
			respaldoWhere : '',
			banderaValidacion : false
	};
	$scope.showModalDependencia = false;
	$scope.objectDependencias = {};
	$scope.modeloAsociaciones = {};
	$scope.dependenciasAplicadas = {cambioAplicado: false};
	$scope.paramValue = [];
	$scope.columnasMetadata = [];
	/*vista previa de reporte*/
	$scope.gridOptions = {
            data: [],
            urlSync: false,
            pagination: {
                itemsPerPage: '10'
            },enableHorizontalScrollbar : true,
            enableVerticalScrollbar  : true
        };
	$scope.rowCollection = [];
	$scope.objetoEdicion = {
			sinParamsEdicion : false
	};
	/*Numeros de orden*/
	$scope.nuOrden={
			maxNuOrdenParam : 0,
			maxNuOrdenColum : 0,
			minAmbos : 1
	};
	/*OBJETO DE CONFIGURACIÓN DEL SWITCHER*/
	$scope.switcherProp = {};
	
	obtenerFormatosDescargas = function() {
		admiReporteService.listFormatoDescarga().success(
			function(data) {
				angular.copy(data,$scope.formatos);
				var obj ={},listaObj =[], idActuales = [],valoresActualesFormatoDes = $scope.reportesTaqVO.reporteFormato;
				angular.forEach($scope.formatos, function(value, key) {
					obj = {id: value.idFormatoDescarga, desc: value.nbFormato};
					listaObj.push(obj);
				});
				angular.copy(listaObj,$scope.formatosTMP);
				for(var i=0; i < valoresActualesFormatoDes.length; i++){
					idActuales.push(valoresActualesFormatoDes[i].idFormatoDescarga);
				}
				angular.copy(idActuales, $scope.reportesTaqVO.reporteFormato);
				angular.copy(idActuales, $scope.reportesTaqVOTMP.reporteFormato);
				$timeout(function() {
					var target = angular.element('#reporteFormato');
					target.selectpicker('refresh');
				}, 100);
			}).error(function(data) {
			$scope.formatos = [];
		});
	};
	function testQuerySinParametros(lista, scriptFull){
		admiReporteService.crearTestQuery(lista, scriptFullTmp).success(function(data) {
			$scope.resultadoTestQuery = data;
			if ($scope.resultadoTestQuery.stResult) {
				$scope.paramConfig = false;
				$scope.showMensajeTest = $scope.paramConfig;
				$scope.habilitarPaginacion = true;
				/*COMPROBAR EL TIPO DE TÍTULO DE LAS CABECERAS*/
				angular.copy(data.columnsMetadataTest,$scope.columnasMetadata);
				var ban = $scope.reportesTaqVO.tipoTitulo.cdTipoTitulo == "TM" ? true: false;
				var listaTitulo = switchTipoTitulo($scope.columnasMetadata, ban);
				angular.copy(listaTitulo,$scope.columnasMetadata);
				/**Mandar a llamar funciones que consultan catalogos 
				 * dentro de la configuración de los params
				 * */
				if($scope.paramConfig){
					$scope.edicionQuery.banderaValidacion = false;
					$scope.edicionQuery.editable = true;
					angular.copy($scope.columnasMetadata, $scope.gridOptions.data);
					angular.copy(data.columnsMetadataTest, $scope.rowCollection);
				}					
			} else {
				$scope.paramConfig = false;
				$scope.showMensajeTest = $scope.paramConfig;
			}
		}).error(function(data) {
			$scope.resultadoTestQuery = data;
		});
	};
	
	$scope.checkParams = function(reportesTaqVO, userForm) {
		$scope.resultadoTestQuery = '';
		$scope.showMensajeTest = undefined;
		scriptFullTmp = "SELECT " + $scope.reportesTaqVO.scriptSelect + " FROM " +
		$scope.reportesTaqVO.scritFrom + " WHERE " + $scope.reportesTaqVO.scriptWhere;
		if ($scope.altaRep.$invalid) {
			showAlert.requiredFields($scope.altaRep);
			growl.error('Formulario incompleto');
			return;
		} else {
			angular.copy( $scope.reportesTaqVO.scriptSelect,$scope.edicionQuery.respaldoSelect);
			angular.copy( $scope.reportesTaqVO.scritFrom,$scope.edicionQuery.respaldoFrom);
			angular.copy( $scope.reportesTaqVO.scriptWhere,$scope.edicionQuery.respaldoWhere);
			admiReporteService.checkParams(scriptFullTmp).success(function(data) {
				//Arreglo de parametros desde API
				$scope.arrayParametros = data;
				comprobarEdicionQuery($scope.arrayParametros, $scope.params);
				angular.copy($scope.params,$scope.paramsTMP);
				if($scope.params.length <= 0){
					/*ejecutar el query y obtener el metadata
					 * la lista params está vacio*/
					testQuerySinParametros($scope.params, scriptFullTmp);
					$scope.habilitarPaginacion = true;
					$scope.reportesTaqVO.tipoAgrupacion = $scope.tipoAgrupacion[0];//cuando sea un "select * from" la columna de paginacion será auto
				}else{
					$scope.habilitarPaginacion = false;
				}
			}).error(function(data) {
				$scope.arrayParametros = [];
				growl.error($scope.mensajeModal('Algo salió mal al identificar los parametros') + ': ' + data.message, {
					ttl : 4000
				});
			});
		}
	};
	
	$scope.editarQuery = function (params){
		showAlert.confirmacion("Al activar esta opción se tendrá que " +
				"validar el script nuevamente, ¿Desea continuar?", confirmaEdicionScriptModal, params,
																	cancelaEdicionScriptModal,params);
	};
	
	confirmaEdicionScriptModal = function (data){
		$scope.edicionQuery.editable = false;
		$scope.edicionQuery.banderaValidacion = true;
	};
	cancelaEdicionScriptModal = function (data){
		return;
	};
	
	
	if(reporteVO.data.parametros != null && reporteVO.data.parametros.length > 0){
		angular.copy(reporteVO.data.parametros, $scope.params);
		angular.copy($scope.params, $scope.arrayParametros);
		angular.copy($scope.params,$scope.paramsTMP);
		$scope.paramConfig = true;
		$scope.edicionQuery.banderaValidacion = true;
	}else if(reporteVO.data.parametros != null && reporteVO.data.parametros.length <= 0){
		var scriptFullTmp = "SELECT " + $scope.reportesTaqVO.scriptSelect + " FROM " +
		$scope.reportesTaqVO.scritFrom + " WHERE " + $scope.reportesTaqVO.scriptWhere;
		$scope.objetoEdicion.sinParamsEdicion = true;
		testQuerySinParametros($scope.params, scriptFullTmp);
	}
	
	if(reporteVO.data.txLayout != null){
		var txLayout = reporteVO.data.txLayout.split('|');
		angular.forEach(txLayout, function (value, index){
			$scope.columnasMetadata.push(value);
		});
	}
	
	function configurarParametrosEdit(listaParams){
		for(var i=0; i < listaParams.length; i++){
			$('#select2-tipoParametro'+i+'-container').text(listaParams[i].tipoParametro.nbTipoParametro);
			if(listaParams[i].stIsCatalogo == 1){
				$scope.cambiosEfectuadosTipCat[listaParams[i].cdParametro] = true;
				$scope.parametrosDependientes.paramestrosTipoCatalogo.push(listaParams[i]);
			}
		}
	};
	
	$scope.paramTipoCatalogo = function (newValue,oldValue,currentParam){		
		if(currentParam.configParamTipoCatVO != undefined){
			showAlert.confirmacion("Las configuraciones realizadas se perderán, ¿Desea continuar?", confirmaParamTipoCatalogo, currentParam, cancelaParamTipoCatalogo,currentParam);
			$timeout(function() {
				currentParam.stIsCatalogo = oldValue;
			}, true);
		}else{
			currentParam.stIsCatalogo = newValue;
			if(newValue == 1){
				$scope.modalConfigTipoCat = true;
				$scope.objectCatalogosParam = currentParam;
			}
		}
	};
	confirmaParamTipoCatalogo = function (param){
		param.configParamTipoCatVO = undefined;
		$scope.catalogoConfigurado.identificador = undefined;
		$scope.cambiosEfectuadosTipCat[param.cdParametro] = false;
		param.stIsCatalogo = 0;
		$scope.parametrosDependientes.paramestrosTipoCatalogo = deleteElementIntoList($scope.parametrosDependientes.paramestrosTipoCatalogo,param.cdParametro);
		//$scope.objectCatalogosParam = undefined;
		$scope.catalogoConfigurado.identificador = undefined;
	};
	cancelaParamTipoCatalogo = function (param){
		return;
	};
	
	$scope.modificarConfParamTipCar = function (parametro){
		$scope.modalConfigTipoCat = true;
		$scope.preseleccionarColumna = true;
		if(parametro.configParamTipoCatVO != undefined){
			$scope.setTablaSeleccionada(parametro.configParamTipoCatVO.tablaActual.idTabla,parametro,$scope.preseleccionarColumna);
			$scope.objetoConCambiosAplicados[parametro.cdParametro] = parametro.configParamTipoCatVO;
			angular.forEach(parametro.configParamTipoCatVO.descripciones, function(value, key) {
				value.check = true;
			});
			angular.forEach(parametro.configParamTipoCatVO.ordenes, function(value, key) {
				value.check = true;
			});
			angular.forEach(parametro.configParamTipoCatVO.restricciones, function(value, key) {
				if(value.valor != null){
					value.check = true;	
				}
			});
		}
	};
	
	$scope.setTablaSeleccionada = function (idTabla, paremetro, banderaPreselect){
		var param = {'idTabla':idTabla,'paremetro':paremetro,'banderaPreselect':banderaPreselect};
		if($scope.idTablaSeleccionada != undefined && paremetro.configParamTipoCatVO != undefined && !banderaPreselect){
			showAlert.confirmacion("Los cambios que haya realizado se perderán, ¿Desea continuar?", confirmaSelectCambioCatalogo, param, cancelaSelectCambioCatalogo);
		}else{
			seleccionarCatalogo(param);
		}
	};
	confirmaSelectCambioCatalogo = function (objecto){
		objecto.paremetro.configParamTipoCatVO = undefined;
		$scope.catalogoConfigurado.identificador = undefined;
		$scope.parametrosDependientes.paramestrosTipoCatalogo = deleteElementIntoList($scope.parametrosDependientes.paramestrosTipoCatalogo,objecto.paremetro.cdParametro);
		seleccionarCatalogo (objecto);
		$scope.resultadoCatalogo[objecto.paremetro.cdParametro] = undefined;
	};
	
	cancelaSelectCambioCatalogo = function (){
		return;
	};
	
	function deleteElementIntoList(listParamTipCat, cdParamtoDelete){
		if(listParamTipCat.length > 0){
			for(var i=0; i < listParamTipCat.length; i++){
				if(cdParamtoDelete == listParamTipCat[i].cdParametro){
					listParamTipCat.splice(i, 1);
				}
			}
		}
		return listParamTipCat;
	};
	
	function filtroTipoParamProp(idPropieda) {
		var listFiltro = [];
		var listReturn = [];
		var length = $scope.relacionTipoParamsComps.length;
		if (length > 0)
			for (var i = 0; i < length; i++) {
				if (idPropieda === $scope.relacionTipoParamsComps[i].tipoParametro) {
					listFiltro.push($scope.relacionTipoParamsComps[i].componente);
				}
		}
		if (listFiltro.length > 0) {
			for (var j = 0; j < listFiltro.length; j++) {
				for (var k = 0; k < $scope.componentes.length; k++) {
					if (listFiltro[j] === $scope.componentes[k].idComponente) {
						listReturn.push($scope.componentes[k]);
					}
				}
			}
		}
		return listReturn;
	};
	
	function filtroComponenteProp(idComponente) {
		var listFiltro = [];
		var listReturn = [];
		var length = $scope.relacionCompPropiedad.length;
		if (length > 0)
			for (var i = 0; i < length; i++) {
				if (idComponente === $scope.relacionCompPropiedad[i].idComponente) {
					listFiltro.push($scope.relacionCompPropiedad[i].idPropiedad);
				}
		}
		if (listFiltro.length > 0) {
			for (var j = 0; j < listFiltro.length; j++) {
				for (var k = 0; k < $scope.propiedades.length; k++) {
					if (listFiltro[j] === $scope.propiedades[k].idPropiedad) {
						listReturn.push($scope.propiedades[k]);
					}
				}
			}
		}
		return listReturn;
	};
	
	$scope.filtroTipoComponente = function(idTipoParam, index, dataParam, confParams) {
		$("#select2-componente" + index + "-container").text("Seleccione una opción");
		if (idTipoParam != undefined && idTipoParam != null) {
			$timeout(function() {
				$scope['relacionTipoParamsCompsFiltro' + index] = filtroTipoParamProp(idTipoParam.idTipoParametro);
			}, 100);
		} else {
			$scope['relacionTipoParamsCompsFiltro' + index] = [];
		}
	};
	
	$scope.filtroCompPropiedad = function(componente, index, dataParam) {
		if (componente != undefined && componente != null) {
			$scope['filtroCompProp' + index] = filtroComponenteProp(componente.idComponente);
			//limpiarAtributosPropiedadTipoQuery(dataParam.cdParametro);
			$timeout(function() {
				var target = angular.element('#propiedades' + index);
				target.selectpicker('refresh');
			});
		} else {
			//growl.warning('Seleccione al menos un tipo de componente.', {ttl: 3000});
			$scope['filtroCompProp' + index] = [];
		}
		/*Vaciar la lista de propiedades configuradas*/
		if(dataParam.propieades != undefined && dataParam.propieades.length > 0){
			dataParam.propieades.length
		}
	};
	
	function seleccionarCatalogo (objecto){//dentro del objeto viene idTabla y el paremetro
		$scope.objeto = {iden: [],desc: [],restri: [],order: []};
		var objecModified = objecto.paremetro;
		var columnasFiltradas = filtrarColumnasPorTabla(objecto.idTabla, $scope.catalogoColumnas,objecModified);
		$scope.idTablaSeleccionada = objecto.idTabla;
		
		angular.copy(columnasFiltradas, $scope.objeto.iden);
		angular.copy(columnasFiltradas, $scope.objeto.desc);
		angular.copy(columnasFiltradas, $scope.objeto.restri);
		angular.copy(columnasFiltradas, $scope.objeto.order);
		
		updateNuOrden(columnasFiltradas, 'COLS');
		
		if(objecto.banderaPreselect){
			
			/**interar los identificadores para marcarlos*/
			var lengthIden = $scope.objeto.iden.length;
			if(lengthIden > 0){
				/*Actualizar el valor del identificador chequeado*/
				$scope.catalogoConfigurado.identificador =JSON.stringify(objecModified.configParamTipoCatVO.identificador[0]);
				for(var i=0; i< lengthIden; i++){
					if($scope.objeto.iden[i].idColumna == objecModified.configParamTipoCatVO.identificador[0].idColumna){
						$scope.objeto.iden[i] = objecModified.configParamTipoCatVO.identificador[0];
						$scope.objeto.iden[i].checked= true;
					}
				}
			}
				
			/**interar las descripciones para marcarlos*/
			var lengthDesc = $scope.objeto.desc.length; 
			var lengthDescExistente = objecModified.configParamTipoCatVO.descripciones.length;
			if(lengthDesc > 0 && lengthDescExistente > 0)
				for(var i=0; i< lengthDesc; i++){
					for(var j=0; j< lengthDescExistente; j++){
						if($scope.objeto.desc[i].idColumna == objecModified.configParamTipoCatVO.descripciones[j].idColumna){
							$scope.objeto.desc[i] = objecModified.configParamTipoCatVO.descripciones[j];
						}
					}
				}
			/**interar las restricciones para marcarlos*/
			var lengthRestri = $scope.objeto.restri.length; 
			var lengthRestriExistente = objecModified.configParamTipoCatVO.restricciones.length;
			if(lengthRestri > 0 && lengthRestriExistente > 0)
				for(var i=0; i< lengthRestri; i++){
					for(var j=0; j< lengthRestriExistente; j++){
						if($scope.objeto.restri[i].idColumna == objecModified.configParamTipoCatVO.restricciones[j].idColumna){
							$scope.objeto.restri[i] = objecModified.configParamTipoCatVO.restricciones[j];
						}
					}
				}
			/**interar las ordanmaientos para marcarlos*/
			var lengthOrder = $scope.objeto.order.length; 
			var lengthOrderExistente = objecModified.configParamTipoCatVO.ordenes.length;
			if(lengthOrder > 0 && lengthOrderExistente > 0)
				for(var i=0; i< lengthOrder; i++){
					for(var j=0; j< lengthOrderExistente; j++){
						if($scope.objeto.order[i].idColumna == objecModified.configParamTipoCatVO.ordenes[j].idColumna){
							$scope.objeto.order[i] = objecModified.configParamTipoCatVO.ordenes[j];
						}
					}
				}
			$scope.objectCatalogosParam = objecModified;//nuevo parámetro en el modal actualizado
		}
	};
	
	$scope.agregarValoresProp = function(index, cdParamActual) {
		var listaValues = $scope["filtroCompProp" + index];
		var objectParamActual = undefined; //objeto temporal de parametrosVO
		$scope.parametroActualModal = undefined;
		var listaTmpPropiedadesAsociadas = [];
		objectParamActual = obtenerParametroActualByCd(cdParamActual);
		$scope.parametroActualModal = cdParamActual; //asignar cdParametro actual
		$scope.listaCompPropModal = [];

		if (listaValues.length > 0 && objectParamActual.componente.cdComponente != 'SWITCH') {
			$scope.modalConfigParam = true; //bandera para mostrar modal
			angular.copy(listaValues, $scope.listaCompPropModal); //clonar listas de propiedades
			$scope.parametroActualModal = cdParamActual; //asignar cdParametro actual
			var lenthParams = $scope.params.length;
			if ($scope.parametroActualModal != undefined) {
				if(objectParamActual.componente.cdComponente == 'LISTD'){
					$scope.esListaDoble[cdParamActual] = true;
				}
				if (objectParamActual.propieades == undefined) { //preguntar si hay propiedades definidad
					objectParamActual.propieades = []; //definir lista de propiedades
				} else {
					listaTmpPropiedadesAsociadas = objectParamActual.propieades; //asociar propiedades asociadas
					if(objectParamActual.propieades.length == listaValues.length)
						$scope.objCheck[cdParamActual] = true;
				}
			}
			if(listaTmpPropiedadesAsociadas.length > 0){
				for(var j=0; j < listaTmpPropiedadesAsociadas.length; j++){
					for(var k=0; k < $scope.listaCompPropModal.length; k++){
						if(listaTmpPropiedadesAsociadas[j].idPropiedad == $scope.listaCompPropModal[k].idPropiedad){
							$scope.listaCompPropModal[k].checkedComp = true;
							if($scope.listaCompPropModal[k].stValorRequerido == 1)
								$scope.listaCompPropModal[k].value = listaTmpPropiedadesAsociadas[j].value;
						}
						if(listaTmpPropiedadesAsociadas[j].cdPropiedad === 'QUERY'){
							$scope.propiedadTipoQuery['requireQuery'+cdParamActual] = true;
							$scope.propiedadTipoQuery[cdParamActual] = listaTmpPropiedadesAsociadas[j].paramPropScriptVO.queryFull;
							$scope.propiedadTipoQuery['verificacionQuery'+cdParamActual] = true;
							$scope.propiedadTipoQuery['cdParamPrevia'+cdParamActual] = cdParamActual;
							$scope.propiedadTipoQuery['valParam'+cdParamActual] = '\'example\'';
							$scope.cambioSeparador(listaTmpPropiedadesAsociadas[j].value, cdParamActual);
							$scope.propiedadTipoQuery['verificacionQuery'+cdParamActual] = false;
							$scope.propiedadTipoQuery.testQuery = {'mensaje':'correcto', 'causa':'0', 'stResult': true, 'metadata':[]};
						}
					}
				}
			}
		} else if(listaValues.length <= 0 && objectParamActual.componente.cdComponente == 'SWITCH'){
			let txValorSeparado = objectParamActual.txValor.split(',');//separar cadena de txvalor
			let txValorUno = txValorSeparado[0].split('|');//separar el label uno
			let txValorDos = txValorSeparado[1].split('|');//separar el label dos

			$scope.switcherProp['switchLabelUno'+cdParamActual] = txValorUno[0];
			$scope.switcherProp['switchValueUno'+cdParamActual] = txValorUno[1];
			$scope.switcherProp['switchLabelDos'+cdParamActual] = txValorDos[0];
			$scope.switcherProp['switchValueDos'+cdParamActual] = txValorDos[1];

			$scope.modalConfigParam = true; //bandera para mostrar modal
	  }else {
			growl.warning('El componente seleccionado no tiene propiedades.', {
				ttl : 3000
			});
			$scope.modalConfigParam = false; //bandera para mostrar modal
		}
	};
	
	$scope.aplicarParamTipCat = function (idTabla,objeto, parametro){
		var ojbetoColTable = {tablaActual: {},identificador : [],descripciones : [],restricciones : [],ordenes : []};
		if(parametro != undefined){
			$scope.resultadoCatalogo[parametro.cdParametro] = undefined;
			if(idTabla == undefined){
				growl.warning('Seleccione al menos un catálogo',{ttl: 1000});
				return;
			}else if($scope.catalogoConfigurado.identificador == undefined){
				growl.warning('Seleccione al menos un identificador',{ttl: 1000});
				return;
			}else if(filtrarCheckMarcados(objeto.desc).length <= 0){
				growl.warning('Seleccione como mínimo una descripción',{ttl: 1000});
				return;
			}
			if($scope.modalTipoCatalogo.$invalid) {
				showAlert.requiredFields($scope.modalTipoCatalogo);
				growl.error('Formulario incompleto');
				return;
			}
			
			angular.copy(filterTablaById(idTabla), ojbetoColTable.tablaActual);
			ojbetoColTable.identificador.push(JSON.parse($scope.catalogoConfigurado.identificador));
			angular.copy(filtrarCheckMarcados(objeto.desc), ojbetoColTable.descripciones);
			var listRetict = filtrarCheckMarcados(objeto.restri, "DOUBLEVAL");
			if(listRetict==="MEN1"){
				growl.warning('Un valor detectado, requeridos dos concatenados con PIPE(|)',{ttl:5000});
				return;
			}else if(listRetict==="MAY2"){
				growl.warning('Más de dos valores detectados, máximo dos requeridos',{ttl:5000});
				return;
			}
			
			angular.copy(listRetict, ojbetoColTable.restricciones);
			angular.copy(filtrarCheckMarcados(objeto.order), ojbetoColTable.ordenes);
			var scriptCatExecute = validateQueryCatalogo(ojbetoColTable);
			
			admiReporteService.ejecutarQueryCatalogo(scriptCatExecute).success(function(data, status, headers, config) {
				$scope.resultadoCatalogo[parametro.cdParametro] = data;
				if(data.stResult){
					parametro.configParamTipoCatVO = ojbetoColTable;
					$scope.cambiosEfectuadosTipCat[parametro.cdParametro] = true;
					growl.success('Configuración asociada correctamente' , {ttl: 3000});
					/**clonar objeto para la validación de cambios al momento de la cancelación del modal*/
					$timeout(function() {
						$scope.objetoConCambiosAplicados[parametro.cdParametro] = ojbetoColTable;
					}, 100);
					var listTMP = deleteElementIntoList($scope.parametrosDependientes.paramestrosTipoCatalogo,parametro.cdParametro);
					listTMP.push(parametro);
					$scope.parametrosDependientes.paramestrosTipoCatalogo = listTMP;
				}else{
					growl.warning("Algo salió mal en la ejecución de la consulta", {ttl: 4000});
				}
			}).error(function(data, status, headers, config) {
				return null;
			});
			
			
		}
	};
	
	/**Función para obtener el parametro 
	 * actual a través de su cdParametro*/
	function obtenerParametroActualByCd(cdParametro) {
		var objectParamReturn = undefined;
		for (var i = 0; i < $scope.params.length; i++) {
			if ($scope.params[i].cdParametro === cdParametro) {
				objectParamReturn = $scope.params[i];
				break;
			}
		}
		return objectParamReturn;
	};
	
	function validateQueryCatalogo(objeto){
		var scriptExecute ='SELECT ';
		var id = objeto.identificador[0].nbReal;
		scriptExecute += id +" AS id, ";
		var desc = '';
		for(var i=0; i < objeto.descripciones.length; i++){
			if(i > 0)
				desc += " ||' '|| ";
			desc += objeto.descripciones[i].nbReal;
		}
		scriptExecute += desc +" AS description ";
		scriptExecute += " FROM " + objeto.tablaActual.nbReal;
		
		if(objeto.restricciones.length >= 1){
			scriptExecute += " WHERE ";
			var restic = '';
			for(var j=0; j < objeto.restricciones.length; j++){
				if(j > 0)
					restic += " AND ";
				var rest = filtroTipoOperador(objeto.restricciones[j].tipoOperador, objeto.restricciones[j].valor);
				restic += objeto.restricciones[j].nbReal +' '+ rest;
			}
			scriptExecute += restic;
		}
		if(objeto.ordenes.length >= 1){
			scriptExecute += " ORDER BY ";
			var orders = '';
			for (var k = 0; k < objeto.ordenes.length; k++) {
				if (k > 0) {
					orders += ',';
				}
				orders += objeto.ordenes[k].nbReal +' '+
						  objeto.ordenes[k].tipoOrdenamiento;
			}
			scriptExecute += orders;
		}
		return scriptExecute;
	};
	function filtroTipoOperador (objectOperador, valores){
		var strReturn = '';
		if(objectOperador != null && objectOperador.stValorRequerido == 1){
			switch (objectOperador.cdOperador) {
				case "IGUAL":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "DIF":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "MAY":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "MAYEQUAL":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "MEN":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "MENEQUAL":
					strReturn += objectOperador.txValor+' \''+valores + '\'';
					break;
				case "LIKE":
					strReturn += objectOperador.txValor+' \'%'+valores + '%\'';
					break;
				case "BETWEEN":
					var valoresSeparados = valores.split('|');
					strReturn += objectOperador.txValor +' '+ 
					 			 valoresSeparados[0] +' AND '+ 
					 			 valoresSeparados[1];
					break;
				case "IN":
					strReturn = inNotInStr(objectOperador.txValor, valores);
					break;
				case "NOTIN":
					strReturn = inNotInStr(objectOperador.txValor, valores);
					break;
				default:
					break;
			}
		}else if(objectOperador != null && objectOperador.stValorRequerido == 0){
			strReturn += objectOperador.txValor;
		}
		return strReturn;
	};
	
	function inNotInStr(operador, valores){
		var valoresSeparados = valores.split('|');
		var val = '';
		var strReturn='';
		strReturn += operador +'( ';
		for(var i=0; i < valoresSeparados.length; i++){
			if(i > 0)
				val += ',';
			val += '\'' + valoresSeparados[i] + '\'';
		}
		strReturn += val + ') ';
		return strReturn;
	};
	
	$scope.identificarParametros = function (objecto, paramActual){
		$scope.propiedadTipoQuery['verificacionQuery'+paramActual] = false;
		$scope.propiedadTipoQuery['valParam'+paramActual] = undefined;
		$scope.propiedadTipoQuery.testQuery = undefined;
		$scope.propiedadTipoQuery['verificacionQuery'+paramActual] = false;
		var query = objecto[paramActual];
		if(query == undefined){
			growl.warning('La consulta es requerida', {ttl:2000});
		}else{
			var strQuery = query.toLowerCase();
			if(strQuery.includes('ld_key') && strQuery.includes('ld_descripcion')){
				admiReporteService.checkParams(query).success(function(data) {
					if(data != null){
						if(data.length <= 0){
							growl.warning('Agregue al menos un parámetro',{ttl: 3000});
							return;
						}else if(data.length > 1){
							growl.warning('Solo puede agregar un parámetro, se han detectado más de uno, favor de validar',{ttl: 5000});
							return;
						}else{
							$scope.propiedadTipoQuery['verificacionQuery'+paramActual] = true;
							$scope.propiedadTipoQuery['cdParamPrevia'+paramActual] = data[0].cdParametro;
						}
					}
				}).error(function(data) {
					growl.error($scope.mensajeModal('Algo salió mal al identificar parametros') + ': ' + data.message, {ttl : 4000});
				});
			}else{
				growl.warning('La consulta debe tener el siguiente formato, '+
						'ej. select campo1 as ld_key, campo2 as ld_descripcion from tabla1 where campo3 in (#{parametro})',{ttl: 6000});
			}
		}
	};
	$scope.ejecutarQueryBusPrevia = function (objecto, paramActual, separador){
		var arrayParamValue = [];
		var cdParam = $scope.propiedadTipoQuery['cdParamPrevia'+paramActual];
		var valParam = $scope.propiedadTipoQuery['valParam'+paramActual];
		if(valParam == undefined){
			growl.warning('Agregue al menos un valor o valores separados por '+ separador, {ttl:3000});
			return;
		}
		var valSeparados = valParam.split(separador); 
		var query = objecto[paramActual];
		if(cdParam != undefined && valParam != undefined && query != undefined){
			arrayParamValue.push({'param' : cdParam,'value' : valSeparados.toString()});
		}
		if(arrayParamValue.length > 0){
			admiReporteService.crearTestQuery(arrayParamValue, query).success(function(data, status, headers, config) {
				$scope.propiedadTipoQuery.testQuery = data;
			}).error(function(data, status, headers, config) {
				growl.error('Ocurrió un error en el proceso, '+ data.message, {ttl:5000});
			});
		}
	};
	

	/**
	 * @descripcion Método para obtener objetos iguales dentro de dos listas dadas 
	 * @author Jorge Luis
	 * */
	function intersectElements (setA, setB){
	  return new Set([...setA].filter(x => setB.has(x)));
	};
	
	/**
	 * @descripcion Método para obtener objetos diferentes dentro de dos listas dadas 
	 * @author Jorge Luis
	 * */
	function diffElements(setA, setB){
		  return new Set([...setA].filter(x => !setB.has(x)));
		};
	
	/**Comprobar los parametros en caso 
	 * de que sea la edición comprobar los nuevos 
	 * y agregarlos la la lista original*/
	function comprobarEdicionQuery(paramasPeticion, listaParametros){
		if(listaParametros.length > 0 && paramasPeticion.length > 0){
			//Parametros aplicados
			let aplicados = new Set([]);
			//Parametro de petición
			let dePeticion = new Set([]);
			
			let paramInterseccion = new Set([]);
			let paramBorrar = new Set([]);
			let paramNuevos = new Set([]);
			
			angular.forEach(listaParametros, function(value, key) {
				aplicados.add(value.cdParametro);
			});
			angular.forEach(paramasPeticion, function(value, key) {
				dePeticion.add(value.cdParametro);
			});
			paramInterseccion = intersectElements(aplicados, dePeticion);//sacar elemento iguales
			paramBorrar = diffElements(aplicados, dePeticion);//detectar elementos a borrar
			paramNuevos = diffElements(dePeticion, aplicados);//detectar elementos nuevos
			
			var cambiosAplicar = {'toDelete': paramBorrar, 'toAdd': paramNuevos,'listPeticion':paramasPeticion};
			
			if(paramBorrar.size > 0){
				var strDelete = Array.from(paramBorrar).toString();
				showAlert.confirmacion("Se perderán las configuraciones realizadas sobre los parámetros : " + strDelete,
						confirmaRemoverElementos, cambiosAplicar, 
						cancelaRemoverElementos,cambiosAplicar);
			}else if(paramBorrar.size <= 0 && paramNuevos.size > 0){
				//agregar todos los nuevos elementos
				for (let item of paramNuevos){
					for(var i=0; i < paramasPeticion.length; i++){
						if(item === paramasPeticion[i].cdParametro)
							$scope.params.push(paramasPeticion[i]);
					}
				}
				mostrarConfigValidadcionCorrecto();
				comprobarDependenciasYParametros();
			}else if(paramBorrar.size <= 0 && paramNuevos.size <= 0 && 
					(paramInterseccion.size === listaParametros.length) && 
					(paramInterseccion.size == paramasPeticion.length)){
				mostrarConfigValidadcionCorrecto();
			}else{
				mostrarConfigValidadcionCorrecto();
			}
		}else{
			angular.copy(paramasPeticion, listaParametros);
			mostrarConfigValidadcionCorrecto();
		}
	};
	
	/*Comprobar los parámetros obtenidos*/
	function comprobarDependenciasYParametros (){
		let listDep = $scope.reportesTaqVO.dependenciasSelect; 
		let ban = true;
		if(listDep != undefined){
			for(let i=0; i < listDep.length; i++){
				if(!$scope.params.includes(listDep[i].cdParametroHijo) && 
				   !$scope.params.includes(listDep[i].cdParametroPadre)){
					$scope.reportesTaqVO.dependenciasSelect.splice(i, 1);
					if($scope.reportesTaqVO.dependenciasSelect.length <= 0){
						$scope.reportesTaqVO.dependenciasSelect = undefined;
						$scope.parametrosDependientes.paramestrosTipoCatalogo.length = 0;
						$scope.parametrosDependientes.catInSelect.length = 0;
					}
						
				}
			}
		}
	};
	
	confirmaRemoverElementos = function (data){
		/*borrar los elementos indicados*/
		for (let item of data.toDelete){
			for(var i=0; i <$scope.params.length; i++){
				if(item === $scope.params[i].cdParametro)
					$scope.params.splice(i, 1);
			}
		}
		for (let item of data.toAdd){
			for(var i=0; i < data.listPeticion.length; i++){
				if(item === data.listPeticion[i].cdParametro)
					$scope.params.push(data.listPeticion[i]);
			}
		}
		mostrarConfigValidadcionCorrecto();
		comprobarDependenciasYParametros();
	};
	
	function reordenarElementos (listPeticion){
		var listTMP = [];
		if(listPeticion.length > 0){
			for(var i=0; i < listPeticion.length; i++){
				for(var j=0; j <$scope.params.length; j++){
					if(listPeticion[i].cdParametro === $scope.params[j].cdParametro){
						listTMP.push($scope.params[j]);
					}
				}
			}
		}
		if(listTMP.length > 0){
			angular.copy(listTMP, $scope.params);
		}
	};
	
	cancelaRemoverElementos = function (data){
		return;
	};
	
	function getCaracterPorValue(value){
		for(var i=0; i < $scope.catCaracteres.length; i++){
			if(value === $scope.catCaracteres[i].txValor){
				return $scope.catCaracteres[i];
			}
		}
		return null;
	};
	
	confirmaEdicionScript = function (data){
		//var obj = {'listaTMPQuitados':listaTMPQuitados,'lNuevos':lNuevos};
		for(var m=0; m < data.listaTMPQuitados.length; m++){
			for(var n=0; n <$scope.params.length; n++){
				if(data.listaTMPQuitados[m].cdParametro === $scope.params[n].cdParametro){
					$scope.params.splice(n, 1);
				}
			}
		}
		/*Una vez que se eliminar los parámetros 
		 se agregan los nuevos*/
		var listTMP = [];
		if(data.lNuevos != undefined){
			listTMP = $scope.params.concat(data.lNuevos);
			angular.copy(listTMP, $scope.params);
		}
		mostrarConfigValidadcionCorrecto();
	};
	cancelaEdicionScript = function (data){
		return;
	};
	
	
	
	
	confirmaRenombrado = function (listaRenombrados){
		for(var i=0; i < listaRenombrados.length; i++){
			for(var j=0; j <$scope.params.length; j++){
				if(listaRenombrados[i].cdParamOriginal === $scope.params[j].cdParametro){
					$scope.params[j] = listaRenombrados[i].nuevoObjetoRenombrado;
					break;
				}
			}
		}
		mostrarConfigValidadcionCorrecto();
	};
	
	cancelaRenombrado = function (listaRenombrados){
		return;
	};
	
	function mostrarConfigValidadcionCorrecto(){
		if($scope.params.length > 0){
			angular.forEach($scope.params, function(param, key) {
				//param.catalogoPropiedades = angular.copy($scope.propiedades);
				$scope.paramValue[key] = undefined;
				$scope.objCheck[param.cdParametro] = false;//construir el objeto para las opciones de marcar todos
				$timeout(function() { //timeout para que angular le de tiempo limpiar el form
					$scope.testQueryForm.$setPristine();
				}, true);
			});
			//respaldo de los parámetro que se editarán
			angular.copy($scope.params, $scope.edicionQuery.respaldoParametros);
			$scope.showModalScript = true;
			$scope.reportesTaqVO.queryFull = scriptFullTmp;
			$scope.edicionQuery.banderaValidacion = true;
		}else{
			$scope.showModalScript = false;
		}
		
	};
	
	/**Función para verificar si hay cambios en el form
	 * antes de cerra el modal*/
	$scope.checarSiHayCambio = function(paramActual) {
		/*comprobar si se tiene algún cambio*/
		var lengthList = $scope.listaCompPropModal.length;
		var objParam = obtenerParametroActualByCd(paramActual);
		var propiedadesActuales = objParam.propieades;
		$scope.propiedadesTMP = [];
		angular.copy(objParam.propieades, $scope.propiedadesTMP);
		var cambio = false;
		if (lengthList > 0) {
			var listTMP = [];
			for (var i = 0; i < lengthList; i++) {
				if ($scope.listaCompPropModal[i].checkedComp) {
					listTMP.push($scope.listaCompPropModal[i]);
					delete $scope.listaCompPropModal[i]['paramPropScriptVO'];
				}
			}
			if(propiedadesActuales != undefined && propiedadesActuales.length > 0){
				angular.forEach(propiedadesActuales, function(value, key) {
					delete value['paramPropScriptVO'];
				});
				if(angular.equals(listTMP, propiedadesActuales)){
					$scope.modalConfigParam = false; //si no hay cambio ocultar el modal
					$scope.objCheck[paramActual] = false;
					$scope.propiedadTipoQuery['requireQuery'+paramActual] = false;
					if($scope.propiedadTipoQuery.testQuery != undefined)$scope.propiedadTipoQuery.testQuery.mensaje = undefined;
				}else{
					showAlert.confirmacion("Los cambios realizados en el formulario se perderán", confirmaModal, paramActual, cancelaModal,paramActual);
				}
				if($scope.propiedadesTMP.length > 0)
					angular.copy($scope.propiedadesTMP, objParam.propieades);
			}else if(listTMP.length > 0){
				showAlert.confirmacion("Los cambios realizados en el formulario se perderán", confirmaModal, paramActual, cancelaModal,paramActual);
			}else{
				$scope.modalConfigParam = false; //si no hay cambio ocultar el modal
				$scope.objCheck[paramActual] = false;
				$scope.propiedadTipoQuery['requireQuery'+paramActual] = false;
				if($scope.propiedadTipoQuery.testQuery != undefined)$scope.propiedadTipoQuery.testQuery.mensaje = undefined;
			}
		}else{
			$scope.modalConfigParam = false; //si no hay cambio ocultar el modal
			$scope.objCheck[paramActual] = false;
			$scope.propiedadTipoQuery['requireQuery'+paramActual] = false;
			if($scope.propiedadTipoQuery.testQuery != undefined)$scope.propiedadTipoQuery.testQuery.mensaje = undefined;
		}
	};
	
	$scope.comprobarParamTipCar = function (actualParam){
		if(actualParam.configParamTipoCatVO != undefined && $scope.cambiosEfectuadosTipCat[actualParam.cdParametro]){
			var v1=true,v2=true,v3=true,v4=true,v5=true;
			/*Validando si cambió la tabla seleccionada*/
			var tablaAplicada = $scope.objetoConCambiosAplicados[actualParam.cdParametro].tablaActual;
			var tablaSeleccionada = filterTablaById($scope.idTablaSeleccionada);
			v1 = angular.equals(tablaAplicada, tablaSeleccionada)
			
			/*Validando si cambió el identificador actual*/
			var identificadorAplicado = $scope.objetoConCambiosAplicados[actualParam.cdParametro].identificador[0];
			//delete identificadorAplicado['checked'];
			var identificadorSelected;
			if($scope.catalogoConfigurado.identificador != undefined){
				identificadorSelected = JSON.parse($scope.catalogoConfigurado.identificador);
				//delete identificadorSelected['checked'];
				v2 = angular.equals(identificadorSelected, identificadorAplicado);
			}
			/*Validando si cambiaron las descripciones*/
			var listaDescripcionesMarcadas = filtrarCheckMarcados($scope.objeto.desc);
			var listaDescripcionesAplicadas = $scope.objetoConCambiosAplicados[actualParam.cdParametro].descripciones;
			v3 = angular.equals(listaDescripcionesMarcadas, listaDescripcionesAplicadas);
			
			/*validando si cambiaron las restricciones*/
			var listaRestriccionesAplicadas = $scope.objetoConCambiosAplicados[actualParam.cdParametro].restricciones;
			var listaRestriccionesMarcadas = filtrarCheckMarcados($scope.objeto.restri);
			v4 = angular.equals(listaRestriccionesAplicadas, listaRestriccionesMarcadas);
			
			/*validando si cambiaron los ordenamientos*/
			var listaOrdenamientoAplicadas = $scope.objetoConCambiosAplicados[actualParam.cdParametro].ordenes;
			var listaOrdenesMarcadas = filtrarCheckMarcados($scope.objeto.order);
			v5 = angular.equals(listaOrdenamientoAplicadas, listaOrdenesMarcadas);
			
			if(!v1 || !v2 || !v3 || !v4 || !v5){
				showAlert.confirmacion("Los cambios sin aplicar se perderán, ¿Desea continuar?", comprobarConfirmaPerder, actualParam, comprobarCancelaPerder);
			}else{
				$scope.idTablaSeleccionada = undefined;
				$scope.preseleccionarColumna = false;
				$scope.objeto = {iden: [],desc: [],restri: [],order: []};
				//actualParam.stIsCatalogo = 0;
				$scope.modalConfigTipoCat = false;
				$scope.catalogoConfigurado.identificador = undefined;
				if($scope.resultadoCatalogo[actualParam.cdParametro] != undefined)
					$scope.resultadoCatalogo[actualParam.cdParametro].mensaje = undefined;
			}
			
		}else{
			$scope.idTablaSeleccionada = undefined;
			$scope.preseleccionarColumna = false;
			$scope.objeto = {iden: [],desc: [],restri: [],order: []};
			actualParam.stIsCatalogo = 0;
			$scope.modalConfigTipoCat = false;
			$scope.catalogoConfigurado.identificador = undefined;
			if($scope.resultadoCatalogo[actualParam.cdParametro] != undefined)
				$scope.resultadoCatalogo[actualParam.cdParametro].mensaje = undefined;
		}
	};
	

	comprobarConfirmaPerder = function (actualParam){
		$scope.modalConfigTipoCat = false;
		$scope.idTablaSeleccionada = undefined;
		$scope.preseleccionarColumna = false;
		$scope.objeto = {iden: [],desc: [],restri: [],order: []};
		//$scope.objectCatalogosParam = undefined;
	};
	
	comprobarCancelaPerder = function (){
		$scope.modalConfigTipoCat = true;
		return;
	};
	

	/**Función de la confimación del cierre de modal*/
	function confirmaModal(object) {
		$scope.modalConfigParam = false;
		$scope.valoresMarcadorAgregados = false;
		$scope.objCheck[object] = false;
		$scope.propiedadTipoQuery['requireQuery'+object] = false;
		if($scope.propiedadTipoQuery.testQuery != undefined)$scope.propiedadTipoQuery.testQuery.mensaje = undefined;
	};
	
	/**Función para la cancelación del cierre dle modal*/
	function cancelaModal(object) {
		$scope.modalConfigParam = true;
	};
	/**Función para filtrar el catálogo de columnas por el id de la tabla*/
	function filtrarColumnasPorTabla(idTabla, listaColumns,paremetro){
		var listaReturn = [];
		if(listaColumns.length > 0)
			for(var i=0; i < listaColumns.length; i++){
				if(listaColumns[i].idTabla == idTabla)
					listaReturn.push(listaColumns[i]);
			}
		return listaReturn;
	};
	/**Función para filtrar el catálogo por el ID unico*/
	function filterTablaById(idTabla){
		var objectReturn = {};
		for(var i=0; i < $scope.catalogoTablas.length; i++){
			if($scope.catalogoTablas[i].idTabla == idTabla){
				angular.copy($scope.catalogoTablas[i] , objectReturn);
				break;
			}	
		}
		return objectReturn;
	};
	/**función para filtrar las columnas que fueron marcadas*/
	function filtrarCheckMarcados (listaDeObjectos, evaluate){
		var listaPropsReturn = [];
		if(listaDeObjectos != undefined){
			for(var i = 0; i < listaDeObjectos.length; i ++){
				if(listaDeObjectos[i].check){
					var tipoOperador = listaDeObjectos[i].tipoOperador != null ? listaDeObjectos[i].tipoOperador : null; 
					if(evaluate != undefined && listaDeObjectos[i].valor != null && tipoOperador != null){
						if(tipoOperador.cdOperador === 'BETWEEN'){
							var valores = listaDeObjectos[i].valor.split('|');
							if(valores.length <=1){
								return "MEN1";
							}else if(valores.length > 2){
								return "MAY2";
							}
						}						
					}
					listaPropsReturn.push(listaDeObjectos[i]);
				}
			}
		}
		return listaPropsReturn;
	};
	
	
	
		
	/**Función para marcar una
	 * propiedad del componente*/
	$scope.checkProp = function(type,actualParamInModal, componente, newValue, oldValue) {
		$timeout(function() {
			if ($scope.objCheck[actualParamInModal] != null && type === 'ALL') {
				if ($scope.objCheck[actualParamInModal]) { //marcar todos
					for (var i = 0; i < $scope.listaCompPropModal.length; i++) {
						$scope.listaCompPropModal[i].checkedComp = true;
					}
				} else { //desmarcar todos
					for (var j = 0; j < $scope.listaCompPropModal.length; j++) {
						$scope.listaCompPropModal[j].checkedComp = false;
						$scope.listaCompPropModal[j].value = undefined;
						$('#select2-value'+$scope.listaCompPropModal[j].idPropiedad+'-container').text('Seleccione una opción');
					}
					$scope.cambioSeparador(null, actualParamInModal);
				}
			} else if (type === 'ONE') {
				var stBan = false;
				for (var k = 0; k < $scope.listaCompPropModal.length; k++) {
					if ($scope.listaCompPropModal[k].checkedComp) {
						stBan = true;
					} else {
						stBan = false;
						$scope.listaCompPropModal[k].value = undefined;
						$('#select2-value'+componente.idPropiedad+'-container').text('Seleccione una opción');
						break;
					}
				}
				if (stBan) {
					$scope.objCheck[actualParamInModal] = true;
				} else {
					$scope.objCheck[actualParamInModal] = false
				}
			}
		},true);
		/*Vaciar el campo actual*/
		if(componente !=  null && componente.stValorRequerido ==1 && !newValue && componente.cdPropiedad !== 'QUERY'){
			componente.value = undefined;
		}else if(componente !=  null && 
				 componente.stValorRequerido ==1 && 
				 !newValue &&  
				 componente.cdPropiedad == 'QUERY' &&  
				 $scope.propiedadTipoQuery[actualParamInModal] == undefined){
			componente.value = undefined;
		}
		
		/*Comprobar si la propiedad checada es de tipo QUERY, de ser el caso
		 * requerir un propiedad extra que va ser el QUERY*/
		if(componente !=  null && componente.cdPropiedad == 'QUERY' && !newValue && $scope.propiedadTipoQuery.testQuery != undefined){
			var obj = {'actualParamInModal':actualParamInModal,'componente':componente,'newValue':newValue};
			showAlert.confirmacion("La configuración sobre esta propiedad se perderá",
					confirmaPerderTipoQuery, obj, cancelaPerderTipoQuery,actualParamInModal);
			$timeout(function() {
				componente.checkedComp = oldValue;
				componente.value = componente.value;
				var valActual = getCaracterPorValue(componente.value);
				$('#select2-value'+componente.idPropiedad+'-container').text(valActual.txCaracter);
			}, true);
		}else{
			limpiarAtributosPropiedadTipoQuery(actualParamInModal);
		}
	};
	
	confirmaPerderTipoQuery = function (data){
		limpiarAtributosPropiedadTipoQuery(data.actualParamInModal);
		$('#select2-value'+data.componente.idPropiedad+'-container').text('Seleccione una opción');
		data.componente.checkedComp = data.newValue;
		data.componente.value = undefined;
	};
	cancelaPerderTipoQuery = function (data){
		return;
	};
	
	function limpiarAtributosPropiedadTipoQuery (actualParamInModal){
		$scope.propiedadTipoQuery['requireQuery'+actualParamInModal] = false;
		$scope.propiedadTipoQuery[actualParamInModal] = undefined;
		$scope.propiedadTipoQuery.testQuery = undefined;
		$scope.propiedadTipoQuery[actualParamInModal] = undefined;
		$scope.propiedadTipoQuery['verificacionQuery'+actualParamInModal] = false;
	};
	
$scope.configurarParamDependientes = function (data, dependenciasActuales){
		if(data.paramestrosTipoCatalogo.length > 0){
			var obj = {};
			var arrayObj = [];
			angular.forEach(data.paramestrosTipoCatalogo, function(value, key) {
				obj = {'cdParametro':value.cdParametro,
					   'configVOActual':value.configParamTipoCatVO,
					   'txParametro':value.configParamTipoCatVO.tablaActual.nbAlias+'('+value.cdParametro+')'};
				arrayObj.push(obj);
			});
			angular.copy(arrayObj,$scope.parametrosDependientes.catInSelect);
			angular.copy($scope.parametrosDependientes.catInSelect, $scope.parametrosDependientes.catInSelectTMP);
			//console.log($scope.parametrosDependientes.catInSelectTMP);
		}
		
		if(dependenciasActuales != undefined && dependenciasActuales.length > 0){
			$scope.parametrosDependientes.elementosDependientes = []; 
			angular.forEach(dependenciasActuales, function(value, key) {
				$scope.parametrosDependientes.elementosDependientes.push([]);
			});
			
			var listaCat = $scope.parametrosDependientes.catInSelect;
			//ITERARACION DE LA ONFIGURACION ACTUAL
			for(var i=0; i < dependenciasActuales.length; i++){
				//ITERACION DEL CATALOGO ACTUAL
				for(var j=0; j < listaCat.length; j++){
					if(dependenciasActuales[i].cdParametroPadre === listaCat[j].cdParametro){
						$scope.objectDependencias['paramPadre'+i] = listaCat[j];
						$scope.parametrosDependientes.listInSelect['listaPadre'+i] = [];
						angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaPadre'+i]);
						$scope.setParamPadre(dependenciasActuales[i].columnaPadre,i);
						
					}
					if(dependenciasActuales[i].cdParametroHijo === listaCat[j].cdParametro){
						$scope.objectDependencias['paramHijo'+i] = listaCat[j];
						$scope.parametrosDependientes.listInSelect['listaHijo'+i] = [];
						angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaHijo'+i]);
						$scope.setParamHijo(dependenciasActuales[i].columnaHijo,i);
					}
					var padre =$scope.objectDependencias['paramPadre'+i];
					var hijo = $scope.objectDependencias['paramHijo'+i];
					if(padre  != undefined && hijo != undefined)
						comprobarMismo(padre,hijo,i);
						$scope.dependenciasAplicadas.cambioAplicado = true;
				}
			}			
		}else if($scope.parametrosDependientes.elementosDependientes.length <= 0){
			$scope.parametrosDependientes.elementosDependientes.push([]);
			$scope.parametrosDependientes.listInSelect['listaPadre'+0] = [];
			$scope.parametrosDependientes.listInSelect['listaHijo'+0] = [];
			angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaPadre'+0]);
			angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaHijo'+0]);
		}else{
			$scope.parametrosDependientes.listInSelect['listaPadre'+0] = [];
			$scope.parametrosDependientes.listInSelect['listaHijo'+0] = [];
			angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaPadre'+0]);
			angular.copy($scope.parametrosDependientes.catInSelectTMP,$scope.parametrosDependientes.listInSelect['listaHijo'+0]);
		}
		//bandera para mostrar el modal
		$scope.showModalDependencia = true;
	};
	/*Función para agregar elemento de configuración de dependencia*/
	$scope.agregarElementoDependencia = function (listaOriginal){
		//agregarElementoDependencia
		$scope.parametrosDependientes.elementosDependientes.push([]);
		var index = $scope.parametrosDependientes.elementosDependientes.length - 1;
		$scope.parametrosDependientes.listInSelect['listaPadre'+index] = [];
		$scope.parametrosDependientes.listInSelect['listaHijo'+index] = [];
		angular.copy(listaOriginal,$scope.parametrosDependientes.listInSelect['listaPadre'+index]);
		angular.copy(listaOriginal,$scope.parametrosDependientes.listInSelect['listaHijo'+index]);
		//$scope.dependenciasAplicadas.cambioAplicado = false;
	};
	
	$scope.setParamPadre = function (data,index){
		$scope.modeloAsociaciones['idPadreSelect'+index] = data;
		$scope.dependenciasAplicadas.cambioAplicado = false;
	};
	
	$scope.setParamHijo = function (data,index){
		$scope.modeloAsociaciones['idHijoSelect'+index] = data;
		$scope.dependenciasAplicadas.cambioAplicado = false;
	};
	
	/*Función para eliminar elemento dependiente de la lista*/
	$scope.removerElementoDependencia = function (indexOfArray, dataosConfigs){
		var object ={'indexOfArray':indexOfArray,'dataosConfigs':dataosConfigs};
		showAlert.confirmacion("¿Esta seguro de eliminar este elemento?", 
				confirmQuitarElementoArray, object, cacnelQuitarElementoArray,object);
	};
	
	confirmQuitarElementoArray = function (data){
		$scope.parametrosDependientes.elementosDependientes.splice(data.indexOfArray, 1);
		if(data.dataosConfigs != undefined && data.dataosConfigs.length > 0){
			data.dataosConfigs.splice(data.indexOfArray, 1);
		}
		//remover los atributos creados dinamicamente
		$timeout(function() {
			delete $scope.modeloAsociaciones['idPadreSelect'+data.indexOfArray];
			delete $scope.modeloAsociaciones['idHijoSelect'+data.indexOfArray];
			delete $scope.objectDependencias['paramPadre'+data.indexOfArray];
			delete $scope.objectDependencias['paramHijo'+data.indexOfArray];
			delete $scope.objectDependencias['paramPadreColumns'+data.indexOfArray];
			delete $scope.objectDependencias['paramHijoColumns'+data.indexOfArray];
		}, 100);
	};
	cacnelQuitarElementoArray = function (data){
		return;
	};
	
	$scope.aplicarConfiguracionDependencia = function (data){
		var dependenciasSelect = [];
		var objectDependenciasSelect = {};
		
		if($scope.modalFormDependencia.$invalid) {
			showAlert.requiredFields($scope.modalFormDependencia);
			growl.error('Formulario incompleto');
		} else {
			for(var i=0; i < data.length; i++){
				if($scope.objectDependencias['paramPadre'+i] != undefined && $scope.objectDependencias['paramHijo'+i] != undefined){
					var paramPadre = $scope.objectDependencias['paramPadre'+i].cdParametro;
					var paramHijo = $scope.objectDependencias['paramHijo'+i].cdParametro;
					var columnaPadre = $scope.modeloAsociaciones["idPadreSelect"+i];
					var columnaHijo = $scope.modeloAsociaciones["idHijoSelect"+i];
					objectDependenciasSelect = {'cdParametroPadre':paramPadre,'cdParametroHijo':paramHijo,'columnaPadre':columnaPadre,'columnaHijo': columnaHijo};
					dependenciasSelect.push(objectDependenciasSelect);
				}else{	
				}
			}
			if(dependenciasSelect.length > 0){
				$scope.reportesTaqVO.dependenciasSelect = dependenciasSelect;
				$scope.dependenciasAplicadas.cambioAplicado = true;
				growl.success('La configuración se aplicó correctamente', {ttl: 3000});
			}
		}
	};
	
	$scope.changeSelectPadre = function (data, index){
		var dataSelected = data['paramPadre'+index];
		if(dataSelected == undefined)
			$scope.modeloAsociaciones['idPadreSelect'+index] = undefined;
		var ban = comprobarMismo(dataSelected,$scope.objectDependencias['paramHijo'+index],index);
		$scope.dependenciasAplicadas.cambioAplicado = false;
	};
	$scope.changeSelectHijo = function (data,index){
		var dataSelected = data['paramHijo'+index];
		if(dataSelected == undefined)
			$scope.modeloAsociaciones['idHijoSelect'+index] = undefined;
		var ban = comprobarMismo($scope.objectDependencias['paramPadre'+index],dataSelected,index);
		$scope.dependenciasAplicadas.cambioAplicado = false;
	};
	
	function comprobarMismo(padre, hijo,index){
		if(padre == null && hijo != null){//padre null
			$scope.objectDependencias['paramPadreColumns'+index]=[];
		}else if(padre != null && hijo == null){
			$scope.objectDependencias['paramHijoColumns'+index]=[];
		}else if(padre == null && hijo == null){
			$scope.objectDependencias['paramPadreColumns'+index]=[];
			$scope.objectDependencias['paramHijoColumns'+index]=[];
		}else if(padre != null && hijo != null){//si ambos son indefinidos	
			if(angular.equals(padre,hijo)){
				growl.warning('Debe seleccionar diferentes parámetros', {ttl: 3000});
				$timeout(function() {
					resetFields(index);
				}, 100);
				return;
			}else{
				$timeout(function() {
					$scope.objectDependencias['paramPadreColumns'+index]=filtrarColumnasPorTabla(padre.configVOActual.tablaActual.idTabla, $scope.catalogoColumnas);
					$scope.objectDependencias['paramHijoColumns'+index]=filtrarColumnasPorTabla(hijo.configVOActual.tablaActual.idTabla, $scope.catalogoColumnas);
				}, 100);
			}
		}
	};
	
	function resetFields(key){
		$scope.objectDependencias['paramPadre'+key] = undefined;
		$scope.objectDependencias['paramHijo'+key] = undefined;
		$('#select2-paramColumnPadre'+key+'-container').text("Seleccione una opción");
		$('#select2-paramColumnHijo'+key+'-container').text("Seleccione una opción");
		$scope.objectDependencias['paramPadreColumns'+key]=[];
		$scope.objectDependencias['paramHijoColumns'+key]=[];
		$scope.modeloAsociaciones['idPadreSelect'+key] = undefined;
		$scope.modeloAsociaciones['idHijoSelect'+key] = undefined;
		$timeout(function (){//timeout para que angular le de tiempo limpiar el form
			$scope.modalFormDependencia.$setPristine();
		}, true);
	};
	
	$scope.resetearContenidoDependencia = function (data, datosAplicados, originalList){
		if(datosAplicados != undefined){
			showAlert.confirmacion("Las asociaciones actuales se perderán", 
					confirmaPerderAsociacion, data, cancelaPerderAsociacion,data);
		}else{
			reset(data);
			data.length = 1;
			angular.forEach(data, function(value, key) {
				angular.copy(originalList, $scope.parametrosDependientes.listInSelect['listaHijo'+key]);
				angular.copy(originalList,$scope.parametrosDependientes.listInSelect['listaPadre'+key]);
			});
		}
	};
	
	confirmaPerderAsociacion = function (data){
		$scope.reportesTaqVO.dependenciasSelect = undefined;
		reset(data);
		data.length = 1;
	};
	cancelaPerderAsociacion = function (){
		return;
	};
	
	function aquitarElementoEnHijo (elementoToDelete,index, originalList){
		var list = [], listTMP = [];
		angular.copy(originalList, $scope.parametrosDependientes.listInSelect['listaHijo'+index]);
		angular.copy($scope.parametrosDependientes.listInSelect['listaHijo'+index], list);
		angular.copy($scope.parametrosDependientes.listInSelect['listaHijo'+index], listTMP);
		if(listTMP != undefined && listTMP.length > 0){
			for(var i=0; i < listTMP.length; i++){
				if(elementoToDelete['paramPadre'+index].cdParametro === listTMP[i].cdParametro){
					list.splice(i, 1);
				}
			}
		}
		if(list.length > 0)
			angular.copy(list, $scope.parametrosDependientes.listInSelect['listaHijo'+index]);
	};
	
	confirmPerderDepencia = function (data){
		$scope.reportesTaqVO.dependenciasSelect = undefined;
		reset(data);
		data.length = 1;
	};
	
	cancelaPerderDepencia = function (data){
		return;
	};
	
	
	function reset(data){
		angular.forEach(data, function(value, key) {
			resetFields(key);
		});
	};
	
	$scope.cerarModalConfigDependencia = function(data,aplicados){
		if(!$scope.dependenciasAplicadas.cambioAplicado && aplicados != undefined){
			showAlert.confirmacion("Los cambios que no se hayan aplicado se perderán, ¿Desear continuar?", 
					confirmaPerderDependencia, data, cancelaPerderDependencia,data);
		}else{
			data.length = 1;
			confirmaPerderDependencia(data);
		}
	};
	confirmaPerderDependencia = function (data){
		$scope.showModalDependencia = false;
		reset(data);
	};
	cancelaPerderDependencia = function (data){
		return;
	};
	
	
	$scope.cambioSeparador = function (separador, cdParamActual){
		if(separador != null){
			$scope.esListaDoble['separador'+cdParamActual] = separador;
		}else{
			$scope.propiedadTipoQuery['requireQuery'+cdParamActual] = false;
			$scope.propiedadTipoQuery[cdParamActual] = undefined;
			$scope.propiedadTipoQuery.testQuery = undefined;
			$scope.propiedadTipoQuery[cdParamActual] = undefined;
			$scope.propiedadTipoQuery['verificacionQuery'+cdParamActual] = false;
			return;
		}
		var listaProp = [];
		angular.copy($scope.listaCompPropModal, listaProp);
		if(listaProp.length > 0)
			for (var i = 0; i < listaProp.length; i++) {
				if(listaProp[i].cdPropiedad == 'QUERY' && listaProp[i].checkedComp){
					$scope.propiedadTipoQuery['requireQuery'+cdParamActual] = true;
				}else if(listaProp[i].cdPropiedad == 'QUERY' && !listaProp[i].checkedComp){
					$scope.propiedadTipoQuery['requireQuery'+cdParamActual] = false;
					$scope.propiedadTipoQuery[cdParamActual] = undefined;
					$scope.propiedadTipoQuery.testQuery = undefined;
					$scope.propiedadTipoQuery[cdParamActual] = undefined;
					$scope.propiedadTipoQuery['verificacionQuery'+cdParamActual] = false;
				}
				//console.log($scope.listaCompPropModal[i]);
			}
	};
	
	/**Función para agregar las propiedades checadas
	 * dentro de la lista de propiedades en el modal*/
	$scope.guadaTmpPropCompo = function(paramActual, objectPropScript,switcherProp) {
		var objParam = obtenerParametroActualByCd(paramActual);
		if(objParam.componente.cdComponente === "SWITCH"){
			if ($scope.modalCompo.$invalid) {
			showAlert.requiredFields($scope.modalCompo);
			growl.error('Formulario incompleto');
			return;
			}else{
			for (var j = 0; j < $scope.params.length; j++) {
			if($scope.params[j].componente != undefined && $scope.params[j].componente.cdComponente === "SWITCH"){
			let v = switcherProp['switchLabelUno'+paramActual]+"|"+switcherProp['switchValueUno'+paramActual]+
			","+switcherProp['switchLabelDos'+paramActual]+"|"+switcherProp['switchValueDos'+paramActual];
			$scope.params[j].txValor = v;
			growl.success('Aplicado correctamente' , {ttl: 3000});
			// debugger;
			}
			}

			return;
			}
		}
		var listaPropActTam = objParam.propieades.length;
		var listaNuevaAAsociar = [];
		if ($scope.modalCompo.$invalid) {
			showAlert.requiredFields($scope.modalCompo);
			growl.error('Formulario incompleto');
			return;
		} else {
			var lengthList = $scope.listaCompPropModal.length;
			if (lengthList > 0) {
				for (var i = 0; i < lengthList; i++) {
					if ($scope.listaCompPropModal[i].checkedComp) {
						if($scope.listaCompPropModal[i].cdPropiedad === 'QUERY' && objectPropScript != undefined){
							if(!objectPropScript['verificacionQuery'+paramActual]){
								growl.warning('El query se debe verificar, favor de validar' , {ttl: 3000});
								return;
							}else if($scope.propiedadTipoQuery.testQuery == undefined && objectPropScript['verificacionQuery'+paramActual]){
								growl.warning('Se debe ejecutar el query con el parámetro' , {ttl: 3000});
								return;
							}	
							var paramsPropScript = {'cdParamProvisional':objectPropScript['cdParamPrevia'+paramActual] , 
													'queryFull': objectPropScript[paramActual],
													'cdParametroOriginal':paramActual};
							$scope.listaCompPropModal[i].paramPropScriptVO = paramsPropScript;
						}
						listaNuevaAAsociar.push($scope.listaCompPropModal[i]);
					}
				}
			}
			//if(listaNuevaAAsociar.length > 0){
				for (var j = 0; j < $scope.params.length; j++) {
					if ($scope.params[j].cdParametro === paramActual) {
							$scope.params[j].propieades = listaNuevaAAsociar;
							$scope.valoresMarcadorAgregados = true;
						if($scope.valoresMarcadorAgregados && listaNuevaAAsociar.length > 0)
							growl.success(''+$scope.params[j].propieades.length+' propiedades agregadas correctamente' , {ttl: 3000});
						if(listaPropActTam > 0 && listaNuevaAAsociar.length <= 0 && $scope.valoresMarcadorAgregados)
							growl.warning('Se quitaron todas las propiedades' , {ttl: 3000});
						break;
					}
				}
			/*}else{
				growl.warning('Marque mínimo una propiedad' , {ttl: 3000});
			}*/
			//actualizar las propiedades
			
		}
	};
	

	$scope.guardaReporte = function(listaParams, currentForm, altaRepform) {
		
		/*actualizar un reporte sin parámetros*/
		if(listaParams.length <= 0 && currentForm == undefined && altaRepform != undefined){
			if (altaRepform.$invalid) {
				showAlert.requiredFields(altaRepform);
				growl.error('Formulario incompleto');
				return;
			}else{
				delete $scope.reportesTaqVOTMP['queryFull'];
				delete $scope.reportesTaqVO['queryFull'];
				var cambiosReporte = angular.equals($scope.reportesTaqVOTMP, $scope.reportesTaqVO);
				var cambiosParams = angular.equals($scope.paramsTMP, $scope.params);
				//console.log(JSON.stringify($scope.reportesTaqVOTMP));
				//console.log(JSON.stringify($scope.reportesTaqVO));
				if(cambiosReporte && cambiosParams){
					growl.warning('No se han detectado cambios favor de validar',{ttl:4000});
					return;
				}else {
					guardarActualizacionSeparados();
				}
			}
		}//cuando el form de reportes y params tienen campos obligaotirios
		else if (currentForm.$invalid && altaRepform.$invalid) {
			showAlert.requiredFields(currentForm);
			showAlert.requiredFields(altaRepform);
			growl.error('Formulario incompleto');
			return;
		//Cuando el alta de reportes tiene campos requeridos
		}else if(currentForm.$invalid && !altaRepform.$invalid){
			showAlert.requiredFields(currentForm);
			growl.error('Formulario incompleto');
			return;
		//Cuando el conf de params tiene camspos requeridos
		}else if(!currentForm.$invalid && altaRepform.$invalid){
			showAlert.requiredFields(altaRepform);
			growl.error('Formulario incompleto');
			return;
		}else{
			delete $scope.reportesTaqVOTMP['queryFull'];
			delete $scope.reportesTaqVO['queryFull'];
			var cambiosReporte = angular.equals($scope.reportesTaqVOTMP, $scope.reportesTaqVO);
			var cambiosParams = angular.equals($scope.paramsTMP, $scope.params);
			if(cambiosReporte && cambiosParams){
				growl.warning('No se han detectado cambios favor de validar',{ttl:4000});
				return;
			}else {
				guardarActualizacionSeparados();
			}
		}
	};
	
	function guardarActualizacionSeparados (){
		//growl.success('El reporte se actualizó correctamente correctamente' , {ttl: 5000});
		var reporteFormatoTMP = [];
		angular.copy($scope.reportesTaqVO.reporteFormato, reporteFormatoTMP);			
		$scope.reportesTaqVO.fhCreacion = $filter('date')($scope.reportesTaqVO.fhCreacion, 'dd/MM/yyyy');
		var fechaConvert = moment($scope.reportesTaqVO.fhCreacion,'DD/MM/YYYY');
		$scope.reportesTaqVO.fhCreacion = fechaConvert;
		angular.copy(obtenerValoresFormatosDescarga($scope.formatos,$scope.reportesTaqVO.reporteFormato),$scope.reportesTaqVO.reporteFormato);
		var strLayout ='';
		for(var i=0; i < $scope.columnasMetadata.length; i++){
			if(i > 0)
				strLayout += '|';
			strLayout += $scope.columnasMetadata[i];
		}
		$scope.reportesTaqVO.txLayout = strLayout;
		var objectReportePetition = {parametros: []};
		angular.copy($scope.reportesTaqVO, objectReportePetition);
		angular.copy($scope.params, objectReportePetition.parametros);
		//objectReportePetition.parametros = $scope.params;
		//console.log(JSON.stringify(objectReportePetition));
		admiReporteService.guadaEdicionReporte(objectReportePetition).success(function (data){
			if(data){
				growl.success('El reporte se actualizó correctamente correctamente' , {ttl: 5000});
				angular.copy(reporteFormatoTMP,$scope.reportesTaqVO.reporteFormato);
				$timeout(function() {
					var target = angular.element('#reporteFormato');
					target.selectpicker('refresh');
				}, 100);
			}
		}).error(function(data, status, headers, config) {
			growl.error('Ocurrió un error al actualizar la información ' + data.message, {ttl: 4000});
		});
	}
	
	$scope.cerrandoTestScript = function (){
		$scope.showModalScript = false;
	};
	
	$scope.testQuery = function(arrayParam22, paramValue22) {
		var arrayParamValue = [];
		//$scope.columnasMetadata = [];
		for (var a = 0; a < arrayParam22.length; a++) {
			arrayParamValue.push({
				"param" : arrayParam22[a].cdParametro,
				"value" : paramValue22[a]
			});
		}
		if ($scope.testQueryForm.$invalid) {
			showAlert.requiredFields($scope.testQueryForm);
			growl.error('Formulario incompleto');
			return;
		} else {
			admiReporteService.crearTestQuery(arrayParamValue, scriptFullTmp).success(function(data) {
				$scope.resultadoTestQuery = data;
				if ($scope.resultadoTestQuery.stResult) {
					$scope.paramConfig = true;
					$scope.showMensajeTest = $scope.paramConfig;
					/*COMPROBAR EL TIPO DE TÍTULO DE LAS CABECERAS*/
					angular.copy(data.columnsMetadataTest,$scope.columnasMetadata);
					var ban = $scope.reportesTaqVO.tipoTitulo.cdTipoTitulo == "TM" ? true: false;
					var listaTitulo = switchTipoTitulo($scope.columnasMetadata, ban);
					angular.copy(listaTitulo,$scope.columnasMetadata);
					/**Mandar a llamar funciones que consultan catalogos 
					 * dentro de la configuración de los params
					 * */
					if($scope.paramConfig){
						$scope.edicionQuery.banderaValidacion = false;
						$scope.edicionQuery.editable = true;
						angular.copy($scope.columnasMetadata, $scope.gridOptions.data);
						angular.copy(data.columnsMetadataTest, $scope.rowCollection);
					}					
				} else {
					if($scope.params.length <= 0)
						$scope.paramConfig = false;
					$scope.showMensajeTest = $scope.paramConfig;
				}
			}).error(function(data) {
				$scope.resultadoTestQuery = data;
			});
		}
	};
	
	
	
	$scope.comprobarTipoPaginacion = function (data){
		if(data == null || data.idTipoAgrupacion != 2){
			$scope.reportesTaqVO.txColumnaPaginacionHojas =undefined;
			$("#select2-txColumnaPaginacionHojas-container").text("Seleccione una opción");
		}
	};
	
	function obtenerValoresFormatosDescarga (listFormatos, aplicados){
		var listReturn = [];
		for(var i=0; i < aplicados.length; i++){
			for(var j=0; j < listFormatos.length; j++){
				if(aplicados[i]== listFormatos[j].idFormatoDescarga){
					listReturn.push(listFormatos[j]);
				}	
			}
		}
		return listReturn; 
	};
	function switchTipoTitulo(listaTitulosActual, indicador){
		var listTmpRowsLowerCase = [];
		var tmp = '';
		var intercambio = '';
		if(listaTitulosActual.length > 0 && indicador){
			for(var i=0; i < listaTitulosActual.length; i++){
				tmp = listaTitulosActual[i];
				intercambio = tmp.toUpperCase();
				listTmpRowsLowerCase.push(intercambio);
			}
			return listTmpRowsLowerCase;
		}else if(listaTitulosActual.length > 0 && !indicador){
			for(var i=0; i < listaTitulosActual.length; i++){
				tmp = listaTitulosActual[i];
				intercambio = tmp.toLowerCase();
				listTmpRowsLowerCase.push(intercambio);
			}
			return listTmpRowsLowerCase;
		}
	};
	
	function updateNuOrden(list, type){
		var tamanio = 0;
		switch(type){
		case 'PARAM':
			tamanio = list.length;
			$scope.nuOrden.maxNuOrdenParam = tamanio;
			break;
		case 'COLS':
			tamanio = list.length;
			$scope.nuOrden.maxNuOrdenColum = tamanio;
			break;
		}
	};
	
	$scope.$watch('params',function (newVal, oldVal){
		$scope.nuOrden.maxNuOrdenParam = newVal.length;
	}, true);
	
	$scope.shoAvisoTipoOperador = function (data){
		if(data != undefined){
			switch(data.cdOperador){
				case 'BETWEEN': 
					showAlert.aviso("Este operador necesita dos valores ej. valor1|valor2. En caso de fecha dd/mm/yyyy");
				break;
				case 'IN': 
					showAlert.aviso("Multiples valores para este operador ej. valor1|valor2|...n");
				break;
				case 'NOTIN': 
					showAlert.aviso("Multiples valores para este operador ej. valor1|valor2|...n");
				break;
			}
		}
	};
	
	$scope.mostrarAvisoValidacion = function (){
		if($scope.edicionQuery.banderaValidacion)
			growl.warning('El scipt debe ser validado nuevamente',{ttl:4000});
	};
	
	$scope.cambiaTipoTitulo = function (data){
		if(data == null && $scope.columnasMetadata.length > 0){
			growl.warning('Seleccione al menos un formato de columna',{ttl:4000});
			return;
		}
		if($scope.columnasMetadata.length > 0){
			var ban = data.cdTipoTitulo == "TM" ? true: false;
			var listaColumnas = switchTipoTitulo($scope.columnasMetadata, ban);
			angular.copy(listaColumnas,$scope.columnasMetadata);
		}
	};

	function getAllCatalogos(){
		admiReporteService.getAllCatalogos().success(function(data) {
			angular.copy(data,$scope.objectAllCatalogos);
			if(data != null || data != undefined){
				angular.copy(data.agrupacionHojasVO,$scope.tipoAgrupacion);
				angular.copy(data.tipoTituloVO,$scope.tipoTitulo);
				angular.copy(data.tipoReporteVO,$scope.tiposReportes);
				angular.copy(data.formatoDescargaVO, $scope.formatos);
				angular.copy(data.tipoParametroVO, $scope.parametros);
				angular.copy(data.componentesVO,$scope.componentes);
				angular.copy(data.tipoParamCompVO, $scope.relacionTipoParamsComps);
				angular.copy(data.propiedadesVO, $scope.propiedades);
				angular.copy(data.propiedadesCompVO, $scope.relacionCompPropiedad);
				angular.copy(data.caracteresVO, $scope.catCaracteres);
				angular.copy(data.tablasVO, $scope.catalogoTablas);
				angular.copy(data.columnasVO, $scope.catalogoColumnas);
				angular.copy(data.tipoOrdenamientoVO, $scope.catTipoOrdenamiento);
				angular.copy(data.tipoOperadorVO, $scope.catTipoOperadores);
				
				$("#select2-tipoAgrupacion-container").text($scope.reportesTaqVO.tipoAgrupacion.cdTipoAgrupacion);
				$("#select2-tipoTitulo-container").text($scope.reportesTaqVO.tipoTitulo.nbTipoTitulo);
				$("#select2-tipoReporte-container").text($scope.reportesTaqVO.tipoReporte.nbTipoReporte);
				
				var obj ={},listaObj =[], idActuales = [],valoresActualesFormatoDes = $scope.reportesTaqVO.reporteFormato;
				angular.forEach($scope.formatos, function(value, key) {
					obj = {id: value.idFormatoDescarga, desc: value.nbFormato};
					listaObj.push(obj);
				});
				angular.copy(listaObj,$scope.formatosTMP);
				for(var i=0; i < valoresActualesFormatoDes.length; i++){
					idActuales.push(valoresActualesFormatoDes[i].idFormatoDescarga);
				}
				angular.copy(idActuales, $scope.reportesTaqVO.reporteFormato);
				angular.copy(idActuales, $scope.reportesTaqVOTMP.reporteFormato);
				$timeout(function() {
					var target = angular.element('#reporteFormato');
					target.selectpicker('refresh');
				}, 100);
				
				configurarParametrosEdit($scope.params);
				
				for(var i=0; i < $scope.params.length; i++){
					$scope.filtroTipoComponente($scope.params[i].tipoParametro,i, $scope.params[i]);
					$('#select2-componente'+i+'-container').text($scope.params[i].componente.nbComponente);
					$scope.filtroCompPropiedad($scope.params[i].componente, i, $scope.params[i]);
				}
				
				for(var i=0; i < $scope.params.length; i++){
					$scope.filtroCompPropiedad($scope.params[i].componente, i, $scope.params[i]);
				}
				
			}
				
		}).error(function(data) {
			$scope.objectAllCatalogos = {};
		});
	};
	/**Llamado de todos los catalogos*/
	getAllCatalogos();
});