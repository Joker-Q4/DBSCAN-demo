package cn.edu.sau.controller;

import cn.edu.sau.dbscan.CreateFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DBSCAN")
public class CFileController {

    @RequestMapping("/CFile")
    public String aFile(@RequestParam String num){
        try {
            CreateFile.autoCreate(Integer.parseInt(num));
            return "success";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
