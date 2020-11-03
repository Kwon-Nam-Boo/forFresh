from django.urls import path
from . import views

app_name = 'Receipt'

urlpatterns = [
    path('getItemInfo', views.getItemInfo),
]
