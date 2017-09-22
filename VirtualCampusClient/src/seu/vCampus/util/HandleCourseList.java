package seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

import common.CourseStatusInfo;
import common.CourseInfo;
public class HandleCourseList {
	private List<CourseInfo> courselist = new ArrayList();
	private List<List<String>> courseHandledList = new ArrayList();
	private String[][] str = new String[5][7];
	public HandleCourseList(List<CourseInfo> courseList){
		this.courselist = courseList;
		handleCourseList();
	}
	
	public List<List<String>> getHandledCourseList(){
		return courseHandledList;
	}
	
	@SuppressWarnings("null")
	public void handleCourseList(){
		for(int i = 0;i<courselist.size();i++){
			CourseInfo tmpList = courselist.get(i);
			System.out.println(tmpList.getTime());
			String[] args = tmpList.getTime().split(",");
			System.out.println(args[0]);
			//System.out.println(args[1]);
			for(int j = 0;j<args.length;j++){
				String tmpStr = tmpList.getName();
				System.out.println(tmpStr);
				String placeStr = tmpList.getPlace();
				System.out.println(placeStr);
				switch(args[j]){
				//周一
				case "周一12":
					str[0][0] = tmpStr + placeStr;
					break;
				case "周一34":
					str[1][0] = tmpStr + placeStr;
					break;
				case "周一56":
					str[2][0] = tmpStr + placeStr;
					break;
				case "周一78":
					str[3][0] = tmpStr + placeStr;
					break;
				case "周一910":
					str[4][0] = tmpStr + placeStr;
					break;
					//周二
				case "周二12":
					str[0][1] = tmpStr + placeStr;
					break;
				case "周二34":
					str[1][1] = tmpStr + placeStr;
					//System.out.println("�������ܶ�34");
					break;
				case "周二56":
					str[2][1] = tmpStr + placeStr;
					break;
				case "周二78":
					str[3][1] = tmpStr + placeStr;
					break;
				case "周二910":
					str[4][1] = tmpStr + placeStr;
					break;
					//周三
				case "周三12":
					str[0][2] = tmpStr + placeStr;
					break;
				case "周三34":
					str[1][2] = tmpStr + placeStr;
					break;
				case "周三56":
					str[2][2] = tmpStr + placeStr;
					break;
				case "周三78":
					str[3][2] = tmpStr + placeStr;
					break;
				case "周三910":
					str[4][2] = tmpStr + placeStr;
					break;
					//周四
				case "周四12":
					str[0][3] = tmpStr + placeStr;
					//System.out.println("����������12");
					break;
				case "周四34":
					str[1][3] = tmpStr + placeStr;
					break;
				case "周四56":
					str[2][3] = tmpStr + placeStr;
					break;
				case "周四78":
					str[3][3] = tmpStr + placeStr;
					break;
				case "周四910":
					str[4][3] = tmpStr + placeStr;
					break;
					//周五
				case "周五12":
					str[0][4] = tmpStr + placeStr;
					break;
				case "周五34":
					str[1][4] = tmpStr + placeStr;
					break;
				case "周五56":
					str[2][4] = tmpStr + placeStr;
					break;
				case "周五78":
					str[3][4] = tmpStr + placeStr;
					break;
				case "周五910":
					str[4][4] = tmpStr + placeStr;
					break;
					//周六
				case "周六12":
					str[0][5] = tmpStr + placeStr;
					break;
				case "周六34":
					str[1][5] = tmpStr + placeStr;
					break;
				case "周六56":
					str[2][5] = tmpStr + placeStr;
					break;
				case "周六78":
					str[3][5] = tmpStr + placeStr;
					break;
				case "周六910":
					str[4][5] = tmpStr + placeStr;
					break;
					//周日
				case "周日12":
					str[0][6] = tmpStr + placeStr;
					break;
				case "周日34":
					str[1][6] = tmpStr + placeStr;
					break;
				case "周日56":
					str[2][6] = tmpStr + placeStr;
					break;
				case "周日78":
					str[3][6] = tmpStr + placeStr;
					break;
				case "周日910":
					str[4][6] = tmpStr + placeStr;
					break;
				default:
					break;
				}
			}
			
		}
		//����Ĵ����Ѿ������� һ���α�Ķ�ά����
		//���潫��ת��Ϊarraylist
		List<String> tmp2list = new ArrayList();
		for(int k = 0;k<str.length;k++){
			tmp2list = new ArrayList();
			for(int t = 0;t<str[k].length;t++){
				System.out.print(str[k][t]+" ");
				tmp2list.add(str[k][t]);
			}
			System.out.println();
			courseHandledList.add(tmp2list);
			
		}
		for(int i = 0;i<courseHandledList.size();i++){
			System.out.println(courseHandledList.get(i).get(0)+courseHandledList.get(i).get(1)+courseHandledList.get(i).get(2)+courseHandledList.get(i).get(3));
		}
	}
	

}
