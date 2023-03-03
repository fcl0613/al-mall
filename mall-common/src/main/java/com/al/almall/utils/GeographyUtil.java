package com.al.almall.utils;

public class GeographyUtil {

    /**
     * 找到一个中心点的四个经纬度坐标
     * @param lng
     * @param lat
     * @param distance
     * @return 最小经度 最小纬度 最大经度 最大纬度
     */
    public static String[] findNeighPosition(String lng, String lat, String distance) {
        double longitude = Double.parseDouble(lng);
        double latitude = Double.parseDouble(lat);
        double dis = Double.parseDouble(distance);
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        double minLat = latitude - dlat;
        double maxLat = latitude + dlat;
        double minLng = longitude - dlng;
        double maxLng = longitude + dlng;
        String[] dataArr = {String.valueOf(minLng), String.valueOf(minLat),
                String.valueOf(maxLng), String.valueOf(maxLat)};
        return dataArr;
    }

    /**
     * 计算两个经纬度之间的距离
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double EARTH_RADIUS = 6371;
        double radiansAX = Math.toRadians(lng1); // A经弧度
        double radiansAY = Math.toRadians(lat1); // A纬弧度
        double radiansBX = Math.toRadians(lng2); // B经弧度
        double radiansBY = Math.toRadians(lat2); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值
//		System.out.println("-------"+EARTH_RADIUS * acos);
        return EARTH_RADIUS * acos; // 最终结果

    }

    public static void main(String[] args) {
//        double[] neighPosition = findNeighPosition("120.116476", "30.480355", "2");
        double distance = getDistance(120.116476, 30.480355, 120.09560530281915, 30.462368567881626);
        System.out.println(distance);
    }
}
