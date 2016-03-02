package lexer.definitions;

/*
 * Classe de definição dos lexemas que poderão ser identificados pelo compilador
 */

import lexer.definitions.Lexeme;

public class Lexemes {
	
	public static enum eLexemes {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
		A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,
		a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,
		PLUS, MINUS, ASTHERISK, SMALLER, GREATER, EQUAL, EXCLAMATION, OPARENTHESES, CPARENTHESES, OBRACKET, CBRACKET, OKEYBRACKET, CKEYBRACKET, COMERCIALE, PIPE, COMMA, POINT 
	};
	
	public static final Lexeme ZERO 		= new Lexeme(0, "0");
	public static final Lexeme ONE 			= new Lexeme(1, "1");
	public static final Lexeme TWO 			= new Lexeme(2, "2");
	public static final Lexeme THREE 		= new Lexeme(3,"3");
	public static final Lexeme FOUR 		= new Lexeme(4,"4");
	public static final Lexeme FIVE 		= new Lexeme(5,"5");
	public static final Lexeme SIX 			= new Lexeme(6,"6");
	public static final Lexeme SEVEN 		= new Lexeme(7,"7");
	public static final Lexeme EIGHT 		= new Lexeme(8,"8");
	public static final Lexeme NINE 		= new Lexeme(9,"9");
	public static final Lexeme A 			= new Lexeme(10,"A");
	public static final Lexeme B 			= new Lexeme(11,"B");
	public static final Lexeme C 			= new Lexeme(12,"C");
	public static final Lexeme D 			= new Lexeme(13,"D");
	public static final Lexeme E 			= new Lexeme(14,"E");
	public static final Lexeme F 			= new Lexeme(15,"F");
	public static final Lexeme G 			= new Lexeme(16,"G");
	public static final Lexeme H 			= new Lexeme(17,"H");
	public static final Lexeme I 			= new Lexeme(18,"I");
	public static final Lexeme J 			= new Lexeme(19,"J");
	public static final Lexeme K 			= new Lexeme(20,"K");
	public static final Lexeme L 			= new Lexeme(21,"L");
	public static final Lexeme M 			= new Lexeme(22,"M");
	public static final Lexeme N 			= new Lexeme(23,"N");
	public static final Lexeme O 			= new Lexeme(24,"O");
	public static final Lexeme P 			= new Lexeme(25,"P");
	public static final Lexeme Q 			= new Lexeme(26,"Q");
	public static final Lexeme R 			= new Lexeme(27,"R");
	public static final Lexeme S 			= new Lexeme(28,"S");
	public static final Lexeme T 			= new Lexeme(29,"T");
	public static final Lexeme U 			= new Lexeme(30,"U");
	public static final Lexeme V 			= new Lexeme(31,"V");
	public static final Lexeme W 			= new Lexeme(32,"W");
	public static final Lexeme X 			= new Lexeme(33,"X");
	public static final Lexeme Y 			= new Lexeme(34,"Y");
	public static final Lexeme Z 			= new Lexeme(35,"Z");
	public static final Lexeme a 			= new Lexeme(36,"a");
	public static final Lexeme b 			= new Lexeme(37,"b");
	public static final Lexeme c 			= new Lexeme(38,"c");
	public static final Lexeme d 			= new Lexeme(39,"d");
	public static final Lexeme e 			= new Lexeme(40,"e");
	public static final Lexeme f 			= new Lexeme(41,"f");
	public static final Lexeme g 			= new Lexeme(42,"g");
	public static final Lexeme h 			= new Lexeme(43,"h");
	public static final Lexeme i 			= new Lexeme(44,"i");
	public static final Lexeme j 			= new Lexeme(45,"j");
	public static final Lexeme k 			= new Lexeme(46,"k");
	public static final Lexeme l 			= new Lexeme(47,"l");
	public static final Lexeme m 			= new Lexeme(48,"m");
	public static final Lexeme n 			= new Lexeme(49,"n");
	public static final Lexeme o 			= new Lexeme(50,"o");
	public static final Lexeme p 			= new Lexeme(51,"q");
	public static final Lexeme r 			= new Lexeme(52,"r");
	public static final Lexeme s 			= new Lexeme(53,"s");
	public static final Lexeme t 			= new Lexeme(54,"t");
	public static final Lexeme u 			= new Lexeme(55,"u");
	public static final Lexeme v 			= new Lexeme(56,"v");
	public static final Lexeme w 			= new Lexeme(56,"w");
	public static final Lexeme x 			= new Lexeme(57,"x");
	public static final Lexeme y 			= new Lexeme(58,"y");
	public static final Lexeme z 			= new Lexeme(59,"z");
	public static final Lexeme PLUS 		= new Lexeme(60,"+");
	public static final Lexeme MINUS 		= new Lexeme(61,"-");
	public static final Lexeme ASTERISK 	= new Lexeme(62,"*");
	public static final Lexeme SMALLER 		= new Lexeme(63,"<");
	public static final Lexeme GREATER 		= new Lexeme(64,">");
	public static final Lexeme EQUAL 		= new Lexeme(65,"=");
	public static final Lexeme EXCLAMATION 	= new Lexeme(66,"!");
	public static final Lexeme OPARENTHESES	= new Lexeme(67,"(");
	public static final Lexeme CPARENTHESES = new Lexeme(68,")");
	public static final Lexeme OBRACKET 	= new Lexeme(69,"[");
	public static final Lexeme CBRACKET 	= new Lexeme(70,"]");
	public static final Lexeme OKEYBRACKET 	= new Lexeme(71,"{");
	public static final Lexeme CKEYBRACKET 	= new Lexeme(72,"}");
	public static final Lexeme COMERCIALE 	= new Lexeme(73,"&");
	public static final Lexeme PIPE 		= new Lexeme(74,"|");
	public static final Lexeme COMMA 		= new Lexeme(75, ",");
	public static final Lexeme POINT 		= new Lexeme(76, ".");
	public static final Lexeme SLASH 		= new Lexeme(77,"/");
	
	public static int getLexemeIndex(String lexeme){
		
		switch(lexeme) {
		case "0":
			return 0;
		case "1":
			return 1;
		case "2":
			return 2;
		case "3":
			return 3;
		case "4":
			return 4;
		case "5":
			return 5;
		case "6":
			return 6;
		case "7":
			return 7;
		case "8":
			return 8;
		case "9":
			return 9;
		case "A":
			return 10;
		case "B":
			return 11;
			
		}
		return -1;
	}
	
}
