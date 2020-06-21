package cn.edu.sau.controller;

import cn.edu.sau.dbscan.DBScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DBSCAN")
public class SimpleDataController {

    @RequestMapping("/SimpleData")
    public String aFile(){
        return DBScan.allPoints();
    }
}
