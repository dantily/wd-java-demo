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

//筛选出814个账号中没有提过单的账号！
public class analyseCn {
	public static void main(String[] args) throws IOException {
		
		Map map = new HashMap();
		map.put("upPlayerNew.act", "修改姓名;修改证件号;修改手机;修改邮箱;一键清空");
		map.put("updatePlayer.act", "修改认证邮箱;修改密保;一键清空");
		map.put("uncomm.act", "修改姓名;密保答案");
		map.put("searchPlayerNew.act", "查询账号VIP信息;密保资料信息;防沉迷信息;消费信息");
		map.put("cnplatform/init.act", "查询新账号玩家所有的登录号CN以及UIN、cnMaster");
		map.put("preQueryUserCertification4Match.act", "查询玩家的认证资料信息和操作记录(包括认证手机、邮箱、密保提示问题答案)");
		map.put("preQueryAccountUpdateLog.act", "查询玩家的认证资料修改记录(包括认证手机、邮箱、密保提示问题答案)");
		map.put("queryPlayer.act", "查询账号VIP信息;账号资料密保信息;防沉迷信息;消费信息");
		map.put("playerHistory.act", "查询玩家的资料修改记录(包括认证手机、邮箱、密保提示问题答案)");
		map.put("simpleQueryPlayer.act", "查询账号VIP信息;账号资料密保信息;防沉迷信息;消费信息");
		map.put("commonQueryPlayer.act", "查询账号VIP信息;账号资料密保信息;防沉迷信息;消费信息");
		map.put("querySdeLog.act", "查询密码和超级密码的修改记录");
		
		map.put("commonDetailPlayer.act", "查询玩家具体的信息  参数有cn,gametype");
		map.put("updateUserCertificationSn.act", "修改实名信息");
		map.put("updateUserCertificationCertNum.act", "修改身份证信息");
		map.put("updateUserSecurityPhone.act", "修改认证手机");
		map.put("updateUserSecurityEmail.act", "修改认证邮箱");
		map.put("emptyUserInfo.act", "清空密保资料");
		map.put("updatePlayerEmail.act", "老账号修改玩家邮箱");
		map.put("updaetPlayerQueAns.act ", "老账号清空密保资料");
		
		List<String> list = new ArrayList<String>(map.keySet());
		
		
		FileReader in = new FileReader("E:/结果1/结果1原始.txt");
		BufferedReader re1 = new BufferedReader(in);
		FileWriter fw = new FileWriter("E:/结果1/结果1原始-text列.txt");
		
		String s = "";
		String target = "";
		while((s=re1.readLine()) != null){
			
			if(s.contains(("GM"))){
//				target = s.substring(0, 19);	//TIME
//				target = s.substring(s.indexOf("GM"), s.indexOf("GM")+7);	//GM
//				target = s.substring(s.indexOf("10.6"), s.lastIndexOf("|"));	//IP
//				target = "#"+s.substring(s.indexOf("cn=")+3).trim()+"#";	//CN
				for(int i=0;i<list.size();i++){
					if(s.contains(list.get(i))){
						target = (String) map.get(list.get(i));
						fw.write(target+"\n");
					}
				}
//				fw.write(target+"\n");
			}else{
//				target = s.substring(0, 19);	//TIME
//				target = "#"+s.substring(s.indexOf("cn=")+3).trim()+"#";	//CN
//				target = s.substring(s.indexOf("10.6"), s.lastIndexOf("|"));	//IP
//				target = s.substring(s.indexOf("INFO")+8,s.indexOf("|10.6"));
				for(int i=0;i<list.size();i++){
					if(s.contains(list.get(i))){
						target = (String) map.get(list.get(i));
						System.out.println(target);
						fw.write(target+"\n");
					}
				}
//				fw.write(target+"\n");
			}
			
		}
		re1.close();
		fw.flush();
		fw.close();
		
		
//		while((s=re1.readLine()) != null){
//			String mark = null;
//			for(int i=0;i<list.size();i++){
//				if(s.contains(list.get(i))){
//					mark = String.valueOf(map.get(list.get(i)));
//				}
//			}
//			if(mark != null){
//				target = s.substring(0,s.lastIndexOf("|"))+"  对账号   "+s.substring(s.indexOf("cn=")+3, s.length())+"  进行了  "+mark+"操作";
//				fw.write(target+"\n");
//			}else{
//				System.out.println("ERROR");
//			}
//			
//		}
		
	}
}
