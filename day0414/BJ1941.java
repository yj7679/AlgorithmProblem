package day0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//소문난 칠공주
public class BJ1941 {
	static char[][] map = new char[5][5];
	static int[][] v=new int[5][5],check=new int[5][5];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5;i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		dfs(0,0,1,0,false);//x좌표, y좌표, 길이, s의 수:y의 수가 4명이상되면 불가능
		System.out.println(ans);
		
	}
	private static void dfs(int r, int c, int dep, int y, boolean selected) {
		if(dep==7) {


			//마지막위치이고 Y인경우
			if(map[r][c] == 'Y') {
				if(y<=2) {
					ans++;
					v[r][c]=2;
					print(v);
					v[r][c]=0;
				}
			}
			//마지막 위치이고 S인 경우
			else {
				ans++;
				v[r][c]=2;
				print(v);
				v[r][c]=0;
			}
			
			
			
			
			return;
		}
		//System.out.println(r+" "+c);
		//처음뽑는 경우 사방확인해서 넣을필요없다
		if(selected==false) {
			//현위치가 Y이고 y를 한명더 포섭할수 있는데 뽑는 경우 (y는 최대 3명)
			if(map[r][c]=='Y' && y<3) {
				for(int d=0; d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
						v[r][c]=2;
						dfs(nr,nc,dep+1,y+1,true);
						v[r][c]=0;
					}
				}
			}
			//현재위치가 S인 경우에 뽑는 경우
			else if(map[r][c]=='S') {
				for(int d=0; d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
						v[r][c]=2;
						dfs(nr,nc,dep+1,y,true);
						v[r][c]=0;
					}
				}
				
			}
			
			//현위치랑 상관없이 안뽑고 넘기는 경우
			for(int d=0; d<4;d++) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				
				if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
					v[r][c]=1;
					dfs(nr,nc,dep,y,selected);				
					v[r][c]=0;
				}
			}
			
			
		}
		
		//처음뽑는 경우가 아니면 사방 중에 친구한명있어야한다
		else if(selected==true) {
			//현위치가 Y이고 y를 한명더 포섭할수 있는데 뽑는 경우 (y는 최대 3명)
			if(map[r][c]=='Y' && y<3) {
				boolean flag= false;
				for(int d=0;d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==2) {
						flag=true;
						break;
					}
				}
				//주변에 친구가없으면 고려하지않는 경우이다.
				if(flag==false) {
					
					return;
				}
				
				
				for(int d=0; d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
						v[r][c]=2;
						dfs(nr,nc,dep+1,y+1,true);
						v[r][c]=0;
					}
				}
			}
			//현재위치가 S인 경우에 뽑는 경우
			else if(map[r][c]=='S') {
				boolean flag= false;
				for(int d=0;d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==2) {
						flag=true;
						break;
					}
				}
				//주변에 친구가없으면 고려하지않는 경우이다.
				if(flag==false) {
					return;
				}
				
				for(int d=0; d<4;d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
						v[r][c]=2;
						dfs(nr,nc,dep+1,y,true);
						v[r][c]=0;
					}
				}
				
			}
			
			//현위치랑 상관없이 안뽑고 넘기는 경우
			for(int d=0; d<4;d++) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				
				if(nr>=0 && nc>=0 && nr<5 && nc<5 && v[nr][nc]==0) {
					v[r][c]=1;
					dfs(nr,nc,dep,y,selected);			
					v[r][c]=0;
				}
			}
			
			
			
		}
		
		
	}
	private static void dfs2(int i, int j) {
		
		
		check[i][j]=0;
		
		for(int d=0;d<4;d++) {
			int nx=i+dx[d];
			int ny=j+dy[d];
			if(nx>=0 && ny>=0 && nx<5 && ny<5 && check[nx][ny]==1) {
				dfs2(nx,ny);
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
