package SPRING.controller;

import SPRING.Entity.TestEntity;
import SPRING.Entity.TestRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class IndexController {

    @GetMapping("/main")
    public String home(){
        return "main";
    }

    @Autowired
    TestRepository testRepository;

    @GetMapping("/save")
    @ResponseBody
    public String getdata(HttpServletRequest request){
        String content = request.getParameter("content");
        TestEntity testEntity = new TestEntity();
        testEntity.content = content;
        testRepository.save(testEntity);
        return "작성성공";
    }


    //@ResponseBody
    @GetMapping("/getlist")
    public void getlist(HttpServletResponse response){

        List<TestEntity> testEntities = testRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (TestEntity entity : testEntities){
            jsonObject.put("no", entity.no);
            jsonObject.put("content", entity.content);
            jsonArray.put(jsonObject);
        }

        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        Optional<TestEntity> optionalTestEntity = testRepository.findById(no);
        testRepository.delete(optionalTestEntity.get());
        return "1";
    }

    @GetMapping("/update")
    @ResponseBody
    @Transactional
    public String update(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        String content = request.getParameter("content");

        Optional<TestEntity> optionalTestEntity = testRepository.findById(no);

        TestEntity entity = optionalTestEntity.get();
        entity.content = content;
        return "1";
    }

}
