package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LexiconSQL {

	
	
	
	public static PreparedStatement getInsertLexiconGramStatement(Connection c) throws SQLException{
		String sql = "INSERT INTO `amazon`.`lexicon` (`word_hash`, `number`) VALUES (?, ?);";
		return c.prepareStatement(sql);
	}
	
	public static void insertGramBatch(PreparedStatement st, int hash, int nb) throws SQLException{
		st.setInt(1, hash);
		st.setInt(2, nb);
		st.addBatch();
		
	}
}
