from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
from . import ocr
from . import categoryModel
# Create your views here.

@api_view(['GET'])
def getItemInfo(request, foodName):
    json_data = ocr.ocr()
    food_list = ocr.firstStep(json_data)
    food_list = ocr.deleteComma(food_list)
    food_list = ocr.twoLineBill(food_list)
    food_list = ocr.concat(food_list)
    ocrList = ocr.delete(food_list)
    ocrList = ocr.makeList(ocrList)
    data = list(categoryModel.categorization(ocrList))
    print(data)
    {"data": data}
    try:
        return JsonResponse({"data":data}, safe=False, json_dumps_params={'ensure_ascii': False})
    except:
        return JsonResponse({
            "fff":111
        })

