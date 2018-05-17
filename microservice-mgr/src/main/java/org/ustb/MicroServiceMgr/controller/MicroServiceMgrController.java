package org.ustb.MicroServiceMgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.ustb.MicroServiceMgr.dao.ApiDAO;
import org.ustb.MicroServiceMgr.dao.MachineDAO;
import org.ustb.MicroServiceMgr.dao.ServiceDAO;
import org.ustb.MicroServiceMgr.domain.Api;
import org.ustb.MicroServiceMgr.domain.ApiItem;
import org.ustb.MicroServiceMgr.domain.Machine;
import org.ustb.MicroServiceMgr.domain.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/")
public class MicroServiceMgrController {

    private static final Logger LOG = LoggerFactory.getLogger(MicroServiceMgrController.class);

    @Autowired
    private ApiDAO apiDao;

    @Autowired
    private MachineDAO machineDao;

    @Autowired
    private ServiceDAO serviceDao;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(){
        List<Api> apiList = apiDao.findAll();

        for(Api api : apiList){
            api.setItems(apiDao.findItemsByTitle(api.getTitle()));
        }

        String json = "[]";
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(fmt);
            json = mapper.writeValueAsString(apiList);
        }
        catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return  json;
    }

    @RequestMapping(path = "/doc/{title}", method = RequestMethod.GET)
    public String doc(@PathVariable("title") String title){
        Api doc = apiDao.findByTitle(title);

        if (doc != null) {
            return doc.getDoc();
        }
        else {
            return "";
        }
    }

    @RequestMapping(path = "/api/publish", method = RequestMethod.POST, consumes = "application/json;charset=utf-8" )
    public void apiPublish(@RequestBody String json){
        try {
            //解析基本信息
            ObjectMapper m = new ObjectMapper();
            JsonNode rootNode = m.readTree(json);
            JsonNode infoNode = rootNode.get("info");

            Api doc = new Api();
            doc.setTitle(infoNode.get("title").textValue());
            doc.setDescription(infoNode.get("description").textValue());
            doc.setHost(rootNode.get("host").textValue());
            doc.setBasePath(rootNode.get("basePath").textValue());
            doc.setDoc(json);
            doc.setLastModify(new Date());
            doc.setResult(1);

            apiDao.save(doc);
            apiDao.clearItems(doc.getTitle());
            LOG.info("API Info Saved {}", doc.getTitle());

            //解析path
            List<ApiItem> items = new ArrayList<ApiItem>();

            JsonNode paths = rootNode.get("paths");
            Iterator<Map.Entry<String, JsonNode>> it = paths.fields();
            while (it.hasNext())
            {
                Map.Entry<String, JsonNode> entry = it.next();
                JsonNode methods = entry.getValue();
                Iterator<Map.Entry<String, JsonNode>> methodIt = methods.fields();

                while (methodIt.hasNext())
                {
                    Map.Entry<String, JsonNode> leaf = methodIt.next();
                    JsonNode leafNode = leaf.getValue();
                    //创建对象
                    ApiItem item = new ApiItem();
                    item.setTitle(infoNode.get("title").textValue());
                    item.setPath(entry.getKey());
                    item.setMethod(leaf.getKey());
                    item.setSummary(leafNode.get("summary").asText());
                    item.setOperationId(leafNode.get("operationId").asText());
                    item.setConsumes("");

                    items.add(item);
                }
            }

            for (ApiItem i : items){
                i.setLastModify(new Date());
                i.setResult(1);
                apiDao.saveItem(i);

                LOG.info("Api Item Saved {}", i.getPath());
            }
        }
        catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }


    @RequestMapping(path = "/service/list", method = RequestMethod.GET)
    public String serviceList(){
        List<Service> services = serviceDao.findAll();

        Date now = new Date();
        for(Service se : services){
            if (now.getTime()-se.getLastModify().getTime()>3*1000){
                se.setResult(2);
            }
        }

        String json = "[]";
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(fmt);
            json = mapper.writeValueAsString(services);
        }
        catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return json;
    }

    @RequestMapping(path = "/machine/list", method = RequestMethod.GET)
    public String machineList(){
        List<Machine> machines = machineDao.findAll();

        Date now = new Date();
        for(Machine m : machines){
            if (now.getTime()-m.getLastModify().getTime()>6*1000){
                m.setResult(2);
            }
        }

        String json = "[]";
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(fmt);
            json = mapper.writeValueAsString(machines);
        }
        catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return json;
    }

    @RequestMapping(path = "/machine/publish", method = RequestMethod.POST)
    public void machinePublish(@RequestBody String json){
        try {
            ObjectMapper m = new ObjectMapper();
            m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Machine machine = m.readValue(json, Machine.class);
            machine.setLastModify(new Date());
            machine.setResult(1);

            if(machine.getHost() != null && machine.getHost() != ""){
                machineDao.save(machine);
                LOG.info("Machine Info Saved {}", machine.getHost());
            }
            else{
                LOG.info("Machine Info Empty");
            }
        }
        catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    ////------------------------------------------------------------

    @RequestMapping(path = "/login", method = RequestMethod.POST )
    public void login(String username, String password){
        //检查用户登录权限，普通用户跳转到index.html，管理员跳转到auth.html
    }

    @RequestMapping(path = "/getauth", method = RequestMethod.GET )
    public void getAuth(){
        //获取所有用户的权限列表
    }

    @RequestMapping(path = "/modifyauth", method = RequestMethod.POST )
    public void modifyAuth(){
        //管理员变更用户的权限
    }
}
