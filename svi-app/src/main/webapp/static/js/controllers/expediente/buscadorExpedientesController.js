/**
 * Maneja la lógica para la busqueda de expedientes y recargar el grid de
 * resultados
 * 
 * @param $window
 * @param $scope
 * @param expedienteService
 * @param growl
 * @param showAlert
 * @returns
 */
angular.module(appTeclo).controller('buscadorExpedientesController',function($window, $scope,$timeout,$filter, expedienteService, growl, showAlert, $location, filtroString) {
	$scope.maxRegistros = 120000;
	$scope.tipoBusq = {
		lista:[],
		valor:2
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
	
	$scope.catIncidencia={
			lista: [],
			valor:undefined
	};
	
	$scope.catBusqMarca = {
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

	$scope.catBusqSubMarca = {
		lista:[],
		valor:undefined
	}
		
	$scope.catBusqPerfil = {
		lista:[],
		valor:undefined
	}
	$scope.filtro = undefined;
	$scope.dataRegistros = [];
	$scope.isOpen = true;	
	$scope.cdPlaca = "";
	$scope.nuExpediente ="";
				
		
	
	
	// primer metodo al cargar la pagina
	// obtiene catalogos y/o expedientes
	// dependiendo si existe filtro anterior
	$scope.init = function() {
		expedienteService.setFiltro(undefined);	
		if(filtroString != undefined && filtroString != "" && filtroString.toString().startsWith("{")){
			$scope.filtro = JSON.parse(filtroString);
		}
		getCargaInicial();			
	}
	
	function getCargaInicial(){		
		expedienteService.getInicio($scope.filtro).success(function(data){
			$scope.catPeriodo.lista = data.catPeriodo;
			$scope.catCSV.lista = data.catCSV;
			$scope.catPT.lista = data.catPT;
			$scope.catMarca.lista = data.catMarca;
			$scope.catSubMarca.lista = data.catSubMarca;
			$scope.catPerfil.lista = data.catPerfil;
			$scope.catPerfilDistinct.lista = data.catPerfilDistinct;
			$scope.catIncidencia.lista = data.catIncidencias;
			
			if(data.expedientes != null && data.expedientes != undefined && data.expedientes.length > 0){
				$scope.dataRegistros = data.expedientes;
			}
			
			refrescarFormularioDeBusqueda();
						
		}).error(function(){
			alert("Algo ha fallado");
		});
	}
	
	function refrescarFormularioDeBusqueda(){
		$scope.catBusqPT.lista = [...$scope.catPT.lista];		
		$scope.catBusqCSV.lista = [...$scope.catCSV.lista];		
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];		       
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		
		if($scope.filtro != null && $scope.filtro != undefined){
		  $scope.cdPlaca = $scope.filtro.cdPlaca;
		  $scope.nuExpediente = $scope.filtro.nuExpediente;
		  $scope.catPeriodo.valor = getEntregasSeleccionadas();	
		  $scope.catBusqPT.valor = getPtsSeleccionados();
		  $scope.catBusqCSV.valor = getCsvsSeleccionados();
		  $scope.catBusqMarca.valor = getMarcasSeleccionadas();
		  $scope.catBusqSubMarca.valor = getSubMarcasSeleccionadas();
		  $scope.catBusqPerfil.valor = getPerfilesSeleccionados();
		  $scope.catIncidencia.valor = getIncidenciasSeleccionadas();
		  $scope.filtrarEntregas();
		  $scope.filtrarPuntosTacticos();
		}	
		
	}
	
	
	
	$scope.verDetalle = function(expediente) {		
		var filtroString = JSON.stringify($scope.filtro);
		$location.path('/expedientes/'+ expediente.idRegistroCsv + '/detalle').search({filtro: filtroString});	
	};
	
	
	$scope.buscarExpedientes=function(){			
		getExpedientes();
	}		
	
	function getExpedientes(){
		$scope.filtro = {};
		$scope.dataRegistros = [];
		$scope.isOpen = false;
		$scope.filtro.listaEntregas = $scope.catPeriodo.valor != undefined ? $scope.catPeriodo.valor.map(function(el){return el.idCat}) : [];
		$scope.filtro.listaLotes = $scope.catBusqPT.valor != undefined ? $scope.catBusqPT.valor.map(function(el){return el.idPt}) : [];
		$scope.filtro.listaCsvs = $scope.catBusqCSV.valor != undefined ? $scope.catBusqCSV.valor.map(function(el){return el.idArchivoCsv}) : [];
		$scope.filtro.listaMarcas = $scope.catBusqMarca.valor != undefined ? $scope.catBusqMarca.valor.map(function(el){return el.idPtMarca}) : [];
		$scope.filtro.listaSubMarcas = $scope.catBusqSubMarca.valor != undefined ? $scope.catBusqSubMarca.valor.map(function(el){return el.idPtSubMarca}) : [];
		$scope.filtro.listaPerfiles = $scope.catBusqPerfil.valor != undefined ? $scope.catBusqPerfil.valor.map(function(el){return el.idPtPerfil}) : [];
		$scope.filtro.listaIncidencias = $scope.catIncidencia.valor != undefined ? $scope.catIncidencia.valor.map(function(el){return el.idIncidencia}) : [];
		$scope.filtro.cdPlaca = $scope.cdPlaca.toUpperCase();
		$scope.filtro.nuExpediente = $scope.nuExpediente.toUpperCase();	
		
		if(!validarFiltroBusqueda()){
			growl.error('Por favor ingrese un parámetro de búsqueda.');
			return false;
		}
		
		expedienteService.getExpedientes($scope.filtro).success(function(data){
			if(data.length >0){
				$scope.dataRegistros = data;
			}else{				
				growl.error('No hay registros');
			}
		}).error(function(err){
			showAlert.aviso(err.message);
			return false;
		})
		
	}
	
	function validarFiltroBusqueda(){
		if($scope.filtro == undefined){
			return false;
		}
		if($scope.filtro.listaEntregas != undefined && $scope.filtro.listaEntregas.length >0){return true}
		if($scope.filtro.listaLotes != undefined && $scope.filtro.listaLotes.length >0){return true}
		if($scope.filtro.listaCsvs != undefined && $scope.filtro.listaCsvs.length >0) {return true}
		if($scope.filtro.listaMarcas != undefined && $scope.filtro.listaMarcas.length >0) {return true}
		if($scope.filtro.listaSubMarcas != undefined && $scope.filtro.listaSubMarcas.length >0) {return true}
		if($scope.filtro.listaPerfiles != undefined && $scope.filtro.listaPerfiles.length >0) {return true}
		if($scope.filtro.cdPlaca != undefined && $scope.filtro.cdPlaca != ""){return true}
		if($scope.filtro.nuExpediente != undefined && $scope.filtro.nuExpediente != ""){return true}
		if($scope.filtro.listaIncidencias != undefined && $scope.filtro.listaIncidencias.length >0){return true}
		return false;
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
	
	$scope.filtrarMarcas=function(){
		if(isTodosCatlgsPerfilVacio()){
			restaurarTodosCatlgsPerfil();
			return;
		}
		if($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0){
			$scope.catBusqSubMarca.lista = [];
			if($scope.catBusqMarca.valor != undefined && $scope.catBusqMarca.valor.length>0){
				for(var i = 0; i<$scope.catBusqMarca.valor.length;i++){
					for(var j=0;j<$scope.catSubMarca.lista.length;j++){
						if(parseInt(($scope.catBusqMarca.valor[i]).idPtMarca) == parseInt($scope.catSubMarca.lista[j].idPtMarca) ){
							$scope.catBusqSubMarca.lista.push($scope.catSubMarca.lista[j]);
						}
					}
				}
				$scope.filtrarSubMarcas();
			}
		}
	}
	
	$scope.filtrarSubMarcas=function(){
		if(isTodosCatlgsPerfilVacio()){
			restaurarTodosCatlgsPerfil();
			return;
		}
		if($scope.catBusqPerfil.valor == undefined || $scope.catBusqPerfil.valor.length == 0 ){		
			$scope.catBusqPerfil.lista = [];
			if($scope.catBusqSubMarca.valor != undefined && $scope.catBusqSubMarca.valor.length >0){
				for(var i =0;i<$scope.catBusqSubMarca.valor.length;i++){
					for(var j=0;j<$scope.catPerfil.lista.length;j++){
						if(parseInt(($scope.catBusqSubMarca.valor[i]).idPtSubMarca) == parseInt($scope.catPerfil.lista[j].idPtSubMarca) ){
							if(isPreexistente($scope.catBusqPerfil.lista, "idPtPerfil", $scope.catPerfil.lista[j].idPtPerfil)<0){
								$scope.catBusqPerfil.lista.push($scope.catPerfil.lista[j]);
							}
						}
					}
				}
			}
		}
		if($scope.catBusqMarca.valor == undefined || $scope.catBusqMarca.valor.length == 0){			
			$scope.catBusqMarca.lista = [];
			if($scope.catBusqSubMarca.valor != undefined && $scope.catBusqSubMarca.valor.length > 0){
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
		if(isTodosCatlgsPerfilVacio()){
			restaurarTodosCatlgsPerfil();
			return;
		}
		if($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0){
			$scope.catBusqSubMarca.lista = [];
			if($scope.catBusqPerfil.valor != undefined && $scope.catBusqPerfil.valor.length > 0){
				for(var i =0;i<$scope.catBusqPerfil.valor.length;i++){
					for(var j=0;j<$scope.catPerfil.lista.length;j++){
						if(parseInt(($scope.catBusqPerfil.valor[i]).idPtPerfil) == parseInt($scope.catPerfil.lista[j].idPtPerfil) ){
							if(isPreexistente($scope.catBusqSubMarca.lista, "idPtPerfil",$scope.catPerfil.lista[j].idPtPerfil)){
								$scope.catBusqSubMarca.lista.push($scope.catPerfil.lista[j]);
							}
						}
					}
				}
				$scope.filtrarSubMarcas();
			}
		}
	}
	
	function isPreexistente(array, attr, value){		
	    for(var i = 0; i < array.length; i += 1) {
	        if(array[i][attr] === value) {
	            return i;
	        }
	    }
	    return -1;		
	}
	
	function getEntregasSeleccionadas(){	
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
	
	function getPtsSeleccionados(){	
		var tmpLotesSeleccionados = [];	
		if($scope.filtro.listaLotes.length == 0){
			return tmpLotesSeleccionados;
		}
		for(var i =0;i<$scope.filtro.listaLotes.length;i++){
			for(var j =0;j<$scope.catPT.lista.length;j++){
				if(parseInt($scope.catPT.lista[j].idPt) == parseInt($scope.filtro.listaLotes[i])){
					tmpLotesSeleccionados.push($scope.catPT.lista[j]);
				}
			}			
		}	
		return tmpLotesSeleccionados;
	}
	
	function getCsvsSeleccionados(){
		var tmpCsvsSeleccionados = [];
		if($scope.filtro.listaCsvs.length == 0){
			return tmpCsvsSeleccionados;
		}
		for(var i =0;i<$scope.filtro.listaCsvs.length;i++){
			for(var j=0;j<$scope.catCSV.lista.length;j++){
				if(parseInt($scope.catCSV.lista[j].idArchivoCsv) == parseInt($scope.filtro.listaCsvs[i])){
					tmpCsvsSeleccionados.push($scope.catCSV.lista[j]);
				}				
			}			
		}	
		return tmpCsvsSeleccionados;
	}
	
	function getMarcasSeleccionadas(){	
		var tmpMarcasSeleccionadas = [];	
		if($scope.filtro.listaMarcas.length == 0){
			return tmpMarcasSeleccionadas;
		}
		for(var i =0;i<$scope.filtro.listaMarcas.length;i++){
			for(var j=0;j<$scope.catMarca.lista.length;j++){		
				if(parseInt($scope.catMarca.lista[j].idPtMarca) == parseInt($scope.filtro.listaMarcas[i])){					
					tmpMarcasSeleccionadas.push($scope.catMarca.lista[j]);
				}
			}
		}	
		return tmpMarcasSeleccionadas;
	}
	
	function getSubMarcasSeleccionadas(){	
		var tmpSubMarcasSeleccionadas = [];	
		if($scope.filtro.listaSubMarcas.length == 0){
			return tmpSubMarcasSeleccionadas;
		}
		for(var i =0;i<$scope.filtro.listaSubMarcas.length;i++){
			for(var j=0;j<$scope.catSubMarca.lista.length;j++){		
				if(parseInt($scope.catSubMarca.lista[j].idPtSubMarca) == parseInt($scope.filtro.listaSubMarcas[i])){					
					tmpSubMarcasSeleccionadas.push($scope.catSubMarca.lista[j]);
				}
			}
		}	
		return tmpSubMarcasSeleccionadas;
	}
	
	function getPerfilesSeleccionados(){	
		var tmpPerfilesSeleccionados = [];	
		if($scope.filtro.listaPerfiles.length == 0){
			return tmpPerfilesSeleccionados;
		}
		for(var i =0;i<$scope.filtro.listaPerfiles.length;i++){
			for(var j=0;j<$scope.catPerfilDistinct.lista.length;j++){		
				if(parseInt($scope.catPerfilDistinct.lista[j].idPtPerfil) == parseInt($scope.filtro.listaPerfiles[i])){					
					tmpPerfilesSeleccionados.push($scope.catPerfilDistinct.lista[j]);
				}
			}			
		}	
		return tmpPerfilesSeleccionados;
	}
	
	function getIncidenciasSeleccionadas(){	
		var tmpIncidenciasSeleccionadas = [];	
		if($scope.filtro.listaIncidencias.length == 0){
			return tmpIncidenciasSeleccionadas;
		}
		for(var i =0;i<$scope.filtro.listaIncidencias.length;i++){
			for(var j=0;j<$scope.catIncidencia.lista.length;j++){		
				if(parseInt($scope.catIncidencia.lista[j].idIncidencia) == parseInt($scope.filtro.listaIncidencias[i])){					
					tmpIncidenciasSeleccionadas.push($scope.catIncidencia.lista[j]);
				}
			}			
		}	
		return tmpIncidenciasSeleccionadas;
	}
	
	$scope.limpiarFomulario = function(event){
		event.preventDefault();
		restaurarFiltro();			
	}
	
	function restaurarFiltro(){
		$scope.filtro = {};
		
		$scope.catPeriodo.lista = [...$scope.catPeriodo.lista];		
		$scope.catBusqPT.lista = [...$scope.catPT.lista];		
		$scope.catBusqCSV.lista = [...$scope.catCSV.lista];	
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista];
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];		       
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];
		$scope.catIncidencia.lista = [...$scope.catIncidencia.lista ];
		
		$scope.catPeriodo.valor = [];
		$scope.catBusqPT.valor=[];	
		$scope.catBusqCSV.valor = [];		
		$scope.catBusqMarca.valor = []
		$scope.catBusqSubMarca.valor = [];
		$scope.catBusqPerfil.valor = [];
		$scope.catIncidencia.valor = [];		
	
		$scope.cdPlaca = "";
		$scope.nuExpediente = "";
		expedienteService.setFiltro(undefined);
		$scope.dataRegistros = [];
	}
	
	function restaurarTodosCatlgsPerfil(){		
		$scope.catBusqMarca.lista = [...$scope.catMarca.lista]; 
		$scope.catBusqSubMarca.lista = [...$scope.catSubMarca.lista];
		$scope.catBusqPerfil.lista = [...$scope.catPerfilDistinct.lista];			
	
	}
	
	function isTodosCatlgsPerfilVacio(){
		if(($scope.catBusqMarca.valor == undefined || $scope.catBusqMarca.valor.length == 0) &&
		   ($scope.catBusqSubMarca.valor == undefined || $scope.catBusqSubMarca.valor.length == 0) &&
		   ($scope.catBusqPerfil.valor == undefined || $scope.catBusqPerfil.valor.length == 0)){
			return true;			
		}
		return false;
	}
	
    $scope.init();

});