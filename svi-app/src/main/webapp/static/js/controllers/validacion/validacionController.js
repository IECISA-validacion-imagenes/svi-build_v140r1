angular.module(appTeclo).controller("validacionController",function($rootScope, $scope, $location, $window, $timeout,validacionService,growl, showAlert, config) {

	//Variables de visualización
	$scope.mostrarAlgo=false;
	$scope.imagenesAsignadas=false;
	//Variables de Catálogos
	$scope.catEntidades =[];
	$scope.catPerfiles = [];
	$scope.catClasifExpediente =[];
	$scope.catSiluetas = [];
	$scope.catSinPlacas = {cd:'NO_PLATE', txt:'SIN PLACA'};
	$scope.catSinEntidad = {cd:'NF',txt:'SIN ENTIDAD'};
	$scope.constantePochoMovil='VARIOS- POCHIMOVIL -COMPACTO';
	
	$scope.perfilSelected=null;
	$scope.entidadDelanteraSelected=null;
	$scope.entidadTraseraSelected=null;
	$scope.clasificExpedienteSelected = null;
	$scope.siluetaSelected = null;


	//Variables de objetos a persistir
	$scope.currentData= [];
	$scope.dataRegistros=[];

	//Variables generales
	const IMAGEN_ERROR='static/dist/img/no-image.png';
	$scope.validacionDatosVO=[];
	$scope.totalRegAsignados=0;
	
	//nuevo catalogo 
	$scope.catMarcas = [];
	

	$scope.seteoInicial = function(){
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
	};
	$scope.seteoInicial();


	$scope.testPerfilCat=[
		{
			idMarca: "1",
			idPerfil: "2",
			idSubmarca: "3",
			marcaSubPerfil: "NISSAN MARCH  COMPACTO",
			urlImagen: "http://172.25.25.29/E01/PT047/imagenes/180913-104957_001_GFDC.jpg"
		},
		{
			idMarca: "1",
			idPerfil: "2",
			idSubmarca: "3",
			marcaSubPerfil: "COMPACTO",
			urlImagen: "http://172.25.25.29/E01/PT047/imagenes/180913-104957_001_GFDC.jpg"
		},
		{
			idMarca: "1",
			idPerfil: "2",
			idSubmarca: "3",
			marcaSubPerfil: "OTROS OBJETOS",
			urlImagen: "http://172.25.25.29/E01/PT047/imagenes/180913-104957_001_GFDC.jpg"
		}
		
	]
		

	/**Obtención de catálogos */
	let getCatalogoEntidades = function () {
		validacionService.getCatalogoEntidades().success(function (data) {
               $scope.catEntidades = data;
            }).error(function (data) {
				$scope.error = data;
            	//hacer algo en el error 
			})};
	let getCatalogoPerfiles = function () {
		validacionService.getcatalogoPerfiles().success(function (data) {
			$scope.catPerfiles = data;
			console.log(data);
		}).error(function (data) {
			$scope.error = data;
		   //hacer algo en el error 
	})};
	let getCatalogoClasifExpediente = function () {
		validacionService.getcatClasifExpediente().success(function (data) {
			
		$scope.catClasifExpediente = data;
	}).error(function (data) {
		$scope.error = data;
		//hacer algo en el error 
	})};
	let getCatValidacionSiluetas = function () {
		validacionService.getcatValidacionSiluetas().success(function (data) {
			$scope.catSiluetas = data;
		}).error(function (data) {
		   //hacer algo en el error 
	})};
	let getcatalogoValidacionMarcas = function () {
		//$scope.catPerfiles = $scope.testPerfilCat;
		
		validacionService.getcatalogoMarcas().success(function (data) {
			$scope.catPerfiles = data;
			console.log($scope.catPerfiles);
		}).error(function (data) {
			$scope.error = data;
		   //hacer algo en el error 
	})
};

	getCatalogoEntidades();
	//getCatalogoPerfiles();
	getCatalogoClasifExpediente();
	getCatValidacionSiluetas();
	getcatalogoValidacionMarcas();

	$scope.setearValoresInicialesDeCatalogos = function(){
		$timeout(function() {
			/*
			$scope.validacionAndFillCatalogos($scope.catPerfiles, 'BICICLETA','perfil');
			$scope.validacionAndFillCatalogos($scope.catEntidades, 'Alaska','entidadDelantera');
			$scope.validacionAndFillCatalogos($scope.catEntidades, 'Alaska','entidadTrasera');
			$scope.validacionAndFillCatalogos($scope.catClasifExpediente, '2 - Perfil con Placa y Entidad  pero Sin Perfil','clasificacionExpediente');
			*/
			$scope.validacionAndFillCatalogos($scope.catSiluetas, 'No tiene silueta','silueta');
		  },1000);
	}
	/** Fin Obtención de catálogos */

	/** Metodos para el llenado de los catalogos */
	//Esto se hace por que la directiva tiene un bug y aun que asigna el elemento, no lo pinta en el componente
	$scope.findInJSON = function(obj,valueToFind,idCatalogo){
		for (var i = 0; i < obj.length; i++){
			switch(idCatalogo) {
			  case 'entidades':
				  if (obj[i].cdEntidad == valueToFind){
					     return i;
					  }
				  break;
			  case 'entidadesByTxt':
				  if (obj[i].txDescEntidad == valueToFind){
					     return i;
					  }
				  break;
			  case 'perfil':
				  if (obj[i].marcaSubPerfil == valueToFind){
					     return i;
					  }
				  break;
			  case 'silueta':
				  if (obj[i].cdValidacionSilueta == valueToFind){
					     return i;
					  }
				  break;
			  case 'expediente':
				  if (obj[i].idPtClasifExpe == valueToFind){
					     return i;
					  }
				  break;
			  case 'idRegistroCsv':
				  if (obj[i].idRegistroCsv == valueToFind){
					     return i;
					  }
				  break;
			  default:
			    // code block
			}		
		}
		return null;
	};
	//Esto se hace por que la directiva tiene un bug y aun que asigna el elemento, no lo pinta en el componente
	$scope.validacionAndFillCatalogos = function(jsonObject,valueToFind,idCatalogo){		
		switch(idCatalogo) {
			case 'perfil':
				var result=$scope.findInJSON(jsonObject,valueToFind,'perfil');
				if(result!==null){
					$scope.perfilSelected=$scope.catPerfiles[result];
					$("#select2-perfil-container").text($scope.perfilSelected.marcaSubPerfil);
				}else{
					$scope.perfilSelected=null;
					$("#select2-perfil-container").text('');
				}
				break;
			case 'silueta':
				var result=$scope.findInJSON(jsonObject,valueToFind,'silueta');
				if(result!==null){
					$scope.siluetaSelected=$scope.catSiluetas[result];
					$("#select2-silueta-container").text($scope.siluetaSelected.txtDescripcion);
				}else{
					$scope.siluetaSelected=null;
					$("#select2-silueta-container").text('');
				}
				break;
			case 'entidadDelantera':
				var result=$scope.findInJSON(jsonObject,valueToFind,'entidades');
				if(result!==null){
					$scope.entidadDelanteraSelected=$scope.catEntidades[result];
					$("#select2-comboEntidadDelantera-container").text($scope.entidadDelanteraSelected.txDescEntidad);
					if($scope.entidadDelanteraSelected.txDescEntidad===$scope.catSinEntidad.txt){
						$scope.currentDataCheck.checkEntidadDelanteraModel=true;
					}
				}else{
					$scope.entidadDelanteraSelected=$scope.catEntidades[result];
					$("#select2-comboEntidadDelantera-container").text('');
				}
				break;
			case 'entidadTrasera':
				var result=$scope.findInJSON(jsonObject,valueToFind,'entidades');
				if(result!==null){
					$scope.entidadTraseraSelected=$scope.catEntidades[result];
					$("#select2-comboEntidadTrasera-container").text($scope.entidadTraseraSelected.txDescEntidad);
					if($scope.entidadTraseraSelected.txDescEntidad===$scope.catSinEntidad.txt){
						$scope.currentDataCheck.checkEntidadTraseraModel=true;
					}
				}else{
					$scope.entidadTraseraSelected=$scope.catEntidades[result];
					$("#select2-comboEntidadTrasera-container").text('');
				}
				break;
			case 'clasificacionExpediente':
				var result=$scope.findInJSON(jsonObject,valueToFind,'expediente');
				if(result!==null){
					$scope.clasificExpedienteSelected=$scope.catClasifExpediente[result];
					$("#select2-clasificacion-container").text($scope.clasificExpedienteSelected.txClasifExpe);
				}else{
					$scope.clasificExpedienteSelected=$scope.catClasifExpediente[result];
					$("#select2-clasificacion-container").text('');
				}
				break;
			default:
			  // code block
		  }
	};
	//Asignamos el valor seleccionado al currentData
	/*$scope.setToEntidadDelantera = function(){
		$scope.currentData.txDescEntidad=$scope.entidadDelanteraSelected.txDescEntidad;		
	};
	$scope.setToEntidadTrasera = function(){
		$scope.currentData.txDescEntidad=$scope.entidadTraseraSelected.txDescEntidad;
	};
	*/
	$scope.setToClasificSelected = function(){
		$scope.currentData.txClasifExpe=$scope.clasificExpedienteSelected.txClasifExpe;
	};
	$scope.setToSiluetaSelected = function(){
		$scope.currentData.idCatValidacion=$scope.siluetaSelected.idCatValidacion;
	};
	/** Fin Metodos para el llenado de los catalogos */


	/** Metodos para el funcionamiento de los Switches */
	$scope.checkPlacaDelanteraAction = function(){		
		if($scope.currentDataCheck.checkPlacaDelanteraModel){
			$scope.currentData.cdPlacaDelantera=$scope.catSinPlacas.cd;
			$scope.currentData.txtPlacaDelantera=$scope.catSinPlacas.txt;
			if($scope.currentDataCheck.checkPlacaDelanteraOficialModel){
				$scope.currentDataCheck.checkPlacaDelanteraOficialModel=false;
			}
		}else{
			$scope.currentData.cdPlacaDelantera='';
			$scope.currentData.txtPlacaDelantera='';
		}
		showAlert.requiredFields($scope.formValidaRegistro);
	}
	$scope.checkEntidadDelanteraAction = function(){
		if($scope.currentDataCheck.checkEntidadDelanteraModel){ 
			$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.catSinEntidad.cd,'entidadDelantera');
		}else{
			//$scope.currentData.txDescEntidad='';
			$scope.entidadDelanteraSelected=null;
			$scope.validacionAndFillCatalogos($scope.catEntidades, null,'entidadDelantera');
		}
		showAlert.requiredFields($scope.formValidaRegistro);
	}
	$scope.checkPlacaTraseraAction = function(){
		if($scope.currentDataCheck.checkPlacaTraseraModel){
			$scope.currentData.cdPlacaTrasera=$scope.catSinPlacas.cd;
			$scope.currentData.txtPlacaTrasera=$scope.catSinPlacas.txt;
			if($scope.currentDataCheck.checkPlacaTraseraOficialModel){
				$scope.currentDataCheck.checkPlacaTraseraOficialModel=false;
			}
		}else{
			$scope.currentData.cdPlacaTrasera='';
			$scope.currentData.txtPlacaTrasera='';
		}
		showAlert.requiredFields($scope.formValidaRegistro);
	}
	$scope.checkEntidadTraseraAction = function(){
		if($scope.currentDataCheck.checkEntidadTraseraModel){
			$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.catSinEntidad.cd,'entidadTrasera');
		}else{
			//$scope.currentData.txDescEntidad='';
			$scope.entidadTraseraSelected=null;
			$scope.validacionAndFillCatalogos($scope.catEntidades, null,'entidadTrasera');
		}
		showAlert.requiredFields($scope.formValidaRegistro);
	}
	$scope.checkPochimovilAction = function(){
		if($scope.currentDataCheck.checkPochimovilModel){
			$scope.validacionAndFillCatalogos($scope.catPerfiles, $scope.constantePochoMovil,'perfil');
		}else{
			//$scope.currentData.txDescripcion='';
			$scope.perfilSelected=null;
			$scope.validacionAndFillCatalogos($scope.catPerfiles, null,'perfil');
		}
		showAlert.requiredFields($scope.formValidaRegistro);
	}
	$scope.checkPlacaDelanteraOficialAction = function(){
		return "jaja";
	}

	/** Metodos con las reglas de validación*/
	$scope.hasValue = function(value){ 
		var hasval=false;
		
		if(value!==null){
			if(value !== undefined){
				if(value.trim()!==''){
					hasval=true;
				}
			}		
		}		
		return hasval;
	};


	$scope.validacionAutomatica = function(identificador){
		$scope.valConsecuente=null;
		$scope.valModificado=null;
		switch(identificador) {
			  case 'PlacaDelantera':
				  $scope.valConsecuente=$scope.currentData.txtPlacaTrasera;
				  $scope.valModificado=$scope.currentData.txtPlacaDelantera;				  
				  break;

			  case 'PlacaTrasera':
				  $scope.valConsecuente=$scope.currentData.txtPlacaDelantera;
				  $scope.valModificado=$scope.currentData.txtPlacaTrasera;
				  break;

			  case 'EntidadDelantera':
			  
			  		var result=$scope.findInJSON($scope.catEntidades,$("#select2-comboEntidadTrasera-container").text(),'entidadesByTxt');
			  		if(result!==null){
				  		$scope.perfilSelected=$scope.catPerfiles[result];
					  	$scope.valConsecuente=$scope.catEntidades[result].cdEntidad;
			  		}else{
						$scope.valConsecuente='';
					}
				  	$scope.valModificado=$scope.entidadDelanteraSelected.cdEntidad;
				  
				  				  
				  break;
				  
			  case 'EntidadTrasera':

			  		var result=$scope.findInJSON($scope.catEntidades,$("#select2-comboEntidadDelantera-container").text(),'entidadesByTxt');
					if(result!==null){
						$scope.perfilSelected=$scope.catPerfiles[result];
						$scope.valConsecuente=$scope.catEntidades[result].cdEntidad;
					}else{
						$scope.valConsecuente='';
					}
				  	$scope.valModificado=$scope.entidadTraseraSelected.cdEntidad;
				  	break;
			
			default:
			  // code block
		  }
		

		  //Reglas de logica proposicional
    		if(($scope.hasValue($scope.valModificado)===true&&$scope.hasValue($scope.valConsecuente)===false)
    				||($scope.hasValue($scope.valModificado)===false&&$scope.hasValue($scope.valConsecuente)===false)){
    			
    			$scope.valConsecuente=$scope.valModificado;
    			//console.log('Se modifico '+identificador+'; valModificado: '+$scope.valModificado+',  valConsecuente: '+$scope.valConsecuente);
    			
    		}else if(($scope.hasValue($scope.valModificado)===false & $scope.hasValue($scope.valConsecuente)===true) 
    				|| ($scope.hasValue($scope.valModificado)===true & $scope.hasValue($scope.valConsecuente)===true)){
    			$scope.valModificado=$scope.valModificado;
    			//console.log('Se modifico '+identificador+'; valModificado: '+$scope.valModificado+',  valConsecuente: '+$scope.valConsecuente);
    		}
		
		switch(identificador) {
			  case 'PlacaDelantera':
					$scope.currentData.cdPlacaTrasera=$scope.valConsecuente;
					$scope.currentData.cdPlacaDelantera=$scope.valModificado;
					$scope.currentData.txtPlacaTrasera=$scope.valConsecuente;
					$scope.currentData.txtPlacaDelantera=$scope.valModificado;

					if($scope.currentData.txPlacaTrasera===$scope.catSinPlacas.txt||$scope.currentData.txPlacaTrasera===$scope.catSinPlacas.cd
						||$scope.currentData.cdPlacaTrasera===$scope.catSinPlacas.txt||$scope.currentData.cdPlacaTrasera===$scope.catSinPlacas.cd){
							$scope.currentDataCheck.checkPlacaTraseraModel=true;
							$scope.checkPlacaTraseraAction();

					}
					if($scope.currentData.txtPlacaDelantera===$scope.catSinPlacas.txt||$scope.currentData.txtPlacaDelantera===$scope.catSinPlacas.cd
						||$scope.currentData.cdPlacaDelantera===$scope.catSinPlacas.txt||$scope.currentData.cdPlacaDelantera===$scope.catSinPlacas.cd){
							$scope.currentDataCheck.checkPlacaDelanteraModel=true;
							$scope.checkPlacaDelanteraAction();

					}
					break;

			  case 'PlacaTrasera':
					$scope.currentData.cdPlacaTrasera=$scope.valModificado;
					$scope.currentData.cdPlacaDelantera=$scope.valConsecuente;
					$scope.currentData.txtPlacaTrasera=$scope.valModificado;
					$scope.currentData.txtPlacaDelantera=$scope.valConsecuente;

					if($scope.currentData.txPlacaTrasera===$scope.catSinPlacas.txt||$scope.currentData.txPlacaTrasera===$scope.catSinPlacas.cd
						||$scope.currentData.cdPlacaTrasera===$scope.catSinPlacas.txt||$scope.currentData.cdPlacaTrasera===$scope.catSinPlacas.cd){
							$scope.currentDataCheck.checkPlacaTraseraModel=true;
							$scope.checkPlacaTraseraAction();

					}
					if($scope.currentData.txtPlacaDelantera===$scope.catSinPlacas.txt||$scope.currentData.txtPlacaDelantera===$scope.catSinPlacas.cd
						||$scope.currentData.cdPlacaDelantera===$scope.catSinPlacas.txt||$scope.currentData.cdPlacaDelantera===$scope.catSinPlacas.cd){
							$scope.currentDataCheck.checkPlacaDelanteraModel=true;
							$scope.checkPlacaDelanteraAction();

					}

				  	break;

			  case 'EntidadDelantera':
			  		$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.valModificado,'entidadDelantera');
					$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.valConsecuente,'entidadTrasera');
				  	break;

			  case 'EntidadTrasera':
			  		$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.valConsecuente,'entidadDelantera');
			  		$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.valModificado,'entidadTrasera');
					break;
				
			  default:
			  // code block
		  }
		
	};
	/** Fin Metodo con las reglas de validación*/
	
	/** Metodos de visor de imágenes */

	let viewer = new Viewer(document.getElementById('myimage'), {
        inline: false,
        viewed: function () {
            viewer.zoomTo(1);
        }
    });

	$scope.updateContadores = function(){
		//console.log($scope.currentData.idArchivoCsv);
		validacionService.archivoEstaValidado($scope.currentData.idArchivoCsv) 
       	.success(function(datav){
       		$scope.validacionDatosVO=datav;
       		console.log($scope.validacionDatosVO);
       	}).error(function(datav){
			$scope.error = datav;
//       		alert('error en id archivo: '+$scope.currentData.idArchivoCsv);
       	})
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

	$scope.fillCurrentImages = function(){
		let imgPerfil = $scope.currentData.nbImagenPerfil===null ? IMAGEN_ERROR : $scope.currentData.txRutaStorage+$scope.currentData.txCarpetaSil+'/'+$scope.currentData.nbImagenPerfil;
		let imgAmbiental = $scope.currentData.nbImagenAmbiental===null ? IMAGEN_ERROR : $scope.currentData.txRutaStorage+$scope.currentData.txCarpetaImg+'/'+$scope.currentData.nbImagenAmbiental;
		let imgPlacaDelantera = $scope.currentData.nbImagenPlacaDelantera === null ? IMAGEN_ERROR : $scope.currentData.txRutaStorage+$scope.currentData.txCarpetaImg+'/'+$scope.currentData.nbImagenPlacaDelantera;
		let imgPlacaTrasera = $scope.currentData.nbImagenPlacaTrasera===null ? IMAGEN_ERROR : $scope.currentData.txRutaStorage+$scope.currentData.txCarpetaImg+'/'+ $scope.currentData.nbImagenPlacaTrasera;
		let imgConductor = $scope.currentData.nbImagenConductor === null ? IMAGEN_ERROR : $scope.currentData.txRutaStorage+$scope.currentData.txCarpetaImg+'/'+ $scope.currentData.nbImagenConductor;
		
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
	/** Fin Metodos de visor de imágenes */

	$scope.mallaTemas = function() {
		
		var headerHeight = $('.main-header').height();
		var menuWidth 	 = $('.main-sidebar').width();
		var windowHeight = $(window).height();
		var windowWidth  = $(window).width();
		var px 			 = "px";
		
		$scope.b_malla = !$scope.b_malla;
		
		$('.content-wrapper').toggleClass('desenfoqueApp');
		
		$timeout(function() {
			$('.mallaTemas').css("top", "0");
			$('.mallaTemas').css("width", (windowWidth-menuWidth)+px);
			$('.mallaTemas').css("height", (windowHeight-headerHeight)+px);
		});
	}

	/**Métdos ´para navegar entre los elementos de la lista */
	$scope.navigateInList = function(movimiento,idRegistroCsvTemp){
		idRegistroCsvTemp = idRegistroCsvTemp ===-1 ? $scope.currentData.idRegistroCsv : idRegistroCsvTemp;
		$scope.newPos=0;
		console.log($scope.newPos);
		var result=$scope.findInJSON($scope.dataRegistros,idRegistroCsvTemp,'idRegistroCsv');          		
		if(result!==null){
			switch(movimiento) {
				case 'avanzar':
					if(result!==$scope.dataRegistros.length-1){
						$scope.newPos=result+1;
						console.log('newpos: '+$scope.newPos);
					}
					break;
				case 'retroceder':
					if(result!==0){
						$scope.newPos=result-1;
						console.log('newpos: '+$scope.newPos);
					}else{
						$scope.newPos=$scope.dataRegistros.length-1;
						console.log('la posicion actual es esta: '+$scope.newPos);
					}
					break;
			  }		
		}

		$scope.currentDataCheck=[];
		$scope.pintadoPantalla($scope.dataRegistros[$scope.newPos]);
	};

	/**Fin Métdos ´para navegar entre los elementos de la lista */


	/**Métodos para seleccionar un elemento de la lista */

	$scope.clickEnTablaImagenes = function(val, event){
		$("#hideElement1").css("display","none");
		$("#hideElement2").css("display","none");
		
		$scope.currentDataCheck=[];
		$scope.pintadoPantalla(val);
		$scope.isOpen = false;
	}
	/** Fin Métodos para seleccionar un elemento de la lista */
	/** Metodos para validar */

	$scope.clickEnBotonValidar2 = function(){
		$scope.buttonDisabled2 = true;
		$scope.jsonPersistence.stValPlacaDelantera===true?$scope.jsonPersistence.stValPlacaDelantera=false:$scope.jsonPersistence.stValPlacaDelantera=true;
		$scope.jsonPersistence.stValEntidadDelantera===true?$scope.jsonPersistence.stValEntidadDelantera=false:$scope.jsonPersistence.stValEntidadDelantera=true;
		$scope.jsonPersistence.stValPlacaTrasera===true?$scope.jsonPersistence.stValPlacaTrasera=false:$scope.jsonPersistence.stValPlacaTrasera=true;
		$scope.jsonPersistence.stValEntidadTrasera===true?$scope.jsonPersistence.stValEntidadTrasera=false:$scope.jsonPersistence.stValEntidadTrasera=true;

		validacionService.validacionRest($scope.jsonPersistence) 
			.success(function(result){
				
				validacionService.archivoEstaValidado($scope.currentData.idArchivoCsv) 
				.success(function(datav){
					$scope.validacionDatosVO=datav;
					//console.log($scope.validacionDatosVO);
					if(datav.archivoValidado){

						$scope.currentDataCheck=[];
						primeraAsignacionFunction();
						
					}else{
						//Marcamos el registro como validado
						
						var idRegistroCsvTemp=$scope.currentData.idRegistroCsv;
						var result=$scope.findInJSON($scope.dataRegistros,$scope.currentData.idRegistroCsv,'idRegistroCsv');          		
						if(result!==null){
							//console.log('arreglo inicial: ');
							//console.log($scope.dataRegistros);
							$scope.navigateInList('avanzar',idRegistroCsvTemp);
							$scope.dataRegistros.splice(result,1);
							//console.log('arreglo final: ');
							//console.log($scope.dataRegistros);
							if($scope.dataRegistros.length<=0){
								primeraAsignacionFunction();
							}
							/*Esta sección por si requieren mostrar el primer registro no validado
							var result=$scope.findRegisterNotValidatedToShow(); 
							if(result!==null){
								$scope.currentDataCheck=[];
								$scope.pintadoPantalla($scope.dataRegistros[result]);	
							}else{
					//			alert('ya no hay registros por validar en fill, reaisgnar');
							}
							*/
							
						}
					}
				}).error(function(datav){
					$scope.error = datav;
            		console.log(datav);
				})
				
			 
			}).error(function(result){
				$scope.error = result;
            		console.log(result);
			});
			$scope.buttonDisabled = false;
	 }

	 
	 $scope.generateJson4Persistence = function(){

		$scope.jsonPersistence={
			
			idRegistroCsv: $scope.currentData.idRegistroCsv,
			cdPlacaDelantera: $scope.currentData.cdPlacaDelantera,
			cdEntidadDelantera:$scope.entidadDelanteraSelected.cdEntidad,
			nbImagenPlacaDelantera: $scope.currentData.nbImagenPlacaDelantera,
			cdPlacaTrasera: $scope.currentData.cdPlacaTrasera,
			cdEntidadTrasera: $scope.entidadTraseraSelected.cdEntidad,
			nbImagenPlacaTrasera: $scope.currentData.nbImagenPlacaTrasera,
			nbImagenConductor: $scope.currentData.nbImagenConductor,
			nbImagenAmbiental: $scope.currentData.nbImagenAmbiental,
			cdPerfil: $scope.perfilSelected.txtPerfil,
			nbImagenPerfil: $scope.currentData.nbImagenPerfil,
			idArchivoCsv: $scope.currentData.idArchivoCsv,
			idPtClasifExpe: $scope.clasificExpedienteSelected.idPtClasifExpe,
			idCatValidacion: $scope.siluetaSelected.idCatValidacion,

			stValPlacaDelantera: $scope.currentDataCheck.checkPlacaDelanteraModel,
			stValEntidadDelantera: $scope.currentDataCheck.checkEntidadDelanteraModel,
			stValPlacaTrasera: $scope.currentDataCheck.checkPlacaTraseraModel,
			stValEntidadTrasera: $scope.currentDataCheck.checkEntidadTraseraModel,

			stPochomovil: $scope.currentDataCheck.checkPochimovilModel,
			stPlacaOfiDelantera: $scope.currentDataCheck.checkPlacaDelanteraOficialModel,
			stPlacaOfiTrasera: $scope.currentDataCheck.checkPlacaTraseraOficialModel,
			stDoblePlaca: $scope.currentDataCheck.checkDoblePlacaModel,
			stDifPerfil: $scope.siluetaSelected.idCatValidacion,
			

			idPerfil: $scope.perfilSelected.idPerfil,
			idMarca: $scope.perfilSelected.idMarca,
			idSubMarca: $scope.perfilSelected.idSubmarca
		};

		console.log($scope.jsonPersistence);
		//return jsonPersistence;

	 };

	$scope.clickEnBotonValidar = function() { 
		$scope.buttonDisabled = true;

		if($scope.formValidaRegistro.$invalid){
			showAlert.requiredFields($scope.formValidaRegistro);
			$scope.buttonDisabled = false;
		}else{
			if($scope.siluetaSelected==null||$scope.perfilSelected==null||
				$scope.clasificExpedienteSelected==null||
				$scope.entidadDelanteraSelected==null||$scope.entidadTraseraSelected==null){
					$scope.buttonDisabled = false;
					
			}else{
				$scope.generateJson4Persistence();
					showAlert.confirmacion("¿Esta seguro de querer validar el registro?",
					$scope.clickEnBotonValidar2, $scope.object, $scope.cancelbtn);
			}	
		}
	}

	$scope.cancelbtn = function(){
		$scope.buttonDisabled = false;
	}

	/** Fin Métodos para validación */
	
	/****** Metodos de Asignación */

	$scope.findRegisterNotValidatedToShow = function(){
		for (var i = 0; i < $scope.dataRegistros.length; i++){
			  // look for the entry with a matching `code` value
			if ($scope.dataRegistros[i].stValidacion == 0){
			     return i;
			}	
		}
		return null;
	};

	$scope.pintadoPantalla = function(jsonObject){
		$scope.currentData=angular.copy(jsonObject);
		$scope.totalRegAsignados=$scope.currentData.totAsignacionInicial;
		console.log('total asignados: '+$scope.totalRegAsignados);
		$scope.updateContadores();
		$scope.seteoInicial();
		$scope.fillCurrentImages();
		console.log($scope.currentData);
		//Inicializamos los valores en catálogos
		$scope.validacionAndFillCatalogos($scope.catPerfiles, $scope.currentData.cdPerfil,'perfil');
		$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.currentData.cdEntidadDelantera,'entidadDelantera');
		$scope.validacionAndFillCatalogos($scope.catEntidades, $scope.currentData.cdEntidadTrasera,'entidadTrasera');
		$scope.validacionAndFillCatalogos($scope.catClasifExpediente, null,'clasificacionExpediente');
		$scope.validacionAndFillCatalogos($scope.catSiluetas, 'SINSILUETA','silueta');

		$scope.validacionAutomatica('PlacaDelantera');
		//$scope.validacionAutomatica('EntidadDelantera');
		$scope.validacionAutomatica('PlacaTrasera');
		//$scope.validacionAutomatica('EntidadTrasera');

		
	}

	$scope.fillData = function(data){
		//Persistimos todos los registros en una variable global
		$scope.dataRegistros = data;
		//Obtenemos siguiente registro por validar
		var result=$scope.findRegisterNotValidatedToShow(); 
		if(result!==null){
			$scope.pintadoPantalla($scope.dataRegistros[result]);	
		}else{
//			alert('ya no hay registros por validar en fill, reaisgnar');
		}
	};

	let testCancelAsignacion = function(){
        $scope.mostrarAlgo = false;
        $scope.imagenesAsignadas = false;
	};

	let primeraAsignacionFunction = function () {
		validacionService.asignacionInicial() 
            .success(function (data) {
				$scope.mostrarAlgo = true;
				$scope.fillData(data);
//				console.log(data);
                if (data.length !== 0) {
                    $scope.imagenesAsignadas = true;
                
                } else {
					$scope.imagenesAsignadas = false;
					$scope.mostrarAlgo = true;
                    showAlert.aviso("Contacte a su administrador, no hay archivos de expediente disponibles");
                }
            }).error(function (data) {
                if (data.message) {
					$scope.error = data;
                    //showAlert.aviso(data.message);
                } else {
					$scope.error = data;
                    //showAlert.aviso(data);
                }
            })
    };
	
	let procederProcesoInicial = function () {
		validacionService.tieneAsignacionesRest()
            .success(function (data) {
                $scope.tieneAsignaciones = data;
                if($scope.tieneAsignaciones === false){
					//$scope.setearValoresInicialesDeCatalogos();
                    showAlert.confirmacion("Usted no tiene registros a validar. El sistema procederá a asignarle. ¿Está seguro?",
                    		primeraAsignacionFunction, $scope.object, testCancelAsignacion);

                }else{
                    primeraAsignacionFunction();
                }
            }).error(function (data) {
                if (data.message) {
					$scope.error = data;
                   // showAlert.aviso(data.message, testAviso);
                } else {
               //     showAlert.aviso(data, testAviso);
                }
            });

        //$scope.mostrarAlgo = true;
    };
    
	/****** FIN  Metodos de Asignación */

	procederProcesoInicial();
	
	  

	

});

