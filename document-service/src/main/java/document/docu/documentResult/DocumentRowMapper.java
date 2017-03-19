package document.docu.documentResult;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 19/3/2560.
 */
public class DocumentRowMapper implements RowMapper<DocumentResult> {
    @Override
    public DocumentResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentResult documentResult = new DocumentResult();

        documentResult.setDoc_id(rs.getInt("doc_id"));
        documentResult.setDoc_title(rs.getString("doc_title"));
        documentResult.setDoc_desc(rs.getString("doc_desc"));
        documentResult.setUser_fname(rs.getString("user_fname"));
        documentResult.setUser_lname(rs.getString("user_lname"));
        documentResult.setRevision_date(rs.getString("revision_date"));
        documentResult.setDoc_tag(rs.getString("doc_tag"));

        return documentResult;

    }
}
