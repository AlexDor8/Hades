package main;

import controller.Manager;
import model.SAX;

public class Hades {

	public static void main(String[] args) {
		SAX SAX = new SAX();
		SAX.leerXML();
		Manager.getInstance();
	}

}
