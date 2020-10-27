<template>
  <nav>
    <v-toolbar dense color="#04B431">
      <v-toolbar-title class="grey--text">
        <!-- <v-img
          class="mt-2 mb-2"
          src="../assets/images/lolbti_logo_2.png"
          height="55"
          width="100"
          @click="$router.push('/').catch(() => {})"
          style="cursor: pointer"
        /> -->
      </v-toolbar-title>

      <v-toolbar-title>{{title}}</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon v-if="!isAlarm" @click="alarmDrawer = true">
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>
      <v-btn icon v-if="isAlarm" @click="alarmDrawer = true">
        <v-icon>mdi-bell</v-icon>
      </v-btn>

      
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
    </v-toolbar>

    <v-navigation-drawer
      v-model="alarmDrawer"
      absolute
      temporary
      bottom
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="alarmGroup"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item v-for="alarm in alarmList" :key="alarm">
            <v-list-item-title>{{alarm}}</v-list-item-title>
          </v-list-item>

          
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      right
    >
      <v-list
        nav
        dense
      >
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
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>

export default {
  props:['title'],
  data() {
    return {
      drawer: false,
      group: null,
      isAlarm: false,
      alarmDrawer: false,
      alarmGroup: null,
      alarmList: {
        alarm1: "알람1",
        alarm2: "알람2",
      },
    }
  },
};
</script>
<style scoped>

</style>
