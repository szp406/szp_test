package com.jdyx.es.controller;


import com.jdyx.es.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="elasticearch示例")
@RestController
@RequestMapping("/es/test")
public class EsApiController {
    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "新建索引", notes = "新建索引")
    @RequestMapping(value = "/createIndex", method = { RequestMethod.GET })
    public String createIndex(String param) throws Exception {
         indexService.createIndex();
         return "success";
    }
}
