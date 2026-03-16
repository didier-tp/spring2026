package tp.appliSpring.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tp.appliSpring.core.entity.Compte;

//@Repository //@Component de type DAO/Repository
@Qualifier("jdbc")
public class DaoCompteJdbc /*extends JdbcDaoSupport*/ implements DaoCompte {
	
	private final String INSERT_SQL = "INSERT INTO compte(label, solde)  values(:label,:solde)";
	private final String UPDATE_SQL = "UPDATE compte set label=:label , solde=:solde where numero=:numero";
	private final String FETCH_ALL_SQL = "select * from compte";
	private final String FETCH_BY_NUM_SQL = "select * from compte where numero=:numero";
	private final String DELETE_BY_NUM_SQL = "delete from compte where numero=:numero";
	
	//@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Compte findById(Long numCpt) {
		Compte compte = null;
		Map<String, Long> parameters = new HashMap<String, Long>();
		parameters.put("numero", numCpt);
		/*
		try {
			compte =  namedParameterJdbcTemplate.queryForObject(FETCH_BY_NUM_SQL, 
					                                                  parameters, 
					                                                  new CompteMapper());
		} catch (DataAccessException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		*/
		List<Compte> comptes = namedParameterJdbcTemplate.query(FETCH_BY_NUM_SQL, 
                parameters, 
                new CompteMapper());
		compte = comptes.isEmpty()?null:comptes.get(0);
		return compte;
	}

	@Override
	public Compte save(Compte compte) {
		if(compte==null) 
			throw new IllegalArgumentException("compte must be not null");
		return (compte.getNumero()==null)?insert(compte):update(compte);
	}
	
	public Compte insert(Compte compte) {
		KeyHolder holder = new GeneratedKeyHolder(); //to retreive auto_increment value of pk
		SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("label", compte.getLabel())
		.addValue("solde", compte.getSolde());
		namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);; //pour h2
		//namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder,  new String[] { "numero" }); //pour postgres
		compte.setNumero(holder.getKey().longValue());//store auto_increment pk in instance to return
		return compte;
	}
	
	public Compte update(Compte compte) {
		//A CODER/COMPLETER  EN TP
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		//A CODER/COMPLETER  EN TP
		return null;
	}

	@Override
	public void deleteById(Long numCpt) {
		//A CODER/COMPLETER  EN TP
	}

}

//classe auxiliaire "CompteMapper" pour convertir Resultset jdbc 
//en instance de la classe Compte :
class CompteMapper implements RowMapper<Compte> {
	@Override
	public Compte mapRow(ResultSet rs, int rowNum) throws SQLException {
		Compte compte = new Compte();
		compte.setNumero(rs.getLong("numero"));
		compte.setLabel(rs.getString("label"));
		compte.setSolde(rs.getDouble("solde"));
		return compte;
	}
}
