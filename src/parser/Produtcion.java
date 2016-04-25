package parser;

import parser.tools.*;

public class Produtcion {
	public void Produce(int produtctionID, Stack productionStack) {
		switch (produtctionID) {
		case 0:
			//programa -> declaracoes
			productionStack.pop();
			productionStack.push("declaracoes");
			break;
		case 1:
			//declaracoes -> declaracao declaracoes
			productionStack.pop();
			productionStack.push("declaracao");
			productionStack.push("declaracoes");
			break;
		case 2:
			//declaracoes -> EPSILON
			productionStack.pop();
			break;
		case 3:
			//declaracao -> tipo-base ID dec-fim
			productionStack.pop();
			productionStack.push("tipo-base");
			productionStack.push("ID");
			productionStack.push("dec-fim");
			break;
		case 4:
			//declaracao - >void ID dec-funcao
			productionStack.pop();
			productionStack.push("VOID");
			productionStack.push("ID");
			productionStack.push("dec-funcao");
			break;
		case 5:
			//dec-fim -> lista-nomes ;
			productionStack.pop();
			productionStack.push("lista-nomes");
			productionStack.push("SEMICOLON");
			break;
		case 6:
			//dec-fim -> dec-funcao
			productionStack.pop();
			productionStack.push("dec-funcao");
			break;
		case 7:
			//lista-nomes -> , ID lista-nomes
			productionStack.pop();
			productionStack.push("ID");
			productionStack.push("lista-nomes");
			break;
		case 8:
			//lista-nomes -> EPSILON
			productionStack.pop();
			break;
		case 9:
			//tipo-base -> int
			productionStack.pop();
			productionStack.push("INTEGER");
			break;
		case 10:
			//tipo-base -> float
			productionStack.pop();
			productionStack.push("FLOAT");
			break;
		case 11:
			//dec-funcao -> ( parametros ) bloco
			productionStack.pop();
			productionStack.push("OPARENTHESES");
			productionStack.push("parametros");
			productionStack.push("CPARENTHESES");
			productionStack.push("bloco");
			break;
		case 12:
			//parametros -> parametro parametros
			productionStack.pop();
			productionStack.push("parametro");
			productionStack.push("parametros");
			break;
		case 13:
			//parametros -> , parametro parametros
			productionStack.pop();
			productionStack.push("COMMA");
			productionStack.push("parametro");
			productionStack.push("parametros");
			break;
		case 14:
			//parametros -> EPSILON
			productionStack.pop();
			break;
		case 15:
			//parametro -> tipo-base ID
			productionStack.pop();
			productionStack.push("tipo-base");
			productionStack.push("ID");
			break;
		case 16:
			//bloco -> { dec-variavel comandos }
			productionStack.pop();
			productionStack.push("OKEYBRACKET");
			productionStack.push("dec-variavel");
			productionStack.push("comandos");
			productionStack.push("CKEYBRACKET");
			break;
		case 17:
			//dec-variavel -> tipo-base ID lista-nomes ; dec-variavel
			productionStack.pop();
			productionStack.push("tipo-base");
			productionStack.push("ID");
			productionStack.push("lista-nomes");
			productionStack.push("SEMICOLON");
			productionStack.push("dec-variavel");
			break;
		case 18:
			//dec-variavel -> EPSILON
			productionStack.pop();
			break;
		case 19:
			//comandos -> comando comandos
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("comandos");
			break;
		case 20:
			//comandos -> EPSILON
			productionStack.pop();
			break;
		case 21:
			//comando -> if ( exp ) comando parte-else
			productionStack.pop();
			productionStack.push("IF");
			productionStack.push("OPARENTHESES");
			productionStack.push("exp");
			productionStack.push("CPARENTHESES");
			productionStack.push("comando");
			productionStack.push("parte-else");
			break;
		case 22:
			//comando -> while ( exp ) comando
			productionStack.pop();
			productionStack.push("while");
			productionStack.push("OPARENTHESES");
			productionStack.push("exp");
			productionStack.push("CPARENTHESES");
			productionStack.push("comando");
			break;
		case 23:
			//comando -> for ( lista-exp ; lista-exp ; lista-exp ) comando
			productionStack.pop();
			productionStack.push("FOR");
			productionStack.push("OPARENTHESES");
			productionStack.push("lista-exp");
			productionStack.push("SEMICOLON");
			productionStack.push("lista-exp");
			productionStack.push("SEMICOLON");
			productionStack.push("lista-exp");
			productionStack.push("CPARENTHESES");
			productionStack.push("comando");
			break;
		case 24:
			//comando -> ID chamada-atr ;
			productionStack.pop();
			productionStack.push("ID");
			productionStack.push("chamada-atr");
			productionStack.push("SEMICOLON");
			break;
		case 25:
			//comando -> return retorno ;
			productionStack.pop();
			productionStack.push("RETURN");
			productionStack.push("retorno");
			productionStack.push("SEMICOLON");
			break;
		case 26:
			//comando -> bloco
			productionStack.pop();
			productionStack.push("bloco");
			break;
		case 27:
			//retorno -> exp
			productionStack.pop();
			productionStack.push("exp");
			break;
		case 28:
			//retorno -> EPSILON
			productionStack.pop();
			break;
		case 29:
			//parte-else -> else comando
			productionStack.pop();
			productionStack.push("ELSE");
			productionStack.push("comando");
			break;
		case 30:
			//parte-else -> EPSILON
			productionStack.pop();
			break;
		case 31:
			//chamada-atr -> chamada
			productionStack.pop();
			productionStack.push("chamada");
			break;
		case 32:
			//chamada-atr -> atribuicao
			productionStack.pop();
			productionStack.push("atribuicao");
			break;
		case 33:
			//chamada -> ( lista-exp )
			productionStack.pop();
			productionStack.push("OPARENTHESES");
			productionStack.push("lista-exp");
			productionStack.push("CPARENTHESES");
			break;
		case 34:
			//atribuicao -> = exp
			productionStack.pop();
			productionStack.push("ATTRIBUTION");
			productionStack.push("exp");
			break;
		case 35:
			//exp -> exp2 exp-p1
			productionStack.pop();
			productionStack.push("exp2");
			productionStack.push("exp-p1");
			break;
		case 36:
			//exp-p1 -> || exp
			productionStack.pop();
			productionStack.push("OR");
			productionStack.push("exp");
			break;
		case 37:
			//exp-p1 -> EPSILON
			productionStack.pop();
			break;
		case 38:
			//exp2 -> exp3 exp-p2
			productionStack.pop();
			productionStack.push("exp3");
			productionStack.push("exp-p2");
			break;
		case 39:
			//exp-p2 -> && exp2
			productionStack.pop();
			productionStack.push("AND");
			productionStack.push("exp2");
			break;
		case 40:
			//exp-p2 -> EPSILON
			productionStack.pop();
			break;
		case 41:
			//exp3 -> exp4 exp-p3
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("exp-p3");
			break;
		case 42:
			//exp-p3 ->  == exp3
			productionStack.pop();
			productionStack.push("COMPARISSON");
			productionStack.push("exp3");
			break;
		case 43:
			//exp-p3 -> != exp3
			productionStack.pop();
			productionStack.push("DIFFERENT");
			productionStack.push("exp3");
			break;
		case 44:
			//exp-p3 -> EPSILON
			productionStack.pop();
			break;
		case 45:
			//exp4 -> exp5 exp-p4
			productionStack.pop();
			productionStack.push("exp5");
			productionStack.push("exp-p4");
			break;
		case 46:
			//exp-p4 -> < exp4
			productionStack.pop();
			productionStack.push("SMALLER");
			productionStack.push("exp4");
			break;
		case 47:
			//exp-p4 -> > exp4
			productionStack.pop();
			productionStack.push("GREATER");
			productionStack.push("exp4");
			break;
		case 48:
			//exp-p4 -> <= exp4
			productionStack.pop();
			productionStack.push("SEQUAL");
			productionStack.push("exp4");
			break;
		case 49:
			//exp-p4 -> >= exp4
			productionStack.pop();
			productionStack.push("GEQUAL");
			productionStack.push("exp4");
			break;
		case 50:
			//exp-p4 -> EPSILON
			productionStack.pop();
			break;
		case 51:
			//exp5 -> exp6 exp-p5
			productionStack.pop();
			productionStack.push("exp6");
			productionStack.push("exp-p5");
			break;
		case 52:
			//exp-p5 -> - exp-p5
			productionStack.pop();
			productionStack.push("MINUS");
			productionStack.push("exp-p5");
			break;
		case 53:
			//exp-p5 -> + exp-p5
			productionStack.pop();
			productionStack.push("PLUS");
			productionStack.push("exp-p5");
			break;
		case 54:
			//exp-p5 -> EPSILON
			productionStack.pop();
			break;
		case 55:
			//exp6 -> NUM exp-p6
			productionStack.pop();
			productionStack.push("NUM");
			productionStack.push("exp-p6");
			break;
		case 56:
			//exp6 -> REAL exp-p6
			productionStack.pop();
			productionStack.push("REAL");
			productionStack.push("exp-p6");
			break;
		case 57:
			//exp6 -> ( exp ) exp-p6
			productionStack.pop();
			productionStack.push("OPARENTHESES");
			productionStack.push("exp");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp-p6");
			break;
		case 58:
			//exp6 -> ID exp7
			productionStack.pop();
			productionStack.push("ID");
			productionStack.push("exp7");
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
