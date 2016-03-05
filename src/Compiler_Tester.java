import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import lexer.definitions.Lexemes;

public class Compiler_Tester {

	public static void main(String[] args) {
		File testFile = new File("C:\\arquivoTeste.c");
		Charset charset = Charset.forName("UTF-8");
		try {
			InputStream in = new FileInputStream(testFile);
			Reader reader = new InputStreamReader(in, charset);
	         // buffer for efficiency
	         Reader buffer = new BufferedReader(reader);
	        //handleCharacters(buffer);
			int r;
			while ((r = buffer.read()) != -1) {
		        char ch = (char) r;
		        Lexemes.printLexemeIndex(ch);
		        //System.out.println("Do something with " + ch);
			}	
			buffer.close();
		} catch(IOException e) {
			System.out.println("Algo deu errado.");
		}
	}

}