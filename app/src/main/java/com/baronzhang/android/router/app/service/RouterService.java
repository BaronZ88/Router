package com.baronzhang.android.router.app.service;

import com.baronzhang.android.router.annotations.router.CombinationUri;
import com.baronzhang.android.router.annotations.router.FullUri;
import com.baronzhang.android.router.annotations.router.IntentExtrasParam;
import com.baronzhang.android.router.annotations.router.UriParam;
import com.baronzhang.android.router.app.model.User;

import java.util.ArrayList;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2017/3/29
 */
public interface RouterService {

    @CombinationUri(scheme = "router", host = "com.baronzhang.android.router.SecondActivity")
    void startSecondActivity(@UriParam("preActivity") String preActivity, @IntentExtrasParam("stringParam") String stringParam, @IntentExtrasParam("intParam") int intParam);

    @CombinationUri(scheme = "router", host = "com.baronzhang.android.router", port = "6666", path = "/activity/third")
    void startThirdActivity(@UriParam("cityId") String cityId, @IntentExtrasParam("array") ArrayList<Integer> array);

    @FullUri("router://com.baronzhang.android.router.FourthActivity")
    void startFourthActivity(@UriParam("cityId") String cityId, @IntentExtrasParam("User") User user);
}
