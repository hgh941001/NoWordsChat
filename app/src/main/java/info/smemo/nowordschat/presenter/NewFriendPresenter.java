package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.appaction.enums.IMFutureFriendType;
import info.smemo.nowordschat.contract.NewFriendContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class NewFriendPresenter implements NewFriendContract.Presenter {

    private NewFriendContract.View mView;

    private ArrayList<BookBean> list;

    public NewFriendPresenter(@NonNull NewFriendContract.View view) {
        mView = checkNotNull(view, "NewFriendContract.View cannot be null");
        mView.setPresenter(this);
    }


    @Override
    public void onRefresh() {
        loadFutureData();
    }

    @Override
    public void loadFutureData() {
        FriendController.getFutureFriend(new FriendController.GetFutureFriendListener() {
            @Override
            public void success(ArrayList<BookBean> bookBeanArrayList) {
                list.clear();
                for (BookBean bookBean : bookBeanArrayList) {
                    if (bookBean.type == IMFutureFriendType.IM_FUTURE_FRIEND_PENDENCY_IN_TYPE) {
                        bookBean.setTitle("ADD");
                    }
                    list.add(0, bookBean);
                }
                mView.notifyDataSetChanged();
            }

            @Override
            public void error(int code, String message) {
                mView.notifyDataSetChanged();
                mView.showSnackbarMessage(message);
            }
        });
    }

    @Override
    public void addFriendResponse(BookBean bookBean, boolean isAccept) {
        FriendController.addFriendResponse(bookBean.identifier, isAccept, new ActionInterface.BaseComplete() {
            @Override
            public void success() {
                mView.showSnackbarMessage("好友添加成功");
                mView.finishSelf();
            }

            @Override
            public void error(int code, String message) {
                mView.showSnackbarMessage(message);
            }
        });
    }

    @Override
    public ArrayList<BookBean> getData() {
        if (null == list)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public void start() {
        loadFutureData();
    }
}
