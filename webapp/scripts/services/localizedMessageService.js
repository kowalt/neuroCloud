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
			case "generate.header":
				return "Generate";
			case "generate.name":
				return "Name";
			case "generate.npl":
				return "Neurons per layer";
			case "generate.af":
				return "Activation function";
			case "generate.generateButton":
				return "Generate";
			case "generate.tooltip.tooltipNPL.title":
				return "Insert numbers separated by spacebar e.g. 4 3 2 2. Every number defines the amount of neurons on the corresponding layer.";
			case "generate.tooltip.tooltipAF.title":
				return "Insert the activation function and domain rules in format function<=>domain_rule1,domain_rule2. Domain rules are optional.";
			case "generate.alert.generatedOK.title":
				return "Network generated";
			case "generate.alert.generatedOK.content":
				return "Network generation successfull";
			case "generate.alert.generatedNOK.title":
				return "Unable to generate: ";
			case "generate.alert.validationErrorMessage.nameMustNotBeEmpty":
				return "Name must not be empty";
			case "generate.alert.validationErrorMessage.incorrectAmountOfNeurons":
				return "Incorrect amount of neurons";
			case "generate.alert.validationErrorMessage.title":
				return "Cannot validate: ";
			case "export.header":
				return "Export";
			case "export.downloadButton":
				return "Download";
			case "training.header":
				return "Training with EBP algorithm";
			case "training.lc":
				return "Learning coefficient";
			case "training.inputSetFile":
				return "Input";
			case "training.outputSetFile":
				return "Output";
			case "training.iterations":
				return "Iterations";
			case "training.trainingButton":
				return "Train";
			case "training.tooltip.title":
				return "File with floating-point values partitioned by comma. Vectors' separator is crlf";
			case "training.alert.unableCheckCurrentlyTrained.title":
				return "Unable to check if network is currently trained";
			case "training.alert.sentOK.title":
				return "Network sent for training";
			case "training.alert.sentNOK.title":
				return "Unable to train network";
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
			case "generate.header":
				return "Generuj nową, losową sieć";
			case "generate.name":
				return "Nazwa";
			case "generate.npl":
				return "Neurony na warstwę";
			case "generate.af":
				return "Funkcja aktywacji";
			case "generate.generateButton":
				return "Generuj";
			case "generate.tooltip.tooltipNPL.title":
				return "Wstaw liczby naturalne odseparowane spacją, np. \"4 3 2 2\". Każda z tych liczb określa ilość neuronów na odpowiadającej warstwie.";
			case "generate.tooltip.tooltipAF.title":
				return "Wstaw funkcje aktywacji i reguły domenowe w formacie \"funkcja<=>reguła_1,reguła_2,...reguła_n\". Reguły domenowe są opcjonalne.";
			case "generate.alert.generatedOK.title":
				return "Wygenerowano sieć";
			case "generate.alert.generatedOK.content":
				return "Generacja sieci przebiegła pomyślnie";
			case "generate.alert.generatedNOK.title":
				return "Nie można wygenerować sieci: ";
			case "generate.alert.validationErrorMessage.nameMustNotBeEmpty":
				return "Nazwa nie może być pusta";
			case "generate.alert.validationErrorMessage.incorrectAmountOfNeurons":
				return "Nieprawidłowa liczba neuronów";
			case "generate.alert.validationErrorMessage.title":
				return "Błąd walidacji: ";
			case "export.header":
				return "Eksport";
			case "export.downloadButton":
				return "Pobierz";
			case "training.header":
				return "Trening algorytmem propagacji wstecznej";
			case "training.lc":
				return "Współczynnik uczenia";
			case "training.inputSetFile":
				return "Plik z danymi wejściowymi";
			case "training.outputSetFile":
				return "Plik z danymi wyjściowymi";
			case "training.iterations":
				return "Liczba iteracji";
			case "training.trainingButton":
				return "Trenuj";
			case "training.tooltip.title":
				return "Plik z liczbami zmiennoprzecinkowymi odseparowanymi przecinkiem. Separatorem poszczególnych wektorów jest crlf.";
			case "training.alert.unableCheckCurrentlyTrained.title":
				return "Nie można sprawdzić, czy sieć jest obecnie trenowana";
			case "training.alert.sentOK.title":
				return "Sieć wysłana do treningu";
			case "training.alert.sentNOK.title":
				return "Nie można wytrenować sieci";			
		}

		return "Localization service error getting Polish value";
	}
}]);