import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUtil {

	public static String singleLog(List<String> list) {

		if (list == null) {
			return null;
		}

		if (list.size() == 1) {
			list.get(0);
		}

		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			map.put(getTime(list.get(i)), list.get(i));
		}
		List<Long> listTime = new ArrayList<Long>(map.keySet());
		if (listTime != null) {
			Long minTime = listTime.get(0);
			for (int j = 0; j < listTime.size(); j++) {
				if (listTime.get(j) == null) {
					System.out.println("-------------");
					continue;
				}
				if (listTime.get(j) < minTime) {
					minTime = listTime.get(j);
				}
			}
			// System.out.println("返回的最小时间日志："+String.valueOf(map.get(minTime)));
			return String.valueOf(map.get(minTime));
		}
		return null;
	}

	public static Long getTime(String sDB) {
		String issueTimeString = "";
		if (sDB.indexOf("2013") != -1) {
			issueTimeString = sDB.substring(sDB.indexOf("2013"),
					sDB.indexOf("2013") + 14);
		} else {
			return null;
		}
		String[] part = issueTimeString.split(" ");
		if (part.length != 2) {
			return null;
		}
		String part1 = part[0];
		String part2 = part[1];
		if (part1.length() == 9) {
			part1 = part1.replaceAll("-", "");
			part1 = part1.substring(0, 4) + "0" + part1.substring(4, 7);
		} else {
			part1 = part1.replaceAll("-", "0");
		}
		if (part2.indexOf(":") == 2) {
			part2 = part2.replaceAll(":", "") + "00";
			if (part2.length() != 6) {
				part2 = part2 + "0";
			}
		} else {
			part2 = "0" + part2.replaceAll(":", "").trim() + "00";
		}

		String issueTimeStringFinal = part1 + part2;
		long issueTime = Long.parseLong(issueTimeStringFinal); // 提单时间
		return issueTime;

	}

	public static void outputLogInfo(Set cnSet, Set gmSet, Set ipSet)
			throws IOException {
		FileWriter w = new FileWriter("E:/cn.txt");
		w.write("账号数量：" + cnSet.size() + "\n");
		List list = new ArrayList(cnSet);
		for (int i = 0; i < list.size(); i++) {
			w.write(String.valueOf(list.get(i)));
		}
		w.flush();
		w.close();

		FileWriter w1 = new FileWriter("E:/gm.txt");
		w1.write("账号数量：" + cnSet.size() + "\n");
		List list1 = new ArrayList(cnSet);
		for (int i = 0; i < list1.size(); i++) {
			w1.write(String.valueOf(list1.get(i)));
		}
		w1.flush();
		w1.close();

		FileWriter w2 = new FileWriter("E:/ip.txt");
		w2.write("账号数量：" + cnSet.size() + "\n");
		List list2 = new ArrayList(cnSet);
		for (int i = 0; i < list2.size(); i++) {
			w2.write(String.valueOf(list2.get(i)));
		}
		w2.flush();
		w2.close();

	}

	
	
		//查询DB记录，查出一段时间内提交单据的最早时间
		public static Long getMinIssueSubmit2(String cn) throws IOException {
			Long result = null;

			try {
					
				List timeList = new ArrayList();
				String s = "";
//				for  (int i=0;i<zhangt.dbMap.size();i++) {
//					s = (String) zhangt.dbMap.get(i);
//					if(s.contains(cn)){
//						String a = s.substring(s.indexOf("20130"),s.indexOf("20130") + 14);
//						timeList.add(a);
//					}
//				}
//				if (zhangtnew.dbMap.get(cn)!=null)
//				{
//					s= (String) zhangtnew.dbMap.get(cn);
//					String a = s.substring(s.indexOf("20130"),s.indexOf("20130") + 14);
//					return Long.parseLong(a);
//				}else
//				{
//					result = 0l;
//				}

				if (timeList.size() == 0) {
					result = 0l;
				} else if (timeList.size() == 1) {
					result = Long.valueOf(timeList.get(0).toString());
				} else {
					Long minTime = Long.valueOf(timeList.get(0).toString());
					for (int i = 0; i < timeList.size(); i++) {
						if (Long.valueOf(timeList.get(i).toString()) < minTime) {
							minTime = Long.valueOf(timeList.get(i).toString());
						}
					}
					result = minTime;
				}
				// System.out.println("结果啊="+minTime);
			} catch (Exception e) {
				System.out.println("Exp");
				// e.printStackTrace();
			} finally {
				return result;
			}

		}
		
		//查询cn提交被盗单据的最晚时间
		public static Long getMinIssueSubmit3(String cn) throws IOException {
			Long result = null;

			try {
					
				List timeList = new ArrayList();
				String s = "";
				String form_id  = null;
				for  (int i=0;i<zhangt2.dbLog.size();i++) {
					s = (String) zhangt2.dbLog.get(i);
//					System.out.println(s);
					if(s.contains(cn)){
//						System.out.println("contains");
						form_id = s.split("\t")[2].trim();
//						System.out.println(form_id);
						if("24".equals(form_id) ||"33".equals(form_id) ||"34".equals(form_id) ||"40".equals(form_id)){
							System.out.println("form_id============"+form_id);
							String a = s.substring(s.indexOf("20130"),s.indexOf("20130") + 14);
							timeList.add(a);
						}
						
					}
				}

				if (timeList.size() == 0) {
					result = 0l;
				} else if (timeList.size() == 1) {
					result = Long.valueOf(timeList.get(0).toString());
				} else {
					Long maxTime = Long.valueOf(timeList.get(0).toString());
					for (int i = 0; i < timeList.size(); i++) {
						if (Long.valueOf(timeList.get(i).toString()) > maxTime) {
							maxTime = Long.valueOf(timeList.get(i).toString());
						}
					}
					result = maxTime;
				}
			} catch (Exception e) {
				System.out.println("Exp");
			} finally {
				return result;
			}

		}
		
	
	
	
	//查询DB记录，查出一段时间内提交单据的最早时间
	public static Long getMinIssueSubmit(String cn) throws IOException {
		Long result = null;

		try {
			FileReader in = new FileReader("E:/所有DB记录.txt");
			BufferedReader re = new BufferedReader(in);
			List timeList = new ArrayList();
			String s = "";
			while ((s = re.readLine()) != null) {
//				if (s.contains(cn)) {
//					String temp =  s.split("\t")[1];
//					if(temp.equals("22") || temp.equals("33") || temp.equals("34") ||temp.equals("40")){
//						System.out.println("Stolen");
//						String a = s.substring(s.indexOf("20130"),s.indexOf("20130") + 14);
//						timeList.add(a);
//					}
//				}
				if(s.contains(cn)){
					String a = s.substring(s.indexOf("20130"),s.indexOf("20130") + 14);
					timeList.add(a);
				}
			}
			// System.out.println(timeList.size());

			if (timeList.size() == 0) {
				result = 0l;
			} else if (timeList.size() == 1) {
				result = Long.valueOf(timeList.get(0).toString());
			} else {
				Long minTime = Long.valueOf(timeList.get(0).toString());
				for (int i = 0; i < timeList.size(); i++) {
					if (Long.valueOf(timeList.get(i).toString()) < minTime) {
						minTime = Long.valueOf(timeList.get(i).toString());
					}
				}
				result = minTime;
			}
			// System.out.println("结果啊="+minTime);
		} catch (Exception e) {
			System.out.println("Exp");
			// e.printStackTrace();
		} finally {
			return result;
		}

	}

	public static void judgeGuilty(String cn, long minIssueSubmit,
			FileWriter fw1, FileWriter fw2) throws IOException {
		FileReader in = new FileReader("E:/中间结果-有账号信息得.txt");
		BufferedReader re = new BufferedReader(in);

		String s = "";
		while ((s = re.readLine()) != null) {
			if (s.contains(cn)) {
				long dbTime = getLogTime(s);
				if (minIssueSubmit > dbTime || minIssueSubmit==0) { // 最早单据提交时间  晚于 GM操作时间
				// System.out.println("guilty"+s);
//					System.out.println(minIssueSubmit);
					fw1.write(s + "\n");
				} else {
					// System.out.println("notSure"+s);
					//fw2.write(s + "\n");
				}
			}
		}
		re.close();

	}
	
	
	public static void judgeGuilty2(String cn, long minIssueSubmit,
			FileWriter fw1, FileWriter fw2,String s) throws IOException {

				long dbTime = getLogTime(s);
				if (minIssueSubmit > dbTime || minIssueSubmit==0) { // 最早单据提交时间  晚于 GM操作时间
					fw1.write(s + "\n");							//s为操作日志
				} else {
					fw2.write(s + "\n");
				}

	}
	
	public static void judgeGuilty3(long minIssueSubmit,FileWriter fw1, FileWriter fw2,String s) throws IOException {
				long dbTime = getLogTime(s);
//				System.out.println(dbTime);
//				System.out.println(minIssueSubmit);
				if (minIssueSubmit > dbTime) { 	// 最晚被盗单据提交时间  晚于 GM操作时间
					System.out.println(minIssueSubmit+" > "+dbTime);
					System.out.println(s);
					fw1.write(s + "\n");							
				} else {
//					fw2.write(s + "\n");
				}
	}

	public static long getLogTime(String log) {
		String oriTime = log.substring(0, 19);
		String sTime = oriTime.replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(":", "");
		return Long.valueOf(sTime);
	}

}
