package parser;

import parser.tools.*;

public class Produtcion {
	public void Produce(int produtctionID, Stack productionStack) {
		switch (produtctionID) {
		case 0:
			//programa -> declaracoes
			break;
		case 1:
			//declaracoes -> declaracao declaracoes
			break;
		case 2:
			//declaracoes -> EPSILON
			break;
		case 3:
			//declaracao -> tipo-base ID dec-fim
			break;
		case 4:
			//declaracao - >void ID dec-funcao
			break;
		case 5:
			//dec-fim -> lista-nomes ;
			break;
		case 6:
			//dec-fim -> dec-funcao
			break;
		case 7:
			//lista-nomes -> , ID lista-nomes
			break;
		case 8:
			//lista-nomes -> EPSILON
			break;
		case 9:
			//tipo-base -> int
			break;
		case 10:
			//tipo-base -> float
			break;
		case 11:
			//dec-funcao -> ( parametros ) bloco
			break;
		case 12:
			//parametros -> parametro parametros
			break;
		case 13:
			//parametros -> , parametros parametros
			break;
		case 14:
			//parametro -> EPSILON
			break;
		case 15:
			//parametro -> tipo-base ID
			break;
		case 16:
			//bloco -> { dec-variavel comandos }
			break;
		case 17:
			//dec-variavel -> tipo-base ID lista-nomes ; dec-variavel
			break;
		case 18:
			//dec-variavel -> EPSILON
			break;
		case 19:
			//comandos -> comando comandos
			break;
		case 20:
			//comandos -> EPSILON
			break;
		case 21:
			//comando -> if ( exp ) comando parte-else
			break;
		case 22:
			//comando -> while ( exp ) comando
			break;
		case 23:
			//comando -> for ( lista-exp ; lista-exp ; lista-exp ) comando
			break;
		case 24:
			//comando -> ID chamada-atr
			break;
		case 25:
			//comando -> return retorno ;
			break;
		case 26:
			//comando -> bloco
			break;
		case 27:
			//retorno -> exp
			break;
		case 28:
			//retorno -> EPSILON
			break;
		case 29:
			//parte-else -> else comando
			break;
		case 30:
			//parte-else -> EPSILON
			break;
		case 31:
			//chamada -> chamada
			break;
		case 32:
			//chamada -> atribuicao
			break;
		case 33:
			//chamada -> ( lista-exp )
			break;
		case 34:
			//atribuicao -> = exp
			break;
		case 35:
			//exp2 exp-p1
			break;
		case 36:
			//exp-p1 -> || exp
			break;
		case 37:
			//exp-p1 -> EPSILON
			break;
		case 38:
			//exp2 -> exp3 exp-p2
			break;
		case 39:
			//exp-p2 -> && exp2
			break;
		case 40:
			//exp-p2 -> EPSILON
			break;
		case 41:
			//exp3 -> exp4 exp-p3
			break;
		case 42:
			//exp-p3 ->  == exp3
			break;
		case 43:
			//exp-p3 -> != exp3
			break;
		case 44:
			//exp-p3 -> EPSILON
			break;
		case 45:
			//exp4 -> exp5 exp-p4
			break;
		case 46:
			//exp-p4 -> < exp4
			break;
		case 47:
			//exp-p4 -> > exp4
			break;
		case 48:
			//exp-p4 -> <= exp4
			break;
		case 49:
			//exp-p4 -> >= exp4
			break;
		case 50:
			//exp-p4 -> EPSILON
			break;
		case 51:
			//exp5 -> exp6 exp-p5
			break;
		case 52:
			//exp-p5 -> - exp-p5
			break;
		case 53:
			//exp-p5 -> + exp-p5
			break;
		case 54:
			//exp-p5 -> EPSILON
			break;
		case 55:
			//exp6 -> NUM exp-p6
			break;
		case 56:
			//exp6 -> REAL exp-p6
			break;
		case 57:
			//exp6 -> ( exp ) exp-p6
			break;
		case 58:
			//exp6 -> ID exp7
			break;
		case 59:
			//exp6 -> ! exp 
			break;
		case 60:
			//exp6 -> - exp
			break;
		case 61:
			//exp-p6 -> / exp6
			break;
		case 62:
			//exp-p6 -> * exp6
			break;
		case 63:
			//exp-p6 -> EPSILON
			break;
		case 64:
			//exp7 -> chamada
			break;
		case 65:
			//exp7 -> exp-p6
			break;
		case 66:
			//exp7 -> EPSILON
			break;
		case 67:
			//lista-exp -> exp lista-exp
			break;
		case 68:
			//lista-exp -> , exp lista-exp
			break;
		case 69:
			//lista-exp -> EPSILON
			break;
		default:
			break;
		}
	}
}
