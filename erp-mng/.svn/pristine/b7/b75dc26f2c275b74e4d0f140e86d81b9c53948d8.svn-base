package com.edgedo.sys.service;
		
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpUserWorkLog;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.ErpWorkTaskOrderUser;
import com.edgedo.sys.mapper.ErpUserWorkLogMapper;
import com.edgedo.sys.mapper.ErpWorkTaskOrderMapper;
import com.edgedo.sys.mapper.ErpWorkTaskOrderUserMapper;
import com.edgedo.sys.queryvo.ErpUserWorkLogQuery;
import com.edgedo.sys.queryvo.ErpUserWorkLogView;
import com.edgedo.sys.queryvo.ErpWorkTaskView;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Length;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpUserWorkLogService {
	
	
	@Autowired
	private ErpUserWorkLogMapper mapper;

	@Autowired
	private ErpWorkTaskOrderUserMapper erpWorkTaskOrderUserMapper;
	@Autowired
	private ErpWorkTaskOrderMapper erpWorkTaskOrderMapper;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = false)
	public List<ErpUserWorkLogView> listPage(ErpUserWorkLogQuery query){
//		//1.每一次触发查询自动更新员工日志
//		//2.依据员工工单表员工所属的任务id ，查询进行那些工作-->对应服务内容
//		List<ErpWorkTaskOrderUser> listWorkUser = erpWorkTaskOrderUserMapper.select();
//		//3.由于存在一个员工对应多个任务工单表  ->list<员工表>
//		for(ErpWorkTaskOrderUser erpWorkTaskOrderUser : listWorkUser){
//			ErpUserWorkLog erpUserWorkLog = new ErpUserWorkLog();
//			//主键
//			erpUserWorkLog.setId(Guid.guid());
//			//创建时间
//			erpUserWorkLog.setCreateTime(new Date());
//			//日期
//			Date totay = new Date();//今天
//			erpUserWorkLog.setWorkDate(totay);
//			boolean flag2 = isNotToday(totay,erpWorkTaskOrderUser.getCreateTime());
//			if(!flag2){
//				continue;
//			}
//			//星期
//			String week = getWeek(totay);
//			erpUserWorkLog.setWeek(week);//星期
//			//数据状态
//			erpUserWorkLog.setDataState("1");
//			//4.判断在员工日志表中是否已经存在一个同样的员工
//			ErpUserWorkLog erpUserWorkLogOld = mapper.selectByWorkUserId(erpWorkTaskOrderUser.getOwnerUserId(),getDateStr(totay));
//			//根据工单id查询
//			ErpWorkTaskOrder erpWorkTaskOrder = erpWorkTaskOrderMapper.selectByIdsOnWorkUserLog(erpWorkTaskOrderUser.getOwnerWorkTaskOrderId());
//			boolean flag = isCurrTimeAndWorkTime(totay,erpWorkTaskOrder.getConfirmTime());
//			if(erpUserWorkLogOld==null && flag){//如果不存在
//				//创建员工日志
//				erpUserWorkLog.setUserId(erpWorkTaskOrderUser.getOwnerUserId());//所属员工id
//				erpUserWorkLog.setUserName(erpWorkTaskOrderUser.getOwnerUserName());//员工姓名
//				erpUserWorkLog.setWorkText(erpWorkTaskOrder.getOwnerWorkTaskDesc());//任务描述==服务内容
//				if(erpWorkTaskOrder.getIsOverWork().equals("1")){
//					//5.再判断是否存在加班
//					erpUserWorkLog.setIsOverWork("1");
//					erpUserWorkLog.setOverWorkTime(erpWorkTaskOrder.getOverWorkTime());//加班时长
//					erpUserWorkLog.setOverWorkDesc(erpWorkTaskOrder.getOverWorkDesc());//加班描述
//					mapper.insert(erpUserWorkLog);//上升数据库
//				}else {
//					erpUserWorkLog.setIsOverWork("0");//否
//					mapper.insert(erpUserWorkLog);//上升数据库
//				}
//			}else if(erpUserWorkLogOld!=null && flag){//存在 在当天  说明干了不止一个任务
//				//更新员工日志 查到的 erpUserWorkLogOld
//				//判断是不是在同一天  不再同一天 新插入
//				boolean flag1 = isNotToday(totay,erpUserWorkLogOld.getWorkDate());
//				if(flag1){
//					//6.将服务内容逗号拼接，存在加班 加上加班时间 拼接加班描述
//					String oldWorkText = erpUserWorkLogOld.getWorkText();
//					String oldWorkTextOwner = erpWorkTaskOrder.getOwnerWorkTaskDesc();
//					String[] oldWorkTextOwnerArr = oldWorkText.split(",");
//					int index = 0;//索引标识
//					//挨个遍历  防止出现重复工作记录
//					for(String str : oldWorkTextOwnerArr){
//						//有相同的证明之前存入该任务
//						if(str.equals(oldWorkTextOwner)){
//							index++;
//						}
//					}
//					if(index!=0){
//						//不变
//						erpUserWorkLogOld.setWorkText(oldWorkText);//服务内容   1
//					}else {
//						//更新
//						String newWorkText = oldWorkText+","+erpWorkTaskOrder.getOwnerWorkTaskDesc();
//						erpUserWorkLogOld.setWorkText(newWorkText);//服务内容   1
//					}
//
//					//继续判断第二份工作有没有加班
//					if(erpWorkTaskOrder.getIsOverWork().equals("1")){
//						erpUserWorkLogOld.setIsOverWork("1");
//						//获取加班描述
//						String oldOverWorkDesc = erpUserWorkLogOld.getOverWorkDesc();
//						String orderOverWorkDesc = erpWorkTaskOrder.getOverWorkDesc();
//						int indexOverWork = 0;
//						//避免报空指针异常
//						if(oldOverWorkDesc==null){
//							String newOverWorkTime = orderOverWorkDesc;
//							erpUserWorkLogOld.setOverWorkDesc(newOverWorkTime);//更新加班描述   3
//						}else {
//							//挨个遍历  防止出现重复加班记录
//							String[] oldOverWorkDescArr =  oldOverWorkDesc.split(",");//员工日志存好的加班记录
//							String[] orderOverWorkDescArr = orderOverWorkDesc.split(",");//工单的加班记录
//							for(String string : oldOverWorkDescArr){
//								if(string.equals(orderOverWorkDescArr[orderOverWorkDescArr.length-1])){
//									indexOverWork++;
//								}
//							}
//							if(indexOverWork!=0){//不变
//								erpUserWorkLogOld.setOverWorkDesc(oldOverWorkDesc);//更新加班描述   3
//							}else {
//								String newOverWorkTime = oldOverWorkDesc+","+orderOverWorkDescArr[orderOverWorkDescArr.length-1];
//								erpUserWorkLogOld.setOverWorkDesc(newOverWorkTime);//更新加班描述   3
//							}
//						}
//
//						//获取加班时长
//						Integer oldWorkTime = erpUserWorkLogOld.getOverWorkTime();
//						Integer orderWorkTime = erpWorkTaskOrder.getOverWorkTime();
//						if(oldWorkTime==null){
//							Integer newWorkTime = orderWorkTime;
//							erpUserWorkLogOld.setOverWorkTime(newWorkTime);//更新加班时长  2
//						}else {
//							if(index!=0 && indexOverWork!=0){//不变
//								erpUserWorkLogOld.setOverWorkTime(oldWorkTime);//更新加班时长  2
//							}else {
//								erpUserWorkLogOld.setOverWorkTime(orderWorkTime);//更新加班时长  2
//							}
//						}
//						mapper.updateByCurrState(erpUserWorkLogOld);//上升数据库
//					}else {
//						mapper.updateByCurrOneState(erpUserWorkLogOld);//上升数据库
//					}
//				}else {
//					//创建员工日志
//					erpUserWorkLog.setUserId(erpWorkTaskOrderUser.getOwnerUserId());//所属员工id
//					erpUserWorkLog.setUserName(erpWorkTaskOrderUser.getOwnerUserName());//员工姓名
//					erpUserWorkLog.setWorkText(erpWorkTaskOrder.getOwnerWorkTaskDesc());//任务描述==服务内容
//					if(erpWorkTaskOrder.getIsOverWork().equals("1")){
//						//5.再判断是否存在加班
//						erpUserWorkLog.setIsOverWork("1");
//						erpUserWorkLog.setOverWorkTime(erpWorkTaskOrder.getOverWorkTime());//加班时长
//						erpUserWorkLog.setOverWorkDesc(erpWorkTaskOrder.getOverWorkDesc());//加班描述
//						mapper.insert(erpUserWorkLog);//上升数据库
//					}else {
//						erpUserWorkLog.setIsOverWork("0");//否
//						mapper.insert(erpUserWorkLog);//上升数据库
//					}
//				}
//
//			}
//		}
		List list = mapper.selectByList(query);
		query.setList(list);
		return list;
	}


	/**
	 * 定时任务  查询员工表  对应的工单  每天晚上查询 插入更新
	 */
	@Scheduled(cron = "0 0 22 * * ? ")//3秒钟
	//@Scheduled(cron = "*/3 * * * * ? ")//3秒钟
	public void timingTasks(){
		//1.根据创建时间  查询  今天 获取所有的员工的工作的信息
			//1)格式化时间  yyyy-MM-dd 查询list集合所有的员工和工单 ,获取到任务标题信息插入
		String currDate = getDateStr(new Date());
		List<ErpWorkTaskOrderUser> list = erpWorkTaskOrderUserMapper.selectCreateTime(currDate);
		for(ErpWorkTaskOrderUser erpWorkTaskOrderUser : list){
			//2)判断他如果当天的时间的员工干了多个任务 查询是否已经存在当天的一个相同员工，没有插入  ，又更新
			int num = mapper.selectUserOne(currDate,erpWorkTaskOrderUser.getOwnerUserId());
			//根据工单id查询
			ErpWorkTaskOrder erpWorkTaskOrder = erpWorkTaskOrderMapper.selectByIdsOnWorkUserLog(erpWorkTaskOrderUser.getOwnerWorkTaskOrderId());
			if(num==0){//不存在
				//根据工单表查询 工单信息
				ErpUserWorkLog erpUserWorkLog = new ErpUserWorkLog();
				//创建员工日志
				erpUserWorkLog.setUserId(erpWorkTaskOrderUser.getOwnerUserId());//所属员工id
				erpUserWorkLog.setUserName(erpWorkTaskOrderUser.getOwnerUserName());//员工姓名
				erpUserWorkLog.setWorkText(erpWorkTaskOrder.getOwnerWorkTaskDesc());//任务描述==服务内容
				if(erpWorkTaskOrder.getIsOverWork().equals("1")){
					//5.再判断是否存在加班
					erpUserWorkLog.setIsOverWork("1");
					erpUserWorkLog.setOverWorkTime(erpWorkTaskOrder.getOverWorkTime());//加班时长
					erpUserWorkLog.setOverWorkDesc(erpWorkTaskOrder.getOverWorkDesc());//加班描述
				}else {
					erpUserWorkLog.setIsOverWork("0");//否
				}
				erpUserWorkLog.setId(Guid.guid());//主键
				erpUserWorkLog.setCreateTime(new Date());//创建时间
				erpUserWorkLog.setWeek(getWeek(new Date()));//星期
				erpUserWorkLog.setWorkDate(new Date());//日期
				erpUserWorkLog.setDataState("1");
				mapper.insert(erpUserWorkLog);//上升数据库
			}else {//存在
				//根据工单表查询 工单信息
				ErpUserWorkLog erpUserWorkLog = mapper.selectUserOneInfo(currDate,erpWorkTaskOrderUser.getOwnerUserId());
				//服务内容
				String workTextOld = erpUserWorkLog.getWorkText()+","+erpWorkTaskOrder.getOwnerWorkTaskDesc();
				erpUserWorkLog.setWorkText(workTextOld);//任务描述==服务内容
				if(erpWorkTaskOrder.getIsOverWork().equals("1")){
					//5.再判断是否存在加班
					erpUserWorkLog.setIsOverWork("1");
					erpUserWorkLog.setOverWorkTime(erpWorkTaskOrder.getOverWorkTime()+erpUserWorkLog.getOverWorkTime());//加班时长
					erpUserWorkLog.setOverWorkDesc(erpWorkTaskOrder.getOverWorkDesc()+","+erpUserWorkLog.getOverWorkDesc());//加班描述
				}
				mapper.updateByInfo(erpUserWorkLog);
			}
		}
		//2.前一天的工作信息
		String betweenTime = getDate(new Date());
		List<ErpWorkTaskOrderUser> listBetween = erpWorkTaskOrderUserMapper.selectCreateTime(betweenTime);
		for(ErpWorkTaskOrderUser erpWorkTaskOrderUser : listBetween){
			//2)判断他如果当天的时间的员工干了多个任务 查询是否已经存在当天的一个相同员工，没有插入  ，又更新
			int num = mapper.selectUserOne(currDate,erpWorkTaskOrderUser.getOwnerUserId());
			//根据工单id查询
			ErpWorkTaskOrder erpWorkTaskOrder = erpWorkTaskOrderMapper.selectByIdsOnWorkUserLog(erpWorkTaskOrderUser.getOwnerWorkTaskOrderId());
			if(num!=0){//不存在
				//根据工单表查询 工单信息
				ErpUserWorkLog erpUserWorkLog = new ErpUserWorkLog();
				//创建员工日志
				erpUserWorkLog.setUserId(erpWorkTaskOrderUser.getOwnerUserId());//所属员工id
				erpUserWorkLog.setUserName(erpWorkTaskOrderUser.getOwnerUserName());//员工姓名
				erpUserWorkLog.setWorkText(erpWorkTaskOrder.getOwnerWorkTaskDesc());//任务描述==服务内容
				if(erpWorkTaskOrder.getIsOverWork().equals("1")){
					//5.再判断是否存在加班
					erpUserWorkLog.setIsOverWork("1");
					erpUserWorkLog.setOverWorkTime(erpWorkTaskOrder.getOverWorkTime());//加班时长
					erpUserWorkLog.setOverWorkDesc(erpWorkTaskOrder.getOverWorkDesc());//加班描述
				}else {
					erpUserWorkLog.setIsOverWork("0");//否
				}
				erpUserWorkLog.setId(Guid.guid());//主键
				erpUserWorkLog.setCreateTime(new Date());//创建时间
				erpUserWorkLog.setWeek(getWeek(new Date()));//星期
				erpUserWorkLog.setWorkDate(new Date());//日期
				erpUserWorkLog.setDataState("1");
				mapper.insert(erpUserWorkLog);//上升数据库
			}else {//存在
				//根据工单表查询 工单信息
				ErpUserWorkLog erpUserWorkLog = mapper.selectUserOneInfo(currDate,erpWorkTaskOrderUser.getOwnerUserId());
				//服务内容
				//6.将服务内容逗号拼接，存在加班 加上加班时间 拼接加班描述
				String oldWorkText = erpUserWorkLog.getWorkText();
				String oldWorkTextOwner = erpWorkTaskOrder.getOwnerWorkTaskDesc();
				String[] oldWorkTextOwnerArr = oldWorkText.split(",");
				int index = 0;//索引标识
				//挨个遍历  防止出现重复工作记录
				for(String str : oldWorkTextOwnerArr){
					//有相同的证明之前存入该任务
					if(str.equals(oldWorkTextOwner)){
						index++;
					}
				}
				if(index!=0){
					//不变
					erpUserWorkLog.setWorkText(oldWorkText);//服务内容   1
				}else {
					//更新
					String newWorkText = oldWorkText+","+erpWorkTaskOrder.getOwnerWorkTaskDesc();
					erpUserWorkLog.setWorkText(newWorkText);//服务内容   1
				}
				//继续判断第二份工作有没有加班
				if(erpWorkTaskOrder.getIsOverWork().equals("1")) {
					erpUserWorkLog.setIsOverWork("1");
					//获取加班描述
					String oldOverWorkDesc = erpUserWorkLog.getOverWorkDesc();
					String orderOverWorkDesc = erpWorkTaskOrder.getOverWorkDesc();
					int indexOverWork = 0;
					//避免报空指针异常
					if (oldOverWorkDesc == null) {
						String newOverWorkTime = orderOverWorkDesc;
						erpUserWorkLog.setOverWorkDesc(newOverWorkTime);//更新加班描述   3
					} else {
						//挨个遍历  防止出现重复加班记录
						String[] oldOverWorkDescArr = oldOverWorkDesc.split(",");//员工日志存好的加班记录
						String[] orderOverWorkDescArr = orderOverWorkDesc.split(",");//工单的加班记录
						for (String string : oldOverWorkDescArr) {
							if (string.equals(orderOverWorkDescArr[orderOverWorkDescArr.length - 1])) {
								indexOverWork++;
							}
						}
						if (indexOverWork != 0) {//不变
							erpUserWorkLog.setOverWorkDesc(oldOverWorkDesc);//更新加班描述   3
						} else {
							String newOverWorkTime = oldOverWorkDesc + "," + orderOverWorkDescArr[orderOverWorkDescArr.length - 1];
							erpUserWorkLog.setOverWorkDesc(newOverWorkTime);//更新加班描述   3
						}
					}
					//获取加班时长
					Integer oldWorkTime = erpUserWorkLog.getOverWorkTime();
					Integer orderWorkTime = erpWorkTaskOrder.getOverWorkTime();
					if(oldWorkTime==null){
						Integer newWorkTime = orderWorkTime;
						erpUserWorkLog.setOverWorkTime(newWorkTime);//更新加班时长  2
					}else {
						if(index!=0 && indexOverWork!=0){//不变
							erpUserWorkLog.setOverWorkTime(oldWorkTime);//更新加班时长  2
						}else {
							erpUserWorkLog.setOverWorkTime(orderWorkTime);//更新加班时长  2
						}
					}
				}
				mapper.updateByInfo(erpUserWorkLog);
			}
		}
	}

	/**
	 * 获取前一天字符串时间
	 * @return
	 */
	public String getDate(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取当前字符串时间
	 * @return
	 */
	public String getDateStr(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currDateStr = simpleDateFormat.format(date);
		return currDateStr;
	}

	/**
	 * 计算星期
	 *
	 */
	public static String getWeek(Date date){
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 判断员工工作是否在当天日期
	 */
	public boolean isCurrTimeAndWorkTime(Date today,Date workTime){
		Calendar calendarStart = Calendar. getInstance();
		calendarStart.setTime(today);
		calendarStart.set(Calendar. HOUR_OF_DAY, 0);
		calendarStart.set(Calendar. MINUTE, 0);
		calendarStart.set(Calendar. SECOND, 0);
		calendarStart.set(Calendar. MILLISECOND, 0);
		Date startToday = new Date();
		startToday = calendarStart.getTime();//当天的零点

		Calendar calendarEnd = Calendar. getInstance();
		calendarEnd.setTime(today);
		calendarEnd.set(Calendar. HOUR_OF_DAY, 0);
		calendarEnd.set(Calendar. MINUTE, 0);
		calendarEnd.set(Calendar. SECOND, 0);
		calendarEnd.set(Calendar. MILLISECOND, 0);
		calendarEnd.add(Calendar. DAY_OF_MONTH, 1);
		Date endToday = new Date();
		endToday = calendarEnd.getTime();//当天最后一秒

		//判断是否工作时间在本天
		if(startToday.getTime()<=workTime.getTime() && workTime.getTime()<=endToday.getTime()){
			//证明在当天工作
			return true;
		}else {
			return false;//不在当天工作
		}
	}

	//判断是不是在第二天工作
	public boolean isNotToday(Date today,Date workTime){
		Calendar calendarStart = Calendar. getInstance();
		calendarStart.setTime(today);
		calendarStart.set(Calendar. HOUR_OF_DAY, 0);
		calendarStart.set(Calendar. MINUTE, 0);
		calendarStart.set(Calendar. SECOND, 0);
		calendarStart.set(Calendar. MILLISECOND, 0);
		Date startToday = new Date();
		startToday = calendarStart.getTime();//当天的零点

		Calendar calendarEnd = Calendar. getInstance();
		calendarEnd.setTime(today);
		calendarEnd.set(Calendar. HOUR_OF_DAY, 0);
		calendarEnd.set(Calendar. MINUTE, 0);
		calendarEnd.set(Calendar. SECOND, 0);
		calendarEnd.set(Calendar. MILLISECOND, 0);
		calendarEnd.add(Calendar. DAY_OF_MONTH, 1);
		Date endToday = new Date();
		endToday = calendarEnd.getTime();//当天最后一秒

		//判断是否工作时间在本天  日志里面的日期  比较   现在时间
		if(startToday.getTime()<=workTime.getTime() && workTime.getTime()<=endToday.getTime()){
			//证明在当天工作
			return true;
		}else {
			return false;//不在当天工作
		}
	}



	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpUserWorkLog voObj) {
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
	public String update(ErpUserWorkLog voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpUserWorkLog voObj) {
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

		return mapper.deleteByIdAll(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpUserWorkLog loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 单个删除
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}

    public void updateByShengHe(String id, String userId, String userName, Date date) {
		mapper.updateByShengHe(id,userId,userName,date);
    }

	public void updateByShengHeId(String id,Date date) {
		mapper.updateByShengHeId(id,date);
	}
}
