package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAX {

	private File ficheroEntrada() {
		String rutaDirectorio = System.getProperty("user.dir");
		String rutaFichero = rutaDirectorio + File.separator + "src" + File.separator+ "resources" +File.separator + "entrada.xml";
		File ficheroEntrada = new File(rutaFichero);
		return ficheroEntrada;
	}
	
	public ArrayList<Jugador> leerXML() {
		SAXParserFactory SAXParserFactory = javax.xml.parsers.SAXParserFactory.newInstance();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			SAXParser saxParser = SAXParserFactory.newSAXParser();
			JugadoresHandler jugadoresHandler = new JugadoresHandler();
			try {
				saxParser.parse(ficheroEntrada(), jugadoresHandler);
				jugadores = jugadoresHandler.getJugadores();
				for (Jugador j: jugadores) {
					System.out.println(j);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return jugadores;
	}
}


