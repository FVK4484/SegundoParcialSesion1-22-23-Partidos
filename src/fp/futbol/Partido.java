package fp.futbol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Partido implements Comparable<Partido> {
	
	private LocalDate fechaPartido;
	private String equipoLocal;
	private String equipoVisitante;
	private Long numEspectadores;
	private TipoCompeticion competicion;
	private List<Resultado> resultadosParciales;
	
	public Partido(LocalDate fechaPartido, String equipoLocal, String equipoVisitante, Long numEspectadores,
			TipoCompeticion competicion, List<Resultado> resultadosParciales) {
		
		Checkers.check("Los minutos de los resultados parciales deben estar ordenados.",
				checkMinutos(resultadosParciales));
		this.fechaPartido = fechaPartido;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.numEspectadores = numEspectadores;
		this.competicion = competicion;
		this.resultadosParciales = new ArrayList<>(resultadosParciales);
		
	}

	public LocalDate getFechaPartido() {
		return fechaPartido;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public Long getNumEspectadores() {
		return numEspectadores;
	}

	public TipoCompeticion getCompeticion() {
		return competicion;
	}

	public List<Resultado> getResultadosParciales() {
		return new ArrayList<>(resultadosParciales);
	}
	
	public Boolean checkMinutos(List<Resultado> resultadosParciales) {
		Boolean res = true;
		if (resultadosParciales.size() > 1) {
			for (int i = 0; i < resultadosParciales.size() - 1; i++) {
				if (resultadosParciales.get(i).minuto() > resultadosParciales.get(i + 1).minuto()) {
					res = false;
					break;
				}
			}
		} 
		return res;
	}
	
	public Integer getGolesLocal() {
		return resultadosParciales.getLast().golesLocal();
	}
	
	public Integer getGolesVisitante() {
		return resultadosParciales.getLast().golesVisitante();
	}
	
	public List<Integer> getMinutos() {
		List<Integer> minutos = new ArrayList<>();
		for (Resultado resultado : resultadosParciales) {
			minutos.add(resultado.minuto());
		}
		return minutos;
	}

	public String toString() {
		return "Partido [fechaPartido=" + fechaPartido + ", equipoLocal=" + equipoLocal + ", equipoVisitante="
				+ equipoVisitante + ", numEspectadores=" + numEspectadores + ", competicion=" + competicion
				+ ", resultadosParciales=" + resultadosParciales + "]";
	}

	public int hashCode() {
		return Objects.hash(equipoLocal, equipoVisitante, fechaPartido);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(equipoLocal, other.equipoLocal) && Objects.equals(equipoVisitante, other.equipoVisitante)
				&& Objects.equals(fechaPartido, other.fechaPartido);
	}

	public int compareTo(Partido p) {
		Integer res = fechaPartido.compareTo(p.fechaPartido);
		if (res == 0) {
			res = equipoLocal.compareTo(p.equipoLocal);
			if (res == 0) {
				res = equipoVisitante.compareTo(p.getEquipoVisitante());
			}
		}
		return res;
	}
		
}
