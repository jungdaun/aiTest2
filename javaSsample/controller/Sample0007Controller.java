package com.shinsegae_inc.swaf.sample.controller;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.core.support.annotation.JsonParamAnnotation;
import com.shinsegae_inc.dhtmlx.controller.SwafDhtmlxController;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 파일 업로드 샘플 Controller
 *
 * @author 김종무
 * @since 2022.05.24
 * @version 1.0
 *
 * <pre>
 * Comments : 파일 업로드(form, grid) 샘플
 * </pre>
 */
@Slf4j
@Controller
public class Sample0007Controller extends SwafDhtmlxController {

	/**
     * @param request
     * @return
     * @throws Exception
	 *
	 * <pre>
	 * Comments : Sample 화면 초기화
	 * </pre>
     */
	@GetMapping("/sample/sample0007.do")
    public ModelAndView init (HttpServletRequest request) throws Exception {
		return getInitMAV(request, "/sample/sample0007");
    }

}
