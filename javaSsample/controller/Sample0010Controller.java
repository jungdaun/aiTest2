package com.shinsegae_inc.swaf.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;
import com.shinsegae_inc.swaf.sample.service.Sample0005Service;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class Sample0010Controller extends SwafDhtmlxController {

    @Autowired
    public Sample0005Service sample0005Service;

    /**
     * @param request
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : Sample 화면 초기화
	 * </pre>
     */
    @GetMapping("/sample/sample0010.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0010");
    }
    
    /**
     * @param param
     * @param model
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 그룹 코드 목록을 조회합니다.
	 * </pre>
     */
    @PostMapping("/sample/sample0010/selectSample0010A.do")
    public String selectSample0010A(ReqParamMap param, Model model) {
        model.addAttribute("result", sample0005Service.selectSample0005(param));
        
        return JSON_VIEW;
    }
    
    /**
     * @param param
     * @param model
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 코드 목록을 조회합니다.
	 * </pre>
     */    
    @PostMapping("/sample/sample0010/selectSample0010B.do")
    public String selectSample0010B(ReqParamMap param, Model model) {
    	model.addAttribute("result", sample0005Service.selectSample0005Dtl(param));
    	
        return JSON_VIEW;
    }
    
}
