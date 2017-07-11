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
			{text: 'English', click: 'setLangCode("en")'},
			{text: 'Polski', click: 'setLangCode("pl")'}
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
			case "login.signIn":
				return "Sign in";
			case "login.login":
				return "Login";
			case "login.password":
				return "Password";
			case "login.rememberMe":
				return "Remember me";
			case "login.createNewAccount":
				return "Create new account";
			case "login.alert.unableToSignIn.title":
				return "Unable to sign in: ";
			case "register.signUp":
				return "Sign up";
			case "register.email":
				return "Email";
			case "register.login":
				return "Login";
			case "register.password":
				return "Password";
			case "register.confirmPassword":
				return "Confiirm password";
			case "register.enterTheMessageForAdminHere":
				return "Enter the message for the admin here...";
			case "register.register":
				return "Register";
			case "register.backToTheLoginPrompt":
				return "Go back to the login prompt";				
			case "register.alert.dataSubmitOK.title":
				return 'Data has been submitted successfully.';
			case "register.alert.dataSubmitOK.content":
				return 'We will look at your request';
			case "register.alert.dataSubmitNOK.title":
				return 'Cannot register: ';
			case "register.alert.errorMessagePasswordsDM":
				return "Passwords doesn't match!";
			case "navbar.dropdown.load":
				return "Load...";
			case "navbar.dropdown.save":
				return "Save";
			case "navbar.dropdown.saveas":
				return "Save as...";
			case "navbar.dropdown.delete":
				return "Delete";
			case "navbar.alert.deleteOK.title":
				return "Network deleted";
			case "navbar.alert.deleteOK.content":
				return "Network deleted successfully";
			case "navbar.alert.deleteNOK.title":
				return "Unable to delete network";	
			case "load.loadViewHeader":
				return "Select network to load";
			case "load.createdIn":
				return "created in";
			case "load.alert.loadListNOK.title":
				return "Unable to load the list of networks";
			case "logout.alert.loggedOut.title":
				return "Logged out";
			case "saveas.header":
				return "Save As";
			case "saveas.name":
				return "new name";
			case "saveas.saveButton":
				return "Save";
			case "workbench.input":
				return "Inputs";
			case "workbench.output":
				return "Outputs";
			case "workbench.upload":
				return "Upload"
			case "workbench.download":
				return "Download";
			case "workbench.runButton":
				return "Run";
			case "workbench.alert.runNOK.title":
				return "Unable to run network";
			case "workbench.alert.tooBig":
				return "Network loaded, but it is too big to be displayed";
			case "workbench.alert.reloadNOK":
				return "Unable to load network";
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
			case "login.signIn":
				return "Zaloguj";
			case "login.login":
				return "Login";
			case "login.password":
				return "Hasło";
			case "login.rememberMe":
				return "Zapamiętaj";
			case "login.createNewAccount":
				return "Stwórz nowe konto";	
			case "login.alert.unableToSignIn.title":
				return "Bład logowania: ";
			case "register.signUp":
				return "Rejestracja nowego konta";
			case "register.email":
				return "Email";
			case "register.login":
				return "Login";
			case "register.password":
				return "Hasło";
			case "register.confirmPassword":
				return "Potwierdzenie hasła";
			case "register.enterTheMessageForAdminHere":
				return "Wpisz tutaj wiadomość dla administratora...";
			case "register.register":
				return "Zarejestruj";
			case "register.backToTheLoginPrompt":
				return "Wróć do ekranu logowania";				
			case "register.alert.dataSubmitOK.title":
				return 'Dane zostały wysłane pomyślnie';
			case "register.alert.dataSubmitOK.content":
				return 'Podanie zostanie rozpatrzone';
			case "register.alert.dataSubmitNOK.title":
				return 'Nie można zarejestrować: ';
			case "register.alert.errorMessagePasswordsDM":
				return "Podane hasła nie zgadzają się";
			case "navbar.dropdown.load":
				return "Ładuj...";
			case "navbar.dropdown.save":
				return "Zapisz";
			case "navbar.dropdown.saveas":
				return "Zapisz jako...";
			case "navbar.dropdown.delete":
				return "Usuń";
			case "navbar.alert.deleteOK.title":
				return "Usunięcie sieci";
			case "navbar.alert.deleteOK.content":
				return "Sieć została usunięta pomyślnie";
			case "navbar.alert.deleteNOK.title":
				return "Nie można usunąć sieci";	
			case "load.loadViewHeader":
				return "Wybierz sieć";
			case "load.createdIn":
				return "utworzono";
			case "load.alert.loadListNOK.title":
				return "Nie można pobrać listy sieci";
			case "logout.alert.loggedOut.title":
				return "Wylogowano";
			case "saveas.header":
				return "Zapisz jako";
			case "saveas.name":
				return "nowa nazwa";
			case "saveas.saveButton":
				return "Zapisz";
			case "workbench.input":
				return "Wejścia";
			case "workbench.output":
				return "Wyjścia";
			case "workbench.upload":
				return "Wstaw wartości wejść"
			case "workbench.download":
				return "Pobierz wartości wyjść";
			case "workbench.runButton":
				return "Uruchom";
			case "workbench.alert.runNOK.title":
				return "Nie można uruchomić sieci";
			case "workbench.alert.tooBig":
				return "Sieć została załadowana, ale jest zbyt duża by mogła zostać wyświetlona";
			case "workbench.alert.reloadNOK":
				return "Nie można załadować sieci";
		}

		return "Localization service error getting Polish value";
	}
}]);