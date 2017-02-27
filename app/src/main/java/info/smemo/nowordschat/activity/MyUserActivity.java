package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.MyUserContract;
import info.smemo.nowordschat.databinding.ActivityMyuserBinding;
import info.smemo.nowordschat.presenter.EditNickPresenter;
import info.smemo.nowordschat.presenter.EditSignaturePresenter;
import info.smemo.nowordschat.presenter.MyUserPresenter;

/**
 * 个人信息资料页
 * Created by neo on 17/1/12.
 */

public class MyUserActivity extends BaseCompatActivity implements MyUserContract.View {

    private ActivityMyuserBinding mBinding;
    private MyUserPresenter mPresenter;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        mBinding = createContentView(R.layout.activity_myuser);
        mPresenter = new MyUserPresenter(this);
        mBinding.setPresenter(mPresenter);
        mBinding.setUserInfo(mPresenter.getUserInfo());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);
    }

    @Override
    public void setPresenter(MyUserContract.Presenter presenter) {

    }

    @Override
    public void startEditNickNamePage() {
        Intent intent = new Intent(this, CommonEditActivity.class);
        intent.putExtra("presenter", EditNickPresenter.class);
        startActivity(intent);
    }

    @Override
    public void startEditSignaturePage() {
        Intent intent = new Intent(this, CommonEditActivity.class);
        intent.putExtra("presenter", EditSignaturePresenter.class);
        startActivity(intent);
    }

    @Override
    public void selectImage() {
        startActivity(new Intent(this, SelectImagesActivity.class));
    }
}
