package com.shinsegae_inc.swaf.sample.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;
import com.shinsegae_inc.swaf.sample.service.Sample0008Service;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class Sample0008Controller extends SwafDhtmlxController {

    @Autowired
    public Sample0008Service Sample0008Service;

    @GetMapping("/sample/sample0008.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0008");
    }

    
    @PostMapping("/sample/sample0008/selectSample0008.do")
    public ModelAndView selectSample0008 (HttpServletRequest request, HttpServletResponse response , ReqParamMap reqParamMap) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        Map<Object, Object> paramMap = reqParamMap.getParamMap();
        mav.addObject("result", Sample0008Service.selectSample0008List(paramMap));
        setSuccMsg(mav);
        return mav;
    }  

}
