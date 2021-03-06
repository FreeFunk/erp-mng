package com.edgedo.sys.service;
		
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpCarTechnicalDossier;
import com.edgedo.sys.entity.ErpCompCode;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.mapper.ErpCompCodeMapper;
import com.edgedo.sys.queryvo.ErpCompCodeQuery;
import com.edgedo.sys.queryvo.ErpCompCodeView;
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
public class ErpCompCodeService {
	
	
	@Autowired
	private ErpCompCodeMapper erpCompCodeMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCompCodeView> listPage(ErpCompCodeQuery erpCompCodeQuery){
		List list = erpCompCodeMapper.listPage(erpCompCodeQuery);
		erpCompCodeQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCompCode erpCompCode) {
		//判断是否存在该记录  公司名称  信用代码
		String msg = isFlag(erpCompCode);
		if(msg.equals("")){
			erpCompCode.setId(Guid.guid());
			erpCompCodeMapper.insert(erpCompCode);
			return "";
		}else {
			return msg;
		}
	}

	private String isFlag(ErpCompCode erpCompCode) {
		int compNameIsOn = erpCompCodeMapper.selectVoCompName(erpCompCode.getCompName());
		int codeIsOn = erpCompCodeMapper.selectVoCode(erpCompCode.getCompCode());
		if(compNameIsOn!=0){//存在
			return "该公司已存在";
		}else if(codeIsOn!=0){//代码已存在
			return "代码重复";
		}else {
			return "";
		}
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCompCode erpCompCode) {
		//判断是否存在该记录  公司名称  信用代码
		String msg = isFlagUpdate(erpCompCode);
		if(msg.equals("")){
			erpCompCodeMapper.updateById(erpCompCode);
			return "";
		}else {
			return msg;
		}
	}

	private String isFlagUpdate(ErpCompCode erpCompCode) {
		int compNameIsOn = erpCompCodeMapper.selectVoCompNameUpdate(erpCompCode.getCompName(),erpCompCode.getId());
		int codeIsOn = erpCompCodeMapper.selectVoCodeUpdate(erpCompCode.getCompCode(),erpCompCode.getId());
		if(compNameIsOn!=0){//存在
			return "该公司已存在";
		}else if(codeIsOn!=0){//代码已存在
			return "代码重复";
		}else {
			return "";
		}
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCompCode erpCompCode) {
		erpCompCodeMapper.updateAllColumnById(erpCompCode);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return erpCompCodeMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return erpCompCodeMapper.updateByIdDelete(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCompCode loadById(String id) {
		return erpCompCodeMapper.selectById(id);
	}


	public List<ErpCompCode> selectAllCarInfo(Map<String, Object> mapQuery) {
		return erpCompCodeMapper.selectAllCarInfo(mapQuery);
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
			ErpCompCode erpCompCode = erpCompCodeMapper.selectByCodeAndCompName(s[0],s[1]);
			if(erpCompCode==null){//新增
				erpCompCode = new ErpCompCode();
				for (int j = 0; j < s.length; j++) {
					if (j == 0 && !s[j].equals("")){
						//公司名称
						erpCompCode.setCompName(s[j]);
					}else if (j == 1 && !s[j].equals("")){
						//信用代码
						erpCompCode.setCompCode(s[j]);
					}else if (j == 2 && !s[j].equals("")){
						//负责人
						erpCompCode.setChargePerson(s[j]);
					}else if (j == 3 && !s[j].equals("")){
						//电话
						erpCompCode.setPhoneNum(s[j]);
					}else if (j == 4 && !s[j].equals("")){
						//道路经营许可证号
						erpCompCode.setLicenseNum(s[j]);
					}
				}
				erpCompCode.setId(Guid.guid());
				erpCompCode.setCreateTime(new Date());
				erpCompCode.setCreateUserName(user.getUserName());
				erpCompCode.setCreateUserId(user.getUserId());
				erpCompCode.setDataState("1");
				erpCompCodeMapper.insert(erpCompCode);
				continue;
			}else {//更新
				String msg = isFlagUpdate(erpCompCode);
				if(msg.equals("该公司已存在")){
					for (int j = 0; j < s.length; j++) {
						if (j == 1 && !s[j].equals("")){
							//信用代码
							erpCompCode.setCompCode(s[j]);
						}else if (j == 2 && !s[j].equals("")){
							//负责人
							erpCompCode.setChargePerson(s[j]);
						}else if (j == 3 && !s[j].equals("")){
							//电话
							erpCompCode.setPhoneNum(s[j]);
						}else if (j == 4 && !s[j].equals("")){
							//道路经营许可证号
							erpCompCode.setLicenseNum(s[j]);
						}
					}
				}else if(msg.equals("代码重复")){
					for (int j = 0; j < s.length; j++) {
						if (j == 0 && !s[j].equals("")){
							//公司名称
							erpCompCode.setCompName(s[j]);
						}else if (j == 2 && !s[j].equals("")){
							//负责人
							erpCompCode.setChargePerson(s[j]);
						}else if (j == 3 && !s[j].equals("")){
							//电话
							erpCompCode.setPhoneNum(s[j]);
						}else if (j == 4 && !s[j].equals("")){
							//道路经营许可证号
							erpCompCode.setLicenseNum(s[j]);
						}
					}
				}else {
					for (int j = 0; j < s.length; j++) {
						if (j == 0 && !s[j].equals("")){
							//公司名称
							erpCompCode.setCompName(s[j]);
						}else if (j == 1 && !s[j].equals("")){
							//信用代码
							erpCompCode.setCompCode(s[j]);
						}else if (j == 2 && !s[j].equals("")){
							//负责人
							erpCompCode.setChargePerson(s[j]);
						}else if (j == 3 && !s[j].equals("")){
							//电话
							erpCompCode.setPhoneNum(s[j]);
						}else if (j == 4 && !s[j].equals("")){
							//道路经营许可证号
							erpCompCode.setLicenseNum(s[j]);
						}
					}
				}
				erpCompCodeMapper.updateVoId(erpCompCode);
				continue;
			}
		}
		return 1;
	}



}
