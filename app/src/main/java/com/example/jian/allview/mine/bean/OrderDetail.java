package com.example.jian.allview.mine.bean;

/**
 * Created by jian on 2017/11/24.
 */

public class OrderDetail {


    /**
     * consumeTime : {"date":25,"day":6,"hours":14,"minutes":59,"month":10,"seconds":44,"time":1511593184000,"timezoneOffset":-480,"year":117}
     * coverURL : static/img/banner-index-1.jpg
     * hotelID : 1
     * id : 1
     * message : 温馨旅社-游客房
     * orderName : 温馨旅社-游客房
     * orderNum : 1
     * orderStatus : 1
     * ordertype : 1
     * payTime : {"date":18,"day":6,"hours":14,"minutes":59,"month":10,"seconds":42,"time":1510988382000,"timezoneOffset":-480,"year":117}
     * roomID : 1
     * scenicID : 0
     * total : 320
     * userID : 1
     */

    private ConsumeTimeBean consumeTime;
    private String coverURL;
    private int hotelID;
    private int id;
    private String message;
    private String orderName;
    private int orderNum;
    private int orderStatus;
    private int ordertype;
    private ConsumeTimeBean payTime;
    private int roomID;
    private int scenicID;
    private int total;
    private int userID;

    public ConsumeTimeBean getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(ConsumeTimeBean consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public ConsumeTimeBean getPayTime() {
        return payTime;
    }

    public void setPayTime(ConsumeTimeBean payTime) {
        this.payTime = payTime;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getScenicID() {
        return scenicID;
    }

    public void setScenicID(int scenicID) {
        this.scenicID = scenicID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public static class ConsumeTimeBean {
        /**
         * date : 25
         * day : 6
         * hours : 14
         * minutes : 59
         * month : 10
         * seconds : 44
         * time : 1511593184000
         * timezoneOffset : -480
         * year : 117
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
