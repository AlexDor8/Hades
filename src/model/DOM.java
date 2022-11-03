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

	/**
	 * 
	 * @param jugadores ArrayList de jugadores que hemos leído y almacenado en la
	 *                  clase SAX
	 * @param niveles   ArrayList con las pantallas que hemos leído y almacenado en
	 *                  la clase Reader
	 */
	public void generarDocument(ArrayList<Jugador> jugadores, ArrayList<String[]> niveles) {
		Element pantallas = document.createElement("pantallas");
		// Nodo raiz
		document.appendChild(pantallas);
		// Creamos tantas pantallas como jugadores haya (jugadores.size)
		for (int i = 0; i < jugadores.size(); i++) {
			Element pantalla = document.createElement("pantalla");
			pantallas.appendChild(pantalla);
			pantalla.setAttribute("jugador", jugadores.get(i).getNombre());
			pantalla.setAttribute("nivel", jugadores.get(i).getPantalla());

			// Recorreomos la martriz donde están almacenadas las pantallas
			for (int ii = 0; ii < niveles.size(); ii++) {
				for (int j = 0; j < niveles.get(ii).length; j++) {
					// Si la pantalla del jugador coincide con un nivel de las pantallas recorremos
					// ese nivel y lo escribimos en nuestro documento
					if (jugadores.get(i).getPantalla().equals(niveles.get(ii)[j])) {
						// Empezamos el bucle en uno para no coger el nivel y empezar por el contenido
						// de este. Por ejemplo, no coger #1
						for (int iii = ii + 1; iii < niveles.size(); iii++) {
							for (int jj = j; jj < niveles.get(iii).length; jj++) {
								// Si hemos llegado al siguiente nivel, detenemos el bucle
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

	/**
	 * A partir de nuestro documento "document" creamos un fichero xml
	 */
	public void generarXML() {
		TransformerFactory factoria = TransformerFactory.newInstance();
		try {
			Transformer transformer = factoria.newTransformer();
			Source source = new DOMSource(document);
			FileWriter fw;
			try {
				// Fichero de salida (destino)
				fw = new FileWriter(ficheroSalida());
				PrintWriter pw = new PrintWriter(fw);
				Result result = new StreamResult(pw);
				try {
					// Recibe el origen (nuestro documento "document") y el destino (nuestro fichero xml)
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

	/**
	 * 
	 * @return devolvemos el fichero xml donde escribiremos todos los datos de las
	 *         pantallas y los jugadores
	 */
	private File ficheroSalida() {
		String rutaDirectorio = System.getProperty("user.dir");
		String rutaFichero = rutaDirectorio + File.separator + "src" + File.separator + "resources" + File.separator
				+ "salida1.xml";
		File ficheroSalida = new File(rutaFichero);
		return ficheroSalida;
	}

}
