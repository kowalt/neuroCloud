app
.factory('localizedMessageService', ['$cookies', function($cookies) {
	return
	{
		getDropdown: function()
		{
			return [
				{text: 'English', click: '$cookies.put("language","en")'},
				{text: 'Polish', click: '$cookies.put("language","pl")'}
			  ];
		},
		
		getLocalizedMessage: function(messageCode)
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
		},
		
		getEnValue: function(messageCode)
		{
			switch(messageCode)
			{
				case "settings.header":
					return "SETTINGS";
			}
			
			return "Localization service error getting English value";
		},

		getPlValue: function(messageCode)
		{
			switch(messageCode)
			{
				case "settings.header":
					return "USTAWIENIA"
			}

			return "Localization service error getting Polish value";
		}
	}
}]);