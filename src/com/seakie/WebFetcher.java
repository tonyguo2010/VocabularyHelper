package com.seakie;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebFetcher {
	static private Dictionary dict = new ExcelDictionary();

	public static void parse(String url) throws IOException {
		Connection conn = Jsoup.connect(url);
		Document soup = conn.get();
		if (conn.response().statusCode() == 200) {
//			System.out.println(soup.text());
			String line = soup.text().replaceAll("[^A-Za-z \\-]", " ").toLowerCase();
//			System.out.println(line);
			String[] words = line.split("\\s+");
			setDict(words);
//			System.out.println(Arrays.toString(words));
		} 
	}

	private static void setDict(String[] words) throws IOException {
		for (String word : words) {
			dict.addWord(word, 1);
		}
		
		dict.output();
	}

}
