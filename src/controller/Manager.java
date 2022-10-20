package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
	
	private static Manager manager;
	
	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
 	}
	
	private Manager() {
		lecturaPantallas();
	}

	ArrayList<String[]> pantallas = new ArrayList<String[]>();

	private  File ficheroPantallas() {
		String rutaDirectorio = System.getProperty("user.dir");
		String rutaFichero = rutaDirectorio + File.separator + "src" + File.separator + "resources" + File.separator
				+ "pantallas.txt";
		File ficheroPantallas = new File(rutaFichero);
		return ficheroPantallas;
	}

	private void lecturaPantallas() {
		BufferedReader br = null;
		int i = 0;
		try {
			String linea = "";
			br = new BufferedReader(new FileReader(ficheroPantallas()));
			while ((linea = br.readLine()) != null) {

				String[] pantallaElemento = linea.split(" ");
				pantallas.add(pantallaElemento);
				for (int j = 0; j < pantallaElemento.length; j++) {

					System.out.print(pantallas.get(i)[j]);
				}
				System.out.println("");
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
	}

}
