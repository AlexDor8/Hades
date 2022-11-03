package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.DOM;
import model.Reader;
import model.SAX;

public class Manager {

	private static Manager manager;

	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	// Constructor privado
	private Manager() {
		SAX SAX = new SAX();
		Reader reader = new Reader();
		reader.lecturaPantallas();
		DOM dom = new DOM();
		dom.generarDocument(SAX.leerXML(), reader.lecturaPantallas());
		dom.generarXML();
	}

}
