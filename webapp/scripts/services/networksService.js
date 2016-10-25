app.factory('networksService', ['$http', '$cookies', function($http, $cookies) {
	var exposedAPI = 
	{
		getParticularNetwork: getParticularNetwork,
		getNetworksList: getNetworksList,
		deleteNetwork: deleteNetwork
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
				"Content-Type": "application/json"
			}
		};
		
		return $http(request).success(function(data)
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
			}
		};

		return $http(request).success(function(data) {
			return data;
		})
		.error(function(err) {
			return err;
		});
	}
	
	function deleteNetwork(id)
	{
		var request = 
		{
			method: "DELETE",
			url: API['NETWORK']+'/'+id.toString()
		}
		
		return $http(request).success(function(data) {
			return data;
		})
		.error(function(err) {
			return err;
		});
	}

	function runNetwork(id)
	{
		
	}
}]);
