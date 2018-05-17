package org.ustb.MicroServiceMgr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ustb.MicroServiceMgr.domain.Machine;
import org.ustb.MicroServiceMgr.domain.Service;

import java.util.List;

@Repository
public class ServiceDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Service service){
        List<Service> list = jdbcTemplate.query("SELECT * FROM service WHERE instanceid = ?",
                new Object[]{service.getInstanceId()}, new BeanPropertyRowMapper(Service.class));

        if (list != null && list.size() > 0) {
            return jdbcTemplate.update("UPDATE service " +
                            "SET hostname=?," +
                            "app=?," +
                            "ipaddr=?," +
                            "homepageurl=?," +
                            "statuspageurl=?," +
                            "healthcheckurl=?," +
                            "lastupdatedtimestamp=?," +
                            "lastmodify=?," +
                            "result=? " +
                            "WHERE instanceid=?",
                    service.getHostName(),
                    service.getApp(),
                    service.getIpAddr(),
                    service.getHomePageUrl(),
                    service.getStatusPageUrl(),
                    service.getHealthCheckUrl(),
                    service.getLastUpdatedTimestamp(),
                    service.getLastModify(),
                    service.getResult(),
                    service.getInstanceId());
        } else {
            return jdbcTemplate.update("INSERT INTO " +
                            "service(instanceid," +
                            "hostname," +
                            "app," +
                            "ipaddr," +
                            "homepageurl," +
                            "statuspageurl," +
                            "healthcheckurl," +
                            "lastupdatedtimestamp," +
                            "lastmodify," +
                            "result) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    service.getInstanceId(),
                    service.getHostName(),
                    service.getApp(),
                    service.getIpAddr(),
                    service.getHomePageUrl(),
                    service.getStatusPageUrl(),
                    service.getHealthCheckUrl(),
                    service.getLastUpdatedTimestamp(),
                    service.getLastModify(),
                    service.getResult());
        }
    }

    public List<Service> findAll(){
        return jdbcTemplate.query("SELECT * FROM service ORDER BY lastmodify DESC",
                new Object[]{},
                new BeanPropertyRowMapper(Service.class));
    }
}
