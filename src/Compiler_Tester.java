import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import lexer.definitions.*;
import lexer.Lexer;

public class Compiler_Tester {

	public static void main(String[] args) {
		File testFile = new File("C:\\arquivoTeste.c");
		Charset charset = Charset.forName("UTF-8");
		Lexer lexer = new Lexer();
		//String lexerResult = null;
		char ch = 0;
		int r = 0;
		int line = 1;
		int collumn = 1;
		
		List<tToken> tokenList = new LinkedList<tToken>();
		try {
			InputStream in = new FileInputStream(testFile);
			Reader reader = new InputStreamReader(in, charset);
	         // buffer for efficiency
	         Reader buffer = new BufferedReader(reader);
	        //handleCharacters(buffer);
			//int r; //Posição antiga do r
			while ((r = buffer.read()) != -1) {
		        ch = (char) r;
		        lexer.feedTokenList(Lexeme.getLexemeIndex(String.valueOf(ch)), String.valueOf(ch), line, collumn);
		        if(String.valueOf(ch).equals("\n")) {
		        	++line;
		        	collumn = 1;
		        } else {
		        	if(!String.valueOf(ch).equals("\r")) {
		        		++collumn;
		        	}
		        }
//		        lexerResult = lexer.spitToken(Lexemes.getLexemeIndex(ch), String.valueOf(ch));
//		        if(lexerResult != null) {
//		        	System.out.println(lexerResult);
//		        }
//		        Lexemes.printLexemeIndex(ch);
//		        System.out.println("Do something with " + ch);
			}	
//			lexerResult = lexer.spitToken(81, "EOF");
//			System.out.println(lexerResult);
			lexer.feedTokenList(82, "$", line, collumn);
			lexer.feedTokenList(82, "$", line, collumn);
			tokenList = lexer.getTokenList();
			for (tToken token : tokenList) {
				System.out.println(token.getToken() + " - linha: " + String.valueOf(token.getLine()) + " - coluna: " + String.valueOf(token.getCollumn()));
			}
			buffer.close();
		} catch(IOException e) {
			System.out.println("Algo deu errado.");
		}
	}

}
