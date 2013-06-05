import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//从日志从筛选出没有提过单的账号的操作记录
public class zhangt2 {
	public static ArrayList dbLog = new ArrayList();
	public static ArrayList listLog = new ArrayList();
	
	public static void init ()
	{
		long start = System.currentTimeMillis();
		try {
			FileReader in = new FileReader("E:/新库中记录.txt");
			BufferedReader re = new BufferedReader(in);
//			FileReader in2 = new FileReader("E:/老库数据合并.txt");
//			BufferedReader re2 = new BufferedReader(in2);
			String s = "";
			while ((s = re.readLine()) != null) {
					dbLog.add(s);
			}
			System.out.println("dbLog.size()="+dbLog.size());
//			while((s= re2.readLine()) != null){
//				dbLog.add(s);
//			}
			
			FileReader in444 = new FileReader("E:/____4wNotSure结果.txt");
			BufferedReader re444 = new BufferedReader(in444);
			String s2 = "";
			while((s2= re444.readLine()) != null){
				listLog.add(s2);
			}
			System.out.println("listLog.size()="+listLog.size());
			
			re.close();
//			re2.close();
			re444.close();
			System.out.println("init over!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		System.out.println("init cost time ="+(System.currentTimeMillis()-start)/1000);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		init();
		FileWriter fw1 = new FileWriter("E:/结果3原始.txt");		//操作前没有提交过单据
		FileWriter fw2 = new FileWriter("E:/4wNotSure2-514结果.txt");	//操作前提交过单据
		
		String s = "";
		long start = System.currentTimeMillis();
		for(int i=0;i<listLog.size();i++){
			s = (String) listLog.get(i);		//s为操作记录
			String cn = s.substring(s.indexOf("cn=")+3).trim();
			Long minIssueSubmit = FileUtil.getMinIssueSubmit3(cn);	//查所cn最晚提交被盗单据的时间
			if(minIssueSubmit == null){
				System.out.println("continue 没提过单子");
				continue;
			}else{
				FileUtil.judgeGuilty3(minIssueSubmit,fw1,fw2,s);
			}
		}
		fw1.close();
		fw2.close();
	}
	
}
