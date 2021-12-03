import java.util.ArrayList;
import java.util.Scanner;

public class dotComGame {
	//use a boolean or int to set direction (V or H) for boat generation
	//use simpleDot engine to generate the boats (x,y)
	//tweak it to retry generation if boat can't fit
	//TODO: boats overlap, direction can be wrong chosen (right dir from the right border)
	static boolean up,down,left,right;
	static int [][]b;
	static ArrayList<int[]>arr;
	static int px,py,d;
	static int []dirs;
	static int finalDir;
	static int availableDirs;
	public static void main(String[] args) {
		//input
		/*
		 * Scanner scan = new Scanner (System.in);
		 * System.out.println("INPUT BOARD SIZE"); int boardSize = scan.nextInt();
		 * System.out.println("INPUT NUMBER OF BOATS"); int boatsNr = scan.nextInt();
		 * int []boatsLen = new int[boatsNr]; for (int i=0;i<boatsNr;i++) {
		 * System.out.println("INPUT BOAT LENGTH, NOT LONGER THAN BOARD SIZE");
		 * boatsLen[i] = scan.nextInt(); }
		 */
		//board size
		b = new int[6][6];
		arr = new ArrayList<>();
		//add boats
		int []b1 = new int[4];
		int []b2 = new int[3];
		int []b3 = new int[3];
		int []b4 = new int[2];

		arr.add(b1);
		arr.add(b2);
		arr.add(b3);
		arr.add(b4);
		
		//right - up - left - down; 
		for (int i=0;i<arr.size();i++) {
			
			//reset the direction array
//			dirs = new int[4];
			doBoat(i);
//			buildBoat()
			printBZ();
		}
		
	}
	
	static void doBoat(int bPos) {
		//generate random coordinates
		generatePos();
		dirs = new int[4];
		System.out.println("px "+px +" py "+py+" len "+arr.get(bPos).length);
		
		//check the area around the generated coordinates
		//area equals the boat length
		checkSea(arr.get(bPos).length);
		//check if any direction is available
		checkAvailableDirs();
		if (availableDirs==0) {
			doBoat (bPos);
		}else {
			//choose one random available direction
			randomDir();
			printBoat(arr.get(bPos).length);

		}
		
//		changeDir();
	}
	static void checkSea(int len) {
		//check borders
		if (px+len-1>=b[0].length)
			dirs[0]=1;
		if (py+len-1>=b[0].length)
			dirs[1]=1;
		if (px-len+1<0)
			dirs[2]=1;
		if (py-len+1<0)
			dirs[3]=1;
		//x+ = right
		System.out.print("right :");
		if (dirs[0]==0)
			for (int i=px;i<px+len;i++) {
				System.out.print(i+" ");
//				if (i<b[0].length) {
					if (b[i][py]==1) {
						dirs[0]=1;
					}
//				}
			}
		//y+ = down
		System.out.print("down :");
		if (dirs[1]==0)
			for (int i=py;i<py+len;i++) {
				System.out.print(i+" ");
//				if (i<b[0].length) {
					if (b[px][i]==1) {
						dirs[1]=1;
					}
//				}
			}
		//x- = left
		
		if (dirs[2]==0)
			for (int i=px;i>px-len;i--) {
//				if (i>=0) {
					if (b[i][py]==1) {
						dirs[2]=1;
					}
//				}
			}
		//y- = up
		if (dirs[3]==0)
			for (int i=py;i>py-len;i--) {
//				if (i>=0) {
					if (b[px][i]==1) {
						dirs[3]=1;
					}
//				}
			}

	}
	
	static void checkAvailableDirs() {
		availableDirs = 0;
		System.out.print("available dirs: ");
		for (int i=0;i<4;i++)
			System.out.print(dirs[i]+" ");
		System.out.println();
		for (int i=0;i<4;i++) {
			if(dirs[i]==0)
				availableDirs++;
		}
		System.out.println("available dirs: "+ availableDirs);
		
	}
	
	static void randomDir() {
		double x = Math.random()*(availableDirs);
		finalDir = (int)x;
		
		if (dirs[finalDir]==1)
			//if (dirs[finalDir+1]==1)
				//finalDir++;
			//cazul in care dir valabile sunt 0 si 2 sau 1 si 3
			finalDir++;
		if (dirs[finalDir]==1)
			finalDir++;
		System.out.println("random chosen dir: "+finalDir);
	}
	
	static void generatePos() {
		double ppx= Math.random()*(b[0].length);
		double ppy= Math.random()*(b[0].length);
		px = (int)ppx;
		py = (int)ppy;
	}
	
	static void changeDir() {
		d = (int)(Math.random()*4);

	}
	
	static void printBoat(int len) {

		if (dirs[finalDir]==0) {
			switch(finalDir) {
			case 0: //x+ = right
				for (int i=px;i<px+len;i++) {
					b[i][py]=1;
				}
				break;
			case 1: //y+ = down
				for (int i=py;i<py+len;i++) {
					b[px][i]=1;
				}
				break;
			case 2: //x- = left
				for (int i=px;i>px-len;i--) {
					b[i][py]=1;
				}
				break;
			case 3: //y- = up
				for (int i=py;i>py-len;i--) {
					b[px][i]=1;
				}
				break;
				
			}
		}
//		else if (d+1<dirs.length)
//			if(dirs[d+1]==0) {
//			d=d+1;
//			printBoat(len);
//		}
	}

	static void printBZ() {
		for (int i=0;i<b.length;i++) {
			for (int j=0;j<b.length;j++) {
				System.out.print(b[j][i]+" ");
			}
			System.out.println();
		}
	}
}
