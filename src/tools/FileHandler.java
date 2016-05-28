package tools;
/*
 * Classe para manuseio de arquivos 
 * Baseada em c�digos do site stackoverflow.com de m�ltiplos autores
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

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

	public static void createFile(String fileName) {
		File file = new File(fileName);
		if(!file.exists() && !file.isDirectory()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeList(ArrayList<String> lines, String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
			for(String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
