<template>
  <nav>
    <v-toolbar dense color="">
      <v-toolbar-title class="grey--text">
        <v-img
          class="mt-2 mb-2"
          src="@/assets/logo.png"
          height="30"
          width="120"
          @click="$router.push('/').catch(() => {})"
          style="cursor: pointer"
        />
      </v-toolbar-title>

      <v-toolbar-title></v-toolbar-title>

      <v-spacer></v-spacer>
      <!-- 알람 -->
      <v-menu offset-y>
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
      </v-menu>

      <v-app-bar-nav-icon
        color="#88dba3"
        @click="drawer = true"
      ></v-app-bar-nav-icon>
    </v-toolbar>

    <v-navigation-drawer v-model="drawer" temporary right app>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>

          <PayButton />
          <br />
          <AddProduct />
        </v-list-item-group>
      </v-list>
      <template v-slot:append>
        <div class="pa-2">
          <v-btn block @click="logout">로그아웃</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </nav>
</template>

<script>
import UserApi from "../api/UserApi";
import PayButton from "../components/payment/Paytest";
import AddProduct from "../components/product/AddProduct";

const storage = window.sessionStorage;
export default {
  props: ["title"],
  components: {
    PayButton,
    AddProduct,
  },
  data() {
    return {
      userInfo: {},
      drawer: false,
      group: null,
      isAlarm: true,
      alarmDrawer: false,
      alarmGroup: null,
      alarmList: {
        alarm1: "알람1",
        alarm2: "알람2",
      },
    };
  },
  created() {
    // console.log("navbar created 들어옴");
    if (storage.getItem("jwt-auth-token")) {
      // console.log("스토리지에 저장되어서 if문 들어옴");
      UserApi.getUserInfo(
        storage.getItem("login_user"),
        (res) => {
          //console.log(res);
          this.userInfo = {
            email: res.data.object.userId,
            nickname: res.data.object.nickName,
          };
        },
        (error) => {
          //  console.log(error);
          alert("세션이 만료되었습니다! 다시 로그인 해주세요");
          this.$router.push({ path: "/" }).catch(() => {});
        }
      );
    } else {
      // console.log("스토리지에 없어서 else문 들어옴");
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "");
      UserApi.getUserInfo(
        storage.getItem("jwt-auth-token"),
        (res) => {
          //     console.log(res);
        },
        (error) => {
          //   console.log(error);
          alert("세션이 만료되었습니다! 다시 로그인 해주세요");
          this.$router.push({ path: "/" }).catch(() => {});
        }
      );
    }
  },
  methods:{
    logout(){
      // userinfo session만 없애는 거 & 해당 사용자 검색어 세션 삭제 
      storage.removeItem("jwt-auth-token");
      storage.removeItem("login_user");
      this.$router.push('/').catch(()=>{});
    }   
  },
};
</script>
<style scoped>
.v-btn {
  height: 24px;
  width: 24px;
}
</style>
