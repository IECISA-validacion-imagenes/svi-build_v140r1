angular.module(appTeclo).controller('expedienteController',	
		function($window, $scope, expedienteService, filtroExpedienteService, growl, showAlert,$filter,$location, expediente) {

	const IMAGEN_ERROR='static/dist/img/no-image.png';
	var filtro = undefined;
	
	// detalle de expediente
	$scope.expediente = {};
	
	// conjunto de checks
	$scope.currentDataCheck={};
	// filtro usado en la pantalla anterior
	$scope.filtro = {}
	
	// lista de ids de expedientes resultante 
	// de la busqueda en la pantalla anterior
	$scope.listaNavegacion = [];
	
	// actual indice en que se encuentra el expediente visto en detalle
	// en la lista de navegacion
	$scope.indiceExpedienteSeleccionado = 0;
	
	$scope.etiquetaExpedientes = {};
	
	$scope.showModalEtiqueta = false;
	
	$scope.openModalEtiqueta = function() {		
		if($scope.formTemplateModal.$invalid) {
			showAlert.requiredFields($scope.formTemplateModal);
			growl.error('Formulario incompleto');
		} else {
			growl.success(angular.toJson($scope.templateModalVO), {title : 'Éxito'});
		}
		
	}

	// primer metodo al cargar la pagina
	$scope.init = function() {
		$scope.expediente = expediente.data;
		$scope.listaNavegacion = expediente.data.listaNavegacion;
		$scope.filtro = expediente.data.filtroAplicado;
		$scope.inicializaChecks();
		$scope.fillCurrentImages();
		$scope.inicializaPerfil();
		if($scope.listaNavegacion !== null && $scope.listaNavegacion.length > 0){
			$scope.indiceExpedienteSeleccionado = $scope.listaNavegacion.indexOf($scope.expediente.idRegistroCsv);
		}
	}
	
	$scope.inicializaChecks = function(){		
		if($scope.expediente.stValidacion == true){
			$scope.currentDataCheck={
					checkPlacaDelanteraOficialModel: $scope.expediente.stPlacaOfiDelantera,
					checkPlacaTraseraOficialModel: $scope.expediente.stPlacaOfiTrasera,					
					checkPlacaDelanteraModel: !$scope.expediente.stValPlacaDelantera,
					checkEntidadDelanteraModel: !$scope.expediente.stValEntidadDelantera,					
					checkPlacaTraseraModel: !$scope.expediente.stValPlacaTrasera,
					checkEntidadTraseraModel: !$scope.expediente.stValEntidadTrasera,					
					checkPochimovilModel:$scope.expediente.stPochomovil,
					checkDoblePlacaModel:$scope.expediente.stDoblePlaca
				};	
		}else{
			$scope.currentDataCheck={
					checkPlacaDelanteraOficialModel:true,
					checkPlacaTraseraOficialModel:true,
					checkPlacaDelanteraModel:false,
					checkEntidadDelanteraModel:false,
					checkPlacaTraseraModel:false,
					checkEntidadTraseraModel:false,
					checkPochimovilModel:false,
					checkDoblePlacaModel:false
				};
		}
			
	};
	
	
	$scope.fillCurrentImages = function(){
		let imgPerfil = $scope.expediente.nbImagenPerfil===null ? IMAGEN_ERROR : $scope.expediente.txRutaStorage+$scope.expediente.txCarpetaSil+'/'+$scope.expediente.nbImagenPerfil;
		let imgAmbiental = $scope.expediente.nbImagenAmbiental===null ? IMAGEN_ERROR : $scope.expediente.txRutaStorage+$scope.expediente.txCarpetaImg+'/'+$scope.expediente.nbImagenAmbiental;
		let imgPlacaDelantera = $scope.expediente.nbImagenPlacaDelantera === null ? IMAGEN_ERROR : $scope.expediente.txRutaStorage+$scope.expediente.txCarpetaImg+'/'+$scope.expediente.nbImagenPlacaDelantera;
		let imgPlacaTrasera = $scope.expediente.nbImagenPlacaTrasera===null ? IMAGEN_ERROR : $scope.expediente.txRutaStorage+$scope.expediente.txCarpetaImg+'/'+ $scope.expediente.nbImagenPlacaTrasera;
		let imgConductor = $scope.expediente.nbImagenConductor === null ? IMAGEN_ERROR : $scope.expediente.txRutaStorage+$scope.expediente.txCarpetaImg+'/'+ $scope.expediente.nbImagenConductor;
		
		$scope.currentSetImagenes = {
				nbImagenAmbiental: imgAmbiental,
				nbImagenPerfil:imgPerfil,
				nbImagenPlacaDelantera:imgPlacaDelantera,
				nbImagenPlacaTrasera: imgPlacaTrasera ,
				nbImagenConductor: imgConductor
		};
		//Imagen que se mostrará al iniciar la aplicación
		$scope.selectImagen('ambiente');
	};
	

	$scope.inicializaPerfil = function() {
		if ($scope.expediente.idPerfil == null) {
			$scope.expediente.perfilSeleccionado = $scope.expediente.cdPerfil.toUpperCase();
		} else {
			$scope.expediente.perfilSeleccionado = $scope.expediente.txMarca
					.toUpperCase()
					+ ' - '
					+ $scope.expediente.txSubMarca
							.toUpperCase()
					+ ' - '
					+ $scope.expediente.txPerfil.toUpperCase();
		}
	};
	
	
	$scope.selectImagen = function(valor){		
		switch(valor) {
		  case 'ambiente':
			  $scope.setImage($scope.currentSetImagenes.nbImagenAmbiental);
			  break;
		  case 'perfil':
			  $scope.setImage($scope.currentSetImagenes.nbImagenPerfil);
			  break;
		  case 'placaDel':
			  $scope.setImage($scope.currentSetImagenes.nbImagenPlacaDelantera);
			  break;
		  case 'placaTra':
			  $scope.setImage($scope.currentSetImagenes.nbImagenPlacaTrasera);
			  break;
		  case 'diver':
			  $scope.setImage($scope.currentSetImagenes.nbImagenConductor);
			  break;
		  default:
		    // code block
		}
		
	};
	
	$scope.setImage = function(imagen){
		let tamano = imagen.length-4;
		let valor = imagen.substring(tamano);
		
    	if(imagen=='' || valor==='null'){
			document.getElementById("myimage").src=IMAGEN_ERROR;			
		}else{
			document.getElementById("myimage").src=imagen;
		}
	};
	
	let viewer = new Viewer(document.getElementById('myimage'), {
        inline: false,
        viewed: function () {
            viewer.zoomTo(1);
        }
    });

	$scope.regresar = function() {		
		expedienteService.setFiltro(JSON.stringify($scope.filtro));		
		$location.path("/expedientes").search({});	
	}

	$scope.navigateInList = function(movimiento){
		if(movimiento == 'avanzar'){
			if($scope.indiceExpedienteSeleccionado + 1 < $scope.listaNavegacion.length){		
				$scope.indiceExpedienteSeleccionado += 1;	
			}else{
				// del ultimo se brinca al primero
				$scope.indiceExpedienteSeleccionado = 0;
			}						
		}else if(movimiento == 'retroceder'){
			if($scope.indiceExpedienteSeleccionado > 0){		
				$scope.indiceExpedienteSeleccionado -= 1;
			}else{
				// del primero se brinca al último
				$scope.indiceExpedienteSeleccionado = $scope.listaNavegacion.length - 1;
			}
		}
		var idRegistroSeleccionado = $scope.listaNavegacion[$scope.indiceExpedienteSeleccionado];		
		getExpediente(idRegistroSeleccionado);	
		
	}
	
	function getExpediente(idRegistroCsv){
		if(!isNaN(idRegistroCsv)){
			expedienteService.getExpediente(parseInt(idRegistroCsv), null).success(function(data){
				$scope.expediente = data;			
				$scope.inicializaChecks();
				$scope.fillCurrentImages();
				$scope.inicializaPerfil();				
			}).error(function(err){
				alert("Hubo un error");
			})
		}
	}
	function cerrarPopOver(){
		$scope.showModalEtiqueta = false;
	}
		
	 $scope.crearEtiqueta = function(){
		 
		if($scope.etiquetaExpedientes.nombre == undefined || $scope.etiquetaExpedientes.nombre == ""){
			cerrarPopOver();
			growl.error('La etiqueta es necesaria para iniciar el marcado de expedientes',{title:"Aviso:", ttl: 3000});
			return false;
		}
		var nbEtiqueta = $scope.etiquetaExpedientes.nombre;		
		cerrarPopOver();
		filtroExpedienteService.crearFiltro(nbEtiqueta, $scope.filtro).success(function(data){		
			if(data.isDuplicada){
				showAlert.confirmacion("Ya existe una etiqueta con las mismas opciones de filtrado denominada "+data.nbEtiqueta+", ¿Desea reutilizarla para marcar los expedientes?", 
						marcarExpediente, data, null,null);
			}else{
				marcar();
				$scope.filtro.nbEtiqueta = data.nbEtiqueta;
				$scope.filtro.idFiltro = data.idEtiqueta;
				$scope.expediente.stPreSeleccion = true;
				growl.success('La etiqueta '+nbEtiqueta+' fue creada exitosamente',{title:"¡Éxito!", ttl: 3000});	
			}
			
			
		}).error(function(err){		
			growl.error(err.message, {title:"Error:", ttl: 4000});	
		})
	}
	function marcarExpediente(etiquetaObj){
	
		$scope.filtro.idFiltro = etiquetaObj.idEtiqueta;
		$scope.filtro.nbEtiqueta = etiquetaObj.nbEtiqueta;
		
		expedienteService.marcarExpediente($scope.expediente.idRegistroCsv).success(function(data){
			growl.success('El expediente ha sido marcado como un candidato para revalidación',{title:"¡Éxito!", ttl: 3000});
			$scope.expediente.stPreSeleccion = true;
		}).error(function(err){
			growl.error(err.msg,{title:"Error:", ttl: 3000});	
		})
	}
	
	function marcar(){
		expedienteService.marcarExpediente($scope.expediente.idRegistroCsv).success(function(data){
			growl.success('El expediente ha sido marcado como un candidato para revalidación',{title:"¡Éxito!", ttl: 3000});
			$scope.expediente.stPreSeleccion = true;
		}).error(function(err){
			growl.error(err.message, {title:"Error:", ttl: 4000});
		})
	}
	
	$scope.confirmarMarcado = function(){
		showAlert.confirmacion("¿Está seguro de marcar el expediente como candidato a revalidación?", 
				marcar, null, null,null);	
	}
	
	$scope.ingresarEtiqueta = function(){
		$scope.etiquetaExpedientes.nombre="";
		$scope.showModalEtiqueta = true;
	}

	
	$scope.limpiarFormEtiqueta = function(componente){	
		
		componente.$setPristine();
	}
	
	$scope.init();

});