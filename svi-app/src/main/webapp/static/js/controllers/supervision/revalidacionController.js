angular.module(appTeclo).controller("revalidacionController",function($window, $rootScope, $location, growl, $routeParams, $scope,$timeout,$filter,supervisionService,growl, showAlert)
{
	$scope.tipoBusq = {valor:null}; 
	$scope.detalleRevalidacion={lista:null};
	$scope.copiaDetalleRevalidacion={lista:null};
	$scope.catMotivo={lista:null};
	$scope.validadores={listaOriginal:null};
	$scope.incidenciaSeleccionada = {idArchivoCsv:undefined, idPtLote:undefined}
	$scope.detalleARevalidar = [];
	$scope.motivoExpedienteSelected=null;
	$scope.hasPermition=true;

	$scope.filtroEtiquetaListData=[];

	$scope.catEtiquetas = {
		lista:[],
		valor:undefined
	}

	$scope.listPreparacionValidar = {
		lista:[],
		validador:null,
		idArchivoCsv:null,
		idEtiqueta:null,
		motivo:null
	};
	

	$scope.gridOptions = {
		data: [],
		urlSync: false,
		pagination: {
			itemsPerPage: '10'
		},enableHorizontalScrollbar : true,
		enableVerticalScrollbar  : true
	};



	$scope.catMarca = {
		lista:[],
		valor:undefined
	}
	
	$scope.catSubMarca = {
		lista:[],
		valor:undefined
	}
	
	$scope.catPerfil = {
		lista:[],
		valor:undefined
	}
	
	$scope.catPerfilDistinct={
		lista: [],
		valor:undefined
	}

	$scope.catBusqMarca = {
		lista:[],
		valor:undefined
	}	

$scope.catBusqSubMarca = {
	lista:[],
	valor:undefined
}
	
$scope.catBusqPerfil = {
	lista:[],
	valor:undefined
}

	var id = $routeParams.idExpediente;
	$scope.tipoBusq.valor = $routeParams.tipoBusqueda;

	function isPreexistente(array, attr, value){		
	    for(var i = 0; i < array.length; i += 1) { 
	        if(array[i][attr] === value) {
	            return i;
	        }
	    }
	    return -1;		
	}
	
	$scope.filtrarMarcas=function(){
		if($scope.catBusqSubMarca.valor == undefined){
			$scope.catBusqSubMarca.lista = [];
			if($scope.catBusqMarca.valor != undefined){
				for(var i =0;i<$scope.catBusqMarca.valor.length;i++){
					var tmp = $filter('filter')($scope.catSubMarca.lista, {idPtMarca: $scope.catBusqMarca.valor[i].idPtMarca, txMarca: $scope.catBusqMarca.valor[i].txMarca});
					$scope.catBusqSubMarca.lista = $scope.catBusqSubMarca.lista.concat(tmp);
				}
				$scope.filtrarSubMarcas();
			}
		}
	}

	$scope.getCatalogoEtiquetas = function(){
		supervisionService.getCatEtiquetasReval().success(function(data){
			//console.log('Se imprien las etiquetas');
			//console.log(data);
			$scope.catEtiquetas.lista=data;
			$scope.getDetallesFiltro();
		}).error(function(){
			alert("algo ha fallado");
		});
	}

	$scope.getAccess = function(){
		supervisionService.getAccess().success(function(data){
			$scope.hasPermition=data;
		}).error(function(){
			alert("algo ha fallado al obtener permisos");
		});
	}
	// $scope.getAccess();
	
	$scope.filtrarSubMarcas=function(){
		if($scope.catBusqPerfil.valor == undefined){
			//filtrar opciones en perfiles
			$scope.catBusqPerfil.lista = [];
			if($scope.catBusqSubMarca.valor != undefined){
				for(var i =0;i<$scope.catBusqSubMarca.valor.length;i++){
					var tmp = $filter('filter')($scope.catPerfil.lista, 
							{ idPtMarca: $scope.catBusqSubMarca.valor[i].idPtMarca, 
							  txMarca: $scope.catBusqSubMarca.valor[i].txMarca,
						      idPtSubMarca: $scope.catBusqSubMarca.valor[i].idPtSubMarca,
						      txSubMarca: $scope.catBusqSubMarca.valor[i].txSubMarca
						    });
					if(isPreexistente($scope.catBusqPerfil.lista, "idPtPerfil", tmp.idPtPerfil)<0){
						$scope.catBusqPerfil.lista = $scope.catBusqPerfil.lista.concat(tmp);
					}
				}
			}
		}else{
			// filtrar opciones en marcas
			$scope.catBusqMarca.lista = [];
			if($scope.catBusqSubMarca.valor != undefined){
				for(var i =0;i<$scope.catBusqSubMarca.valor.length;i++){
					for(var j =0;j<$scope.catMarca.lista.length;j++){
						if(parseInt($scope.catMarca.lista[j].idPtMarca) == parseInt(($scope.catBusqSubMarca.valor[i]).idPtMarca)){
							$scope.catBusqMarca.lista.push($scope.catMarca.lista[j]);
						}
					}	
					
				}
			}			
		}
	}
	
	$scope.filtrarSubMarcasPorPerfil = function(){
		// filtrar ascendentemente si no hay selección en submarca
		if($scope.catBusqSubMarca.valor == undefined){
			$scope.catBusqSubMarca.lista = [];
			if($scope.catMarca.valor != undefined){
				for(var i =0;i<$scope.catBusqPerfil.valor.length;i++){
					var tmp = $filter('filter')($scope.catSubMarca.lista, {idPtPerfil: $scope.catBusqPerfil.valor[i].idPtPerfil, txPerfil: $scope.catBusqPerfil.valor[i].txPerfil});
					$scope.catBusqSubMarca.lista = $scope.catBusqSubMarca.lista.concat(tmp);
				}
			}
		}		
	}

	$scope.filtro=null;
	function getCargaInicial(){		
		supervisionService.getCatalog($scope.filtro).success(function(data){
			$scope.catMarca.lista = data.catMarca;
			$scope.catSubMarca.lista = data.catSubMarca;
			$scope.catPerfil.lista = data.catPerfil;
			$scope.catPerfilDistinct.lista = data.catPerfilDistinct;

			refrescarFormularioDeBusqueda();
						
		}).error(function(){
			alert("Algo ha fallado");
		});
	}

	function refrescarFormularioDeBusqueda(){	
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];		       
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		
		if($scope.filtro != null && $scope.filtro != undefined){
		  $scope.catBusqMarca.valor = getMarcasSeleccionadas();
		  $scope.catBusqSubMarca.valor = getSubMarcasSeleccionadas();
		  $scope.catBusqPerfil.valor = getPerfilesSeleccionados();
		  $scope.filtrarMarcas();
		  $scope.filtrarSubMarcas();
		}	
		
	}

	$scope.init = function(){
		if($scope.tipoBusq.valor == 1){
			supervisionService.consultaDetalleIncidenciaPT(id).success(function(data){
				
				$scope.detalleRevalidacion.lista = data.detallePT;
				$scope.catMotivo.lista = data.catMotivo;
				$scope.validadores.listaOriginal = data.catValidadores;
				$scope.validadores.lista = angular.copy($scope.validadores.listaOriginal);
				//////console.log($scope.incidenciaSeleccionada);
				//////console.log($scope.detalleRevalidacion);
			}).error(function(err){
				
			})
		}else if($scope.tipoBusq.valor == 2){
			getCargaInicial();
			supervisionService.consultaDetalleIncidenciaCSV(id).success(function(data){
				$scope.detalleRevalidacion.lista = data.detalleCSV;
				$scope.copiaDetalleRevalidacion.lista = data.detalleCSV;
				$scope.gridOptions.data = $scope.copiaDetalleRevalidacion.lista;

				$timeout($scope.fillChekedDetalleArchivosPreSelected(), 500);
				

				$scope.rowCollection = $scope.gridOptions.data;
				//$scope.copiaDetalleRevalidacion.lista = supervisionService.convertListToMap($scope.copiaDetalleRevalidacion.lista, $scope.cabeceraDetalleCSV);
				//////console.log(data.catMotivo);
				//////console.log(data.catValidadores);
				$scope.catMotivo.lista = data.catMotivo;
				$scope.validadores.listaOriginal = data.catValidadores;
				//////console.log($scope.detalleRevalidacion);
				//////console.log($scope.gridOptions.data);
				// if($scope.listPreparacionValidar.validador!=undefined){
				// 	//////console.log('se reasigna validador');
				// 	$scope.detalleRevalidacion.validador=$scope.listPreparacionValidar.validador;
				// }
				// if($scope.listPreparacionValidar.motivo!=null){
				// 	//////console.log('se reasigna motivo');
				// 	$scope.catMotivo.valor = $scope.listPreparacionValidar.motivo;
				// }

				if($scope.catMotivo.lista!=null){
					if($scope.listPreparacionValidar.validador!=undefined){
						var res = $scope.findInJSON($scope.validadores.listaOriginal,$scope.listPreparacionValidar.validador.idValidador, 'motivoValidador');
						$scope.detalleRevalidacion.validador = $scope.validadores.listaOriginal[res];
					}
					if($scope.listPreparacionValidar.motivo!=null){
						var res = $scope.findInJSON($scope.catMotivo.lista,$scope.listPreparacionValidar.motivo.idCat, 'motivoReasignacion');
						$scope.catMotivo.valor = $scope.catMotivo.lista[res];
					}
				}

				
			}).error(function(err){
				
			})
		}else if($scope.tipoBusq.valor == 4){
			$scope.getCatalogoEtiquetas();
		}
		
	}
	$timeout($scope.init(), 500);

	$scope.findInJSON = function(obj,valueToFind,idCatalogo){
		for (var i = 0; i < obj.length; i++){
			switch(idCatalogo) {
			  case 'motivoReasignacion':
				  if (obj[i].idCat == valueToFind){
					     return i;
					  }
				  break;
			  case 'motivoValidador':
				if (obj[i].idValidador == valueToFind){
				   return i;
				}
				break;
				  
			  default:
			    // code block
			}		
		}
		return null;
	};

	$scope.nombreEtiqueta=null;
	$scope.getDetallesFiltro = function(){
		var filtro=undefined;
		for (let etiqueta of $scope.catEtiquetas.lista){
			if(etiqueta.idEtiqReval==id){
				console.log(etiqueta)
				filtro = JSON.parse(etiqueta.filtro);
				$scope.nombreEtiqueta=etiqueta.nbEtiqueta;
			}
		}

		supervisionService.getExpedientes(filtro).success(function(datafil){
			//console.log('Se imprien los detalles');
			for(var i=0;i<datafil.length;i++){
				$scope.filtroEtiquetaListData.push(datafil[i].idRegistroCsv);
			}
			//console.log($scope.filtroEtiquetaListData);
				// getCargaInicial();
				supervisionService.consultaDetalleIncidenciaNuExpedientes($scope.filtroEtiquetaListData).success(function(data){

						$scope.detalleRevalidacion.lista = data.detalleCSV;
						$scope.copiaDetalleRevalidacion.lista = data.detalleCSV;
						$scope.gridOptions.data = $scope.copiaDetalleRevalidacion.lista;

						$timeout($scope.fillChekedDetalleArchivosPreSelected(), 500);
						$scope.rowCollection = $scope.gridOptions.data;
						$scope.catMotivo.lista = data.catMotivo;
						$scope.validadores.listaOriginal = data.catValidadores;
						if($scope.catMotivo.lista!=null){
							if($scope.listPreparacionValidar.validador!=undefined){
								var res = $scope.findInJSON($scope.validadores.listaOriginal,$scope.listPreparacionValidar.validador.idValidador, 'motivoValidador');
								$scope.detalleRevalidacion.validador = $scope.validadores.listaOriginal[res];
							}
							if($scope.listPreparacionValidar.motivo!=null){
								var res = $scope.findInJSON($scope.catMotivo.lista,$scope.listPreparacionValidar.motivo.idCat, 'motivoReasignacion');
								$scope.catMotivo.valor = $scope.catMotivo.lista[res];
							}
						}
				}).error(function(err){
					alert('algo paso');
				})
		}).error(function(){
			alert("algo ha fallado");
		});

		

		

	}

	$scope.fillChekedDetalleArchivosPreSelected = function(){
		for(var i=0;i<$scope.gridOptions.data.length;i++){
			if($scope.gridOptions.data[i].stPreSeleccion==true){
				$scope.checkDetalleArchivos($scope.gridOptions.data[i].idRegistroCsv, true)
			}
		}	
	}

	

	$scope.filtrarCombos= function(){
		$scope.gridOptions.data = angular.copy($scope.detalleRevalidacion.lista);
		$scope.filtro = {};
		$scope.dataRegistros = [];
		
		$scope.filtro.listaMarcas = $scope.catBusqMarca.valor != undefined ? $scope.catBusqMarca.valor.map(function(el){return el.idPtMarca}) : [];
		$scope.filtro.listaSubMarcas = $scope.catBusqSubMarca.valor != undefined ? $scope.catBusqSubMarca.valor.map(function(el){return el.idPtSubMarca}) : [];
		$scope.filtro.listaPerfiles = $scope.catBusqPerfil.valor != undefined ? $scope.catBusqPerfil.valor.map(function(el){return el.idPtPerfil}) : [];
		//////console.log($scope.filtro.listaMarcas);
		//////console.log($scope.filtro.listaSubMarcas);
		//////console.log($scope.filtro.listaPerfiles);

		//////console.log($scope.detalleRevalidacion.lista);
		if(!validarFiltroBusqueda()){
			growl.error('Por favor ingrese un parámetro de búsqueda.');
			return false;
		}else{
			$scope.gridOptions.data = angular.copy($scope.filtradoPorMarcSubPer());
		}
	}
	$scope.filtradoPorMarcSubPer = function(){
		var subconjunto=[]
		for(var i=0;i<$scope.detalleRevalidacion.lista.length;i++){
			var isInMarca=true, isInSubmarca=true, isInPerfil=true;
			if($scope.filtro!=null){
				if($scope.filtro.listaMarcas!=null){
					for(var j=0;j<$scope.filtro.listaMarcas.length;j++){
						if($scope.detalleRevalidacion.lista[i].idMarca===$scope.filtro.listaMarcas[j]){
							isInMarca=true;
							break;
						}else{
							isInMarca=false;
						}
					}
				}
				if($scope.filtro.listaSubMarcas!=null){
					for(var j=0;j<$scope.filtro.listaSubMarcas.length;j++){
						if($scope.detalleRevalidacion.lista[i].idSubmarca===$scope.filtro.listaSubMarcas[j]){
							isInSubmarca=true;
							break;
						}else{
							isInSubmarca=false;
						}
					}
				}
				if($scope.filtro.listaPerfiles!=null){
					for(var j=0;j<$scope.filtro.listaPerfiles.length;j++){
						if($scope.detalleRevalidacion.lista[i].idPerfil===$scope.filtro.listaPerfiles[j]){
							isInPerfil=true;
							break;
						}else{
							isInPerfil=false;
						}
					}
				}
			}
			if(isInMarca&&isInSubmarca&&isInPerfil){
				subconjunto.push($scope.detalleRevalidacion.lista[i]);
			}
		}
		return subconjunto;
	}

	function validarFiltroBusqueda(){
		if($scope.filtro==undefined) {return false}
		if($scope.filtro.listaMarcas.length>=0||
			$scope.filtro.listaSubMarcas.length>=0||
			$scope.filtro.listaPerfiles.length>=0) {
				return true
		}
		return false;
	}
	
	$scope.filtrar= function(check, scope){
		if(check){
			$scope.gridOptions.data = $scope.gridOptions.data.filter(function(el){
				return el.inconsistencia.length >0;
			});
		}else{
			$scope.gridOptions.data = angular.copy($scope.filtradoPorMarcSubPer());
		}
		scope.allCheck = false;
		$scope.checkAllDetalleArchivos(false);
		//$scope.refreshData();
	}
	
	$scope.checkAllDetalleArchivos = function(check){
		if(check){
			$scope.gridOptions.data= $scope.gridOptions.data.map(function(el){
				el.stPreSeleccion = true;
				return el;
			})
			$scope.detalleARevalidar = $scope.gridOptions.data.map(function(el){
				return el.idRegistroCsv;
			});
		}else{
			$scope.gridOptions.data= $scope.gridOptions.data.map(function(el){
				el.stPreSeleccion = false;
				return el;
			})
			$scope.detalleARevalidar = [];
		}
		//////console.log($scope.detalleARevalidar);
	}

	$scope.checkDetalleArchivos = function(val, check){//here
		if(check){
			$scope.detalleARevalidar.push(val);
		}else{
			$scope.detalleARevalidar = $scope.detalleARevalidar.filter(function(el){ return el != val});
		}
	}

	$scope.regresar = function (){
		$location.path("/asignaIncidencias");
	};

	$scope.revalidar1= function(revalidaTodo){ 
		showAlert.confirmacion("¿Deseas continuar con la asignación a revalidar?", revalidar, revalidaTodo, noaction);
	}
	function noaction(){
	}

	$scope.openModalPreparacion = function(){
		$scope.showModalAgregarRevalidacion=true;
		$scope.contnewpreparacion=true;
		$scope.infoGrupo={
			total:$scope.detalleARevalidar.length
		}

	}

	$scope.preparacionValidacion = function(){
		if($scope.catMotivo.valor2==null){
			growl.error("Selecciona un motivo de validación para el grupo de expedientes")
			return false;
		}
		var grupoExpedientes={
			idmotivo:parseInt($scope.catMotivo.valor2.idCat),
			listaRegistros:angular.copy($scope.detalleARevalidar)
		};
		//console.log($scope.catMotivo.valor2);
		$scope.listPreparacionValidar.lista.push(grupoExpedientes);
		if($scope.catMotivo.valor!=undefined){
			$scope.listPreparacionValidar.motivo=$scope.catMotivo.valor;
		}
		if($scope.detalleRevalidacion.validador!=undefined){
			$scope.listPreparacionValidar.validador=$scope.detalleRevalidacion.validador;
		}
		if($scope.tipoBusq.valor==4){//Búsqueda por etiqueta
			$scope.listPreparacionValidar.idArchivoCsv=null;
			$scope.listPreparacionValidar.idEtiqueta=id;
		}else{
			$scope.listPreparacionValidar.idArchivoCsv=parseInt(id);
		}
		
		
		$scope.detalleARevalidar=[];
		console.log($scope.listPreparacionValidar);
		$scope.contnewpreparacion=false;
		$scope.init();	
	}

	function revalidar(revalidaTodo){
		var isAllAsigned = true;

		if($scope.tipoBusq.valor >= 2){
			if($scope.catMotivo.valor==null){
				growl.error("Debes seleccionar un motivo de validación");
				return false;
			}
			if($scope.detalleRevalidacion.validador==null||$scope.detalleRevalidacion.validador==undefined){
				growl.error("Debes seleccionar un validador");
				return false;
			}
		}else{

		}

		
		

		////////console.log(revalidaTodo);

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
		//Si todos los campos estan llenos: (motivo,validador y lista seleccionada)
		if(isAllAsigned){
			$scope.reqVO = angular.copy($scope.detalleRevalidacion); 
			$scope.reqVO.motivo = $scope.catMotivo.valor;
			////////console.log($scope.reqVO);
			////////console.log(revalidaTodo);
			if(!revalidaTodo){
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? angular.copy($scope.listPreparacionValidar) : angular.copy($scope.detalleRevalidacion.lista);
			}else{
				$scope.reqVO.lista = $scope.tipoBusq.valor == 2 ? $scope.reqVO.lista.map(function(el){return el.idRegistroCsv}) : angular.copy($scope.detalleRevalidacion.lista);
			}
			if($scope.tipoBusq.valor == 2){
				$scope.reqVO.idArchivoCsv = id;
			}
			//
			////////console.log($scope.reqVO);
			
			if($scope.reqVO.lista.length != 0){
				if($scope.tipoBusq.valor >= 2){
					//console.log('here0');
					supervisionService.revalidar($scope.tipoBusq.valor, $scope.listPreparacionValidar).success(function(data){
						$scope.cleanAfterPost(revalidaTodo);	
					}).error(function(err){
						growl.error("Ocurrio un error en la solicitud al servidor para agregar la asignación")
						showAlert.error(err);
					});
				}else{
					//console.log('here0');
					supervisionService.revalidar($scope.tipoBusq.valor, $scope.reqVO).success(function(data){
						$scope.cleanAfterPost(revalidaTodo);
					}).error(function(err){
						growl.error("Ocurrio un error en la solicitud al servidor para agregar la asignación")
						showAlert.error(err);
					});
				}
				
			}else{
				growl.error("Debe de seleccionar al menos un registro para revalidar")
			}
			//console.log('herefin');
		}
	}

	$scope.cleanAfterPost = function(revalidaTodo){
		$scope.catMotivo.valor = undefined;
		//console.log('here1');
		$scope.detalleRevalidacion.validador = undefined;
		$scope.detalleARevalidar = [];
		//console.log('here2');
		$("#select2-comboMotivoRevalidaRapida-container").text("Seleccione");
		$("#select2-comboRevalidaValidadorRapida-container").text("Seleccione");
		if(revalidaTodo){
			//console.log('here3');
			$scope.showModalRevalidacionRapida = false;
		}else{
			//console.log('here4');
			$scope.showModalRevalidacion = false;
		}
		//console.log('here5');
		growl.success('La asignación se realizó correctamente');
		$scope.regresar();
		
		$scope.incidenciaSeleccionada = {};	
	}

});