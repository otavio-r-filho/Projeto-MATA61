package lexer;

import lexer.definitions.*;

public class Lexer {
	private Tokens tokens;
	
	private States states;
	
	private int actualState = 1;
	private int lastFinalState = 0;
	private String actualChain;
	
	//[Estado][Lexema]
	public int edges[][] = {/* 0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,
						A ,B ,C ,D ,E ,F ,G ,H ,I ,J ,K ,L ,M ,N ,O ,P ,Q ,R ,S ,T ,U ,V ,W ,X ,Y ,Z ,
						a ,b ,c ,d ,e ,f ,g ,h ,i ,j ,k ,l ,m ,n ,o ,p ,q ,r ,s ,t ,u ,v ,w ,x ,y ,z ,
						+ ,- ,* ,/ ,< ,> ,= ,! ,; ,, ,. ,( ,) ,[ ,] ,{ ,} ,& ,| */
	/*Estado 0*/       {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ENTRY*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 1*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ENTRY*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 2*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID0*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,3 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,4 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 3*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*IF*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 4*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID1*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,5 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 5*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*INT*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 6*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID2*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 7*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*NUM*/				0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						46,47,48,49,50,52,54,56,58,59,8 ,61,62,63,64,65,66,67,68,1 },
	/*Estado 8*/	   {9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,
	/*REAL0*/			0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 },
	/*Estado 9*/	   {9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,
	/*REAL*/			0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 10*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID3*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,13,6 ,6 ,11,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 11*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID4*/					6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,12,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 12*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*FOR*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 13*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID5*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,14,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 14*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID6*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						15,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 15*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID7*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,16,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 16*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*FLOAT*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 17*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID8*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,18,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 18*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID9*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,19,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 19*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID10*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,20,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 20*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ELSE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,20,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 21*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID11*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,22,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 22*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID12*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,23,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 23*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*NEW*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 24*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID13*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,25,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 25*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID14*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,26,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 26*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID15*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,27,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 27*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID16*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,28,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 28*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID17*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,29,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 29*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*RETURN*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 30*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID18*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,31,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },	
	/*Estado 31*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID19*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,32,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 32*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*STR*/				6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 33*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID20*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,34,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 34*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID21*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,35,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 35*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID22*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,36,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 36*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*THEN*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 37*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID23*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,38,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 38*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID24*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,39,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 39*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID25*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,40,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 40*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*VOID*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 41*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID26*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,42,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 42*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID27*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,43,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 43*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID28*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,44,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 44*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*ID29*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,45,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 45*/	   {6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
	/*WHILE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 46*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*PLUS*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 47*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*MINUS*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 48*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ASTERISK*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 49*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SLASH*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 50*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SMALLER*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,51,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 51*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SEQUAL*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 52*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*GREATER*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,53,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 53*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*GEQUAL*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 54*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*ATTRIBUTION*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,55,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 55*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMPARISSON*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 56*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*EXCLAMATION*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,57,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 57*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*DIFFERENT*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 58*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*SEMICOLON*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 59*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMMA*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 60*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*POINT*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 61*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OPARENTHESES*/	6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 62*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CPARENTHESES*/	6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 63*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 64*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 65*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*OKEYBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 66*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*CKEYBRACKET*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 67*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*COMMERCIALE*/		6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 },
	/*Estado 68*/	   {7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,
	/*PIPE*/			6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,6 ,
						6 ,6 ,6 ,6 ,17,10,6 ,6 ,2 ,6 ,6 ,6 ,6 ,21,6 ,6 ,6 ,24,30,33,6 ,37,41,6 ,6 ,6 ,	
						46,47,48,49,50,52,54,56,58,59,60,61,62,63,64,65,66,67,68,1 }
	};

//spittoken modificado a partir da função spitToken2

	public String spitToken(int lexemeIndex, String lexeme) {
		switch(actualState){
		case 0:
			actualChain = "";
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
			}
			return Tokens.ERROR.getToken();
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
					//Tokens.NUM.setTokenValue(actualChain);
					//Eliminando 0 à esquerda;
					Tokens.NUM.setTokenValue(actualChain.replaceFirst("^0+(?!$)", ""));
					actualChain = lexeme;
					return Tokens.NUM.getToken();
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
		case 8: //REAL0 - estado intermediário entre NUM e REAL - não é um estado final
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
					Tokens.REAL.setTokenValue(actualChain);
					actualChain = lexeme;
					return Tokens.REAL.getToken();
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
					Tokens.ID.setTokenValue(actualChain);
					actualChain = "";
					return Tokens.ID.getToken();
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
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     spitToken2     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	public String spitToken2(int lexemeIndex, String lexeme) {
		switch(actualState){
		case 0:
			actualChain = "";
			if(lexemeIndex != -1) {
				actualState = edges[actualState][lexemeIndex];
				actualChain = lexeme;
			} else {
				actualState = 0;
			}
			return Tokens.ERROR.getToken();
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
					actualChain = "";
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
					actualChain = "";
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
					Tokens.NUM.setTokenValue(actualChain);
					actualChain = "";
					return Tokens.NUM.getToken();
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
		case 8: //REAL0 - estado intermediário entre NUM e REAL - não é um estado final
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
					Tokens.REAL.setTokenValue(actualChain);
					actualChain = "";
					return Tokens.REAL.getToken();
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					actualChain = "";
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
					Tokens.ID.setTokenValue(actualChain);
					actualChain = "";
					return Tokens.ID.getToken();
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
}
