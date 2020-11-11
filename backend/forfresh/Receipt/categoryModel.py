import os
import operator
import pickle
from tensorflow.keras.models import load_model

def categorization(item):
    f_list = ['돼지고기', '쇠고기', '닭고기', '알류', '축산가공식품', '생선', '김/해초','젓갈','건어물','아이스크림/빙수','냉동/간편조리식품','쌀', '잡곡/혼합곡', '과일', '채소','견과류','반찬','김치','음료','차류','커피','유제품','과자','가공식품'] 
    # model = load_model('C:/ssafy/test1/real/best_model.h5')
    # os.chdir("C:/ssafy/test1/real") 
    # with open('tokenizer.pickle', 'rb') as handle:
    model = load_model('/home/ubuntu/categoryModel/best_model.h5')
    #os.chdir("C:/ssafy/test1/real") 
    with open('/home/ubuntu/categoryModel/tokenizer.pickle', 'rb') as handle:
        tokenizer = pickle.load(handle)
    #word_to_index = tokenizer.word_index
    #print(word_to_index)

    for x in item:
        l = [x['foodName']]
        t = tokenizer.texts_to_sequences(l)
        if(len(t[0])== 0):
            x['category'] = '미분류'
            x['status'] = '상온'
        else:
            index, value = max(enumerate(model.predict(t)[0]), key=operator.itemgetter(1))
            x['category'] = f_list[index]
            # 냉동, 아이스크림의 경우
            if(index == 10 or index == 9):
                x['status'] = '냉동'
            # 쌀, 잡곡의 경우
            elif(index == 11 or index == 12 or index == 15 or index == 19 or index == 20 or index ==22 or index ==23):
                x['status'] = '상온'
            else:
                x['status'] = '냉장'
    return item   
