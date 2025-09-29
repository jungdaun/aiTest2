package com.shinsegae_inc.swaf.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae_inc.core.service.SwafService;
import com.shinsegae_inc.swaf.common.mapper.CommonMapper;



/**
 * 기본 컴포넌트 Sample(Sample0001)을 위한 Service class
 * 
 * @author sklee21
 * @since 2022.05.17
 * @version 1.0
 */
@Service
public class Sample0001Service extends SwafService{
	
	@Autowired
    private transient CommonMapper commonMapper;
	
	
	/**
	 * 카테고리 대/중/소 데이터를 조회한다.<br>
	 * 
	 * <pre>
	 * 	param = {
	 * 		LEVEL: L=대, M=중, S=소
	 * 		CTGR_CD: 선택된 상위 카테고리 코드
	 * }
	 * </pre>
	 * 
	 * @param param LEVEL과 CTGR_CD를 parameter로 갖는다.
	 * @return
	 */
    public List<Map<String, Object>> selectCategoryList(Map<Object, Object> param) {
    	//
    	// DB에서 조회한 데이터로 가정
    	List<Map<String, Object>> categoryList = null;
    	String level = MapUtils.getString(param, "LEVEL", "L");
    	
    	// 카테고리(대)
    	if ( Objects.equals(level, "L") ) {
    		categoryList = this.commonMapper.selectList("Sample.selectLCategoryList", param);
    		
    		
    	// 카테고리(중)
    	} else if ( Objects.equals(level, "M") ) {
    		categoryList = this.commonMapper.selectList("Sample.selectMCategoryList", param);
    		
    		
    	// 카테고리(소)
    	} else if ( Objects.equals(level, "S") ) {
    		categoryList = this.commonMapper.selectList("Sample.selectSCategoryList", param);
    	
    	} else {
    		categoryList = new ArrayList<>();
    	}
    	
    	return categoryList;
    }

}

