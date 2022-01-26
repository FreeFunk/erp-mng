package com.edgedo.sys.service;

import java.lang.reflect.Array;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpChangeNetCounMonth;
import com.edgedo.sys.entity.ErpChangeNetRec;
import com.edgedo.sys.mapper.ErpChangeNetRecMapper;
import com.edgedo.sys.queryvo.ErpChangeNetCounMonthQuery;
import com.edgedo.sys.queryvo.ErpChangeNetCounMonthView;
import com.edgedo.sys.queryvo.ErpChangeNetRecQuery;
import com.edgedo.sys.queryvo.ErpChangeNetRecView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpChangeNetRecService {


	@Autowired
	private ErpChangeNetRecMapper mapper;



	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpChangeNetRecView> listPage(ErpChangeNetRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpChangeNetRec voObj) {
		voObj.setId(Guid.guid());
		voObj.setCreateTime(new Date());
		mapper.insert(voObj);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpChangeNetRec voObj) {
		mapper.updateById(voObj);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpChangeNetRec voObj) {
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

		return mapper.deleteByDataState(ids);
	}



	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpChangeNetRec loadById(String id) {
		return mapper.selectById(id);
	}


	public ErpChangeNetRec selectByPlatformID(String platformID) {
		return mapper.selectByPlatformID(platformID);
	}


	/**
	 * 月度统计
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetCounMonthView> listCountMonth(ErpChangeNetCounMonthQuery query) {
		List<ErpChangeNetCounMonthView> erpChangeNetCounMonthList= new ArrayList<>();
		List<ErpChangeNetRecView> erpChangeNetRecList = mapper.selectAlls(query.getQueryObj().getChangeNetType());
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

		for (ErpChangeNetRecView erpChangeNetRecView:erpChangeNetRecList) {
			ErpChangeNetCounMonthView erpChangeNetCounMonth = new ErpChangeNetCounMonthView();
			//现平台
			erpChangeNetCounMonth.setNowPt(erpChangeNetRecView.getNowPt());
			//
			erpChangeNetCounMonth.setChangeNetType(erpChangeNetRecView.getChangeNetType());
			//时间
			erpChangeNetCounMonth.setCountData(data);
			//月份
			erpChangeNetCounMonth.setCountMonth(month);
			erpChangeNetCounMonthList.add(erpChangeNetCounMonth);

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
		for (ErpChangeNetCounMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList) {
			//转出统计
			int haiGangQuSum=0;
			int kaiFaQu=0;
			int shanHaiGuanSum=0;
			int beiDaiHeSum=0;
			int fuNingQuSum=0;
			int qingLongXianSum=0;
			int changLiXianSum=0;
			int luLongXianSum=0;

			//转入统计
			int haiGangQuSum2=0;
			int kaiFaQu2=0;
			int shanHaiGuanSum2=0;
			int beiDaiHeSum2=0;
			int fuNingQuSum2=0;
			int qingLongXianSum2=0;
			int changLiXianSum2=0;
			int luLongXianSum2=0;

			int sumCounts;
			for(ErpChangeNetRecView erpChangeNetRecView:erpChangeNetRecList) {
				if(erpChangeNetCounMonthView.getNowPt().equals(erpChangeNetRecView.getNowPt())){
					if(erpChangeNetRecView.getXianquName()!=null &&  erpChangeNetRecView.getChangeNetType().equals("转出")){
						if(erpChangeNetRecView.getXianquName().equals("海港区")){
							haiGangQuSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("山海关区")){
							shanHaiGuanSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("北戴河区")){
							beiDaiHeSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("抚宁县")){
							fuNingQuSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("青龙满族自治县")){
							qingLongXianSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("昌黎县")){
							changLiXianSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("卢龙县")){
							luLongXianSum++;
						}else if(erpChangeNetRecView.getXianquName().equals("秦皇岛开发区")){
							kaiFaQu++;
						}
					}else if(erpChangeNetRecView.getXianquName()!=null && erpChangeNetRecView.getChangeNetType().equals("转入")){
						if(erpChangeNetRecView.getXianquName().equals("海港区")){
							haiGangQuSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("山海关区")){
							shanHaiGuanSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("北戴河区")){
							beiDaiHeSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("抚宁县")){
							fuNingQuSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("青龙满族自治县")){
							qingLongXianSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("昌黎县")){
							changLiXianSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("卢龙县")){
							luLongXianSum2++;
						}else if(erpChangeNetRecView.getXianquName().equals("秦皇岛开发区")){
							kaiFaQu2++;
						}
					}
					if(erpChangeNetRecView.getChangeNetType().equals("转出")){
						erpChangeNetCounMonthView.setHaiGangQu(haiGangQuSum);
						erpChangeNetCounMonthView.setShanHaiGuan(shanHaiGuanSum);
						erpChangeNetCounMonthView.setBeiDaiHe(beiDaiHeSum);
						erpChangeNetCounMonthView.setFuNingQu(fuNingQuSum);
						erpChangeNetCounMonthView.setQingLongXian(qingLongXianSum);
						erpChangeNetCounMonthView.setChangLiXian(changLiXianSum);
						erpChangeNetCounMonthView.setLuLongXian(luLongXianSum);
						erpChangeNetCounMonthView.setKaiFaQu(kaiFaQu);
						sumCounts = haiGangQuSum+shanHaiGuanSum+beiDaiHeSum+fuNingQuSum+qingLongXianSum+changLiXianSum+luLongXianSum+kaiFaQu;
						erpChangeNetCounMonthView.setSumCount(sumCounts);
					}else{
						erpChangeNetCounMonthView.setHaiGangQu(haiGangQuSum2);
						erpChangeNetCounMonthView.setShanHaiGuan(shanHaiGuanSum2);
						erpChangeNetCounMonthView.setBeiDaiHe(beiDaiHeSum2);
						erpChangeNetCounMonthView.setFuNingQu(fuNingQuSum2);
						erpChangeNetCounMonthView.setQingLongXian(qingLongXianSum2);
						erpChangeNetCounMonthView.setChangLiXian(changLiXianSum2);
						erpChangeNetCounMonthView.setLuLongXian(luLongXianSum2);
						erpChangeNetCounMonthView.setKaiFaQu(kaiFaQu2);
						sumCounts = haiGangQuSum+shanHaiGuanSum+beiDaiHeSum+fuNingQuSum+qingLongXianSum+changLiXianSum+luLongXianSum+kaiFaQu;
						erpChangeNetCounMonthView.setSumCount(sumCounts);
					}


				}
				erpChangeNetCounMonthView.setCountType("已统计");
			}
		}
		Collections.sort(erpChangeNetCounMonthList, new Comparator<ErpChangeNetCounMonth>() {
         public int compare(ErpChangeNetCounMonth o2, ErpChangeNetCounMonth o1) {
			 int sort = o2.getSumCount() - o1.getSumCount();
				             return sort;
		 }
     });


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
		ErpChangeNetCounMonthView erpChangeNetCounMonth1 = new ErpChangeNetCounMonthView();
		ErpChangeNetCounMonthView erpChangeNetCounMonth2 = new ErpChangeNetCounMonthView();
		for(ErpChangeNetCounMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList){
			if(erpChangeNetCounMonthView.getChangeNetType().equals("转出")){
				//海港区合计
				haiGangQuAllSum = haiGangQuAllSum+erpChangeNetCounMonthView.getHaiGangQu();
				//山海关合计
				shanHaiGuanAllSum = shanHaiGuanAllSum+erpChangeNetCounMonthView.getShanHaiGuan();
				//北戴河合计
				beiDaiHeAllSum = beiDaiHeAllSum+erpChangeNetCounMonthView.getBeiDaiHe();
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
				sumCount = erpChangeNetCounMonthView.getHaiGangQu()+erpChangeNetCounMonthView.getShanHaiGuan()+erpChangeNetCounMonthView.getBeiDaiHe()+erpChangeNetCounMonthView.getFuNingQu()
						+erpChangeNetCounMonthView.getQingLongXian()+erpChangeNetCounMonthView.getChangLiXian()+erpChangeNetCounMonthView.getLuLongXian()+erpChangeNetCounMonthView.getKaiFaQu();
				erpChangeNetCounMonth1.setChangeNetType("转出");
				erpChangeNetCounMonth1.setCountData(data);
				erpChangeNetCounMonthView.setSumCount(sumCount);
				allSumCount = allSumCount+sumCount;
			}else{
				haiGangQuAllSum2 = haiGangQuAllSum2+erpChangeNetCounMonthView.getHaiGangQu();
				shanHaiGuanAllSum2 = shanHaiGuanAllSum2+erpChangeNetCounMonthView.getShanHaiGuan();
				beiDaiHeAllSum2 = beiDaiHeAllSum2+erpChangeNetCounMonthView.getBeiDaiHe();
				fuNingQuAllSum2 = fuNingQuAllSum2+erpChangeNetCounMonthView.getFuNingQu();
				kaiFaQuAllSum2 = kaiFaQuAllSum2+erpChangeNetCounMonthView.getKaiFaQu();
				qingLongXianAllSum2 = qingLongXianAllSum2+erpChangeNetCounMonthView.getQingLongXian();
				changLiXianAllSum2 = changLiXianAllSum2+erpChangeNetCounMonthView.getChangLiXian();
				luLongXianAllSum2 = luLongXianAllSum2+erpChangeNetCounMonthView.getLuLongXian();
				sumCount2 = erpChangeNetCounMonthView.getHaiGangQu()+erpChangeNetCounMonthView.getShanHaiGuan()+erpChangeNetCounMonthView.getBeiDaiHe()+erpChangeNetCounMonthView.getFuNingQu()
						+erpChangeNetCounMonthView.getQingLongXian()+erpChangeNetCounMonthView.getChangLiXian()+erpChangeNetCounMonthView.getLuLongXian()+erpChangeNetCounMonthView.getKaiFaQu();
				erpChangeNetCounMonth2.setChangeNetType("转入");
				erpChangeNetCounMonth2.setCountData(data);
				erpChangeNetCounMonthView.setSumCount(sumCount2);
				allSumCount2 = allSumCount2+sumCount2;
			}

		}
		//转出合计行
		erpChangeNetCounMonth1.setNowPt("合计");
		erpChangeNetCounMonth1.setHaiGangQu(haiGangQuAllSum);
		erpChangeNetCounMonth1.setShanHaiGuan(shanHaiGuanAllSum);
		erpChangeNetCounMonth1.setKaiFaQu(kaiFaQuAllSum);
		erpChangeNetCounMonth1.setBeiDaiHe(beiDaiHeAllSum);
		erpChangeNetCounMonth1.setFuNingQu(fuNingQuAllSum);
		erpChangeNetCounMonth1.setQingLongXian(qingLongXianAllSum);
		erpChangeNetCounMonth1.setChangLiXian(changLiXianAllSum);
		erpChangeNetCounMonth1.setLuLongXian(luLongXianAllSum);
		erpChangeNetCounMonth1.setCountMonth(month);
		erpChangeNetCounMonth1.setSumCount(allSumCount);
		erpChangeNetCounMonth1.setCountData(data);
		//erpChangeNetCounMonthList.add(erpChangeNetCounMonth1);
		//转入合计行
		erpChangeNetCounMonth2.setNowPt("合计");
		erpChangeNetCounMonth2.setHaiGangQu(haiGangQuAllSum2);
		erpChangeNetCounMonth2.setShanHaiGuan(shanHaiGuanAllSum2);
		erpChangeNetCounMonth2.setKaiFaQu(kaiFaQuAllSum2);
		erpChangeNetCounMonth2.setBeiDaiHe(beiDaiHeAllSum2);
		erpChangeNetCounMonth2.setFuNingQu(fuNingQuAllSum2);
		erpChangeNetCounMonth2.setQingLongXian(qingLongXianAllSum2);
		erpChangeNetCounMonth2.setChangLiXian(changLiXianAllSum2);
		erpChangeNetCounMonth2.setLuLongXian(luLongXianAllSum2);
		erpChangeNetCounMonth2.setCountMonth(month);
		erpChangeNetCounMonth2.setSumCount(allSumCount2);
		erpChangeNetCounMonth2.setCountData(data);
		//erpChangeNetCounMonthList.add(erpChangeNetCounMonth2);

		//按月数查找列表
		List<ErpChangeNetCounMonthView> newErpChangeNetCounMonthList= new ArrayList<>();
		if(query.getQueryObj().getCountData()!=null && !query.getQueryObj().getCountData().equals("")){
			String selectMonth = query.getQueryObj().getCountData();
//			String date[] = selectMonth.split("-");
//			int i = Integer.valueOf(date[1]);

			for(ErpChangeNetCounMonthView erpChangeNetCounMonthView:erpChangeNetCounMonthList){
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
		}else {
			query.setList(erpChangeNetCounMonthList);
			return erpChangeNetCounMonthList;
		}
	}

	public void deleteRepeat() {
		mapper.deleteRepeat();
	}
}
