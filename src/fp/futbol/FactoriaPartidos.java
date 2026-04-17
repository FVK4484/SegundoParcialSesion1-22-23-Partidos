package fp.futbol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;

public class FactoriaPartidos {
	
	private static Partido parsearPartido(String lineaCSV) {
		Checkers.checkNoNull(lineaCSV);
		String [] trozos = lineaCSV.split(";");
		Checkers.check("La cadena debe tener 6 trozos.", trozos.length == 6);
		LocalDate fechaPartido = LocalDate.parse(trozos[0].trim(), 
				DateTimeFormatter.ofPattern("d/M/y"));
		String equipoLocal = trozos[1].trim();
		String equipoVisitante = trozos[2].trim();
		Long numEspectadores = Long.valueOf(trozos[3].trim());
		TipoCompeticion competicion = TipoCompeticion.valueOf(trozos[4].trim().toUpperCase());
		List<Resultado> resultadosParciales = parsearResultados(trozos[5].trim());
		return new Partido(fechaPartido, equipoLocal, equipoVisitante, numEspectadores, competicion, resultadosParciales);
	}
	
	private static List<Resultado> parsearResultados(String resultadoCSV) {
		List<Resultado> listaResultados = new ArrayList<>();
		String resultadoNuevo = resultadoCSV.replace("[", "").replace("]", "");
		String [] trozos = resultadoNuevo.split(",");
		for (String trozo : trozos) {
			String [] linea = trozo.split("-");
			Integer minuto = Integer.valueOf(linea[0].trim());
			Integer golesLocal = Integer.valueOf(linea[1].trim());
			Integer golesVisitante = Integer.valueOf(linea[2].trim());
			listaResultados.add(new Resultado(minuto, golesLocal, golesVisitante));
		}
		return listaResultados;
	}
	
	public static List<Partido> leerPartidos(String fichero) {
		List<Partido> result = new ArrayList<>();
		try {
			List<String> lineas = Files.readAllLines(Paths.get(fichero));
			lineas.remove(0);
			for (String linea : lineas) {
				result.add(parsearPartido(linea));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
}
