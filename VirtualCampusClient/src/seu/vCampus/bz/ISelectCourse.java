package seu.vCampus.bz;
import java.util.List;

import common.CourseInfo;
public abstract interface ISelectCourse {
	public abstract boolean addCourse(CourseInfo mCourse);//��ӿγ�
	public abstract boolean modifyCourse(CourseInfo mCourse);//�޸Ŀγ�
	public abstract boolean deleteCourse(CourseInfo mCourse);//ɾ���γ�
	public abstract boolean selectCourse(String mString);//ѡ��γ�
	public abstract boolean cancelCourse(String mString);//ȡ���γ�
	public abstract List EnquiryCourseById(String mString);//���ݿγ�ID���Ҷ�Ӧ�Ŀγ���Ϣ
	public abstract List EnquirySelectCourse(String stuId);//�鿴ѡ��Ŀγ� ѧ��
	public abstract List EnquiryTeacherCourse();//�鿴��ʦ�α�
	public abstract List EnquiryAllCourse();//�鿴���пγ�
	public abstract List EnquirySelectCourseCredit();//�鿴��ѡ�γ�ѧ��
	public abstract List EnquirySelectStudent(CourseInfo teacher);//�鿴ѡ��ÿγ̵�ѧ����

	
}
