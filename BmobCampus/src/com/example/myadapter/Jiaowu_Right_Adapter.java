package com.example.myadapter;

import com.example.campusapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Jiaowu_Right_Adapter extends BaseAdapter
{
	private static String spaceString="\n\n                                                               ";
	public static String[][] jiaowu_right_text = {
			{
					" 福州大学至诚学院“本科教学工程”项目实施情况总结报告"+spaceString+"[2015-07-27]",
					" 环境工程专业综合改革试点"+spaceString+"[2015-07-27]",
					" 土木工程专业综合改革试点"+spaceString+"[2015-07-27]",
					" 计算机科学与技术专业综合改革试点"+spaceString+"[2015-07-27]",
					" 物流管理专业综合改革试点"+spaceString+"[2015-07-27]",
					" 管理学教育实践基地——福州市鼓楼区洪山镇人民政府"+spaceString+"[2015-07-27]",
					" 工程实践教育中心—福州高图信息技术有限公司"+spaceString+"[2015-07-27]",
					" 计算机工程实验教学示范中心"+spaceString+"[2015-07-27]" },
			{
					" 退学办理流程"+spaceString+"[2014-12-26]",
					" 申请复（入）学流程"+spaceString+"[2014-12-26]",
					" 申请保留入学资格流程"+spaceString+"[2014-12-26]",
					" 申请休学（保留学籍）流程"+spaceString+"[2014-12-26]",
					" 课程无法重新修读处理流程 "+spaceString+"[2014-12-26]",
					" 调停课以及补课办理流程"+spaceString+"[2014-12-26]",
					" 培养方案制订流程"+spaceString+"[2014-12-26]",
					" 排课基本流程"+spaceString+"[2014-12-26]",
					" 教材预订流程"+spaceString+"[2014-12-26]",
					" 毕业生毕业资格、授予学位资格审批流程"+spaceString+"[2014-12-26]" },
			{
					" 关于2016年元旦放假课程调整的通知"+spaceString+"[2015-12-31]",
					" 关于做好2016届预计毕业生电子注册摄像工作的通知"+spaceString+"[2015-12-29]",
					" 福州大学至诚学院2015-2016学年第一学期晚练次数名单公示"+spaceString+"[2015-12-28]",
					" 关于做好期末考试及下学期开学等工作的通知"+spaceString+"[2015-12-24]",
					" 有关院选《桥牌》12月13日晚最后一次课的通知"+spaceString+"[2015-12-11]",
					" 福州大学至诚学院2014级学生“思想政治理论课”社会实践指导手册"+spaceString+"[2015-12-09]",
					" 福州大学至诚学院《军事理论》考试方式及试卷结构"+spaceString+"[2015-11-27]",
					" 有关11月26日晚《明清小说分类选讲》停课的通知"+spaceString+"[2015-11-26]",
					" 关于举办福州大学至诚学院第六届程序设计竞赛的通知"+spaceString+"[2015-11-13]" },
			{ "至诚学院教务部留言板" },
			{
					"【通知公告】 关于转发做好2016年度全国专业技术人员职称外语等级统一考试工作的通知  "+spaceString+"(2015/12/31)",
					"【通知公告】 关于2016年元旦放假的通知"+spaceString+"(2015/12/31) ",
					"【通知公告】 关于召开福州大学至诚学院全体教职工大会的通知 "+spaceString+"(2015/12/30)",
					"【通知公告】 关于召开福州大学至诚学院中层干部大会的通知"+spaceString+"(2015/12/30)",
					"【通知公告】 关于我院化工楼入口雨篷、体育教研室窗户及指纹机布线等零星项目密封报价结... "+spaceString+"(2015/12/29)",
					"【通知公告】 关于我院创意与设计系教学设备密封报价结果公示"+spaceString+" (2015/12/29)",
					"【通知公告】 关于我院化学工程系教学设备密封报价结果公示"+spaceString+"(2015/12/29)",
					"【通知公告】 关于我院大学生维护与研发中心设备密封报价结果公示"+spaceString+"(2015/12/29)",
					"【通知公告】 福州大学至诚学院关于教师等专业技术职务聘任制工作实施方案（试行）的补充... "+spaceString+"(2015/12/29)",
					"【学院动态】 我院教师参加第六届当代中国新话语国际学术研讨会"+spaceString+" (2015/12/25)",
					"【学院动态】 第二届榕城高校大学生演讲辩论邀请赛在我院举行"+spaceString+"(2015/12/25)" } };

	public static int[] jiaowu_left_logo = { R.drawable.jiaowuright_1,
			R.drawable.jiaowuright_2, R.drawable.jiaowuright_3,
			R.drawable.jiaowuright_4, R.drawable.jiaowuright_5,
			R.drawable.jiaowuright_6, R.drawable.jiaowuright_7,
			R.drawable.jiaowuright_8, R.drawable.jiaowuright_9,
			R.drawable.jiaowuright_10, R.drawable.jiaowuright_11,
			R.drawable.jiaowuright_12, R.drawable.jiaowuright_13,
			R.drawable.jiaowuright_14, R.drawable.jiaowuright_15 };
	private static int low = 0;// 代表左边的第几项
	private LayoutInflater layoutInflater;

	public Jiaowu_Right_Adapter(Context context, int low)
	{
		layoutInflater = LayoutInflater.from(context);
		this.low = low;
	}

	@Override
	public int getCount()
	{
		return jiaowu_right_text[low].length;
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = new ViewHolder();
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.row, null);
			holder.tv_jiaowu_right_text = (TextView) convertView
					.findViewById(R.id.tv_menutext);
			holder.iv_jiaowu_right_logo01 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo01);
			holder.iv_jiaowu_right_logo02 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo02);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_jiaowu_right_text.setText(jiaowu_right_text[low][position]);
		holder.iv_jiaowu_right_logo01
				.setImageResource(jiaowu_left_logo[position % 15]);
		holder.iv_jiaowu_right_logo02.setImageResource(-1);
		return convertView;
	}

	static class ViewHolder
	{
		TextView tv_jiaowu_right_text;
		ImageView iv_jiaowu_right_logo01, iv_jiaowu_right_logo02;
	}
}
