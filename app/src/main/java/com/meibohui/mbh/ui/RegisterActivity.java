package com.meibohui.mbh.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.meibohui.mbh.R;
import com.meibohui.mbh.model.Person;
import com.meibohui.mbh.utils.TLog;
import com.meibohui.mbh.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.widget.Toast.makeText;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_password)
    public EditText et_password;
    @BindView(R.id.cb_record)
    public CheckBox cb_record;
    @BindView(R.id.cb_pwd_eye)
    public CheckBox cb_pwd_eye;
    @BindView(R.id.btn_rigister)
    public Button btn_rigister;
    @BindView(R.id.et_username)
    public EditText et_username;
    @BindView(R.id.cb_username)
    public CheckBox cb_username;
    @BindView(R.id.bt_username_clear)
    public Button bt_username_clear;
    @BindView(R.id.et_verify_code)
    public EditText et_verify_code;

    private String mPhone;
    private String mCode;
    private String mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initListener();

        cb_record.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showTextToast(RegisterActivity.this, "记录");
                } else {
                    ToastUtil.showTextToast(RegisterActivity.this, "解除");
                }
            }
        });

        cb_pwd_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ToastUtil.showTextToast(RegisterActivity.this, "密码不可见");
                } else {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ToastUtil.showTextToast(RegisterActivity.this, "密码可见");
                }
            }
        });

        et_password.setInputType(cb_pwd_eye.isChecked() ? EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    private void initView() {


    }

    private void initListener() {
        btn_rigister.setOnClickListener(this);
        cb_username.setOnClickListener(this);
        bt_username_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_get_verify: //获取验证码
                submitPhone();  //非空判断
                String country =  "86";
//                SMSSDK.getVerificationCode(country, mPhone);  //请求短信验证码
                break;
            case R.id.btn_rigister: //注册
                submitPhone(); //非空判断
                register();
                break;
        }
    }


    private void register() {
        //TODO
        Person p2 = new Person();
        p2.setAccount(mPhone);
        p2.setPassword(mPwd);
        p2.setImei("admin");
//        InputStream is = getResources().openRawResource(R.mipmap.app_icon);
//        File file = new File("test.jpg");
//
//        try {
//            // 打开一个已存在文件的输出流
//            FileOutputStream fos = new FileOutputStream(file);
//            // 将输入流is写入文件输出流fos中
//            int ch = 0;
//            while((ch=is.read()) != -1){
//                fos.write(ch);
//            }
//            //关闭输入流等
//            fos.close();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } finally{
//
//        }

        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    ToastUtil.showTextToast(RegisterActivity.this, "添加数据成功，返回objectId为："+objectId);
                }else{
                    ToastUtil.showTextToast(RegisterActivity.this, "创建数据失败：" + e.getMessage());
                    TLog.error("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    public void verifyPramer(String pramer){
        if(pramer.length() < 5)
            ToastUtil.showTextToast(RegisterActivity.this, "账号密码必须长度不足5位");
        return;
    }

    private void submitPhone() {
        // validate
        mPhone = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtil.showTextToast(RegisterActivity.this, "username不能为空");
            return;
        }
        verifyPramer(mPhone);
        submitPwd();
    }

    private void submitPwd() {
        // validate
        mPwd = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(mPwd)) {
            ToastUtil.showTextToast(RegisterActivity.this, "密码不能为空");
            return;
        }
        verifyPramer(mPwd);
    }

    private void submitCode(){
        mCode = et_verify_code.getText().toString().trim();
        if (TextUtils.isEmpty(mCode)) {
            ToastUtil.showTextToast(RegisterActivity.this, "code不能为空");
            return;
        }
        submitPhone();
    }
}
