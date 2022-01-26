package test.code;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.util.StringHelper;

import java.io.File;


public class ErpMngWebCodeGenIn {
	
	public static void main(String[] args) throws Exception {
		String templateDir = "template";
		//TODO:表名
		String tableName = "erp_out_form";
		//TODO:项目文件夹
		String projectDir = "erp-mng-web";
		//TODO:mapper文件夹
		String mapperDir = "erp";
		//TODO:模块包路径
		String modelpackage = "com.edgedo.sys";
		//TODO:前端的包路径
		String packageName = "com.edgedo.sys.erpoutform";
		//TODO:访问url前缀
		String urlprefix = "/erp/erpoutform/";
		//TODO:生成代码连接数据库
		String configFile = "config/jdbc-config.xml";



		String className =StringHelper.underlineToCamel(tableName);
		GeneratorProperties.PROPERTIES_FILE_NAMES[0] = configFile;
		GeneratorProperties.reload();
		GeneratorProperties.setProperty("tableName", tableName);
		GeneratorProperties.setProperty("basepackage", packageName);
		GeneratorProperties.setProperty("modelpackage", modelpackage);
		GeneratorProperties.setProperty("projectDir", projectDir);
		GeneratorProperties.setProperty("mapperDir", mapperDir);
		GeneratorProperties.setProperty("camelClassName", className);
		//url前缀
		GeneratorProperties.setProperty("urlprefix", urlprefix + className);
		GeneratorProperties.setProperty("templateDir", templateDir);


		String classLowerCase = className.toLowerCase();
		GeneratorProperties.setProperty("classLowerCase", classLowerCase);
		

		String path = ErpMngWebCodeGenIn.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
    	File jarFile = new File(path);
    	File parent = jarFile.getParentFile().getParentFile().getParentFile();//tdp-plant/tdp-sys-service/code-gen
    	String outRoot =  parent.getAbsolutePath();
    	GeneratorProperties.setProperty("outRoot",outRoot);
    	GeneratorProperties.setProperty("java_typemapping.java.sql.Timestamp","java.util.Date");
    	GeneratorProperties.setProperty("java_typemapping.java.sql.Date","java.util.Date");
    	GeneratorProperties.setProperty("java_typemapping.java.sql.Time","java.util.Date");
    	GeneratorProperties.setProperty("java_typemapping.java.lang.Byte","Integer");
    	GeneratorProperties.setProperty("java_typemapping.java.lang.Short","Integer");
    	GeneratorProperties.setProperty("java_typemapping.java.math.BigDecimal","java.math.BigDecimal");
    	GeneratorProperties.setProperty("java_typemapping.java.sql.Clob","String");
		GeneratorFacade g = new GeneratorFacade();
		g.generateByTable(tableName);	//通过数据库表生成文件,template为模板的根目录  表名+模板名称
		
	}
	
}

