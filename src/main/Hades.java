package main;

import controller.Manager;
import model.DOM;
import model.SAX;

public class Hades {

	public static void main(String[] args) {
		SAX SAX = new SAX();
		//Manager.getInstance();
		//Manager.lecturaPantallas();
		DOM dom = new DOM();
		dom.generarDocument(SAX.leerXML(),Manager.lecturaPantallas());
		dom.generarXML();
	}

}
