# 👩‍👦‍👦 협업 도구 설정

## 💾 GitLab

- 커밋 기록까지 병합하는 방법(추후 Git hub에 잔디심기할 때 용이)
  ```
  git remote -v
  git remote remove origin
  ```
  remote 가 올리는 경로를 설정하는 명령어
  
  remote 를 Sub3로 변경해서 기존의 저장소를 Sub3로 옮겨버림(이번 프로젝트는 하지 않음)
  ```
  git remote add origin https://lab.ssafy.com/s03-final/s03p31a407.git
  git push origin develop
  ```

## 🔨JIRA

- 연동
  - Jira - mattermost 연동 방법입니다
  `/jira connect`
  `/jira subscribe`
- filtering view 설정
  board -> configure -> quick filters

## 📣 MatterMost

- GitLab - MM 연동
  - `통합기능`
  - incoming webhook 주소 복사
  - mm notification -> 입력


# 🌏 AWS

- server : ubuntu 18.04
  - 서버가 하나의 노트북이라고 생각하면 됩니다.
- hostname : k3a407.p.ssafy.io
- 서버 접속

  - [윈도우 10 에서 pem 파일을 사용하기 – 아마존 라이트세일 리눅스 서버에 접속하는 방법(SSH)](https://swiftcoding.org/lightsail-from-window10)
  - [OpenSSH 클라이언트 설치 확인](https://m.blog.naver.com/PostView.nhn?blogId=alice_k106&logNo=220882708567&proxyReferer=https:%2F%2Fwww.google.com%2F)
  - `ssh -i ubuntu@k3a407.p.ssafy.io`  
    (이전 프로젝트 예시)
    ```
    $ ssh -i I3A310T.pem ubuntu@i3a310.p.ssafy.io
    load pubkey "I3A310T.pem": invalid format
    The authenticity of host 'i3a310.p.ssafy.io (52.78.99.106)' can't be established.
    ECDSA key fingerprint is SHA256:7kmpFDLXVPVWEYBnJ7GjaTUwh9EL7VkturtGkeoljwM.
    Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
    Warning: Permanently added 'i3a310.p.ssafy.io,52.78.99.106' (ECDSA) to the list of known hosts.
    Connection closed by 52.78.99.106 port 22
    ```
  - [리눅스 서버 root와 사용자, 그리고 CLI 명령어 실행 시 주의사항](https://swiftcoding.org/remind-of-cli-commands)
  - 서버 시간 설 정

  - (추가) mobaxterm  
    - putty, xshell, SecureCRT와 비슷한 툴(윈도우 환경에서 용이)
    - 사용 이유: 한번 등록하면 서버 접속이 편함, 여러개의 쉘 틀어두고 이용 , 드래그 앤 드랍 파일 전송
    - 사용법
      - mobaxterm 설치후 창 열기 -> Session -> SSH 
      - Remote host: ubuntu@k3a407.p.ssafy.io(호스트명)
      - port: 22(지정된 포트번호)
      - Advanced ssh settings -> use private key 체크 -> pem키 등록


- Docker
  - 설치
    - `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
    - `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
    - `sudo apt-get update && sudo apt-get install docker-ce`
- Maria DB
  - user : root
  - password : 비밀번호(정해준 비밀번호)
  - 생성
    - `docker run --name maria-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=비밀번호?XpA_G -d mariadb`
  - 접속
    - `docker exec -it maria-db mysql -u root -p`

- git clone
  ```
  ubuntu@ip-172-26-2-101:~/sub-pjt2-2$ git clone https://lab.ssafy.com/s03-final/s03p31a407.git
  ```
- 프론트

  - nginx로 http에 요청을 보내면 tomcat
    - node.js 설치 [[참고]](https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/)
      - npm 사용을 위해서
      - `node --version`
      - `npm --version`
  - global 환경설정
    - yarn 설치 [[참고]](https://linuxize.com/post/how-to-install-yarn-on-ubuntu-18-04/)
      - `yarn --version`
    - vue-cli 설치
      - `yarn global add @vue/cli`
    - ~~vue 설치~~
      - `npm install vue`
      - `vue -version`
  - `/frontend`에서 환경설정
    - 통합명령어 - `yarn install`
      - `yarn add node-sass sass-loader && yarn add vue-router vuex && yarn add vue-session && yarn add vue bootstrap-vue bootstrap && yarn add axios && yarn add firebase && yarn add proj4`
    - sass-loader 설치
      - `yarn add node-sass sass-loader`
    - vuex 설치
      - `yarn add vue-router vuex`
    - vue session 설치
      - `yarn add vue-session`
    - bootstrap 설치
      - `yarn add vue bootstrap-vue bootstrap`
    - axios 설치
      - `yarn add axios`
    - firebase 설치
      - `yarn add firebase`
    - 좌표변경
      - `yarn add proj4`
    - (추가)
      - `yarn install`을 하면 아마 해당 프로젝트에 필요한건 다 설치 될 것
  - build
    - `yarn build` 또는
    - `npm run build`
  - nginx
    - 설치
      - `sudo apt-get update && sudo apt-get upgrade && sudo apt-get install nginx`
    - dist 경로 생성후 root경로로 설정해주기.
    - nginx에서 자동으로 설정한 경로를 임의로 바꿔줘야함
    - `sudo vim /etc/nginx/sites-available/default`  
    (이전 프로젝트 예시)  
    ```
    ubuntu@ip-172-26-1-226:~/a310/sub_pjt2_3_develop/frontend/dist$ pwd
    /home/ubuntu/a310/sub_pjt2_3_develop/frontend/dist
    ```

    -(추가) default 설정 예시
      ```
      // 프런트앤드 위치
      root /home/ubuntu/app/swim/workspace/forfresh/frontend/dist/;

        index index.html index.htm index.nginx-debian.html;
        server_name _;
        location / {
                try_files $uri $uri/ /index.html;
        }
        // 백엔드
        location /api {
                proxy_pass http://localhost:8080/api/;
                proxy_redirect off;
                charset utf-8;

                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header X-NginX-Proxy true;
        }

      ```
      - 프록시를 바꿔준다고 생각하면 된다. 예를 들어 클라이언트는 k3a407.p.ssafy.io/api/main 으로 메인 화면을 가고 싶다고 요청하면
      nginx로 서버측의 localhost:8080/api/main을 호출하게 해준다는 것이다.

    - nginx 재시작 (dist 경로에서하기)
      - `sudo systemctl restart nginx`
  - SSL 설정(맨 밑에 추가)

    - [참고](https://velog.io/@pinot/Ubuntu-18.04%EC%97%90%EC%84%9C-Lets-Encrypt%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Nginx%EC%97%90-SSL%EC%9D%84-%EC%A0%81%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)

    ```
    IMPORTANT NOTES:
    - Congratulations! Your certificate and chain have been saved at:
    /etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem
    Your key file has been saved at:
    /etc/letsencrypt/live/i3a310.p.ssafy.io/privkey.pem
    Your cert will expire on 2020-11-18. To obtain a new or tweaked
    version of this certificate in the future, simply run certbot again
    with the "certonly" option. To non-interactively renew *all* of
    your certificates, run "certbot renew"
    - If you like Certbot, please consider supporting our work by:

    Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
    Donating to EFF:                    https://eff.org/donate-le

    ```

    ```
    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    new certificate deployed with reload of nginx server; fullchain is
    /etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem
    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    ** DRY RUN: simulating 'certbot renew' close to cert expiry
    **          (The test certificates below have not been saved.)

    Congratulations, all renewals succeeded. The following certs have been renewed:
    /etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem (success)
    ** DRY RUN: simulating 'certbot renew' close to cert expiry
    **          (The test certificates above have not been saved.)
    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    IMPORTANT NOTES:
    - Your account credentials have been saved in your Certbot
    configuration directory at /etc/letsencrypt. You should make a
    secure backup of this folder now. This configuration directory will
    also contain certificates and private keys obtained by Certbot so
    making regular backups of this folder is ideal.

    ```

- 백엔드(Spring)

  - global 환경설정
    - maven 설치
      - `sudo apt install maven`
  - build(backend에서)
    - `mvn clean package`
      ```
      ubuntu@ip-172-26-2-101:~/sub-pjt2-2/feature-userpage/backend$ cd target
      ubuntu@ip-172-26-2-101:~/sub-pjt2-2/feature-userpage/backend/target$ ls
      classes                 maven-archiver  webcuration-0.0.1-SNAPSHOT.jar
      generated-sources       maven-status    webcuration-0.0.1-SNAPSHOT.jar.original
      generated-test-sources  test-classes
      ```
      - `mvn clean package`
        - 에러남 무슨차이?
    - 백엔드 서버 가동 : 내부의 tomcat 서버 가동
      - `.jar` 파일이 있는 `target`경로에 들어가서
      - `sudo java -jar webcuration-0.0.1-SNAPSHOT.jar &`
  - PM2

    - [PM2란?](https://cheese10yun.github.io/PM2/)
      - 앱에서 충돌이 발생할 경우 앱을 자동으로 다시 시작
      - 런타임 성능 및 자원 소비에 대한 통찰력을 획득
      - 성능 향상을 위해 설정을 동적으로 수정
      - 클러스터링을 제어
    - [documentation](https://pm2.keymetrics.io/docs/usage/pm2-doc-single-page/)
    - 설치
      - `npm install pm2 -g`
    - 설정

      - app.json 설정 (backend & frontend 와 같은 레벨에 생성)
        -(추가) args의 주소 값만 jar파일 위치로 지정해준다
        ```
        {
        	"apps": [{
        		"name": "tenten",
        		"cwd": ".",
        		"script": "java",
        		"args": [
        			"-jar",
        			"/home/ubuntu/dist/server/forfresh-0.0.1-SNAPSHOT.jar"
        		],
        		"env": {
        		},

        		"node_args": [],
        		"log_date_format": "YYYY-MM-DD HH:mm Z",
        		"exec_interpreter": "none",
        		"exec_mode": "fork"
        	}]
        }
        ```

      - 자동으로 `java -jar /...경로.../forfresh-SNAPSHOT.jar`를 실행하게 설정

    - 시작
      - `pm2 start app.json`
    - 웹 뷰 지원
      - `pm2 plus`

  - WAS(Tomcat8 설치)

- 백엔드(django)설치

  - python 설치
    - https://aliwo.github.io/swblog/linux/ubuntu/ubuntu-new-python/#
  - pip3 설치
    - `sudo apt install python3-pip`
  - django 설치
    - `pip install django~=2.2.7`
  - requirements.txt
    - `sudo pip install -r requirements.txt --user`
  -(추가) freeze
    - pip freeze는(requirements 내용을 출력해줘서 복붙하면 편하게 requirements.txt 제작이 가능하다)
  - ❗Error❗
    - `ImportError: cannot import name 'main'` 에러 시  
      - ✍️ pip를 업데이트 한 후에 문제가 계속 발생, 일단 pip를 지우고 다시 설치하면 해결
      - `sudo python3 -m pip uninstall pip && sudo apt-get install python3-pip --reinstall`
    - `tensorboard 1.14.0 has requirement setuptools>=41.0.0, but you'll have setuptools 40.6.2 which is incompatible.`
      - 버젼오류같음...  

  -(추가) tensorflow
    - 만약 aws 서버에서 tesensorflow 2.0은 잘 작동하지 않는다. 이전 프로젝트에서 이 부분 때문에 팀원이 tensorflow 모델을 적용하지 못한것이다. 이를 해결하기 위한 두가지 방법이 있다면

    - 1.  **가상 환경을 설치**하여 돌린다. 추천하는 방법이자, 포멀한 방법이다. 가상 환경에 설치하는 것이므로 GPU에 대해서도 걱정할 필요가 없다(ex. 쥬피터). 하지만 세팅이 너무 어렵다 ... 구글링과 문서 잘읽고 시도해보자

    - 2.  **pip upgrade** 일단 좋은 방법은 아닌것 같지만 .. 간단해서 이 방식으로 진행하였다. pip 버전을 업데이트 하면 tesensorflow 2.0 이상의 버전으로 설치가 가능하며 작동이 된다.

      
- 백엔드(Django)

  - ❌ `python manage.py runserver 0:8082`
    - local 에서 test server 용이지, 배포용은 아니다. django 내장 server를 사용해서 배포하면 안된다
  - Gunicorn 설치
    - requirement.txt에 `gunicorn==19.7.1` 추가
    - `pip install -r requirements.txt --user`
  - 실행
	screen 이란?  
	screen은 여러 프로세스(일반적으로 대화식 shell을 가리킴)간의 물리적 터미널을 다중화하는 전체 화면 창 관리자  
	한마디로,  screen에서는 세션을 끊고 나서도 원래 사용하고 있던 세션에서 시작된 프로세스는 계속 실행(동시에 다른작업 가능)  
    - `screen`입력 후 enter  
      ✍️ screen 은 서버 연결을 꺼도, 실행 중이던 프로그램을 그대로 유지, screen에서 실행한다
    - ⭕️ `gunicorn forfresh.wsgi:application --bind=0:8082 --reload `  
      ✍️ --reload: 소스코드가 바뀌면 재기동  
  - 확인
	- `screen -ls`로 현재 스크린의 상태와 포트번호 확인
	-  screen 내부를 확인하고 싶으면 `screen -r`, 특정 screen `screen -r 스크린ID`   
  - stop
    - `pgrep -f gunicorn`로 gunicorn 포트번호 확인
    - `sudo kill -9 포트번호`
    - `screen -ls`
    - `screen -X -S 포트번호 quit`
  -(추가) /home/ubuntu/categoryModel/
    - 제작한 tokenList(tokenizer.pickkle)와 카테고리 분류 모델(best_model.h5)을 넣어둔 폴더.(경로 설정만 잘해주자)
    - 두 파일에 대해서는 read.me 참고

- 젠킨스(CI/CD) 관리

  ✅ 참고 자료: Jenkins를 이용한 CI_CD.pdf( 실습코치 이수영,김윤재 )  

  - 젠킨스를 설정 해준다면 위처럼 별도로 git clone을 받아 직접 해줄 필요가 없음
  - default 설정, yarn과 pip install 등등의 특정 부분만 잘 세팅해준다면 젠킨스가 알아서 pull받아서 build함(장고 제외, 직접해줌)

  - Docker로 jenkins 설치하고 실행하기
    - `sudo docker pull jenkins/jenkins:lts`
  - jenkins 컨테이너 실행
    - `sudo docker run -d -p 7070:8080 -v /home/ubuntu/app/swim:/var/jenkins_home --name swim_jenkins -u root jenkins/jenkins:lts`  
      -d 백그라운드로 실행  
      -p 호스트 7070포트와 도커 네트워크 상의 8080포트를 연결(이미 8080 사용중이라 임의 변경)  
      -v 호스트의 파일 시스템과 도커 컨테이터 파일 시스템 연결(/app/swim 디렉터리에/var/jekins_home을 마운트시킨다)  
      --name 도커 컨테이너 이름 지정 (여기서는 swim_jenkins)  
      -u 사용자를 root로 지정
  - jenkins컨테이너 작동 확인
    - `sudo docker ps -a`
  - jenkins 웹 페이지 초기 세팅
    - `http://k3a407.p.ssafy.io:7070` 으로 접근한다
      - 비밀번호를 알아낸다
    - `sudo docker exec -it swim_jenkins bash`
    - `cat /var/lib/secrets/initialAdminPassword`
      - 나온 비밀번호를 입력한다
    - `exit`
  - CI/CD를 적용할 아이템 등록

    - 새로운 Item 생성 : forfresh

  - Gitlab과 연결을 위한 플러그인 설치

    - jenkins 관리 > 플러그인 관리 > 설치가능 들어가서 Publish Over SSH 설치

    - Key 파일 지정
      - 주어진 pem key(K3A407T)를 메모장으로 키면 Key값 확인 가능
      ```
      -----BEGIN RSA PRIVATE KEY-----
      ....
      -----END RSA PRIVATE KEY-----
      ```
    - ssh server 설정

      - hostname과 username 등록
      - `hostname: k3a407.p.ssafy.io, Username: ubuntu`

    - Gitlab 플러그인 설치 및 설정
      - jenkins 관리 > 플러그인 관리 > 설치가능 에 들어가서 GitLab Plugin 설치
    - Gitlab 지정

      - `Connection name, Connection host Url, Credentials` 작성
      - url은 연결하고 싶은 Git(ex `https://lab.ssafy.com`)
      - Credentials를 Add 해준다

    - Add Credentials

      - `Kind: GitLab API token`
      - `API token: Gitlab API`는 로그인한 다음에 User Settings > Access Tokens에 들어가서
        발급받은 토큰 작성
      - Id, Description은 각자 알아서
      - 완료 후, 깃랩과 연동 확인(Test) --> Success
      - (추가) ❌API token 오류
        - `API token: Gitlab API`으로 인증이 안되는 경우가 있다. 그리고 해결을 못했다...
        - 그래서 다른 방식을 사용하였다. `Username with password`는 해당 프로젝트에 권한이 있는 ID의 id,pass만으로 접근가능
        - 더 간단하고 쉬운 방법이지만 id,pass를 맡기는 방식이라 조금 아쉽기는 하다.

    - 소스 코드 관리 설정하기  
      ✍️ pull 땡겨 올 Repository를 등록
      - forfresh > 구성 > 소스 코드 관리
      - `Repository URL: https://lab.ssafy.com/s03-final/s03p31a407.git`
      - Credentials는 위의 방식과 동일하게 작성
      - `Branch Specifier: develop` (원하는 branch설정 가능)
    - 빌드 유발 설정하기  
      ✍️ webhook 시그널을 받고 빌드할 수 있도록 트리거 설정
      - `Build when a change is pushed to GitLab webhook URL : .....` 을 체크
    - Gitlab 시크릿 토큰값 설정하기  
      ✍️ 빌드 유발에서 고급 설정에서 시크릿 토큰값을 생성( GitLab webhook 설정에 필요)
      - Secret token -> Generate

  - Webhook 지정하기  
    ✍️ gitlab에 push event가 생기면 jenkins로 시그널을 보내줘야한다  
    자동으로 push 이벤트를 감지하고 신호를 보낼 수 있도록 gitlab webhook을 지정
    - gitlab 로그인 후 >> setting > integrations
    - 위에서 만든 `URL, Secret Token` 입력하여 웹훅 지정
  - 빌드 플러그인 설정

    - 빌드를 위한 플러그인 설치
      - NodeJS 플러그인을 설치(현 프로젝트에서는 안쓰는 듯 하다)
      - maven 플러그인을 설치
      - jenkins 관리 > Global Tool Configuration 에 들어가서 nodejs와 maven 버전을 설치
      - nodejs는 LTS 버전인 12.18.3, maven은 3.6.3
    - 빌드 환경 설정하기

      - forfresh > 구성 에서 빌드환경을 추가(설치한 것을 등록)

    - Pull 받아오기 테스트
      - Build Now 를 클릭하여 빌드가 되는지 확인
      - 원하는 브랜치가 잘있는지 확인
      - Docker volume 설정한 /app/swim/workspace를 확인한다

  - 빌드하기  
    ✍️ 이전까지는 pull만 받아온 것, 이제는 빌드할 차례

    - forfresh > 구성 > 빌드 에서 Execute Shell 항목 추가

      - Frontend Build

        - `cd frontend` (해당 frontend 위치는 다를 수 있음)  
          `npm install -g yarn`  
          `yarn install`  
          `yarn build `

      - Backend Build (mvn 백엔드 배포)

        - invoke top-Level Maven targets 항목 추가  
          `Maven Version: mvn 3.6.3`  
          `Goals: clean package`
        - invoke top-Level Maven targets > 고급
        - `POM:backend_spring/pom.xml`(해당 pom 위치는 다를 수 있음)

  - 빌드 후 조치하기  
    ✍️ 빌드하고 나서 실행할 명령어를 설정  
    SSH로 AWS EC2에 접근해서 빌드된 파일을 지정한 곳으로 이동하고 배포
    - 빌드 후 조치 > send build artifacts over SSH
      - `Source files: backend_spring/target/forfresh-0.0.1-SNAPSHOT.jar`  
        Soucre files: 배포할 파일
      - `Remove prefix: backend_spring/target`  
        Remove prefix: 제거할 접두사
      - `Remote directory: dist/server`  
        Remote directory: 배포할 파일이 저장될 디렉토리를 지정 (없으면 새로 생성 X, 미리 만들기)
      - `Exec command: sudo pm2 restart /home/ubuntu/dist/server/app.json`  
        Exec command : 배포 후 실행 할 명령어를 입력 (pm2 실행)

  -(추가) 그 외 
    - 빌드 오류가 나면 대부분은 구글링으로 문제해결은 가능하다
    - 보통은 버전 오류, install이 안되있거나, 경로 , permisson 오류가 대부분이다.
    - 해당 설정은 프로젝트의 브런치가 push을 하던간에, pull을 받고 재배포한다.(kn으로 push해도, develop을 pull하고 배포한다. 아무관련이 없지만)
    - develop에 올릴때는 서버 side로 경로를 재조정해서 올리자(master로 설정해두었다면 master에 올릴때 재조정하자)

- https 자동설정 
  ✅ 참고 사이트: https://certbot.eff.org/

  - certbot instructions > My HTTP website is running `Nginx on Ubuntu 16.04 (xenial)`

  ```
  sudo snap install core; sudo snap refresh core
  sudo snap install --classic certbot
  sudo ln -s /snap/bin/certbot /usr/bin/certbot
  sudo certbot --nginx
  ```

  이메일 입력 후, 설정 완료하면 https를 사용할 수 있다(Base Url 유의)
