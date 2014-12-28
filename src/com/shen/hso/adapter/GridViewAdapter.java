package com.shen.hso.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shen.hso.base.CustomBaseAdapter;
import com.shen.hso.util.LogUtil;

public class GridViewAdapter<T> extends CustomBaseAdapter<T> {

	private Context context;     //运行上下文 
	private LayoutInflater layoutInflater; //视图容器 
	
	public GridViewAdapter(Context context,List<T> list)
	{
		this.context = context;
		layoutInflater = LayoutInflater.from(context);   //创建视图容器并设置上下文   
		this.mList = list;
	}
	@Override
	protected View getExView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub   
		        LogUtil.e("method", "getView");   
		       final int selectID = position;   
		       //自定义视图   
		        ListItemView  listItemView = null;   
		        if (convertView == null) {   
		            listItemView = new ListItemView();    
		            //获取list_item布局文件的视图   
		            //convertView = layoutInflater.inflate(R.layout.list_item, null);   
		           //获取控件对象   
//		            listItemView.image = (ImageView)convertView.findViewById(R.id.imageItem);   
//		            listItemView.title = (TextView)convertView.findViewById(R.id.titleItem);   
//		            listItemView.info = (TextView)convertView.findViewById(R.id.infoItem);   
//		            listItemView.detail= (Button)convertView.findViewById(R.id.detailItem);   
//		            listItemView.check = (CheckBox)convertView.findViewById(R.id.checkItem);   
		            //设置控件集到convertView   
		            convertView.setTag(listItemView);   
		        }else {   
		            listItemView = (ListItemView)convertView.getTag();   
		        }   
		//      Log.e("image", (String) listItems.get(position).get("title"));  //测试   
		//      Log.e("image", (String) listItems.get(position).get("info"));   
		           
		        //设置文字和图片   
//		        listItemView.image.setBackgroundResource((Integer) mList.get(   
//		                position).get("image"));   
//		        listItemView.title.setText((String) mList.get(position)   
//		                .get("title"));   
//		        listItemView.info.setText((String) mList.get(position).get("info"));   
		        listItemView.detail.setText("商品详情");   
		        //注册按钮点击时间爱你   
		        listItemView.detail.setOnClickListener(new View.OnClickListener() {   
		            @Override  
		            public void onClick(View v) {   
		               //显示物品详情   
		                //showDetailInfo(selectID);   
		            }   
		        });   
		        // 注册多选框状态事件处理   
//		        listItemView.check   
//		               .setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {   
//		                   @Override  
//		                    public void onCheckedChanged(CompoundButton buttonView,   
//		                            boolean isChecked) {   
//		                        //记录物品选中状态   
//		                        //checkedChange(selectID);   
//		                    }   
//		        });   
		           
		        return convertView; 
	}

	@Override
	protected void onReachBottom()
	{
		
	}

	public final class ListItemView
	{   //自定义控件集合     
		public ImageView image;     
		public TextView title;     
	    public TextView info;   
	    public CheckBox check;   
	    public Button detail;          
	}     

}
