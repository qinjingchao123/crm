package com.bjpowernode.crm.settings.dictionary.value.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.settings.dictionary.value.dao.DictionaryValueDao;
import com.bjpowernode.crm.settings.dictionary.value.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.value.service.DictionaryValueService;
/**
 * 数据字典值业务处理类
 * @author Administrator
 *
 */
public class DictionaryValueServiceImpl implements DictionaryValueService {
	private DictionaryValueDao dictionaryValueDao=SqlSessionUtil.getSqlSession().getMapper(DictionaryValueDao.class);
	/**
	 * 根据typeCode查询数据字典值
	 */
	@Override
	public List<DictionaryValue> queryDictionaryValueByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		return dictionaryValueDao.queryDictionaryValueByTypeCode(typeCode);
	}

}
