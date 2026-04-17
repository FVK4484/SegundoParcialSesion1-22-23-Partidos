package fp.futbol.test;

import java.util.List;
import java.util.Set;

import fp.futbol.EstadisticasPartidos;
import fp.futbol.FactoriaPartidos;
import fp.futbol.Partido;

public class TestEstadisticasPartidos {

	public static void main(String[] args) {
		
		List<Partido> listaPartidos = FactoriaPartidos.leerPartidos("./data/CSV de la sesión 1.txt");
		EstadisticasPartidos est = new EstadisticasPartidos(listaPartidos.stream());
		
		testGetNumGolesEquipos(est, Set.of("Sevilla", "RealBetis"));
		testGetNumGolesEquipos(est, Set.of("RealMadrid", "Barcelona"));
		
		testGetNumGolesDespuesMinuto(est, 90);
		testGetNumGolesDespuesMinuto(est, 100);
		
		testGetNPartidosMasEspectadores(est, 3);
		testGetNPartidosMasEspectadores(est, 5);
		
		testGetCompeticionMasEspectadores(est, 1);
		testGetCompeticionMasEspectadores(est, 10);
		
	}
	
	private static void testGetNumGolesEquipos(EstadisticasPartidos est, Set<String> equipos) {
		System.out.println(" EJERCICIO 4.1===================================================================");
		System.out.println(" El número total de goles de los equipos " + equipos + " es " + est.getNumGolesEquipos(equipos));
	}
	
	private static void testGetNumGolesDespuesMinuto(EstadisticasPartidos est, Integer minutoUmbral) {
		System.out.println(" EJERCICIO 4.2===================================================================");
		System.out.println(" El número total de goles después del minuto " + minutoUmbral + " es " + est.getNumGolesDespuesMinuto(minutoUmbral));
	}
	
	private static void testGetNPartidosMasEspectadores(EstadisticasPartidos est, Integer n) {
		System.out.println(" EJERCICIO 4.3===================================================================");
		System.out.println(" Los " + n + " partidos con más espectadores son");
		for (Partido partido : est.getNPartidosMasEspectadores(n)) {
			System.out.println("    " + partido);
		}
	}
	
	private static void testGetCompeticionMasEspectadores(EstadisticasPartidos est, Integer mes) {
		System.out.println(est.getCompeticionMasEspectadores(mes));
	}

}