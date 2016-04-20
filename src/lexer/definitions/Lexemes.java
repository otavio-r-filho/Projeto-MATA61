package lexer.definitions;

/*
 * Classe de definição dos lexemas que poderão ser identificados pelo compilador
 * Códigos UTF-8:
 * Espaço: 32
 * NewLine: 13 | 10 | 13 + 10
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
		case "C":
			return 12;
		case "D":
			return 13;
		case "E":
			return 14;
		case "F":
			return 15;
		case "G":
			return 16;
		case "H":
			return 17;
		case "I":
			return 18;
		case "J":
			return 19;
		case "K":
			return 20;
		case "L":
			return 21;
		case "M":
			return 22;
		case "N":
			return 23;
		case "O":
			return 24;
		case "P":
			return 25;
		case "Q":
			return 26;
		case "R":
			return 27;
		case "S":
			return 28;
		case "T":
			return 29;
		case "U":
			return 30;
		case "V":
			return 31;
		case "W":
			return 32;
		case "X":
			return 33;
		case "Y":
			return 34;
		case "Z":
			return 35;
		case "a":
			return 36;
		case "b":
			return 37;
		case "c":
			return 38;
		case "d":
			return 39;
		case "e":
			return 40;
		case "f":
			return 41;
		case "g":
			return 42;
		case "h":
			return 43;
		case "i":
			return 44;
		case "j":
			return 45;
		case "k":
			return 46;
		case "l":
			return 47;
		case "m":
			return 48;
		case "n":
			return 49;
		case "o":
			return 50;
		case "p":
			return 51;
		case "q":
			return 52;
		case "r":
			return 53;
		case "s":
			return 54;
		case "t":
			return 55;
		case "u":
			return 56;
		case "v":
			return 57;
		case "w":
			return 58;
		case "x":
			return 59;
		case "y":
			return 60;
		case "z":
			return 61;
		case "+":
			return 62;
		case "-":
			return 63;
		case "*":
			return 64;
		case "/":
			return 65;
		case "<":
			return 66;
		case ">":
			return 67;
		case "=":
			return 68;
		case "!":
			return 69;
		case ";":
			return 70;
		case ",":
			return 71;
		case ".":
			return 72;
		case "(":
			return 73;
		case ")":
			return 74;
		case "[":
			return 75;
		case "]":
			return 76;
		case "{":
			return 77;
		case "}":
			return 78;
		case "&":
			return 79;
		case "|":
			return 80;
		case "\n": //ignorando salto de linha
			return 81;
		case " ": //ignorando espaços
			return 81;
		default:
			return -1;
		}
	}
	
public static int getLexemeIndex(char lexeme){
		
		switch(lexeme) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		case 'G':
			return 16;
		case 'H':
			return 17;
		case 'I':
			return 18;
		case 'J':
			return 19;
		case 'K':
			return 20;
		case 'L':
			return 21;
		case 'M':
			return 22;
		case 'N':
			return 23;
		case 'O':
			return 24;
		case 'P':
			return 25;
		case 'Q':
			return 26;
		case 'R':
			return 27;
		case 'S':
			return 28;
		case 'T':
			return 29;
		case 'U':
			return 30;
		case 'V':
			return 31;
		case 'W':
			return 32;
		case 'X':
			return 33;
		case 'Y':
			return 34;
		case 'Z':
			return 35;
		case 'a':
			return 36;
		case 'b':
			return 37;
		case 'c':
			return 38;
		case 'd':
			return 39;
		case 'e':
			return 40;
		case 'f':
			return 41;
		case 'g':
			return 42;
		case 'h':
			return 43;
		case 'i':
			return 44;
		case 'j':
			return 45;
		case 'k':
			return 46;
		case 'l':
			return 47;
		case 'm':
			return 48;
		case 'n':
			return 49;
		case 'o':
			return 50;
		case 'p':
			return 51;
		case 'q':
			return 52;
		case 'r':
			return 53;
		case 's':
			return 54;
		case 't':
			return 55;
		case 'u':
			return 56;
		case 'v':
			return 57;
		case 'w':
			return 58;
		case 'x':
			return 59;
		case 'y':
			return 60;
		case 'z':
			return 61;
		case '+':
			return 62;
		case '-':
			return 63;
		case '*':
			return 64;
		case '/':
			return 65;
		case '<':
			return 66;
		case '>':
			return 67;
		case '=':
			return 68;
		case '!':
			return 69;
		case ';':
			return 70;
		case ',':
			return 71;
		case '.':
			return 72;
		case '(':
			return 73;
		case ')':
			return 74;
		case '[':
			return 75;
		case ']':
			return 76;
		case '{':
			return 77;
		case '}':
			return 78;
		case '&':
			return 79;
		case '|':
			return 80;
		case 10: //ignorando saltos de linha
			return 81;
		case 13:
			return 81;
		case 32: //ignorando espaços
			return 81;
		default:
			return -1;
		}
	}

public static void printLexemeIndex(char lexeme){
	
	switch(lexeme) {
	case '0':
		System.out.println( 0 );
		break;
	case '1':
		System.out.println( 1 );
		break;
	case '2':
		System.out.println( 2 );
		break;
	case '3':
		System.out.println( 3 );
		break;
	case '4':
		System.out.println( 4 );
		break;
	case '5':
		System.out.println( 5 );
		break;
	case '6':
		System.out.println( 6 );
		break;
	case '7':
		System.out.println( 7 );
		break;
	case '8':
		System.out.println( 8 );
		break;
	case '9':
		System.out.println( 9 );
		break;
	case 'A':
		System.out.println( 10 );
		break;
	case 'B':
		System.out.println( 11 );
		break;
	case 'C':
		System.out.println( 12 );
		break;
	case 'D':
		System.out.println( 13 );
		break;
	case 'E':
		System.out.println( 14 );
		break;
	case 'F':
		System.out.println( 15 );
		break;
	case 'G':
		System.out.println( 16 );
		break;
	case 'H':
		System.out.println( 17 );
		break;
	case 'I':		
		System.out.println( 18 );
		break;
	case 'J':
		System.out.println( 19 );
		break;
	case 'K':
		System.out.println( 20 );
		break;
	case 'L':
		System.out.println( 21 );
		break;
	case 'M':
		System.out.println( 22 );
		break;
	case 'N':
		System.out.println( 23 );
		break;
	case 'O':
		System.out.println( 24 );
		break;
	case 'P':
		System.out.println( 25 );
		break;
	case 'Q':
		System.out.println( 26 );
		break;
	case 'R':
		System.out.println( 27 );
		break;
	case 'S':
		System.out.println( 28 );
		break;
	case 'T':
		System.out.println( 29 );
		break;
	case 'U':
		System.out.println( 30 );
		break;
	case 'V':
		System.out.println( 31 );
		break;
	case 'W':
		System.out.println( 32 );
		break;
	case 'X':
		System.out.println( 33 );
		break;
	case 'Y':
		System.out.println( 34 );
		break;
	case 'Z':
		System.out.println( 35 );
		break;
	case 'a':
		System.out.println( 36 );
		break;
	case 'b':
		System.out.println( 37 );
		break;
	case 'c':
		System.out.println( 38 );
		break;
	case 'd':
		System.out.println( 39 );
		break;
	case 'e':
		System.out.println( 40 );
		break;
	case 'f':
		System.out.println( 41 );
		break;
	case 'g':
		System.out.println( 42 );
		break;
	case 'h':
		System.out.println( 43 );
		break;
	case 'i':
		System.out.println( 44 );
		break;
	case 'j':
		System.out.println( 45 );
		break;
	case 'k':
		System.out.println( 46 );
		break;
	case 'l':
		System.out.println( 47 );
		break;
	case 'm':
		System.out.println( 48 );
		break;
	case 'n':
		System.out.println( 49 );
		break;
	case 'o':
		System.out.println( 50 );
		break;
	case 'p':
		System.out.println( 51 );
		break;
	case 'q':
		System.out.println( 52 );
		break;
	case 'r':
		System.out.println( 53 );
		break;
	case 's':
		System.out.println( 54 );
		break;
	case 't':
		System.out.println( 55 );
		break;
	case 'u':
		System.out.println( 56 );
		break;
	case 'v':
		System.out.println( 57 );
		break;
	case 'w':
		System.out.println( 58 );
		break;
	case 'x':
		System.out.println( 59 );
		break;
	case 'y':
		System.out.println( 60 );
		break;
	case 'z':
		System.out.println( 61 );
		break;
	case '+':
		System.out.println( 62 );
		break;
	case '-':
		System.out.println( 63 );
		break;
	case '*':
		System.out.println( 64 );
		break;
	case '/':
		System.out.println( 65 );
		break;
	case '<':
		System.out.println( 66 );
		break;
	case '>':
		System.out.println( 67 );
		break;
	case '=':
		System.out.println( 68 );
		break;
	case '!':
		System.out.println( 69 );
		break;
	case ';':
		System.out.println( 70 );
		break;
	case ',':
		System.out.println( 71 );
		break;
	case '.':
		System.out.println( 72 );
		break;
	case '(':
		System.out.println( 73 );
		break;
	case ')':
		System.out.println( 74 );
		break;
	case '[':
		System.out.println( 75 );
		break;
	case ']':
		System.out.println( 76 );
		break;
	case '{':
		System.out.println( 77 );
		break;
	case '}':
		System.out.println( 78 );
		break;
	case '&':
		System.out.println( 79 );
		break;
	case '|':
		System.out.println( 80 );
		break;
	case 10: //Espaço
		System.out.println(81);
		break;
	case 13:
		System.out.println(81);
		break;
	case 32:
		System.out.println(81);
		break;
	default:
		System.out.println("Lexema: " + (int) lexeme);
		break;
	}
}
}
