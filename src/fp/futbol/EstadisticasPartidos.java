package fp.futbol;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstadisticasPartidos {
	
	private List<Partido> partidos;
	private Integer numPartidos;
	
	public EstadisticasPartidos(Stream<Partido> partidosStream) {
		this.partidos = partidosStream.collect(Collectors.toList());
		this.numPartidos = partidos.size();
	}

	public List<Partido> getPartidos() {
		return new ArrayList<>(partidos);
	}

	public Integer getNumPartidos() {
		return numPartidos;
	}

	public String toString() {
		return partidos.stream()
				.map(Partido::toString)
				.collect(Collectors.joining("\n"));
	}

	public int hashCode() {
		return Objects.hash(partidos);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasPartidos other = (EstadisticasPartidos) obj;
		return Objects.equals(partidos, other.partidos);
	}
	
	// 1. Integer getNumGolesEquipos(Set<String> equipos): dado un 
	// conjunto de equipos, devuelve el total de goles marcados por 
	// esos equipos en todas las competiciones. Implemente este método 
	// con bucles. (1 pto)
	public Integer getNumGolesEquipos(Set<String> equipos) {
		Integer goles = 0;
		for (Partido partido : partidos) {
			if (equipos.contains(partido.getEquipoLocal())) {
				goles = goles + partido.getGolesLocal();
			} else if (equipos.contains(partido.getEquipoVisitante())) {
				goles = goles + partido.getGolesVisitante();
			}
		}
		return goles;
	}
	
	// 2. Integer getNumGolesDespuesMinuto(Integer minutoUmbral): dado un 
	// valor minutoUmbral de tipo Integer, devuelve el número de goles marcados 
	// con posterioridad a ese minuto en todos los partidos. Tenga en cuenta 
	// que en cada resultado parcial se marca un solo gol. (1 pto)
	public Integer getNumGolesDespuesMinuto(Integer minutoUmbral) {
		Long goles = partidos.stream()
				.flatMap(p -> p.getResultadosParciales().stream())
				.filter(r -> r.minuto() > minutoUmbral)
				.count();
		return goles.intValue();
	}
	
	// 3. SortedSet<Partido> getNPartidosMasEspectadores(Integer n): dado un 
	// número entero n, devuelve un SortedSet con los n partidos con más espectadores. 
	// El conjunto estará ordenado por número de goles de menor a mayor. Tenga en cuenta 
	// que, si hay dos partidos con el mismo número de goles, ambos deberían incluirse 
	// en el conjunto resultado. (1,5 ptos)
	public SortedSet<Partido> getNPartidosMasEspectadores(Integer n) {
		return partidos.stream()
				.sorted(Comparator.comparing(Partido::getNumEspectadores).reversed())
				.limit(n)
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing((Partido p) -> p.getResultadosParciales().size())
						.thenComparing(Comparator.naturalOrder()))));
	}
	
	// 4. Competicion getCompeticionMasEspectadores(Integer mes): dado un entero 
	// mes que representa un mes, devuelve la competición con más espectadores 
	// acumulados en ese mes. Si no se puede calcular, se eleva NoSuchElementException. (1,5 ptos)
	public TipoCompeticion getCompeticionMasEspectadores(Integer mes) {
		return partidos.stream()
				.filter(p -> p.getFechaPartido().getMonthValue() == mes)
				.collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingLong(Partido::getNumEspectadores)), 
						p -> p.orElseThrow(NoSuchElementException::new).getCompeticion()));
				
	}
	
}
