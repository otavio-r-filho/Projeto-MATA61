package lexer;

import java.util.ArrayList;
import java.util.List;

import lexer.definitions.*;

public class Lexer {
	private Tokens tokens;
	
	private int actualState = 1;
	private int lastFinalState = 0;
	private String actualChain;
	
	private ArrayList<tToken> tokenList;
	
	public Lexer() {
		tokenList = new ArrayList<tToken>();		
	}
	
	//[Estado][Lexema]
	public int edges[][] = {/* 0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,
						A ,B ,C ,D ,E ,F ,G ,H ,I ,J ,K ,L ,M ,N ,O ,P ,Q ,R ,S ,T ,U ,V ,W ,X ,Y ,Z ,
						a ,b ,c ,d ,e ,f ,g ,h ,i ,j ,k ,l ,m ,n ,o ,p ,q ,r ,s ,t ,u ,v ,w ,x ,y ,z ,
						+ ,- ,* ,/ ,< ,> ,= ,! ,; ,, ,. ,( ,) ,[ ,] ,{ ,} ,& ,|," ",/n,/r,$ */
	/*Estado 0*/       {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,                                                   //10 collumns
	/*ENTRY*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,   //26 collumns
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,   //26 collumns
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},                  //20 collumns
	/*Estado 1*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ENTRY*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 2*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID0*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,3 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,4 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 3*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*IF*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 4*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID1*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,5 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 5*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*INT*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 6*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID2*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 7*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*NUM*/				0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						46,47,48,49,50,52,54,56,58,59,8 ,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 8*/	   {9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,
	/*REAL0*/			0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,1},
	/*Estado 9*/	   {9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,
	/*REAL*/			0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 10*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID3*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,13,6 ,6 ,11,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 11*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID4*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,12,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 12*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*FOR*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 13*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID5*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,14,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 14*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID6*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						15,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 15*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID7*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,16,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 16*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*FLOAT*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 17*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID8*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,18,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 18*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID9*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,19,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 19*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID10*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,20,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 20*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ELSE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,20,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 21*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID11*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,22,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 22*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID12*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,23,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 23*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*NEW*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 24*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID13*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,25,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 25*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID14*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,26,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 26*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID15*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,27,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 27*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID16*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,28,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 28*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID17*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,29,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 29*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*RETURN*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 30*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID18*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,31,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},	
	/*Estado 31*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID19*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,32,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 32*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*STR*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 33*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID20*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,34,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 34*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID21*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,35,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 35*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID22*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,36,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 36*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*THEN*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 37*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID23*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,38,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 38*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID24*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,39,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 39*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID25*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,40,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 40*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*VOID*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 41*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID26*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,42,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 42*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID27*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,43,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 43*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID28*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,44,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 44*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID29*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,45,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 45*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*WHILE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 46*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*PLUS*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 47*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*MINUS*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 48*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ASTERISK*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 49*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SLASH*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 50*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SMALLER*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,51,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 51*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SEQUAL*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 52*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*GREATER*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,53,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 53*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*GEQUAL*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 54*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ATTRIBUTION*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,55,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 55*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMPARISSON*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 56*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*EXCLAMATION*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,57,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 57*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*DIFFERENT*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 58*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SEMICOLON*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 59*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMMA*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 60*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*POINT*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 61*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OPARENTHESES*/	6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 62*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CPARENTHESES*/	6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 63*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 64*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 65*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OKEYBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 66*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CKEYBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 67*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMMERCIALE*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,69,68,1 ,1},
	/*Estado 68*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*PIPE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,70,1 ,1},
	/*Estado 69*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*AND*/		        6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1},
	/*Estado 70*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OR*/		        6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 ,1}
	};

	
	public void feedTokenList(int lexemeIndex, String lexeme, int line, int collumn) {
		if(lexeme.equals("$") && tokenList.get(tokenList.size() - 1).getTokenType().equals("$")){
			return;
		}
		
		tToken tokenResult = new tToken();
		
		tokenResult = spitToken(lexemeIndex, lexeme, line, collumn);
		if(tokenResult != null) {
			tokenList.add(tokenResult);
		}
	}
	
	public ArrayList<tToken> getTokenList() {
		return this.tokenList;
	}

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     spitToken     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//spittoken modificado a partir da fun��o spitToken2

	public String spitTokenString(int lexemeIndex, String lexeme, int line, int collumn) {
		switch(actualState){
		case 0:
			tToken errorToken = new tToken("ERROR", actualChain, 38);
			actualChain = "";
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
			}
//			return Tokens.ERROR.getToken();
			return errorToken.getToken();
		case 1: //ENTRY
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
				actualChain = "";
			}
			return null;
		case 3: //IF
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.IF.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 5: //INT
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.INT.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 1;
				return null;
			}
		case 7: //NUM
			if(lexemeIndex != -1) {
				if(((lexemeIndex > 61) && (lexemeIndex != 72)) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					//Eliminando 0 � esquerda;
//					Tokens.NUM.setTokenValue(actualChain.replaceFirst("^0+(?!$)", ""));
					tToken numToken = new tToken("NUM", actualChain, 36);
					actualChain = lexeme;
//					return Tokens.NUM.getToken();
					return numToken.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					//actualChain = actualChain + lexeme;
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 8: //REAL0 - estado intermedi�rio entre NUM e REAL - n�o � um estado final
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = actualChain + lexeme;
				return null;
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 9: //REAL
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
//					Tokens.REAL.setTokenValue(actualChain);
					tToken realToken = new tToken("REAL", actualChain, 37);
					actualChain = lexeme;
//					return Tokens.REAL.getToken();
					return realToken.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 12: //FOR
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.FOR.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 16: //FLOAT
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.FLOAT.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 20: //ELSE
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.ELSE.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 23: //NEW
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.NEW.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 29: //RETURN
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.RETURN.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 32: //STR
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.STR.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 36: //THEN
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.THEN.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 40: //VOID
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.VOID.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 45: //WHILE
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return Tokens.WHILE.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 46: //PLUS
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.PLUS.getToken();
		case 47: //MINUS
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.MINUS.getToken();
		case 48: //ASTERISK
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.ASTERISK.getToken();
		case 49: //SLASH
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.SLASH.getToken();
		case 50: //SMALLER
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.SMALLER.getToken();
		case 51: //SEQUAL
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.SEQUAL.getToken();
		case 52: //GREATER
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.GREATER.getToken();	
		case 53: //GEQUAL
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = "";
			return Tokens.GEQUAL.getToken();
		case 54: //ATTIBUTION
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.ATTRIBUTION.getToken();
		case 55: //DIFFERENT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = "";
			return Tokens.COMPARISSON.getToken();
		case 56: //EXCLAMATION
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.EXCLAMATION.getToken();
		case 57: //DIFFERENT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.DIFFERENT.getToken();
		case 58: //SEMICOLON
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.SEMICOLON.getToken();
		case 59: //COMMA
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.COMMA.getToken();
		case 60: //POINT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.POINT.getToken();
		case 61: //OPARENTHESES
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.OPARENTHESES.getToken();
		case 62: //CPARENTHESES
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.CPARENTHESES.getToken();
		case 63: //OBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.OBRACKET.getToken();
		case 64: //CBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.CBRACKET.getToken();
		case 65: //OKEYBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.OKEYBRACKET.getToken();
		case 66: //CKEYBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.CKEYBRACKET.getToken();
		case 67: //COMMERCIALE
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.COMERCIALE.getToken();
		case 68: //PIPE
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
			return Tokens.PIPE.getToken();
		default:
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
//					Tokens.ID.setTokenValue(actualChain);
					tToken idToken = new tToken("ID", actualChain, 35);
					actualChain = "";
//					return Tokens.ID.getToken();
					return idToken.getToken();
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		}
	}

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     spitToken     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	public tToken spitToken(int lexemeIndex, String lexeme, int line, int collumn) {
		switch(actualState){
		case 0:
			tToken errorToken = new tToken("ERROR", actualChain, 38, line, collumn - actualChain.length());
			actualChain = "";
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
			}
//			return Tokens.ERROR;
			return errorToken;
		case 1: //ENTRY
			if(lexemeIndex != -1) {
				if(lexemeIndex == 82){
					return (new tToken("$", "$", 40, line, collumn - "$".length()));
				}
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
				actualChain = "";
			}
			return null;
		case 3: //IF
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
					return (new tToken("IF", "if", 4, line, collumn - "if".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 5: //INT
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.INT;
					return (new tToken("INTEGER", "int", 5, line, collumn - "int".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 7: //NUM
			if(lexemeIndex != -1) {
				if(((lexemeIndex > 61) && (lexemeIndex != 72)) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					//Tokens.NUM.setTokenValue(actualChain);
					//Eliminando 0 � esquerda;
//					Tokens.NUM.setTokenValue(actualChain.replaceFirst("^0+(?!$)", ""));
					tToken numToken = new tToken("NUM", actualChain.replaceFirst("^0+(?!$)", ""), 38, line, collumn - actualChain.length());
					actualChain = lexeme;
					return numToken;
				} else {
					actualState = edges[actualState][lexemeIndex];
					//actualChain = actualChain + lexeme;
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 8: //REAL0 - estado intermedi�rio entre NUM e REAL - n�o � um estado final
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = actualChain + lexeme;
				return null;
			} else {
				actualChain = "";
				actualState = 0;
				return null;
			}
		case 9: //REAL
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
//					Tokens.REAL.setTokenValue(actualChain);
					tToken realToken = new tToken("REAL", actualChain, 39, line, collumn - actualChain.length());
					actualChain = lexeme;
//					return Tokens.REAL;
					return realToken;
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 12: //FOR
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.FOR;
					return (new tToken("FOR", "for", 3, line, collumn - "for".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 16: //FLOAT
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.FLOAT;
					return (new tToken("FLOAT", "float", 2, line, collumn - "float".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 20: //ELSE
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.ELSE;
					return (new tToken("ELSE", "else", 1, line, collumn - "else".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 23: //NEW
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.NEW;
					return (new tToken("NEW", "new", 6, line, collumn - "new".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 29: //RETURN
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.RETURN;
					return (new tToken("RETURN", "return", 7, line, collumn - "return".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 32: //STR
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.STR;
					return (new tToken("STRING", "str", 8, line, collumn - "str".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 36: //THEN
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.THEN;
					return (new tToken("THEN", "then", 9, line, collumn - "then".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 40: //VOID
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.VOID;
					return (new tToken("VOID", "void", 10, line, collumn - "void".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 45: //WHILE
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
					actualChain = lexeme;
//					return Tokens.WHILE;
					return (new tToken("WHILE", "while", 11, line, collumn - "while".length()));
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		case 46: //PLUS
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.PLUS;
			return (new tToken("PLUS", "+", 12, line, collumn - "+".length()));
		case 47: //MINUS
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.MINUS;
			return (new tToken("MINUS", "-", 13, line, collumn - "-".length()));
		case 48: //ASTERISK
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.ASTERISK;
			return (new tToken("ASTERISK", "*", 14, line, collumn - "*".length()));
		case 49: //SLASH
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.SLASH;
			return (new tToken("SLASH", "/", 15, line, collumn - "/".length()));
		case 50: //SMALLER
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.SMALLER;
			return (new tToken("SMALLER", "<", 16, line, collumn - "<".length()));
		case 51: //SEQUAL
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.SEQUAL;
			return (new tToken("SEQUAL", "<=", 20, line, collumn - "<=".length()));
		case 52: //GREATER
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.GREATER;
			return (new tToken("GREATER", ">", 17, line, collumn - ">".length()));
		case 53: //GEQUAL
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = "";
//			return Tokens.GEQUAL;
			return (new tToken("GEQUAL", ">=", 21, line, collumn - ">=".length()));
		case 54: //ATTIBUTION
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.ATTRIBUTION;
			return (new tToken("ATTRIBUTION", "=", 18, line, collumn - "=".length()));
		case 55: //DIFFERENT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = "";
//			return Tokens.COMPARISSON;
			return (new tToken("COMPARISSON", "==", 22, line, collumn - "==".length()));
		case 56: //EXCLAMATION
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 68) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.EXCLAMATION;
			return (new tToken("EXCLAMATION", "!", 19, line, collumn - "!".length()));
		case 57: //DIFFERENT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.DIFFERENT;
			return (new tToken("DIFFERENT", "!=", 23, line, collumn - "!=".length()));
		case 58: //SEMICOLON
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.SEMICOLON;
			return (new tToken("SEMICOLON", ";", 24, line, collumn - ";".length()));
		case 59: //COMMA
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.COMMA;
			return (new tToken("COMMA", ",", 25, line, collumn - ",".length()));
		case 60: //POINT
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.POINT;
			return (new tToken("POINT", ".", 34, line, collumn - ".".length()));
		case 61: //OPARENTHESES
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.OPARENTHESES;
			return (new tToken("OPARENTHESES", "(", 26, line, collumn - "(".length()));
		case 62: //CPARENTHESES
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.CPARENTHESES;
			return (new tToken("CPARENTHESES", ")", 27, line, collumn - ")".length()));
		case 63: //OBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.OBRACKET;
			return (new tToken("OBRACKET", "[", 28, line, collumn - "[".length()));
		case 64: //CBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.CBRACKET;
			return (new tToken("CBRACKET", "]", 29, line, collumn - "]".length()));
		case 65: //OKEYBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.OKEYBRACKET;
			return (new tToken("OKEYBRACKET", "{", 30, line, collumn - "{".length()));
		case 66: //CKEYBRACKET
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.CKEYBRACKET;
			return (new tToken("CKEYBRACKET", "}", 31, line, collumn - "}".length()));
		case 67: //COMMERCIALE
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 79) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.COMERCIALE;
			return (new tToken("COMMERCIALE", "&", 32, line, collumn - "&".length()));
		case 68: //PIPE
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				if(lexemeIndex == 80) {
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
				actualState = 0;
			}
			actualChain = lexeme;
//			return Tokens.PIPE;
			return (new tToken("PIPE", "|", 33, line, collumn - "|".length()));
		case 69: //AND
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			} 
			actualChain = "";
//			return Tokens.AND;
			return (new tToken("AND", "&&", 35, line, collumn - "&&".length()));
		case 70: //OR
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
			} else {
				actualState = 0;
			} 
			actualChain = "";
//			return Tokens.OR;
			return (new tToken("OR", "||", 36, line, collumn - "||".length()));
		default:
			if(lexemeIndex != -1) {
				if((lexemeIndex > 61) || (edges[actualState][lexemeIndex] == 1)) {
					actualState = edges[actualState][lexemeIndex];
//					Tokens.ID.setTokenValue(actualChain);
					tToken idToken = new tToken("ID", actualChain, 37, line, collumn - actualChain.length());
					actualChain = "";
//					return Tokens.ID;
					return idToken;
				} else {
					actualState = edges[actualState][lexemeIndex];
					actualChain = actualChain + lexeme;
					return null;
				}
			} else {
//				actualChain = "";
				actualState = 0;
				return null;
			}
		}
	}	
}
