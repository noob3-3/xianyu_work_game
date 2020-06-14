# xianyu_work_game

### 获取数据方法

##### 脑筋急转弯获取

```java
示例
public void getData() {
    Brain_Teasers_Data z = Brain_Teasers_Data.Get_Brain_Teasers_Data();
    List<Brain_Teasers_Data.ResultBean.ListBean> b=z.getResult().getList();
    for (int i=0;i<b.size();i++)
    {
        System.out.println( b.get( i ).getContent() );#问题
        System.out.println( b.get( i ).getAnswer() );#答案
    }
}
```
##### 笑话获取

```java
示例
public void getData() {
    Joke_Data z = Joke_Data.Get_Joke_Data();
    List<Joke_Data.ResultBean.ListBean> b = z.getResult().getList();
    for (int i=0;i<b.size();i++)
    {
        System.out.println( b.get( i ).getContent());
    }
}
```

##### 谜语

```java
示例
public void getData() {
    Riddle_Data z = Riddle_Data.Get_Riddle_Data();
    List<Riddle_Data.ResultBean.ListBean> b = z.getResult().getList();
    for (int i=0;i<b.size();i++)
    {
        System.out.println( b.get( i ).getContent() );#题目
        System.out.println( b.get( i ).getAnswer() );#答案
    }
}
```
