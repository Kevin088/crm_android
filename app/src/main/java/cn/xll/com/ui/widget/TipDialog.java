package cn.xll.com.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xll.com.R;
import cn.xll.com.utils.SupoffUtils;


/**
 *@author xull
 *@date 2016/11/23
 */
public class TipDialog extends Dialog {
	private Button btnOk;
	private Button btnCancel;
	private TextView textTitle;
	private TextView textMessage;
	private View lineMiddle,lineTitle;
	//TODO
	private boolean isShowCancel=true;
	private boolean isShowlineMiddle=true;
	private boolean isShowMessage=true;
	private boolean isShowTitle =true;
	private RelativeLayout tipTrueDialog;
	private String title;
	private String message;
	private String strOk;
	private String strCancel;
	private View.OnClickListener PositiveButton,
			NegativeButton;
	private Context context;
 	public TipDialog(Context context) {
		super(context, R.style.tip_dialog);
		this.context=context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_dialog);
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.TipDialog);
		relative.getLayoutParams().width = SupoffUtils.getScreenWidth(context);
		relative.getLayoutParams().height =SupoffUtils.getScreenHeight(context);
		tipTrueDialog = (RelativeLayout) findViewById(R.id.RelativeLayoutTwo);
		textTitle = (TextView) findViewById(R.id.DialogTitle);
		lineTitle=findViewById(R.id.Line1);
		if(isShowTitle){
			textTitle.setText(title);
		}else {
			textTitle.setVisibility(View.GONE);
			lineTitle.setVisibility(View.GONE);

		}

		btnCancel = (Button) findViewById(R.id.TwoDialogCancel);
		btnCancel.setText(strCancel);
		btnCancel.setOnClickListener(NegativeButton);
		
		
		if (isShowCancel) {
			btnOk = (Button) findViewById(R.id.TwoDialogOk);
			tipTrueDialog.setVisibility(View.VISIBLE);
			findViewById(R.id.DialogOk).setVisibility(View.GONE);
		} else {
			btnOk = (Button) findViewById(R.id.DialogOk);
			btnOk.setVisibility(View.VISIBLE);
			tipTrueDialog.setVisibility(View.GONE);
		}
		btnOk.setText(strOk);
		btnOk.setOnClickListener(PositiveButton);

		lineMiddle = findViewById(R.id.Line2);
		textMessage = (TextView) findViewById(R.id.DialogText);
		textMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
		textMessage.setText(message);
		if (isShowlineMiddle) {
			lineMiddle.setVisibility(View.VISIBLE);
		} else {
			lineMiddle.setVisibility(View.GONE);
		}
		if (isShowMessage) {
			textMessage.setVisibility(View.VISIBLE);
		} else {
			textMessage.setVisibility(View.GONE);
		}
		
		//TODO
//		tipTrueDialog.setItems(items,)
	}

	public void setShowlineMiddle(boolean isShowlineMiddle) {
		this.isShowlineMiddle = isShowlineMiddle;
	}

	public void setTextTitle(TextView textTitle) {
		this.textTitle = textTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStrCancel(String strCancel) {
		this.strCancel = strCancel;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStrOk(String strOk) {
		this.strOk = strOk;
	}

	public void setPositiveButton(
			View.OnClickListener positiveButton) {
		PositiveButton = positiveButton;
	}

	public void setShowCancel(boolean isShowCancel) {
		this.isShowCancel = isShowCancel;
	}

	public void setShowMessage(boolean isShowMessage) {
		this.isShowMessage = isShowMessage;
	}

	public void setNegativeButton(View.OnClickListener negativeButton) {
		NegativeButton = negativeButton;
	}
	
	public void setGlobalDialog(boolean isGlobal){
		if(isGlobal){
			this.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		}
	}

	public void setIsShowTitle(boolean isShowTitle) {
		this.isShowTitle = isShowTitle;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
		}
		return false;
	}
}
