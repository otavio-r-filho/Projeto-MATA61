package lexer;

import lexer.definitions.*;

public class Lexer {
	private Tokens tokens;
	
	private States states;
	
	private static int actualState = 1; 
	
	int edges[][] = {/* 0,1,2,3,4,5,6,7,8,9,
						A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,
						a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,
						+,-,*,<,>,=,!,(,),[,],{,},&,|,,,.*/
	/*Estado 0*/       {0,0,0,0,0,0,0,0,0,0,
						0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
						0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
						0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	/*Estado 1*/	   {7,7,7,7,7,7,7,7,7,7,
						6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,
						6,6,6,6
						}
	};
	
}
