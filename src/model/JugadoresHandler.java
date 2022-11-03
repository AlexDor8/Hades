package model;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JugadoresHandler extends DefaultHandler {

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private Jugador jugador;
	private StringBuilder stringBuilder = new StringBuilder();

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (qName) {
		case "partida":
			jugador = new Jugador();
			jugadores.add(jugador);
			jugador.setNombre(attributes.getValue("jugador"));
			break;
		case "puntuacion":
			stringBuilder.delete(0, stringBuilder.length());
			break;
		case "pantalla":
			stringBuilder.delete(0, stringBuilder.length());
			jugador.setEstado(attributes.getValue("estado"));
			break;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		switch (qName) {
		case "puntuacion":
			jugador.setPuntuacion(Integer.parseInt(stringBuilder.toString()));
			break;
		case "pantalla":
			jugador.setPantalla(stringBuilder.toString());
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		stringBuilder.append(ch, start, length);
	}

}
