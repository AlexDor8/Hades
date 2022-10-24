package model;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOM {
	private Document document;

	public DOM() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void generarDocument() {
		Element pantallas = document.createElement("pantallas");
		document.appendChild(pantallas);
		
		JugadoresHandler jugadoresHandler = new JugadoresHandler();
		
		ArrayList<Jugador> jugadores = jugadoresHandler.getJugadores();
		
		for (int i = 0;i <jugadores.size();i++) {
			Element pantalla = document.createElement("pantalla");
			pantallas.appendChild(pantalla);
		}
	}
	
	public void generarXML() {
		
	}
	
	
}
