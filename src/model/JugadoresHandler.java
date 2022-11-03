package model;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// Heredamos de la clase DefaultHandler
public class JugadoresHandler extends DefaultHandler {

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private Jugador jugador;
	// Objeto para leer los elementos de texto
	private StringBuilder stringBuilder = new StringBuilder();

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	// sobreescribimos los metodos startElement, endElement y characters de
	// DefaultHandler

	/**
	 * Método para reocnocer etiquetas de apertura del xml
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		/**
		 * El parámetro "qName" contiene el nombre de cada elemento. Con el switch
		 * hacemos que dependiendo de el nombre del elemento se ejecute el código dentro
		 * del switch
		 */
		switch (qName) {
		case "partida":
			// Instanciamos jugador y lo guardamos en el Arraylist de jugadores
			jugador = new Jugador();
			jugadores.add(jugador);
			// Con el parámetro attributes hacemos un set del atributo que tiene partida y
			// con "getValue" cogemos su valor. En este caso, el nombre de los jugadores
			jugador.setNombre(attributes.getValue("jugador"));
			break;
		case "puntuacion":
			// Vaciamos el buffer para cuando lleguemos la texto en la funcion characters
			stringBuilder.delete(0, stringBuilder.length());
			break;
		case "pantalla":
			// Vaciamos el buffer para cuando lleguemos la texto en la funcion characters
			stringBuilder.delete(0, stringBuilder.length());
			// Con el parámetro attributes hacemos un set del atributo que tiene pantalla y
			// con "getValue" cogemos su valor. En este caso, el estado de la partida
			jugador.setEstado(attributes.getValue("estado"));
			break;
		}

	}

	/**
	 * Método para reconocer etiquetas de cierre del xml
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		/**
		 * El parámetro "qName" contiene el nombre de cada elemento. Con el switch
		 * hacemos que dependiendo de el nombre del elemento se ejecute el código dentro
		 * del switch
		 */
		switch (qName) {
		case "puntuacion":
			// Hacemos un set del texto con la puntuación. En este caso, como es un entero
			// hacemos un parseInt para convertir el String
			jugador.setPuntuacion(Integer.parseInt(stringBuilder.toString()));
			break;
		case "pantalla":
			jugador.setPantalla(stringBuilder.toString());
			break;
		}
	}

	/**
	 * Método para reconocer etiquetas de texto del xml
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		stringBuilder.append(ch, start, length);
	}

}
