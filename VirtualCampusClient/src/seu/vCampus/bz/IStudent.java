package seu.vCampus.bz;
import common.Student;
import common.StudentRollInfo;

import java.util.List;

public abstract interface IStudent {
	public abstract  List EnquiryStuById(StudentRollInfo StuId);//��ѧ��ID��ѯ
	public abstract  List EnquiryAllStu();//��������ѧ��
	public abstract  boolean AddStu(StudentRollInfo stu);//���ѧ��
	public abstract  boolean DeleteStu(StudentRollInfo stuId);//ɾ��ѧ��
	public abstract  boolean ModifyStu(StudentRollInfo stu);//�޸�ѧ����Ϣ
	
   }
