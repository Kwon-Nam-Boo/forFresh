<template>
  <v-main>
    <!-- 냉장고가 없을 때 -->
    <v-container 
      class="text-center fill-height"
      v-if="isNoItem"
    >
      <v-row class="align-center">
        <v-col>
          <img src="@/assets/img/isnoitem.png" width="200">
          <h1 class="display-2 primary--text">
            냉장고가 없어요!
          </h1>
          <p>냉장고를 추가해주세요</p>
          <v-btn
            dark
            large
            color="#88dba3"
            @click="addRef"
          >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        </v-col>
      </v-row>

    </v-container>
    <!-- 냉장고 고르기 탭 -->
    <v-tabs
      v-model="tab"
      background-color=""
      color="#88dba3"
      show-arrows
    >
      <v-tabs-slider color="#88dba3"></v-tabs-slider>

      <v-tab
        v-for="item in items"
        :key="item.refrigNo"
      >
        {{item.refrigName}}
      </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item.refrigNo"
      >
        <v-card
          color="="
          flat
        >
          <Refrigerator :refrigNo="item.refrigNo"></Refrigerator>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    <v-speed-dial
      v-model="fab"
      v-if="!isNoItem"
      bottom
      right
      fixed
      direction="top"
      transition="slide-y-reverse-transition"
    >
      <template v-slot:activator>
        <v-btn
          v-model="fab"
          color="#88dba3"
          dark
          fab
          small
        >
          <v-icon v-if="fab">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-format-list-bulleted-square
          </v-icon>
        </v-btn>
      </template>
        <!-- 냉장고 삭제 -->
        <v-btn
          fab
          dark
          small
          color="red"
          @click="deleteRef"
        >
          <v-icon>mdi-delete</v-icon>
        </v-btn>
        <!-- 냉장고 이름 편집 -->
        <v-btn
          fab
          dark
          small
          color="yellow"
          @click="editRef"
        >
          <v-icon>mdi-square-edit-outline</v-icon>
        </v-btn>
        <!-- 냉장고 공유 -->
        <v-btn
          fab
          dark
          small
          color="green"
          @click="shareRef"
        >
          <v-icon>mdi-share-variant</v-icon>
        </v-btn>
        <!-- 냉장고 추가 -->
        <v-btn
          fab
          dark
          small
          color="indigo"
          @click="addRef"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        
    </v-speed-dial>
    <v-dialog
      v-model="isAddRef"
    >
      <AddRefrigerator @close="closeDialog" @getRef="getRef"></AddRefrigerator>
    </v-dialog>
    <v-dialog
      v-model="isShareRef"
    >
      <ShareRefrigerator :item="items[tab]" @close="closeDialog"></ShareRefrigerator>
    </v-dialog>
    <v-dialog
      v-model="isDeleteRef"
    >
      <DeleteRefrigerator :item="items[tab]" @close="closeDialog" @getRef="getRef"></DeleteRefrigerator>
    </v-dialog>
    <v-dialog
      v-model="isEditRef"
    >
      <EditRefrigerator :item="items[tab]" @close="closeDialog" @getRef="getRef"></EditRefrigerator>
    </v-dialog>
  </v-main>
</template>

<script>
import Refrigerator from "../components/Refrigerator";
import AddRefrigerator from "../components/AddRefrigerator";
import ShareRefrigerator from "../components/ShareRefrigerator";
import DeleteRefrigerator from "../components/DeleteRefrigerator";
import EditRefrigerator from "../components/EditRefrigerator";
import RefApi from "../api/RefApi";
const storage = window.sessionStorage;
export default {
  components: {
    Refrigerator,
    AddRefrigerator,
    ShareRefrigerator,
    DeleteRefrigerator,
    EditRefrigerator,
  },
  async created() {
    this.$emit('updateTitle', '메인페이지');
    this.items = await this.getRef();
    if(this.items==[]||this.items==""||this.items==null){
      this.isNoItem = true;
    } else {
      this.isNoItem = false;
    }
  },
  data() {
    return {
      title:"메인페이지",
      tab: null,
      fab: false,
      items: [],
      isNoItem: false,
      isAddRef: false,
      isShareRef: false,
      isDeleteRef: false,
      isEditRef: false,
    }
  },
  methods: {
    addRef() {
      this.isAddRef=true;
    },
    shareRef() {
      this.isShareRef=true;
    },
    deleteRef() {
      this.isDeleteRef=true;
    },
    editRef() {
      this.isEditRef=true;
    },
    closeDialog() {
      this.isAddRef=false;
      this.isShareRef=false;
      this.isDeleteRef=false;
      this.isEditRef=false;
    },
    getRef() {
      return new Promise(resolve => {
        const data = {
          userId: storage.getItem("login_user"),
        }
        RefApi.getRef(
          data,
          (res) => {
            resolve(res.data.object);
          },
          (error) => {
            resolve([]);
          }
        )
      });
    }
  }
};
</script>
<style>

</style>
