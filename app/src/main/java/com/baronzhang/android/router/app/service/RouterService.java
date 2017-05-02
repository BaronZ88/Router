package com.baronzhang.android.router.app.service;

import com.baronzhang.android.router.annotation.router.CombinationUri;
import com.baronzhang.android.router.annotation.router.FullUri;
import com.baronzhang.android.router.annotation.router.IntentExtrasParam;
import com.baronzhang.android.router.annotation.router.UriParam;
import com.baronzhang.android.router.app.model.User;

import java.util.ArrayList;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/3/29
 */
public interface RouterService {

    @CombinationUri(scheme = "router", host = "com.baronzhang.android.router.SecondActivity")
    void startSecondActivity(@UriParam("preActivity") String preActivity, @IntentExtrasParam("stringParam") String stringParam, @IntentExtrasParam("intParam") int intParam);

    @CombinationUri(scheme = "router", host = "com.baronzhang.android.router", port = "6666", path = "/activity/third")
    void startThirdActivity(@UriParam("preActivity") String preActivity, @IntentExtrasParam("array") ArrayList<Integer> array);

    @FullUri("router://com.baronzhang.android.router.FourthActivity")
    void startFourthActivity(@UriParam("preActivity") String preActivity, @IntentExtrasParam("user") User user);

    @FullUri("router://com.baronzhang.android.router.FifthActivity")
    void startFifthActivity(@UriParam("preActivity") String preActivity, @IntentExtrasParam("users") ArrayList<User> users);
}
