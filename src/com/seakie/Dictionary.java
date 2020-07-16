package com.seakie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import com.seakie.LanguageCode;
import com.seakie.Translation;

public class Dictionary extends HashMap<String, Word> {

	public String trans(String input) throws IOException {
		Translation trans = new Translation();
		String newName = trans.GetTransResult(trans.Translate(input, LanguageCode.English, LanguageCode.Chinese));
		String output = URLDecoder.decode(newName, StandardCharsets.UTF_8.toString());
		return output;
	}
	public void addWord(String wordName, int count) {
		Word word = this.get(wordName);
		if (word == null) {
			word = new Word(wordName, count);
			this.put(wordName, word);
		} else {
			word.count += count;
		}
	}
	
	public void output() throws IOException{
		ArrayList<Word> results = new ArrayList<>(this.values());
		
//		results.sort(Collections.reverseOrder(Comparator.comparing(Word::getCount)));
		results.sort(Comparator.comparing(Word::getCount));
		
		translate(results);
		save(results);
	}

	private void translate(ArrayList<Word> words) {
		StringBuilder parts = new StringBuilder();
		
		StringBuilder accumulation = new StringBuilder();
		for (int index = 0; index < words.size(); index++){
			parts.append(words.get(index).getWord() + "\n");
			
			// url cannot be too long
			if (parts.length() >= 4500) {
				// send at once
				try {
//					System.out.println(parts.toString());
					String temp = trans(parts.toString());
					accumulation.append(temp);
					accumulation.append("\n");
//					System.out.println(temp);
//					System.out.println("==============");
					parts.setLength(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// send the rest
		try {
//			System.out.println(parts.toString());
			String temp = trans(parts.toString());
			accumulation.append(temp);
			accumulation.append("\n");
//			System.out.println(temp);
//			System.out.println("==============");
			parts.setLength(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		String[] meaning = accumulation.toString().split("\n");
//		cleanDup(meaning);
		for (int index = 0; index < words.size(); index++) {
			if (index < meaning.length){
				words.get(index).setMeaning(meaning[index]);
			}
		}
	}

	public void cleanDup(String[] meaning) {
		ArrayList<String> list = new ArrayList<String>();
		
		for (int index = 0; index < meaning.length; index++) {
			if (index == 0) {
				list.add(meaning[index]);
			} else if (meaning[index].equals(meaning[index - 1]) == false){
				list.add(meaning[index]);
			}
		}
		
		System.out.println(list);
		Arrays.fill(meaning, null);
		String[] temp = (String[]) list.toArray(new String[0]);
		System.arraycopy(temp, 0, meaning, 0, temp.length);
	}
	private void translate_ex(ArrayList<Word> results) {
		StringBuilder sb = new StringBuilder();
		StringBuilder parts = new StringBuilder();
		
		for (int index = 0; index < results.size(); index++){
			parts.append(results.get(index).getWord() + "#");
			
			if ((index + 1) % 5 == 0){
				String meanings = null;
				try {
					Thread.sleep(3000);
					meanings = trans(parts.toString());
					sb.append(meanings);
					System.out.println(parts.toString());
					System.out.println(meanings.toString());
					System.out.println();
					parts.setLength(0);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		String meanings = null;
		try {
			meanings = trans(parts.toString());
			sb.append(meanings);
			parts.setLength(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] meaning = sb.toString().split("#");
		for (int index = 0; index < results.size(); index++) {
			results.get(index).setMeaning(meaning[index]);
		}
	}
	protected void save(ArrayList<Word> results) throws IOException {
		System.out.println(results);
	}
	
}
