package day0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//연구소3 - BFS
public class BJ17142 {
	static int N,M;
	static int[][] map,v;
	static int ans= Integer.MAX_VALUE;
	static ArrayList<Point> list = new ArrayList<>(); 
	static int[] dx= {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N =Integer.parseInt(str[0]);
		M =Integer.parseInt(str[1]);
		int cnt=0;// 바이러스 개수 세기
		map = new int[N][N];
		v = new int[N][N];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j]==2) {
					list.add(new Point(i,j));
					cnt++;
				}
			}
		}

		Point[] virus = new Point[cnt];
		for(int i=0;i<list.size();i++) {
			virus[i] = new Point(list.get(i).x,list.get(i).y);
		}

		
		//print(map);
		combination(virus, 0,0, new Point[M]);
		
		//어떻게 놓아도 바이러스를 퍼뜨리지 못한경우
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		
		//퍼뜨려진경우
		else {
			System.out.println(ans-1);
		}
		
		
	}
	private static void combination(Point[] virus, int k, int idx, Point[] sel) {
		if(k==sel.length) {
			//System.out.println(Arrays.toString(sel));
			//바이러스에서 퍼져나가는거 하나씩 돌리기
			

			for(int i=0;i<sel.length;i++) {
				bfs(sel[i]);// 시작위치는 바이러스의 위치
			}
			int max=0;
			
			for(int i=0;i<N;i++) {
				for(int j=0; j<N; j++) {
					//빈칸인데 바이러스퍼진곳에서만 비교
					if(map[i][j]==0 &&v[i][j]!=0) {
						max = Math.max(max, v[i][j]);
					}
					//빈칸인데 바이러스 못퍼졌으면 답으로 고려하지않음
					else if(map[i][j]==0 && v[i][j]==0) {
						max = Integer.MAX_VALUE;
					}

				}
			}

			print(v);
			System.out.println(max);
			System.out.println("---------------------------------------");
			
			

	
			ans = Math.min(max, ans);
			
			clear();
			return;
		}
		
		for(int i=idx;i<virus.length;i++) {
			sel[k]=virus[i];
			combination(virus,k+1,i+1,sel);
		}
	}
	private static void clear() {
		for(int i=0;i<N; i++) {
			for(int j=0;j<N; j++) {
				v[i][j]=0;
			}
		}
	}
//	private static boolean hasEmpty() {
//		for (int i = 0; i < v.length; i++) {
//			for (int j = 0; j < v.length; j++) {
//				
//				//빈칸인데 못간곳 존재하면 안된다.
//				if(v[i][j]==0 && map[i][j]==0) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	private static void bfs(Point point) {
		Queue<Point> q = new LinkedList<>();
		q.add(point);
		v[point.x][point.y]=1;
		
		while(!q.isEmpty()) {
			for(int s=0;s<q.size();s++) {
				Point p = q.poll();
				int x = p.x;
				int y = p.y;
				
				for(int d=0;d<4;d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					//모든공간에 최소시간이 들어가므로 큰게들어있을떄 더빨리 그곳에 도착하면 바꾼다.
					if(nx>=0 && ny>=0 & nx<N && ny<N && (v[nx][ny]==0 || v[nx][ny]>v[x][y]) && map[nx][ny]==0) {
						q.add(new Point(nx,ny));
						v[nx][ny]=v[x][y]+1;
						
					}
				}

			}
		}
		
		
	}
	private static void print(int[][] map2) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

	
}
