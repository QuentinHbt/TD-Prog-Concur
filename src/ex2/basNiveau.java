package Ex2;

import java.util.Arrays;

public class basNiveau extends AbstractFileBloquanteBornee<Object> {

	public basNiveau(int n) throws IllegalArgumentException {
		super(n);
	}

	@Override
	public synchronized void deposer(Object e) throws InterruptedException {

		if (!estPleine) {
			if(queue == tableau.length -1){
				tableau[queue] = e;
			}
			if(queue == 0 ){
				tableau[queue] = e;
				queue ++;
			}
			else
				tableau[++queue] = e;
			estVide = false;
			System.out.println("Déposer");
			if (queue == tableau.length) {
				estPleine = true;
			}

		} else {
			TestBNRetrait.liberer();
			this.wait();
		}

	}

	/**
	 * Prendre une référence dans la file.
	 * 
	 * La prise est faite en tête de file. L'objet référencé n'est pas copié au
	 * moment du dépôt. La prise est bloquante lorsque la file est vide.
	 * 
	 * returns la référence de tête de la file
	 */
	@Override
	public synchronized Object prendre() throws InterruptedException {
		Object retour = null;
		if (!estVide) {
			retour = tableau[tete];
			tableau[tete] = null;
			System.out.println("Prendre");
			tete++;
			if (estPleine) {
				estPleine = false;
			}
			if (tete == queue) {
				estVide = true;
			}

		} else {
			TestBNDepot.liberer();
			this.wait();
		}
		return retour;
	}

	@Override
	public String toString() {
		return "basNiveau [tableau=" + Arrays.toString(tableau) + ", tete="
				+ tete + ", queue=" + queue + ", estVide=" + estVide
				+ ", estPleine=" + estPleine + "]";
	}

}
