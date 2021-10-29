package www.test.com.medical_system.bean;

import java.io.Serializable;
import java.util.List;

import androidx.work.Data;

/**
 * Created by adamDeng
 * on 2021-09-22
 */


public class Heal implements Serializable {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    /**
     * code : 200
     * msg : success
     * newslist : [{"id":"a2ca152c70af423444ce1362a94e997e","ctime":"2021-09-22 11:45","title":"疾控中心：建议旅行归来自我观察14天","description":"建议个人充分权衡出行时机和必要性，避免非必要的跨境旅行。如非必要，建议暂时不要前往境内中高风险地区。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0918/3642de18j00qzmgtn000mc0009c0070c.jpg?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0918/16/GK6MC06A00388045.html"},{"id":"f6a23d4646c0eda87a034bf8df994c4e","ctime":"2021-09-22 11:45","title":"哈尔滨新增确诊3例，一人轨迹涉2省3地","description":"国家卫生健康委已派出工作组，赴哈尔滨指导疫情处置工作。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0922/32d4c9d4j00qzt3a6000oc0009c0070c.jpg?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0922/06/GKFT2CVJ0038804U.html"},{"id":"8843222e02f1bbe98aed77d6d6616ab5","ctime":"2021-09-16 23:00","title":"\u201c双节\u201d如何出行？个人防控应注意五点","description":"入境口岸、定点医疗机构等高风险岗位的工作人员，提倡避免假期期间到外地旅游，也避免乘坐飞机、列车和长途公共汽车外出。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0916/3a0199a4p00qzj5om001ac0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0916/22/GK233U820038804U.html"},{"id":"3152e056ec31254e3a28cdbf9e334682","ctime":"2021-09-15 23:00","title":"秘鲁研究：接种疫苗者感染新冠症状更轻","description":"调查显示，接种新冠疫苗的人不太可能被感染，死亡率也低很多。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0915/0fb0e0a2p00qzhdfh0014c0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0915/22/GJVJN8NU0038804U.html"},{"id":"deefa048f403777ef02da3d7410fec57","ctime":"2021-09-15 07:00","title":"新版全员核酸检测组织实施指南发布","description":"《指南》表明，500万人口以上城市应在3天内完成全员核酸检测。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0915/16c7d4e6p00qzg3og001ec0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0915/06/GJTR5I2S0038804U.html"},{"id":"abe96673bffa723b94e5683a384e5d5b","ctime":"2021-09-14 00:00","title":"9次核酸、38天才确诊,隔离期要加长吗？","description":"专家分析，延长集中隔离时间可在一定程度上降低病毒传播风险，但随着病毒不断变异，依然无法排除遇到长期病毒携带者的极端情况。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0913/c9a2ffc5p00qzdd76001dc0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0913/23/GJQHFVJU0038804U.html"},{"id":"af9b24ad2885a0eefbe8ee43673e355e","ctime":"2021-09-13 01:00","title":"专家释疑:为何多轮检测仍会出现阴转阳?","description":"多次核酸检测才检出阳性或与检测时体内病毒量的多少、采样方式是否正确以及个体差异等相关。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0912/581b36bfp00qzbvyz001hc0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0912/23/GJO050AP00388045.html"},{"id":"9e6643465b3adc25d5ef11245ca4cdec","ctime":"2021-09-09 23:00","title":"犬咬伤怎么办？早期伤口处理很重要","description":"犬咬伤是动物致伤中最为常见的类型，除了非特异性感染外，还可引起狂犬病、破伤风、气性坏疽等特殊感染。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0909/50710442p00qz5wad000vc0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0909/22/GJG3KJLT0038804U.html"},{"id":"2365da97ad91e4d88ddbc5cb414424ba","ctime":"2021-09-09 09:00","title":"陕西自然灾害受灾人口达442.48万人次","description":"今年以来，全国暴雨、强对流天气频发，在多省(区)造成不利影响。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0908/0d1d1948p00qz4ec5000zc0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0909/07/GJEHRKQU0038804U.html"},{"id":"393b309ee91f4c92c2040b9908eb1c72","ctime":"2021-09-08 01:00","title":"有过敏史不宜打疫苗？疾控专家教你判断","description":"不是打完疫苗之后出现的症状、体征都认为和疫苗有关系。","source":"网易健康","picUrl":"http://cms-bucket.ws.126.net/2021/0907/22b93ae2p00qz26uq000uc0009c0070c.png?imageView&thumbnail=150y100","url":"https://jiankang.163.com/21/0908/00/GJB4ALP30038804U.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;


    public static class NewslistBean implements Serializable {
        /**
         * id : a2ca152c70af423444ce1362a94e997e
         * ctime : 2021-09-22 11:45
         * title : 疾控中心：建议旅行归来自我观察14天
         * description : 建议个人充分权衡出行时机和必要性，避免非必要的跨境旅行。如非必要，建议暂时不要前往境内中高风险地区。
         * source : 网易健康
         * picUrl : http://cms-bucket.ws.126.net/2021/0918/3642de18j00qzmgtn000mc0009c0070c.jpg?imageView&thumbnail=150y100
         * url : https://jiankang.163.com/21/0918/16/GK6MC06A00388045.html
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String ctime;
        private String title;
        private String description;
        private String source;
        private String picUrl;
        private String url;
    }
}
