package Pack01.repository;

import Pack01.domain.User;
import Pack01.domain.UserRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRankingRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRankingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserRanking> findUserRankingList(){

        RowMapper<UserRanking> rowMapper = getRowMapper();
        String query = "select * from user_ranking";
        List<UserRanking> userRankingList = jdbcTemplate.query(query, rowMapper);

        return userRankingList;
    }

    public UserRanking findUserRankingById(Long user_id){
        RowMapper<UserRanking> rowMapper = getRowMapper();
        String query = "select * from user_ranking where user_id = ?";
        List<UserRanking> userRankingList = jdbcTemplate.query(query,ps->{ps.setLong(1,user_id);}, rowMapper);
        System.out.println(userRankingList);
        return DataAccessUtils.singleResult(userRankingList);
    }


    private RowMapper<UserRanking> getRowMapper() {
        return (rs, rowNum) -> {
            UserRanking userRanking = new UserRanking(
                    rs.getLong("user_id"),
                    rs.getString("name"),
                    rs.getInt("correct"),
                    rs.getInt("tried_problem"),
                    rs.getInt("score"),
                    rs.getDouble("solve_ratio"),
                    rs.getInt("ranking")
                    );
            return userRanking;
        };
    }
}
