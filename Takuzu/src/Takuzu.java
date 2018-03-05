
public class Takuzu {
	
	private int n;									//taille de la grille
	private int[][] grille;							//la grille
	private final static int TAILLE_DEFAUT = 10;	//taille d'une grille standard
	
	/**
	 * Constructeur d'un Takuzu de taille TAILLE_DEFAUT vide.
	 */
	public Takuzu(){
		this.n = TAILLE_DEFAUT;
		this.grille = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				grille[i][j] = -1;
			}
		}
	}
	
	/**
	 * Constructeur d'un Takuzu de taille TAILLE_DEFAUT vide.
	 */
	public Takuzu(int[][] grille){
		int n = grille.length;
		if(n != 0 && n == grille[0].length && n%2 == 0){
			this.n = n;
			this.grille = grille;
		}
	}
	
	/**
	 * M�thode de r�solution d'un takuzu par r�currence.
	 * Sert d'initialisation � la r�currence, 
	 * l'h�r�dit� ce fait dans resolution(int).
	 */
	protected void resolution(){
		this.resolution(0);
	}
	
	/**
	 * M�thode de r�solution d'un takuzu par r�currence. 
	 * Elle prend en param�tre une position qui correspond
	 * � une case de la grille p = ligne * n + colonne.
	 * Par exemple, la case de coordonn�es (3,2) correspond � la position
	 * p = 3 * n + 2
	 * @param p La position sur laquelle on travail. 
	 * @return true si la position m�ne � une solution, false sinon.
	 */
	private boolean resolution(int p){
		int l = p/n; //r�cup�ration de la ligne
		int c = p%n; //r�cupration de la colonne
		
		//Si on atteind la derni�re case, on a alors fini la r�solution
		if(p == n*n)
			return true;
		
		//Si la case est d�j� rempli, on passe � la case suivante
		if(grille[l][c] != -1)
			return resolution(p+1);
		
		//Partie backtraking 
		for (int k=0; k < 2; k++){
			
			if(c == 0 && l == 2)
				System.out.println(this+" valide "+valide(k, l, c)+" unique "+unique(k, l, c)+" 3chiffreIdentique "+!chiffreIdentique(k, l, c));
			//Test des violations de r�gles
			if(valide(k, l, c) &&  unique(k, l, c) && !chiffreIdentique(k, l, c)){
				System.out.println(this);
				grille[l][c] = k;
				if(resolution(p+1)){
					return true;
				}
			}
			
			//Essai non-concluant, la case est r�initialis�e
			grille[l][c] = -1;
		}
		return false;
	}
	
	/**
	 * M�thode qui v�rifie si la ligne comporte le m�me nombre de 0 que de 1.
	 * @param t La valeur en cours d'�valuation
	 * @param l L'indice de la ligne
	 * @param c L'indice de la colonne
	 * @return true si la ligne est valide, false sinon
	 */
	private boolean valide(int t, int l, int c){
		grille[l][c] = t;
		int nb0 = 0;
		int nb1 = 0;
		if(c == n-1){
			for(int i = 0; i < n; i++){
				if(grille[l][i] == 0)
					nb0++;
				else
					nb1++;
			}
			if(nb0 == nb1)
				return true;
			return false;
		}
		else if(l == n-1){
			for(int i = 0; i < n; i++){
				if(grille[i][c] == 0)
					nb0++;
				else
					nb1++;
			}
			if(nb0 == nb1)
				return true;
			return false;
		}
		return true;
	}
	
	/**
	 * M�thode qui v�rifie si la ligne/colonne en cours de r�solution
	 * n'est pas identique � une autre ligne/colonne d�j� r�solu
	 * @param t La valeur en cours d'�valuation
	 * @param l L'indice de la ligne
	 * @param c L'indice de la colonne
	 * @return true si la ligne/colonne est unique, false sinon
	 */
	private boolean unique(int t, int l, int c){
		grille[l][c] = t;
		boolean unique;
		if(l != 0 && c == n - 1){
			unique = false;
			for(int i = l - 1; i > - 1; i--){
				unique = false;
				for(int j = 0; j < n && unique == false; j++){
					if(grille[l][j] != grille[i][j])
						unique = true;
				}
				if(unique == false)
					return false;
			}
		}
		else if(c != 0 && l == n - 1){
			unique = false;
			for(int i = c - 1; i > -1; i--){
				unique = false;
				for(int j = 0; j < n && unique == false; j++){
					if(grille[j][c] != grille[j][i])
						unique = true;
				}
				if(unique == false)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * M�thode qui v�rifie si il n'y a pas trois chiffre identique cons�cutif
	 * @param t La valeur � tester
	 * @param l L'indice de la ligne
	 * @param c L'indice de la colonne
	 * @return true si trois chiffre cons�cutif sont �gaux, false sinon
	 */
	private boolean chiffreIdentique(int t, int l, int c){
		grille[l][c] = t;
		if(c > 1){
			if(grille[l][c] == grille[l][c-1] && grille[l][c] == grille[l][c-2]){
				grille[l][c] = -1;
				return true;
			}
		}
		if(c > 0 && c < n-1){
			if(grille[l][c-1] == grille[l][c] && grille[l][c+1] == grille[l][c]){
				grille[l][c] = -1;
				return true;
			}
		}
		if(c < n-2){
			if(grille[l][c] == grille[l][c+1] && grille[l][c] == grille[l][c+2]){
				grille[l][c] = -1;
				return true;
			}
		}
		if(l > 1){
			if(grille[l][c] == grille[l-1][c] && grille[l][c] == grille[l-2][c]){
				grille[l][c] = -1;
				return true;
			}
		}
		if(l > 0 && l < n-1){
			if(grille[l-1][c] == grille[l][c] && grille[l+1][c] == grille[l][c]){
				grille[l][c] = -1;
				return true;
			}
		}
		if(l < n-2){
			if(grille[l][c] == grille[l+1][c] && grille[l][c] == grille[l+2][c]){
				grille[l][c] = -1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�thode d'affichage.
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(grille[i][j] != -1)
					s += grille[i][j] + " ";
				else
					s += "_ ";
			}
			s += "\n";
		}
		return s;
	}
}
