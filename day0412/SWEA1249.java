package day0412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 보급로
public class SWEA1249 {
	static int T,N;
	static int[][] map;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/day0412/SWEA1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i <N; i++) {
				String str = br.readLine();
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Character.getNumericValue(str.charAt(j));
				}
			}

			System.out.print("#"+tc+" ");
			bfs();
			
			//print(map);
			
		}
		
	}
	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		int[][] v =new int[N][N];
		int[][] check = new int[N][N];
		v[0][0]=1;
		while(!q.isEmpty()) {
			//System.out.println("====================");
			//print(map);
			//System.out.println("--------");
			//print(v);
			int size= q.size();
			for(int s=0; s<size;s++) {
				Point p = q.poll();
				int x  = p.x;
				int y  = p.y;
				for(int d=0;d<4; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					//안갔던 곳이거나 현재가 더작으면 넣는다.
					if( nx>=0 && ny>=0 && nx<N && ny<N && (v[nx][ny]==0 ||map[nx][ny]+v[x][y]<v[nx][ny])) {
						q.add(new Point(nx,ny));
		
						v[nx][ny]=map[nx][ny]+v[x][y];
						check[nx][ny]=1;
						
						
					}
					
					
				}
				

			}


		}
		
		System.out.println(v[N-1][N-1]-1);
		
	}
	private static void print(int[][] map2) {
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2.length; j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

}
