#!/usr/bin/env python
# coding: utf-8

import requests
import json
import re
import time
import pandas as pd
import os
from tqdm import tqdm
from urllib.request import urlopen
from bs4 import BeautifulSoup


# URL 로 데이터 받아오기
def crwal(foodCode, pageNum):
    url = "https://search.shopping.naver.com/search/category?catId="+ str(foodCode) +"&pagingIndex=" + str(pageNum)
    html = urlopen(url)
    return html
foodList = ['축산가공']
# ,'알류', '축산가공', '생선', '김_해초', '해산물', '젓갈','건어물'
foodCode = [50001174]
# , 50001173, 50001174 ,50001175,50001176,50001049,50001050, 50001051

# 문자열에서 데이터 뽑기
def parse(pageString):
    bsObj = BeautifulSoup(pageString, "html5lib")
    script = bsObj.find("script", {"id":"__NEXT_DATA__"}).text
    json_data = json.loads(script)
    
    for j in json_data['props']['pageProps']['initialState']['products']['list']:
        productList = []
        content_text = re.sub(r'\([^)]*\)', '', j["item"]["productTitle"])
        productList.append(content_text)
        productList.append(j["item"]["category3Name"])
        list.append(productList)

    return []

for i,y in enumerate(foodCode):
    list =[]
    for x in tqdm(range(1, 12000)):
        pageString = crwal(y,x)
        products = parse(pageString)
        dataframe = pd.DataFrame(list)
        csvName = foodList[i]
        dataframe.to_csv('C:/ssafy/test1/'+ csvName +'.csv',encoding='utf-8-sig',header=False,index =False)      
print("done")





