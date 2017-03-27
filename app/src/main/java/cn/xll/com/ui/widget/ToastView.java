package cn.xll.com.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.xll.com.R;


public class ToastView extends Toast {

	public ToastView(Context context) {
		super(context);
	}

	public static Toast makeText(Context context, CharSequence text, int duration) {
		Toast result = new Toast(context);
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout=(LinearLayout) inflater.inflate(R.layout.dfhe_toast, null);
		
		TextView textView =(TextView) layout.findViewById(R.id.tv_text);
		textView.setText(text);
		
		result.setView(layout);
		result.setDuration(duration);
		return result;
	}

}
