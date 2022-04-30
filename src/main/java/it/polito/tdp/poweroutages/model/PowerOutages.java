package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages {
	
	private int id;
	private Nerc nerc;
	private int customerAffected;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private long durata;
	private int anno;
	
	public PowerOutages(int id, Nerc nerc, int customerAffected, LocalDateTime inizio, LocalDateTime fine) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.customerAffected = customerAffected;
		this.inizio = inizio;
		this.fine = fine;
		LocalDateTime dataInizio = LocalDateTime.from(inizio);
		this.durata = dataInizio.until(fine, ChronoUnit.MINUTES);
		this.anno = inizio.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomerAffected() {
		return customerAffected;
	}

	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}

	public long getDurata() {
		return durata;
	}

	public void setDurata(long durata) {
		this.durata = durata;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
