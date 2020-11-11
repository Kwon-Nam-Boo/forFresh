from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
from . import ocr
from . import categoryModel
import ast
# Create your views here.

@api_view(['GET'])
def getItemInfo(request, foodName):
    # json_data = ocr.ocr()
    # food_list = ocr.firstStep(json_data)
    # food_list = ocr.deleteComma(food_list)
    # food_list = ocr.twoLineBill(food_list)
    # food_list = ocr.concat(food_list)
    # ocrList = ocr.delete(food_list)
    # ocrList = ocr.makeList(ocrList)
    # data = list(categoryModel.categorization(ocrList))
    data="잘넘어갔음크크크크"
    print(data)
    {"data": data}
    try:
        return JsonResponse({"data":data}, safe=False, json_dumps_params={'ensure_ascii': False})
    except:
        return JsonResponse({
            "fff":111
        })

@api_view(['POST'])
def getReceiptInfo(request):
    receiptUrl=request.body.decode('utf-8')
    json_data = ocr.ocr(receiptUrl)
    food_list = ocr.firstStep(json_data)
    food_list = ocr.deleteComma(food_list)
    food_list = ocr.twoLineBill(food_list)
    food_list = ocr.concat(food_list)
    ocrList = ocr.delete(food_list)
    ocrList = ocr.makeList(ocrList)
    #data = list(categoryModel.categorization(ocrList))
    try:
        return JsonResponse({"data":ocrList}, safe=False, json_dumps_params={'ensure_ascii': False})
    except:
        return JsonResponse({
            "fff":111
        })

@api_view(['POST'])
def getCategoryInfo(request):
    ocrList = ast.literal_eval(request.body.decode('utf-8'))
    data = list(categoryModel.categorization(ocrList))
    try:
        return JsonResponse({"data":data}, safe=False, json_dumps_params={'ensure_ascii': False})
    except:
        return JsonResponse({
            "fff":111
        })
