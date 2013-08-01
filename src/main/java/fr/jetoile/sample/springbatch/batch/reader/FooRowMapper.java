package fr.jetoile.sample.springbatch.batch.reader;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FooRowMapper implements RowMapper<FooModel> {

	@Override
	public FooModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		FooModel foo = new FooModel();
		foo.setId(rs.getLong("ID"));
		foo.setNom(rs.getString("NOM"));
		foo.setMotcle(rs.getString("MOTCLE"));
		return foo;
	}
}
