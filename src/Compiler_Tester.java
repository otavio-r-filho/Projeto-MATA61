import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import lexer.definitions.Lexemes;
import lexer.Lexer;

public class Compiler_Tester {

	public static void main(String[] args) {
		File testFile = new File("C:\\arquivoTeste.c");
		Charset charset = Charset.forName("UTF-8");
		Lexer lexer = new Lexer();
		String lexerResult = null;
		char ch = 0;
		int r = 0;
		try {
			InputStream in = new FileInputStream(testFile);
			Reader reader = new InputStreamReader(in, charset);
	         // buffer for efficiency
	         Reader buffer = new BufferedReader(reader);
	        //handleCharacters(buffer);
			//int r; //Posição antiga do r
			while ((r = buffer.read()) != -1) {
		        ch = (char) r;
		        lexerResult = lexer.spitToken(Lexemes.getLexemeIndex(ch), String.valueOf(ch));
		        if(lexerResult != null) {
		        	System.out.println(lexerResult);
		        }
		        //Lexemes.printLexemeIndex(ch);
		        //System.out.println("Do something with " + ch);
			}	
			lexerResult = lexer.spitToken(81, "EOF");
			System.out.println(lexerResult);
			buffer.close();
		} catch(IOException e) {
			System.out.println("Algo deu errado.");
		}
	}

}
