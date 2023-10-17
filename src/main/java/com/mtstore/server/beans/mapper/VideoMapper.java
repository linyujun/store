package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.entity.VideoEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2022-04-11
 */
public interface VideoMapper extends BaseMapper<VideoEntity> {

    //机构取视频
    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT * from kz_video WHERE id in " +
            "(SELECT video_id FROM `kz_video_agent` WHERE agent_id = #{tenantId}) " +
            "or tenant_id = #{tenantId} or is_permit = 1 " +
            "${ew.customSqlSegment}")
    Page<VideoEntity> getPageByAgent(Integer tenantId, Page<VideoEntity> page, @Param("ew") Wrapper<VideoEntity> wrapper);

    //老师取视频
    @Select("SELECT * FROM kz_history ${ew.customSqlSegment}")
    Page<VideoEntity> getPageByTeacher(Page<VideoEntity> page, @Param("ew") Wrapper<VideoEntity> wrapper);

    //同步视频信息到其他表
    @Update("UPDATE kz_practice t1,kz_video t2 SET t1.video_name=t2.video_name,t1.video_url=t2.video_url," +
            "t1.cover_img_url=t2.cover_img_url,t1.dance_type=t2.dance_type,t1.belongs_to=t2.belongs_to," +
            "t1.duration=t2.duration " +
            "WHERE t2.id = #{id} and t1.video_id=t2.id;")
    void syncPracticeById(Integer id);

    @Update("UPDATE kz_practice t1,kz_video t2 SET t1.video_name=t2.video_name," +
            "t1.video_url=t2.video_url,t1.cover_img_url=t2.cover_img_url," +
            "t1.dance_type=t2.dance_type,t1.belongs_to=t2.belongs_to,t1.duration=t2.duration " +
            "WHERE t1.video_id=t2.id;")
    void syncAllPractice();

    @Update("UPDATE kz_plan_video t1,kz_video t2 SET t1.video_name=t2.video_name," +
            "t1.video_url=t2.video_url,t1.cover_img_url=t2.cover_img_url," +
            "t1.dance_type=t2.dance_type,t1.belongs_to=t2.belongs_to,t1.duration=t2.duration " +
            "WHERE t1.video_id=t2.id;")
    void syncAllPlanVideo();

    @Update("UPDATE kz_plan_video t1,kz_video t2 SET t1.video_name=t2.video_name," +
            "t1.video_url=t2.video_url,t1.cover_img_url=t2.cover_img_url," +
            "t1.dance_type=t2.dance_type,t1.belongs_to=t2.belongs_to,t1.duration=t2.duration " +
            "WHERE t2.id = #{id} and t1.video_id=t2.id;")
    void syncPlanVideoById(Integer id);

    @Update("update kz_video set uuid = concat('O', tenant_id,'-V',id);")
    void updateUUID();


    @Select("SELECT * FROM kz_video where id = #{id} limit 1")
//    @Cacheable(cacheManager = "cacheManager", cacheNames = "videos", key = "#id")
    VideoEntity queryById(Integer id);
}
