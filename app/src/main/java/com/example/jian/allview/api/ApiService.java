package com.example.jian.allview.api;



import com.example.jian.allview.find.bean.FindArticle;
import com.example.jian.allview.mine.bean.Hotel;
import com.example.jian.allview.mine.bean.HotelAndRoomID;
import com.example.jian.allview.mine.bean.HotelInfo;
import com.example.jian.allview.mine.bean.IDInt;
import com.example.jian.allview.mine.bean.Order;
import com.example.jian.allview.mine.bean.OrderDetail;
import com.example.jian.allview.mine.bean.ScenicSpot;
import com.example.jian.allview.mine.bean.User;
import com.example.jian.allview.mine.bean.ID;
import com.example.jian.allview.mine.bean.UserInfo;
import com.example.jian.allview.mine.bean.UserInfomation;
import com.example.jian.allview.news.bean.CommentResponse;
import com.example.jian.allview.news.bean.NewsDetail;
import com.example.jian.allview.news.bean.NewsResponse;
import com.example.jian.allview.news.bean.ResultResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by jian on 2017/9/19.
 */

public interface ApiService {
    String GET_COMMENT_LIST = "http://is.snssdk.com/article/v2/tab_comments/";
    String GET_ARTICLE_LIST = "http://is.snssdk.com/api/news/feed/v66/?category=news_local&concern_id=6226114249253456386&refer=1&count=20&loc_mode=6&loc_time=1505739453&latitude=29.136081&longitude=110.465326&city=%E5%BC%A0%E5%AE%B6%E7%95%8C%E5%B8%82&tt_from=pull&user_city=%E5%BC%A0%E5%AE%B6%E7%95%8C&lac=29518&cid=45723&cp=5e97bcfccf924q1&plugin_enable=3&iid=15115459457&device_id=35657034542&ac=wifi&channel=leshistore&aid=13&app_name=news_article&version_code=636&version_name=6.3.6&device_platform=android&ab_version=177253%2C171044%2C176488%2C172665%2C172657%2C171194%2C176915%2C173737%2C170354%2C175617%2C174742%2C176743%2C177071%2C168041%2C159170%2C169445%2C174273%2C168998%2C174396%2C170493%2C177454%2C177166%2C152026%2C176592%2C176287%2C174801%2C177276%2C31210%2C151777%2C176655%2C177414%2C171482%2C170713%2C176741%2C156262%2C145585%2C174429%2C177111%2C177109%2C177258%2C173197%2C175879%2C162572%2C176599%2C176609%2C161379%2C152377%2C175631%2C176617%2C170988%2C170989%2C171537%2C150353%2C176597%2C164095%2C176652%2C177384%2C176607&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=Le+X820&device_brand=LeEco&language=zh&os_api=23&os_version=6.0.1&uuid=861462030871784&openudid=480cf0d5370472bc&manifest_version_code=636&resolution=1440*2560&dpi=560&update_version_code=6368&_rticket=1505741092865&plugin=2431";
//    String GET_ARTICLE_LIST ="api/news/feed/v66/?category=news_local&concern_id=6226114249253456386&refer=1&count=20&min_behot_time=1505728106&last_refresh_sub_entrance_interval=1505741092&loc_mode=6&loc_time=1505739453&latitude=29.136081&longitude=110.465326&city=%E5%BC%A0%E5%AE%B6%E7%95%8C%E5%B8%82";
//            "http://is.snssdk.com/api/news/feed/v53/?category=news_local&min_behot_time=1505728106&city=张家界市&iid=15115459457&device_id=35657034542";
    /**
     * 获取新闻列表

     */
    @GET
    Observable<NewsResponse> getNewsList(@Url String url);

    /**
     * 获取新闻详情
     */
    @GET
    Observable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);



    @GET
    Observable<CommentResponse> getComment(@Url String url);



    @POST("User/login.action")
    Observable<String> getLoginStatues(@Body User user);

    @POST("User/add.action")
    Observable<String> getRegsitStatues(@Body User user);

    @POST("User/get.action")
    Observable<UserInfomation> getUserInfo(@Body UserInfo user);

    @POST("User/update.action")
    Observable<String> deaUserInfo(@Body UserInfomation user);

    @POST("UserOrder/getSummaryByUserID.action")
    Observable<List<Order>> getUserOrderList(@Body ID arg);
    @POST("HotFound/get.action")
    Observable<List<FindArticle>> getFindArticleList();
    @POST("UserOrder/get.action")
    Observable<OrderDetail> getOrderDetail(@Body IDInt arg);
    @POST("HotelRoomDetail/getByByHotelAndRoom.action")
    Observable<HotelInfo> getOrderHotelRoomInfo(@Body HotelAndRoomID arg);
    @POST("Hotel/get.action")
    Observable<Hotel> getOrderHotelInfo(@Body IDInt arg);
    @POST("Scenic/get.action")
    Observable<ScenicSpot> getScenicSpotInfo(@Body IDInt arg);

}
