<template>
  <v-carousel
    v-model="model"
    vertical
    vertical-delimiters
    hide-delimiter-background
    touchless
    height="100%"
    v-touch="{ up: () => swipe('Up'), down: () => swipe('Down') }"
  >
    <v-carousel-item
      v-for="(pic, i) in pics"
      :key="i"
      :src="require(`@/assets/${pic.src}`)"
    >
      <!-- <v-sheet
        :color="color"
        height="100%"
        tile
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-3">
            Slide {{ i + 1 }}
          </div>
        </v-row>
      </v-sheet> -->
      <!-- <div class="move">
      <v-btn
          class="mt-13"
          @click="$router.push('/').catch(() => {})" >
          forfresh로 이동하기
          
          </v-btn>
      </div> -->
      <a  @click="$router.push('/login').catch(() => {})" class="btn">forfresh   >></a>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  created() {
    this.$emit("updateTitle", null);
  },
  data() {
    return {
      model: 0,
      
      pics:[
        {src:"slide5.png"},
        {src:"slide4.png"},
      ]
    };
  },
  methods: {
    //위아래로 드래그하여 화면전환
    swipe(direction) {
      if (direction.includes("Up")) {
        this.model++;
      } else if (direction.includes("Down")) {
        this.model--;
      }
    },
  },
};
</script>
<style scoped>
.move{
  float:right;
}
.vtn {
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   height: 5vh;
   width: 1em;
   background: #7cd4d4;
   
}
.btn {
   font-size: 1em;
   font-weight:bold;
   font-family:Roboto;
   border: 3px solid rgb(58, 58, 58);
   color:green;
   text-decoration: none;
   border-radius: 100em;
   padding: 10px 20px;
   letter-spacing: 0.05em;
   position: relative;
   transition: 0.3s;
   float:right;
   margin-top: 5%;
   
}
.btn::before {
   content: '';
   border: 3px solid #f9f6f1;
   width: 100%;
   height: 100%;
   position: absolute;
   left: 3px;
   top: 2px;
   border-radius: 100em;
   background: #f9f6f1;
   z-index: -1;
   transition: 0.3s;
}
.btn:hover::before {
   left:0;
   top:0;
   width: calc(100% - 4px);
   height: calc(100% - 4px);
}
</style>
