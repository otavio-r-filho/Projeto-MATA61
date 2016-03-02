package tools;
/*
 * Classe para manuseio de arquivos 
 * Baseada em códigos do site stackoverflow.com de múltiplos autores
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class FileHandler {
	
//	public static void main(String[] args) throws IOException {
//	    // replace this with a known encoding if possible
//	    Charset encoding = Charset.defaultCharset();
//	    for (String filename : args) {
//	        File file = new File(filename);
//	        handleFile(file, encoding);
//	    }
//	}
	
	private static void handleFile(File file, Charset encoding)
	        throws IOException {
	    try (InputStream in = new FileInputStream(file);
	         Reader reader = new InputStreamReader(in, encoding);
	         // buffer for efficiency
	         Reader buffer = new BufferedReader(reader)) {
	        handleCharacters(buffer);
	    }
	}
	
	private static void handleCharacters(Reader reader)
	        throws IOException {
	    int r;
	    while ((r = reader.read()) != -1) {
	        char ch = (char) r;
	        System.out.println("Do something with " + ch);
	    }
	}

}
