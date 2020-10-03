FROM arm64v8/openjdk:8u212
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
VOLUME /usr/local/logs/
ADD canteen-1.0-SNAPSHOT.jar app.jar
# spring.profiles.active指定启动环境
#-c为清除以前启动的数据
#方法1
ENTRYPOINT ["java","-Xmx512m","-jar","/app.jar","--spring.profiles.active=dev","-c"]