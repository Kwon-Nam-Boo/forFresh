## 목차
- [개요](#개요)  
- [기능](#기능) 
- [유사 서비스](#유사-서비스) 
- [향후 전망](#향후-전망)
- [기술 스택](#기술-스택)
- [기술 설명](#기술-설명)
	- [Vue](#Vue)
	- [ERD](#erd)
	- [PICKLE](#pickle)
	- [팀별 개발표준](#팀별-개발표준)
- [테스트 방법](#테스트-방법)
- [디렉토리 구조도](#디렉토리-구조도)

## 데이터


## ✋개요
> 영수증 사진을 OCR API로 텍스트 데이터로 변환해 냉장고 식재료를 잘 활용할 수 있게 관리해주는 서비스 


## ⭐기능
-	회원관리
-	OCR API를 이용한 영수증 디지털화
-	AI를 통한 데이터 검증
-	식품 카테고리 분류
-	냉장고 식자재 관리
-	냉장고 공유
-	상품구매
-	장바구니


## 🛠사용 알고리즘

## ⚽유사 서비스
> - 라이프스타일 어플리케이션인 ‘거꾸로 레시피’와 ‘더나은 냉장고’가 있음
> - 특징 : 
    - 사용자의 냉장고 속 식재료를 직접 저장해두고 조합 가능한 레시피를 제공.
    - 또한 식재료의 유통기한, 식재료 개수 등과 같은 식재료 관리 서비스도 눈여겨볼 만합니다.
> - 문제점: 
    - 식자재 등록을 직접 해야 한다는 문제점이 있음. 
## 🤷‍♂️향후 전망
> 2025년까지 보급을 목표로 하는 RFID(radio frequency identifier, ID정보를 포함시칸 결제방법)을 이용해 편의점의 식품을 관리하는 시스템이 도입 예정입니다.
> 영수증 대신 캐시리스 결제가 진행되면 무엇을 구입했는지 쉽게 알 수 있습니다. 
> 처음에는 RFID의 코스트와 도입을 위한 오퍼레이션을 생각하면 편의점만의 대응인 것 같지만 마트에서도 보급되게 되면, 유통기한에 영향이 큰 신선식품에 대응할 수 있어 냉장고 식재료의 관리 시스템과 같은 프로젝트의 고도화와 최적화로 저욱 주목 받게 될 것입니다.
## 기술 스택
<img src="/uploads/e19f0ca0dbb86a65efeaf316708d779b/기술스택.jpg"  width="700" height="370">


## 기술 설명
### OCR API
-   네이버 CLOVA OCR활용 (참고 링크 <a href="https://www.ncloud.com/product/aiService/ocr">https://www.ncloud.com/product/aiService/ocr</a>)
###	OCR Process
-	단순한 OCR(광학 문자 인식)을 넘어 문서의 종류를 분류하고, 구조화된 형태로 정확히 문자 정보를 추출합니다.
고객이 이미지 데이터(사진)를 RESTful API 방식으로 전달하면, 해당 이미지를 분석하여 텍스트 추출 결과를 JSON 형태로 제공합니다

### Vue


### ERD
<img src="/uploads/6cd703254dbd62591d2d493b6f7da1af/ERD.PNG"  width="700" height="370">

### Pickle

### 팀별 개발표준

-	Naming Rule
	-	[Camel case](https://www.geeksforgeeks.org/java-naming-conventions/)
		- 여러 단어인지 한 단어인지 구분이 안갈땐 space를 넣어보고 판단한다.
	-	request url에는 소문자만

-	💾 Git
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


## 테스트 방법
> 프로젝트를 배포한 url과 테스트하기 위한 계정 ID/PW를 적어주세요

## 데이터 정제
>	프로젝트를 배포한 url과 테스트하기 위한 계정 ID/PW를 적어주세요


### 디렉토리 구조도
> 폴더 구조가 어떻게 되는지 폴더, 파일별 역할들을 간략하게 적어주세요  
> 너무 자세히 적을 필요는 없습니다
