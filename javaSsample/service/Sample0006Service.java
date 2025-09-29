package com.shinsegae_inc.swaf.sample.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinsegae_inc.core.map.ReqParamMap;
import com.shinsegae_inc.core.service.SwafService;
import com.shinsegae_inc.core.util.SessionUtils;
import com.shinsegae_inc.swaf.common.mapper.CommonMapper;
import com.shinsegae_inc.swaf.common.service.AuiGridService;
import com.shinsegae_inc.swaf.common.utils.SwafBizUtils;

/**
 * 그리드 엑셀 Import/Export 기능 샘플 Service
 *
 * @author 김종무
 * @since 2022.05.24
 * @version 1.0
 *
 * <pre>
 * Comments : 그리드 엑셀 Import/Export 기능 샘플
 * </pre>
 */
@Service
public class Sample0006Service extends SwafService{
	
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
	* Commnets : 사용자 목록 조회
	* </pre>
	*/
    public List<Map<String, Object>> selectSample0006(ReqParamMap param) {
    	return commonMapper.selectList("Vdi.selectVdiList", param.getParamMap());
    }

    /**
	*
	* @param param
	* @return
	* @throws Exception
	*
	* <pre>
	* Commnets : 사용자 목록 저장
	* </pre>
	*/    
	public void saveSample0006(ReqParamMap param) throws Exception {
		param.put("insertQuery", "CommonCode.insertCodeGrp");
		param.put("updateQuery", "CommonCode.updateCodeGrp");
		param.put("deleteQuery", "CommonCode.deleteCodeGrp");
		
		auiGridService.save(param);
	}
}

