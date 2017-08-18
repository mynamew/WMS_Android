package com.timi.sz.wms_android.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;


/**
 * 自定义的Dialog
 *
 * @autor timi
 * create at 2017/5/5 14:26
 */
public class MyDialog extends Dialog {
    private Context ctx;
    private View rootView;
    /**
     * 0 :  默认的弹出框优先级
     * 1 ： 系统公告
     * 2 ： 版本更新的引导
     * 3 ： 账号冻结
     */
    public int level = 0;//弹出框的默认优先级

    public MyDialog(Context context, int layoutId) {
        super(context, R.style.Dialog);
        initView(context, layoutId);

    }

    public void initView(Context ctx, int layoutId) {
        this.ctx = ctx;
        rootView = LayoutInflater.from(ctx).inflate(layoutId, null);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(true);
        this.setContentView(rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.this.dismiss();
            }
        });
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
               //弹框消失的回调
                myDialogDismiss();
            }
        });
    }

    /**
     * 设置Dialog中的文本
     *
     * @autor timi
     * create at 2017/5/5 14:34
     */
    public MyDialog setTextViewContent(int resId, String content) {
        if(!(rootView.findViewById(resId) instanceof TextView)){
            return this;
        }
        TextView tv = (TextView) rootView.findViewById(resId);
        if (null != tv && null != content && !TextUtils.isEmpty(content)) {
            tv.setText(content);
        }
        return this;
    }

    /**
     * 设置按钮的文本及点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setButtonListener(int resId, String content, final DialogClickListener listener) {
        if(!(rootView.findViewById(resId) instanceof Button)){
            return this;
        }
        Button bt = (Button) rootView.findViewById(resId);
        if (null != bt) {
            if (null != content && !TextUtils.isEmpty(content)) {
                bt.setText(content);
            }
            if (null != listener) {
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }

    /**
     * 设置图片及点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setImageViewListener(int resId, final DialogClickListener listener) {
        if(!(rootView.findViewById(resId) instanceof ImageView)){
            return this;
        }
        ImageView iv = (ImageView) rootView.findViewById(resId);
        if (null != iv) {
            if (null != listener) {
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }
    /**
     * 设置图片及点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setImageViewListener(int resId, Bitmap bp,final DialogClickListener listener) {
        if(!(rootView.findViewById(resId) instanceof ImageView)){
            return this;
        }
        ImageView iv = (ImageView) rootView.findViewById(resId);
        if (null != iv) {
            if (null != listener) {
                iv.setImageBitmap(bp);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }
    /**
     * 设置View的点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setViewListener(int resId, final DialogClickListener listener) {
        View viewById = rootView.findViewById(resId);
        if (null != viewById) {
            if (null != listener) {
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }

    /**
     * 设置布局的点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setLinearlayoutListener(int resId, final DialogClickListener listener) {
        LinearLayout viewById = (LinearLayout) rootView.findViewById(resId);
        if (null != viewById) {
            if (null != listener) {
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }

    /**
     * 设置相对布局的点击事件
     *
     * @autor timi
     * create at 2017/5/5 14:37
     */
    public MyDialog setRelativelayoutListener(int resId, final DialogClickListener listener) {
        RelativeLayout viewById = (RelativeLayout) rootView.findViewById(resId);
        if (null != viewById) {
            if (null != listener) {
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.dialogClick(MyDialog.this);
                    }
                });
            }
        }
        return this;
    }

    /**
     * 设置是否能够点击外部按钮取消弹出框
     *
     * @autor timi
     * create at 2017/5/5 14:41
     */
    public MyDialog setCancelByOutside(boolean isCancel) {
        this.setCanceledOnTouchOutside(isCancel);
        return this;
    }

    /**
     * 设置Dialog的优先级
     *
     * @autor timi
     * create at 2017/5/5 15:17
     */
    public MyDialog setLevel(int level) {
        this.level = level;
        return this;
    }

    /**
     * 设置弹出框 不能够被返回键取消掉
     *
     * @autor timi
     * create at 2017/5/5 14:44
     */
    public MyDialog setCantCancelByBackPress() {
        //弹出加载框的时候不能被取消掉
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return this;
    }

    private DialogDismissListener disListener = null;

    /**
     * 设置dialog消失的监听器
     *
     * @autor timi
     * create at 2017/5/8 9:24
     */
    public MyDialog setMyDialogDismissListener(DialogDismissListener disListener) {
        this.disListener = disListener;
        return this;
    }

    /**
     * 供外部调用 即MyDialogUtils调用 MyDialog消失的回调
     *
     * @autor timi
     * create at 2017/5/8 9:27
     */
    public MyDialog myDialogDismiss() {
        if (null != disListener) {
            this.disListener.dialogDismiss(this);
        }
        return this;
    }

    /**
     * Dialog点击的接口
     *
     * @autor timi
     * create at 2017/5/5 15:15
     */
    public interface DialogClickListener {
        void dialogClick(MyDialog dialog);
    }

    /**
     * MyDialog 消失的接口
     *
     * @autor timi
     * create at 2017/5/8 9:25
     */
    public interface DialogDismissListener {
        void dialogDismiss(MyDialog dialog);
    }
}
