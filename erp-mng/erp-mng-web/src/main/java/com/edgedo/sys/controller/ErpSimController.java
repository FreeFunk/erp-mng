package com.edgedo.sys.controller;


import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.entity.ErpSimXufeiRec;
import com.edgedo.sys.entity.SysOrg;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.*;
import com.edgedo.sys.service.ErpSimService;
import com.edgedo.sys.service.ErpSimXufeiRecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
//import org.apache.poi.ss.usermodel.Cell;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.FaultAction;


@Api(tags = "ErpSim")
@Controller
@RequestMapping("/admin/erpSim")
public class ErpSimController extends BaseController{

	@Autowired
	private ErpSimService service;

	@Autowired
	private ErpSimXufeiRecService erpSimXufeiRecService;

	//换卡SIM卡号
	String simNum = "";
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpSim", notes = "分页查询ErpSim")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpSimQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getSelectTime()!=null && !query.getQueryObj().getSelectTime().equals("")){
			String dateQuJian = query.getQueryObj().getSelectTime();
			String dateQuJianStr = dateQuJian.replace(" ","");//去掉空格
			String[] arr = dateQuJianStr.split("-");//2019 08 16 2019 08 20
			String selectTime = arr[0]+"-"+arr[1]+"-"+arr[2];
			String newEndTime = arr[3]+"-"+arr[4]+"-"+arr[5];
			query.getQueryObj().setSelectTime(selectTime);//开始时间
			query.getQueryObj().setNewEndTime(newEndTime);//结束时间
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpSim", notes = "新增修改ErpSim")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpSim voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
			if(errMsg!=null && !errMsg.equals("")){
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}
			buildResponse(modelAndView);
		}else{
			errMsg = service.updateHtml(voObj);
			buildResponse(modelAndView);
		}
		return modelAndView;
	}


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpSim", notes = "根据ID批量删除ErpSim")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		//判断是否进行多个删除还是单个删除
		if(arr.length==1){//删除一个
			service.deleteByIdsOne(arr[0]);
		}else{
			List<String> list = new ArrayList<String>();
			for(String str : arr){
				list.add(str);
			}
			service.deleteByIds(list);
		}
		return buildResponse(modelAndView);
	}


	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载ErpSim", notes = "根据ID加载ErpSim")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 查出所有角色
	 * @param
	 * @return
	 */
	@RequestMapping("/listAllUserType")
	public ModelAndView listAllUserType(@ModelAttribute SysUserQuery query){
		ModelAndView modelAndView = new ModelAndView();
		List list=service.selectAllType(query);
		return buildResponse(modelAndView,list);
	}

	/**
	 * 跳转续费 进行插入
	 * @param
	 * @return
	 */
	@RequestMapping("/simXuFei")
	public ModelAndView simXuFei(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//id 查询sim卡记录
		ErpSim erpSim = service.selectByIds(ids);
		//判断是否已经使用
		if(erpSim.getSimState().equals("2")){
			//插入sim卡续费中
			ErpSimXufeiRecView erpSimXufeiRecView = erpSimXufeiRecService.insertByErpSimInfo(erpSim);
			simNum = erpSimXufeiRecView.getSimNum();
			ErpSimXufeiRecQuery erpSimXufeiRecQuery = new ErpSimXufeiRecQuery();
			erpSimXufeiRecQuery.setQueryObj(erpSimXufeiRecView);
			return buildResponse(modelAndView,erpSimXufeiRecQuery);
		}else {
			modelAndView.addObject("success" ,false );
			modelAndView.addObject("code","0");
			return modelAndView;
		}
	}

	/**
	 * 获取续费跳转的SIM卡号参数
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadSimNum",method = RequestMethod.POST)
	public ModelAndView loadSimNum(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//进行渲染
		ErpSimXufeiRecView erpSimXufeiRecView = new ErpSimXufeiRecView();
		erpSimXufeiRecView.setSimNum(simNum);
		simNum = "";
		return  buildResponse(modelAndView,erpSimXufeiRecView);

	}

	/**
	 * 功能描述: <br>
	 * 〈根据sim卡号查询SIM卡〉
	 * @Author: ZhaoSiDa
	 * @Date: 2020/4/1 0001 下午 4:45
	 */
	@RequestMapping(value = "/loadBySimNum",method = RequestMethod.POST)
	public ModelAndView loadBySimNum(String simNum){
		ModelAndView modelAndView = new ModelAndView();
		ErpSim erpSim = service.selectBySimCode(simNum);
		return  buildResponse(modelAndView,erpSim);

	}

	/**
	 * 续费
	 * @param erpSimXufeiRec
	 * @return
	 */
	@RequestMapping(value = "/newSaveSimXuFei",method = RequestMethod.POST)
	public ModelAndView newSaveSimXuFei(ErpSimXufeiRec erpSimXufeiRec){
		ModelAndView modelAndView = new ModelAndView();
		//进行渲染
		erpSimXufeiRecService.insertNew(erpSimXufeiRec);
		return  buildResponse(modelAndView);

	}

	/**
	 * 注销
	 * @param erpSim
	 * @return
	 */
	@RequestMapping(value = "/zhuXiao",method = RequestMethod.POST)
	public ModelAndView zhuXiao(ErpSim erpSim){
		ModelAndView modelAndView = new ModelAndView();
		service.updateZhuXiao(erpSim);
		return  buildResponse(modelAndView);

	}

	/**
	 * 导入Sim卡信息
	 * @param excel
	 * @return
	 */
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/importSimka",method = RequestMethod.POST)
	public ModelAndView importSimka(@RequestParam("file") MultipartFile excel){
		ModelAndView modelAndView = new ModelAndView();

		String fileName = excel.getOriginalFilename();
		String fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);
		if(!fileExtend.equals("xls")){
			return buildResponse(modelAndView,"请上传.xls格式的文件");
		}
		InputStream inputStream =null;
		Workbook book = null;
		Sheet carSheet = null;
		try {
			inputStream = excel.getInputStream();
			if(inputStream.available()==0){
				return buildResponse(modelAndView,"导入文件中没有任何内容");
			}
			book = Workbook.getWorkbook(inputStream);
			carSheet = book.getSheet(0);
			int rows = carSheet.getRows();
			int columns = carSheet.getColumns();
			if(rows<2){
				return buildResponse(modelAndView,"表中无数据");
			}
			List<String> simKaEg = new ArrayList<String>();
			Cell[] headers = carSheet.getRow(0);
			for(int i=0;i<headers.length;i++){
				Cell cell = headers[i];
				String cellValue = cell.getContents();
				if(cellValue.equals("")){
					return buildResponse(modelAndView,"表中数据格式不对");
				}
				if(cellValue==null && cellValue.length()==0){
					simKaEg.add("none");
					continue;
				}
				simKaEg.add(fenxiHeader(cellValue.replaceAll("\n","").replaceAll(" ","")));
			}
			System.out.println(simKaEg);
			List<Map<String,String>> simKaList = new ArrayList<Map<String,String>>();
			List<String> simNumList = new ArrayList<>();
			for(int i=1;i<rows;i++){
				Map<String,String> simKaMap= new HashMap<String,String>();
				Cell[] properties = carSheet.getRow(i);
				for(int j=0;j<properties.length;j++){
					Cell cell = properties[j];
					//import org.apache.poi.ss.usermodel.Cell;
					String key = simKaEg.get(j);
					String value = cell.getContents();
					if(value.indexOf("/")!=-1 && value.length()>8){//2019/12/20
						simKaMap.put(key,cell.getContents().replaceAll("\n","").replaceAll(" ",""));
					} else if(value.indexOf("/")!=-1 && value.length()<=8){//12/12/21
						DateCell dc = (DateCell)cell;
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
						String strDate = simpleDateFormat.format(dc.getDate());
						simKaMap.put(key,strDate.replaceAll("\n","").replaceAll(" ",""));
					}else if(value.indexOf("/")==-1){//245465
						simKaMap.put(key,cell.getContents().replaceAll("\n","").replaceAll(" ",""));
					}
				}
				//卡号
				String simNum = simKaMap.get("simNum");
				if(simNum!=null && !simNum.equals("")){
					simNumList.add(simNum);
					//生成Sim卡集合
					simKaList.add(simKaMap);
				}
			}
			//判断卡号是否有重复
//			String errmsg = service.isRepeatSimNum(simNumList);
//			if(errmsg!=null && !errmsg.equals("")){//重复
//				return buildErrorResponse(modelAndView,errmsg+"重复！");
//			}
			service.updateAndinsert(simKaList);
//			service.updateSimKa(simKaList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return buildResponse(modelAndView,"导入成功");
	}

	@ResponseBody
	@RequestMapping(value = "/exportSimka",method = RequestMethod.POST)
	public ModelAndView exportSimka() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List> map = new HashMap<>();
		//1.查询所有车辆信息
		List<ErpSim> list = service.selectAllSim();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		for(ErpSim erpSim : list){
//			//开卡时间
//			Date dateStartTime =  erpSim.getSimStartTime();
//			//到期时间
//			Date dateEndTime = erpSim.getEndTime();
//			try {
//				// 注意格式需要与上面一致，不然会出现异常
//				erpSim.setSimStartTime(simpleDateFormat.parse(simpleDateFormat.format(dateStartTime)));
//				erpSim.setEndTime(simpleDateFormat.parse(simpleDateFormat.format(dateEndTime)));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}

	@ResponseBody
	@RequestMapping(value = "/exportSelectSimka",method = RequestMethod.POST)
	public ModelAndView exportSelectSimka(String ids) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List> map = new HashMap<>();
		String[] arr = ids.split(",");
		List<String> idList = new ArrayList<String>();
		//判断是否进行多个删除还是单个删除
		if(arr.length>0) {//删除一个
			for (String str : arr) {
				idList.add(str);
			}
			List<ErpSim> list = service.selectSelectAllSim(idList);
			map.put("list", list);
			return buildResponse(modelAndView, map);
		}else{
			idList.add("未选中");
			map.put("error", idList);
			return buildResponse(modelAndView, map);
		}
	}

	/**
	 * exce头部区域
	 * @param cellValue
	 * @return
	 */
	private String fenxiHeader(String cellValue ){
		if(cellValue.indexOf("sim卡号")>=0)return "simNum";
		else if(cellValue.indexOf("设备号")>=0)return "sheBeiNum";
		else if(cellValue.indexOf("业户名称")>=0)return "yehuName";
		else if(cellValue.indexOf("车牌号")>=0)return "carPlateNum";
		else if(cellValue.indexOf("车辆列表")>=0)return "carLIeBiao";
		else if(cellValue.indexOf("供应商名")>=0)return "supplierName";
		else if(cellValue.indexOf("到期时间")>=0)return "endTime";
		else if(cellValue.indexOf("缴费时间")>=0)return "jiaoFeiTime";
		else if(cellValue.indexOf("套餐名")>=0)return "setMealName";
		else if(cellValue.indexOf("sim卡状态")>=0)return "simState";
		else if(cellValue.indexOf("注销原因")>=0)return "outReason";
		else if(cellValue.indexOf("开卡时间")>=0)return "simStartTime";
		else if(cellValue.indexOf("沉默期(月)")>=0)return "simReticentNum";
		else if(cellValue.indexOf("卡的套餐描述")>=0)return "simTaocanDesc";
		else if(cellValue.indexOf("付费类型")>=0)return "payType";
		else if(cellValue.indexOf("金额")>=0)return "payMoney";
		else if(cellValue.indexOf("自动激活时间")>=0)return "activeTime";
		else if(cellValue.indexOf("初次使用时间")>=0)return "firstUseTime";
		else if(cellValue.indexOf("卡占用人")>=0)return "simUserName";
		else if(cellValue.indexOf("续费时间")>=0)return"xufeiTime";
		return "none;";
	}



}
