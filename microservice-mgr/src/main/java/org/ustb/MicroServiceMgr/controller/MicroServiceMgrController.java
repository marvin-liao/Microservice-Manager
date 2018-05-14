package org.ustb.MicroServiceMgr.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.ustb.MicroServiceMgr.dao.AppdocDAO;
import org.ustb.MicroServiceMgr.domain.Appdoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class MicroServiceMgrController {

    private static final Logger LOG = LoggerFactory.getLogger(MicroServiceMgrController.class);

    @Autowired
    private AppdocDAO dao;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(){
        List<Appdoc> list = dao.findAll();
        List<String> result = new ArrayList<String>();

        for(Appdoc doc : list){
            result.add(doc.getDoc());
        }

        return  "[" + String.join(",", result) + "]";
    }

    @RequestMapping(path = "/doc/{title}", method = RequestMethod.GET)
    public String doc(@PathVariable("title") String title){
        Appdoc doc = dao.findByTitle(title);

        if (doc != null) {
            return doc.getDoc();
        }
        else {
            return "";
        }
    }

    @RequestMapping(path = "/api/publish", method = RequestMethod.POST, consumes = "application/json;charset=utf-8" )
    public void publish(@RequestBody String json){
        try {
            ObjectMapper m = new ObjectMapper();
            JsonNode rootNode = m.readTree(json);
            JsonNode infoNode = rootNode.get("info");

            Appdoc doc = new Appdoc();
            doc.setTitle(infoNode.get("title").textValue());
            doc.setDescription(infoNode.get("description").textValue());
            doc.setDoc(json);

            dao.save(doc);

            LOG.info("APP[{}] swagger document is updated", infoNode.get("title"));
        }
        catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

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

    /*
    @RequestMapping(path = "/publish", method = RequestMethod.POST, consumes = "application/json;charset=utf-8" )
    public void publish(@RequestBody Swagger swagger){
        //Swagger original = swaggerDocRepo.findByBasePath(swagger.getBasePath());

        // MongoDB docs cannot contain "$" when update, so we must delete old one and save the new one.
        //swaggerDocRepo.delete(original.getId());

        System.out.println("123123");
        swaggerDocRepo.save(swagger);

        Iterator<Swagger> iter = swaggerDocRepo.  ().iterator();

        while(iter.hasNext())
        {
            System.out.println("ID:" + iter.next().getId());
        }

    }
    */
}
