package com.shinsegae_inc.swaf.sample.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;
import com.shinsegae_inc.swaf.common.utils.SwafBizUtils;
import com.shinsegae_inc.swaf.sample.service.Sample0001Service;

import groovy.util.logging.Slf4j;



/**
 * 기본 컴포넌트 Sample(Sample0001)을 위한 Controller class
 * 
 * @author sklee21
 * @since 2022.05.17
 * @version 1.0
 */
@Slf4j
@Controller
public class Sample0001Controller extends SwafDhtmlxController {
	
	@Autowired
	private Sample0001Service sample0001Service;
	
	

	/**
	 * 초기화
	 * /templates/sample/sample0001.html return
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/sample/sample0001.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
    	
		return getInitMAV(request, "/sample/sample0001");
    }
    
    
	/**
	 * Popup 초기화
	 * /templates/sample/sample0001_P01.html return
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/sample/sample0001_P01.do")
    public ModelAndView initPopup(HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0001_P01");
    }
    
    
    /**
     * 카테고리 대/중/소 데이터를 조회한다.<br>
     * 
     * @param request
     * @param response
     * @param reqParamMap
     * @return
     * @throws Exception
     */
    @PostMapping("/sample/sample0001/getCategoryList.do")
    public ModelAndView selectTemplate0001 (HttpServletRequest request, HttpServletResponse response , ReqParamMap reqParamMap) throws Exception {
        Map<Object, Object> paramMap = reqParamMap.getParamMap();
        
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("result", sample0001Service.selectCategoryList(paramMap));
        
        this.setSuccMsg(mav);
        
        return mav;
    }
    
}
