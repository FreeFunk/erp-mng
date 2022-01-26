package com.edgedo.sys.service;
		
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.mapper.*;
import com.edgedo.sys.queryvo.*;

import com.edgedo.timedtask.ReadExcelCarInfo;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarInfoService {
	
	
	@Autowired
	private ErpCarInfoMapper mapper;

	@Autowired
	private ErpCarTeamMapper erpCarTeamMapper;

	@Autowired
	private ErpChannelAgentMapper erpChannelAgentMapper;

	@Autowired
	private ErpSupplierMapper erpSupplierMapper;

	@Autowired
	private ErpGpsTerminalMapper erpGpsTerminalMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ErpCarTechnicalDossierMapper erpCarTechnicalDossierMapper;

	@Autowired
	private ErpSimMapper erpSimMapper;

	@Autowired
	private ErpSimService erpSimService;

	@Autowired
	private ErpOperatorMapper erpOperatorMapper;

	@Autowired
	private SysXianquMapper sysXianquMapper;

	@Autowired
	private ErpTerminalChangeRecMapper erpTerminalChangeRecMapper;

	@Autowired
	private SysPhoneMsgRecService sysPhoneMsgRecService;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarInfoView> listPage(ErpCarInfoQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarInfoView> listDeletePage(ErpCarInfoQuery query){
		List list = mapper.deletelistPage(query);
		query.setList(list);
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarInfoView> endTimelistPage(ErpCarInfoQuery query) {
		List list = mapper.endTimelistPage(query);
		query.setList(list);
		return list;
	}


	/**
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarInfo voObj) {
		voObj.setCreateTime(new Date());
		//判断有没有sim卡号
		String err = selectByErpSimBysim(voObj.getSimCode());
		if(err.equals("没有SIM卡，请先添加SIM卡!")){
			return err;
		}else if(err.equals("该sim卡已使用,请更换卡号!")){
			return err;
		}else{
			voObj.setId(Guid.guid());
			ErpCarTeam erpCarTeam = erpCarTeamMapper.selectByCarTeamId(voObj.getOwnerTeamName());//车队名
			voObj.setOwnerTeamId(erpCarTeam.getId());
			voObj.setOwnerTeamName(erpCarTeam.getCarTeamName());
			//依据县区id查询地区信息
			SysXianqu sysXianqu = erpChannelAgentMapper.selectByXianQuId(voObj.getXianquId());
			voObj.setProvinceId(sysXianqu.getProvinceId());//省id
			voObj.setProvinceName(sysXianqu.getProvinceName());//省名
			voObj.setCityId(sysXianqu.getCityId());//城市id
			voObj.setCityName(sysXianqu.getCityName());//城市名字
			voObj.setXianquName(sysXianqu.getName());//县区名
			//根据供应商id 查询供应商名
			String supplierName = erpSupplierMapper.selectBySupplierId(voObj.getSupplierId());
			voObj.setSupplierName(supplierName);
			if(voObj.getTerminalCode().equals("")){
				voObj.setTerminalId("");
				voObj.setTerminalCode("");
				voObj.setTerminalChangshang("");
				//渠道代理
				String channelAgentIName = erpChannelAgentMapper.selectByChannelAgentId(voObj.getChannelAgentId());
				voObj.setChannelAgentName(channelAgentIName);
				//业务人员
				String yewuUserName = sysUserMapper.selectByYewuUserId(voObj.getYewuUserId());
				voObj.setYewuUserName(yewuUserName);
				//安装人员
				String installUserName = sysUserMapper.selectByInstallUserId(voObj.getInstallUserId());
				voObj.setInstallUserName(installUserName);
				//北斗
				ErpOperator erpOperator = erpOperatorMapper.selectByIdOne(voObj.getBeidouOperator());
				voObj.setBeidouOperator(erpOperator.getName());
				voObj.setDataState("1");

				//更新sim
				erpSimService.updateByErpSimInfo(voObj);
				mapper.insert(voObj);
				return "";
			}else {
				//查询终端编号 ID
				ErpGpsTerminal erpGpsTerminal = erpGpsTerminalMapper.selectByTerminalCodeByBySupplierIdBySupplierName(voObj.getTerminalCode(),voObj.getSupplierId(),supplierName);
				if(erpGpsTerminal==null){
					return "终端编号不存在,查看是否输入正确";
				}else {
					voObj.setTerminalId(erpGpsTerminal.getId());
					voObj.setTerminalCode(erpGpsTerminal.getTerminalCode());
					voObj.setTerminalChangshang(erpGpsTerminal.getTerminalChangshang());
					//渠道代理
					String channelAgentIName = erpChannelAgentMapper.selectByChannelAgentId(voObj.getChannelAgentId());
					voObj.setChannelAgentName(channelAgentIName);
					//业务人员
					String yewuUserName = sysUserMapper.selectByYewuUserId(voObj.getYewuUserId());
					voObj.setYewuUserName(yewuUserName);
					//安装人员
					String installUserName = sysUserMapper.selectByInstallUserId(voObj.getInstallUserId());
					voObj.setInstallUserName(installUserName);
					//北斗
					ErpOperator erpOperator = erpOperatorMapper.selectByIdOne(voObj.getBeidouOperator());
					voObj.setBeidouOperator(erpOperator.getName());
					voObj.setDataState("1");

					//更新sim
					erpSimService.updateByErpSimInfo(voObj);
					mapper.insert(voObj);
					return "";
				}
			}
		}
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCarInfo voObj) {
		//县区id
		String xianQu = voObj.getXianquId();
		if(xianQu.equals("")){
			voObj.setXianquName("");
			voObj.setXianquId("");
		}else {
			String[] xianQuArr = xianQu.split(",");
			voObj.setXianquName(xianQuArr[1]);
			voObj.setXianquId(xianQuArr[0]);
		}

		//车队名+id
		String team = voObj.getOwnerTeamName();
		if(team.equals("")){
			voObj.setOwnerTeamName("");
			voObj.setOwnerTeamId("");
		}else {
			String[] teamArr = team.split(",");
			voObj.setOwnerTeamName(teamArr[1]);
			voObj.setOwnerTeamId(teamArr[0]);
		}

		//供应商id
		String supplierId = voObj.getSupplierId();
		if(supplierId.equals("")){
			voObj.setSupplierName("");
			voObj.setSupplierId("");
		}else {
			String[] supplierIdArr = supplierId.split(",");
			voObj.setSupplierName(supplierIdArr[1]);
			voObj.setSupplierId(supplierIdArr[0]);
		}
		//渠道代理id
		String agentId = voObj.getChannelAgentId();

		if(agentId.equals("")){
			voObj.setChannelAgentName("");
			voObj.setChannelAgentId("");
		}else {
			String[] agentIdArr = agentId.split(",");
			voObj.setChannelAgentName(agentIdArr[1]);
			voObj.setChannelAgentId(agentIdArr[0]);
		}

		//业务id
		String yewuUserId = voObj.getYewuUserId();

		if(yewuUserId.equals("")){
			voObj.setYewuUserName("");
			voObj.setYewuUserId("");
		}else {
			String[] yewuUserIdArr = yewuUserId.split(",");
			voObj.setYewuUserName(yewuUserIdArr[1]);
			voObj.setYewuUserId(yewuUserIdArr[0]);
		}

		//安装录入id
		String installUserId = voObj.getInstallUserId();

		if(installUserId.equals("")){
			voObj.setInstallUserName("");
			voObj.setInstallUserId("");
		}else {
			String[] installUserIdArr = installUserId.split(",");
			voObj.setInstallUserName(installUserIdArr[1]);
			voObj.setInstallUserId(installUserIdArr[0]);
		}

//		ErpOperator erpOperator = erpOperatorMapper.selectByIdOne(voObj.getBeidouOperator());
//		voObj.setBeidouOperator(erpOperator.getName());

		mapper.updateByCarInfoId(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarInfo voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids,List<String> listSim) {
		mapper.updateBySimCodeList(listSim);
		mapper.deleteByIdsAll(ids);
	}
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCarInfo loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 动态查询车队名
	 * @param
	 * @return
	 */
    public List<ErpCarTeamView> selectCarTeam() {
    	return mapper.selectCarTeam();
    }

	/**
	 * 展示供应商名
	 */
	public List<ErpSupplierView> selectBySupplierName() {
		return mapper.selectBySupplierName();
	}

	/**
	 * 查询业务人员---外勤
	 * @return
	 */
	public List<SysUserView> selectByYeWu() {
		return mapper.selectByYeWu();
	}

	/**
	 * 展示录入人员  -- 内勤人员
	 */
	public List<SysUserView> selectByInputUser() {
		return mapper.selectByInputUser();
	}

	/**
	 * 展示渠道代理
	 * @return
	 */
    public List<ErpChannelAgentView> selectChannelAgentName() {
    	return mapper.selectChannelAgentName();
    }


	/**
	 * 判断有没有该sim卡
	 * @param simCode
	 * @return
	 */
	public String selectByErpSimBysim(String simCode) {
		int num = mapper.selectByErpSimByNum(simCode);
		//是否在正在使用
		String simState = mapper.selectByErpSimNum(simCode);
		if(num==0){
			return "没有SIM卡，请先添加SIM卡!";
		}else if(simState.equals("2")){
			return "该sim卡已使用,请更换卡号!";
		}else {
			return "";
		}
	}

	/**
	 * 根据id查询一条车辆信息
	 * @param ids
	 * @return
	 */
	public ErpCarInfo selectByIdCarInfo(String ids) {
		return mapper.selectByIdCarInfo(ids);
	}

	/**
	 * 插入一条换卡记录，依据车辆查询出来
	 * @param erpCarInfo
	 */
	public ErpCarSimChangeRecView insertByErpChangeSim(ErpCarInfo erpCarInfo) {
		//携带车牌号，车颜色，车架号，车对应的id插入到车辆换卡记录
		ErpCarSimChangeRecView erpCarSimChangeRec = new ErpCarSimChangeRecView();
		erpCarSimChangeRec.setId(Guid.guid());
		erpCarSimChangeRec.setCarFrameNum(erpCarInfo.getCarFrameNum());
		erpCarSimChangeRec.setCarPlateColor(erpCarInfo.getCarPlateColor());
		erpCarSimChangeRec.setOwnerCarInfoId(erpCarInfo.getId());
		erpCarSimChangeRec.setCarPlateNum(erpCarInfo.getCarPlateNum());
		erpCarSimChangeRec.setOrgSimNum(erpCarInfo.getSimCode());
		ErpSim erpSim = erpSimMapper.selectBySimCode(erpCarInfo.getSimCode());
		erpCarSimChangeRec.setOrgSimOperator(erpSim.getSupplierName());
		erpCarSimChangeRec.setDataState("1");
		erpCarSimChangeRec.setCreateTime(new Date());
		mapper.insertByErpSimChangeRec(erpCarSimChangeRec);
		return erpCarSimChangeRec;
	}

	/**
	 * 删除单个记录
	 * @param
	 */
	public void deleteByIdsOne(String ids,String simCode,String deleteRemark) {
		//关联的sim卡号也注销
		//mapper.updateBySimCodeErpSim(simCode);
		mapper.deleteByIdsOne(ids,deleteRemark,new Date());
	}

    public ErpCarInfoView selectByCarPlateNumCarPlateColor(String vehicleNo, String plateColor) {
		return mapper.selectByCarPlateNumCarPlateColor(vehicleNo,plateColor);
    }

	public List selectSim() {
		return erpSimMapper.selectSim();
	}

	public List<ErpCarInfo> selectAll() {
		return mapper.selectAll();
	}

    public ErpCarTechnicalDossier selectBycarFrameNum(String carFrameNum) {
		return erpCarTechnicalDossierMapper.selectBycarFrameNum(carFrameNum);
    }

	@SuppressWarnings("resource")
	@Transactional(rollbackFor=Exception.class)
	public int batchImport(String fileName, MultipartFile file) throws Exception {
		InputStream is = file.getInputStream();
		List<ErpCarInfo> listErpCarInfo = new ArrayList<>();//车辆信息
		List<ErpCarTechnicalDossier> listErpCarTechnicalDossier = new ArrayList<>();//技术档案
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
			if(s!=null && s.length>14){
				List<ErpCarInfo> erpCarInfoList = mapper.selectByCarPlateNumlist(s[14]);
				if(erpCarInfoList!=null && erpCarInfoList.size()>0){
					ErpCarInfo erpCarInfo = erpCarInfoList.get(0);
					for (int j = 0; j < s.length; j++) {
						if (j == 0 && !s[j].equals("")){
							//终端ID  0
							erpCarInfo.setTerminalId(s[j]);
						}else if (j == 1 && !s[j].equals("")){
							//终端厂商名称 1
							erpCarInfo.setTerminalChangshang(s[j]);
						}else if (j == 2 && !s[j].equals("")){
							//终端型号 2
							erpCarInfo.setTerminalCode(s[j]);
						}else if (j == 3 && !s[j].equals("")){
							//SIM卡卡号 3
							erpCarInfo.setSimCode(s[j]);
							//根据sim卡号查询相关信息
							ErpSim erpSim = erpSimMapper.selectBySimCode(s[j]);
							if(erpSim!=null){
								erpCarInfo.setSimId(erpSim.getId());
								erpCarInfo.setSupplierId(erpSim.getSupplierId());
								erpCarInfo.setSupplierName(erpSim.getSupplierName());
							}
						}else if (j == 4 && !s[j].equals("")){
							//道路运输证号 4
							erpCarInfo.setRoadTransportNum(s[j]);
						}else if (j == 5 && !s[j].equals("")){
							//所属省 5
							erpCarInfo.setProvinceName(s[j]);
							erpCarInfo.setProvinceId("HEBEI");
						}else if (j == 6 && !s[j].equals("")){
							//所属市 6
							erpCarInfo.setCityName(s[j]);
							erpCarInfo.setCityId("130300");
						}else if (j == 7 && !s[j].equals("")){
							//所属县 7  秦皇岛开发区 抚宁县 青龙满族自治县
							if(s[j].equals("秦皇岛开发区")){
								s[j]="开发区";
							}else if (s[j].equals("抚宁县")){
								s[j]="抚宁区";
							}else if (s[j].equals("青龙满族自治县")){
								s[j]="青龙县";
							}
							String xianQuId = sysXianquMapper.selectXianquId(s[j]);
							erpCarInfo.setXianquName(s[j]);
							erpCarInfo.setXianquId(xianQuId);
						}else if (j == 8 && !s[j].equals("")){
							//车主/业户 8
							erpCarInfo.setYehuName(s[j]);
						}else if (j == 9 && !s[j].equals("")){
							//联系人 9
							erpCarInfo.setContactPerson(s[j]);
						}else if (j == 10 && !s[j].equals("")){
							//联系人手机 10
							erpCarInfo.setContactPhoneNum(s[j]);
						}else if (j == 11 && !s[j].equals("")){
							//手机验证状态 11
							if(s[j]=="已验证"){
								erpCarInfo.setPhoneVerificationState("1");
							}else {
								erpCarInfo.setPhoneVerificationState("0");
							}

						}else if (j == 12 && !s[j].equals("")){
							//判断是否为日期类型
							Date date = getDate2New(s[j]);
							erpCarInfo.setFirstOnlineTime(date);
						}else if (j == 13 && !s[j].equals("")){
							//车辆识别代码/车架号  13
							erpCarInfo.setCarFrameNum(s[j]);
						}else if (j == 14 && !s[j].equals("")){
							//车牌号  14
							erpCarInfo.setCarPlateNum(s[j]);
						}else if (j == 15 && !s[j].equals("")){
							//车牌颜色 15
							if(s[j]=="蓝色"){
								erpCarInfo.setCarPlateColor("0");
							}else {
								erpCarInfo.setCarPlateColor("1");
							}
						}else if (j == 16 && !s[j].equals("")){
							//燃料种类 16
						}else if (j == 17 && !s[j].equals("")){
							//车辆类型 17
						}else if (j == 18 && !s[j].equals("")){
							//车辆品牌 18
						}else if (j == 19 && !s[j].equals("")){
							//车辆型号 19
						}else if (j == 20 && !s[j].equals("")){
							//道路运输经营许可证号 35
							erpCarInfo.setBusinessLicenseNumber(s[j]);
						}else if(j == 44 && !s[j].equals("")){
							//北斗平台
							erpCarInfo.setBeidouOperator(s[j]);
						}else if(j == 45 && !s[j].equals("")){
							//安装时间
							String endtime = s[j];
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date newEndTime = sdf.parse(endtime);
							erpCarInfo.setEndTime(newEndTime);
						}else if(j == 46 && !s[j].equals("")){
							//到期时间
							String installationTime = s[j];
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date newInstallationTime = sdf.parse(installationTime);
							erpCarInfo.setInstallationTime(newInstallationTime);
						}
					}
					mapper.updateById(erpCarInfo);
					/*ErpSim erpSim = erpSimMapper.selectBySimCode(s[3]);
					String supplierName ="";
					if(erpSim!=null) {
						supplierName = erpSim.getSupplierName();
					}
					updateBySimCodes(s[14],s[3],supplierName);*/
					continue;
				}else {
					ErpCarInfo erpCarInfo = new ErpCarInfo();
					ErpCarTechnicalDossier erpCarTechnicalDossier = new ErpCarTechnicalDossier();
					for (int j = 0; j < s.length; j++) {
							String id = Guid.guid();
							erpCarInfo.setId(id);
							erpCarTechnicalDossier.setId(id);
							if (j == 0 && !s[j].equals("")){
								//终端ID  0
								erpCarInfo.setTerminalId(s[j]);
							}else if (j == 1 && !s[j].equals("")){
								//终端厂商名称 1
								erpCarInfo.setTerminalChangshang(s[j]);
							}else if (j == 2 && !s[j].equals("")){
								//终端型号 2
								erpCarInfo.setTerminalCode(s[j]);
							}else if (j == 3 && !s[j].equals("")){
								//SIM卡卡号 3
								erpCarInfo.setSimCode(s[j]);
								//根据sim卡号查询相关信息
								ErpSim erpSim = erpSimMapper.selectBySimCode(s[j]);
								if(erpSim!=null){
									erpCarInfo.setSimId(erpSim.getId());
									erpCarInfo.setSupplierId(erpSim.getSupplierId());
									erpCarInfo.setSupplierName(erpSim.getSupplierName());
								}
							}else if (j == 4 && !s[j].equals("")){
								//道路运输证号 4
								erpCarInfo.setRoadTransportNum(s[j]);
							}else if (j == 5 && !s[j].equals("")){
								//所属省 5
								erpCarInfo.setProvinceName(s[j]);
								erpCarInfo.setProvinceId("HEBEI");
							}else if (j == 6 && !s[j].equals("")){
								//所属市 6
								erpCarInfo.setCityName(s[j]);
								erpCarInfo.setCityId("130300");
							}else if (j == 7 && !s[j].equals("")){
								//所属县 7  秦皇岛开发区 抚宁县 青龙满族自治县
								if(s[j].equals("秦皇岛开发区")){
									s[j]="开发区";
								}else if (s[j].equals("抚宁县")){
									s[j]="抚宁区";
								}else if (s[j].equals("青龙满族自治县")){
									s[j]="青龙县";
								}
								String xianQuId = sysXianquMapper.selectXianquId(s[j]);
								erpCarInfo.setXianquName(s[j]);
								erpCarInfo.setXianquId(xianQuId);
							}else if (j == 8 && !s[j].equals("")){
								//车主/业户 8
								erpCarInfo.setYehuName(s[j]);
							}else if (j == 9 && !s[j].equals("")){
								//联系人 9
								erpCarInfo.setContactPerson(s[j]);
							}else if (j == 10 && !s[j].equals("")){
								//联系人手机 10
								erpCarInfo.setContactPhoneNum(s[j]);
							}else if (j == 11 && !s[j].equals("")){
								//手机验证状态 11
								if(s[j]=="已验证"){
									erpCarInfo.setPhoneVerificationState("1");
								}else {
									erpCarInfo.setPhoneVerificationState("0");
								}

							}else if (j == 12 && !s[j].equals("")){
								//判断是否为日期类型
								Date date = getDate2New(s[j]);
								erpCarInfo.setFirstOnlineTime(date);
							}else if (j == 13 && !s[j].equals("")){
								//车辆识别代码/车架号  13
								erpCarInfo.setCarFrameNum(s[j]);
								erpCarTechnicalDossier.setCarFrameNum(s[j]);
							}else if (j == 14 && !s[j].equals("")){
								//车牌号  14
								erpCarInfo.setCarPlateNum(s[j]);
								erpCarTechnicalDossier.setCarPlateNum(s[j]);
							}else if (j == 15 && !s[j].equals("")){
								//车牌颜色 15
								if(s[j]=="蓝色"){
									erpCarInfo.setCarPlateColor("0");
									erpCarTechnicalDossier.setCarPlateColor("0");
								}else {
									erpCarInfo.setCarPlateColor("1");
									erpCarTechnicalDossier.setCarPlateColor("1");
								}
							}else if (j == 16 && !s[j].equals("")){
								//燃料种类 16
								erpCarTechnicalDossier.setFuelType(s[j]);
							}else if (j == 17 && !s[j].equals("")){
								//车辆类型 17
								erpCarTechnicalDossier.setCarType(s[j]);
							}else if (j == 18 && !s[j].equals("")){
								//车辆品牌 18
								erpCarTechnicalDossier.setCarBrand(s[j]);
							}else if (j == 19 && !s[j].equals("")){
								//车辆型号 19
								erpCarTechnicalDossier.setCarModel(s[j]);
							}else if (j == 20 && !s[j].equals("")){
								//总质量(kg) 20
								if(s[j].equals("--")){
									erpCarTechnicalDossier.setTotalMass("--");
								}else {
									erpCarTechnicalDossier.setTotalMass(s[j]);
								}

							}else if (j == 21 && !s[j].equals("")){
								//核定载质量(kg) 21
								erpCarTechnicalDossier.setHedingzaiMass(new BigDecimal(s[j]) );
							}else if (j == 22 && !s[j].equals("")){
								//准牵引总质量(kg) 22
								erpCarTechnicalDossier.setTractionMass(new BigDecimal(s[j]));
							}else if (j == 23 && !s[j].equals("")){
								//外廓尺寸-长(mm) 23
								erpCarTechnicalDossier.setTechOuterLength(s[j]);
							}else if (j == 24 && !s[j].equals("")){
								//外廓尺寸-宽(mm) 24
								erpCarTechnicalDossier.setTechOuterWidth(s[j]);
							}else if (j == 25 && !s[j].equals("")){
								//外廓尺寸-高(mm) 25
								erpCarTechnicalDossier.setTechOuterHeight(s[j]);
							}else if (j == 26 && !s[j].equals("")){
								//货厢内部尺寸-长(mm) 26
								erpCarTechnicalDossier.setBoxInnerLength(s[j]);
							}else if (j == 27 && !s[j].equals("")){
								//货厢内部尺寸-宽(mm) 27
								erpCarTechnicalDossier.setBoxInnerWidth(s[j]);
							}else if (j == 28 && !s[j].equals("")){
								//货厢内部尺寸-高(mm) 28
								erpCarTechnicalDossier.setBoxInnerHeight(s[j]);
							}else if (j == 29 && !s[j].equals("")){
								//轴数 29
								erpCarTechnicalDossier.setAxleNum(new Integer(s[j]));
							}else if (j == 30 && !s[j].equals("")){
								//轮胎数 30
								erpCarTechnicalDossier.setTyreNum(new Integer(s[j]));
							}else if (j == 31 && !s[j].equals("")){
								//轮胎规格 31
								erpCarTechnicalDossier.setTyreSpecifications(s[j]);
							}else if (j == 32 && !s[j].equals("")){
								//车辆出厂日期 32
								Date date = getDate(s[j]);
								erpCarTechnicalDossier.setCarProduceDate(date);
							}else if (j == 33 && !s[j].equals("")){
								//经营范围 33
								erpCarTechnicalDossier.setBusinessScope(s[j]);
							}else if (j == 34 && !s[j].equals("")){
								//车身颜色 34
								erpCarTechnicalDossier.setCarBodyColor(s[j]);
							}else if (j == 35 && !s[j].equals("")){
								//道路运输经营许可证号 35
								erpCarInfo.setBusinessLicenseNumber(s[j]);
							}else if (j == 36 && !s[j].equals("")){
								//车辆购置方式 36
								erpCarTechnicalDossier.setCarPurchaseType(s[j]);
							}else if (j == 37 && !s[j].equals("")){
								//车辆保险到期时间 37
								Date date = getDate(s[j]);
								erpCarTechnicalDossier.setCarInsuringEndTime(date);
							}else if (j == 38 && !s[j].equals("")){
								//检验有效期至 38
								Date date = getDate1(s[j]);
								erpCarTechnicalDossier.setCarCheckEndTime(date);
							}else if (j == 39 && !s[j].equals("")){
								//执照上传数 39
								erpCarTechnicalDossier.setLicenseUploadNum(new Integer(s[j]));
							}else if (j == 40 && !s[j].equals("")){
								//服务合同到期时间 40
								Date date = getDate(s[j]);
								erpCarTechnicalDossier.setServiceContractEndTime(date);
							}else if (j == 41 && !s[j].equals("")){
								//行驶证发证日期 41
								Date date = getDate(s[j]);
								erpCarTechnicalDossier.setDrivingPermitStartTime(date);
							}else if (j == 42 && !s[j].equals("")){
								//发动机号 42
								erpCarTechnicalDossier.setEngineNumber(s[j]);
							}else if (j == 43 && !s[j].equals("")){
								//发动机型号 43
								erpCarTechnicalDossier.setEngineModel(s[j]);
							}else if(j == 44 && !s[j].equals("")){
								//北斗平台
								erpCarInfo.setBeidouOperator(s[j]);
							}else if(j == 45 && !s[j].equals("")){
								//安装时间
								String endtime = s[j];
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date newEndTime = sdf.parse(endtime);
								erpCarInfo.setEndTime(newEndTime);
							}else if(j == 46 && !s[j].equals("")){
								//到期时间
								String installationTime = s[j];
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date newInstallationTime = sdf.parse(installationTime);
								erpCarInfo.setInstallationTime(newInstallationTime);
							}
							erpCarInfo.setDataState("1");
							erpCarTechnicalDossier.setDataState("1");
							erpCarInfo.setCreateTime(new Date());
							erpCarTechnicalDossier.setCreateTime(new Date());

						}
					listErpCarInfo.add(erpCarInfo);
					listErpCarTechnicalDossier.add(erpCarTechnicalDossier);

					}
				}
			}
			//批量插入
            if(listErpCarInfo!=null && listErpCarInfo.size()>0){
                mapper.insertListErpCarInfo(listErpCarInfo);
            }
            if(listErpCarTechnicalDossier!=null && listErpCarTechnicalDossier.size()>0){
                erpCarTechnicalDossierMapper.insertListErpCarTechnicalDossier(listErpCarTechnicalDossier);
            }
			/*if(listErpCarInfo.size()!=0){
				for(ErpCarInfo erpCarInfo1 : listErpCarInfo){
					if(erpCarInfo1.getId()!=null){
						mapper.insert(erpCarInfo1);
					}
				}
			}
			if(listErpCarTechnicalDossier.size()!=0){
				for(ErpCarTechnicalDossier erpCarTechnicalDossier1 : listErpCarTechnicalDossier){
					if(erpCarTechnicalDossier1.getId()!=null){
						erpCarTechnicalDossierMapper.insert(erpCarTechnicalDossier1);
					}
				}
			}*/
		return 1;
	}


    private void updateBySimCodes(String carPlateNum, String simCode, String supplierName){
		Map<String,String> map = new HashMap<>();
		map.put("carPlateNum",carPlateNum);
		map.put("simCode",simCode);
		map.put("supplierName",supplierName);
		mapper.updateBySimNumAndSupplierName(map);
	}

	private Date getDate2(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try{
			date = simpleDateFormat.parse(s);
		}catch (Exception e){
		    date = null;
			e.printStackTrace();
		}
		return date;
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



	private Date getDate1(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try{
			date = simpleDateFormat.parse(s);
		}catch (Exception e){
            date = null;
			e.printStackTrace();
		}
		return date;
	}


	public Date getDate(String strTime){
		Date date = null;
		if(strTime!=null && strTime.contains("CST")){
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
			try {
				date = sdf.parse(strTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			if (isValidDate(strTime)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try{
				date = simpleDateFormat.parse(strTime);
			}catch (Exception e){
				date = null;
				e.printStackTrace();
			}
			}else {
				return null;
			}
		}
		return date;
	}
	//假设传入的日期格式是yyyy-MM-dd HH:mm:ss, 也可以传入yyyy-MM-dd，如2018-1-1或者2018-01-01格式

	public static boolean isValidDate(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01

			format.setLenient(false);
			Date date = format.parse(strDate);

			//判断传入的yyyy年-MM月-dd日 字符串是否为数字
			String[] sArray = strDate.split("-");
			for (String s : sArray) {
				boolean isNum = s.matches("[0-9]+");
				//+表示1个或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
				if (!isNum) {
					return false;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}

		return true;
	}

	public List<ErpCarSimpleInfo> selectAllCarInfo(Map<String,Object> mapQuery) {
		return mapper.selectAllCarInfo(mapQuery);
	}

	public List<ErpCarSimpleInfo> selectAllCarInfoNew(Map<String,Object> mapQuery) {
		return mapper.selectAllCarInfoNew(mapQuery);
	}

	public void updateNewTerminal(ErpTerminalChangeRec erpTerminalChangeRec) {
		erpTerminalChangeRecMapper.insert(erpTerminalChangeRec);
		//更新车辆信息
		ErpCarInfo erpCarInfo = mapper.selectByIdCarInfo(erpTerminalChangeRec.getOwnerCarInfoId());
		erpCarInfo.setTerminalId(erpTerminalChangeRec.getNewTerminalCode());
		mapper.updateByTerminal(erpCarInfo);
	}

	public void selectByCarNum(String simNum ,String supplierName) {
		mapper.selectByCarNums(simNum,supplierName);
	}

	public List<ErpCarSimpleInfo> selectAllById(List<String> idList) {
		return mapper.selectAllById(idList);
	}


	public void updateBySimNum(Map<String, String> map) {
		mapper.updateBySimNum(map);
	}

	public void sendRemindCheckGpsPhoneMsg(String beidouOperator) {
		//单个手机号
		Map<String,Object> map = new HashMap<>();
		map.put("sendType","REMIND_CHECK_GPS_TZ_MSG");
		map.put("content","【北斗平台】尊敬的车主：出车前务必检查车辆北斗定位情况，核对小票打印信息和疲劳驾驶记录，如设备状态异常请及时联系各县区售后进行维修。路上严查，以免罚款。北斗定位查询方法：微,信关注《道路运输服务网》公,众,号，点击车辆定位查看。");
		map.put("beidouOperator",beidouOperator);
		mapper.sendRemindCheckGpsPhoneMsg(map);
	}

	public int countSumNum() {
		return mapper.countSumNum();
	}
}
