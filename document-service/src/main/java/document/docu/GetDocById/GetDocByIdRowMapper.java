package document.docu.GetDocById;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jongzazaal on 20/3/2560.
 */
public class GetDocByIdRowMapper implements RowMapper<GetDocByIdResource> {


    @Override
    public GetDocByIdResource mapRow(ResultSet rs, int rowNum) throws SQLException {
        GetDocByIdResource getDocByIdResource = new GetDocByIdResource();
        getDocByIdResource.setDoc_id(rs.getInt("doc_id"));
        getDocByIdResource.setDoc_tittle(rs.getString("doc_title"));
        getDocByIdResource.setDoc_desc(rs.getString("doc_desc"));
        getDocByIdResource.setUser_id(rs.getInt("user_id"));
        getDocByIdResource.setDoc_date(rs.getString("doc_date"));
        getDocByIdResource.setDoc_tag(rs.getString("doc_tag"));

        String shares_id = rs.getString("shares_id");
        List<Integer> sharesList = new ArrayList<>();
        for(String s:shares_id.split(", ")){
            sharesList.add(Integer.parseInt(s));
        }
        getDocByIdResource.setShares_id(sharesList);

        String dep_name = rs.getString("dep_name");
        List<String> depNameList = new ArrayList<>();
        for(String s:dep_name.split(", ")){
            depNameList.add(s);
        }
        getDocByIdResource.setDep_name(depNameList);



        return getDocByIdResource;

    }
}
