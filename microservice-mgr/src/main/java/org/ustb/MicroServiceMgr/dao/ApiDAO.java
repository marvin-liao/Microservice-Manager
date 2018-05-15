package org.ustb.MicroServiceMgr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ustb.MicroServiceMgr.controller.MicroServiceMgrController;
import org.ustb.MicroServiceMgr.domain.Api;

import java.util.List;

@Repository
public class ApiDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ApiDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Api doc){
        List<Api> list = jdbcTemplate.query("SELECT * FROM appdoc WHERE title = ?",
                new Object[]{doc.getTitle()}, new BeanPropertyRowMapper(Api.class));

        if (list != null && list.size() > 0) {
            String sql = "UPDATE appdoc SET description=?, doc=? WHERE title=?";
            LOG.info("RUN sql[{}]", sql);
            return jdbcTemplate.update(sql, doc.getDescription(), doc.getDoc(), doc.getTitle());
        } else {
            String sql = "INSERT INTO appdoc(title, description, doc) values(?, ?, ?)";
            LOG.info("RUN sql[{}]", sql);
            return jdbcTemplate.update(sql, doc.getTitle(), doc.getDescription(), doc.getDoc());
        }
    }

    public List<Api> findAll(){
        String sql = "SELECT * FROM appdoc";
        LOG.info("RUN sql[{}]", sql);
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Api.class));
    }

    public Api findByTitle(String title){
        String sql = "SELECT * FROM appdoc WHERE title = ?";
        LOG.info("RUN sql[{}]", sql);
        List<Api> list = jdbcTemplate.query(sql, new Object[]{title}, new BeanPropertyRowMapper(Api.class));

        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
