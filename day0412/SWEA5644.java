package day0412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//무선 충전 - 시뮬레이션
public class SWEA5644 {
	static int[][][] map;
	static int T,M,A;
	static int[] moveA,moveB;
	static int[] BC;
	static int[] dx= {0,-1,0,1,0}; //정지 상우하좌 순서
	static int[] dy= {0,0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/day0412/SWEA5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int ans=0;
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(st.nextToken()); // 사용자 이동 횟수
			A = Integer.parseInt(st.nextToken()); // 배터리충전기의 개수
			moveA =new int[M];
			moveB =new int[M];
			map = new int[A][11][11]; // 충전기 개수 만큼의 맵 만듬
			BC = new int[A];
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<M;i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<M;i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				makeBC(x,y,C,P,i);
				BC[i] =P;
//				print(map[i]);
//				System.out.println("----------------");
			}
			int nAx = 1;
			int nAy = 1;
			int nBx = 10;
			int nBy = 10;
			//충전할 수 있는 경우의 배터리 종류를 담을 list
			ArrayList<Integer> Alist = new ArrayList<>();
			ArrayList<Integer> Blist = new ArrayList<>();


			
			//이동 된 위치에서 배터리를 충전할 수 있는 매시점의 최대값을 구하면된다.
			for(int bc=0;bc < A;bc++) {
				if(map[bc][nAx][nAy]!=0) {
					Alist.add(bc);
				}
				if(map[bc][nBx][nBy]!=0) {
					Blist.add(bc);
				}
			}
			
			int max = 0;
			
			//반복문으로 A와 B가 고른경우을 반복한다.
			for(Integer a: Alist) {
				
				for(Integer b:Blist) {
					int sum=0;
					//a와 b를 선택했을때 배터리 충전량계산
					if(a==b) {//같은 경우
						sum = BC[a];
					}
					else {
						sum = BC[a]+BC[b];
					}
					
					max = Math.max(sum, max);
				}
			}
			
			if(Alist.size()==0) {
				for(Integer b:Blist) {
					
					max = Math.max(BC[b], max);
				}
			}
			else if(Blist.size()==0) {
				for(Integer a:Alist) {
					
					max = Math.max(BC[a], max);
				}
			}
			
			
			ans+=max;
			for(int i=0;i<M;i++) {
				Alist = new ArrayList<>();
				Blist = new ArrayList<>();
				//한번씩 움직임
				nAx = nAx +dx[moveA[i]];
				nAy = nAy +dy[moveA[i]];
				nBx = nBx +dx[moveB[i]];
				nBy = nBy +dy[moveB[i]];
//				System.out.println(nAx+" "+nAy);
//				System.out.println(nBx+" "+nBy);
				
				//이동 된 위치에서 배터리를 충전할 수 있는 매시점의 최대값을 구하면된다.
				for(int bc=0;bc < A;bc++) {
					if(map[bc][nAx][nAy]!=0) {
						Alist.add(bc);
					}
					if(map[bc][nBx][nBy]!=0) {
						Blist.add(bc);
					}
				}
				
				max = 0;
				
				//반복문으로 A와 B가 고른경우을 반복한다.
				for(Integer a: Alist) {
					
					for(Integer b:Blist) {
						int sum=0;
						//a와 b를 선택했을때 배터리 충전량계산
						if(a==b) {//같은 경우
							sum = BC[a];
						}
						else {
							sum = BC[a]+BC[b];
						}
						
						max = Math.max(sum, max);
					}
				}
				
				if(Alist.size()==0) {
					for(Integer b:Blist) {
						
						max = Math.max(BC[b], max);
					}
				}
				else if(Blist.size()==0) {
					for(Integer a:Alist) {
						
						max = Math.max(BC[a], max);
					}
				}
				
				
				//다고르고 나오면 a와 b에 있을때 최대값을 뽑아나왔다.
//				System.out.println(max);
//				System.out.println("-------");
				ans+=max;
			}
			
			
			System.out.println("#"+tc+" "+ ans);
		}
	}
	private static void print(int[][] is) {
		for(int i=1;i<=10;i++) {
			for (int j = 1; j <=10; j++) {
				System.out.print(is[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	private static void makeBC(int x, int y, int c, int p,int idx) {
		for(int i=x-c; i<=x+c;i++) {
			for(int j=y-c;j<=y+c;j++) {
				if(i>=1 && j>=1 && i<=10 && j<=10 && Math.abs(x-i)+Math.abs(y-j)<=c) {
					map[idx][i][j]=p;
				
				}
				
			}
		}
	}
	
	

}
