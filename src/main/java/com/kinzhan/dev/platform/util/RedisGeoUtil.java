package com.kinzhan.dev.platform.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisGeoUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StringRedisTemplate getRedisTemplate() {
        return this.redisTemplate;
    }

    /**
     *
     * @param key
     * @param point
     * @param member
     * @return
     */
    public Long geoAdd(String key, Point point, String member) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForGeo().remove(key, member);
        }
        return redisTemplate.opsForGeo().add(key, point, member);
    }

    /**
     * 查找指定key的经纬度信息，可以指定多个member，批量返回
     *
     * redis命令：geopos key 北京
     */
    public List<Point> geoGet(String key, String... members) {
        return redisTemplate.opsForGeo().position(key, members);
    }

    /**
     * 返回两个地方的距离，可以指定单位，比如米m，千米km，英里mi，英尺ft
     *
     * redis命令：geodist key 北京 上海
     */
    public Distance geoDist(String key, String member1, String member2, Metric metric) {
        return redisTemplate.opsForGeo().distance(key, member1, member2, metric);
    }

    /**
     * 根据给定的经纬度，返回半径不超过指定距离的元素
     *
     * redis命令：georadius key 116.405285 39.904989 100 km WITHDIST WITHCOORD ASC
     * COUNT 5
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByXY(String key, Circle circle, long count) {
        // includeDistance 包含距离
        // includeCoordinates 包含经纬度
        // sortAscending 正序排序
        // limit 限定返回的记录数
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        return redisTemplate.opsForGeo().radius(key, circle, args);
    }

    /**
     * 根据指定的地点查询半径在指定范围内的位置
     *
     * redis命令：georadiusbymember key 北京 100 km WITHDIST WITHCOORD ASC COUNT 5
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByPlace(String key, String member, Distance distance,
                                                                        long count) {
        // includeDistance 包含距离
        // includeCoordinates 包含经纬度
        // sortAscending 正序排序
        // limit 限定返回的记录数
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        return redisTemplate.opsForGeo().radius(key, member, distance, args);
    }


    public static void main(String[] args) {
        //cityId=420000&driverId=000001&lng=114.366386&lat=30.408199
        // 将骑手添加到Redis中
//        String redisKey = CommonUtil.buildRedisKey(GEO_KEY, cityId);
//        String redisKey = "geo_key";
//        Long addnum = geoAdd(redisKey, new Point(lng, lat), riderId);
//        List<Point> points = geoGet(redisKey, riderId);
//        System.out.println("添加位置坐标点：" + points);
//
//        //查询附近的骑手
//        Circle circle = new Circle(lng, lat, Metrics.KILOMETERS.getMultiplier());
//        GeoResults<RedisGeoCommands.GeoLocation<String>> results = nearByXY(redisKey, circle, 5);
//        System.out.println("查询附近司机位置：" + results);
//        List<DriverPosition> list = new ArrayList<>();
//        results.forEach(item -> {
//            GeoLocation<String> location = item.getContent();
//            Point point = location.getPoint();
//            DriverPosition position = DriverPosition.builder().cityCode(cityId).driverId(location.getName())
//                    .lng(point.getX()).lat(point.getY()).build();
//            list.add(position);
//        });
//
//        return list;

        //通过经度，纬度查找附近的人
//        Circle circle = new Circle(116.48105, 39.996794, Metrics.KILOMETERS.getMultiplier());
//        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(50);
//        GeoResults<RedisGeoCommands.GeoLocation<Object>> byxy = redisTemplate.opsForGeo().radius("home", circle, args);
//        System.out.println("通过经纬度附近的人：" + byxy);
//
////通过地方查找附近5km的2个人
//        RedisGeoCommands.GeoRadiusCommandArgs args2 = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(2);
//        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius = redisTemplate.opsForGeo().radius("home", "张三", new Distance(5, Metrics.KILOMETERS),args2);
//        System.out.println("通过名字附近的人：" + radius);


        //注意：GeoHash对二维经纬度坐标进行一维映射是有损的，通过映射再还原回的经纬度坐标和原始输入的经纬度坐标存在一定的误差。
//        List<Point> position = redisTemplate.opsForGeo().position("home", "张三");
//        System.out.println(position);
//        List<Point> positions = redisTemplate.opsForGeo().position("home", "张三", "李四");
//        System.out.println(positions);
    }
}
