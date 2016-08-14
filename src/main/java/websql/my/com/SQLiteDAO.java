package websql.my.com;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component("sqliteDAO")
public class SQLiteDAO {

	private JdbcTemplate jdbcTemplate;
	private MapSqlParameterSource par = new MapSqlParameterSource();
	private String sql;
	private SimpleJdbcInsert SimpleInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SimpleInsert = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name", "author");
	}

	public void insert(record rec) {
		// sql = "insert into mp3 (name, author) VALUES (?, ?)";
		// KeyHolder kh = new GeneratedKeyHolder();
		// jdbcTemplate.update(sql, new Object[] { rec.getName(),
		// rec.getAuthor() });
		// return (Integer) kh.getKey();
		par.addValue("author", rec.getAuthor());
		par.addValue("name", rec.getName());
		SimpleInsert.execute(par);

	}

	public void clean() {
		sql = "delete from mp3";
		jdbcTemplate.execute(sql);
	}

	public void deleteID(int id) {
		sql = "delete * from mp3 where id=:id";

		par.addValue("id", id);
		jdbcTemplate.update(sql, par);
	}

	public record getRecordbyID(int id) {
		sql = "select * from mp3 where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RecRowMapper());

	}

	private static final class RecRowMapper implements RowMapper<record> {

		@Override
		public record mapRow(ResultSet rs, int rowNum) throws SQLException {
			record rec = new record();
			rec.setId(rs.getInt("id"));
			rec.setAuthor(rs.getString("author"));
			rec.setName(rs.getString("name"));

			return rec;
		}
	}
}
