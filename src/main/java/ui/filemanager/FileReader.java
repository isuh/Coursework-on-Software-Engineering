package ui.filemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import options.OptId;
import options.Options;

public class FileReader {
	/**
	 * Открывает файл и возвращает его содержимое в виде строки,
	 * после чего закрывает файл.
	 * @param selected путь к файлу
	 * @return содержимое файла
	 */
	public static String readTextFromFileToString(String selected) {
		StringBuilder sb = new StringBuilder();
				
		Charset charset = Charset.forName(Options.getInstance().getString(OptId.CHARSET));
		// TODO Работа с BOM для UTF-8 : http://commons.apache.org/proper/commons-io/javadocs/api-release/index.html?org/apache/commons/io/package-summary.html
		
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(selected), charset)) {
		    String line = null;
		    boolean firstline=true;
		    while ((line = reader.readLine()) != null) {
		    	if(!firstline) 
		    		sb.append('\n');
		    	sb.append(line);
		    	firstline=false;
		    }
		    reader.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return sb.toString();
	}
}
