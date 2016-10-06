app.factory('networkService', ['$http', function($http) {
	return 
	{
		getParticularNetwork: function(id) 
		{
			var request = 
			{
				method: "GET",
				url: API['NETWORK']+'/'+id.toString(),
				headers:
				{
					"Content-Type": "application/json",
					"Cookie": $cookies.get("session_id")
				}
			}

			$http(request).success(function(data) {
				return data;
			})
			.error(function(err) {
				return err;
			});
		}
	}
}]);