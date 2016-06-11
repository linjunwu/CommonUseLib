package com.baidu.commonuselib.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.baidu.commonuselib.R;


/**
 * SimpleProgressDialog
 *
 * @author linjunwu
 * @since 2016/6/7
 */
public class SimpleProgressDialog extends AlertDialog {

    private Context mCtx;

    protected SimpleProgressDialog(Context context) {
        super(context);
        mCtx = context;
    }

    protected SimpleProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected SimpleProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static SimpleProgressDialog show(Context context) {
        SimpleProgressDialog simpleProgressDialog = new SimpleProgressDialog(context);
        simpleProgressDialog.show();

        // 注意一定要在 show方法之后。因为window有个mDecor对于dialog来说好像有个确定的宽度，所以
        // 对于此场景下没有效果，但是可以写死宽度，前提最好有个ui设计来确定大小
        WindowManager.LayoutParams lp = simpleProgressDialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        simpleProgressDialog.onWindowAttributesChanged(lp);
        simpleProgressDialog.getWindow().setAttributes(lp);
        return simpleProgressDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.progress_dialog, null);
        setView(view);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }
}
