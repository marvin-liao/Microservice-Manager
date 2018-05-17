package org.ustb.MicroServiceMgr;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ustb.MicroServiceMgr.dao.MachineDAO;
import org.ustb.MicroServiceMgr.dao.ServiceDAO;
import org.ustb.MicroServiceMgr.domain.Api;
import org.ustb.MicroServiceMgr.domain.Service;
import org.ustb.MicroServiceMgr.lib.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class MicroServiceMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(MicroServiceMonitor.class);

    @Autowired
    private ServiceDAO serviceDao;

    @Scheduled(fixedRate = 1500)
    public void monitorEureka(){
        List<Service> serObjList = new ArrayList<Service>();

        try {
            String json = HttpUtil.doGet("http://DISCOVERY:8761/eureka/apps/");
            if (json != null) {
                ObjectMapper m = new ObjectMapper();
                JsonNode rootNode = m.readTree(json);
                JsonNode serviceList = rootNode.get("applications").get("application");

                Iterator<JsonNode> services = serviceList.iterator();
                while (services.hasNext()) {
                    JsonNode application = services.next();
                    JsonNode objList = application.get("instance");
                    Iterator<JsonNode> iter = objList.iterator();

                    m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    while (iter.hasNext()) {
                        JsonNode instance = iter.next();
                        Service se = m.readValue(instance.toString(), Service.class);
                        serObjList.add(se);
                        LOG.info("Service Instance parsed {}", se.getInstanceId());
                    }
                }
            }
        }
        catch (IOException e) {
            LOG.error(e.getMessage());
        }

        if (serObjList.size() > 0){
            for (Service se : serObjList){
                se.setLastModify(new Date());
                se.setResult(1);
                serviceDao.save(se);

                LOG.info("Service Info Saved {}", se.getInstanceId());
            }
        }
    }

    public void monitorApi(){

    }
}