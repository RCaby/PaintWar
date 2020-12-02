package com.paintwar.client.view.pages;


public enum PageName {
	CONNEXION_CHOICE("ConnexionChoice"), LOG_IN("LogIn"), SIGN_IN("SignIn"), PARAMETERS("Parameters"), GUEST("Guest"),
	HOME("Home"), COLLECTION("Collection"), BATTLEPASS("Battlepass"), SHOP("Shop");

	final String name;

	private PageName(String name) {
		this.name = name;
	}
}