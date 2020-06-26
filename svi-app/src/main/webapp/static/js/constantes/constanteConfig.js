angular.module(appTeclo).constant("constante", {
//	Prod 110
	urlWs: "/iecisavexpediente_v140r3prod_api",
//	Prod 58
//	urlWs: "/iecisavexpediente_v130r158prod_api",
//	QA 110
//	urlWs: "/iecisavexpediente_v130r1qa_api",
//	QA 58
//	urlWs: "/iecisavexpediente_v130r158qa_api",
	
	
	appletClass:'application.App',
});

angular.module(appTeclo)
.factory('config',['$http','$location','constante','$rootScope',
	function ($http,$location,constante,$rootScope) {

		var protocol = $location.protocol()+ "://";
		var host = location.host;
		var url = protocol + host + constante.urlWs;
		var absUrl = $location.absUrl();

		let contextApp = absUrl.split("/")[3];
		
		$rootScope.applet_route =url;
		$rootScope.codebase=constante.appletClass;
		let sourceJnlp=protocol + host +'/'+contextApp+'/jnlp/aplicacion.jnlp';
	
		return {
			baseUrl:url,
			absUrl:absUrl,
			contextApp:contextApp,
			sourceJnlp:sourceJnlp
		}
	}
]);