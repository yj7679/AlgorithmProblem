package day0413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA4014 {
	static int T,N,X;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/day0413/SWEA4014.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		T =Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] =Integer.parseInt(st.nextToken());
				}
				
			}
			System.out.print("#"+tc+" ");
			//print(map);
			//System.out.println();
			
			int ans=0;
			for(int i=0;i<N;i++) {
				int state_up = map[i][0];
				//System.out.println("---------------------");
				//System.out.println(i);
				
				int cnt_up=1;
				boolean flag=true;
				for (int j = 1; j < N; j++) {
					//값이 유지되는 경우
					if(map[i][j]==state_up) {
						cnt_up++;
						//세울게 있는 경우 X개만큼 지나왔으면 세우는것 성공
						if(flag==false && cnt_up==X) {
							//System.out.println(j+ "에서 채웠음");
							cnt_up=0;
							flag=true;
						}
						
						
					}
					//값이 달라진 경우
					else {
						//새로운 값이 이전상태에 비해서 클경우 count와 x를 비교
						if(map[i][j]==state_up+1) {
							//x보다 count가 작은 경우 실패
							if(X>cnt_up) {
								flag=false;
								//System.out.println("앞"+(i+1)+ "번째줄 "+j+"에서 실패");
								break;
							}
							//경사판을 세울 수 있는 경우
							else if(X<=cnt_up) {
								//state를 현재 바닥의 높이로 바꾸고cnt=1로바꿈
								state_up = map[i][j];
								cnt_up=1;

							}
						}
						
						//새로운 값이 이전상태에 비해서 작은 경우
						else if(map[i][j]==state_up-1){
							if(flag==false) {
								break;
							}
							//System.out.println(j+ "에서 작아서 일단 실패 체크");
							flag=false;
							cnt_up=1;
							state_up = map[i][j];
						}
						else {
							flag=false;
							break;
						}
					}
					
				}
				//무사히 한줄을 완성한 경우
				if(flag) {
					ans++;
				}
				
			}
			
			for(int j=0;j<N;j++) {
				int state_up = map[0][j];
				//System.out.println("---------------------");
				
				int cnt_up=1;
				boolean flag=true;
				for (int i = 1; i < N; i++) {
					//값이 유지되는 경우
					if(map[i][j]==state_up) {
						cnt_up++;
						//세울게 있는 경우 X개만큼 지나왔으면 세우는것 성공
						if(flag==false && cnt_up==X) {
							//System.out.println(j+ "에서 채웠음");
							cnt_up=0;
							flag=true;
						}
						
						
					}
					//값이 달라진 경우
					else {
						//새로운 값이 이전상태에 비해서 클경우 count와 x를 비교
						if(map[i][j]==state_up+1) {
							//x보다 count가 작은 경우 실패
							if(X>cnt_up) {
								flag=false;
								//System.out.println("앞"+(i+1)+ "번째줄 "+j+"에서 실패");
								break;
							}
							//경사판을 세울 수 있는 경우
							else if(X<=cnt_up) {
								//state를 현재 바닥의 높이로 바꾸고cnt=1로바꿈
								state_up = map[i][j];
								cnt_up=1;

							}
						}
						
						//새로운 값이 이전상태에 비해서 작은 경우
						else if(map[i][j]==state_up-1){
							if(flag==false) {
								break;
							}
							//System.out.println(j+ "에서 작아서 일단 실패 체크");
							flag=false;
							cnt_up=1;
							state_up = map[i][j];
						}
						else {
							flag=false;
							break;
						}
						
					}
					
				}
				//무사히 한줄을 완성한 경우
				if(flag) {
					ans++;
				}
				
			}
			
			
			System.out.println(ans);
			
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
