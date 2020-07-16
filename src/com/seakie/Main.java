package com.seakie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input the page link:");
		String link = scan.nextLine();
		WebFetcher.parse(link);
		scan.close();
		
//		WebFetcher.parse("https://www.cp24.com/news/mississauga-man-74-charged-after-used-condoms-tied-to-9-parked-cars-1.5023159");
//		String result = new Dictionary().trans("rectal/atomic/laptop");
//		System.out.println(result);

//		String meaning = "һֱ/���/����/����/��/����/����/��ȥ/�ʵ�/������/����/��Ժ/����/�ƻ�/�շ�/����/������/����/���/������/ isnt /����/��ɫ/��ɫ/��ɫ/����/";
//		String[] meanings = meaning.split("/");
//		cleanDup(meanings);
//		System.out.println(Arrays.toString(meanings));
	}

	private static void cleanDup(String[] meanings) {
		ArrayList<String> list = new ArrayList<String>();
		
		for (int index = 0; index < meanings.length - 1; index++) {
			if (meanings[index].equals(meanings[index + 1]) == false){
				list.add(meanings[index]);
			}
		}
		
		System.out.println(list);
		Arrays.fill(meanings, null);
		String[] temp = (String[]) list.toArray(new String[0]);
		System.arraycopy(temp, 0, meanings, 0, temp.length);
	}

}
