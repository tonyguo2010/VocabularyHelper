package com.seakie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;


public class Translation {
	private String urlTemplate = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s";

	public String Translate(String transSrc, String inputCode, String languageCode) throws IOException {
		if (transSrc == null || transSrc.trim().equals(""))
			return "";
		if (languageCode == null || languageCode.trim().equals(""))
			return "";
		
//		System.out.println(transSrc);
		URL url = BuildUrl(transSrc, inputCode, languageCode);
//		System.out.println(url);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");

		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bReader = new BufferedReader(reader);

		StringBuilder sb = new StringBuilder();
		while (true) {
			String result = bReader.readLine();
			if (result == null) {
				break;
			}
			sb.append(result);
		}

		bReader.close();

		return sb.toString();
	}

	String GetTransResult(String jsonInput) throws IOException {
//		System.out.println(jsonInput);
		JSONArray trans = new JSONArray(jsonInput).getJSONArray(0);
		StringBuilder output = new StringBuilder();
		
		for(int index = 0; index < trans.length(); index++)
		{
		  String object = trans.getJSONArray(index).getString(0);
//		  System.out.println(object.toString());
		  output.append(object);
		}
//		System.out.println(start.getJSONArray(0).getJSONArray(0).getString(0));
		
		return output.toString();
	}
	
	URL BuildUrl(String transSrc, String inputCode, String languageCode) throws IOException {
		String outputLanguageCode = languageCode;
		
		String input = URLEncoder.encode(transSrc, StandardCharsets.UTF_8.toString());

		String adr = String.format(urlTemplate, inputCode, outputLanguageCode, input);
        URL url = new URL(String.format(urlTemplate, inputCode, outputLanguageCode, input));
        
        return url;
	}
}
