from django.urls import path
from . import views

app_name = 'Receipt'

urlpatterns = [
    path('getItemInfo/<str:foodName>/', views.getItemInfo),
    path('getReceiptInfo/', views.getReceiptInfo),
    path('getCategoryInfo/', views.getCategoryInfo),
]
