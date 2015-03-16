package com.arun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMatcher {
	public static void main(String args[]) {
		new TextMatcher().match("displaced");
	}

	public void match(String searchText) {
		URL url = null;
		BufferedReader in = null;
		try {
			url = new URL(
					"https://s3.amazonaws.com/BucketForStoringFiles/searchFiles/story.txt");

			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				//System.out.println(str);
				String regex=String.format("%s",searchText);
				Pattern pattern = Pattern.compile( regex );
			    Matcher matcher = pattern.matcher( str );
			    while ( matcher.find() ) {
			      System.out.println("match found: " + matcher.group());
			    }
			}

		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
