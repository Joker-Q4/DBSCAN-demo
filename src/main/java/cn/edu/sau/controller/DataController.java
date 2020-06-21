package cn.edu.sau.controller;

import cn.edu.sau.dbscan.DBScan;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DBSCAN")
public class DataController {

    @RequestMapping("/Data")
    public String aFile(@RequestParam String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        double r = Double.parseDouble(jsonObject.getString("r"));
        int minPoints = Integer.parseInt(jsonObject.getString("minPoints"));
        DBScan.setNum(r, minPoints);
        String a = DBScan.display();
        System.out.println(a);
        return a;
    }
}
