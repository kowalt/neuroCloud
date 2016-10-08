app.factory('networksService', ['$http', '$cookies', function($http, $cookies) {
	var exposedAPI = 
	{
		getParticularNetwork: getParticularNetwork,
		getNetworksList: getNetworksList
	}
	return exposedAPI;

	function getNetworksList()
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
	
	function getParticularNetwork(id)
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
	}
}]);