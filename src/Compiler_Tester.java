import generator.Generator;
import lexer.Lexer;
import lexer.definitions.Lexeme;
import lexer.definitions.tToken;
import parser.Parser;
import semantic.Analyzer;
import tools.FileHandler;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Compiler_Tester {

	private static JFrame fatherFrame;

	public static void main(String[] args) {
		String source, dest;
		if(args.length == 2) {
			source = args[0];
			dest = args[1];
		} else {
			source = "prog.c";
			dest = "prog.s";
		}
		File testFile = new File(source);
		Charset charset = Charset.forName("UTF-8");
		Lexer lexer = new Lexer();
		Parser parser = new Parser();
		Analyzer analyzer;
		Generator generator;
		//String lexerResult = null;
		char ch = 0;
		int r = 0;
		int line = 1;
		int collumn = 1;
		
		ArrayList<tToken> tokenList = new ArrayList<tToken>();
		try {
			InputStream in = new FileInputStream(testFile);
			Reader reader = new InputStreamReader(in, charset);
			// buffer for efficiency
			Reader buffer = new BufferedReader(reader);
			//handleCharacters(buffer);
			//int r; //Posi��o antiga do r
			while ((r = buffer.read()) != -1) {
				ch = (char) r;
				lexer.feedTokenList(Lexeme.getLexemeIndex(String.valueOf(ch)), String.valueOf(ch), line, collumn);
				if (String.valueOf(ch).equals("\n")) {
					++line;
					collumn = 1;
				} else {
					if (!String.valueOf(ch).equals("\r")) {
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

			for(tToken token: tokenList) {
				if(token.getTokenType().equals("ERROR")) {
					System.out.println("Erro sintático. Linha: " + token.getLine() + " .Coluna: " +token.getCollumn());
					JOptionPane.showMessageDialog(fatherFrame, "Erro sintático. Caractere \"" + token.getTokenValue() + "\" não reconhecido. Linha: " + token.getLine() + " .Coluna: " + token.getCollumn(), "Erro sintático", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			if (parser.checkSyntax(tokenList)) {
				System.out.println("\nChecagem sintatica OK.");
				analyzer = new Analyzer(parser.getASTTree());
				if(analyzer.analyzeTree(parser.getASTTree())) {
					System.out.println("Análise semântica OK");
					generator = new Generator();
					FileHandler.createFile(dest);
					FileHandler.writeList(generator.cgen(parser.getASTTree()), dest);
				} else {
					System.out.println(analyzer.getErrorDescription());
					JOptionPane.showMessageDialog(fatherFrame, analyzer.getErrorDescription(), "Erro semântico", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				System.out.println("\nEste programa nao obedece a sintaxe da gramatica.");
				JOptionPane.showMessageDialog(fatherFrame, parser.getErrorDescription(), "Erro sintático", JOptionPane.WARNING_MESSAGE);
			}
			
		} catch(IOException e) {
			System.out.println("Algo deu errado.");
		}
	}

}
