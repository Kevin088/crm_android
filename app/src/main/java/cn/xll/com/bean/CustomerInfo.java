package cn.xll.com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/27.
 */
public class CustomerInfo {

    /**
     * msg :
     * obj : [{"address":"asdfasdf","broadband":1,"broadbandEndTime":"20190101","broadbandPrice":222.22,"broadbandSatisfy":1,"datetime":1286691631000,"districtName":"安平1","district_id":1,"id":1,"isBroadbandFusion":1,"iscompute":1,"name":"asdf","telephone":"1223323","tv":1,"tvEndTime":"20190101","tvPrice":22.01,"tvSatisfy":1,"userName":"admin","username_id":1},{"address":"33","broadband":1,"broadbandEndTime":"1212","broadbandPrice":33,"broadbandSatisfy":1,"datetime":1262275200000,"districtName":"安平1","district_id":1,"id":3,"isBroadbandFusion":1,"iscompute":1,"name":"33","telephone":"33","tv":1,"tvEndTime":"11221","tvPrice":21,"tvSatisfy":1,"userName":"admin","username_id":1}]
     * pageCount : 1
     * success : true
     */

    private String msg;
    private int pageCount;
    private boolean success;
    /**
     * address : asdfasdf
     * broadband : 1
     * broadbandEndTime : 20190101
     * broadbandPrice : 222.22
     * broadbandSatisfy : 1
     * datetime : 1286691631000
     * districtName : 安平1
     * district_id : 1
     * id : 1
     * isBroadbandFusion : 1
     * iscompute : 1
     * name : asdf
     * telephone : 1223323
     * tv : 1
     * tvEndTime : 20190101
     * tvPrice : 22.01
     * tvSatisfy : 1
     * userName : admin
     * username_id : 1
     */

    private List<ObjBean> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        private String address;
        private int broadband;
        private String broadbandEndTime;
        private double broadbandPrice;
        private int broadbandSatisfy;
        private long datetime;
        private String districtName;
        private int district_id;
        private int id;
        private int isBroadbandFusion;
        private int iscompute;
        private String name;
        private String telephone;
        private int tv;
        private String tvEndTime;
        private double tvPrice;
        private int tvSatisfy;
        private String userName;
        private int username_id;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getBroadband() {
            return broadband;
        }

        public void setBroadband(int broadband) {
            this.broadband = broadband;
        }

        public String getBroadbandEndTime() {
            return broadbandEndTime;
        }

        public void setBroadbandEndTime(String broadbandEndTime) {
            this.broadbandEndTime = broadbandEndTime;
        }

        public double getBroadbandPrice() {
            return broadbandPrice;
        }

        public void setBroadbandPrice(double broadbandPrice) {
            this.broadbandPrice = broadbandPrice;
        }

        public int getBroadbandSatisfy() {
            return broadbandSatisfy;
        }

        public void setBroadbandSatisfy(int broadbandSatisfy) {
            this.broadbandSatisfy = broadbandSatisfy;
        }

        public long getDatetime() {
            return datetime;
        }

        public void setDatetime(long datetime) {
            this.datetime = datetime;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsBroadbandFusion() {
            return isBroadbandFusion;
        }

        public void setIsBroadbandFusion(int isBroadbandFusion) {
            this.isBroadbandFusion = isBroadbandFusion;
        }

        public int getIscompute() {
            return iscompute;
        }

        public void setIscompute(int iscompute) {
            this.iscompute = iscompute;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public int getTv() {
            return tv;
        }

        public void setTv(int tv) {
            this.tv = tv;
        }

        public String getTvEndTime() {
            return tvEndTime;
        }

        public void setTvEndTime(String tvEndTime) {
            this.tvEndTime = tvEndTime;
        }

        public double getTvPrice() {
            return tvPrice;
        }

        public void setTvPrice(double tvPrice) {
            this.tvPrice = tvPrice;
        }

        public int getTvSatisfy() {
            return tvSatisfy;
        }

        public void setTvSatisfy(int tvSatisfy) {
            this.tvSatisfy = tvSatisfy;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUsername_id() {
            return username_id;
        }

        public void setUsername_id(int username_id) {
            this.username_id = username_id;
        }
    }
}
