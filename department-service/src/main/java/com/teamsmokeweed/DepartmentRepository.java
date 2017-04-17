package com.teamsmokeweed;

import com.teamsmokeweed.model.getdepname.getalldep.GetAllDepResponse;
import com.teamsmokeweed.model.getdepname.getalldep.GetAllDepRowMapping;
import com.teamsmokeweed.model.getdepname.getdepname.GetDepNameResponse;
import com.teamsmokeweed.model.getdepname.getdepname.GetDepNameRowMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Armerino on 14/4/2560.
 */
@Repository
public class DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public GetDepNameResponse GetDepName(Integer dep_id){
        try {
            GetDepNameResponse getDepNameResponse = jdbcTemplate.queryForObject("SELECT dep_name FROM department WHERE dep_id = ?",
                    new Object[]{dep_id}, new GetDepNameRowMapping());
            return getDepNameResponse;
        }
        catch (Exception e){
            return new GetDepNameResponse();
        }

    }

    public List<GetAllDepResponse> GetAllDep(){

        List<GetAllDepResponse> getAllDepResponses = jdbcTemplate.query("SELECT dep_id, dep_name FROM department", new GetAllDepRowMapping());
        return getAllDepResponses;
    }
}
