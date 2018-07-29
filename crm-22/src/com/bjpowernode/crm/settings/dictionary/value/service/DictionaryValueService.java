package com.bjpowernode.crm.settings.dictionary.value.service;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.value.domain.DictionaryValue;

/**
 * 数据字典值业务处理接口
 * @author Administrator
 *
 */
public interface DictionaryValueService {
	/**
	 * 根据typeCode查询数据字典值
	 * @param typeCode
	 * @return
	 */
	public List<DictionaryValue> queryDictionaryValueByTypeCode(String typeCode);
}
