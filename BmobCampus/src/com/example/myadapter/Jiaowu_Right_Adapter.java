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
					" ���ݴ�ѧ����ѧԺ�����ƽ�ѧ���̡���Ŀʵʩ����ܽᱨ��"+spaceString+"[2015-07-27]",
					" ��������רҵ�ۺϸĸ��Ե�"+spaceString+"[2015-07-27]",
					" ��ľ����רҵ�ۺϸĸ��Ե�"+spaceString+"[2015-07-27]",
					" �������ѧ�뼼��רҵ�ۺϸĸ��Ե�"+spaceString+"[2015-07-27]",
					" ��������רҵ�ۺϸĸ��Ե�"+spaceString+"[2015-07-27]",
					" ����ѧ����ʵ�����ء��������й�¥����ɽ����������"+spaceString+"[2015-07-27]",
					" ����ʵ���������ġ����ݸ�ͼ��Ϣ�������޹�˾"+spaceString+"[2015-07-27]",
					" ���������ʵ���ѧʾ������"+spaceString+"[2015-07-27]" },
			{
					" ��ѧ��������"+spaceString+"[2014-12-26]",
					" ���븴���룩ѧ����"+spaceString+"[2014-12-26]",
					" ���뱣����ѧ�ʸ�����"+spaceString+"[2014-12-26]",
					" ������ѧ������ѧ��������"+spaceString+"[2014-12-26]",
					" �γ��޷������޶��������� "+spaceString+"[2014-12-26]",
					" ��ͣ���Լ����ΰ�������"+spaceString+"[2014-12-26]",
					" ���������ƶ�����"+spaceString+"[2014-12-26]",
					" �ſλ�������"+spaceString+"[2014-12-26]",
					" �̲�Ԥ������"+spaceString+"[2014-12-26]",
					" ��ҵ����ҵ�ʸ�����ѧλ�ʸ���������"+spaceString+"[2014-12-26]" },
			{
					" ����2016��Ԫ���żٿγ̵�����֪ͨ"+spaceString+"[2015-12-31]",
					" ��������2016��Ԥ�Ʊ�ҵ������ע����������֪ͨ"+spaceString+"[2015-12-29]",
					" ���ݴ�ѧ����ѧԺ2015-2016ѧ���һѧ����������������ʾ"+spaceString+"[2015-12-28]",
					" ����������ĩ���Լ���ѧ�ڿ�ѧ�ȹ�����֪ͨ"+spaceString+"[2015-12-24]",
					" �й�Ժѡ�����ơ�12��13�������һ�οε�֪ͨ"+spaceString+"[2015-12-11]",
					" ���ݴ�ѧ����ѧԺ2014��ѧ����˼���������ۿΡ����ʵ��ָ���ֲ�"+spaceString+"[2015-12-09]",
					" ���ݴ�ѧ����ѧԺ���������ۡ����Է�ʽ���Ծ�ṹ"+spaceString+"[2015-11-27]",
					" �й�11��26��������С˵����ѡ����ͣ�ε�֪ͨ"+spaceString+"[2015-11-26]",
					" ���ھٰ츣�ݴ�ѧ����ѧԺ�����������ƾ�����֪ͨ"+spaceString+"[2015-11-13]" },
			{ "����ѧԺ�������԰�" },
			{
					"��֪ͨ���桿 ����ת������2016���ȫ��רҵ������Աְ������ȼ�ͳһ���Թ�����֪ͨ  "+spaceString+"(2015/12/31)",
					"��֪ͨ���桿 ����2016��Ԫ���żٵ�֪ͨ"+spaceString+"(2015/12/31) ",
					"��֪ͨ���桿 �����ٿ����ݴ�ѧ����ѧԺȫ���ְ������֪ͨ "+spaceString+"(2015/12/30)",
					"��֪ͨ���桿 �����ٿ����ݴ�ѧ����ѧԺ�в�ɲ�����֪ͨ"+spaceString+"(2015/12/30)",
					"��֪ͨ���桿 ������Ժ����¥����������������Ҵ�����ָ�ƻ����ߵ�������Ŀ�ܷⱨ�۽�... "+spaceString+"(2015/12/29)",
					"��֪ͨ���桿 ������Ժ���������ϵ��ѧ�豸�ܷⱨ�۽����ʾ"+spaceString+" (2015/12/29)",
					"��֪ͨ���桿 ������Ժ��ѧ����ϵ��ѧ�豸�ܷⱨ�۽����ʾ"+spaceString+"(2015/12/29)",
					"��֪ͨ���桿 ������Ժ��ѧ��ά�����з������豸�ܷⱨ�۽����ʾ"+spaceString+"(2015/12/29)",
					"��֪ͨ���桿 ���ݴ�ѧ����ѧԺ���ڽ�ʦ��רҵ����ְ��Ƹ���ƹ���ʵʩ���������У��Ĳ���... "+spaceString+"(2015/12/29)",
					"��ѧԺ��̬�� ��Ժ��ʦ�μӵ����쵱���й��»������ѧ�����ֻ�"+spaceString+" (2015/12/25)",
					"��ѧԺ��̬�� �ڶ����ųǸ�У��ѧ���ݽ���������������Ժ����"+spaceString+"(2015/12/25)" } };

	public static int[] jiaowu_left_logo = { R.drawable.jiaowuright_1,
			R.drawable.jiaowuright_2, R.drawable.jiaowuright_3,
			R.drawable.jiaowuright_4, R.drawable.jiaowuright_5,
			R.drawable.jiaowuright_6, R.drawable.jiaowuright_7,
			R.drawable.jiaowuright_8, R.drawable.jiaowuright_9,
			R.drawable.jiaowuright_10, R.drawable.jiaowuright_11,
			R.drawable.jiaowuright_12, R.drawable.jiaowuright_13,
			R.drawable.jiaowuright_14, R.drawable.jiaowuright_15 };
	private static int low = 0;// ������ߵĵڼ���
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
