angular.module(appTeclo).controller("asignacionIncidenciaController",function($location, $window, $rootScope, $scope,$timeout,$filter,supervisionService,growl, showAlert, filtroString)
{

	$scope.preseleccion ={valor:1};
	$scope.automaticSearch=0;
	$scope.hasPermition=true;

	$scope.tipoBusq = {
		lista:[],
		valor:1
	}
	$scope.catPeriodo = {
		lista:[],
		valor:undefined
	}	
	
	$scope.catCSV = {
		lista:[],
		valor:undefined
	}
	
	$scope.catPT = {
		lista:[],
		valor:undefined
	}
	
	$scope.catBusqPT = {
		lista:[],
		valor:undefined
	}
	
	$scope.catBusqCSV = {
		lista:[],
		valor:undefined
	};

	$scope.catEtiquetas = {
		lista:[],
		valor:undefined
	}
	
	$scope.incidenciaSeleccionada = {};
	
	$scope.validadores = {}
	
	$scope.catMotivo = {};
	
	$scope.busquedaVO = {
		busca:""
	}
	$scope.showCSVLabel=false;
	
	$scope.gridOptions = {
            data: [],
            urlSync: false,
            pagination: {
                itemsPerPage: '10'
            },enableHorizontalScrollbar : true,
            enableVerticalScrollbar  : true
        };
	
	
	$scope.cabeceraDetalleCSV = [
		{id:"nuCarril", name:"CARRIL"},
		{id:"placaDelantera", name:"PLACA DELANTERA"},
		{id:"entidadDelantera", name:"ENTIDAD DELANTERA"},
		{id:"placaTrasera", name:"PLACA TRASERA"},
		{id:"entidadTrasera", name:"ENTIDAD TRASERA"},
		{id:"perfil", name:"PERFIL"}
	];
	
	$scope.copiaDetalleRevalidacion = {
		lista:[]
	}
	
	$scope.detalleRevalidacion = {}
	
	$scope.detalleARevalidar = [];
	
	$scope.showModalRevalidacionRapida = false;
	
	$scope.listaIncidencias=[];

	$scope.init= function(){
		if (filtroString != undefined && filtroString != "" && filtroString.startsWith("{")) {
			$scope.filtro = JSON.parse(filtroString);
		}
		//Consultamos ctálogos
		supervisionService.catalogoTipoBusqueda().success(function(data){
			//Seteamos combos de catalogos
			$scope.catPeriodo.lista = data.catPeriodo;
			$scope.catCSV.lista = data.catCSV;
			$scope.catPT.lista = data.catPT;
			$scope.catBusqPT.lista = [...$scope.catPT.lista];
			$scope.catBusqCSV.lista = [...$scope.catCSV.lista];
			$('#select2-comboPeriodoIncidencia-container').text("Seleccione");
			//Si hay filtro guardado seteamos los varlores preseleccionados en los catálogos
			if ($scope.filtro != undefined && filtroString != "") {
				refrescarFormularioDeBusqueda();
				//console.log($scope.filtro);
			}
		}).error(function(err){
			growl.error(err.message, {title:"Error:", ttl: 4000});  
		});
	}
	$scope.etCatEtiquetas = function(){
		supervisionService.getCatEtiquetasReval().success(function(data){
			////console.log('Se imprien las etiquetas');
			//console.log(data);
			$scope.catEtiquetas.lista=data;
		}).error(function(err){
			growl.error(err.message, {title:"Error:", ttl: 4000});  
		});
	}
	$scope.etCatEtiquetas();
	

	$scope.invocarFiltroEtiquetas = function(){
		////console.log($scope.catEtiquetas.valor);
		if($scope.catEtiquetas.valor!=undefined){
			////console.log('Tiene vaalor');
		}
	}
	//Buscamos si hay información guadada en filtro y de ser asi llennamos los combos con la 
	//selección previa
	refrescarFormularioDeBusqueda = () => {
			if($scope.filtro.tipoBusqvalor==4){
				
				$scope.preseleccion.valor = $scope.filtro.tipoBusqvalor;
				$scope.etCatEtiquetas();
				for (var i=0;i<$scope.catEtiquetas.lista.length;i++){
					if($scope.filtro.etiquetaSelected.idEtiqReval==$scope.catEtiquetas.lista[i].idEtiqReval){
						if($scope.catEtiquetas.lista[i].stActivo==true){
							$scope.catEtiquetas.valor =$scope.filtro.etiquetaSelected;
							$("#select2-comboEtiqueta-container").text($scope.catEtiquetas.valor.nbEtiqueta);
							//console.log("etiqueta activa");
						}
					}
				}
				
				
				$scope.showEtiqueta()
				if($scope.filtro.tipoBusqvalor===4){
					$scope.showEtiquetaLabel=true;
				}else{$scope.showEtiquetaLabel=false;}

			}else{
				$scope.catPeriodo.valor = getEntregasSeleccionadas();
				$scope.catBusqPT.valor = getPtsSeleccionados();
				$scope.catBusqCSV.valor = getCSVSeleccionados();

				$scope.tipoBusq.valor = $scope.filtro.tipoBusqvalor;
				$scope.showLabelCSV()
				if($scope.filtro.tipoBusqvalor===1){
					$scope.showCSVLabel=false;
				}else{$scope.showCSVLabel=true;}
				$scope.automaticSearch=1;
				$timeout($scope.consultarIncidencias(), 7000);
			}
	};

	getCSVSeleccionados = () => {	
		var tmpCSVSeleccionados = [];	
		if($scope.filtro.listaCsvs.length == 0){
			return tmpCSVSeleccionados;
		}

		for (let id of $scope.filtro.listaCsvs){
			for(let idArchivoCsv of $scope.catBusqCSV.lista){
					if(id==idArchivoCsv.idArchivoCsv){
						tmpCSVSeleccionados = tmpCSVSeleccionados.concat(idArchivoCsv);
					}
			}
		}
		return tmpCSVSeleccionados;
	}

	getEntregasSeleccionadas = () => {	
		var tmpEntregasSeleccionadas = [];	
		if($scope.filtro.listaEntregas.length == 0){
			return tmpEntregasSeleccionadas;
		}

		for(var i =0;i<$scope.filtro.listaEntregas.length;i++){
			var tmp = $filter('filter')($scope.catPeriodo.lista, {idCat:$scope.filtro.listaEntregas[i]});					
			tmpEntregasSeleccionadas = tmpEntregasSeleccionadas.concat(tmp);
		}
		return tmpEntregasSeleccionadas;
	}

	getPtsSeleccionados = () => {	
		var tmpPeriodosSeleccionados = [];	
		if($scope.filtro.listaLotes.length == 0){
			return tmpPeriodosSeleccionados;
		}

		for (let id of $scope.filtro.listaLotes){
			for(let idpt of $scope.catBusqPT.lista){
					if(id==idpt.idPt){
						tmpPeriodosSeleccionados = tmpPeriodosSeleccionados.concat(idpt);
						////console.log(tmpPeriodosSeleccionados);
					}
			}
		}
		return tmpPeriodosSeleccionados;
	}

	$scope.responseConsultaIncidencia = function(){
		supervisionService.consultaIncidencias($scope.filtro).success(function(data){
			if(data.length >0){
				$scope.listaIncidencias = data;
				////console.log(data);
			}else{
				showAlert.aviso("No hay registros");
			}
		}).error(function(err){
			growl.error(err.message, {title:"Error:", ttl: 4000});  
		})
	}

	$scope.updateFiltro = function(tipo){
		if(tipo==='etiqueta'){
			$scope.filtro = {};
			$scope.filtro.tipoBusqvalor = $scope.preseleccion.valor;
			$scope.filtro.tipoBusqueda = $scope.preseleccion.valor;
			$scope.filtro.etiquetaSelected = $scope.catEtiquetas.valor;
		}else{
			$scope.filtro = {};
			$scope.filtro.listaEntregas = $scope.catPeriodo.valor != undefined ? $scope.catPeriodo.valor.map( el => el.idCat) : [];
			$scope.filtro.listaLotes = $scope.catBusqPT.valor != undefined ? $scope.catBusqPT.valor.map(function(el){return el.idPt}) : [];
			$scope.filtro.listaCsvs =  $scope.catBusqCSV.valor != undefined ? ($scope.tipoBusq.valor == 2 ? $scope.catBusqCSV.valor.map( el => el.idArchivoCsv) : []) : [];
			$scope.filtro.tipoBusqvalor = $scope.tipoBusq.valor;
			$scope.filtro.tipoBusqueda = $scope.tipoBusq.valor;
		}
		//////console.log($scope.filtro);	
	}
	 
	//Sección para la consulta de la búsqueda de la información
	$scope.consultarIncidencias=function(){
		if($scope.preseleccion.valor == 4){
			$scope.updateFiltro('etiqueta');
			$scope.verRevalidacionPage();
		}else{
			if(!$scope.formConsultaIncidencia.$invalid){
				$scope.updateFiltro('PTCSV');
				$scope.responseConsultaIncidencia();
			}else{
				if($scope.automaticSearch==1){
					$scope.updateFiltro('PTCSV');
					$scope.responseConsultaIncidencia();
				}else{
					showAlert.requiredFields($scope.formConsultaIncidencia);
					showAlert.aviso("Fomulario incompleto");
				}
				$scope.automaticSearch=0;
			}
		}
		// if (filtroString != undefined && filtroString != "" && filtroString.startsWith("{")) {
		// 	if($scope.filtro.preseleccion.valor == 4){
		// 		$scope.updateFiltro('etiqueta');
		// 		$scope.verRevalidacionPage();
		// 	}else{
		// 		$scope.updateFiltro('PTCSV');
		// 		$scope.responseConsultaIncidencia();
		// 	}
		// }else{
		// 	if($scope.preseleccion.valor===4){
		// 		$scope.updateFiltro('etiqueta');
		// 		$scope.verRevalidacionPage();
		// 	}else{
		// 		if(!$scope.formConsultaIncidencia.$invalid){
		// 			$scope.updateFiltro('PTCSV');
		// 			$scope.responseConsultaIncidencia();
		// 		}else{
		// 			showAlert.requiredFields($scope.formConsultaIncidencia);
		// 			showAlert.aviso("Fomulario incompleto");
		// 		}
		// 	}
		// }	
	}
	
	$scope.resetTablaIncidencias = function(){
		$scope.listaIncidencias = [];
		$scope.catPeriodo.valor = undefined;
		$scope.catBusqPT.lista = [...$scope.catPT.lista];
		$scope.catBusqPT.valor = undefined; 
		$scope.catBusqCSV.lista = [...$scope.catCSV.lista];
		$scope.catBusqCSV.valor = undefined;
	}
	$scope.showLabelCSV = function(){
		$scope.showCSVLabel = $scope.tipoBusq.valor == 1 ? true : false;
		$scope.showCSVLabel = $scope.tipoBusq.valor == 2 ? false : true;
	}
	$scope.showEtiqueta = function(){
		$scope.showEtiquetaLabel = $scope.preseleccion.valor == 3 ? true : false;
		$scope.showEtiquetaLabel = $scope.preseleccion.valor == 4 ? false : true;
	}
	
	$scope.filtrarEntregas=function(){
		$scope.catBusqPT.lista = [];
		if($scope.catPeriodo.valor != undefined){
			for(var i =0;i<$scope.catPeriodo.valor.length;i++){
				var tmp = $filter('filter')($scope.catPT.lista, {nbEntrega:$scope.catPeriodo.valor[i].nameCat});
				$scope.catBusqPT.lista = $scope.catBusqPT.lista.concat(tmp);
			}
		}
	}
	
	$scope.filtrarPuntosTacticos=function(){
		$scope.catBusqCSV.lista = [];
		if($scope.catBusqPT.valor != undefined){
			for(var i =0;i<$scope.catBusqPT.valor.length;i++){
				var tmp = $filter('filter')($scope.catCSV.lista, {nbPt:$scope.catBusqPT.valor[i].nbNombre, idPt: $scope.catBusqPT.valor[i].idPt});
				$scope.catBusqCSV.lista = $scope.catBusqCSV.lista.concat(tmp);
			}
		}
	}

	$scope.verRevalidacionPage = function(id) {

		supervisionService.setFilter(JSON.stringify($scope.filtro));
		
		$scope.detalleRevalidacion.lista = [];
		$scope.detalleRevalidacion.validador = undefined;
		$scope.catMotivo.lista = [];
		$scope.catMotivo.valor = undefined;
		$scope.detalleARevalidar = [];
		$scope.rowCollection = [];

		if($scope.filtro.tipoBusqvalor == 1){
			$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idPtLote == id});
			//////console.log($scope.incidenciaSeleccionada);
			
			$location.path('/asignaIncidencias/revalidacion/'
				+ id + '/'+$scope.tipoBusq.valor+'/detalles');
			
		}

		if($scope.filtro.tipoBusqvalor == 2){
			$scope.copiaDetalleRevalidacion.lista = [];
			$scope.gridOptions.data = [];
			$scope.detalleRevalidacion.lista = [];
			
			$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idArchivoCsv == id});
			//////console.log($scope.incidenciaSeleccionada);
			$location.path('/asignaIncidencias/revalidacion/'
				+ id + '/'+$scope.tipoBusq.valor+'/detalles');
		}

		if($scope.filtro.tipoBusqvalor == 4){
			$scope.copiaDetalleRevalidacion.lista = [];
			$scope.gridOptions.data = [];
			$scope.detalleRevalidacion.lista = [];

			$location.path('/asignaIncidencias/revalidacion/'
				+ $scope.filtro.etiquetaSelected.idEtiqReval + '/'+$scope.filtro.tipoBusqvalor+'/detalles');
		}

	};
	
	// $scope.mostrarModal=function(id){
	// 	$scope.detalleRevalidacion.lista = [];
	// 	$scope.detalleRevalidacion.validador = undefined;
	// 	$scope.catMotivo.lista = [];
	// 	$scope.catMotivo.valor = undefined;
	// 	$scope.detalleARevalidar = [];
	// 	$scope.rowCollection = [];
	// 	if($scope.tipoBusq.valor == 1){
	// 		$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idPtLote == id});
	// 		supervisionService.consultaDetalleIncidenciaPT(id).success(function(data){
	// 			$scope.detalleRevalidacion.lista = data.detallePT;
	// 			$scope.catMotivo.lista = data.catMotivo;
	// 			$scope.validadores.listaOriginal = data.catValidadores;
	// 			$scope.validadores.lista = angular.copy($scope.validadores.listaOriginal);
	// 		}).error(function(err){
	// 			growl.error(err.message, {title:"Error:", ttl: 4000});  
	// 		})
	// 	}
		
	// 	if($scope.tipoBusq.valor == 2){
	// 		$scope.copiaDetalleRevalidacion.lista = [];
	// 		$scope.gridOptions.data = [];
	// 		$scope.detalleRevalidacion.lista = [];
	// 		$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idArchivoCsv == id});
	// 		supervisionService.consultaDetalleIncidenciaCSV(id).success(function(data){
	// 			$scope.detalleRevalidacion.lista = data.detalleCSV;
	// 			$scope.copiaDetalleRevalidacion.lista = data.detalleCSV;
	// 			$scope.gridOptions.data = $scope.copiaDetalleRevalidacion.lista;
	// 			$scope.rowCollection = $scope.gridOptions.data;
	// 			//$scope.copiaDetalleRevalidacion.lista = supervisionService.convertListToMap($scope.copiaDetalleRevalidacion.lista, $scope.cabeceraDetalleCSV);
	// 			$scope.catMotivo.lista = data.catMotivo;
	// 			$scope.validadores.listaOriginal = data.catValidadores;
	// 		}).error(function(err){
	// 			growl.error(err.message, {title:"Error:", ttl: 4000});  
	// 		})
	// 	}
	// 	$scope.showModalRevalidacion = true;
	// }
	
	$scope.mostrarModal2 = function(id){
		if($scope.tipoBusq.valor == 1){
			$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idPtLote == id});
			supervisionService.consultaDetalleIncidenciaPT(id).success(function(data){
				$scope.detalleRevalidacion.lista = data.detallePT;
				$scope.catMotivo.lista = data.catMotivo;
				$scope.validadores.listaOriginal = data.catValidadores;
				$scope.validadores.lista = angular.copy($scope.validadores.listaOriginal);
				$scope.showModalRevalidacionRapida = true;
			}).error(function(err){
				growl.error(err.message, {title:"Error:", ttl: 4000});  
			});
		}else if($scope.tipoBusq.valor == 2){
			$scope.incidenciaSeleccionada = $scope.listaIncidencias.find(function(el){return el.idArchivoCsv == id});
			supervisionService.consultaDetalleIncidenciaCSV(id).success(function(data){
				$scope.detalleRevalidacion.lista = data.detalleCSV;
				$scope.catMotivo.lista = data.catMotivo;
				$scope.validadores.listaOriginal = data.catValidadores;
				$scope.showModalRevalidacionRapida = true;
			}).error(function(err){
				growl.error(err.message, {title:"Error:", ttl: 4000});  
			})
		}
	}
	
	// $scope.reajustaListaValidador= function(val, e){
	// 	if(val != undefined){
	// 		var isAvailable = false;
	// 		$scope.validadores.lista.find(function(el){
	// 			if(val.idValidador == el.idValidador){
	// 				isAvailable = true;
	// 			}
	// 		});
			
	// 		if(isAvailable){
	// 			$scope.validadores.lista = $scope.validadores.lista.filter(function(el){ return val.idValidador != el.idValidador});
	// 			e.prevValidador = val;
	// 		}else{
	// 			e.validador = undefined;
	// 			growl.error('Ya tiene una asignación este validador');
	// 		}
	// 	}else{
	// 		$scope.validadores.lista.push(e.prevValidador);
	// 	}
	// }

	$scope.getAccess = function(){
		supervisionService.getAccess().success(function(data){
			$scope.hasPermition=data;
		}).error(function(){
			alert("algo ha fallado al obtener permisos");
		});
	}
	// $scope.getAccess();

	$scope.revalidar1= function(revalidaTodo){
		$scope.object="hey";
		showAlert.confirmacion("¿Deseas continuar con la asignación a revalidar?", $scope.revalidar(), revalidaTodo, noaction);
	}
	function noaction(){
	}

	$scope.revalidar=function(revalidaTodo){
		var isAllAsigned = true;

		if(!revalidaTodo){
			if($scope.tipoBusq.valor == 1){
				$scope.detalleRevalidacion.lista.map(function(el){
					if(el.validador == undefined)
						isAllAsigned = false;
				});
				
				if(!isAllAsigned){
					growl.error("Hay archivos que no se le han asignado validadores")
				}
				
				if(isAllAsigned && $scope.catMotivo.valor == undefined){
					isAllAsigned = false;
					growl.error("No se ha especificado el motivo")
				}
				
			} else if($scope.tipoBusq.valor == 2){
				if($scope.detalleRevalidacion.validador != undefined){
					if($scope.catMotivo.valor == undefined){
						isAllAsigned = false;
						growl.error("No se ha especificado el motivo")
					}
				}else{
					isAllAsigned = false;
					growl.error("No se le ha asignado validador")
				}
			}
		}else{
			if($scope.tipoBusq.valor == 1){
				
				//Asignamos todos al mismo validador
				$scope.detalleRevalidacion.lista = $scope.detalleRevalidacion.lista.map(function(el){
					el.validador = $scope.detalleRevalidacion.validador;
					return el;
				});
				
				if(isAllAsigned && $scope.catMotivo.valor == undefined){
					isAllAsigned = false;
					growl.error("No se ha especificado el motivo")
				}
			} else if($scope.tipoBusq.valor == 2){
				if($scope.detalleRevalidacion.validador != undefined){
					if($scope.catMotivo.valor == undefined){
						isAllAsigned = false;
						growl.error("No se ha especificado el motivo")
					}
				}else{
					isAllAsigned = false;
					growl.error("No se le ha asignado validador")
				}
			}
		}
		
		if(isAllAsigned){
			$scope.reqVO = angular.copy($scope.detalleRevalidacion);
			$scope.reqVO.motivo = $scope.catMotivo.valor;
			if(!revalidaTodo){
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? angular.copy($scope.detalleARevalidar) : $scope.reqVO.lista;
			}else{
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? $scope.reqVO.lista.map(function(el){return el.idRegistroCsv}) : $scope.reqVO.lista;
			}
			$scope.reqVO.idArchivoCsv = $scope.incidenciaSeleccionada.idArchivoCsv != undefined ? $scope.incidenciaSeleccionada.idArchivoCsv : undefined;
			////console.log($scope.reqVO);
			
			if($scope.reqVO.lista.length != 0){
				supervisionService.revalidar($scope.tipoBusq.valor, $scope.reqVO).success(function(data){
					$scope.catMotivo.valor = undefined;
					$scope.detalleRevalidacion.validador = undefined;
					$scope.detalleARevalidar = [];
					$("#select2-comboMotivoRevalidaRapida-container").text("Seleccione");
					$("#select2-comboRevalidaValidadorRapida-container").text("Seleccione");
					if(revalidaTodo){
						$scope.showModalRevalidacionRapida = false;
					}else{
						$scope.showModalRevalidacion = false;
					}
					
					if($scope.tipoBusq.valor == 1){
						$scope.listaIncidencias = $scope.listaIncidencias.map(function(el){
							if(el.idPtLote == $scope.incidenciaSeleccionada.idPtLote){
								el.stRevalidacion = true;
								el.nuValidados = 0;
								el.nuFaltantes = el.nuCsv;
							}
							return el;
						}) 
					}else if($scope.tipoBusq.valor == 2){
						$scope.listaIncidencias = $scope.listaIncidencias.map(function(el){
							if(el.idArchivoCsv == $scope.incidenciaSeleccionada.idArchivoCsv){
								el.stRevalidacion = false;
								el.totalValidado = 0;
								el.totalIncidencia =0;
							}
							return el;
						}) 
					}
					$scope.incidenciaSeleccionada = {};				
				}).error(function(err){
					showAlert.error(err);
				});
			}else{
				growl.error("Debe de seleccionar al menos un registro para revalidar")
			}
		}
	}
	
	$scope.confirmarPT = function(id){
		supervisionService.confirmaPuntoTactico(id).success(function(data){
			if(data){
				$scope.listaIncidencias = $scope.listaIncidencias.map(function(el){
					if(el.idPtLote == id)
						el.stValidacion = true;
					return el;
				});
				showAlert.aviso("El punto tactico ha sido confirmado como validado");
			}else{
				showAlert.error("No se pudo confirmar el punto tactico");
			}
			
		}).error(function(err){
			growl.error(err.message, {title:"Error:", ttl: 4000});  
		});
	}
	
	$scope.filtrar= function(check, scope){
		if(check){
			$scope.gridOptions.data = $scope.gridOptions.data.filter(function(el){
				return el.inconsistencia.length >0;
			});
		}else{
			$scope.gridOptions.data = angular.copy($scope.detalleRevalidacion.lista);
		}
		scope.allCheck = false;
		$scope.checkAllDetalleArchivos(false);
		//$scope.refreshData();
	}
	
	$scope.checkDetalleArchivos = function(val, check){
		if(check){
			$scope.detalleARevalidar.push(val);
		}else{
			$scope.detalleARevalidar = $scope.detalleARevalidar.filter(function(el){ return el != val});
		}
	}
	
	$scope.refreshData = function() {
		$scope.gridOptions.data = $filter('filter')($scope.rowCollection, null);
		$scope.rowCollection = $scope.gridOptions.data;
	};
	
	$scope.checkAllDetalleArchivos = function(check){
		if(check){
			$scope.gridOptions.data= $scope.gridOptions.data.map(function(el){
				el.check = true;
				return el;
			})
			$scope.detalleARevalidar = $scope.gridOptions.data.map(function(el){
				return el.idRegistroCsv;
			});
		}else{
			$scope.gridOptions.data= $scope.gridOptions.data.map(function(el){
				el.check = false;
				return el;
			})
			$scope.detalleARevalidar = [];
		}
	}
	
	$scope.detalleRegistrosIncidencias= function(listIncidencias){
		var contenidoInicial ="<div class='row'><div class='col-md-12'>";
		var contenido = "";
		var contenidoFinal = "</div></div>";
		
		for(var i = 0;i<listIncidencias.length;i++){
			contenido = contenido + "<div class='row'><div class='col-md-12'>"+listIncidencias[i].txIncidencia+"</div></div>";
		}
		
		contenido = contenidoInicial + contenido + contenidoFinal;

		return contenido;
	}
	
	$scope.init();
});