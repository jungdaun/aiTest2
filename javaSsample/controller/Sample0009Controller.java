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
public class Sample0009Controller extends SwafDhtmlxController {

    @GetMapping("/sample/sample0009.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0009");
    }
}
