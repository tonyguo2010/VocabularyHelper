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

//		String meaning = "一直/射击/瘟疫/刺伤/会/警告/观众/过去/帐单/恶作剧/上午/法院/高速/计划/收费/三联/储备金/百万/外观/驱动器/ isnt /交易/角色/棕色/棕色/饰面/";
//		String[] meanings = meaning.split("/");
//		cleanDup(meanings);
//		System.out.println(Arrays.toString(meanings));
	}

}
