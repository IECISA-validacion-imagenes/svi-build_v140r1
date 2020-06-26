angular.module(appTeclo).filter('groupBy', function($parse) {
	return _.memoize(function(items, filtro) {
		var getter = $parse(filtro);
		return _.groupBy(items, function(item) {
			return getter(item);
		});
	});
});

angular.module(appTeclo).filter('cortarTexto', function() {
	return function(input, limit) {
 		return (input.length > limit) ? input.substr(0, limit): input;
	};

});

angular.module(appTeclo).filter('iif', function() {
	 return function(input, trueValue, falseValue) {
	        return input ? trueValue : falseValue;
	   };
});
 