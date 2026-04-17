package fp.futbol;

import fp.utiles.Checkers;

public record Resultado(Integer minuto, Integer golesLocal, 
		Integer golesVisitante) {
	
	public Resultado {
		Checkers.check("Los minutos deben ser mayores o iguales que cero.", 
				minuto >= 0);
		Checkers.check("Los goles del equipo local deben ser mayores o iguales que cero.", 
				golesLocal >= 0);
		Checkers.check("Los goles del equipo visitante deben ser mayores o iguales que cero.", 
				golesVisitante >= 0);
	}
	
}
