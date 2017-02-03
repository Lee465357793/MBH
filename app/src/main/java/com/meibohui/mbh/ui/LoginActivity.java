package com.meibohui.mbh.ui;

import android.content.Intent;
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
import com.meibohui.mbh.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_password)
    public EditText et_password;
    @BindView(R.id.cb_record)
    public CheckBox cb_record;
    @BindView(R.id.cb_pwd_eye)
    public CheckBox cb_pwd_eye;
    @BindView(R.id.bt_login)
    public Button bt_login;
    @BindView(R.id.bt_login_error)
    public Button bt_login_error;
    @BindView(R.id.bt_register_go)
    public Button bt_register_go;
    @BindView(R.id.et_username)
    public EditText et_username;
    @BindView(R.id.cb_username)
    public CheckBox cb_username;
    @BindView(R.id.bt_username_clear)
    public Button bt_username_clear;
    private String mPhone;
    private String mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initListener();
        cb_record.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showTextToast(LoginActivity.this, "记录");
                } else {
                    ToastUtil.showTextToast(LoginActivity.this, "解除");
                }
            }
        });

        cb_pwd_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ToastUtil.showTextToast(LoginActivity.this, "密码不可见");
                } else {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ToastUtil.showTextToast(LoginActivity.this, "密码可见");
                }
            }
        });

        et_password.setInputType(cb_pwd_eye.isChecked() ? EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }


    private void initView() {


    }

    private void initListener() {
        bt_login.setOnClickListener(this);
        bt_login_error.setOnClickListener(this);
        bt_register_go.setOnClickListener(this);
        cb_username.setOnClickListener(this);
        bt_username_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_login: //登录
                login();
                break;
            case R.id.bt_login_error: //忘记密码
                break;
            case R.id.bt_register_go: //去注册
                registerGo();  //打开注册界面
                break;
            case R.id.bt_username_clear: //清除账号内容
                et_username.setText("");
                break;
        }
    }

    public void login(){
        submitPhone();//校验
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.addWhereEqualTo("account", mPhone);
        query.addWhereEqualTo("password", mPwd);
        query.setLimit(1);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null){
                        ToastUtil.showTextToast(LoginActivity.this, list.get(0).toString());
                    }else{
                        ToastUtil.showTextToast(LoginActivity.this, e.toString());
                    }

            }
        });

//        if(bu != null){
//            bu.login(new SaveListener<Person>() {
//
//                @Override
//                public void done(Person person, BmobException e) {
//
//                }
//            });
//        }

    }

    private void registerGo() {
        Intent registerGo = new Intent(this, RegisterActivity.class);
        startActivity(registerGo);
        finish();
    }


    private void submitPhone() {
        // validate
        mPhone = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtil.showTextToast(LoginActivity.this, "username不能为空");
            return;
        }
        submitPwd();
    }
    private void submitPwd() {
        // validate
        mPwd = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(mPwd)) {
            ToastUtil.showTextToast(LoginActivity.this, "密码不能为空");
            return;
        }
    }

}
