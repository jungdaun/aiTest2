package com.shinsegae_inc.swaf.sample.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinsegae_inc.core.service.SwafService;
import com.shinsegae_inc.core.util.SessionUtils;
import com.shinsegae_inc.swaf.common.mapper.CommonMapper;
import com.shinsegae_inc.swaf.common.utils.SwafBizUtils;

@Service
public class Sample0008Service extends SwafService{
	
	@Autowired
    private transient CommonMapper commonMapper;
    
    /**
	 * POS)메뉴 일자별 매출 - 조회
	 * 
	 * @param param
	 *   - dc_searchParam
	 * @return 
	 */
    public List<Object> selectSample0008List(Map<Object, Object> hm) {
    	return commonMapper.selectList("sample0008.select", hm);
    }

	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void save(Map<Object, Object> paramMap) throws Exception{
		String userNo = SessionUtils.getUserNo();

	}

} 