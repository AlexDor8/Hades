package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	ArrayList<String[]> pantallas = new ArrayList<String[]>();

	/**
	 * 
	 * @return devolvemos el fichero con las pantallas
	 */
	private File ficheroPantallas() {
		String rutaDirectorio = System.getProperty("user.dir");
		String rutaFichero = rutaDirectorio + File.separator + "src" + File.separator + "resources" + File.separator
				+ "pantallas.txt";
		File ficheroPantallas = new File(rutaFichero);
		return ficheroPantallas;
	}

	/**
	 * Leemeos el fichero con las pantallas y lo guardamos en un Arraylist que
	 * contendrĂ¡ arrays de Strings, esto lo haremos para luego recorrerlo como una
	 * matriz
	 * 
	 * @return devolvemos el Arraylist donde hemos almacenado las pantallas
	 */
	public ArrayList<String[]> lecturaPantallas() {
		BufferedReader br = null;
		int i = 0;
		try {
			String linea = "";
			br = new BufferedReader(new FileReader(ficheroPantallas()));
			// Leemos el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				// Separamos las lineas que hemos leido praviamente y guardamos cada elemento en
				// un ArrayList
				String[] pantallaElemento = linea.split(" ");
				pantallas.add(pantallaElemento);
				for (int j = 0; j < pantallaElemento.length; j++) {

					// System.out.print(pantallas.get(i)[j]);
				}
				// System.out.println("");
				i++;

			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		}
		return pantallas;
	}

}
