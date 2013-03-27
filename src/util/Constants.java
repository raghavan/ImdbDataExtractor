package util;


public interface Constants {
		//DB props
		String dbDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:8889/imdb";
		String username = "root";
		String password = "root";
		
			
		//Query
		String QUERY_INSERT_RATING = "insert into rating(vote_ratio,vote_count,rating,movie_name,year) values ";

		
		//File
		String RATINGS_LIST_FILE = "files/ratings_new.list";
		
		//Symbols
		String QUOTE = "\"";
		String COMMA = ",";
		
	
}
