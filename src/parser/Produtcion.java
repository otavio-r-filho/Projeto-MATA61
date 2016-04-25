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
		default:
			break;
		}
	}
}
