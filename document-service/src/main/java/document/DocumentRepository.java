package document;

import document.docu.PostDocument.PostDocResource;
import document.docu.PostDocument.PostDocStatus;
import document.docu.UserPass.UserPassResult;
import document.docu.UserPass.UserPassRowMapper;
import document.docu.documentResult.DocumentResult;
import document.docu.documentResult.DocumentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        if (list.size() > 0) {
            return list;
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Boolean isUser(String user_id) {

        List<UserPassResult> listUser = jdbcTemplate.query("SELECT user_username FROM users WHERE user_id = ?"
                , new Object[]{user_id}, new UserPassRowMapper());

        if (listUser.size() > 0) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = false)
    public PostDocStatus createDoc(PostDocResource postDocResource, List<String> linkLoad) {

        try {
//        this.jdbcTemplate.update("INSERT INTO docs(doc_title, doc_desc, user_id, doc_tag) VALUES(?, ?, ?, ?);",
//                new Object[]{postDocResource.getDoc_title(), postDocResource.getDoc_desc(), postDocResource.getUser_id(),
//                        postDocResource.getDoc_tag()
//        });

            //---------------------------------------------------------------------------------

            final String INSERT_SQL = "INSERT INTO docs(doc_title, doc_desc, user_id, doc_tag) VALUES(?, ?, ?, ?);";
//            final String name = "lenny";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        @Override
                        public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                            java.sql.PreparedStatement ps = (PreparedStatement) con.prepareStatement(INSERT_SQL, new String[]{"a_id"});
                            ps.setString(1, postDocResource.getDoc_title());
                            ps.setString(2, postDocResource.getDoc_desc());
                            ps.setInt(3, postDocResource.getUser_id());
                            ps.setString(4, postDocResource.getDoc_tag());
                            return ps;
                        }
                    },
                    keyHolder);
            int doc_id = keyHolder.getKey().intValue();

            for(String l:linkLoad){

                if(!insertmFile(doc_id, l)){
                    return new PostDocStatus(false, "erroe insert mfile");
                }
                System.out.println(l+"//");
            }
            //---------------------------------------------------------------

            return new PostDocStatus(true, "sucess");
        } catch (Exception e) {
            return new PostDocStatus(false, "error");
        }

    }

    @Transactional(readOnly = false)
    public boolean insertmFile(int doc_id, String linkLoad) {

        try {

            this.jdbcTemplate.update("INSERT INTO mfile(revision_id, mfile_raw_filename)\n" +
                            "VALUES((SELECT MAX(revision_id) AS 'revision_id' FROM `revision`WHERE doc_id = ?), ?\n" +
                            ");"
                    , new Object[]{doc_id, linkLoad});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public File multipartFileToFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }


    @Transactional(readOnly = false)
    public PostDocStatus intt(PostDocResource postDocResource) {
        System.out.println("1");


//        this.jdbcTemplate.("INSERT INTO a(a_name) VALUES (?) RETURNING a_name;", new Object[]{"ssss"});

        final String INSERT_SQL = "INSERT INTO a(a_name) VALUES (?) RETURNING a_name;";
        final String name = "lenny";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                        java.sql.PreparedStatement ps = (PreparedStatement) con.prepareStatement(INSERT_SQL, new String[]{"a_id"});
                        ps.setString(1, name);
                        return ps;
                    }
                },
                keyHolder);

        System.out.println("2//" + keyHolder.getKeyList());
//        return new PostDocStatus(true, "sucess//"+keyHolder.getKey());

        System.out.println("3");
        return new PostDocStatus(true, "sucess");
    }


}
