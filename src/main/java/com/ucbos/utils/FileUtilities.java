package com.ucbos.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * Utility Class creates a CSV with a given format
 * @author sdontireddy
 *
 */
public class FileUtilities {

	private static final char DEFAULT_SEPARATOR = ',';

	public static void writeLine(Writer w, List<String> values) throws IOException {
		writeLine(w, values, DEFAULT_SEPARATOR, ' ');
	}

	public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
		writeLine(w, values, separators, ' ');
	}

	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

		boolean first = true;

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}
			if (customQuote == ' ') {
				sb.append(followCVSformat(value));
			} else {
				sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
			}

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());

	}
	
	public static void createReportCSV(String fileName ,List<String> values,boolean printTimeStamp) throws Exception{
        FileWriter writer = new FileWriter(fileName,true);
        
		if (printTimeStamp) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();

			FileUtilities.writeLine(writer, Arrays.asList("-----" + df.format(date) + "-----"));
		}
        FileUtilities.writeLine(writer, values);
        
        writer.flush();
        writer.close();

	}

}