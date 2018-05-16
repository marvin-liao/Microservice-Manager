package org.ustb.MicroServiceMgr;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ustb.MicroServiceMgr.domain.Api;
import org.ustb.MicroServiceMgr.domain.Service;
import org.ustb.MicroServiceMgr.lib.HttpUtil;

import java.io.IOException;
import java.util.Iterator;

@Component
public class MicroServiceMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(MicroServiceMonitor.class);

    @Scheduled(fixedRate = 5000)
    public void monitorEureka(){
        try {
            String json = HttpUtil.doGet("http://DISCOVERY:8761/eureka/apps/");
            if (json != null) {
                ObjectMapper m = new ObjectMapper();
                JsonNode rootNode = m.readTree(json);
                JsonNode serviceList = rootNode.get("applications").get("application");

                m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Iterator<JsonNode> services = serviceList.iterator();
                while (services.hasNext()) {
                    JsonNode current = services.next();
                    Service se = m.readValue(current.toString(), Service.class);
                    LOG.info("Service Monitor {}", se.getName());
                }
            }
        }
        catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public void monitorApi(){

    }
}