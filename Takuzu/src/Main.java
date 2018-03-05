
public class Main {

	public static void main(String[] args) {
//		Takuzu t = new Takuzu();
//		t.resolution();
//		System.out.println(t);
		
		int[][] grille = {
					{-1,-1,-1,-1,0,-1,0,-1},
					{-1,-1,-1,-1,-1,-1,-1,-1},
					{0,0,-1,-1,-1,-1,0,-1},
					{-1,-1,-1,-1,-1,-1,-1,-1},
					{-1,-1,0,-1,-1,-1,0,0},
					{1,-1,-1,-1,-1,-1,-1,-1},
					{-1,-1,0,-1,0,0,-1,-1},
					{-1,-1,0,-1,0,0,-1,0}
				};
		Takuzu test = new Takuzu(grille);
		test.resolution();
		System.out.println(test);
	}

}
