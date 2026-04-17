package fp.futbol.test;

import java.util.List;

import fp.futbol.FactoriaPartidos;
import fp.futbol.Partido;

public class TestFactoriaPartidos {

	public static void main(String[] args) {
		
		List<Partido> listaPartidos = FactoriaPartidos.leerPartidos("./data/CSV de la sesión 1.txt");
		
		testPartidos(listaPartidos);
		
	}
	
	private static void testPartidos(List<Partido> listaPartidos) {
		for (Partido partido : listaPartidos) {
			System.out.println(" " + partido);
		}
	}

}
