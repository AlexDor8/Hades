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
	private SAX SAX;
	private Reader reader;
	private DOM dom;

	// Solo podemos crear una única instancia
	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	// Constructor privado
	private Manager() {
		this.SAX = new SAX();
		this.dom = new DOM();
		this.reader = new Reader();
	}

	public void run() {
		reader.lecturaPantallas();
		dom.generarDocument(SAX.leerXML(), reader.lecturaPantallas());
		dom.generarXML();
	}

}
