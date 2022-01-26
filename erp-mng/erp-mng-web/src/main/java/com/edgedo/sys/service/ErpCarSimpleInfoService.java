package com.edgedo.sys.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.mapper.*;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoQuery;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarSimpleInfoService {
	
	
	@Autowired
	private ErpCarSimpleInfoMapper mapper;

	@Autowired
	private ErpAddCarStatisticsMapper erpAddCarStatisticsMapper;

	@Autowired
	private ErpBeidouAddStatisticsMapper erpBeidouAddStatisticsMapper;

	@Autowired
	private ErpOperatorMapper erpOperatorMapper;

	@Autowired
	private SysXianquMapper sysXianquMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarSimpleInfoView> listPage(ErpCarSimpleInfoQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarSimpleInfo voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCarSimpleInfo voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarSimpleInfo voObj) {
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
	public int deleteByIds(List<String> ids) {
		
		return mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCarSimpleInfo loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 获取每周五的车辆增加信息
	 */
	//@Scheduled(cron = "0/2 * * * * ? ")
	public void selectAndInsertCarAddNum() {
		String currToday = getDate(new Date());
		//3.若是进行查询  根据SUPPLIER_NAME  模糊查询  县区模糊查询
		//创建两个数组  一个存放县区  一个存放  供应商
		List<ErpOperator> listSupplierName = erpOperatorMapper.selectByList();
		List<SysXianqu> listXianQu = sysXianquMapper.selectyList();
//		String[] xianQuArr = new String[]{"海港区","抚宁县","昌黎县","卢龙县","开发区","山海关","青龙满族自治县","北戴河"};
//		String[] supplierNameArr = new String[]
//				{"中电科","烽火台","天地通","盛通","中斗","乐麒通","信达","通斗","唐山中交",
//							"弗斯特","神龙","广安","首航","万合","融星","奇华","天枢"};
		for(SysXianqu sysXianqu : listXianQu){
			for(ErpOperator erpOperator : listSupplierName){
				if(sysXianqu.getName().equals("青龙县")){
					String xianQu = "青龙满族自治县";
					//封装对象
					ErpAddCarStatistics erpAddCarStatistics = new ErpAddCarStatistics();
					erpAddCarStatistics.setId(Guid.guid());//主键
					erpAddCarStatistics.setCreateTime(new Date());//创建时间
					erpAddCarStatistics.setXianquName(xianQu);//县区名
					erpAddCarStatistics.setStatisticsCarToday(currToday);//当天时间
					erpAddCarStatistics.setSupplierName(erpOperator.getName());//供应商名字
					erpAddCarStatistics.setDataState("1");//数据状态
					//4.返回个数
					int num = mapper.selectBySupplierNameNum(xianQu,erpOperator.getName());//根据县区和供应商名查询
					erpAddCarStatistics.setCarTodayNum(num);//车辆数
					//5.插入数据库中
					erpAddCarStatisticsMapper.insert(erpAddCarStatistics);
				}else if(sysXianqu.getName().equals("抚宁区")){
					String xianQu = "抚宁县";
					//封装对象
					ErpAddCarStatistics erpAddCarStatistics = new ErpAddCarStatistics();
					erpAddCarStatistics.setId(Guid.guid());//主键
					erpAddCarStatistics.setCreateTime(new Date());//创建时间
					erpAddCarStatistics.setXianquName(xianQu);//县区名
					erpAddCarStatistics.setStatisticsCarToday(currToday);//当天时间
					erpAddCarStatistics.setSupplierName(erpOperator.getName());//供应商名字
					erpAddCarStatistics.setDataState("1");//数据状态
					//4.返回个数
					int num = mapper.selectBySupplierNameNum(xianQu,erpOperator.getName());//根据县区和供应商名查询
					erpAddCarStatistics.setCarTodayNum(num);//车辆数
					//5.插入数据库中
					erpAddCarStatisticsMapper.insert(erpAddCarStatistics);
				}else {
					//封装对象
					ErpAddCarStatistics erpAddCarStatistics = new ErpAddCarStatistics();
					erpAddCarStatistics.setId(Guid.guid());//主键
					erpAddCarStatistics.setCreateTime(new Date());//创建时间
					erpAddCarStatistics.setXianquName(sysXianqu.getName());//县区名
					erpAddCarStatistics.setStatisticsCarToday(currToday);//当天时间
					erpAddCarStatistics.setSupplierName(erpOperator.getName());//供应商名字
					erpAddCarStatistics.setDataState("1");//数据状态
					//4.返回个数
					int num = mapper.selectBySupplierNameNum(sysXianqu.getName(),erpOperator.getName());//根据县区和供应商名查询
					erpAddCarStatistics.setCarTodayNum(num);//车辆数
					//5.插入数据库中
					erpAddCarStatisticsMapper.insert(erpAddCarStatistics);
				}
			}
		}
	}

	/**
	 * 格式化日期
	 */
	public String getDate(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currDateStr = simpleDateFormat.format(date);
		return currDateStr;
	}


	/**
	 * 根据入网时间  判断  在上周六到这周五的车辆区间
	 */
	//@Scheduled(cron = "0/2 * * * * ? ")
	public void selectAndInsertCarBeiDouNum() {
		//计算 当前时间和6天前的时间
		String currDate = getDate(new Date());//当前时间
		String betweenDate = getBetweenTime(new Date());//6天前的时间
		//创建两个数组  一个存放县区  一个存放  平台名
		List<ErpOperator> listSupplierName = erpOperatorMapper.selectByList();
		List<SysXianqu> listXianQu = sysXianquMapper.selectyList();
//		String[] xianQuArr = new String[]{"海港区","抚宁县","昌黎县","卢龙县","开发区","青龙满族自治县"};
//		String[] supplierNameArr = new String[]
//				{"轩慧","中交兴路","盛通","中斗","乐麒通","烽火台","通斗","中电科"};
		//1.进行遍历
		for(SysXianqu sysXianqu : listXianQu){
			for(ErpOperator erpOperator : listSupplierName) {
				if(sysXianqu.getName().equals("青龙县")){
					String xianQu = "青龙满族自治县";
					//2.封装对象
					ErpBeidouAddStatistics erpBeidouAddStatistics = new ErpBeidouAddStatistics();
					erpBeidouAddStatistics.setId(Guid.guid());//主键
					erpBeidouAddStatistics.setCreateTime(new Date());//创建时间
					erpBeidouAddStatistics.setXianquName(xianQu);//县区
					erpBeidouAddStatistics.setPlatformName(erpOperator.getName());//平台名
					erpBeidouAddStatistics.setStatisticsToday(betweenDate+"-"+currDate);//统计时间区间
					//3.根据统计的时间区间区查询该区间入网的车辆  根据县区  平台名
					int num = mapper.selectByRuWangTime(xianQu,erpOperator.getName(),betweenDate,currDate);
					erpBeidouAddStatistics.setCarAddNum(num);
					erpBeidouAddStatistics.setDataState("1");//数据状态
					//上升数据库
					erpBeidouAddStatisticsMapper.insert(erpBeidouAddStatistics);
				}else if(sysXianqu.getName().equals("抚宁区")){
					//2.封装对象
					String xianQu = "抚宁县";
					ErpBeidouAddStatistics erpBeidouAddStatistics = new ErpBeidouAddStatistics();
					erpBeidouAddStatistics.setId(Guid.guid());//主键
					erpBeidouAddStatistics.setCreateTime(new Date());//创建时间
					erpBeidouAddStatistics.setXianquName(xianQu);//县区
					erpBeidouAddStatistics.setPlatformName(erpOperator.getName());//平台名
					erpBeidouAddStatistics.setStatisticsToday(betweenDate+"-"+currDate);//统计时间区间
					//3.根据统计的时间区间区查询该区间入网的车辆  根据县区  平台名
					int num = mapper.selectByRuWangTime(xianQu,erpOperator.getName(),betweenDate,currDate);
					erpBeidouAddStatistics.setCarAddNum(num);
					erpBeidouAddStatistics.setDataState("1");//数据状态
					//上升数据库
					erpBeidouAddStatisticsMapper.insert(erpBeidouAddStatistics);
				}else {
					//2.封装对象
					ErpBeidouAddStatistics erpBeidouAddStatistics = new ErpBeidouAddStatistics();
					erpBeidouAddStatistics.setId(Guid.guid());//主键
					erpBeidouAddStatistics.setCreateTime(new Date());//创建时间
					erpBeidouAddStatistics.setXianquName(sysXianqu.getName());//县区
					erpBeidouAddStatistics.setPlatformName(erpOperator.getName());//平台名
					erpBeidouAddStatistics.setStatisticsToday(betweenDate+"-"+currDate);//统计时间区间
					//3.根据统计的时间区间区查询该区间入网的车辆  根据县区  平台名
					int num = mapper.selectByRuWangTime(sysXianqu.getName(),erpOperator.getName(),betweenDate,currDate);
					erpBeidouAddStatistics.setCarAddNum(num);
					erpBeidouAddStatistics.setDataState("1");//数据状态
					//上升数据库
					erpBeidouAddStatisticsMapper.insert(erpBeidouAddStatistics);
				}

			}
		}

	}

	/**
	 * 设定时间区间
	 * 上周六 到这周五
	 */
	public String getBetweenTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -6);
		Date dateNew = c.getTime();
		String preDay = sdf.format(dateNew);
		return preDay;
	}

	public ErpCarSimpleInfoView selectByCarPlateNum(String carPlateNum) {
		return mapper.selectByCarPlateNum(carPlateNum);
	}

	public int selectByNewTime() {
		String time = getDate(new Date());
		return erpAddCarStatisticsMapper.selectByFlag(time);
	}

	public int selectByBeiDouNewTime() {
		String endTime = getDate(new Date());
		String startTime = getBetweenSevenDay(new Date());
		String time = startTime+"-"+endTime;
		return erpBeidouAddStatisticsMapper.selectByFlag(time);
	}

	public String getBetweenSevenDay(Date dt){
		long time= dt.getTime();
		//获取7天的毫秒数
		long sevenTime = 7*24*60*60*1000;
		//当前时间毫秒数-7天的毫秒数=7天之间那天的毫秒数
		long times = time - sevenTime;
		//将毫秒数转日期
		Date dat=new Date(times);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String sb=format.format(gc.getTime());
		return sb;
	}
	public void insertOrUpdate(ErpCarSimpleInfo erpCarSimpleInfo) {
		ErpCarSimpleInfo erpCarSimpleInfo1= mapper.selectByCarFrameNumNew(erpCarSimpleInfo.getCarFrameNum());
		if(erpCarSimpleInfo1==null){
			erpCarSimpleInfo.setCreateTime(new Date());
			insert(erpCarSimpleInfo);
		}else {
			erpCarSimpleInfo.setId(erpCarSimpleInfo1.getId());
			erpCarSimpleInfo.setCreateTime(new Date());
			update(erpCarSimpleInfo);
		}
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,isolation= Isolation.SERIALIZABLE)
	public void insertAll(List<CarSimpleInfoVo> list) {
		mapper.insertAll(list);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteAll() {
		mapper.deleteAll();
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public int countByXianquId(String xianquId) {
		return  mapper.countByXianquId(xianquId);
	}

	public List<ErpCarSimpleInfo> selectAllCarInfo() {
		return mapper.selectAllCarInfo();
	}
}
