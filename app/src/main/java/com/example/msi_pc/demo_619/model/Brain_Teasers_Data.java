package com.example.msi_pc.demo_619.model;


import com.google.gson.Gson;

import java.util.List;

public class Brain_Teasers_Data {

    /**
     * status : 0
     * msg : ok
     * result : {"total":1,"pagenum":1,"pagesize":5,"list":[{"content":"一个没有胸部的巨大女人该怎么称呼？","answer":"巨无霸"},{"content":"一个男人加一个女人会成了什么？","answer":"两个人"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前\u201c公\u201d尽弃"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前\u201c公尽弃"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前功尽弃"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public static Brain_Teasers_Data Get_Brain_Teasers_Data()
    {
        String key = "8789d7324e904d78";
        //脑筋急转弯
        String url = "https://api.jisuapi.com/jzw/search?appkey="+key+"&pagenum=1&pagesize=15";
        String data = Server.getServerResult( url, "GET" );
        System.out.println( data );
        Gson gson = new Gson();
        Brain_Teasers_Data z = gson.fromJson(data, Brain_Teasers_Data.class );
        return z;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * total : 1
         * pagenum : 1
         * pagesize : 5
         * list : [{"content":"一个没有胸部的巨大女人该怎么称呼？","answer":"巨无霸"},{"content":"一个男人加一个女人会成了什么？","answer":"两个人"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前\u201c公\u201d尽弃"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前\u201c公尽弃"},{"content":"一个离过五十次婚的女人，应该怎么形容她?","answer":"前功尽弃"}]
         */

        private int total;
        private int pagenum;
        private int pagesize;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPagenum() {
            return pagenum;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : 一个没有胸部的巨大女人该怎么称呼？
             * answer : 巨无霸
             */

            private String content;
            private String answer;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
