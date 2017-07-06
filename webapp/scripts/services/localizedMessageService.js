app
.factory('localizedMessageService', ['$cookies', function($cookies) {
	var exposedAPI =
	{
		getDropdown: getDropdown,
		getLocalizedMessage: getLocalizedMessage
	}
	
	return exposedAPI;
	
	function getDropdown()
	{
		return [
			{text: '<img src="images/flag-united-kingdom.png" height="12" width="16">English', click: 'setLangCode("en")'},
			{text: '<img src="images/flag-poland.png" height="12" width="16">Polish', click: 'setLangCode("pl")'}
		  ];
	}
	
	function getLocalizedMessage(messageCode)
	{
		var langCode = $cookies.get('language');

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
				return "Settings";
		}
		
		return "Localization service error getting English value";
	}

	function getPlValue(messageCode)
	{
		switch(messageCode)
		{
			case "settings.header":
				return "Ustawienia"
		}

		return "Localization service error getting Polish value";
	}
}]);