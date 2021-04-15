package day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//구슬 탈출2 - BFS
public class BJ13460 {
	static int N,M;
	static char[][] map;
	static int targetx,targety;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static class Point{
		int x,y,count;

		public Point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		int startbx =0,startby=0,startrx=0,startry=0;
		
		for(int i=0; i<N;i++) {
			String st = br.readLine();
			for (int j = 0; j < M; j++) {
				if(st.charAt(j)=='B') {
					startbx = i;
					startby = j;
					map[i][j]='.';
				}
				else if(st.charAt(j)=='R') {
					startrx = i;
					startry = j;
					map[i][j]='.';
				}
				else if(st.charAt(j)=='O') {
					targetx = i;
					targety = j;
					map[i][j]='.';
				}
				else {	
					map[i][j]= st.charAt(j);
				}
			}
		}
		//print(map);
		Point redBall = new Point(startrx,startry,1);
		Point blueBall = new Point(startbx,startby,1);
		bfs(redBall,blueBall);
	}
	private static void bfs(Point startredBall, Point startblueBall) {
		Queue<Point> redQ = new LinkedList<>();
		Queue<Point> blueQ = new LinkedList<>();
		int[][] rv = new int[N][M];
		int[][] bv = new int[N][M];
		
		redQ.add(startredBall);
		blueQ.add(startblueBall);
		rv[startredBall.x][startredBall.y]= startredBall.count;
		bv[startblueBall.x][startblueBall.y]= startblueBall.count;
		
		//둘중하나라도 굴릴게 있으면 굴린다.
		while(!redQ.isEmpty() || !blueQ.isEmpty()) {
			// 각자 굴리는 만큼 굴린다.
			for(int s =0; s < blueQ.size(); s++) {
				Point blueBall = blueQ.poll();
				Point redBall = redQ.poll();
				//11번쨰로 굴리는 공이면 10번쨰까지 성공못했으니까 -1출력
				if(redBall.count==11) {
					System.out.println(-1);
					System.exit(0);
				}

				
				//상 하 좌우 기울이기 두공이 같이 움직임
				for(int d=0; d<4;d++) {
					boolean redpass=false;
					boolean bluepass=false;
					
					int rNewx = redBall.x+dx[d];
					int rNewy = redBall.y+dy[d];

					//빨간공 기울이기
					while(map[rNewx][rNewy]=='.' ) {//빈칸이고 간곳이 아니면
						//굴리다가 빨간공 통과
						if(rNewx==targetx && rNewy==targety) {
							redpass=true;
						}
						rNewx = rNewx +dx[d];
						rNewy = rNewy +dy[d];
					}
					//한번 더갔으니까 돌려줌
					rNewx = rNewx -dx[d];
					rNewy = rNewy -dy[d];
					
					int bNewx = blueBall.x+dx[d];
					int bNewy = blueBall.y+dy[d];
					// 파란공 기울이기
					while(map[bNewx][bNewy]=='.') {
						//굴리다가 파란공 통과
						if(bNewx==targetx && bNewy==targety) {
							bluepass=true;
						}
						bNewx = bNewx +dx[d];
						
						bNewy = bNewy +dy[d];
					}
					//한번더갔으니까 돌려줌
					bNewx = bNewx -dx[d];
					bNewy = bNewy -dy[d];				
					
					//파란공이 동시에 들어가든, 따로혼자들어가든 들어가면 실패조건이므로 그냥 continue
					if(bluepass) {
						continue;
					}
					
					//파란공 안들어가고 빨간공만 들어가면 성공
					else if(redpass){
						System.out.println(redBall.count);
//						print(rv);
//						System.out.println("---------");
//						print(bv);
						System.exit(0);
					}
					
					
					
					
					//굴리는방향d는 상우하좌 순서
					//두공이 겹친 경우
					if((rNewx == bNewx) &&(rNewy==bNewy)) {
						//위로굴리기
						if(d==0) {
							// red가 더 아래에서 시작한 경우 red공을 아래로 한칸 땡김
							if(redBall.x>blueBall.x) {
								rNewx+=1;
							}else {//blue가 더아래서 시작한 경우
								bNewx+=1;
							}
						}else if(d==1) {
							// red가 왼쪽에서 시작한 경우 red공을 왼쪽 한칸 땡김
							if(redBall.y<blueBall.y) {
								rNewy-=1;
							}else {//blue 땡기는 경우
								bNewy-=1;
							}
						}else if(d==2) {
							// red가 위에서 시작한 경우 red공을 위쪽 한칸 땡김
							if(redBall.x<blueBall.x) {
								rNewx-=1;
							}else {//blue 땡기는 경우
								bNewx-=1;
							}					
						}else if(d==3) {
							// red가 오른쪽에서 시작한 경우 red공을 오른쪽 한칸 땡김
							if(redBall.y>blueBall.y) {
								rNewy+=1;
							}else {//blue 땡기는 경우
								bNewy+=1;
							}	
						}
						
					}
					//둘중 하나의 공이라도 다른 위치로 바뀐 경우 둘다 q에 넣음
					if(redBall.x!=rNewx || redBall.y!=rNewy || blueBall.x!=bNewx || blueBall.y!=bNewy) {
						redQ.add(new Point(rNewx,rNewy,redBall.count+1));
						blueQ.add(new Point(bNewx,bNewy,blueBall.count+1));
						rv[rNewx][rNewy]=redBall.count+1;
						bv[bNewx][bNewy]=blueBall.count+1;
					}
				}
				
			}
		}
		//q다빌때까지 성공해서못빠져나갔으면 -1출력
		System.out.println(-1);
		System.exit(0);
		
	}
	private static void print(int[][] rv) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(rv[i][j]);
			}
			System.out.println();
		}
	}

}
