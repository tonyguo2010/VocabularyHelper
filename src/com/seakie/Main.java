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
//		String result = new Dictionary().trans("rectal\natomic\nlaptop");
//		System.out.println(result);

//		String meaning = "һֱ/���/����/����/��/����/����/��ȥ/�ʵ�/������/����/��Ժ/����/�ƻ�/�շ�/����/������/����/���/������/ isnt /����/��ɫ/��ɫ/��ɫ/����/";
//		String[] meanings = meaning.split("/");
//		cleanDup(meanings);
//		System.out.println(Arrays.toString(meanings));
	}

}
