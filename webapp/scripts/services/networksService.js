app.factory('networksService', ['$http', '$cookies', function($http, $cookies) {
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
					"Cookie": "session_id="+$cookies.get("session_id")
				}
			};

			$http(request).success(function(data) {
				return data;
			})
			.error(function(err) {
				return err;
			});
		},
		
		getNetworksList: function()
		{
			var request =
			{
				method: "GET",
				url: API['NETWORKS'],
				headers: 
				{
					"Content-Type": "application/json",
					"Cookie": "session_id="+$cookies.get("session_id")
				}
			};
			
			$http(request).success(function(data)
			{
				return data;
			})
			.error(function(err) {
				return err;
			});
		}
	}
}]);