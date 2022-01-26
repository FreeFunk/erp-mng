package com.edgedo.sys.service;
		
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCompCode;
import com.edgedo.sys.entity.ErpOutForm;
import com.edgedo.sys.mapper.ErpOutFormMapper;
import com.edgedo.sys.queryvo.ErpOutFormQuery;
import com.edgedo.sys.queryvo.ErpOutFormView;
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
public class ErpOutFormService {
	
	
	@Autowired
	private ErpOutFormMapper erpOutFormMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpOutFormView> listPage(ErpOutFormQuery erpOutFormQuery){
		List list = erpOutFormMapper.listPage(erpOutFormQuery);
		erpOutFormQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpOutForm erpOutForm) {
		erpOutForm.setId(Guid.guid());
		erpOutFormMapper.insert(erpOutForm);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpOutForm erpOutForm) {
		erpOutFormMapper.updateById(erpOutForm);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpOutForm erpOutForm) {
		erpOutFormMapper.updateAllColumnById(erpOutForm);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return erpOutFormMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return erpOutFormMapper.updateByIdDelete(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpOutForm loadById(String id) {
		return erpOutFormMapper.selectById(id);
	}


	public List<ErpOutForm> selectAllCarInfo(Map<String, Object> mapQuery) {
		return erpOutFormMapper.selectAllCarInfo(mapQuery);
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
			ErpOutForm erpOutForm = new ErpOutForm();
			for (int j = 0; j < s.length; j++) {
				if (j == 0 && !s[j].equals("")){
					//转出时间
					Date date = getDate2New(s[j]);
					erpOutForm.setOutTime(date);
				}else if (j == 1 && !s[j].equals("")){
					//车牌号
					erpOutForm.setCarPlateNum(s[j]);
				}else if (j == 2 && !s[j].equals("")){
					//原平台
					erpOutForm.setOrgPlatform(s[j]);
				}else if (j == 3 && !s[j].equals("")){
					//现平台
					erpOutForm.setNewPlatform(s[j]);
				}else if (j == 4 && !s[j].equals("")){
					//区县
					erpOutForm.setXianquName(s[j]);
				}
			}
			erpOutForm.setId(Guid.guid());
			erpOutForm.setCreateTime(new Date());
			erpOutForm.setCreateUserName(user.getUserName());
			erpOutForm.setCreateUserId(user.getUserId());
			erpOutForm.setDataState("1");
			erpOutFormMapper.insert(erpOutForm);
			continue;
		}
		return 1;
	}



	public  Date getDate2New(String dateString){
		Date date = null;
		if(dateString!=null && dateString.contains("CST")){
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				date = simpleDateFormat.parse(dateString);
			}catch (Exception e){
				date = null;
				e.printStackTrace();
			}
			return date;
		}
		return date;
	}
}
