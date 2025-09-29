package com.shinsegae_inc.swaf.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.core.support.annotation.JsonParamAnnotation;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;
import com.shinsegae_inc.swaf.sample.service.Sample0003Service;

import groovy.util.logging.Slf4j;

/**
 * 그리드 기능 샘플 Controller
 *
 * @author 김종무
 * @since 2022.05.16
 * @version 1.0
 *
 * <pre>
 * Comments : 그리드 기능 샘플을 조회 및 저장을 합니다.
 * </pre>
 */
@Slf4j
@Controller
public class Sample0003Controller extends SwafDhtmlxController {

	@Autowired
    public Sample0003Service sample0003Service;

    /**
     * @param request
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : Sample 화면 초기화
	 * </pre>
     */
    @GetMapping("/sample/sample0003.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0003");
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
    @PostMapping("/sample/sample0003/selectSample0003.do")
    public String selectSample0003(ReqParamMap param, Model model) {
        model.addAttribute("result", sample0003Service.selectSample0003(param));
        
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
    @PostMapping("/sample/sample0003/selectSample0003Dtl.do")
    public String selectSample0003Dtl(ReqParamMap param, Model model) {
    	model.addAttribute("result", sample0003Service.selectSample0003Dtl(param));
    	
        return JSON_VIEW;
    }

    /**
     * @param param
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 그룹 코드 수정 사항을 저장합니다.
	 * </pre>
     */    
    @PostMapping("/sample/sample0003/saveSample0003.do")
    @JsonParamAnnotation
    public String saveSample0003(ReqParamMap param) throws Exception {
    	sample0003Service.saveSample0003(param);
    	
        return JSON_VIEW;
    }
    
    /**
     * @param param
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 코드 수정 사항을 저장합니다.
	 * </pre>
     */    
    @PostMapping("/sample/sample0003/saveSample0003Dtl.do")
    @JsonParamAnnotation
    public String saveSample0003Dtl(ReqParamMap param) throws Exception {
    	sample0003Service.saveSample0003Dtl(param);
    	
    	return JSON_VIEW;
    }

}
