package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

	public void generarDocument(ArrayList<Jugador> jugadores, ArrayList<String[]> niveles) {
		Element pantallas = document.createElement("pantallas");
		document.appendChild(pantallas);

		for (int i = 0; i < jugadores.size(); i++) {
			Element pantalla = document.createElement("pantalla");
			pantallas.appendChild(pantalla);
			pantalla.setAttribute("jugador", jugadores.get(i).getNombre());
			pantalla.setAttribute("nivel", jugadores.get(i).getPantalla());

			for (int ii = 0; ii < niveles.size(); ii++) {
				for (int j = 0; j < niveles.get(ii).length; j++) {
					if (jugadores.get(i).getPantalla().equals(niveles.get(ii)[j])) {
						for (int iii = ii + 1; iii < niveles.size(); iii++) {
							for (int jj = j; jj < niveles.get(iii).length; jj++) {
								if (niveles.get(iii)[jj].contains("#")) {
									iii = niveles.size() - 1;
									break;
								}
								Element pixel = document.createElement("pixel");
								pixel.appendChild(document.createTextNode(niveles.get(iii)[jj]));
								pantalla.appendChild(pixel);
								pixel.setAttribute("col", String.valueOf(jj));
								pixel.setAttribute("fil", String.valueOf(iii));

							}
						}

					}

				}
			}
		}

	}

	public void generarXML() {
		TransformerFactory factoria = TransformerFactory.newInstance();
		try {
			Transformer transformer = factoria.newTransformer();
			Source source = new DOMSource(document);
			FileWriter fw;
			try {
				fw = new FileWriter(ficheroSalida());
				PrintWriter pw = new PrintWriter(fw);
				Result result = new StreamResult(pw);
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}

	private File ficheroSalida() {
		String rutaDirectorio = System.getProperty("user.dir");
		String rutaFichero = rutaDirectorio + File.separator + "src" + File.separator + "resources" + File.separator
				+ "salida1.xml";
		File ficheroSalida = new File(rutaFichero);
		return ficheroSalida;
	}

}
