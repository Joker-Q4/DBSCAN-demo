package cn.edu.sau.controller;

import cn.edu.sau.dbscan.AppFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/DBSCAN")
public class AFileController {

    @RequestMapping("/AFile")
    public String aFile(@RequestParam String json){
        StringBuilder allMsg = new StringBuilder();
        JSONArray jsonArray = JSONArray.parseArray(json);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject job = jsonArray.getJSONObject(i);
            if(i == 0) {
                allMsg.append(job.getString("x"));
                allMsg.append(",");
                allMsg.append(job.getString("y"));
            } else {
                allMsg.append("\n");
                allMsg.append(job.getString("x"));
                allMsg.append(",");
                allMsg.append(job.getString("y"));
            }
        }
        try {
            AppFile.writeAFile(allMsg.toString());
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
