package org.ustb.MicroServiceMgr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ustb.MicroServiceMgr.controller.MicroServiceMgrController;
import org.ustb.MicroServiceMgr.domain.Appdoc;

import java.util.List;

@Repository
public class AppdocDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AppdocDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Appdoc doc){
        List<Appdoc> list = jdbcTemplate.query("SELECT * FROM appdoc WHERE title = ?",
                new Object[]{doc.getTitle()}, new BeanPropertyRowMapper(Appdoc.class));

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

    public List<Appdoc> findAll(){
        String sql = "SELECT * FROM appdoc";
        LOG.info("RUN sql[{}]", sql);
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Appdoc.class));
    }

    public Appdoc findByTitle(String title){
        String sql = "SELECT * FROM appdoc WHERE title = ?";
        LOG.info("RUN sql[{}]", sql);
        List<Appdoc> list = jdbcTemplate.query(sql, new Object[]{title}, new BeanPropertyRowMapper(Appdoc.class));

        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
