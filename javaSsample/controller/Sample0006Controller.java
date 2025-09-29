package com.shinsegae_inc.swaf.sample.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.core.support.annotation.JsonParamAnnotation;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;
import com.shinsegae_inc.swaf.sample.service.Sample0006Service;

import groovy.util.logging.Slf4j;

/**
 * 그리드 엑셀 Import/Export 샘플 Controller
 *
 * @author 김종무
 * @since 2022.05.24
 * @version 1.0
 *
 * <pre>
 * Comments : 그리드 엑셀 Import/Export 기능 샘플
 * </pre>
 */
@Slf4j
@Controller
public class Sample0006Controller extends SwafDhtmlxController {

    @Autowired
    public Sample0006Service sample0006Service;

    /**
     * @param request
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : Sample 화면 초기화
	 * </pre>
     */
    @GetMapping("/sample/sample0006.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0006");
    }

    /**
     * @param param
     * @param model
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 사용자 목록을 조회합니다.
	 * </pre>
     */
    @PostMapping("/sample/sample0006/selectSample0006.do")
    public String selectSample0006(ReqParamMap param, Model model) {
    	model.addAttribute("result", sample0006Service.selectSample0006(param));
    	
        return JSON_VIEW;
    }

    /**
     * @param param
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : 사용자 목록 수정 사항을 저장합니다.
	 * </pre>
     */
    @PostMapping("/sample/sample0006/saveSample0006.do")
    @JsonParamAnnotation
    public String saveSample0006(ReqParamMap param) throws Exception {
    	sample0006Service.saveSample0006(param);
    	
        return JSON_VIEW;
    }

}
