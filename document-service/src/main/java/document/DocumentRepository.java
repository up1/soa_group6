package document;

import document.docu.DocumentResult;
import document.docu.DocumentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jongzazaal on 18/3/2560.
 */
@Repository
public class DocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<DocumentResult> allDocumentById(int user_id) {

        List<DocumentResult> list = jdbcTemplate.query("SELECT d.doc_id, d.doc_title, d.doc_desc, u.user_fname, u.user_lname , r.revision_date, d.doc_tag FROM docs d JOIN shares s ON (d.doc_id = s.doc_id) JOIN dep dep ON (s.shares_id = dep.dep_id ) JOIN (SELECT doc_id, MAX(revision_date) AS 'revision_date' FROM revision GROUP BY doc_id) r ON (d.doc_id = r.doc_id) JOIN users u ON (d.user_id = u.user_id) WHERE d.user_id = ? OR s.shares_id = ? GROUP BY d.doc_id"
                , new Object[]{user_id, user_id}, new DocumentRowMapper());

        if(list.size()>0){
            return list;
        }
        return list;
    }
}
