package com.shinsegae_inc.swaf.sample.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.core.service.SwafService;
import com.shinsegae_inc.swaf.common.mapper.CommonMapper;
import com.shinsegae_inc.swaf.common.service.AuiGridService;

/**
 * 그리드 레이아웃 조절 기능 샘플 Service
 *
 * @author 김종무
 * @since 2022.08.23
 * @version 1.0
 *
 * <pre>
 * Comments : 그리드 동적 변경 기능 샘플을 조회 및 저장을 합니다.
 * </pre>
 */
@Service
public class Sample0010Service extends SwafService {
	
	@Autowired
    private CommonMapper commonMapper;
    
    @Autowired
    private AuiGridService auiGridService;
    
	/**
	*
	* @param param
	* @return
	*
	* <pre>
	* Commnets : 그룹코드 목록 조회
	* </pre>
	*/
    public List<Map<String, Object>> selectSample0010A(ReqParamMap param) {
    	return commonMapper.selectList("CommonCode.selectCodeGrpList", param.getParamMap());
    }
    
	/**
	 *
	 * @param param
	 * @return
	 *
	 * <pre>
	 * Commnets : 코드 목록 조회
	 * </pre>
	 */
    public List<Map<String, Object>> selectSample0010B(ReqParamMap param) {
    	return commonMapper.selectList("CommonCode.selectCodeList", param.getParamMap());
    }

}

