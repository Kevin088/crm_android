package cn.xll.com.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xll.com.R;


/**
 * HuaErYun
 * Created by xhl on 2015/8/26.
 * Descripation:
 */
public class TitleBarView extends RelativeLayout implements View.OnClickListener {

    private Context context;
    /**
     * 左侧点击蒙版
     */
    private FrameLayout flTitleBarLeft;
    /**
     * 左侧的布局容器
     */
    private RelativeLayout rlTitleBarLeft;
    /**
     * 左侧图片
     */
    private ImageView ivLeft;
    /**
     * 左侧文字
     */
    private TextView tvLeft;
    /**
     * 右侧点击蒙版
     */
    private FrameLayout flTitleBarRight;
    /**
     * 右侧容器
     */
    private RelativeLayout rlTitleBarRight;
    /**
     * 右侧图片
     */
    private ImageView ivRight;
    /**
     * 右侧文字
     */
    private TextView tvRight;
    /**
     * 中间容器
     */
    private LinearLayout llTitleBarMiddle;
    /**
     * 中间文字
     */
    private TextView tvMiddle;
    /**
     * 中间旁边的文字
     */
    private TextView tvMiddleTwo;
    /**
     * 中间图片
     */
    private ImageView ivMiddle;
    /**
     * 右侧文字 图片的
     */
    private RelativeLayout relRight;
    private OnTitleBarClickListener listener;
    private TextView tvLeftTwo;
    public final static int TITLE_BAR_LEFT_CLICK = 1;
    public final static int TITLE_BAR_MINDDLE_CLICK = 2;
    public final static int TITLE_BAR_RIGHT_CLICK = 3;
    public final static int TITLE_BAR_MIDDLE_IMAGE_UP = 4;
    public final static int TITLE_BAR_MIDDLE_IMAGE_DOWN = 5;
    public final static int MIDDLE = 6;
    public final static int LEFT = 7;
    public final static int RIGHT = 8;
    public final static int TEXT = 9;
    public final static int IMAGE = 10;
    private RelativeLayout titleBar;
    private View deviceLine;
    public interface OnTitleBarClickListener {
        abstract void onTitleBarClick(int titleId);
    }

    public TitleBarView(Context context) {
        super(context);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.title_bar_layout, this);
        titleBar= (RelativeLayout) view.findViewById(R.id.titlebar);
        rlTitleBarLeft = (RelativeLayout) view.findViewById(R.id.rl_title_bar_left);
        rlTitleBarRight = (RelativeLayout) view.findViewById(R.id.rl_title_bar_right);
        flTitleBarLeft = (FrameLayout) view.findViewById(R.id.fl_title_bar_left);
        flTitleBarRight = (FrameLayout) view.findViewById(R.id.fl_title_bar_right);
        llTitleBarMiddle = (LinearLayout) view.findViewById(R.id.ll_title_bar_middle);
        flTitleBarLeft.setOnClickListener(this);
        flTitleBarRight.setOnClickListener(this);
        llTitleBarMiddle.setOnClickListener(this);

        ivLeft = (ImageView) view.findViewById(R.id.iv_title_bar_left_image);
        tvLeft = (TextView) view.findViewById(R.id.tv_title_bar_left_text);
        ivMiddle = (ImageView) view.findViewById(R.id.iv_title_bar_middle_image);
        tvMiddle = (TextView) view.findViewById(R.id.tv_title_bar_middle_text);
        tvMiddleTwo = (TextView) view.findViewById(R.id.tv_title_bar_middle_texttwo);
        ivRight = (ImageView) view.findViewById(R.id.iv_title_bar_right_image);
        tvRight = (TextView) view.findViewById(R.id.tv_title_bar_right_text);
        tvLeftTwo= (TextView) view.findViewById(R.id.tv_title_bar_left_text_two);
        relRight=(RelativeLayout) view.findViewById(R.id.rl_title_bar_right);

        deviceLine=view.findViewById(R.id.devide_line);
    }

    /**
     * 设置TitleBar左侧的图片
     *
     * @param resId 图片资源ID
     * @return
     */
    public TitleBarView withLeftImage(int resId) {
        ivLeft.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.GONE);
        ivLeft.setImageResource(resId);
        return this;
    }

    /**
     * 设置左侧的文字内容
     *
     * @param leftText
     * @return
     */
    public TitleBarView withLeftText(String leftText) {
        tvLeft.setVisibility(View.VISIBLE);
        ivLeft.setVisibility(View.GONE);
        tvLeft.setText(leftText);
        return this;
    }

    /**
     * 设置中间的标题
     *
     * @param title         标题的内容
     * @param middleImageId 标题右侧的图片，如果没有图片传0；
     * @return
     */
    public TitleBarView withTitle(String title, int middleImageId) {
        tvMiddle.setVisibility(View.VISIBLE);
        tvMiddle.setText(title);
        if (0 != middleImageId) {
            ivMiddle.setVisibility(View.VISIBLE);
            ivMiddle.setImageResource(middleImageId);
        }
        return this;
    }
    /**
     * 设置中间的副标题
     *
     * @param title         标题的内容
     * @return
     */
    public TitleBarView withTitleTwo(String title) {
        tvMiddleTwo.setVisibility(View.VISIBLE);
        tvMiddleTwo.setText(title);
        return this;
    }


    /**
     * 设置TitleBar右侧的图片
     *
     * @param resId
     * @return
     */
    public TitleBarView withRightImage(int resId) {
        ivRight.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.GONE);
        ivRight.setImageResource(resId);
        return this;
    }

    /**
     * 设置TitleBar的文字
     *
     * @param rightText
     * @return
     */
    public TitleBarView withRightText(String rightText) {
        tvRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.GONE);
        tvRight.setText(rightText);
        return this;
    }
    /**
     * 设置TitleBar的文字
     *
     * @param rightText
     * @return
     */
    public TitleBarView withLeftTwoText(String rightText) {
        tvLeftTwo.setVisibility(View.VISIBLE);
        tvLeftTwo.setText(rightText);
        return this;
    }
    /**
     * 设置中间的图片
     *
     * @param resId
     */
    public void setMiddleImage(int resId) {
        if (ivMiddle.getVisibility() != View.VISIBLE) {
            ivMiddle.setVisibility(View.VISIBLE);
        }
        ivMiddle.setImageResource(resId);
    }
    /**
     * 设置中间的文字
     *
     * @param resId
     */
    public void setMiddleTextColor(int resId) {
        tvMiddle.setTextColor(context.getResources().getColor(resId));
    }
    /**
     * 设置右侧文字的背景
     *
     * @param resId
     */
    public void setRightTextBackground(int resId) {
        if (tvRight.getVisibility() != View.VISIBLE) {
            tvRight.setVisibility(View.VISIBLE);
        }
        tvRight.setBackgroundResource(resId);
    }
    /**
     * 设置TitleBar右侧隐藏
     */
    public void setRightGone(){
        tvRight.setVisibility(GONE);
        ivRight.setVisibility(GONE);
    }

    /**
     * 设置右侧文字的颜色
     *
     * @param resId
     */
    public void setRightTextColor(int resId) {
        if (tvRight.getVisibility() != View.VISIBLE) {
            tvRight.setVisibility(View.VISIBLE);
        }
        tvRight.setTextColor(context.getResources().getColor(resId));
    }

    public void setLeftTextColor(int resId) {
        if (tvLeft.getVisibility() != View.VISIBLE) {
            tvLeft.setVisibility(View.VISIBLE);
        }
        tvLeft.setTextColor(context.getResources().getColor(resId));
    }
    /**
     * 设置右侧的文字的margin
     *
     * @param
     * @return
     */
    public void setRightTextMargin(int left,int top,int right,int bottom) {
        if (tvRight.getVisibility() != View.VISIBLE) {
            tvRight.setVisibility(View.VISIBLE);
        }

      //  RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams)tvRight.getLayoutParams();
        LayoutParams lp = (LayoutParams) relRight.getLayoutParams();

       // lp.addRule(RelativeLayout.);
        lp.setMargins(left, top, right, bottom);
       // lp.set
        relRight.setLayoutParams(lp);

    }

    /**
     * 设置TitleBar的背景色
     * @param color
     */
    public void setTitleBarBackground(int color){
        titleBar.setBackgroundColor(context.getResources().getColor(color));
        deviceLine.setVisibility(GONE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_title_bar_left:
                if (null != listener) {
                    listener.onTitleBarClick(TITLE_BAR_LEFT_CLICK);
                }
                break;
            case R.id.ll_title_bar_middle:
                if (null != listener) {
                    listener.onTitleBarClick(TITLE_BAR_MINDDLE_CLICK);
                }
                break;
            case R.id.fl_title_bar_right:
                if (null != listener) {
                    listener.onTitleBarClick(TITLE_BAR_RIGHT_CLICK);
                }
                break;
        }
    }

    /**
     * 设置TitleBar点击的监听
     *
     * @param listener
     */
    public void setOnTitleBarClickListener(OnTitleBarClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置中间图片的动画
     * @param animationCode
     */
    public void setMiddleImageAnimation(int animationCode){
        if (null != ivMiddle){
            switch (animationCode){
                case TITLE_BAR_MIDDLE_IMAGE_UP:
                    ObjectAnimator.ofFloat(ivMiddle,"rotation",0f,180f).setDuration(200).start();
                    break;
                case TITLE_BAR_MIDDLE_IMAGE_DOWN:
                    ObjectAnimator.ofFloat(ivMiddle,"rotation",180f,360f).setDuration(200).start();
                    break;
            }

        }
    }

    /**
     * 设置布局的可见性
     * @param visibility
     * @param positionCode Titlbar中要设置属性的位置码；TitleBarView.LEFT为左侧，TitleBarMiddle.MIDDLE为中间标题,TitleBarView.RIGHT为右侧
     */
    public void setViewVisibility(int visibility, int positionCode, int contentCode){
        switch (positionCode){
            case LEFT:
                if (contentCode == TEXT){
                    tvLeft.setVisibility(visibility);
                } else if (contentCode == IMAGE){
                    ivLeft.setVisibility(visibility);
                }
                if (visibility != View.VISIBLE){
                    flTitleBarLeft.setClickable(false);
                } else {
                    flTitleBarLeft.setClickable(true);
                }
                break;
            case MIDDLE:
                llTitleBarMiddle.setVisibility(visibility);
                break;
            case RIGHT:
                if (contentCode == TEXT){
                    tvRight.setVisibility(visibility);
                } else if (contentCode == IMAGE){
                    ivRight.setVisibility(visibility);
                }
                if (visibility != View.VISIBLE){
                    flTitleBarRight.setClickable(false);
                } else {
                    flTitleBarRight.setClickable(true);
                }
                break;
        }
    }
}
