# 启动

- 使用idea打开整个工程，进入pom.xml，进行pom刷新，确保所需要的包都已经导入

![image-20221026132642911](https://github-1308115823.cos.ap-nanjing.myqcloud.com/images/202210261326087.png)

- 配置数据库（使用的是云服务器，数据库已经配置好，无需再配置）配置在 `application.properties`

![image-20221026132744413](https://github-1308115823.cos.ap-nanjing.myqcloud.com/images/202210261327522.png)

- 运行`SengineerApplication`主类

![image-20221026132836748](https://github-1308115823.cos.ap-nanjing.myqcloud.com/images/202210261328371.png)

- 快速查看工程中的方法 ，启动后访问 `http://localhost:8080/swagger-ui/index.html#/`

![image-20221026132811844](https://github-1308115823.cos.ap-nanjing.myqcloud.com/images/202210261328921.png)



# 选课的设计思路

## 选课

1. 通过检索获取课程数据
2. 点击选课后进行判断学生是否选过这门课
   1. 如果选过，提醒 不能重复选课
   2. 如果未选 ，进行第三步
3. 获取对应行的的课程数据和学生的数据，进行学生课程的添加
4. 选课成功

## 退选

1. 判断学生是否选过这门课
   1. 如果选过，进行第二步
   2. 未选，提醒 请先选课再进行退选
2. 从课程数据中删除学生的数据

# 测试

有部分方法没有`controller`，我已经在 test 中测试了他们的正确性

![image-20221027173531834](https://github-1308115823.cos.ap-nanjing.myqcloud.com/images/202210271735745.png)

