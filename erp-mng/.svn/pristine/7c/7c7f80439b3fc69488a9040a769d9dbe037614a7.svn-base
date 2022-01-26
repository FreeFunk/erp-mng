package com.edgedo.sys.service;
		
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCompCode;
import com.edgedo.sys.entity.ErpInstructionsForm;
import com.edgedo.sys.mapper.ErpInstructionsFormMapper;
import com.edgedo.sys.queryvo.ErpInstructionsFormQuery;
import com.edgedo.sys.queryvo.ErpInstructionsFormView;
import com.edgedo.timedtask.ReadExcelCarInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpInstructionsFormService {
	
	
	@Autowired
	private ErpInstructionsFormMapper erpInstructionsFormMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpInstructionsFormView> listPage(ErpInstructionsFormQuery erpInstructionsFormQuery){
		List list = erpInstructionsFormMapper.listPage(erpInstructionsFormQuery);
		erpInstructionsFormQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpInstructionsForm erpInstructionsForm) {
		erpInstructionsForm.setId(Guid.guid());
		erpInstructionsFormMapper.insert(erpInstructionsForm);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpInstructionsForm erpInstructionsForm) {
		erpInstructionsFormMapper.updateById(erpInstructionsForm);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpInstructionsForm erpInstructionsForm) {
		erpInstructionsFormMapper.updateAllColumnById(erpInstructionsForm);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return erpInstructionsFormMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return erpInstructionsFormMapper.updateByIdDelete(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpInstructionsForm loadById(String id) {
		return erpInstructionsFormMapper.selectById(id);
	}


	public List<ErpInstructionsForm> selectAllCarInfo(Map<String, Object> mapQuery) {
		return erpInstructionsFormMapper.selectAllCarInfo(mapQuery);
	}

	@Transactional(rollbackFor=Exception.class)
	public int batchImport(String fileName, MultipartFile file, User user) throws Exception {
		InputStream is = file.getInputStream();

		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
			//文件上传格式出错
			return -2;
		}
		Workbook workbook = null;
		try {
			//获取Workbook实例
			workbook = ReadExcelCarInfo.getWorkBook(fileName, is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Sheet sheet = workbook.getSheetAt(0);

		//去除sheet中的一些无效行，比如值为空但是有格式的空白行
		sheet = ReadExcelCarInfo.resetSheet(sheet);
		int lastRowNum = sheet.getLastRowNum();
		String[] s;

		for (int i = 1; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			int cells = sheet.getRow(i).getPhysicalNumberOfCells();
			//获取每一行的数值，并将其填入一个字符串的数组
			s = ReadExcelCarInfo.getExcelRows(row, cells);
			//先判断是否存在
			ErpInstructionsForm erpInstructionsForm = new ErpInstructionsForm();
			for (int j = 0; j < s.length; j++) {
				if (j == 0 && !s[j].equals("")) {
					//设备品牌
					erpInstructionsForm.setTerminalBrand(s[j]);
				} else if (j == 1 && !s[j].equals("")) {
					//指令名称
					erpInstructionsForm.setInstructionsName(s[j]);
				} else if (j == 2 && !s[j].equals("")) {
					//指令内容
					erpInstructionsForm.setInstructionsContent(s[j]);
				}
			}
			erpInstructionsForm.setId(Guid.guid());
			erpInstructionsForm.setCreateTime(new Date());
			erpInstructionsForm.setCreateUserName(user.getUserName());
			erpInstructionsForm.setCreateUserId(user.getUserId());
			erpInstructionsForm.setDataState("1");
			erpInstructionsFormMapper.insert(erpInstructionsForm);
		}
		return 1;
	}
}
