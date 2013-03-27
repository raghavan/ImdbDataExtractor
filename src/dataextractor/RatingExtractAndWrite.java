package dataextractor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Rating;

import dbhandler.DBHandler;

import util.Constants;

public class RatingExtractAndWrite {

	public static void main(String args[]) {
		FileInputStream fstream = null;
		List<String> notAddedIntoDb = new ArrayList<String>();
		try {
			fstream = new FileInputStream(Constants.RATINGS_LIST_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		DBHandler dhDbHandler = new DBHandler();
		String strLine = null;
		try {
			while ((strLine = br.readLine()) != null) {
				strLine = strLine.trim();
				Rating rating = makeRating(strLine);				
				if (rating != null) {
					//String insertStmt = makeInsertStmtForRating(rating);
					if (!dhDbHandler.addRecordIntoDb(rating)) {
						notAddedIntoDb.add(strLine);
					}
				}else{
					notAddedIntoDb.add(strLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();			
			notAddedIntoDb.add(strLine);
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(String str : notAddedIntoDb){
			System.out.println("Not added " +str);
		}
	}

	public static String makeInsertStmtForRating(Rating rating) throws Exception{
		StringBuffer insertStmt = new StringBuffer(Constants.QUERY_INSERT_RATING);
		insertStmt.append(" (" + Constants.QUOTE + rating.getVoteRatio() + Constants.QUOTE + Constants.COMMA
				+ rating.getVoteCount() + Constants.COMMA + rating.getRating() + Constants.COMMA + Constants.QUOTE
				+ rating.getMovieName() + Constants.QUOTE + Constants.COMMA + rating.getYear() + ")");
		return insertStmt.toString();
	}

	public static Rating makeRating(String strLine) throws Exception {
		if (!strLine.isEmpty()) {
			strLine = strLine.trim();
			String test[] = strLine.split(" ");
			int lastWordLength = test[test.length - 1].length();
			try {
				if (lastWordLength >= 6) {
					String year = test[test.length - 1].substring(1, 5);
					Integer.parseInt(year);
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				strLine = strLine.substring(0, strLine.length() - lastWordLength);
			}
			strLine = strLine.trim();

			String strArr[] = strLine.split(" ");
			List<String> strWithEmptySpaceRemoved = new ArrayList<String>();
			for (String str : strArr) {
				if (!str.isEmpty()) {
					strWithEmptySpaceRemoved.add(str);
				}
			}
			Rating rating = new Rating();
			StringBuffer movieName = new StringBuffer();
			for (int i = 0; i < (strWithEmptySpaceRemoved.size() - 1); i++) {
				String currentStr = strWithEmptySpaceRemoved.get(i);
				switch (i) {
					case 0 :
						rating.setVoteRatio(currentStr);
						break;
					case 1 :
						int valInt = 0;
						try {
							valInt = Integer.parseInt(currentStr);
						} catch (NumberFormatException e) {
							e.printStackTrace();
							System.out.println(strLine);
						}
						rating.setVoteCount(valInt);
						break;
					case 2 :
						float valFloat = 0F;
						try {
							valFloat = Float.parseFloat(currentStr);
						} catch (NumberFormatException e) {
							e.printStackTrace();
							System.out.println(strLine);							
						}
						rating.setRating(valFloat);
						break;
					default :
						movieName.append(currentStr + " ");
				}
			}
			rating.setMovieName(movieName.substring(0, movieName.length() - 1));
			String year = strWithEmptySpaceRemoved.get(strWithEmptySpaceRemoved.size() - 1).substring(1, 5);
			rating.setYear(Integer.parseInt(year));

			return rating;
		}
		return null;
	}

}
