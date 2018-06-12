package org.ustb.MicroServiceMgr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ustb.MicroServiceMgr.controller.MicroServiceMgrController;
import org.ustb.MicroServiceMgr.domain.Api;
import org.ustb.MicroServiceMgr.domain.ApiItem;

import java.util.List;

@Repository
public class ApiDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ApiDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateResult(String title, int result){
        return jdbcTemplate.update("UPDATE api_item" +
                        " SET result=?" +
                        " WHERE title=?",
                result, title);
    }

    public int save(Api doc){
        List<Api> list = jdbcTemplate.query("SELECT * FROM api WHERE title = ?",
                new Object[]{doc.getTitle()}, new BeanPropertyRowMapper(Api.class));

        if (list != null && list.size() > 0) {
            return jdbcTemplate.update("UPDATE api SET description=?,doc=?,host=?,basepath=?,lastmodify=?,result=? WHERE title=?",
                    doc.getDescription(),
                    doc.getDoc(),
                    doc.getHost(),
                    doc.getBasePath(),
                    doc.getLastModify(),
                    doc.getResult(),
                    doc.getTitle());
        } else {
            return jdbcTemplate.update("INSERT INTO api(title,description,doc,host,basepath,lastmodify,result) values(?,?,?,?,?,?,?)",
                    doc.getTitle(),
                    doc.getDescription(),
                    doc.getDoc(),
                    doc.getHost(),
                    doc.getBasePath(),
                    doc.getLastModify(),
                    doc.getResult());
        }
    }


    public int clearItems(String title){
        return jdbcTemplate.update("DELETE FROM api_item WHERE title = ?", title);
    }

    public int saveItem(ApiItem item){
        return jdbcTemplate.update("INSERT INTO " +
                        "api_item(title, path, method, summary, operationId, lastmodify, result) " +
                        "values(?, ?, ?, ?, ?, ?, ?)",
                item.getTitle(),
                item.getPath(),
                item.getMethod(),
                item.getSummary(),
                item.getOperationId(),
                item.getLastModify(),
                item.getResult());
    }

    public List<ApiItem> findItemsByTitle(String title){
        String sql = "SELECT * FROM api_item WHERE title = ?";
        return jdbcTemplate.query(sql, new Object[]{title}, new BeanPropertyRowMapper(ApiItem.class));
    }

    public List<Api> findAll(){
        String sql = "SELECT * FROM api";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Api.class));
    }

    public Api findByTitle(String title){
        String sql = "SELECT * FROM api WHERE title = ?";
        List<Api> list = jdbcTemplate.query(sql, new Object[]{title}, new BeanPropertyRowMapper(Api.class));

        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
