<template>
  <nav>
    <v-toolbar dense color="">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <v-toolbar-title class="grey--text" style="margin-left: 15%">
        <v-img
          class="mt-2 mb-2"
          src="@/assets/logo.png"
          height="30"
          width="120"
          @click="$router.push('/home').catch(() => {})"
          style="cursor: pointer"
        />
      </v-toolbar-title>

      <v-toolbar-title></v-toolbar-title>

      <v-spacer></v-spacer>
      <!-- 알람 -->
      <!-- <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            icon
            v-if="!isAlarm"
            @click="alarmMenu = true"
            v-bind="attrs"
            v-on="on"
          >
            <v-icon color="#88dba3">mdi-bell-outline</v-icon>
          </v-btn>
          <v-btn
            icon
            color="#88dba3"
            v-if="isAlarm"
            @click="alarmMenu = true"
            v-bind="attrs"
            v-on="on"
          >
            <v-badge color="red" dot left overlap>
              <v-icon>mdi-bell</v-icon>
            </v-badge>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="alarm in alarmList" :key="alarm">
            <v-list-item-title>{{ alarm }}</v-list-item-title>
          </v-list-item>
        </v-list>
        
      </v-menu> -->

      <v-btn icon v-if="!isAlarm" @click="alarmDrawer = true">
        <v-icon color="#88dba3">mdi-bell-outline</v-icon>
      </v-btn>
      <v-btn icon v-if="isAlarm" @click="alarmDrawer = true">
        <v-badge color="red" dot left overlap>
          <v-icon color="#88dba3">mdi-bell</v-icon>
        </v-badge>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer v-model="alarmDrawer" temporary right app>
      <v-list subheader>
        <v-subheader style="background-color: #e2efef">냉장고 공유</v-subheader>
        <v-list-item-group v-model="alarmGroup">
          <v-list-item
            v-for="(alarm, i) in alarmList"
            :key="i"
            @click="goShared(alarm)"
          >
            <v-img
              src="@/assets/fridge.png"
              height="30"
              width="20"
              style="margin-right: 5%"
            />
            <v-list-item-title
              >{{ alarm.nickName }}님이 {{ alarm.refrigName }}을
              공유했습니다.</v-list-item-title
            >
          </v-list-item>
        </v-list-item-group>
      </v-list>
      <v-divider></v-divider>
      <v-list subheader>
        <v-subheader style="background-color: #e2efef"
          >유통기한 임박</v-subheader
        >
        <v-list-item-group v-model="alarmGroup">
          <v-list-item 
            v-for="food in foodList" 
            :key="food.foodNo" 
            @click="moveDetail(food.foodNo)"
          >
            <v-img
              :src="$store.state.foodCategoryList[food.categoryNo].img"
              height="30"
              width="20"
              style="margin-right: 5%"
            />
            <v-list-item-title>{{ food.foodName }}</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer v-model="drawer" temporary app>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item @click="$router.push('/home').catch(() => {})">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item @click="$router.push('/productmain').catch(() => {})">
            <v-list-item-icon>
              <v-icon>mdi-cart</v-icon>
            </v-list-item-icon>
            <v-list-item-title>장보기</v-list-item-title>
          </v-list-item>

          <v-list-item @click="$router.push('/shoppinglist').catch(() => {})">
            <v-list-item-icon>
              <v-icon>mdi-shopping</v-icon>
            </v-list-item-icon>
            <v-list-item-title>장바구니</v-list-item-title>
          </v-list-item>

          <v-list-item
            v-if="userInfo.email == 'admin@ssafy.com'"
            @click="$router.push('/addproduct').catch(() => {})"
          >
            <v-list-item-icon>
              <v-icon>mdi-cart-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title>상품 등록</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
      <template v-slot:append>
        <div class="pa-2">
          <v-btn block @click="logout" color="#88dba3">로그아웃</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
    <v-dialog v-model="isSharedRef">
      <SharedRefrigerator
        @close="closeDialog"
        :alarm="SharedAlarm"
      ></SharedRefrigerator>
    </v-dialog>
  </nav>
</template>

<script>
import UserApi from "../api/UserApi";
import PayButton from "../components/payment/Paytest";
import AlarmApi from "../api/AlarmApi";
import RefApi from "../api/RefApi";
import SharedRefrigerator from "./SharedRefrigerator";
import FoodApi from "../api/FoodApi";
const storage = window.sessionStorage;
export default {
  props: ["title"],
  components: {
    SharedRefrigerator,
  },
  data() {
    return {
      userInfo: {},
      drawer: false,
      group: null,
      isAlarm: false,
      isSharedRef: false,
      alarmDrawer: false,
      alarmGroup: null,
      alarmList: [],
      foodList: [],
      SharedAlarm: null,
    };
  },
  created() {
    if (storage.getItem("jwt-auth-token")) {
      UserApi.getUserInfo(
        storage.getItem("login_user"),
        (res) => {
          this.userInfo = {
            email: res.data.object.userId,
            nickname: res.data.object.nickName,
            phone: res.data.object.phone,
            addr1: res.data.object.addr1,
            addr2: res.data.object.addr2,
            addr3: res.data.object.addr3,
          };
        },
        (error) => {
          alert("세션이 만료되었습니다! 다시 로그인 해주세요");
          this.$router.push({ path: "/" }).catch(() => {});
        }
      );
    } else {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "");
      UserApi.getUserInfo(
        storage.getItem("jwt-auth-token"),
        (res) => {
          
        },
        (error) => {
          alert("세션이 만료되었습니다! 다시 로그인 해주세요");
          this.$router.push({ path: "/" }).catch(() => {});
        }
      );
    }
  },
  async mounted() {
    const alarms = await this.getShare();
    if(alarms != null){
      this.isAlarm = true;
      for(var alarm of alarms){
        var data1 = {
          refrigNo: alarm.refrigNo
        }
        var refInfo = await this.getRefByNo(data1);
        var nickName = await this.getUserInfo(refInfo.userId);
        refInfo.nickName = nickName;
        this.alarmList.push(refInfo);
      } 
    }
    const refrigList = await this.getRefrig();
    if(refrigList != null){
      for(var ref of refrigList){
        const foodList = await this.getFood(ref.refrigNo);
        for(var food of foodList){
          var dateCur = new Date(food.registDate);
          dateCur.setDate(dateCur.getDate()+Number(food.expireDate));
          if((dateCur - new Date())/1000/60/60/24 <= 2){
            this.isAlarm = true;
            this.foodList.push(food);
          }
        }
      }
    }
  },
  methods: {
    logout() {
      storage.removeItem("jwt-auth-token");
      storage.removeItem("login_user");
      this.$router.push("/").catch(() => {});
    },
    getShare() {
      return new Promise((resolve) => {
        const data = {
          userId: storage.getItem("login_user"),
        };
        AlarmApi.getShare(
          data,
          (res) => {
            resolve(res.data.object);
          },
          (error) => {
            resolve(null);
          }
        );
      });
    },
    getRefByNo(data) {
      return new Promise((resolve) => {
        RefApi.getRefByNo(
          data,
          (res) => {
            resolve(res.data.object);
          },
          (error) => {}
        );
      });
    },
    getUserInfo(data) {
      return new Promise((resolve) => {
        UserApi.getUserInfo(
          data,
          (res) => {
            resolve(res.data.object.nickName);
          },
          (error) => {}
        );
      });
    },
    getRefrig() {
      return new Promise((resolve) => {
        const data = {
          userId: storage.getItem("login_user"),
        }
        RefApi.getRef(
          data,
          (res) => {
            resolve(res.data.object);
          },
          (error) => {
            resolve(null);
          }
        );
      });
    },
    getFood(refrigNo) {
      return new Promise((resolve) => {
        const data = {
          refrigNo: refrigNo,
        }
        FoodApi.getFoodList(
          data,
          (res) => {
            resolve(res.data.object);
          },
          (error) => {
            resolve(null);
          }
        );
      });
    },
    goShared(alarm) {
      this.SharedAlarm = alarm;
      this.isSharedRef = true;
    },
    closeDialog() {
      this.isSharedRef = false;
      location.reload();
    },
    moveDetail(foodNo) {
      storage.setItem('foodNo', foodNo);
      this.$router.push('/detail').catch(() => {
        location.reload();
      });
    },
  },
};
</script>
<style scoped>
.v-btn {
  height: 24px;
  width: 24px;
}
.v-subheader {
  font-size: 2.3vh;
}
</style>
