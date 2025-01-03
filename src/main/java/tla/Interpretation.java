package tla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Interpretation {

	// permet la lecture de chaîne au clavier
	private static BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
	private HashMap<String, Double>  m;

	public Interpretation() {
		/* A COMPLETER */
		m = new HashMap<String, Double>();
	}

	/*
	interprete le noeud n
	et appel récursif sur les noeuds enfants de n
	 */
	public Double interpreter(Noeud n) {
		if (n.getTypeDeNoeud() == TypeDeNoeud.intv) {
			return Double.parseDouble(n.getValeur());
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.add) {
			return interpreter(n.enfant(0)) + interpreter(n.enfant(1));
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.sub) {
			return interpreter(n.enfant(0)) - interpreter(n.enfant(1));
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.kPow) {
			return Math.pow(interpreter(n.enfant(0)), interpreter(n.enfant(1)));
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.multiply) {
			return interpreter(n.enfant(0)) * interpreter(n.enfant(1));
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.ident) {
			if (!m.containsKey(n.getValeur())) {
				throw new IllegalStateException("Identifiant non défini : " + n.getValeur());
			}
			return m.get(n.getValeur());
		}
		if (n.getTypeDeNoeud() == TypeDeNoeud.doublev) {
			return Double.parseDouble(n.getValeur());
		}
		return null; // Retour par défaut (ne devrait jamais être atteint)
	}


}
