package org.ustb.MicroServiceMgr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ustb.MicroServiceMgr.domain.Machine;

import java.util.List;

@Repository
public class MachineDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MachineDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Machine machine){
        List<Machine> list = jdbcTemplate.query("SELECT * FROM machine WHERE host = ?",
                new Object[]{machine.getHost()}, new BeanPropertyRowMapper(Machine.class));

        if (list != null && list.size() > 0) {
            return jdbcTemplate.update("UPDATE machine SET name=?,ip=?,time=?,image=?,lastmodify=?,result=? WHERE host=?",
                    machine.getName(),
                    machine.getIp(),
                    machine.getTime(),
                    machine.getImage(),
                    machine.getLastModify(),
                    machine.getResult(),
                    machine.getHost());
        } else {
            return jdbcTemplate.update("INSERT INTO machine(host,name,ip,time,image,lastmodify,result) values(?,?,?,?,?,?,?)",
                    machine.getHost(),
                    machine.getName(),
                    machine.getIp(),
                    machine.getTime(),
                    machine.getImage(),
                    machine.getLastModify(),
                    machine.getResult());
        }
    }

    public List<Machine> findAll(){
        return jdbcTemplate.query("SELECT * FROM machine ORDER BY lastmodify DESC",
                new Object[]{},
                new BeanPropertyRowMapper(Machine.class));
    }

}
