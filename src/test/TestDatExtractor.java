package test;

import org.junit.Test;

import model.Rating;
import dataextractor.RatingExtractAndWrite;
import dbhandler.DBHandler;

public class TestDatExtractor {

	@Test
	public void testRatingMaker() {
		DBHandler dbHandler = new DBHandler();

		Rating rating = null;
		try {
			rating = RatingExtractAndWrite
					.makeRating("0000012200      57   6.3  Yggdrasill: Whose Roots Are Stars in the Human Mind (1997)");
			System.out.println(rating);
			String insertStmt = RatingExtractAndWrite.makeInsertStmtForRating(rating);
			boolean val = dbHandler.addRecordIntoDb(rating);
			System.out.println(val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testRatingMakerWithEndSplChar() {
		DBHandler dbHandler = new DBHandler();
		Rating rating = null;
		try {
			rating = RatingExtractAndWrite.makeRating("3.....12.2       8   7.6  Yoga Exercise Workout (1993/I) (V)");
			System.out.println(rating);
			String insertStmt = RatingExtractAndWrite.makeInsertStmtForRating(rating);
			boolean val = dbHandler.addRecordIntoDb(rating);
			System.out.println(val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRatingMakerWithYearSplChar() {
		DBHandler dbHandler = new DBHandler();

		Rating rating = null;
		try {
			rating = RatingExtractAndWrite.makeRating("00.0012201      57   6.8  \"$#*! My Dad Says\" (2010) {Code Ed (#1.4)}");
			System.out.println(rating);
			//String insertStmt = RatingExtractAndWrite.makeInsertStmtForRating(rating);
			//boolean val = dbHandler.addRecordIntoDb(rating);
			//System.out.println(val);	
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
