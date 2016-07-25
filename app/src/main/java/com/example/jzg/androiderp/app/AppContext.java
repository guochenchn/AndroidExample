package com.example.jzg.androiderp.app;/**
 * author: gcc
 * date: 2016/6/13 13:59
 * email:
 */

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.example.jzg.androiderp.Interface.HttpService;
import com.example.jzg.androiderp.utils.FrescoImageLoader;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orm.SugarContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import us.pinguo.edit.sdk.PGEditImageLoader;
import us.pinguo.edit.sdk.base.PGEditSDK;

/**
 * author: guochen
 * date: 2016/6/13 13:59
 * email: 
 */
public class AppContext extends Application {
    private static final String API = "http://api.laifudao.com";
    public static HttpService httpService;
    public static boolean isNetWork = true;
    public static int isWifi = 0;
    public DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
        initSugar();
        //初始化logger
        initLogger();
        initHttp();
        initImageLoader();
        initNet();
        initCmage();
        initFresco();
        initPic();
        initRealm();

    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
    }

    private void initFresco() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                //缓存文件的路径
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"Moe Studio"))
                //缓存文件的名字
                .setBaseDirectoryName("fresco_sample")
                //缓存文件的最大值,必须设置
                .setMaxCacheSize(200*1024*1024)//200MB
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, imagePipelineConfig);
    }

    private void initPic() {
       String photoPath = Environment.getExternalStorageDirectory() + "/PicTest/" + System.currentTimeMillis() + "/photo_temp/";
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)//开启相机
                .setEnableEdit(true)//开启编辑
                .setEnableCrop(true)//开启剪裁
                .setEnableRotate(true)//开启旋转
                .setCropSquare(true)//剪裁正方形
                .setEnablePreview(true)//开启预览功能
        .build();
        //配置imageloader
        FrescoImageLoader imageloader =  new FrescoImageLoader(this);
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .setTakePhotoFolder(new File(photoPath))
        .build();
        GalleryFinal.init(coreConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void initCmage() {
        PGEditImageLoader.initImageLoader(this);
        PGEditSDK.instance().initSDK(this);
    }

    private void initSugar() {
        SugarContext.init(this);
    }

    private void initNet() {
        ReactiveNetwork reactiveNetwork = new ReactiveNetwork();
        reactiveNetwork.observeConnectivity(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
//                        Logger.d(connectivityStatus.toString());
//                        Logger.e(ConnectivityStatus.OFFLINE.toString());
                        if (connectivityStatus.toString().equals(ConnectivityStatus.OFFLINE.toString()) || connectivityStatus.toString().equals(ConnectivityStatus.WIFI_CONNECTED_HAS_NO_INTERNET.toString())) {
                            Toast.makeText(getApplicationContext(), "没有网络", Toast.LENGTH_SHORT).show();
                            isNetWork = false;
                        } else {
                            isNetWork = true;
                        }

                        if (connectivityStatus.toString().equals(ConnectivityStatus.WIFI_CONNECTED.toString()) && connectivityStatus.toString().equals(ConnectivityStatus.WIFI_CONNECTED_HAS_NO_INTERNET.toString())) {
                            //连接了无线,且无线不能用
                            isWifi = 1;
                        } else if (connectivityStatus.toString().equals(ConnectivityStatus.OFFLINE.toString())) {
                            //网络不可用
                            isWifi = 1;
                        } else if (connectivityStatus.toString().equals(ConnectivityStatus.MOBILE_CONNECTED.toString())) {
                            //连接了移动网络
                            isWifi = 2;
                        } else {
                            isWifi = 0;
                        }
//                        bt_testreactivenetwork.setText(connectivityStatus.toString());
                    }
                });
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with
        // configuration.

        options = new DisplayImageOptions.Builder()
               // .showImageOnLoading(R.drawable.jingzhengu_moren)//设置图片在下载期间显示的图片
               // .showImageForEmptyUri(R.drawable.jingzhengu_moren)//设置图片Uri为空或是错误的时候显示的图片
               // .showImageOnFail(R.drawable.jingzhengu_moren)//设置图片加载/解码过程中错误时候显示的图片
                .delayBeforeLoading(100)//设置延时多少时间后开始下载
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的资源是否缓存在SD卡中
                .considerExifParams(true)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片以何种编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(500))//是否图片加载好后渐入的动画时间
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 初始化网络框架
     */
    private void initHttp() {
        OkHttpClient client = new OkHttpClient.Builder()
                // 向Request Header添加一些业务相关数据，如APP版本，token
                  .addInterceptor(new HeadInterceptor())
                //日志Interceptor，可以打印日志
                //  .addInterceptor(logging)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                // 失败重试
                .retryOnConnectionFailure(true)
                // 支持Https需要加入SSLSocketFactory
                //   .sslSocketFactory(sslSocketFactory)
                // 信任的主机名，返回true表示信任，可以根据主机名和SSLSession判断主机是否信任
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                // 使用host name作为cookie保存的key
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

//        restAdapter.client().setConnectTimeout(10, TimeUnit.MINUTES);
//        restAdapter.client().setReadTimeout(10, TimeUnit.MINUTES);
//        restAdapter.client().setWriteTimeout(10, TimeUnit.MINUTES);
//        restAdapter.client().interceptors().add(new LoggingInterceptor());//拦截器
        // creating a service for adapter with our GET class
        httpService = restAdapter.create(HttpService.class);
    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
        Logger.init("AppContext")
                .setLogLevel(LogLevel.FULL)
                .setMethodCount(3);
//                .methodCount(3)                 // default 2
//                .logTool(new AndroidLogTool()); // custom log tool, optional
    }

    public class HeadInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            /*Request originalRequest = chain.request();
            Request compressedRequest = originalRequest.newBuilder()
                    .headers(Headers.of(getHeaders()))
                    .build();
            return chain.proceed(compressedRequest);*/
            Request request = chain.request();
            // Logger.i("request="+request);
            long t1 = System.nanoTime();
            //Logger.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            // Logger.i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            final String responseString = new String(response.body().bytes());
            // Logger.i("Response: " + responseString);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseString)).build();
        }
    }
}
