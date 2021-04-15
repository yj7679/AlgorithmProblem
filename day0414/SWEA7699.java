package day0414;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//수지의 수지맞은 여행 - DFS
public class SWEA7699 {
	static int T,R,C,ans;
	static char[][] map;
	
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/day0414/SWEA7699.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st=null;
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine()," ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			for(int i=0; i<R; i++) {
				String str = br.readLine();
				for(int j=0; j<C;j++) {
					map[i][j] = str.charAt(j);
				}
			}
			//print(map);
			ans = 0;
			dfs(0,0,1,new int['Z'-'A'+1]);
			System.out.println("#"+ tc+" "+ans);
		}

	}
	private static void dfs(int x, int y, int dep, int[] alpa) {

			

		
		
		alpa[map[x][y]-'A']=1;
		
		for(int d=0;d<4;d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx>=0 && ny>=0 && nx<R && ny<C && alpa[map[nx][ny]-'A']==0) {

				dfs(nx,ny,dep+1,alpa);
			}
			
			
		}
		alpa[map[x][y]-'A']=0;
		ans = Math.max(ans, dep);
	}
	private static void print(char[][] map2) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

}
