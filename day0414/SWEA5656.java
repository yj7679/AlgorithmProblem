package day0414;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 벽돌 깨기 - 조합+시뮬레이션
public class SWEA5656 {
	static int W,H,N,T,ans;
	static int[][] map,temp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/day0414/SWEA5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T ;tc++) {
			ans=Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//print(map);
			
			//0~W-1까지 중에서 N개 뽑기
			permutation(0,new int[N]);
			
			
			
			
			
			
			
			
			
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	private static void permutation(int k,int[] sel) {
		if(k==sel.length) {
			//System.out.println(Arrays.toString(sel));
			temp = new int[H][W];
			for(int i=0;i<H;i++) {
				for (int j = 0; j < W; j++) {
					temp[i][j] = map[i][j];
				}
			}
			//print(temp);


			//연쇄 부서지기
			for(int i : sel) {
				simul(i);
				//싹 내리기
				gravity();
			}
			//System.out.println("--------------");
			//print(temp);

			int cnt=0;
			for(int i=0; i<H;i++) {
				for(int j=0;j<W;j++) {
					if(temp[i][j]!=0) {
						cnt++;
					}
				}
			}
			ans = Math.min(cnt, ans);
			//System.out.println("--------------");
			//print(temp);
			//System.out.println("==========");
			//System.exit(0);
			
			
			return;
		}
		
		
		for(int i=0; i<W; i++) {
			sel[k] =i;
			permutation(k+1,sel);
		}

		
	}
	private static void gravity() {
		ArrayList<Integer> numbers=new ArrayList<>();

		for (int j = 0; j < W; j++) {
			for(int i=H-1;i>=0;i--) {
				if(temp[i][j]!=0) {
					numbers.add(temp[i][j]);
					temp[i][j]=0;
				}
			}
			int cnt=H-1;
			for(Integer num : numbers) {
				temp[cnt--][j]=num;
			}
			numbers.clear();
		}

	}
	private static void simul(int i) {
		//맨위 벽돌찾기
		boolean flag=false;
		int x=0;
		int y=0;
		for(int r=0;r<H;r++) {
			if(temp[r][i]!=0) {
				flag=true;
				x=r;
				y=i;
				break;
			}
		}
		
		if(flag==false) {
			return;
		}
		
		broke(x,y);
		
		
		
		
	}
	private static void broke(int x, int y) {
		int count = temp[x][y];
		temp[x][y]=0;
		for(int i=x-count+1;i<=x+count-1;i++) {
			if( i>=0 && i<H && temp[i][y]!=0) {
				broke(i,y);
			}
		}
		for(int i=y-count+1;i<=y+count-1;i++) {
			if(i>=0 && i<W && temp[x][i]!=0) {
				broke(x,i);
			}
		}
	}
	private static void print(int[][] map2) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

}
