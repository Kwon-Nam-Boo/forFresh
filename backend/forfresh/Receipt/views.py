from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
# Create your views here.

@api_view(['GET'])
def getItemInfo(request, foodName):
    data = list(foodName)
    print(data)
    try:
        return JsonResponse({
            "foodName" : data,
            'category' : 1,
            'status' : "냉동",
        })
    except:
        return JsonResponse({
            "fff":111
        })

