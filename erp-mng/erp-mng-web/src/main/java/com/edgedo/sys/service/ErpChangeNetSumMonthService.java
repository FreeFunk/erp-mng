package com.edgedo.sys.service;
		
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpChangeNetSumMonth;
import com.edgedo.sys.mapper.ErpChangeNetRecMapper;
import com.edgedo.sys.mapper.ErpChangeNetSumMonthMapper;
import com.edgedo.sys.queryvo.ErpChangeNetCounMonthView;
import com.edgedo.sys.queryvo.ErpChangeNetRecView;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthQuery;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpChangeNetSumMonthService {
	
	
	@Autowired
	private ErpChangeNetSumMonthMapper mapper;

	@Autowired
	private ErpChangeNetRecMapper erpChangeNetRecMapper;

	String flag = "0";
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpChangeNetSumMonthView> listPage(ErpChangeNetSumMonthQuery query){
		List list = mapper.selectByList(query);
		query.setList(list);
		return list;
	}
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpChangeNetSumMonth voObj) {
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
	public String update(ErpChangeNetSumMonth voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpChangeNetSumMonth voObj) {
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
	public ErpChangeNetSumMonth loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 定时任务
	 * @param query
	 */
	public void dingshirenwu(ErpChangeNetSumMonthQuery query){
		Date sendDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(sendDate);
		if(newDate.equals("")){
			listCountMonth(query);
		}
		Timer dTimer = new Timer();
		dTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				int day = c.get(Calendar.DAY_OF_MONTH);
				if (day == 30) {
					// 每天执行，若为每月30号才执行
					//Statistics(query);
					System.out.println("每月定时任务已执行");
				}
			}
		}, sendDate,  24* 60* 60 * 1000);//24* 60* 60 * 1000
	}

	/**
	 * 月度统计
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetSumMonthView> listCountMonth(ErpChangeNetSumMonthQuery query) {
		List<ErpChangeNetSumMonthView> erpChangeNetCounMonthList= new ArrayList<>();

		//List<ErpChangeNetRecView> nowPT = mapper.selectByNowPt(query);
		//添加现有平台

		Calendar cal = Calendar.getInstance();
		//提取月份
		cal.setTime(new Date());

		Integer month;
		Integer year;
		month= cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		//String newData = String.valueOf(year)+"-"+String.valueOf(month);
		String stringMonh = String.valueOf(month);
		String newMonth;
		String data;
		// 将字符串转换为字符数组
		char[] chs = stringMonh.toCharArray();
		if(chs.length == 1){
			newMonth = "0"+month;
			data = String.valueOf(year)+"-"+newMonth;
		}else{
			newMonth = stringMonh;
			data = String.valueOf(year)+"-"+newMonth;
		}
		List<ErpChangeNetRecView> erpChangeNetRecList = erpChangeNetRecMapper.selectAlls(query.getQueryObj().getCountData());
		for (ErpChangeNetRecView erpChangeNetRecView:erpChangeNetRecList) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
			Date erpChangeNetData = erpChangeNetRecView.getCreateTime();
			String newErpChangeNetData = sdf.format(erpChangeNetData);
			if(newErpChangeNetData.equals(query.getQueryObj().getCountData())){
				ErpChangeNetSumMonthView erpChangeNetCounMonth = new ErpChangeNetSumMonthView();
				//ID
				erpChangeNetCounMonth.setId(Guid.guid());
				//创建时间
				erpChangeNetCounMonth.setCreateTime(new Date());
				//现平台
				erpChangeNetCounMonth.setNowPt(erpChangeNetRecView.getNowPt());
				//转网类型
				erpChangeNetCounMonth.setChangeNetType(erpChangeNetRecView.getChangeNetType());//时间年月
				erpChangeNetCounMonth.setCountData(data);
				//月份
				erpChangeNetCounMonth.setCountMonth(month);
				erpChangeNetCounMonthList.add(erpChangeNetCounMonth);
			}
		}
		//去重
		for (int x = 0; x < erpChangeNetCounMonthList.size() - 1; x++) {
			for (int y = x + 1; y < erpChangeNetCounMonthList.size(); y++) {
				if (erpChangeNetCounMonthList.get(x).getNowPt().equals(erpChangeNetCounMonthList.get(y).getNowPt())
						&& erpChangeNetCounMonthList.get(x).getChangeNetType().equals(erpChangeNetCounMonthList.get(y).getChangeNetType())) {
					erpChangeNetCounMonthList.remove(y);
					y--;
				}
			}
		}

		//开始统计
		for (ErpChangeNetSumMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList) {
			//转出统计
			int haiGangQu=0;
			int kaiFaQu=0;
			int shanHaiGuan=0;
			int beiDaiHe=0;
			int fuNingQu=0;
			int qingLongXian=0;
			int changLiXian=0;
			int luLongXian=0;
			//转入统计
			int haiGangQu2=0;
			int kaiFaQu2=0;
			int shanHaiGuan2=0;
			int beiDaiHe2=0;
			int fuNingQu2=0;
			int qingLongXian2=0;
			int changLiXian2 = 0;
			int luLongXian2=0;
			for(ErpChangeNetRecView erpChangeNetRecView:erpChangeNetRecList) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
				Date erpChangeNetData = erpChangeNetRecView.getCreateTime();
				String newErpChangeNetData = sdf.format(erpChangeNetData);
				if(erpChangeNetCounMonthView.getNowPt().equals(erpChangeNetRecView.getNowPt()) && newErpChangeNetData.equals(query.getQueryObj().getCountData())){
					if(erpChangeNetRecView.getXianquName()!=null &&  erpChangeNetRecView.getChangeNetType().equals("转出")){
						if(erpChangeNetRecView.getXianquName().equals("海港区")){
							haiGangQu++;
						}else if(erpChangeNetRecView.getXianquName().equals("山海关区")){
							shanHaiGuan++;
						}else if(erpChangeNetRecView.getXianquName().equals("北戴河区")){
							beiDaiHe++;
						}else if(erpChangeNetRecView.getXianquName().equals("抚宁县")){
							fuNingQu++;
						}else if(erpChangeNetRecView.getXianquName().equals("青龙满族自治县")){
							qingLongXian++;
						}else if(erpChangeNetRecView.getXianquName().equals("昌黎县")){
							changLiXian++;
						}else if(erpChangeNetRecView.getXianquName().equals("卢龙县")){
							luLongXian++;
						}else if(erpChangeNetRecView.getXianquName().equals("秦皇岛开发区")){
							kaiFaQu++;
						}
					}else if(erpChangeNetRecView.getXianquName()!=null && erpChangeNetRecView.getChangeNetType().equals("转入")){
						if(erpChangeNetRecView.getXianquName().equals("海港区")){
							haiGangQu2++;
						}else if(erpChangeNetRecView.getXianquName().equals("山海关区")){
							shanHaiGuan2++;
						}else if(erpChangeNetRecView.getXianquName().equals("北戴河区")){
							beiDaiHe2++;
						}else if(erpChangeNetRecView.getXianquName().equals("抚宁县")){
							fuNingQu2++;
						}else if(erpChangeNetRecView.getXianquName().equals("青龙满族自治县")){
							qingLongXian2++;
						}else if(erpChangeNetRecView.getXianquName().equals("昌黎县")){
							changLiXian2++;
						}else if(erpChangeNetRecView.getXianquName().equals("卢龙县")){
							luLongXian2++;
						}else if(erpChangeNetRecView.getXianquName().equals("秦皇岛开发区")){
							kaiFaQu2++;
						}
					}
					if(erpChangeNetCounMonthView.getChangeNetType().equals("转出")){
						erpChangeNetCounMonthView.setHaiGangQu(haiGangQu);
						erpChangeNetCounMonthView.setShanHaiGuanQu(shanHaiGuan);
						erpChangeNetCounMonthView.setBeiDaiHeQu(beiDaiHe);
						erpChangeNetCounMonthView.setFuNingQu(fuNingQu);
						erpChangeNetCounMonthView.setQingLongXian(qingLongXian);
						erpChangeNetCounMonthView.setChangLiXian(changLiXian);
						erpChangeNetCounMonthView.setLuLongXian(luLongXian);
						erpChangeNetCounMonthView.setKaiFaQu(kaiFaQu);
					}else{
						erpChangeNetCounMonthView.setHaiGangQu(haiGangQu2);
						erpChangeNetCounMonthView.setShanHaiGuanQu(shanHaiGuan2);
						erpChangeNetCounMonthView.setBeiDaiHeQu(beiDaiHe2);
						erpChangeNetCounMonthView.setFuNingQu(fuNingQu2);
						erpChangeNetCounMonthView.setQingLongXian(qingLongXian2);
						erpChangeNetCounMonthView.setChangLiXian(changLiXian2);
						erpChangeNetCounMonthView.setLuLongXian(luLongXian2);
						erpChangeNetCounMonthView.setKaiFaQu(kaiFaQu2);
					}
				}
				erpChangeNetCounMonthView.setCountType("已统计");
			}
		}

		//转出合计统计
		int sumCount;
		int haiGangQuAllSum=0;
		int kaiFaQuAllSum =0;
		int shanHaiGuanAllSum=0;
		int beiDaiHeAllSum=0;
		int fuNingQuAllSum=0;
		int qingLongXianAllSum=0;
		int changLiXianAllSum=0;
		int luLongXianAllSum=0;
		int allSumCount=0;
		//转入合计统计
		int sumCount2;
		int haiGangQuAllSum2=0;
		int kaiFaQuAllSum2 =0;
		int shanHaiGuanAllSum2=0;
		int beiDaiHeAllSum2=0;
		int fuNingQuAllSum2=0;
		int qingLongXianAllSum2=0;
		int changLiXianAllSum2=0;
		int luLongXianAllSum2=0;
		int allSumCount2=0;
		ErpChangeNetSumMonthView erpChangeNetCounMonth1 = new ErpChangeNetSumMonthView();
		ErpChangeNetSumMonthView erpChangeNetCounMonth2 = new ErpChangeNetSumMonthView();
		for(ErpChangeNetSumMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList){
			if(erpChangeNetCounMonthView.getChangeNetType().equals("转出")){
				//海港区合计
				haiGangQuAllSum = haiGangQuAllSum+erpChangeNetCounMonthView.getHaiGangQu();
				//山海关合计
				shanHaiGuanAllSum = shanHaiGuanAllSum+erpChangeNetCounMonthView.getShanHaiGuanQu();
				//北戴河合计
				beiDaiHeAllSum = beiDaiHeAllSum+erpChangeNetCounMonthView.getBeiDaiHeQu();
				//抚宁县合计
				fuNingQuAllSum = fuNingQuAllSum+erpChangeNetCounMonthView.getFuNingQu();
				//开发区合计
				kaiFaQuAllSum = kaiFaQuAllSum + erpChangeNetCounMonthView.getKaiFaQu();
				//青龙县合计
				qingLongXianAllSum = qingLongXianAllSum+erpChangeNetCounMonthView.getQingLongXian();
				//昌黎县合计
				changLiXianAllSum = changLiXianAllSum+erpChangeNetCounMonthView.getChangLiXian();
				//卢龙县合计
				luLongXianAllSum = luLongXianAllSum+erpChangeNetCounMonthView.getLuLongXian();
				sumCount = erpChangeNetCounMonthView.getHaiGangQu()+erpChangeNetCounMonthView.getShanHaiGuanQu()+erpChangeNetCounMonthView.getBeiDaiHeQu()+erpChangeNetCounMonthView.getFuNingQu()
						+erpChangeNetCounMonthView.getQingLongXian()+erpChangeNetCounMonthView.getChangLiXian()+erpChangeNetCounMonthView.getLuLongXian()+erpChangeNetCounMonthView.getKaiFaQu();
				erpChangeNetCounMonth1.setChangeNetType("转出");
				erpChangeNetCounMonth1.setCountData(data);
				erpChangeNetCounMonthView.setSumCount(sumCount);
				allSumCount = allSumCount+sumCount;
			}else if(erpChangeNetCounMonthView.getChangeNetType().equals("转入") && erpChangeNetCounMonthView != null){
				System.out.println(erpChangeNetCounMonthView.getHaiGangQu());
				haiGangQuAllSum2 = haiGangQuAllSum2+erpChangeNetCounMonthView.getHaiGangQu();
				shanHaiGuanAllSum2 = shanHaiGuanAllSum2+erpChangeNetCounMonthView.getShanHaiGuanQu();
				beiDaiHeAllSum2 = beiDaiHeAllSum2+erpChangeNetCounMonthView.getBeiDaiHeQu();
				fuNingQuAllSum2 = fuNingQuAllSum2+erpChangeNetCounMonthView.getFuNingQu();
				kaiFaQuAllSum2 = kaiFaQuAllSum2+erpChangeNetCounMonthView.getKaiFaQu();
				qingLongXianAllSum2 = qingLongXianAllSum2+erpChangeNetCounMonthView.getQingLongXian();
				changLiXianAllSum2 = changLiXianAllSum2+erpChangeNetCounMonthView.getChangLiXian();
				luLongXianAllSum2 = luLongXianAllSum2+erpChangeNetCounMonthView.getLuLongXian();
				sumCount2 = erpChangeNetCounMonthView.getHaiGangQu()+erpChangeNetCounMonthView.getShanHaiGuanQu()+erpChangeNetCounMonthView.getBeiDaiHeQu()+erpChangeNetCounMonthView.getFuNingQu()
						+erpChangeNetCounMonthView.getQingLongXian()+erpChangeNetCounMonthView.getChangLiXian()+erpChangeNetCounMonthView.getLuLongXian()+erpChangeNetCounMonthView.getKaiFaQu();
				erpChangeNetCounMonth2.setChangeNetType("转入");
				erpChangeNetCounMonth2.setCountData(data);
				erpChangeNetCounMonthView.setSumCount(sumCount2);
				allSumCount2 = allSumCount2+sumCount2;
			}

		}
		//转出合计行
		erpChangeNetCounMonth1.setId(Guid.guid());
		erpChangeNetCounMonth1.setNowPt("合计");
		erpChangeNetCounMonth1.setHaiGangQu(haiGangQuAllSum);
		erpChangeNetCounMonth1.setShanHaiGuanQu(shanHaiGuanAllSum);
		erpChangeNetCounMonth1.setKaiFaQu(kaiFaQuAllSum);
		erpChangeNetCounMonth1.setBeiDaiHeQu(beiDaiHeAllSum);
		erpChangeNetCounMonth1.setFuNingQu(fuNingQuAllSum);
		erpChangeNetCounMonth1.setQingLongXian(qingLongXianAllSum);
		erpChangeNetCounMonth1.setChangLiXian(changLiXianAllSum);
		erpChangeNetCounMonth1.setLuLongXian(luLongXianAllSum);
		erpChangeNetCounMonth1.setCountMonth(month);
		erpChangeNetCounMonth1.setSumCount(allSumCount);
		//erpChangeNetCounMonthList.add(erpChangeNetCounMonth1);
		//转入合计行
		erpChangeNetCounMonth2.setId(Guid.guid());
		erpChangeNetCounMonth2.setNowPt("合计");
		erpChangeNetCounMonth2.setHaiGangQu(haiGangQuAllSum2);
		erpChangeNetCounMonth2.setShanHaiGuanQu(shanHaiGuanAllSum2);
		erpChangeNetCounMonth2.setKaiFaQu(kaiFaQuAllSum2);
		erpChangeNetCounMonth2.setBeiDaiHeQu(beiDaiHeAllSum2);
		erpChangeNetCounMonth2.setFuNingQu(fuNingQuAllSum2);
		erpChangeNetCounMonth2.setQingLongXian(qingLongXianAllSum2);
		erpChangeNetCounMonth2.setChangLiXian(changLiXianAllSum2);
		erpChangeNetCounMonth2.setLuLongXian(luLongXianAllSum2);
		erpChangeNetCounMonth2.setCountMonth(month);
		erpChangeNetCounMonth2.setSumCount(allSumCount2);
		//erpChangeNetCounMonthList.add(erpChangeNetCounMonth2);
		List<ErpChangeNetSumMonthView> erpChangeNetSumMonthViewesList =  mapper.queryAll(query.getQueryObj().getCountData());

		if(erpChangeNetSumMonthViewesList!=null){
			for(ErpChangeNetSumMonthView erpChangeNetSumMonth:erpChangeNetSumMonthViewesList){
				for(ErpChangeNetSumMonthView erpChangeNetSumMonthList:erpChangeNetCounMonthList){
					if(erpChangeNetSumMonth.getNowPt().equals(erpChangeNetSumMonthList.getNowPt())
							&& erpChangeNetSumMonth.getCountData().equals(erpChangeNetSumMonthList.getCountData())){
						flag = "1";
						break;
					}
				}
			}
		}
		if(flag.equals("0")){
			for(ErpChangeNetSumMonthView erpChangeNetSumMonthList:erpChangeNetCounMonthList){
				mapper.insert(erpChangeNetSumMonthList);
			}
		}

		//按月份查找列表
		List<ErpChangeNetSumMonthView> newErpChangeNetCounMonthList=new ArrayList<>();
			String selectMonth = query.getQueryObj().getCountData();
			String date[] = selectMonth.split("-");
			int i = Integer.valueOf(date[1]);
		for(ErpChangeNetSumMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList){
			if(query.getQueryObj().getChangeNetType()!=null && !query.getQueryObj().getChangeNetType().equals("")){
				if(erpChangeNetCounMonthView.getCountData().equals(selectMonth) && erpChangeNetCounMonthView.getChangeNetType().equals(query.getQueryObj().getChangeNetType())){
					newErpChangeNetCounMonthList.add(erpChangeNetCounMonthView);
					newErpChangeNetCounMonthList.sort((x,y)->y.getSumCount()-x.getSumCount());
				}
			}else{
				if(erpChangeNetCounMonthView.getCountData().equals(selectMonth)){
					newErpChangeNetCounMonthList.add(erpChangeNetCounMonthView);
					newErpChangeNetCounMonthList.sort((x,y)->y.getSumCount()-x.getSumCount());
				}
			}
		}
		if(query.getQueryObj().getChangeNetType().equals("转出") && query.getQueryObj().getCountData().equals(data)){
			newErpChangeNetCounMonthList.add(erpChangeNetCounMonth1);
		}else if(query.getQueryObj().getChangeNetType().equals("转入") && query.getQueryObj().getCountData().equals(data)){
			newErpChangeNetCounMonthList.add(erpChangeNetCounMonth2);
		}
		query.setList(newErpChangeNetCounMonthList);
		return newErpChangeNetCounMonthList;
	}
		/*String neirong="";*/
}
