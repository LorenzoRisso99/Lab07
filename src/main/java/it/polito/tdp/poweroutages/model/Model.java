package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages> result;
	List<PowerOutages> po;
	int maxCustomerAffected = 0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> getPowerOutagesByNerc(Nerc nerc) {
		return podao.getPowerOutagesByNerc(nerc);
	}

	public List<PowerOutages> getSoluzione(int anni, int ore, Nerc nerc) {
		this.result = null;
		po = podao.getPowerOutagesByNerc(nerc);
		List<PowerOutages> parziale = new ArrayList<>();
		search(parziale, anni, ore);
		return result;
	}

	public void search(List<PowerOutages> parziale, int anni, int ore) {
		if (result == null || calcolaCA(parziale) > maxCustomerAffected) {
			maxCustomerAffected = calcolaCA(parziale);
			result = new ArrayList<PowerOutages>(parziale);
		}

		for (PowerOutages p : po) {
			if (!parziale.contains(p)) {
				parziale.add(p);
				if (isValid(anni, ore, parziale)) {
					search(parziale, anni, ore);
				}
				parziale.remove(p);
			}
		}

	}

	public int calcolaCA(List<PowerOutages> parziale) {
		int somma = 0;
		for (PowerOutages p : parziale) {
			somma += p.getCustomerAffected();
		}
		return somma;
	}

	public int calcolaOre(List<PowerOutages> parziale) {
		int ore = 0;
		for (PowerOutages p : parziale) {
			ore += p.getDurata();
		}
		return ore;
	}

	public boolean isValid(int anni, int ore, List<PowerOutages> parziale) {
		int oreOut = calcolaOre(parziale);
		oreOut = oreOut / 60;
		if (oreOut > ore) {
			return false;
		}
		int minAnni = Integer.MAX_VALUE;
		int maxAnni = 0;
		for (PowerOutages p : parziale) {
			if (minAnni > p.getAnno()) {
				minAnni = p.getAnno();
			}
			if (maxAnni < p.getAnno()) {
				maxAnni = p.getAnno();
			}
		}
		if ((maxAnni - minAnni) > anni) {
			return false;
		}
		return true;
	}

}
