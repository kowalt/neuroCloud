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
			case "settings.selectLanguage":
				return "Select language";
			case "navbar.networks":
				return "Networks";
			case "navbar.workspace":
				return "Workspace";
			case "navbar.generate":
				return "Generate";
			case "navbar.export":
				return "Export";
			case "navbar.training":
				return "Training";
			case "navbar.settings":
				return "Settings";
			case "navbar.logout":
				return "Logout";
		}

		return "Localization service error getting English value";
	}

	function getPlValue(messageCode)
	{
		switch(messageCode)
		{
			case "settings.header":
				return "Ustawienia";
			case "settings.selectLanguage":
				return "Wybierz język";
			case "navbar.networks":
				return "Sieci";
			case "navbar.workspace":
				return "Przestrzeń robocza";
			case "navbar.generate":
				return "Generuj";
			case "navbar.export":
				return "Eksport";
			case "navbar.training":
				return "Trening";
			case "navbar.settings":
				return "Ustawienia";
			case "navbar.logout":
				return "Wyloguj";
		}

		return "Localization service error getting Polish value";
	}
}]);