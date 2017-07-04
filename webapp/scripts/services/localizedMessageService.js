app
.factory('localizedMessageService', '$cookies' [function($cookies) {
	var langCode = $cookies.get('language');
	
	function getDropdown()
	{
		return [
			{text: 'English', click: '$cookies.put("language","en")'},
			{text: 'Polish', click: '$cookies.put("language","pl")'}
		  ];
	}
	
	function getLocalizedMessage(messageCode)
	{
		switch(langCode)
		{
			case "en":
				return getEnValue(messageCode);
				break;
			case "pl":
				return getPlValue(messageCode);
				break;
		}
		
		return "Localization service error";
	}
	
	function getEnValue(messageCode)
	{
		switch(messageCode)
		{
			case "settings.header":
				return "SETTINGS";
		}
		
		return "Localization service error getting English value";
	}

	function getPlValue(messageCode)
	{
		switch(messageCode)
		{
			case "settings.header":
				return "USTAWIENIA"
		}
		
		return "Localization service error getting Polish value";
	}
}]);