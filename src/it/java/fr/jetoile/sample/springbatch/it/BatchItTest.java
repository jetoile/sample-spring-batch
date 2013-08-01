package fr.jetoile.sample.springbatch.it;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import fr.jetoile.sample.springbatch.batch.reader.FooRowMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;

import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.Operations.sql;
import static org.fest.assertions.Assertions.assertThat;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/fr/jetoile/sample/springbatch/it/springbatch.xml"})
public class BatchItTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@After
	public void tearDown() {
		Operation operation = sequenceOf(
				sql("DROP TABLE FOO")
		);

		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
	}

	@Before
	public void setup() throws Exception {

		Operation operation = sequenceOf(
				sql("CREATE TABLE FOO (ID INTEGER, NOM VARCHAR, MOTCLE VARCHAR )"),
				insertInto("FOO")
						.columns("ID", "NOM", "MOTCLE")
						.values(1, "emploi", "Bts  OU en  ou altermance")
						.values(2, "emploi2", "Bts ET en et altermance")
						.values(3, "emploi3", "Bts (altermance)")
						.values(4, "emploi4", null)
						.values(5, "emploi5", "Bts SAUF(altermance)")
						.values(6, "emploi6", "Bts SAUF (altermance)")
						.values(7, "emploi7", "Bts SAUF   (altermance)")
						.values(8, "emploi8", "Bts NOT   (altermance)")

						.build()
		);

		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
	}

	@Test
	public void testJob() throws Exception {

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());


		String fooReaderSql = "SELECT ID, NOM, MOTCLE FROM FOO WHERE ID=?";

		FooModel res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"1"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
		assertThat(res.getId()).isEqualTo(1);
		assertThat(res.getNom()).isEqualTo("emploi");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"2"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
		assertThat(res.getId()).isEqualTo(2);
		assertThat(res.getNom()).isEqualTo("emploi2");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"3"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts altermance");
		assertThat(res.getId()).isEqualTo(3);
		assertThat(res.getNom()).isEqualTo("emploi3");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"4"}, new FooRowMapper());
		assertThat(res.getMotcle()).isNull();
		assertThat(res.getId()).isEqualTo(4);
		assertThat(res.getNom()).isEqualTo("emploi4");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"5"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts SAUF(altermance)");
		assertThat(res.getId()).isEqualTo(5);
		assertThat(res.getNom()).isEqualTo("emploi5");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"6"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts SAUF (altermance)");
		assertThat(res.getId()).isEqualTo(6);
		assertThat(res.getNom()).isEqualTo("emploi6");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"7"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts SAUF   (altermance)");
		assertThat(res.getId()).isEqualTo(7);
		assertThat(res.getNom()).isEqualTo("emploi7");
		System.out.println(res);

		res = jdbcTemplate.queryForObject(fooReaderSql, new String[]{"8"}, new FooRowMapper());
		assertThat(res.getMotcle()).isEqualTo("Bts NOT   (altermance)");
		assertThat(res.getId()).isEqualTo(8);
		assertThat(res.getNom()).isEqualTo("emploi8");
		System.out.println(res);
	}



}
