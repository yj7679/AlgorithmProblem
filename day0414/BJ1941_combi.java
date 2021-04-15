package day0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//소문난 칠공주
public class BJ1941_combi {
	static char[][] map = new char[5][5];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int ans=0;
	static int[][] v = new int[5][5],check=new int[5][5];
	static class Point{
		int r,c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Point> S = new ArrayList<>();
		
		for(int i=0; i<5;i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='S') {
					S.add(new Point(i,j));
				}
			}
		}
		combination(S,0,0, new Point[4]);
		System.out.println(ans);
	}
	
	private static void combination(ArrayList<Point> S, int k, int idx, Point[] sel) {
		if(k==sel.length) {
			//System.out.println(Arrays.toString(sel));
			v=new int[5][5];
			for(Point p : sel) {
				v[p.r][p.c] =2;//확정은 2로표시
			}
			//테두리의포인트를 뽑을 리스트
			ArrayList<Point> temp = new ArrayList<>();
			//v가 1이 아닌 주위 포인트를 리스트에 넣고 3개뽑기
			for(Point p : sel) {
				for(int d=0; d<4;d++) {
					int nr = p.r+dx[d];
					int nc = p.c+dy[d];
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
						v[nr][nc]=1; //1은 확정난 포인트위치가 아니다.
						temp.add(new Point(nr,nc));
						
					}
				}
			}
			
			//temp list에 있는 포인트 중에서 3개뽑기.
			combination2(temp,0,0,new Point[3]);
			
			
			
			
			return;
		}
		
		
		int size= S.size();
		for(int i=idx; i<size;i++) {
			sel[k] = S.get(i);
			combination(S,k+1,i+1,sel);
			
		}
		
	}

	private static void combination2(ArrayList<Point> temp, int k, int idx, Point[] sel) {
		if(k==sel.length) {
			check=new int[5][5];
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(v[i][j]==2) {
						check[i][j]=2;
					}
				}
			}
			
			for(Point p: sel) {
				check[p.r][p.c]=2;
			}
			
			l:for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(check[i][j]==2) {
						dfs(i,j);
						break l;
					}
					
				}
			}
			boolean flag=true;
			b:for(int i=0;i<5;i++) {
				for(int j=0;j<5; j++) {
					if(check[i][j]==2) {
						flag = false;
						break b;
					}
				}
			}

			if(flag==true) {
				ans++;
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(v[i][j]==2) {
							check[i][j]=2;
						}
					}
				}
				
				for(Point p: sel) {
					check[p.r][p.c]=2;
				}
				print(check);
			}
			


			return;
		}
		
		
		int size= temp.size();
		for(int i=idx; i<size; i++) {
			sel[k] = temp.get(i);
			combination2(temp,k+1,i+1,sel);
		}

	}

	private static void dfs(int i, int j) {
		
		check[i][j]=0;
		for(int d=0;d<4;d++) {
			int nr = i+dx[d];
			int nc = j+dy[d];
			if(nr>=0 && nc>=0 && nr<5 && nc<5 && check[nr][nc]==2) {
				dfs(nr,nc);
			}
		}
	}

	private static void print(int[][] v2) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(v2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------");
	}

}
