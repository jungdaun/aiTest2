package com.shinsegae_inc.swaf.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class Sample0002Controller extends SwafDhtmlxController {

    @GetMapping("/sample/sample0002.do")
    public ModelAndView init(HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0002");
    }
    
    
    @GetMapping("/sample/sample0002_T01.do")
    public ModelAndView initSample0002T01(HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0002_T01");
    }

}
