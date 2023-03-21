package com.itplus.app_music.server;

import com.itplus.app_music.Model.Album;
import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.Model.Chude;
import com.itplus.app_music.Model.ChudeTheloai;
import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.Model.QuangCao;
import com.itplus.app_music.Model.Theloai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("banner.php")
    Call<List<QuangCao>> GetDatabanner();

    @GET("playlist.php")
    Call<List<PlayList>> GetDataPlaylist();

    @GET("chudevatheloai.php")
    Call<ChudeTheloai> GetDataChudeTheloai();

    @GET("album.php")
    Call<List<Album>> GetDataAlbum();

    @GET("baihatyeuthich.php")
    Call<List<Baihathot>> GetDataBaihatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihathot>> GetDanhsachbaihattheoqc(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihathot>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachallplaylist.php")
    Call<List<PlayList>> GetDanhsachAllPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihathot>> GetDanhsachbhtheotheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachalltheloai.php")
    Call<List<Theloai>> GetallTheloai();

    @FormUrlEncoded
    @POST("getchudetheotheloai.php")
    Call<List<Chude>> GetCDtheoTL(@Field("idtheloai") String idtheloai);

    @GET("getallalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihathot>> GetDanhsachbhtheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> UpdateLike(@Field("like") String like,@Field("idbaihat") String idbaihat);
}
