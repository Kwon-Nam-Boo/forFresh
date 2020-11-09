import json
import base64
import requests
import os

# 1. ocr적용한 데이터를 json형식으로 받아온다
def ocr(receiptUrl):
    # 본인의 APIGW Invoke URL
    URL = "https://fbdeed6741fc4821a3980bbb7b0d966f.apigw.ntruss.com/custom/v1/4576/b9eb5486a776fe6d124e50c85724ce6245748e8dcec0fd967e2ae275ba74e47d/general"
    KEY = "VUxMdFFLYXdibWlLTUNUQ2p2YUhXcFJObHZGQWx2Y2U="
    headers = {
        "Content-Type": "application/json",
        "X-OCR-SECRET": KEY
    }
    data = {
        "version": "V1",
        "requestId": "sample_id",  # 요청을 구분하기 위한 ID, 사용자가 정의
        "timestamp": 0,  # 현재 시간값
        "images": [
            {
                "name": "sample_image",
                "format": "jpg",
                #"data": img.decode('utf-8')
                "data": None,
                "url": receiptUrl
            }
        ]
    }
    data = json.dumps(data)
    response = requests.post(URL, data=data, headers=headers)
    json_data = json.loads(response.text)
    return json_data

# 2. 상품명 이후로 인식한 글자와 해당 좌표를 딕셔너리로 뽑아준다.
def firstStep(json_data):
    food_list = {}
    cnt = 0
    foodFlag = False
    for x in json_data["images"][0]["fields"]:
        # 해당 단어의 위 좌표, 아래 좌표
        y2 = x["boundingPoly"]["vertices"][2]['y']
        y1 = x["boundingPoly"]["vertices"][1]['y']

        x2 = x["boundingPoly"]["vertices"][1]['x']
        x1 = x["boundingPoly"]["vertices"][0]['x']
        # 상품명이라는 단어가 들어오기 전까지는 모두 무시
        if((x2-x1)*1.7 < (y2-y1)):
            continue
        if(x["inferText"] == "상품명" or x["inferText"] == "품명"):
            foodFlag = True
            # 상품 idx 에 , 해당 단어의 중앙 높이와 단어를 리스트로 저장
            food_list[cnt] = [(y2 + y1)/2, x["inferText"]]
        elif(foodFlag):
            # 같은 줄인가? 에 대한 플래그
            lineFlag = False
            for v in food_list.keys():
                # 딕셔러니를 돌면서 해당 단어의 위 좌표, 아래좌표 사이에 있다면? (같은줄!)
                if(food_list[v][0] >= y1 and food_list[v][0] <= y2):
                    lineFlag = True
                    food_list[v].append(x["inferText"])
                    del food_list[v][0]
                    food_list[v].insert(0, (y2+y1)/2)
                    break
            # 만약 딕셔너리를 돌았는데 같은줄 이없었다? (새로운 줄추가.. 딕셔너리 요소 추가)
            if(lineFlag is False):
                cnt += 1
                food_list[cnt] = [(y2+y1)/2, x["inferText"]]
    return food_list


# 3. , @ * · (괄호) 등의 특수문자 제거
def deleteComma(food_list):
    for v in food_list.keys():
        removelist = []
        for i,v2 in enumerate(food_list[v][1:]):
            x = v2.replace(",","")
            x = x.replace("@","")
            x = x.replace("*","")
            x = x.replace("·","")
            x = x.replace(".","")
            # 제거 해준값이 비어있으면 ..? 리스트 제거
            if(x ==''):
                removelist.append(i)
                continue;
            food_list[v][i+1] = x
        for r in removelist:
            del food_list[v][r+1]
            
        # 괄호값 제거 하기
        for i,v2 in enumerate(food_list[v][1:]):
            x = list(v2)
            checkBracket = False
            for x1 in x[:]:
                if(x1 == "("):
                    x.remove(x1)
                    checkBracket = True
                    continue
                if(x1 == ")"):
                    checkBracket = False
                    x.remove(x1)
                    continue
                if(checkBracket is True):
                    x.remove(x1)
            food_list[v][i+1] = ''.join(x)
    return food_list        

def twoLineBill(food_list):
    removelist = []
    for v in food_list.keys():
         del food_list[v][0]
         if(v == 0): continue
         intFlag = False
         for i,v2 in enumerate(food_list[v][:]):
                if(v2.isdigit() is False):
                    intFlag = True
                    break
         # 그줄이 모두 숫자일경우, 해당줄의 마지막이 숫자가 아닌경우
         if(intFlag is False or (v-1 != 0 and food_list[v-1][-1].isdigit() is False)):
            if(food_list[v][0].isdigit()):
                food_list[v-1].extend(food_list[v][1:])
            else:
                food_list[v-1].extend(food_list[v])
            removelist.append(v)
    #print(removelist)
    for r in removelist:
        del food_list[r]
    return food_list

# 4. 상품명 합치기(문자열을 모은다)
def concat(food_list):
    for v in food_list.keys():
        checkFirstString = False;
        stringFlag = False
        list =[]
        for i,v2 in enumerate(food_list[v][:]):
            # 숫자 값인데 그이전에 String값을 받은적이없다( 즉, 상품명을 받은적이 없을시 해당 숫자는 인덱스일 것이다)
            if(v2.isdigit() and checkFirstString is False):
                continue
            if(v2.isdigit()):
                stringFlag = False
                list.append(v2)
            # 문자열인데 시작점일 경우?
            elif(v2.isdigit() is False and stringFlag is False):
                stringFlag = True
                checkFirstString = True
                list.append(v2)
            elif(v2.isdigit() is False and stringFlag is True):
                list[-1]+=(" " + v2)
        food_list[v] = list
    return food_list

# 5. 상품명 제거, (문자열을 모은다)
def delete(food_list):
    ocrList = []
    # 첫행 제거 (상품명 or 품명)
    del food_list[0]
    for v in reversed(list(food_list.keys())):
        if(len(food_list[v])>=3):
            break
        del food_list[v]
    for v in food_list.keys():
        ocrList.append(food_list[v])
    return ocrList

def makeList(ocrList):
    item =[]
    for v in ocrList:
        dic = {}
        dic['foodName'] = v[0]
        # 분류한 한줄에 4가지 요소 이상일 경우?
        if(len(v)>=4):
            # 단가와 금액의 값이 만약 문자열이면(default값 출력)
            if(v[-1].isdigit() is False or v[-3].isdigit() is False):
                dic['price'] = 0
                dic['count'] = 1
            # 개수가 만약 문자열이거나 1~50 사이의 숫자가 아니면(default값 출력)
            elif(v[-2].isdigit() is False or (int(v[-2]) < 0 or int(v[-2]) > 51)):
                dic['price'] = int(v[-3])
                dic['count'] = 1
            else:
                dic['price'] = int(v[-3])
                dic['count'] = int(v[-2])
        #분류한 한줄에 3가지 요소 이상일경우? 개수가 빠졋다고 가정 default 1로 삽입하자
        elif(len(v)==3):
            if(v[-1].isdigit and v[-2].isdigit):
                dic['price'] = int(v[-2])
                dic['count'] = 1
        item.append(dic)
    return item