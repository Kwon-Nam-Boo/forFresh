## 목차

- [개요](#개요)
- [기능](#기능)
- [데이터](#데이터)
- [유사 서비스](#유사-서비스)
- [향후 전망](#향후-전망)
- [기술 스택](#기술-스택)
- [기술 설명](#기술-설명)
  - [OCR](#Vue)
  - [데이터정제](#데이터정제)
  - [FRONT](#FRONT)
  - [ERD](#ERD)
  - [BACKEND(SPRING)](<#BACKEND(SPRING)>)
  - [BACKEND(DJANGO)](<#BACKEND(DJANGO)>)
- [팀별 개발표준](#팀별-개발표준)
- [디렉토리 구조도](#디렉토리-구조도)

## ✋개요

> 영수증 사진을 OCR API로 텍스트 데이터로 변환해 냉장고 식재료를 잘 활용할 수 있게 관리해주는 서비스

1. git & jira

```
 - jira - https://jira.ssafy.com/projects/S03P31A407
 - git - https://lab.ssafy.com/s03-final/s03p31a407
```

2. 배포정보

```
 WebApp - http://k3a407.p.ssafy.io/
 test 가능한 계정/패스워드 - admin@ssafy.com / admin123
```

3. 프로젝트 제목

```
 - forfresh
```

## ⭐기능

- User 관리 , 이메일 인증
<div>
	<img width="190" src="/uploads/0c03d3ee389e317388cc78da12799445/image.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img width="190" src="/uploads/cdae07a1f87ce955bdebd9a2d3985555/image.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img width="190" src="/uploads/09f3dd12dd7103d0a794dbd0c71d6c64/image.png">
</div>

- JWT
- 냉장고 영수증 OCR기술 활용 물품 데이터화
<div>
<img width="190" src="/uploads/16989aa98f1de63533ec42487be1994c/캡처.PNG">
</div>

- 기계학습을 통한 영수증 물품 카테고리 분류
<div>
<img width="190" src="/uploads/c9d8801a0518f66d399d5a3de80ae653/image.png">
</div>

- 개인 냉장고 등록, 삭제

<div>
<img width="190" src="/uploads/2ec06dc58d2aefb198f4b5a56e49fe4c/image.png">
</div>

- 냉장고 공유

<div>
<img width="190" src="/uploads/73acf33460757b9921d1a6653d2c16b2/image.png
">
</div>

- 냉장고 식품 등록, 삭제
<div>
<img width="190" src="/uploads/9d11a8fb791894a0d1b87b87d009dfb3/image.png
">
</div>

- 식품 상세페이지 제공
<div>
<img width="190" src="/uploads/15ec5a18229ecde7b324acf01f99cf0f/image.png
">
</div>

- 상품 신선도 정보 제공 및 알림
- 식품 상세페이지 제공
<div>
<img width="190" src="/uploads/6aab5ecd33eae07540d617c760f74f79/image.png
">
</div>

- 상품 등록
<div>
<img width="190" src="/uploads/2b8872e66a447a971ab657c984cd1ec6/image.png
">
</div>

- 상품 구매, 결제
<div>
<img width="190" src="/uploads/310bf26c6aa4449889f132ebfb95ead3/image.png
">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img width="190" src="/uploads/12870df18e69d61df76b2973e8c6605f/image.png
">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img width="190" src="/uploads/dc6b8443b16e0d5ec3819fc7295a2f5a/image.png
">
</div>

- 장바구니 페이지 제공
<div>
<img width="190" src="/uploads/e5aa7088d37b0c58a46878bccd97cf34/image.png
">
</div>

- 상품 상세페이지 제공
- 상품 상세페이지 내 댓글 등록 수정 삭제
<div>
<img width="190" src="/uploads/6efaa2c7da1d02695c6754a6c9b6aed3/image.png
">
</div>

## 데이터

- 네이버 쇼핑 크롤링
- 파이썬
- 텐서플로우 keras

## ⚽유사 서비스

> - 라이프스타일 어플리케이션인 ‘거꾸로 레시피’와 ‘더나은 냉장고’가 있음
> - 특징 :
>   사용자의 냉장고 속 식재료를 직접 저장해두고 조합 가능한 레시피를 제공.
>   또한 식재료의 유통기한, 식재료 개수 등과 같은 식재료 관리 서비스도 눈여겨볼 만합니다.
> - 문제점:
>   식자재 등록을 직접 해야 한다는 문제점이 있음.

- 차별점
  >     OCR API를 이용해 영수증 문자를 데이터화 해 개인 냉장고에 저장합니다.
  >
  > 장보기 기능을 통해 쉽게 식자재를 구매할 수 있습니다.

## 🤷‍♂️향후 전망

> 2025년까지 보급을 목표로 하는 RFID(radio frequency identifier, ID정보를 포함시칸 결제방법)을 이용해 편의점의 식품을 관리하는 시스템이 도입 예정입니다.
> 영수증 대신 캐시리스 결제가 진행되면 무엇을 구입했는지 쉽게 알 수 있습니다.
> 처음에는 RFID의 코스트와 도입을 위한 오퍼레이션을 생각하면 편의점만의 대응인 것 같지만 마트에서도 보급되게 되면, 유통기한에 영향이 큰 신선식품에 대응할 수 있어 냉장고 식재료의 관리 시스템과 같은 프로젝트의 고도화와 최적화로 저욱 주목 받게 될 것입니다.

## 기술 스택

<img src="/uploads/e19f0ca0dbb86a65efeaf316708d779b/기술스택.jpg"  width="700" height="370">

```
 Front : Vue, Vuetify.js, Firebase, JavaScript, CSS, HTML
 Back : Spring, Django, Python, Tesor flow keras, Naver OCR API, AWS EC2, MySQL
          Docker, PM2
 관리 : Jenkis (CI/CD) , git , Jira
```

## 기술 설명

### OCR API

- 네이버 CLOVA OCR활용 (참고 링크 <a href="https://www.ncloud.com/product/aiService/ocr">https://www.ncloud.com/product/aiService/ocr</a>)

### OCR Process

- 단순한 OCR(광학 문자 인식)을 넘어 문서의 종류를 분류하고, 구조화된 형태로 정확히 문자 정보를 추출합니다.
  고객이 이미지 데이터(사진)를 RESTful API 방식으로 전달하면, 해당 이미지를 분석하여 텍스트 추출 결과를 JSON 형태로 제공합니다.

### 데이터 정제 (python, tesorflow keras 등)

- OCR 데이터 정제
  OCR 데이터에는 **두가지 이슈**가 존재했다.  
  1. OCR이 화질, 글자의 폰트, 정확성, 모호한 값(ex. 1,l,I) 등의 이유로 **부정확한 데이터**  
  2. **다양한 영수증**의 종류에 따라 영수증에 따라 **너무 다른 규칙성**(쿠폰 허용, 데이터 두 줄 출력) 
 
  이에 최대한의 정제 과정을 거칠수 있도록 개발하였다. 데이터 정제는 다음과 같은 순서로 진행된다.(ocr.py 파일)

  **1. firstStep(json_data)**  
    - 상품명(품명) 이후로 인식한 글자와 해당 좌표를 딕셔너리로 뽑아준다.(그 이전값은 필요없기 때문)  
    - 이후 좌표로 단어 데이터의 높이를 계산하여 높이의 좌표가 유사한 단어들을 한줄로 출력한다(같은 줄)  
    - 즉, 해당 step은 **상품리스트를 한줄씩 출력해주는 출발지점**이다  

  **2. deleteComma(food_list)**  
    - 해당 데이터들에 들어갈 수 있는 **특수문자를 제거**해 준다(, @ * · (괄호) 등의 특수문자) 

  **3. twoLineBill(food_list)**  
    - **두줄로 상품데이터를 출력하는 영수증데이터를 한줄로 재정렬**한다  
    - 해당 줄이 모두 숫자일경우나 해당줄의 마지막이 숫자가 아닌경우는 이러한 두줄 영수증의 형태를 가질 확률이 높다  

  **4. concat(food_list)**
    - 쪼개져있는 상품데이터를 **하나의 상품명 합친다**(문자열을 모은다)  

  **5. delete(food_list)**
    - **불필요한 데이터를 제거**한다(상품데이터 이외의 값)

  **6. makeList(ocrList)**
    - **상품명, 수량, 단가의 리스트화** 시킨다
    - 만약 데이터의 누수가 있을 경우 default값을 출력(수량 default 1)  

- 식품데이터 정제
  - 모든 데이터를 하나의csv파일로 과정사이에 데이터를 정제한다
  - 해당 데이터들에 들어갈 수 있는 **특수문자, 단위를 제거**해 준다
  - 중복된 상품데이터 제거 (**670만개 -> 31만개**)

### 데이터 크롤링
- 카테고리 분류 모델 제작을 위한 데이터 크롤링
  - 네이버 쇼핑, 상품 데이터를 추출
  - 네이버 쇼핑 API로 추출한 Json파일에서, 상품데이터를 뽑아낸다(crawler.py)  
> 데이터

| 카테고리               | 페이지 | 데이터수 |
| ------------------ | -------- | ---------|
| 돼지고기           | 1~2000  | 약 8만     |
| 쇠고기             | 1~4000  | 약 17만    |
| 닭고기             | 1~1000  | 약 2.5만개 |
| 알류               | 1~1000  | 약 2.5만개 |
| 축산가공식품       | 1~12000 | 약 50만개  |
| 생선               | 1~6800   | 약 30만개 |
| 김/해초            | 1~6800   | 약 30만개 |
| 해산물             | 1~4700   | 약 20만개 |
| 젓갈               | 1~2700   | 약 13만개 |
| 건어물             | 1~4200   | 약 19만개 |
| 아이스크림         | 1~3000   | 약 14만개 |
| 냉동               | 1~12000  | 약 50만개 |
| 쌀                 | 1~3000   | 약 14만개 |
| 잡곡/혼합곡         | 1~3000   | 약 14만개 |
| 과일               | 1~4000   | 약 17만개 |
| 채소               | 1~4000   | 약 17만개 |
| 견과류             | 1~6800   | 약 30만개 |
| 반찬               | 1~6800   | 약 30만개 |
| 김치               | 1~4000   | 약 17만개 |
| 음료               | 1~20000  | 약 76만개 |
| 차류               | 1~6800   | 약 30만개 |
| 커피               | 1~6800   | 약 30만개 |
| 유제품             | 1~7850   | 약 36.6만 |
| 과자               | 1~10000  | 약 44만개 |
| 가공식품           | 1~12400  | 약 58.6만개|
| 모든 식품          |          | 총 670만개 |

### LSTM 네트워크 모델
- 상품명을 통해 카테고리 분류  
  - **정제한 25개의 카테고리 데이터를 셔플**하여 csv 파일로 생성(finalShuffle.csv)
  - Tensorflow Keras에서 **LSTM 네트워크 모델**을 사용
    - **LSTM 모델이란?**
    - **RNN 인공신경망 모델의 일종**으로 , 순환구조로 학습시켜 순차적인 데이터(단어 학습)에 용이함
    - RNN은 단어사이의 간격(ex. i am .... a ....big...french 에서 i와 french)이 너무 멀면 가중치가 저하해 학습효과가 급격히 떨어짐
    - **LSTM은 내부에 cell-state를 사용**하여, 이런 **단점을 보완**한 네트워크 모델이므로 채택하였고 실제로 loss가 감소한것을 확인

  - **학습 과정(lstmModel.ipynb)**
    1. 각 상품의 **카테고리를 index**로 교체(ex 돼지고기 -> 0, 쇠고기 -> 1)  

    2. 상품명을 tokenizer로 토큰화하여, **단어를 숫자값, 인덱스로 변환하여 저장**(해당 list는 모델이 학습하고, 작동하는데 필요)  

    3. **학습시킬 데이터, 테스트할 데이터**로 구분(8.5 : 1.5 비율로 나누었음)  

    4. 전체 데이터셋의(상품명) 길이는 최대 max에 맞춰둔다  

    5. to_categorical 함수로 입력받은 (n) 크기의 **1차원 정수 배열을 (n, k) 크기의 2차원 배열로 변경**  

    6. **학습 시킬 모델 생성**, Embedding()을 사용하여 임베딩 층(embedding layer) 추가, **LSTM 네트워크 추가**, 카테고리 수 25의 Dense를 가진 software 함수 설정(Softmax(소프트맥스)는 입력받은 값을 출력으로 0~1사이의 값으로 모두 정규화하며 출력 값들의 총합은 항상 1이 되는 특성을 가진 함수)  

    7. **최적화 알고리즘은 Adam**을 사용하여 모델을 컴파일한다  

    8.  batch_size(한번에 학습시킬 크기), epoch(학습 횟수)를 설정하여 **학습**시킨다.(총 158분 소요)

    9. 2번에서 만들어둔 **토큰리스트와 모델을 저장**해서 사용(각각 tokenizer.pickle, best_model.h5의 형태로 저장, doc폴더에 넣어둠. 사용하고 싶으면 해당 폴더로 경로를 지정하여 사용)

### front (vue,vuetify, javascript, http, css, firebase 등)

- Firebase 
    - 두 가지의 사진 파일을 firebase storage에 업로드 해야했음.
      처리하는 과정에서 비동기적 특성으로 인해 DB에 반환 받은 URL 값을 넣는데 문제가 발생
      이를 해결하기 위해 async await 활용, 문법적인 문제로 정상적으로 동작되지 않는 문제가 발생
      이는 promise를 적용해 해결함

### ERD

<img src="/uploads/a2dc42f5aa276afec002c70d057722e9/ERD.PNG"  width="700" height="370">

- 각 유저별 상품에 대한 장바구니 등록 여부와 상품별 댓글 개수를 count하기 위해 세 개의 테이블을 조인 했음
  장바구니 등록 여부는 장바구니를 등록한 것과 안한 것 모두를 표현하기 위해 left outer join 을 사용.

### backend(spring)

- JPA
  - 여러개의 테이블을 조인하고 반환한 결과값을 매핑하기 위해 DTO에 테이블 관계를 표현하는 어노테이션을 기입하는게 일반적임
  하지만 학습이 필요해 다른 번거롭지만 쉬운 방법을 택함
  projection을 활용했음. select 문에서 반환할 컬럼을 하나하나 interface내 변수로 정의하는 방식임.
  하지만 이 마저도 모든 데이터를 매핑하는데 한계가 있음 향후 발전과제로 어노테이션 학습이 필요함.
- JWT
  - Front의 navbar 컴포넌트가 모든 페이지에 들어있는 특성을 이용해 각 페이지가 새로고침 될 때 마다 header 정보와 함께 axios get 요청을 보냄
  interceptor가 이를 확인해 인증이 되면 서비스를 이용할 수 있고 그렇지 않으면 세션이 만료되었다는 메세지를 출력하여 로그인 페이지로 이동.
- KakaoPay API
  - 결제 정보를 front로부터 받아 kakaopay api를 활용해 요청을 보냄. test 사업자 번호를 이용해 test결제를 이용함
- Email 인증
  - 중복 email여부를 확인 하고 6자리의 난수를 생성해 해당 email주소로 mail 발송하고 front로 생성된 난수를 반환함
  front에서 해당 난수와 사용자가 입력한 인증번호가 일치한지 여부를 확인하여 회원가입 절차를 진행

### backend(django)

- 장고는 두 가지 용도로 사용된다(views.py)
  - **getReceiptInfo(request)**: OCR 데이터 추출 및 정제하여 response
  - **getCategoryInfo(request)**: 상품명에 따른 카테고리 분류 및 저장 방법 설정하여 response

### 팀별 개발표준

- Naming Rule

  - [Camel case](https://www.geeksforgeeks.org/java-naming-conventions/)
    - 여러 단어인지 한 단어인지 구분이 안갈땐 space를 넣어보고 판단한다.
  - request url에는 소문자만

- 💾 Git
  - Commit
    - {Resolved | } IssueID | description
    - description : 기능(english) 작업 내용(한글 명사형) 끝내기
  - Merge request
    - Title : title - 재량껏
    - Description : IssueID | description
  - Branch Handling
    - conflict를 최소화 하는 방향으로 진행.
    - 최대한 비슷한 branch들끼리 병합 후에(account, feed 따로) -> develop 에 merge
    - 최종 서버 업로드는 master branch 사용.
      - 개발 중인 상태에서는 develop branch 사용.
  - Branch naming
    - `master -> develop -> <developer-name>`
    - developer별로 개발 기능을 잘 나누면 점검이 용이하다.
    - 충돌 최소화
  - Issue
    - gitlab -> [issues 탭](https://lab.ssafy.com/s03-final/s03p31a407/issues) 활용

### 디렉토리 구조도

```
📦backend
┗ 📂forfresh
┃ ┣ 📂forfresh
┃ ┃ ┣ 📂**pycache**
┃ ┃ ┃ ┣ 📜settings.cpython-36.pyc
┃ ┃ ┃ ┣ 📜urls.cpython-36.pyc
┃ ┃ ┃ ┣ 📜wsgi.cpython-36.pyc
┃ ┃ ┃ ┗ 📜**init**.cpython-36.pyc
┃ ┃ ┣ 📜asgi.py
┃ ┃ ┣ 📜settings.py
┃ ┃ ┣ 📜urls.py
┃ ┃ ┣ 📜wsgi.py
┃ ┃ ┗ 📜**init**.py
┃ ┣ 📂Receipt
┃ ┃ ┣ 📂migrations
┃ ┃ ┃ ┣ 📂**pycache**
┃ ┃ ┃ ┃ ┗ 📜**init**.cpython-36.pyc
┃ ┃ ┃ ┗ 📜**init**.py
┃ ┃ ┣ 📂**pycache**
┃ ┃ ┃ ┣ 📜admin.cpython-36.pyc
┃ ┃ ┃ ┣ 📜categoryModel.cpython-36.pyc
┃ ┃ ┃ ┣ 📜categoryModel.cpython-38.pyc
┃ ┃ ┃ ┣ 📜models.cpython-36.pyc
┃ ┃ ┃ ┣ 📜ocr.cpython-36.pyc
┃ ┃ ┃ ┣ 📜ocr.cpython-38.pyc
┃ ┃ ┃ ┣ 📜test.cpython-38.pyc
┃ ┃ ┃ ┣ 📜urls.cpython-36.pyc
┃ ┃ ┃ ┣ 📜views.cpython-36.pyc
┃ ┃ ┃ ┗ 📜**init**.cpython-36.pyc
┃ ┃ ┣ 📜admin.py
┃ ┃ ┣ 📜apps.py
┃ ┃ ┣ 📜categoryModel.py
┃ ┃ ┣ 📜models.py
┃ ┃ ┣ 📜ocr.py
┃ ┃ ┣ 📜tests.py
┃ ┃ ┣ 📜urls.py
┃ ┃ ┣ 📜views.py
┃ ┃ ┗ 📜**init**.py
┃ ┣ 📜db.sqlite3
┃ ┣ 📜manage.py
┃ ┗ 📜requirements.txt

📦backend_spring
┣ 📂.mvn
┃ ┗ 📂wrapper
┃ ┃ ┣ 📜maven-wrapper.jar
┃ ┃ ┣ 📜maven-wrapper.properties
┃ ┃ ┗ 📜MavenWrapperDownloader.java
┣ 📂.settings
┃ ┣ 📜org.eclipse.core.resources.prefs
┃ ┣ 📜org.eclipse.jdt.core.prefs
┃ ┣ 📜org.eclipse.wst.common.project.facet.core.xml
┃ ┗ 📜org.springframework.ide.eclipse.prefs
┣ 📂src
┃ ┣ 📂main
┃ ┃ ┣ 📂java
┃ ┃ ┃ ┗ 📂com
┃ ┃ ┃ ┃ ┗ 📂forfresh
┃ ┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SwaggerConfig.java
┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MailController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShareController.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtInterceptor.java
┃ ┃ ┃ ┃ ┃ ┣ 📂model
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dao
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kakaopay
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentListDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MailDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MailDaoImpl.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂product
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductCommentDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShoppingListDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂refrig
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpirationDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodlistDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigRegistDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefrigShareDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDao.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kakaopay
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmountVO.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CardVO.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayApprovalVO.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayReadyVO.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentList.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TotalPayment.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂product
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Product.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductComment.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductCommentPass.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShoppingList.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂refrig
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Expiration.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodItem.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Foodlist.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Refrig.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigRegist.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShare.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShareId.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RequestDelFood.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Idpass.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BasicResponse.java
┃ ┃ ┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodlistService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayService.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂util
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MailHandler.java
┃ ┃ ┃ ┃ ┃ ┗ 📜ComForfreshApplication.java
┃ ┃ ┗ 📂resources
┃ ┃ ┃ ┗ 📜application.properties
┃ ┗ 📂test
┃ ┃ ┗ 📂java
┃ ┃ ┃ ┗ 📂com
┃ ┃ ┃ ┃ ┗ 📂forfresh
┃ ┃ ┃ ┃ ┃ ┗ 📜ComForfreshApplicationTests.java
┣ 📂target
┃ ┣ 📂classes
┃ ┃ ┣ 📂com
┃ ┃ ┃ ┗ 📂forfresh
┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┗ 📜SwaggerConfig.class
┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┣ 📜FoodController.class
┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayController.class
┃ ┃ ┃ ┃ ┃ ┣ 📜MailController.class
┃ ┃ ┃ ┃ ┃ ┣ 📜ProductController.class
┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigController.class
┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShareController.class
┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.class
┃ ┃ ┃ ┃ ┣ 📂interceptor
┃ ┃ ┃ ┃ ┃ ┗ 📜JwtInterceptor.class
┃ ┃ ┃ ┃ ┣ 📂model
┃ ┃ ┃ ┃ ┃ ┣ 📂dao
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kakaopay
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaymentListDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MailDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MailDaoImpl.class
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂product
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductCommentDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShoppingListDao$ShopListProduct.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShoppingListDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂refrig
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpirationDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodlistDao$FoodlistExpiration.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodlistDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigRegistDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefrigShareDao.class
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDao.class
┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kakaopay
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmountVO.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CardVO.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayApprovalVO.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayReadyVO.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentList.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TotalPayment.class
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂product
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Product.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductComment.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductCommentPass.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShoppingList.class
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂refrig
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Expiration.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FoodItem.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Foodlist.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Refrig.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigRegist.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShare.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefrigShareId.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RequestDelFood.class
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Idpass.class
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.class
┃ ┃ ┃ ┃ ┃ ┗ 📜BasicResponse.class
┃ ┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┃ ┣ 📜FoodlistService.class
┃ ┃ ┃ ┃ ┃ ┣ 📜JwtService.class
┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoPayService.class
┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.class
┃ ┃ ┃ ┃ ┣ 📂util
┃ ┃ ┃ ┃ ┃ ┗ 📜MailHandler.class
┃ ┃ ┃ ┃ ┗ 📜ComForfreshApplication.class
┃ ┃ ┣ 📂META-INF
┃ ┃ ┃ ┣ 📂maven
┃ ┃ ┃ ┃ ┗ 📂com.forfresh
┃ ┃ ┃ ┃ ┃ ┗ 📂forfresh
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜pom.properties
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜pom.xml
┃ ┃ ┃ ┗ 📜MANIFEST.MF
┃ ┃ ┗ 📜application.properties
┃ ┗ 📂test-classes
┃ ┃ ┗ 📂com
┃ ┃ ┃ ┗ 📂forfresh
┃ ┃ ┃ ┃ ┗ 📜ComForfreshApplicationTests.class
┣ 📜.classpath
┣ 📜.gitignore
┣ 📜.project
┣ 📜mvnw
┣ 📜mvnw.cmd
┣ 📜pom.xml
┗ 📜Readme.md

- frontend
  📦src
  ┣ 📂api
  ┃ ┣ 📜AlarmApi.js
  ┃ ┣ 📜FoodApi.js
  ┃ ┣ 📜PaymentApi.js
  ┃ ┣ 📜ProductApi.js
  ┃ ┣ 📜RefApi.js
  ┃ ┗ 📜UserApi.js
  ┣ 📂assets
  ┃ ┣ 📂advimg
  ┃ ┃ ┣ 📜advertise1.jpg
  ┃ ┃ ┣ 📜advertise2.png
  ┃ ┃ ┣ 📜advertise3.jpg
  ┃ ┃ ┣ 📜advertise4.jpg
  ┃ ┃ ┣ 📜advertise5.jpg
  ┃ ┃ ┣ 📜advertise6.jpg
  ┃ ┃ ┗ 📜advertise7.jpg
  ┃ ┣ 📂css
  ┃ ┃ ┗ 📜main.scss
  ┃ ┣ 📂img
  ┃ ┃ ┣ 📜isnoitem.png
  ┃ ┃ ┗ 📜tofu.png
  ┃ ┣ 📜camera.png
  ┃ ┣ 📜fridge.png
  ┃ ┣ 📜logo.png
  ┃ ┣ 📜logo.svg
  ┃ ┣ 📜payment_icon_yellow_small.png
  ┃ ┣ 📜slide1.png
  ┃ ┣ 📜slide2.png
  ┃ ┣ 📜slide3.png
  ┃ ┣ 📜slide4.png
  ┃ ┗ 📜slide5.png
  ┣ 📂components
  ┃ ┣ 📂payment
  ┃ ┃ ┗ 📜Paytest.vue
  ┃ ┣ 📂product
  ┃ ┃ ┣ 📜AddProduct.vue
  ┃ ┃ ┣ 📜Advertise.vue
  ┃ ┃ ┣ 📜BottomNavigation.vue
  ┃ ┃ ┣ 📜CategoryList.vue
  ┃ ┃ ┣ 📜ProductCategoryItem.vue
  ┃ ┃ ┣ 📜ProductComment.vue
  ┃ ┃ ┣ 📜ProductHome.vue
  ┃ ┃ ┣ 📜QnaInfoForm.vue
  ┃ ┃ ┗ 📜RecommendFood.vue
  ┃ ┣ 📜AddRefrigerator.vue
  ┃ ┣ 📜DeleteFood.vue
  ┃ ┣ 📜DeleteRefrigerator.vue
  ┃ ┣ 📜EditRefrigerator.vue
  ┃ ┣ 📜NavBar.vue
  ┃ ┣ 📜Refrigerator.vue
  ┃ ┣ 📜SharedRefrigerator.vue
  ┃ ┗ 📜ShareRefrigerator.vue
  ┣ 📂plugins
  ┃ ┗ 📜vuetify.js
  ┣ 📂views
  ┃ ┣ 📂error
  ┃ ┃ ┣ 📜Error.vue
  ┃ ┃ ┗ 📜PageNotFound.vue
  ┃ ┣ 📂payment
  ┃ ┃ ┗ 📜PaymentSuccess.vue
  ┃ ┣ 📂product
  ┃ ┃ ┣ 📜BuyPage.vue
  ┃ ┃ ┣ 📜ProductCategory.vue
  ┃ ┃ ┣ 📜ProductDetail.vue
  ┃ ┃ ┣ 📜ProductMain.vue
  ┃ ┃ ┣ 📜ProductSearch.vue
  ┃ ┃ ┗ 📜ShoppingList.vue
  ┃ ┣ 📂user
  ┃ ┃ ┣ 📜FindPassWord.vue
  ┃ ┃ ┣ 📜Join.vue
  ┃ ┃ ┗ 📜Login.vue
  ┃ ┣ 📜Detail.vue
  ┃ ┣ 📜Home.vue
  ┃ ┣ 📜Post.vue
  ┃ ┗ 📜Welcome.vue
  ┣ 📂vuex
  ┃ ┣ 📜actions.js
  ┃ ┣ 📜Doughnut.js
  ┃ ┣ 📜getters.js
  ┃ ┣ 📜mutations.js
  ┃ ┗ 📜store.js
  ┣ 📜App.vue
  ┣ 📜main.js
  ┗ 📜routes.js
```
